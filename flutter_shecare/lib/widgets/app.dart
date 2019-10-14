import 'package:flutter/material.dart';
import 'colors.dart';
import 'home.dart';
import 'calendar.dart';
import 'shop.dart';
import 'knowledge.dart';
import 'person.dart';

List<Widget> _options = <Widget>[
  Home(),
  Calendar(),
  Shop(),
  Knowledge(),
  Personal(),
];

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: AppPage(),
    );
  }
}

class AppPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _AppState();
  }
}

class _AppState extends State<AppPage> {
  int _selectedIndex = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: BottomNavigationBar(
        items: <BottomNavigationBarItem>[
          BottomNavigationBarItem(icon: Icon(Icons.home), title: Text('首页')),
          BottomNavigationBarItem(
              icon: Icon(Icons.calendar_today), title: Text('日历')),
          BottomNavigationBarItem(icon: Icon(Icons.shop), title: Text('商城')),
          BottomNavigationBarItem(
              icon: Icon(Icons.collections_bookmark), title: Text('知识堂')),
          BottomNavigationBarItem(icon: Icon(Icons.person), title: Text('我的')),
        ],
        currentIndex: _selectedIndex,
        fixedColor: YColors.colorPrimary,
        onTap: _onItemTapped,
        type: BottomNavigationBarType.fixed,
        showSelectedLabels: true,
      ),
      body: Center(
        child: _options.elementAt(_selectedIndex),
      ),
    );
  }

  void _onItemTapped(int index) {
    print('点击：$index');
    setState(() {
      _selectedIndex = index;
    });
  }
}
