import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';

class WordWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return WordState();
  }
}

class WordState extends State<WordWidget> {
  @override
  Widget build(BuildContext context) {
    final wordPair = WordPair.random();
    return Scaffold(
      appBar: AppBar(
        title: Text('Word'),
      ),
      body: Center(
        child: Text('$wordPair'),
      ),
    );
  }
}
