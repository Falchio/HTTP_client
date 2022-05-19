import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


private const val URL_ADDRESS = "http://localhost:8080/obo_messaging/api/send"
private const val JSON_PARAM_NAME = "json"
private const val JSON_VALUE =
    "[{\"park\":\"park1\", \"count\":5}, {\"park\":\"park2\", \"count\":10},{\"park\":\"park3\",\"count\":333}]"

fun main() {
    sendGetRequest(JSON_PARAM_NAME, JSON_VALUE)
}


fun sendGetRequest(paramName: String, paramValue: String) {
    val encodedParamName = URLEncoder.encode(paramName, "UTF-8")
    val encodedParamValue = URLEncoder.encode(paramValue, "UTF-8")
    val reqParam = "$encodedParamName=$encodedParamValue"


    val url = URL("$URL_ADDRESS?$reqParam")

    with(url.openConnection() as HttpURLConnection) {
        // optional default is GET
        requestMethod = "GET"

        println("URL : $url")
        println("Response Code : $responseCode")

        BufferedReader(InputStreamReader(inputStream)).use {
            val response = StringBuffer()

            var inputLine = it.readLine()
            while (inputLine != null) {
                response.append(inputLine)
                inputLine = it.readLine()
            }
            it.close()
            println("Response : $response")
        }
    }
}
