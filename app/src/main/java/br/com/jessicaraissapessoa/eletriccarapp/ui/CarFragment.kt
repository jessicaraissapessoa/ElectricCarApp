package br.com.jessicaraissapessoa.eletriccarapp.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.jessicaraissapessoa.eletriccarapp.R
import br.com.jessicaraissapessoa.eletriccarapp.data.CarsApi
import br.com.jessicaraissapessoa.eletriccarapp.domain.Carro
import br.com.jessicaraissapessoa.eletriccarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.URL

class CarFragment : Fragment() {

    lateinit var fabCalcular : FloatingActionButton
    lateinit var listaCarros : RecyclerView
    lateinit var progress : ProgressBar
    lateinit var noInternetImage : ImageView
    lateinit var noInternetText : TextView
    lateinit var carsApi: CarsApi

    var carrosArray : ArrayList<Carro> = ArrayList()

    override fun onCreateView( //Esse onCreateView é onde vai nos dar a possibilidade de retornar um layout que a gente precisa
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
        setupView(view)
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        if (checkForInternet(context)) { //Se tem internet, chama o serviço
            //callService() -> Essa é outra forma de chamar serviço
            getAllCars()
        } else { //Se não tiver internet, ocultamos informações e exibir o aviso de falta de conexão
            emptyState()
        }
    }

    fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://igorbag.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create()) //Converter do JSON
            .build()

        carsApi = retrofit.create(CarsApi::class.java)
    }

    fun getAllCars() { //Método para pegar API e falar que é uma fila de execução

        carsApi.getAllCars().enqueue(object : Callback<List<Carro>> {

            override fun onResponse(call: Call<List<Carro>>, response: Response<List<Carro>>) {
                if (response.isSuccessful) {
                    progress.isVisible = false
                    noInternetImage.isVisible = false
                    noInternetText.isVisible = false

                    response.body()?.let {
                        setupList(it) //Passando se for diferente de nulo
                    }
                } else {
                    Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Carro>>, t: Throwable) {
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun emptyState() {
        progress.isVisible = false
        listaCarros.isVisible = false
        noInternetImage.isVisible = true
        noInternetText.isVisible = true
    }

    fun setupView(view: View) {
        view.apply {
            fabCalcular = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carros)
            progress = findViewById(R.id.pb_loader)
            noInternetImage = findViewById(R.id.iv_empty_state)
            noInternetText = findViewById(R.id.tv_no_wifi)
        }
    }

    fun setupList(lista : List<Carro>) {
        val carroAdapter = CarAdapter(lista)
        listaCarros.apply {
            isVisible = true
            adapter = carroAdapter
        }
    }

    fun setupListeners() {
        fabCalcular.setOnClickListener {
            startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }

    fun callService() {
        val urlBase = "https://igorbag.github.io/cars-api/cars.json"
        progress.isVisible = true
        MyTask().execute(urlBase)
    }

    fun checkForInternet(context: Context?) : Boolean {

        //Pegando dentro do sistema operacional Android o serviço de conectividade
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //Tratativas:
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //Se for Android M tem um comportamento
            val network = connectivityManager.activeNetwork?: return false //Pegando se tem internet ativa
            val activeNetwork = connectivityManager.getNetworkCapabilities(network)?: return false //Vendo se ela tem capacidade de conexão

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    //Utilizar o retrofit como absttração do AsyncTask!
    inner class MyTask : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            //Executado antes de rodar
            super.onPreExecute()
            Log.d("MyTask", "Iniciando...")
        }

        override fun doInBackground(vararg url: String?): String { //É quem faz as coisas executarem por debaixo dos panos

            var urlConnection : HttpURLConnection? = null

            try { //try/catch é sempre bom de ser usado quando dependemos de algo externo
                val urlBase = URL(url[0])

                urlConnection = urlBase.openConnection() as HttpURLConnection //abrindo conexão com a internet
                urlConnection.connectTimeout = 60000 //para saber quanto tempo (milisegundos) que vai demorar e vamos respeitar
                urlConnection.readTimeout = 60000 //definição do tempo de leitura com readTimeout
                urlConnection.setRequestProperty( //Nosso endpoint só aceita esse tipo de informação
                    "Accept",
                    "application/json"
                )

                val responseCode = urlConnection.responseCode

                if (responseCode == HttpURLConnection.HTTP_OK) { //Fe for ok faz sentido eu pegar esse responde e publicar
                    var response = urlConnection.inputStream.bufferedReader().use { it.readText() } //stream dos dados que são trafegados pela internet

                    publishProgress(response) //Publica progresso para nosso método do background
                } else {
                    Log.e("Erro", "Serviço indisponível no momento...")
                }

            } catch (ex: Exception) {
                Log.e("Erro", "Erro ao ralizar processamento...")
            } finally { //Quando esse try/catch terminar
                urlConnection?.disconnect() //Para não ter recurso desperdiçado
            }

            return ""
        }

        override fun onProgressUpdate(vararg values: String?) { //Que é quando nossos dados estarão sendo atualizados
            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray

                Log.d("Resposta JSON", values[0]!!)

                for (i in 0 until jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).getString("id")
                    Log.d("ID ->", id)

                    val preco = jsonArray.getJSONObject(i).getString("preco")
                    Log.d("preço ->", preco)

                    val bateria = jsonArray.getJSONObject(i).getString("bateria")
                    Log.d("bateria ->", bateria)

                    val potencia = jsonArray.getJSONObject(i).getString("potencia")
                    Log.d("potência ->", potencia)

                    val recarga = jsonArray.getJSONObject(i).getString("recarga")
                    Log.d("recarga ->", recarga)

                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")
                    Log.d("urlPhoto ->", urlPhoto)

                    val model = Carro(
                        id = id.toInt(),
                        preco = preco,
                        bateria = bateria,
                        potencia = potencia,
                        recarga = recarga,
                        urlPhoto = urlPhoto
                    )

                    carrosArray.add(model)
                    Log.d("Model ->", model.toString())
                }

                progress.isVisible = false
                noInternetImage.isVisible = false
                noInternetText.isVisible = false

                //setupList()

            } catch (ex: Exception) {
                Log.e("Erro ->", ex.message.toString())
            }
        }

    }
}