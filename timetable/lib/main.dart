import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title : 'wg_app',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Home()
    );
  }
}

class Home extends StatefulWidget{
  final String value;
  Home({Key key, this.value}) : super(key: key);
  @override
  _HomeState createState() => _HomeState();
}
class _HomeState extends State<Home>{
  var _textController = TextEditingController();
  //int _currentIndex = 0;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Home'),
      ),
      body: ListView(
        children: <Widget>[
          ListTile(
            title: RaisedButton(
              child: Text("Add Items"),
              onPressed: (){
                Navigator.of(context).push(
                  MaterialPageRoute(
                    builder: (context) => Addlist()
                  ),
                );
              },
            ),
          ),
          ListTile(
            title: Text("${widget.value}"),
          ),
        ],
      ),
    );
  }
}
class Addlist extends StatefulWidget{
  @override
  _Addlist createState() => _Addlist();
}
class _Addlist extends State<Addlist>{
  var _textController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Einkaufsware hinzufügen'),
      ),
      body: ListView(
          children: <Widget>[
            ListTile(
              title: TextFormField(
                decoration: InputDecoration(hintText: 'Ware'),
                controller: _textController,
              ),
            ),
            ListTile(
              title: RaisedButton(
                child: Text("Add to list"),
                onPressed: (){
                   var route = MaterialPageRoute(
                       builder: (context) => Home(value: _textController.text),
                   );
                   Navigator.of(context).push(route);
                },
              ),
            ),
            ],
        ),
    );
  }
}
