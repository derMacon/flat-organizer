class Item {

  String name;
  int count;

  Item({this.name, this.count});

  Item.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    count = json['count'];
  }

}