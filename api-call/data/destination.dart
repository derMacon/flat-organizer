class Destination {

  int roomId;
  int roomNumber;
  int level;
  String roomDescription;

  Destination({this.roomId, this.roomNumber, this.level, this.roomDescription});

  Destination.fromJson(Map<String, dynamic> json) {
    roomId = json['roomId'];
    roomNumber = json['roomNumber'];
    level = json['level'];
    roomDescription = json['roomDescription'];
  }

  String toString() {
    return "{"
      + "roomId: " + roomId.toString() + ", "
      + "roomNumber: " + roomNumber.toString() + ", "
      + "level: " + level.toString() + ", "
      + "roomDescription: " + roomDescription
      + "}";
  }

}
