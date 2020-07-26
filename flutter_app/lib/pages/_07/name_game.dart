import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:flutter_app/widgets/_07/model/name_model.dart';
import 'package:flutter_app/widgets/_07/widgets/random_name.dart';
import 'package:flutter_app/widgets/_07/widgets/test_dart.dart';
import 'package:flutter_app/widgets/_07/widgets/welcome.dart';


///测试随机名字游戏组件

class NameGame extends StatelessWidget{
  ///设置状态name

  final name=NameModel();

  @override
  Widget build(BuildContext context) {

    return Column(
      children: <Widget>[
        Provider<String>.value(
          child: ChangeNotifierProvider.value(
            value: name,
            child: Column(
              children: <Widget>[
                Welcome(),
                RandomName(),
              ],
            ),
          ),
        ),
        TestOther(),
      ],
    );
  }


}