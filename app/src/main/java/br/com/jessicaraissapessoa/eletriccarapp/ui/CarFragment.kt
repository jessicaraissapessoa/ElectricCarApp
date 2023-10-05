package br.com.jessicaraissapessoa.eletriccarapp.ui

import android.content.Intent
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
import br.com.jessicaraissapessoa.eletriccarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
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
            startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }

    inner class GetCarInformations : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            //Executado antes de rodar
            super.onPreExecute()
            Log.d("MyTask", "Iniciando...")
        }

        override fun doInBackground(vararg url: String?): String { //É quem faz as coisas executarem por debaixo dos panos

            var urlConnection : HttpURLConnection? = null

            try { //try/catch é sempre bom de ser usado quando dependemos de algo externo
                val urlBase = URL(url[0])

                //Para fazer uma requisição, precisamos abrir uma conexão com a internet:
                urlConnection = urlBase.openConnection() as HttpURLConnection

                //Colocar um connectTimeout para saber quanto tempo (milisegundos) que vai demorar e vamos respeitar:
                urlConnection.connectTimeout = 60000

                //Agora vamos definir o tempo de leitura com readTimeout:
                urlConnection.readTimeout = 60000

                //Agora vamos criar o imputStream que vai ser o stream dos dados que são trafegados pela internet:
                var inString = streamToString(urlConnection.inputStream)

                //Publica a inString para nosso método do background
                publishProgress(inString)

            } catch (ex: Exception) {
                Log.e("Erro", "Erro ao ralizar processamento...")
            } finally { //Quando esse try/catch terminar
                urlConnection?.disconnect() //Para não ter recurso desperdiçado
            }

            return ""
        }

        override fun onProgressUpdate(vararg values: String?) { //Que é quando nossos dados estarão sendo atualizados
            try {
                var json  : JSONObject
                values[0]?.let { //Só pega se for não nulo
                    json  = JSONObject(it)
                }

            } catch (ex: Exception) {

            }
        }

        fun streamToString(inputStream: InputStream) : String {

            val bufferReader = BufferedReader(InputStreamReader(inputStream))
            var line : String
            var result = ""

            try {
                do {
                    line = bufferReader.readLine()
                    line?.let {
                        result += line
                    }
                } while (line != null)
            } catch (ex: Exception) {
                Log.e("Erro", "Erro ao parcelar Stream")
            }
            return result
        }

    }
}