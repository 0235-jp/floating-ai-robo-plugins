package jp.co.u0235.floating_ai_robo.plugins.http_plugin

import HttpSetting
import HttpSettingsAapi
import android.widget.Toast
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity: FlutterActivity(), HttpSettingsAapi {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        HttpSettingsAapi.setUp(flutterEngine.dartExecutor, this)
        super.configureFlutterEngine(flutterEngine)
    }

    override fun addHttpSetting(httpSetting: HttpSetting) {
        Toast.makeText(context, httpSetting.url, Toast.LENGTH_SHORT).show()
    }
}
