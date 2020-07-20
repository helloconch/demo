import 'package:flutter/material.dart';

///帖子文章点赞组件
///
///
/// 包括点赞组件icon 以及组件点击效果
/// 需要外部参数[likeNum]点赞数量

class AriticleLikeBar extends StatefulWidget {
  final int likeNum;

  const AriticleLikeBar({Key key, this.likeNum}) : super(key: key);

  @override
  State createState() {
    return ArticleLikeBarState();
  }
}

class ArticleLikeBarState extends State<AriticleLikeBar> {
  int likeNum;
  @override
  void initState() {
    super.initState();
    likeNum ??= widget.likeNum;
  }

  //点赞动作效果

  void like() {
    setState(() {
      likeNum = ++likeNum;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Row(
      children: <Widget>[
        Icon(Icons.thumb_up, color: Colors.grey, size: 18),
        Padding(padding: EdgeInsets.only(left: 10)),
        FlatButton(
          child: Text('$likeNum'),
          onPressed: () => like(),
        )
      ],
    );
  }
}
