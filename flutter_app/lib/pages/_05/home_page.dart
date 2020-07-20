import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'dart:async';

/// APP首页入口
///
/// 加载状态类组件HomePageState
class HomePage extends StatefulWidget {
  final String prefix = '当前时间';
  @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }
}

/// 首页有状态组件类
///
/// 主要获取当前时间，并动态展示当前时间
class HomePageState extends State<HomePage> {
  ///展示当前时间字符串
  String currentTimeStr;

  @override
  void initState() {
    super.initState();
    this.currentTimeStr = getCurrentTimeStr();
    refreshTimeStr();
  }

  String getCurrentTimeStr() {
    DateTime now = DateTime.now();
    var formatter = DateFormat('yy-MM-dd hh:mm');
    return formatter.format(now);
  }

  void refreshTimeStr() {
    const period = Duration(microseconds: 500);

    Timer.periodic(period, (timer) {
      setState(() {
        this.currentTimeStr = getCurrentTimeStr();
      });
    });
  }

  ///获取当前时间戳
  ///
  /// [prefix]需要传入一个前缀信息
  /// 返回一个字符串类型的前缀信息：时间戳

  String getCurrentTime(String prefix) {
    DateTime now = DateTime.now();
    //var formatter=DateFormat('yy-mm-dd H:m:s');
    //String nowTime=formatter.format(now);
    return '$prefix $now';
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[Text(widget.prefix), Text(this.currentTimeStr)],
    );
  }
}
