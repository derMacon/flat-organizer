CREATE DATABASE flat_app;

USE flat_app;

CREATE TABLE user (
  user_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(500) NOT NULL,
  password VARCHAR(5000) NOT NULL
);

CREATE TABLE room (
  room_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  level INT(6) UNSIGNED NOT NULL,
  kitchen_id INT(6) UNSIGNED NOT NULL,
  toilet_id INT(6) UNSIGNED NOT NULL,
  room_description VARCHAR(500) NOT NULL
);

CREATE TABLE flatmate (
  flatmate_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  user_id INT(6) UNSIGNED NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user (user_id),
  room_id INT(6) UNSIGNED NOT NULL,
  FOREIGN KEY (room_id) REFERENCES room (room_id),
  firstname VARCHAR(500) NOT NULL,
  surname VARCHAR(500) NOT NULL,
  birthday DATE NOT NULL
);

CREATE TABLE item (
  item_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  item_count INT(6) UNSIGNED NOT NULL,
  flatmate_id INT(6) UNSIGNED,
  FOREIGN KEY (flatmate_id) REFERENCES flatmate (flatmate_id),
  item_name VARCHAR(500) NOT NULL
);

CREATE TABLE task (
  taskId INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(5000) NOT NULL,
  responsibleFlatmate INT(6) UNSIGNED,
  FOREIGN KEY (responsibleFlatmate) REFERENCES  (flatmate_id),
  status BIT,
  publishingDate DATE NOT NULL
);

INSERT INTO user (user_id, username, password) VALUES (1, "user1", "password");
INSERT INTO user (user_id, username, password) VALUES (2, "user2", "password");
INSERT INTO user (user_id, username, password) VALUES (20, "u", "p");

INSERT INTO room (room_id, level, kitchen_id, toilet_id, room_description) VALUES (3, 0, 0, 0,
"vorne links");
INSERT INTO room (room_id, level, kitchen_id, toilet_id, room_description) VALUES (4, 1, 1, 1,
"hinten rechts");

INSERT INTO flatmate (flatmate_id, user_id, room_id, firstname, surname, birthday)
VALUES (5, 1, 3, "firstname u1", "surname u1", '2018-10-20');

INSERT INTO flatmate (flatmate_id, user_id, room_id, firstname, surname, birthday)
VALUES (6, 2, 4, "firstname u2", "surname u2", '2018-10-20');

INSERT INTO flatmate (flatmate_id, user_id, room_id, firstname, surname, birthday)
VALUES (7, 20, 4, "firstname short u", "surname short u", '2018-10-20');

INSERT INTO item (item_id, item_count, flatmate_id, item_name) VALUES (8, 5, 5, "product 1");
INSERT INTO item (item_id, item_count, flatmate_id, item_name) VALUES (9, 5, 5, "product 2");

