import 'package:demo/tabbox.dart';
import 'package:flutter/material.dart';
import 'login.dart';
import 'register.dart';
import 'words.dart';
import 'lifecycle.dart';

final loginRouteName = "login";
final registerRouteName = "register";
final wordRouteName = "words";
final lifecycleRouteName = "lifecycle";
final tabBoxRouteName = "tabBox";

class CalcWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: '计数器',
      theme: new ThemeData(primaryColor: Colors.grey),
      routes: {
        loginRouteName: (context) => LoginWidget(),
        registerRouteName: (context) => RegisterWidet(),
        wordRouteName: (context) => WordWidget(),
        lifecycleRouteName: (context) => LifeCycleWidget(),
        tabBoxRouteName: (context) => ParentWidgetC(),
      },
      home: new HomePage(
        title: 'HomePage',
      ),
    );
  }
}

class HomePage extends StatefulWidget {
  final String title;
  HomePage({Key key, this.title}) : super(key: key);

  @override
  State<HomePage> createState() {
    return MyHomepageState();
  }
}

class MyHomepageState extends State<HomePage> {
  int count = 1;

  void incrementCount() {
    setState(() {
      count++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('click'),
            Text(
              '$count',
              style: Theme.of(context).textTheme.display1,
            ),
            FlatButton(
              child: Text('click login'),
              textColor: Colors.blue,
              onPressed: () {
                Navigator.push(context,
                    new MaterialPageRoute(builder: (context) {
                  return LoginWidget();
                }));
              },
            ),
            FlatButton(
              onPressed: () {
                Navigator.pushNamed(context, registerRouteName,
                    arguments: "hell register");
              },
              textColor: Colors.red,
              child: Text('click register'),
            ),
            FlatButton(
              child: Text('click word'),
              textColor: Colors.yellow,
              onPressed: () {
                Navigator.pushNamed(context, wordRouteName);
              },
            ),
            FlatButton(
              child: Text('查看生命周期'),
              textColor: Colors.green,
              onPressed: () {
                Navigator.pushNamed(context, lifecycleRouteName);
              },
            ),
            FlatButton(
              child: Text('Tabbox'),
              textColor: Colors.pink,
              onPressed: () {
                Navigator.pushNamed(context, tabBoxRouteName);
              },
            )
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: incrementCount,
        tooltip: 'add',
      ),
    );
  }
}
