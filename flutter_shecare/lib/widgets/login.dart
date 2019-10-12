import 'package:flutter/material.dart';
import 'colors.dart';
import 'app.dart';
import 'topbar.dart';

final homeRouteName = "home";

class Login extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes: {
        homeRouteName: (context) => App(),
      },
      home: LoginPage(),
      theme: ThemeData(
          primaryColor: YColors.colorPrimary,
          accentColor: YColors.accentColor,
          cursorColor: YColors.accentColor),
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
  TextEditingController _phonecontroller = new TextEditingController();
  TextEditingController _pwdcontroller = new TextEditingController();
  bool _phoneState, _pwdState = false;
  String _checkStr;

  void _checkPhone() {
    if (_phonecontroller.text.isNotEmpty &&
        _phonecontroller.text.trim().length == 11) {
      _phoneState = true;
    } else {
      _phoneState = false;
    }
  }

  void _checkPwd() {
    if (_pwdcontroller.text.isNotEmpty &&
        _pwdcontroller.text.trim().length >= 6 &&
        _pwdcontroller.text.trim().length <= 10) {
      _pwdState = true;
    } else {
      _pwdState = false;
    }
  }

  @override
  Widget build(BuildContext context) {
    double statusBarHeight = MediaQuery.of(context).padding.top;
    return Scaffold(
        body: Container(
      color: Colors.white,
      margin: EdgeInsets.only(top: statusBarHeight),
      child: ListView(
        children: <Widget>[
          TopBar(title: '登录'),
          Padding(
            padding: const EdgeInsets.only(top: 50),
            child: Image.asset(
              'imgs/icon_login_app.png',
              width: 100,
              height: 100,
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(top: 20),
            child: Image.asset(
              'imgs/icon_login_app_desc.png',
              width: 200,
              height: 30,
            ),
          ),
          Container(
            margin:
                const EdgeInsets.only(top: 30, left: 50, right: 50, bottom: 10),
            child: TextField(
              decoration: InputDecoration(hintText: '手机号'),
              controller: _phonecontroller,
            ),
          ),
          Container(
            margin: const EdgeInsets.only(left: 50, right: 50),
            child: TextField(
              decoration: InputDecoration(hintText: '密码'),
              obscureText: true,
              controller: _pwdcontroller,
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
                  _checkPhone();
                  _checkPwd();
                  if (_phoneState && _pwdState) {
                    //登录成功
                    Navigator.pushNamedAndRemoveUntil(
                        context, homeRouteName, (route) => route == null);
                  } else {
                    if (!_phoneState) {
                      _checkStr = '请输入11位手机号！';
                    } else if (!_pwdState) {
                      _checkStr = '请输入6-10位密码！';
                    }
                  }
                  if (!_phoneState || !_pwdState) {
                    showDialog<Null>(
                      context: context,
                      barrierDismissible: false,
                      child: new AlertDialog(
                        title: new Text(
                          '温馨提示',
                          style: new TextStyle(
                            color: Colors.black54,
                            fontSize: 18.0,
                          ),
                        ),
                        content: new Text(_checkStr),
                        actions: <Widget>[
                          new FlatButton(
                              onPressed: () {
                                Navigator.pop(context);
                              },
                              child: new Text('确定')),
                        ],
                      ),
                    );
                  }
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
