import 'package:flutter/material.dart';

class LayoutDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Layout'),
      ),
      body: StackWidget(),
    );
  }
}

class RowWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(' hello world'),
            Text(' I am Jack'),
          ],
        ),
        Row(
          mainAxisSize: MainAxisSize.min,
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(' hello world'),
            Text(' I am Jack'),
          ],
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.end,
          textDirection: TextDirection.rtl,
          children: <Widget>[
            Text(' hello world'),
            Text(' I am Jack'),
          ],
        ),
        Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(
              ' hello world',
              style: TextStyle(fontSize: 30.0),
            ),
            Text(' I am Jack'),
          ],
        )
      ],
    );
  }
}

class ColumnWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.green,
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisSize: MainAxisSize.max,
          children: <Widget>[
            Container(
              color: Colors.red,
              child: Column(
                children: <Widget>[
                  Text(' hello world'),
                  Text(' I am Jack'),
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}

class FlexWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Flex(
          direction: Axis.horizontal,
          children: <Widget>[
            Expanded(
              flex: 1,
              child: Container(
                height: 30.0,
                color: Colors.red,
              ),
            ),
            Expanded(
              flex: 2,
              child: Container(
                height: 30.0,
                color: Colors.green,
              ),
            )
          ],
        ),
        Padding(
          padding: const EdgeInsets.only(top: 20.0),
          child: SizedBox(
            height: 100.0,
            child: Flex(
              direction: Axis.vertical,
              children: <Widget>[
                Expanded(
                  flex: 2,
                  child: Container(
                    height: 30.0,
                    color: Colors.red,
                  ),
                ),
                Spacer(
                  flex: 1,
                ),
                Expanded(
                  flex: 1,
                  child: Container(
                    height: 30.0,
                    color: Colors.green,
                  ),
                )
              ],
            ),
          ),
        )
      ],
    );
  }
}

class WrapWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Wrap(
      spacing: 8.0,
      runSpacing: 4.0,
      alignment: WrapAlignment.center,
      children: <Widget>[
        Chip(
          avatar: CircleAvatar(
            backgroundColor: Colors.blue,
            child: Text('A'),
          ),
          label: Text('Hamilton'),
        ),
        Chip(
          avatar: CircleAvatar(
            backgroundColor: Colors.blue,
            child: Text('M'),
          ),
          label: Text('Lafayette'),
        ),
        Chip(
          avatar: CircleAvatar(
            backgroundColor: Colors.blue,
            child: Text('H'),
          ),
          label: Text('Mullligan'),
        ),
        Chip(
          avatar: CircleAvatar(
            backgroundColor: Colors.blue,
            child: Text('J'),
          ),
          label: Text('Laurens'),
        ),
      ],
    );
  }
}

class StackWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ConstrainedBox(
      constraints: BoxConstraints.expand(),
      child: Stack(
        alignment: Alignment.center,
        children: <Widget>[
          Container(
            child: Text('Helllo world', style: TextStyle(color: Colors.white)),
            color: Colors.red,
          ),
          Positioned(
            child: Text('I am jack'),
            left: 18.0,
          ),
          Positioned(
            child: Text('Your Friend'),
            top: 18.0,
          )
        ],
      ),
    );
  }
}
