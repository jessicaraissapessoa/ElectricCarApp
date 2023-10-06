package br.com.jessicaraissapessoa.eletriccarapp.ui

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.jessicaraissapessoa.eletriccarapp.R
import br.com.jessicaraissapessoa.eletriccarapp.data.CarFactory
import br.com.jessicaraissapessoa.eletriccarapp.domain.Carro
import br.com.jessicaraissapessoa.eletriccarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.net.URL

class CarFragment : Fragment() {

    lateinit var fabCalcular : FloatingActionButton
    lateinit var listaCarros : RecyclerView

    override fun onCreateView( //Esse onCreateView é onde vai nos dar a possibilidade de retornar um layout que a gente precisa
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
        setupListeners()
    }

    fun setupView(view: View) {
        view.apply {
            fabCalcular = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carros)
        }
    }

    fun setupList() {

        //listaCarros.layoutManager = LinearLayoutManager(this) aplicamos no recyclerView em activity_main.xml

        val adapter = CarAdapter(CarFactory.list) //Acesso direto à CarFactory por ser object
        listaCarros.adapter = adapter
    }

    fun setupListeners() {
        fabCalcular.setOnClickListener {
            MyTask().execute("https://igorbag.github.io/cars-api/cars.json")
            //startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }

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

                var response = urlConnection.inputStream.bufferedReader().use { it.readText() } //stream dos dados que são trafegados pela internet

                publishProgress(response) //Publica progresso para nosso método do background

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

                for (i in 0 until jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).getString("id")
                    Log.d("ID ->", id)

                    val preco = jsonArray.getJSONObject(i).getString("preço")
                    Log.d("preço ->", preco)

                    val bateria = jsonArray.getJSONObject(i).getString("bateria")
                    Log.d("bateria ->", bateria)

                    val potencia = jsonArray.getJSONObject(i).getString("potência")
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
                    Log.d("Model ->", model.toString())
                }

            } catch (ex: Exception) {
                Log.e("Erro ->", ex.message.toString())
            }
        }

    }
}