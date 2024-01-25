# TemplateContentProvider 📱

## 概要
`TemplateContentProvider`は、[Floating AI robo](https://play.google.com/store/apps/details?id=jp.co.u0235.floating_ai_robo)プラグイン開発のためのスタートポイントです！🚀 このテンプレートは、プラグインの基本枠組みと、特定機能へのクエリ応答方法を示しています。

## Floating AI roboプラグインの詳細 🪄
- Floating AI roboで使えるToolsの詳細情報を提供します！🔍
- Toolsの実行結果をFloating AI roboに返します！💫

## プラグインの設定 🛠️
### Androidマニフェスト設定
- Floating AI roboは[jp.co.u0235.floating_ai_robo.ACTION_TOOLS](https://github.com/0235-jp/floating-ai-robo-plugin-template/blob/26f726f485d66c9cf3197d3881016d98a7408bb4/app/src/main/AndroidManifest.xml#L23C26-L23C26)が設定されたproviderをプラグインと認識します。🧐
- `[jp.co.u0235.floating_ai_robo.permission.TOOLS_READ](https://github.com/0235-jp/floating-ai-robo-plugin-template/blob/26f726f485d66c9cf3197d3881016d98a7408bb4/app/src/main/AndroidManifest.xml#L21C39-L21C39)パーミッションでプラグインにアクセスします。🔑

### [プラグインの実装](https://github.com/0235-jp/floating-ai-robo-plugin-template/blob/26f726f485d66c9cf3197d3881016d98a7408bb4/app/src/main/java/jp/co/u0235/floating_ai_robo_plugin/template/TemplateContentProvider.kt#L51)
- `query()`メソッドを通じてFloating AI roboがプラグインにアクセスします。✨
- `tools`エンドポイントにクエリを行い、プラグインの機能一覧と詳細をcursorにセットして返します。📜
  - パラメータ：
    - `source`: データソース (provider or activity)。
    - `target`: アクセスターゲット (providerの場合はURI, activityの場合はClassName)。
    - `displayName`: アプリ上に表示されるツール名 🏷️。
    - `functionName`: AIが認識する機能名 🤖。
    - `description`: AIが認識する機能の説明 📖。
    - `displayDescription`: アプリ上に表示される機能説明 📖。
    - `parametersSchema`: AIが認識するパラメータのJSON文字列。

### [sourceがproviderの場合の処理](https://github.com/0235-jp/floating-ai-robo-plugin-template/blob/26f726f485d66c9cf3197d3881016d98a7408bb4/app/src/main/java/jp/co/u0235/floating_ai_robo_plugin/template/TemplateContentProvider.kt#L69C22-L69C22)
- Floating AI roboは`target`に設定されたURIに`parametersSchema`で指定したパラメータをpathSegmentsに設定してqueryを実行します。
- 処理結果は`type`と`result`カラムをcursorにセットして返します。
  - `type`: talk, text, image, noneのいずれか。
    - `talk`: Floating AI roboは`result`に設定されたテキストをそのまま読み上げます。
    - `text`: Floating AI roboはは`result`に設定された文字列をAIで処理して読み上げます。
    - `image`: Floating AI roboはは`result`に設定されたイメージオブジェクトをAIで処理して読み上げます。
      - base64にエンコードされた画像もしくはurlを受け付けます。
    - `none`: Floating AI roboはは何もせず待機状態に遷移します。

### [sourceがactivityの場合の処理](https://github.com/0235-jp/floating-ai-robo-plugin-template/blob/26f726f485d66c9cf3197d3881016d98a7408bb4/app/src/main/java/jp/co/u0235/floating_ai_robo_plugin/template/SharVisionActivity.kt#L17)
- Floating AI roboは`target`に設定されたActivityに`parametersSchema`で指定したパラメータをextraに設定してアクセスします。
- ActivityResultで{Key: uri, Value: providerのURI}をExtraに設定して終了してください。
- その後、Floating AI roboはProviderにパラメータなしでqueryを実行します。cursorの設定値はsourceがproviderの場合と同様です。
