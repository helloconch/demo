import 'package:flutter/material.dart';
import 'colors.dart';
import 'app.dart';

final homeRouteName = "home";

class Login extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes: {
        homeRouteName: (context) => App(),
      },
      home: LoginPage(),
    );
  }
}

class LoginPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _LoginPageState();
  }
}

class _LoginPageState extends State<LoginPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
      color: Colors.white,
      padding: const EdgeInsets.only(top: 50),
      alignment: Alignment.center,
      child: Column(
        children: <Widget>[
          Text(
            "登录",
            style: TextStyle(
              color: Colors.black87,
              fontSize: 18.0,
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(top: 50),
            child: Image.asset(
              'imgs/icon_login_app.png',
              width: 100,
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(top: 20),
            child: Image.asset(
              'imgs/icon_login_app_desc.png',
              width: 200,
            ),
          ),
          Container(
            margin:
                const EdgeInsets.only(top: 30, left: 50, right: 50, bottom: 10),
            child: TextField(
              decoration: InputDecoration(hintText: '手机号'),
            ),
          ),
          Container(
            margin: const EdgeInsets.only(left: 50, right: 50),
            child: TextField(
              decoration: InputDecoration(hintText: '密码'),
              obscureText: true,
            ),
          ),
          Container(
              margin: const EdgeInsets.only(left: 50, right: 50, top: 20),
              child: FlatButton(
                color: YColors.buttonColor,
                highlightColor: YColors.buttonPressColor,
                colorBrightness: Brightness.dark,
                splashColor: Colors.grey,
                child: Text("登录"),
                padding: const EdgeInsets.only(
                    left: 100, right: 100, top: 12, bottom: 12),
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20.0)),
                onPressed: () {
                  Navigator.pushNamed(context, homeRouteName);
                },
              )),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.only(top: 20, right: 30, bottom: 20),
                child: Text(
                  " 注册用户 ",
                  style: TextStyle(fontSize: 14, color: Colors.grey),
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(top: 20, left: 30, bottom: 20),
                child: Text(
                  " 忘记密码? ",
                  style: TextStyle(fontSize: 14, color: Colors.grey),
                ),
              ),
            ],
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Image.asset(
                'imgs/icon_login_wechat.png',
                width: 50,
              ),
              Image.asset(
                'imgs/icon_login_qq.png',
                width: 50,
              ),
              Image.asset(
                'imgs/icon_login_sina.png',
                width: 50,
              ),
            ],
          )
        ],
      ),
    ));
  }
}
