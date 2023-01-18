package com.klaatu.rest2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getPic(view: View)
    {
        Ion.with(this)
            .load("https://api.thecatapi.com/api/images/get?format=json&size=med&results_per_page=3")
            .asString()
            .setCallback({e,result->
             Log.d("msg","JSON es: \n$result")
                processPic(result)
            })
    }


 fun processPic(result:String)
 {
     val json = JSONObject("{\"images\":$result}")
     val arrayIMGS = json.getJSONArray("images")
     val firstIMG = arrayIMGS.getJSONObject(0)
     val URL = firstIMG.getString("url")

     Picasso.get()
         .load(URL)
         .into(imageView)
 }
}