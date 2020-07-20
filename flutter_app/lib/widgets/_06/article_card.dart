import 'package:flutter/material.dart';
import 'package:flutter_app/util/struct/_06/article_summary_struct.dart';
import 'package:flutter_app/util/struct/_06/user_info_struct.dart';
import 'article_bottom_bar.dart';
import 'article_like_bar.dart';
import 'article_summary.dart';

///此为帖子描述类，包括帖子UI中的所有元素

class ArticleCard extends StatelessWidget {
  final UserInfoStruct userInfoStruct;
  final ArticleSummaryStruct articleSummaryStruct;

  const ArticleCard({Key key, this.userInfoStruct, this.articleSummaryStruct})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        ArticleSummary(
            title: articleSummaryStruct.title,
            summary: articleSummaryStruct.articleSummary,
            articleImage: articleSummaryStruct.articleImage),
        Row(
          children: <Widget>[
            ArticleBottomBar(
                nickname: userInfoStruct.nickname,
                headerImage: userInfoStruct.headerImage,
                commentNum: articleSummaryStruct.commentNum),
            AriticleLikeBar(likeNum: articleSummaryStruct.likeNum)
          ],
        )
      ],
    );
  }
}
