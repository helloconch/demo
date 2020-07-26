import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:flutter_app/widgets/_07/model/name_model.dart';

///随机展示人名
class RandomName extends StatelessWidget{
  ///有状态类返回组件信息
  @override
  Widget build(BuildContext context) {

    final   _name=Provider.of<NameModel>(context);
    print('random name build');

    return FlatButton(
      child: Text('${_name.value}'),
      onPressed: ()=>_name.changeName(),
    );
  }


}