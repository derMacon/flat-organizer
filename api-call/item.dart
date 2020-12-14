import './Destination.dart';

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


main() {
  Map<String, dynamic> map = {
        "itemId": 3,
        "itemCount": 1,
        "itemName": "Backpapier",
        "destination": {
            "roomId": 115,
            "roomNumber": 1,
            "level": 2,
            "roomDescription": "Küche 2. OG"
        },
        "status": false,
        "valid": true
    };

    Item it = new Item.fromJson(map);
    print(it);
}
