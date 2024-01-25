package jp.co.u0235.floating_ai_robo.plugins.http_plugin

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream

class HttpPluginContentProvider : ContentProvider() {

    private val toolWeather = arrayOf(
        "provider", "content://jp.co.u0235.floating-ai-robo-plugin.template.provider/get_current_weather",
        "get weather", "get_current_weather", "Get the current weather in a given location",
        "Get the current weather in a given location", "{\n" +
                "          \"type\": \"object\",\n" +
                "          \"properties\": {\n" +
                "            \"location\": {\n" +
                "              \"type\": \"string\",\n" +
                "              \"description\": \"The city and state, e.g. Tokyo\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"required\": [\"location\"]\n" +
                "        }"
    )
    private val toolCamera = arrayOf(
        "activity", "jp.co.u0235.floating_ai_robo_plugin.template.CameraActivity",
        "open camera", "open_camera", "open camera app",
        "open camera app", "{}"
    )
    private val toolSharVision = arrayOf(
        "activity", "jp.co.u0235.floating_ai_robo_plugin.template.ShareVisionActivity",
        "shar vision", "shar_image", "Share your vision with users using the camera.",
        "Share your vision with users using the camera.", "{}"
    )

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor {
        val pathSegments = uri.pathSegments
        if (pathSegments.isEmpty()) return MatrixCursor(arrayOf("Error"))
        return when (pathSegments.last()) {
            "tools" -> {
                val cursor = MatrixCursor(
                    arrayOf(
                        "source",
                        "target",
                        "displayName",
                        "functionName",
                        "description",
                        "displayDescription",
                        "parametersSchema"
                    )
                )
                cursor.addRow(toolWeather)
                cursor.addRow(toolSharVision)
                cursor.addRow(toolCamera)
                cursor
            }

            toolWeather[3] -> {
                val cursor = MatrixCursor(arrayOf("type", "result"))
                val location = uri.getQueryParameter("location")
                if (location.equals("tokyo", ignoreCase = true)) {
                    cursor.addRow(arrayOf("text", "{\"Weather\": \"snow\", \"3 Celsius\"}"))
                } else if (location.equals("london", ignoreCase = true)) {
                    cursor.addRow(arrayOf("text", "{\"Weather\": \"rain\", \"15 Celsius\"}"))
                } else {
                    cursor.addRow(arrayOf("text", "not support location"))
                }
                cursor
            }

            "open_camera" -> {
                val cursor = MatrixCursor(arrayOf("type", "result"))
                cursor.addRow(arrayOf("talk", "opened the camera."))
                cursor
            }

            "get_image" -> {
                val cursor = MatrixCursor(arrayOf("type", "result"))
                val `is` = context!!.assets.open("sample_image.png")
                val bitmap = BitmapFactory.decodeStream(`is`)
                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                val byteArray = outputStream.toByteArray()
                `is`.close()
                val baseString = Base64.encodeToString(byteArray, Base64.NO_WRAP)
                cursor.addRow(arrayOf("image", "data:image/png;base64,$baseString"))
                // or use url
                // cursor.addRow(arrayOf("image", "https://image.png" ))
                cursor
            }

            else -> {
                val cursor = MatrixCursor(arrayOf("type", "result"))
                cursor.addRow(arrayOf("none", ""))
                cursor
            }
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        throw UnsupportedOperationException("Not yet implemented")
    }
}