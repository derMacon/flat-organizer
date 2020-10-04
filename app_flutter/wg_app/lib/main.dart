import 'package:flutter/material.dart';
import 'item.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

void main() {
  runApp(MaterialApp(
    home: ItemList(),
  ));
}

class ItemList extends StatefulWidget {
  @override
  _ItemListState createState() => _ItemListState();
}

class _ItemListState extends State<ItemList> {
  List<Item> items = [
    Item(name: 'item 1', count: 3, requester: 'req1'),
  ];

  /**
   * Sends Post request to server using json syntax
   */
  Future<void> sendPost() async {
    var url = "http://10.0.2.2:8080/post";
    var body = json.encode({"name": "send name", "count": "8", "requester": "send requester"});
    Map<String,String> headers = {
      'Content-type' : 'application/json',
      'Accept': 'application/json',
    };

    http.Response response = await http.post(url, body: body, headers: headers);
    final responseJson = json.decode(response.body);
    print(responseJson);
    print("sendPost");
  }

  @override
  void initState() {
    super.initState();
    print('initState function ran');
    updateList();
    sendPost();
  }

  /**
   * Fetch new items and add them to current list
   */
  Future<void> updateList() async {
    List<Item> new_items = await getData();

    setState(() {
      items.addAll(new_items);
    });
  }

  /**
   * Fetch items from endpoint
   */
  Future<List<Item>> getData() async {
    http.Response response = await http.get("http://10.0.2.2:8080/greeting");
    var parsed = jsonDecode(response.body);
    List<Item> item_lst =
        (parsed as List).map((data) => new Item.fromJson(data)).toList();
    return item_lst;
  }

  @override
  Widget build(BuildContext context) {
    print('build function ran');
    return Scaffold(
      backgroundColor: Colors.grey[200],
      appBar: AppBar(
        title: Text('Awesome Quotes'),
        centerTitle: true,
        backgroundColor: Colors.redAccent,
      ),
      body: Column(
        children:
            items.map((item) => Text('${item.name} - ${item.count} - ${item.requester}')).toList(),
      ),
    );
  }
}
