package com.gyanhub.todayclube99.time

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

private var requestQueue: RequestQueue? = null

class GetTime {

    fun timeAndDate(volleyCallBack: VolleyCallBack, context: Context) {
        requestQueue = Volley.newRequestQueue(context)
        val url =
            "https://www.timeapi.io/api/Time/current/coordinate?latitude=23.1763&longitude=75.7730"
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                volleyCallBack.onGetTime(
                    response.getString("date").toString(),
                    response.getString("time").toString()
                )

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }

}

interface VolleyCallBack {
    fun onGetTime(date: String, time: String)
}