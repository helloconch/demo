import 'dart:convert';

import 'package:convert/convert.dart';
import 'package:crypto/crypto.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_shecare/common/Global.dart';
import 'package:flutter_shecare/models/index.dart';

import 'app.dart';
import 'colors.dart';
import 'topbar.dart';
import 'package:toast/toast.dart';

final homeRouteName = "home";

final currentLoginUser = 'currentLoginUser';

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

  String generateMd5() {
    var password = _pwdcontroller.text.trim();
    var content = Utf8Encoder().convert(password);
    var digest = md5.convert(content);
    return hex.encode(digest.bytes);
  }

  void _login() async {
    String phone = _phonecontroller.text.trim();

    Dio dio = Dio();
    Response response = await dio.post(
        'https://api.shecarefertility.com/ThermometerV2/login/login.json',
        data: {
          "buildType": 'customer',
          "emailOrPhone": phone,
          "password": generateMd5(),
          "phoneID": ''
        });

    print('登录返回数据:${response.data.toString()}');
    var loginInfo = Logininfo.fromJson(response.data);
    int code = loginInfo.code;
    if (code == 200) {
      Global.save(currentLoginUser, phone);
      Navigator.pushNamedAndRemoveUntil(
          context, homeRouteName, (route) => route == null);
    } else {
      print("loginBean:${loginInfo.code}");
      Toast.show("账户或密码错误", context,
          duration: Toast.LENGTH_SHORT, gravity: Toast.BOTTOM);
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
              keyboardType: TextInputType.number,
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
                    _login();
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
