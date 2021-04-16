package com.example.continuada02

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListarCachorros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_cachorros)

        getData()

    }

    fun getData() {

        var indicados: Int = 0
        var naoIndicados: Int = 0
        var total:Int = 0


        val tvIndicados : TextView = findViewById(R.id.tv_indicados)
        val tvNaoIndicados : TextView = findViewById(R.id.tv_nao_indicados)
        val tvTotal : TextView = findViewById(R.id.tv_total)


        val retrofitClient = ConfigApi
            .getRetrofitInstance("https://5f861cfdc8a16a0016e6aacd.mockapi.io")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getCachorros()

        callback.enqueue(object : Callback<List<Cachorros>> {
            override fun onFailure(call: Call<List<Cachorros>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Cachorros>>, response: Response<List<Cachorros>>) {
                response.body()?.forEach {

                    val container: LinearLayout = findViewById(R.id.container_cachorros)

                    val tvCachorro = TextView(baseContext)
                    tvCachorro.text = "Id: ${it.id} - Raça: ${it.raca} - Preço médio: ${it.precoMedio} - crianças: ${it.indicadoCriancas}"
                    tvCachorro.setTextColor(Color.parseColor("#9911AA"))

                    container.addView(tvCachorro)

                    if (it.indicadoCriancas == true){
                        indicados += 1
                        total += 1
                    }else{
                        naoIndicados += 1
                        total += 1
                    }

                }

                tvIndicados.text = "Cachorros indicados para crianças: ${indicados.toString()}"
                tvNaoIndicados.text = "Cachorros indicados para crianças: ${naoIndicados.toString()}"
                tvTotal.text = "Total de Cachorros: ${total.toString()}"

            }
        })

    }

}