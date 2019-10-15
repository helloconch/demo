import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_shecare/common/Global.dart';
import 'package:flutter_statusbarcolor/flutter_statusbarcolor.dart';

import './widgets/login.dart';
import './widgets/app.dart';
import 'package:flutter_shecare/widgets/file.dart';

//void main() => runApp(MyApp());
void main() async {
  //修改状态栏内容样式dark/light
  SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle.dark);
  //修改状态栏背景色
  await FlutterStatusbarcolor.setStatusBarColor(Colors.white10);
  Global.get(currentLoginUser).then((value) {
    print('初始化状态:$value');
    if (value != null) {
      runApp(App());
    } else {
      runApp(Login());
    }
  });
}
