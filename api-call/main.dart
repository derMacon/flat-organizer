import 'api/api-call.dart';
import 'data/item.dart';

main() async {
  List<Item> newItems = await getNewItems();
  List<Item> oldItems = await getOldItems();

  print("\nnew items:\n");
  print(newItems);

  print("\nold items:\n");
  print(oldItems);
  print("\n");
}
