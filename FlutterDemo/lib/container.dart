import 'package:flutter/material.dart';

class ContainerDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('容器'),
      ),
      body: PaddingTest(),
    );
  }
}

class PaddingTest extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(16.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.only(left: 8.0),
            child: Text('Hello world'),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(vertical: 8.0),
            child: Text('I am Jack'),
          ),
          Padding(
            padding: const EdgeInsets.fromLTRB(20.0, 0, 20.0, 0),
            child: Text('Your friend'),
          ),
          ConstrainedBox(
            child: Container(
              height: 5.0,
              child: redBox,
            ),
            constraints:
                BoxConstraints(minWidth: double.infinity, minHeight: 50.0),
          ),
          Padding(
            padding: EdgeInsets.only(top: 20, left: 20),
            child: SizedBox(
              width: 80.0,
              height: 80.0,
              child: redBox,
            ),
          ),
          BoxDecoratedWidget(),
          Container(
            color: Colors.black,
            child: Transform(
              alignment: Alignment.topRight,
              transform: Matrix4.skewY(0.3),
              child: Container(
                padding: const EdgeInsets.all(8.0),
                color: Colors.deepOrange,
                child: const Text('Apartment for rent !'),
              ),
            ),
          ),
          TransformDemo()
        ],
      ),
    );
  }
}

Widget redBox = DecoratedBox(
  decoration: BoxDecoration(color: Colors.red),
);

class BoxDecoratedWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return DecoratedBox(
      decoration: BoxDecoration(
          gradient: LinearGradient(colors: [Colors.red, Colors.orange[700]]),
          borderRadius: BorderRadius.circular(3.0),
          boxShadow: [
            BoxShadow(
                color: Colors.black54,
                offset: Offset(2.0, 2.0),
                blurRadius: 4.0)
          ]),
      child: Padding(
        padding: EdgeInsets.symmetric(horizontal: 60.0, vertical: 18.0),
        child: Text('login', style: TextStyle(color: Colors.white)),
      ),
    );
  }
}

class TransformDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 5),
      child: DecoratedBox(
        decoration: BoxDecoration(color: Colors.red),
        child: Transform.translate(
          offset: Offset(-20.0, -5.0),
          child: Text('Hello world'),
        ),
      ),
    );
  }
}
