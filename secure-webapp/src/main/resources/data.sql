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

INSERT INTO user (user_id, username, password, role) VALUES (1, "felixS", "schwart1201", "ROLE_USER");
INSERT INTO user (user_id, username, password, role) VALUES (2, "sinje", "cremer0704", "ROLE_USER");
INSERT INTO user (user_id, username, password, role) VALUES (3, "kathi", "nadler1005", "ROLE_USER");
INSERT INTO user (user_id, username, password, role) VALUES (4, "lena", "tschentscher2505", "ROLE_USER");
INSERT INTO user (user_id, username, password, role) VALUES (5, "kira", "jehns1606", "ROLE_USER");
INSERT INTO user (user_id, username, password, role) VALUES (6, "silas", "hoffmann1706", "ROLE_USER");
INSERT INTO user (user_id, username, password, role) VALUES (7, "lina", "velte1607", "ROLE_USER");
INSERT INTO user (user_id, username, password, role) VALUES (8, "felixR", "reinhardt2907", "ROLE_USER");
INSERT INTO user (user_id, username, password, role) VALUES (9, "ole", "ribbeck0108", "ROLE_USER");
INSERT INTO user (user_id, username, password, role) VALUES (10, "jules", "kocher0108", "ROLE_USER");
INSERT INTO user (user_id, username, password, role) VALUES (11, "hannah", "dürkop1708", "ROLE_USER");
INSERT INTO user (user_id, username, password, role) VALUES (12, "linshu", "gao2709", "ROLE_USER");


INSERT INTO room (room_id, room_number, level, room_description)
VALUES (100, 1, 0, "Zimmer neben Eingangstür");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (101, 2, 0, "Zimmer gegenüber Eingangstür");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (102, 3, 0, "Zimmer gegenüber der Treppe");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (103, 4, 0, "Badezimmer Erdgeschoss");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (104, 4, 0, "Zimmer hinter Badezimmer Erdgeschoss");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (105, 5, 0, "Küche Erdgeschoss");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (106, 6, 0, "Gästeklo Erdgeschoss");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (107, 1, 1, "Badezimmer 1. OG rechts von Treppe");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (108, 2, 1, "Zimmer gegenüber Verbindungstür");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (109, 3, 1, "Zimmer gegenüber Badezimmer 1. OG (rechts)");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (110, 4, 1, "Zimmer gegenüber Treppe");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (111, 5, 1, "Zimmer Küche 1. OG");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (112, 6, 1, "Zimmer neben Küche 1. OG");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (113, 7, 1, "Zimmer neben Badezimmer 1. OG");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (114, 8, 1, "Badezimmer 1. OG links von Treppe");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (115, 1, 2, "Küche 2. OG");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (116, 2, 2, "Zimmer links von der Treppe");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (117, 3, 2, "Zimmer in der Mitte");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (118, 4, 2, "Zimmer rechts von der Treppe");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (119, 0, 0, "Flur Erdgeschoss");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (120, 0, 1, "Flur 1. OG");

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (121, 0, 2, "Flur 2. OG");


INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (200, 100, 105, 103, 103);

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (201, 101, 105, 103, 103);

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (202, 102, 105, 103, 103);

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (203, 104, 105, 103, 103);

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (205, 108, 111, 107, 120);

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (206, 109, 111, 107, 120);

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (207, 110, 111, 107, 120);

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (208, 112, 111, 114, 120);

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (209, 113, 111, 114, 120);

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (210, 116, 115, 107, 120);

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (211, 117, 115, 114, 120);

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (212, 118, 115, 107, 120);


INSERT INTO flatmate (flatmate_id, user_id, living_space_id, firstname, surname, birthday)
VALUES (300, 1, 202, "Felix", "Schwart", '2018-01-12');

INSERT INTO flatmate (flatmate_id, user_id, living_space_id, firstname, surname, birthday)
VALUES (301, 2, 209, "Sinje", "Cremer", '2018-04-07');

INSERT INTO flatmate (flatmate_id, user_id, living_space_id, firstname, surname, birthday)
VALUES (302, 3, 208, "Kathi", "Nadler", '2018-01-10');

INSERT INTO flatmate (flatmate_id, user_id, living_space_id, firstname, surname, birthday)
VALUES (303, 4, 207, "Lena", "Tschentscher", '2018-05-25');

INSERT INTO flatmate (flatmate_id, user_id, living_space_id, firstname, surname, birthday)
VALUES (304, 5, 206, "Kira", "Jens", '2018-06-16');

INSERT INTO flatmate (flatmate_id, user_id, living_space_id, firstname, surname, birthday)
VALUES (305, 6, 205, "Silas", "Hoffmann", '2018-06-17');

INSERT INTO flatmate (flatmate_id, user_id, living_space_id, firstname, surname, birthday)
VALUES (306, 7, 203, "Lina", "Velte", '2018-07-16');

INSERT INTO flatmate (flatmate_id, user_id, living_space_id, firstname, surname, birthday)
VALUES (307, 8, 210, "Felix", "Reinhardt", '2018-07-29');

INSERT INTO flatmate (flatmate_id, user_id, living_space_id, firstname, surname, birthday)
VALUES (308, 9, 211, "Ole", "Ribbeck", '2018-08-01');

INSERT INTO flatmate (flatmate_id, user_id, living_space_id, firstname, surname, birthday)
VALUES (309, 10, 200, "Julius", "Kocher", '2018-08-02');

INSERT INTO flatmate (flatmate_id, user_id, living_space_id, firstname, surname, birthday)
VALUES (310, 11, 212, "Hannah", "Unknown", '2018-08-17');

INSERT INTO flatmate (flatmate_id, user_id, living_space_id, firstname, surname, birthday)
VALUES (311, 12, 201, "Linshu", "Gao", '2018-09-27');


INSERT INTO task (task_id, task_title, description, task_status, publishing_date)
VALUES (400, "test title", "test description 1", false, '2018-09-27');

INSERT INTO task_flatmate (task_id, flatmate_id)
VALUES (400, 300);


INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (500, "Klopapier", "BATHROOM_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (501, "Küchenpapier", "KITCHEN_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (502, "Backpapier", "KITCHEN_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (503, "Badreiniger", "BATHROOM_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (504, "Bioabfalltüten", "CLEANING_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (505, "Desinfektionsspray", "CLEANING_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (506, "Gelbe Müllsäcke", "CLEANING_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (507, "Geschirrspülsalz", "KITCHEN_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (508, "Kalkreiniger", "CLEANING_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (509, "Lappen", "CLEANING_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (510, "Metallschwäme", "CLEANING_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (511, "Olivenöl", "KITCHEN_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (512, "Pfeffer (Körner)", "KITCHEN_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (513, "Rapsöl", "KITCHEN_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (514, "Salz", "KITCHEN_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (515, "Scheuermilch", "CLEANING_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (516, "Schwämme", "CLEANING_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (517, "Seife", "CLEANING_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (518, "Sonnenblumenöl", "KITCHEN_SUPPLY");

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (519, "Spüli", "CLEANING_SUPPLY");
