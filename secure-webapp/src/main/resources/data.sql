DROP DATABASE flat_app;
CREATE DATABASE flat_app;

USE flat_app;
SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE user (
  user_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(500) NOT NULL,
  password VARCHAR(5000) NOT NULL,
  role VARCHAR(5000) NOT NULL
);

CREATE TABLE room (
  room_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  room_number INT(6) UNSIGNED NOT NULL,
  level INT(6) UNSIGNED NOT NULL,
  room_description VARCHAR(5000) NOT NULL
);

CREATE TABLE living_space (
  living_space_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  bedroom_id INT(6) UNSIGNED NOT NULL,
  FOREIGN KEY (bedroom_id) REFERENCES room (room_id),
  kitchen_id INT(6) UNSIGNED NOT NULL,
  FOREIGN KEY (kitchen_id) REFERENCES room (room_id),
  bathroom_id INT(6) UNSIGNED NOT NULL,
  FOREIGN KEY (bathroom_id) REFERENCES room (room_id),
  storage_id INT(6) UNSIGNED NOT NULL,
  FOREIGN KEY (storage_id) REFERENCES room (room_id)
);

CREATE TABLE flatmate (
  flatmate_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  user_id INT(6) UNSIGNED NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user (user_id),
  living_space_id INT(6) UNSIGNED NOT NULL,
  FOREIGN KEY (living_space_id) REFERENCES room (room_id),
  firstname VARCHAR(500) NOT NULL,
  surname VARCHAR(500) NOT NULL,
  birthday DATE NOT NULL
);

CREATE TABLE item (
  item_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  item_count INT(6) UNSIGNED NOT NULL,
  item_name VARCHAR(500) NOT NULL,
  destination_id INT(6) UNSIGNED,
  FOREIGN KEY (destination_id) REFERENCES room (room_id),
  status BIT
);

CREATE TABLE task (
  task_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  task_title VARCHAR(500) NOT NULL,
  description VARCHAR(5000) NOT NULL,
  task_status BIT,
  publishing_date DATE NOT NULL
);

CREATE TABLE task_flatmate (
  task_id INT(6) UNSIGNED NOT NULL,
  flatmate_id INT(6) UNSIGNED NOT NULL,
  PRIMARY KEY CLUSTERED (task_id, flatmate_id),
  FOREIGN KEY (task_id) REFERENCES task (task_id),
  FOREIGN KEY (flatmate_id) REFERENCES flatmate (flatmate_id)
);

CREATE TABLE item_preset (
  preset_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  preset_name VARCHAR(500) NOT NULL,
  supply_category VARCHAR(30) NOT NULL
);

