import 'package:flutter/material.dart';
import 'package:webview_flutter/webview_flutter.dart';
import 'pagedetails.dart';

class Browser extends StatelessWidget {
  final String url;
  final String title;

  const Browser({Key key, this.url, this.title}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    double statusBarHeight = MediaQuery.of(context).padding.top;
    return Container(
      child: WebView(
        initialUrl: url,
        javascriptMode: JavascriptMode.unrestricted,
        javascriptChannels: <JavascriptChannel>[
          _javascriptChannel(context),
        ].toSet(),
        navigationDelegate: (NavigationRequest request) {
          print('blocking navigation to $request}');
          if (request.url != url) {
            Navigator.push(context, new MaterialPageRoute(builder: (context) {
              return PageDetails(
                url: request.url,
                title: '知识',
              );
            }));
            return NavigationDecision.prevent;
          } else {
            return NavigationDecision.navigate;
          }
        },
      ),
    );
  }

  JavascriptChannel _javascriptChannel(BuildContext context) {
    return JavascriptChannel(
        name: 'AnalyticsClickListener',
        onMessageReceived: (JavascriptMessage message) {});
  }
}
