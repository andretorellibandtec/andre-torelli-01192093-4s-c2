package com.example.continuada02

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun listar(view: View) {
        getData()
    }

    fun cadastrar(view: View) {}

    fun getData() {
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

                }
            }
        })

    }


}