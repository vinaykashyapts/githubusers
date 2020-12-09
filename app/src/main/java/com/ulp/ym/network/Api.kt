package com.ulp.ym.network

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object Api {

    internal fun httpGet(myURL: String?): String {

        val inputStream: InputStream
        val result:String

        // create URL
        val url: URL = URL(myURL)

        // create HttpURLConnection
        val conn: HttpURLConnection = url.openConnection() as HttpURLConnection

        // make GET request to the given URL
        conn.connect()

        // receive response as inputStream
        inputStream = conn.inputStream

        // convert inputstream to string
        if(inputStream != null)
            result = convertInputStreamToString(inputStream)
        else
            result = "Did not work!"

        return result
    }

    private fun convertInputStreamToString(inputStream: InputStream): String {
        val bufferedReader:BufferedReader? = BufferedReader(InputStreamReader(inputStream))

        var line:String? = bufferedReader?.readLine()
        var result:String = ""

        while (line != null) {
            result += line
            line = bufferedReader?.readLine()
        }

        inputStream.close()
        return result
    }
}