///描述帖子的数据结构
class ArticleSummaryStruct {
  ///帖子标题
  final String title;

  ///帖子概要
  final String articleSummary;

  ///帖子一张图
  final String articleImage;

  ///点赞数量
  final int likeNum;

  ///评论数量
  final int commentNum;

  const ArticleSummaryStruct(this.title, this.articleSummary, this.articleImage,
      this.likeNum, this.commentNum);
}
