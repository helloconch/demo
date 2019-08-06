import 'package:flutter/material.dart';

class ImageDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    String icons = "";
    icons += "\uE914";
// error: &#xE000; or 0xE000 or E000
    icons += " \uE000";
// fingerprint: &#xE90D; or 0xE90D or E90D
    icons += " \uE90D";

    return Scaffold(
      appBar: AppBar(
        title: Text('Image'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Image(
              image: AssetImage('images/avator.jpg'),
              width: 100,
            ),
            Image.asset('images/avator.jpg', width: 100),
            Image(
                image: NetworkImage(
                  'http://b-ssl.duitang.com/uploads/item/201501/16/20150116145231_3xcYy.jpeg',
                ),
                width: 100.0),
            Image.network(
              'http://b-ssl.duitang.com/uploads/item/201501/16/20150116145231_3xcYy.jpeg',
              width: 50.0,
            ),
            Image(
              image: AssetImage('images/avator.jpg'),
              width: 100.0,
              color: Colors.blue,
              colorBlendMode: BlendMode.difference,
            ),
            Text(icons,
                style: TextStyle(
                    fontFamily: "MaterialIcons",
                    fontSize: 24.0,
                    color: Colors.green)),
            Icon(
              Icons.accessibility,
              color: Colors.green,
            ),
            Icon(Icons.error, color: Colors.green)
          ],
        ),
      ),
    );
  }
}
