import 'package:demo/imageWidget.dart';
import 'package:demo/layout.dart';
import 'package:flutter/material.dart';
import 'package:demo/login.dart';
import 'package:demo/register.dart';
import 'package:demo/words.dart';
import 'package:demo/tabbox.dart';
import 'package:demo/lifecycle.dart';
import 'package:demo/textWidget.dart';
import 'package:demo/buttonWidget.dart';
import 'package:demo/container.dart';
import 'package:demo/scroll.dart';

final loginRouteName = "login";
final registerRouteName = "register";
final wordRouteName = "words";
final lifecycleRouteName = "lifecycle";
final tabBoxRouteName = "tabBox";
final textRouteName = "textdemo";
final buttonRouteName = "buttonDemo";
final imageRouteName = "imageDemo";
final layoutRouteName = "layoutDemo";
final containerRouteName = "containerDemo";
final scrollRouteName = "scrolldome";

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
        textRouteName: (context) => TextDemo(),
        buttonRouteName: (context) => ButtonDemo(),
        imageRouteName: (context) => ImageDemo(),
        layoutRouteName: (context) => LayoutDemo(),
        containerRouteName: (context) => ContainerDemo(),
        scrollRouteName: (context) => ScrollWidget(),
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
          child: Row(
        children: <Widget>[
          Column(
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
              ),
              FlatButton(
                child: Text('TextWidget'),
                textColor: Colors.blue,
                onPressed: () {
                  Navigator.pushNamed(context, textRouteName);
                },
              ),
              FlatButton(
                child: Text('Button Widget'),
                textColor: Colors.blue,
                onPressed: () => Navigator.pushNamed(context, buttonRouteName),
              ),
              FlatButton(
                onPressed: () => Navigator.pushNamed(context, imageRouteName),
                textColor: Colors.red,
                child: Text('Image'),
              ),
              FlatButton(
                onPressed: () => Navigator.pushNamed(context, layoutRouteName),
                textColor: Colors.black,
                child: Text('Layout'),
              ),
              FlatButton(
                onPressed: () =>
                    Navigator.pushNamed(context, containerRouteName),
                textColor: Colors.black,
                child: Text('容器类'),
              ),
            ],
          ),
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              FlatButton(
                onPressed: () =>
                    Navigator.pushNamed(context, scrollRouteName),
                textColor: Colors.black,
                child: Text('Scroll滚动'),
              ),
            ],
          )
        ],
      )),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: incrementCount,
        tooltip: 'add',
      ),
    );
  }
}
