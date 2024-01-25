package jp.co.u0235.floating_ai_robo_plugin.template

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.AppCompatButton

class ShareVisionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_vision)
        findViewById<AppCompatButton>(R.id.camera_button).setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(
                "uri",
                "content://jp.co.u0235.floating-ai-robo-plugin.template.provider/get_image"
            )
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}