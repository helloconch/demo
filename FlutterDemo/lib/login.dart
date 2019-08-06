import 'package:flutter/material.dart';

class LoginWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Login'),
        ),
        body: FormTest());
  }
}

class LoginView extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return LoginViewState();
  }
}

class LoginViewState extends State<LoginView> {
  FocusNode focusNode1 = new FocusNode();
  FocusNode focusNode2 = new FocusNode();
  FocusScopeNode focusScopeNode;

  @override
  void initState() {
    usernameController.text = 'hello word';
    usernameController.selection = TextSelection(
        baseOffset: 2, extentOffset: usernameController.text.length);
    usernameController.addListener(() {
      print(usernameController.text);
    });

    focusNode1.addListener(() {
      print('焦点发生改变');
    });
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(16.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          TextField(
            autofocus: true,
            focusNode: focusNode1,
            controller: usernameController,
            decoration: InputDecoration(
                labelText: 'username',
                hintText: 'username or email',
                prefixIcon: Icon(Icons.person)),
            onChanged: (v) {
              print('onChange:$v');
              print(usernameController.text);
            },
          ),
          TextField(
            focusNode: focusNode2,
            decoration: InputDecoration(
                labelText: 'password',
                hintText: 'password',
                prefixIcon: Icon(
                  Icons.lock,
                  color: Colors.blue,
                )),
            obscureText: true,
          ),
          FlatButton(
            child: Text('Move Focus'),
            color: Colors.blue,
            onPressed: () {
              if (null == focusScopeNode) {
                focusScopeNode = FocusScope.of(context);
              }
              focusScopeNode.requestFocus(focusNode2);
            },
          ),
          FlatButton(
            child: Text('Hide Focus'),
            color: Colors.red,
            onPressed: () {
              focusNode1.unfocus();
              focusNode2.unfocus();
            },
          )
        ],
      ),
    );
  }
}

TextEditingController usernameController = new TextEditingController();

class FormTest extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return FormState();
  }
}

class FormState extends State<FormTest> {
  TextEditingController unameController = new TextEditingController();
  TextEditingController passwordController = new TextEditingController();

  GlobalKey formKey = new GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Form'),
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(vertical: 16.0, horizontal: 24.0),
        child: Form(
          key: formKey, //设置globalKey，用于后面获取FormState
          autovalidate: true, //开启自动校验
          child: Column(
            children: <Widget>[
              TextFormField(
                autofocus: true,
                controller: unameController,
                decoration: InputDecoration(
                    labelText: 'username',
                    hintText: 'username',
                    icon: Icon(Icons.person)),
                validator: (v) {
                  // 校验用户名
                  return v.trim().length > 0 ? null : '用户名不能为空';
                },
              ),
              TextFormField(
                controller: passwordController,
                decoration: InputDecoration(
                    icon: Icon(Icons.lock),
                    labelText: 'password',
                    hintText: 'psw'),
                obscureText: true,
                validator: (v) {
                  return v.trim().length > 5 ? null : '密码不能小于6位';
                },
              ),
              Padding(
                padding: const EdgeInsets.only(top: 28.0),
                child: Row(
                  children: <Widget>[
                    Expanded(
                      child: RaisedButton(
                        padding: EdgeInsets.all(15.0),
                        child: Text('login'),
                        color: Theme.of(context).primaryColor,
                        textColor: Colors.white,
                        onPressed: () {
                          print('验证提交数据');

                          // if ((formKey.currentState as FormState).validate()) {
                          //   print('验证提交数据');
                          // }

                        },
                      ),
                    )
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
