import 'package:flutter/material.dart';

//Widget管理自身状态

class TabBox extends StatefulWidget {
  TabBox({Key key}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return TabboxAState();
  }
}

class TabboxAState extends State<TabBox> {
  bool _active = false;
  void handleTap() {
    setState(() {
      _active = !_active;
    });
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: handleTap,
      child: Container(
        child: Center(
          child: Text(
            _active ? 'active' : 'inactive',
            style: new TextStyle(fontSize: 32.0, color: Colors.white),
          ),
        ),
        width: 200.0,
        height: 200.0,
        decoration: BoxDecoration(
            color: _active ? Colors.lightGreen[700] : Colors.grey[600]),
      ),
    );
  }
}

//父widget管理子widget的state

class ParentWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return ParentWidgetState();
  }
}

class ParentWidgetState extends State<ParentWidget> {
  bool active = false;

  void handleTapboxChanged(bool newValue) {
    print('父widget收到子widget回调:$newValue');
    setState(() {
      active = newValue;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: TabBoxB(
        active: active,
        onChanged: handleTapboxChanged,
      ),
    );
  }
}

class TabBoxB extends StatelessWidget {
  final bool active;
  final ValueChanged<bool> onChanged;

  TabBoxB({Key key, this.active: false, @required this.onChanged})
      : super(key: key);

  void handleTap() {
    print('内部调用handleTap 抛给父widget>>>>>>');
    onChanged(!active);
  }

  Widget build(BuildContext context) {
    return new GestureDetector(
      onTap: handleTap,
      child: new Container(
        child: new Center(
          child: new Text(
            active ? 'Active' : 'Inactive',
            style: new TextStyle(fontSize: 32.0, color: Colors.white),
          ),
        ),
        width: 200.0,
        height: 200.0,
        decoration: new BoxDecoration(
          color: active ? Colors.lightGreen[700] : Colors.grey[600],
        ),
      ),
    );
  }
}

//混合管理
class ParentWidgetC extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return ParentWidgetCState();
  }
}

class ParentWidgetCState extends State<ParentWidgetC> {
  bool active = false;

  void handleTapBoxChange(bool value) {
    setState(() {
      active = value;
    });
  }

  @override
  Widget build(BuildContext context) {
    return TaboxC(active: active, onChanged: handleTapBoxChange);
  }
}

class TaboxC extends StatefulWidget {
  final bool active;
  final ValueChanged<bool> onChanged;

  TaboxC({Key key, this.active: false, @required this.onChanged})
      : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return TaboxCState();
  }
}

class TaboxCState extends State<TaboxC> {
  bool highlight = false;

  void handleTapDown(TapDownDetails details) {
    setState(() {
      highlight = true;
    });
  }

  void handleTapUp(TapUpDetails details) {
    setState(() {
      highlight = false;
    });
  }

  void handleCancel() {
    setState(() {
      highlight = false;
    });
  }

  void handleTap() {
    widget.onChanged(!widget.active);
  }

  @override
  Widget build(BuildContext context) {
    // 在按下时添加绿色边框，当抬起时，取消高亮
    return GestureDetector(
      onTapDown: handleTapDown,
      onTapUp: handleTapUp,
      onTapCancel: handleCancel,
      onTap: handleTap,
      child: new Container(
        child: new Center(
          child: new Text(widget.active ? 'Active' : 'Inactive',
              style: new TextStyle(fontSize: 32.0, color: Colors.white)),
        ),
        width: 200.0,
        height: 200.0,
        decoration: new BoxDecoration(
          color: widget.active ? Colors.lightGreen[700] : Colors.grey[600],
          border: highlight
              ? new Border.all(
                  color: Colors.teal[700],
                  width: 10.0,
                )
              : null,
        ),
      ),
    );
  }
}
