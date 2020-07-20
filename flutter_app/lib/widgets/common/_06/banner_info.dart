import 'package:flutter/material.dart';
///banner展示组件
///
/// 只要传入需要展示的[bannerImage]

class BannerInfo extends StatelessWidget{

  final String bannerImage;

  const BannerInfo({Key key,this.bannerImage}):super(key:key);


  ///左侧的标题和标题描述组件
  Widget getLeftInfo(){
    return Row(
      children: <Widget>[],
    );
  }

  @override
  Widget build(BuildContext context) {
    return Row(
      children: <Widget>[
        Image.network(bannerImage,
        width: MediaQuery.of(context).size.width,
        fit: BoxFit.cover)
      ],
    );
  }

}