package jp.co.penguin.gourmetsearch.data.samplecode

import android.os.AsyncTask
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class SampleOkHttpClient(val listener: SampleOkHttpClientListener) : AsyncTask<Unit, Unit, String>() {

    override fun doInBackground(vararg params: Unit?): String? {
        val client = HttpClient()
        val stream = BufferedInputStream(
                client.get("https://randomuser.me/api"))
        return readStream(stream)
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        if (result != null) {
            listener?.onFetched(result)
        }

    }

    private fun readStream(inputStream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }

    inner class HttpClient {
        fun get(url: String): InputStream {
            val request = Request.Builder().url(url).build()
            val response = OkHttpClient().newCall(request).execute()
            val body = response.body()

            return body!!.byteStream()
        }
    }

    interface SampleOkHttpClientListener {
        fun onFetched(result: String)
    }

}

/* Usage:
    val client = SampleOkHttpClient(object : SampleOkHttpClient.SampleOkHttpClientListener {
        override fun onFetched(result: String) {
            print(result)
        }
    }).execute()
 */