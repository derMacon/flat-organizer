import 'package:flutter/material.dart';
import 'item.dart';
import 'package:http/http.dart';
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
    Item(name: 'item 1', count: 3),

    // Item(name: 'item 1', count: 3, holder: 'Fritz'),
    // Item(name: 'item 2', count: 4, holder: 'Bob'),
    // Item(name: 'item 3', count: 6, holder: 'Gundula'),
  ];

  @override
  void initState() {
    super.initState();
    print('initState function ran');
    updateList();
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
    Response response = await get("http://10.0.2.2:8080/greeting");
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
            items.map((item) => Text('${item.name} - ${item.count}')).toList(),
      ),
    );
  }
}
