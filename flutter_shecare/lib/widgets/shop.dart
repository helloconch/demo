import 'package:flutter/material.dart';
import 'package:flutter_webview_plugin/flutter_webview_plugin.dart';

final shopUrl = 'https://wechat.shecarefertility.com/dist/index.html?';

class Shop extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes: {
        "/": (_) => new WebviewScaffold(
              url: shopUrl,
            ),
      },


    );
  }
}
