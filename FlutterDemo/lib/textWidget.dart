import 'package:flutter/material.dart';

class TextDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Text Widget',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Text Widget'),
        ),
        body: Center(
            child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('Hello World', textAlign: TextAlign.center),
            Text(
              'Hello World I am Jack' * 3,
              maxLines: 1,
              overflow: TextOverflow.ellipsis,
            ),
            Text('Hello World', textScaleFactor: 2),
            Text(
              'Hello World',
              style: TextStyle(
                  background: new Paint()..color = Colors.yellow,
                  color: Colors.blue,
                  fontSize: 18,
                  height: 1.2,
                  decoration: TextDecoration.underline,
                  decorationStyle: TextDecorationStyle.dotted),
            ),
            Text.rich(TextSpan(children: [
              TextSpan(text: "Home:"),
              TextSpan(
                  text: "https://www.baidu.com",
                  style: TextStyle(color: Colors.blue)),
            ])),
            DefaultTextStyle(
              style: TextStyle(color: Colors.red, fontSize: 20.0),
              textAlign: TextAlign.start,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text('Hello world'),
                  Text('I am Jack'),
                  Text('I am Jack',
                      style: TextStyle(inherit: false, color: Colors.grey))
                ],
              ),
            )
          ],
        )),
      ),
    );
  }
}
