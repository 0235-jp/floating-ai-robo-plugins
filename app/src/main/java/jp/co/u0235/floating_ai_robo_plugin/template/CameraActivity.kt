package jp.co.u0235.floating_ai_robo_plugin.template

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import androidx.activity.ComponentActivity

class CameraActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        val resultIntent = Intent()
        resultIntent.putExtra(
            "uri",
            "content://jp.co.u0235.floating-ai-robo-plugin.template.provider/open_camera"
        )
        setResult(RESULT_OK, resultIntent)
        finish()
        // The camera needs to start after the results are returned,
        // so there is a slight delay in starting the camera.
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            applicationContext!!.startActivity(intent)
        }, 100)
    }
}