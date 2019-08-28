import 'package:url_launcher/url_launcher.dart';
import 'package:flutter/material.dart';

/**
 * 引用第三方库
 */
class MyThirdApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: '使用第三方库',
      home: new Scaffold(
        appBar: new AppBar(
          title: new Text('使用url库'),
        ),
        body: new Center(
          child: new RaisedButton(onPressed: () {
            const url = "https://www.baidu.com";
            launch(url);
          }, child: new Text('open baidu'),),
        ),
      ),
    );
  }
}