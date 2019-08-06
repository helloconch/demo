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
          )
        ],
      ),
    );
  }
}

Widget redBox = DecoratedBox(
  decoration: BoxDecoration(color: Colors.red),
);
