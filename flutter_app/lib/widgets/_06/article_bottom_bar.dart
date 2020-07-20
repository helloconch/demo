import 'package:flutter/material.dart';

///帖子下面的信息栏
///
/// 包括用户头像/昵称/评论信息
/// 需要参数[nickname] [headerImage] [commentNum]

class ArticleBottomBar extends StatelessWidget {
  ///用户昵称
  final String nickname;

  ///评论数量
  final int commentNum;

  ///用户头像
  final String headerImage;

  const ArticleBottomBar(
      {Key key, this.nickname, this.headerImage, this.commentNum})
      : super(key: key);

  ///帖子中用户头像和昵称
  Widget getUserWidget() {
    return Row(
      children: <Widget>[
        Image.network(headerImage,
            width: 15.0, height: 15.0, fit: BoxFit.cover),
        Text(nickname)
      ],
    );
  }

  ///帖子栏中的评论信息
  Widget getCommentWidget() {
    return Row(
      children: <Widget>[
        Icon(Icons.comment, color: Colors.grey, size: 18),
        Padding(padding: EdgeInsets.only(left: 10)),
        Text('$commentNum')
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    return Row(
      children: <Widget>[getUserWidget(), getCommentWidget()],
    );
  }
}
