import 'package:flutter/material.dart';

class MyContain extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '容器组件示例',
      home: Scaffold(
        appBar: AppBar(
          title: Text("示例"),
        ),
        body: Center(
          child: Container(
            width: 200,
            height: 200,
            //添加边框装饰效果
            decoration: BoxDecoration(
                color: Colors.white,
                border: new Border.all(
                    color: Colors.grey,
                    width: 8
                ),
                borderRadius:
                const BorderRadius.all(const Radius.circular(8.0))
            ),

            child: Text(
              'Flutter',
              textAlign: TextAlign.center,
              style: TextStyle(fontSize: 28),
            ),
          ),
        ),
      ),
    );
  }
}


class ImageDemo extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return new Center(
      child: new Image.network(
        'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=548281219,2631336242&fm=26&gp=0.jpg',
        fit: BoxFit.fitWidth,),
    );
  }
}


class ContainerDemo extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return new Scaffold(

      appBar: new AppBar(title: new Text('文本组件'),),

      body: new Column(
        children: <Widget>[
          new Text('红色+黑色删除线+25',
            style: new TextStyle(
                color: const Color(0xffff0000),
                decoration: TextDecoration.lineThrough,
                decorationColor: const Color(0xffff0000),
                fontSize: 25.0
            ),
          ),
          new Text('橙色',
          style: new TextStyle(
            color: const Color(0xffff9900),
            decoration: TextDecoration.underline,
            fontSize: 24.0
          ),)
        ],
      ),

    );
  }
}

class LayoutDemo extends StatelessWidget{

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text('图片按钮组件示例'),
      ),
      body: new Center(
        child: new IconButton(
          icon: Icon(Icons.volume_up,size: 48.0,),
          tooltip: '按下操作',
          onPressed: (){
            print('按下操作');
          },
        ),
      ),
    );
  }
}

class MyRaisedButton extends StatelessWidget{

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'RaisedButton示例',
      home:new Scaffold(
        appBar: new AppBar(
          title: new Text('组件'),
        ),
        body: new Center(
          child: new RaisedButton(onPressed:(){
            
          },child: new Text('click'),),
        
        ),
      )
    );
  }
}