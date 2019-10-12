import 'package:flutter/material.dart';
import 'browser.dart';
import 'colors.dart';

class PageDetails extends StatelessWidget {
  final String url;
  final String title;

  PageDetails({Key key, this.url, this.title}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('$title'),
        backgroundColor: YColors.colorPrimary,
      ),
      body: Browser(
        url: url,
      ),
    );
  }
}
