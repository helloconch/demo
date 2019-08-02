import 'package:flutter/material.dart';

class RegisterWidet extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var value = ModalRoute.of(context).settings.arguments;

    return Scaffold(
      appBar: AppBar(
        title: Text('register'),
      ),
      body: Center(
        child:Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
             Text('$value'),
             Image.asset('images/avator.jpg'),
             Image.asset('images/2.0x/amh_icon.png')
          ],
        ),
      ),
    );
  }
}
