import 'package:flutter/material.dart';

class MyWidgetApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Flutter',
      theme: new ThemeData(
          primaryColor: Colors.blue
      ),
      home: new HomePage(title: 'Flutter Home Page'),
    );
  }
}

class HomePage extends StatefulWidget {
  final String title;

  HomePage({Key key, this.title}) :super(key: key)

  @override
  State createState() {
    return new _MyHomePageState();
  }


}

class _MyHomePageState extends State<HomePage> {

  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(

      appBar: new AppBar(
        title: new Text(widget.title),
      ),

      body: new Center(
        child: new Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            new Text('You have pushed the button this many times'),
            new Text(
              '$_counter',
              style: Theme
                  .of(context)
                  .textTheme
                  .display1,
            )
          ],
        ),
      ),

      floatingActionButton: new FloatingActionButton(
        onPressed: _incrementCounter, tooltip: '+',
        child: new Icon(Icons.add),),

    );
  }
}