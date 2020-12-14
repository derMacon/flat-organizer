import './api-call.dart';
import './item.dart';

main() async {
  print("new items:\n");
  List<Item> newItems = await getNewItems();
  print(newItems);

  print("old items:\n");
  List<Item> oldItems = await getNewItems();
  print(oldItems);
}