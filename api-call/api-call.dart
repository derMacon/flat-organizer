import 'dart:convert';
import 'package:http/http.dart';
// import 'package:http/http.dart' as http;
import './item.dart';

const String server_ip = "168.119.120.2";
const String proj_name = "flat-organizer-demo";
const String endpoint_newItems = "/api/groceryList/getNewItems";

String createHeaderInfo() {
  String username = 'admin';
  String password = 'password';
  return 'Basic ' + base64Encode(utf8.encode('$username:$password'));
}

main() async {
  String basicAuth = createHeaderInfo();
  String url = 'http://' + server_ip + ':8080/' + proj_name + endpoint_newItems;
	print("url: " + url);

  Response r = await get(url, headers: <String, String>{'authorization': basicAuth});
  print(r.statusCode);
  print(r.body);
}
