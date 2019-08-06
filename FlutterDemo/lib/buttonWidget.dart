import 'package:flutter/material.dart';

class ButtonDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Button Widget'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            RaisedButton(
              child: Text('normal'),
              onPressed: () {},
            ),
            FlatButton(
              child: Text('normal'),
              onPressed: () {},
            ),
            OutlineButton(
              child: Text('normal'),
              onPressed: () {},
            ),
            IconButton(
              icon: Icon(Icons.thumb_up),
              color: Colors.blue,
              onPressed: () {},
            ),
            FlatButton(
              color: Colors.blue,
              highlightColor: Colors.blue[700],
              colorBrightness: Brightness.dark,
              splashColor: Colors.grey,
              child: Text('submit'),
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(20.0)),
              onPressed: () => {},
            ),
            SwitchAndCheckbox(),
          ],
        ),
      ),
    );
  }
}

class SwitchAndCheckbox extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return SwitchAndCheckboxState();
  }
}

class SwitchAndCheckboxState extends State<SwitchAndCheckbox> {
  bool switchSelected = true;
  bool checkboxSelected = true;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Switch(
          value: switchSelected,
          onChanged: (value) {
            setState(() {
              switchSelected = value;
            });
          },
        ),
        Checkbox(
          value: checkboxSelected,
          activeColor: Colors.red,
          onChanged: (value) {
            setState(() {
              print('>>>>>$value');
              checkboxSelected = value;
            });
          },
        )
      ],
    );
  }
}
