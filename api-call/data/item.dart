import './destination.dart';

class Item {

  int itemId;
  int itemCount;
  String itemName;
  Destination destination;
  bool status;

  Item({this.itemId, this.itemCount, this.itemName, this.destination, this.status});

  Item.fromJson(Map<String, dynamic> json) {
    itemId = json['itemId'];
    itemCount = json['itemCount'];
    itemName = json['itemName'];
    destination = Destination.fromJson(json['destination']);
    status = json['status'];
  }

  String toString() {
    return "{"
      + "itemId: " + itemId.toString() + ", "
      + "itemCount: " + itemCount.toString() + ", "
      + "itemName: " + itemName.toLowerCase() + ", "
      + "destination: " + destination.toString() + ", "
      + "status: " + status.toString() + "}";
  }

}
