package com.javeria.booklisting

import android.util.Log
import com.javeria.booklisting.remote.Constants
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset

object Utils {

    fun createUrl(stringUrl: String): URL? {
        var url: URL? = null
        try {
            url = URL(stringUrl)
        } catch (ex: MalformedURLException) {
            Log.e("ERROR", "Error with creating URL", ex)
        }
        return url
    }

    fun getSearchQueryResponse(url: URL): String? {
        var jsonResponse = ""

        var httpUrlConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        try {
            val httpUrlConnection = url.openConnection() as HttpURLConnection
            httpUrlConnection.requestMethod = Constants.getMethod
            httpUrlConnection.setReadTimeout(10000 /* milliseconds */)
            httpUrlConnection.setConnectTimeout(15000 /* milliseconds */)
            httpUrlConnection.connect()
            val responseCode = httpUrlConnection.responseCode
            if (responseCode.equals(Constants.statusCode)) {
                inputStream = httpUrlConnection.inputStream
                jsonResponse = readFromStream(inputStream) ?: ""

            }
        } catch (ex: Exception) {
            Log.e("ERROR", ex.toString())
        }
        finally {
            if (httpUrlConnection != null) httpUrlConnection.disconnect()
            if (inputStream != null) inputStream.close()
        }
        return jsonResponse
    }

    private fun readFromStream(inputStream: InputStream?): String? {
        val jsonAsString = StringBuilder()

        try {
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(
                    inputStream,
                    Charset.forName("UTF-8")
                )
                val bufferedReader =
                    BufferedReader(inputStreamReader)
                var line = bufferedReader.readLine()
                while (line != null) {
                    jsonAsString.append(line)
                    line = bufferedReader.readLine()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return jsonAsString.toString()
    }
}