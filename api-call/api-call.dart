import 'package:http/http.dart' as http;

main() async {
	print("hi!");
  final response = await http.get('https://jsonplaceholder.typicode.com/albums/1');
  print(response.statusCode);
}
