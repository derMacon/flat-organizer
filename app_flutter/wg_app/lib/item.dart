class Item {

  String name;
  int count;
  String requester;

  Item({this.name, this.count, this.requester});

  Item.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    count = json['count'];
    requester = json['requester'];
  }

}