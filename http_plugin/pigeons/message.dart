import 'package:pigeon/pigeon.dart';

@ConfigurePigeon(PigeonOptions(
  dartOut: 'lib/pigeon/messages.g.dart',
  dartOptions: DartOptions(),
  kotlinOut:
      'android/app/src/main/kotlin/jp/co/u0235/floating_ai_robo/plugins/http_plugin/Messages.g.kt',
      
  kotlinOptions: KotlinOptions(),
  javaOut: 'android/app/src/main/java/io/flutter/plugins/Messages.java',
  javaOptions: JavaOptions(),
  dartPackageName: 'jp.co.u0235.floating_ai_robo.plugins.http_plugin',
))

class HttpSetting {
  HttpSetting({
    required this.url,
  });
  String url;
}

@HostApi()
abstract class HttpSettingsAapi {
  void addHttpSetting(HttpSetting httpSetting);
}
