import 'package:flutter/material.dart';
import 'package:flutter_app/util/struct/_06/user_info_struct.dart';
import 'package:flutter_app/util/struct/_06/article_summary_struct.dart';
import 'package:flutter_app/widgets/_06/article_card.dart';
import 'package:flutter_app/widgets/common/_06/banner_info.dart';
///首页列表信息
///
/// 展示banner和帖子信息
class HomePage extends StatelessWidget{

  /// banner 地址信息
  final String bannerImage =
      'https://img.089t.com/content/20200227/osbbw9upeelfqnxnwt0glcht.jpg';

  /// 帖子标题
  final UserInfoStruct userInfo = UserInfoStruct('flutter',
      'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595270060839&di=ca3c505c7b2ab073fee283d15ed0019b&imgtype=0&src=http%3A%2F%2Ft7.baidu.com%2Fit%2Fu%3D3616242789%2C1098670747%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D900%26h%3D1350');

  /// 帖子概要描述信息
  final ArticleSummaryStruct articleInfo = ArticleSummaryStruct(
      '你好，教个朋友',
      '我是一个小可爱',
      'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595270106896&di=6ca9f31675935e6be4a1f01ca453daa0&imgtype=0&src=http%3A%2F%2Ft7.baidu.com%2Fit%2Fu%3D1828762032%2C4073974079%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D800%26h%3D533',
      20,
      30);




  @override
  Widget build(BuildContext context) {

    return Container(
      child: Column(
        children: <Widget>[
          BannerInfo(bannerImage: bannerImage),
          ArticleCard(
            userInfoStruct: userInfo,
            articleSummaryStruct: articleInfo,
          )
        ],
      ),
    );
  }

}