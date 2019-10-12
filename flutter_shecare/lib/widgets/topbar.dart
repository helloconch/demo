import 'package:flutter/material.dart';

class TopBar extends StatelessWidget {
  final String title;

  TopBar({Key key, this.title}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    double statusBarHeight = MediaQuery.of(context).padding.top;
    return Container(
        alignment: Alignment.center,
        margin: EdgeInsets.only(top: statusBarHeight),
        child: Padding(
          padding: EdgeInsets.all(10),
          child: Text(
            '$title',
            style: TextStyle(fontSize: 18),
          ),
        ));
  }
}
