import 'package:flutter/material.dart';

/**
 * 使用主题
 */
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final appName = '自定义主题';
    return new MaterialApp(
      title: appName,
      theme: new ThemeData(
          brightness: Brightness.light,
          primaryColor: Colors.lightBlue[600],
          accentColor: Colors.orange[600]
      ),
      home: new MyHomePage(title: appName),
    );
  }
}

class MyHomePage extends StatelessWidget {

  final String title;

  MyHomePage({Key key, @required this.title}) :super(key: key)

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text(title),
      ),
      body: new Center(
        child: new Container(
          color: Theme
              .of(context)
              .accentColor,
          child: new Text(
              'Flutter',
              style: Theme
                  .of(context)
                  .textTheme
                  .title),
        ),
      ),

      floatingActionButton: new Theme(
          data: Theme.of(context).copyWith(accentColor: Colors.grey),
          child: new FloatingActionButton(
            onPressed: null, child: new Icon(Icons.computer),)),
    );
  }
}