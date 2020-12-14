import 'dart:convert';
import 'package:http/http.dart';
import '../data/item.dart';

// ----- private constants -----

const String _server_ip = "168.119.120.2";
const String _proj_name = "flat-organizer-demo";

const String _endpoint_newItems = "/api/groceryList/getNewItems";
const String _endpoint_oldItems = "/api/groceryList/getOldItems";



// ----- public functions -----

/**
 * Get Request for items currently in the cart
 */
Future<List<Item>> getNewItems() async {
  Response response = await _getRequest(_endpoint_newItems);
  return parseItemLst(response.body);
}

/**
 * Get Request for items from the last shopping trip
 */
Future<List<Item>> getOldItems() async {
  Response response = await _getRequest(_endpoint_oldItems);
  return parseItemLst(response.body);
}


// ----- private functions -----

/**
 * Get Request without any parameters except the endpoint url
 */
Future<Response> _getRequest(String endpoint) async {
  String url = 'http://' + _server_ip + ':8080/' + _proj_name + endpoint;
  return await get(url, headers: _createHeaderInfo());
}

/**
 * Creates the header info by reading the credentials and inserting
 * them into the correct format
 */
Map<String, String> _createHeaderInfo() {
  // todo read credentials from properties file
  String username = 'admin';
  String password = 'password';

  String content = 'Basic ' + base64Encode(utf8.encode('$username:$password'));

  return <String, String>{'authorization': content};
}

/**
 * Creates a list of items from a given json string
 */
List<Item> parseItemLst(String json) {
  var parsed = jsonDecode(json);
  List<Item> item_lst =
      (parsed as List).map((data) => new Item.fromJson(data)).toList();
  return item_lst;
}
