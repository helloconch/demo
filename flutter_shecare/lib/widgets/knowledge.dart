import 'package:flutter/material.dart';
import 'browser.dart';
import 'topbar.dart';

final knowledgeUrl =
    'https://knowledge.shecarefertility.com/renderAppKnowledgeMain/';

class Knowledge extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: <Widget>[
          TopBar(title: '知识'),
          Expanded(
            child: Browser(url: knowledgeUrl),
          )
        ],
      ),
    );
  }
}
