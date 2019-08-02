import 'package:flutter/material.dart';

class LifeCycleWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return LifeCycleState();
  }
}

class LifeCycleState extends State<LifeCycleWidget> {
  int counter=0;

  @override
  void initState() {
    super.initState();
    print("initState>>>>>>>>>>>>>>>>>>>>>>>>>");
  }

  @override
  Widget build(BuildContext context) {

    print("build>>>>>>>>>>>>>>>>>>>>>>>>>");

    return Scaffold(
      appBar: AppBar(
        title: Text('LifeCycle'),
      ),
      body: Center(
        child: FlatButton(
          child: Text('$counter'),
          onPressed: () {
            setState(() {
              setState(() {
               counter++; 
              });
            });
          },
        ),
      ),
    );
  }

  @override
  void didUpdateWidget(LifeCycleWidget oldWidget) {
    super.didUpdateWidget(oldWidget);
    print("didUpdateWidget>>>>>>>>>>>>>>>>>>>>>>>>>");
  }

  @override
  void deactivate() {
    super.deactivate();
    print("deactivate>>>>>>>>>>>>>>>>>>>>>>>>>");
  }

  @override
  void dispose() {
    super.dispose();
    print("dispose>>>>>>>>>>>>>>>>>>>>>>>>>");
  }

  @override
  void reassemble() {
    super.reassemble();
    print("reassemble>>>>>>>>>>>>>>>>>>>>>>>>>");
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    print("didChangeDependencies>>>>>>>>>>>>>>>>>>>>>>>>>");
  }
}
