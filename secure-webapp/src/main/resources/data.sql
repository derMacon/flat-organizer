insert into app_user (username, password, role)
values ('user1', '$2a$10$FBJBMqWqoPXc2aEKeOZoaechKfjahVlrNi6wFQwoq1KhLnqxCzqy2', 'ROLE_USER')
ON CONFLICT DO NOTHING;


INSERT INTO room (room_id, room_number, level, room_description)
VALUES (100, 1, 0, 'Zimmer neben Eingangstür')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (101, 2, 0, 'Zimmer gegenüber Eingangstür')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (102, 3, 0, 'Zimmer gegenüber der Treppe')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (103, 4, 0, 'Badezimmer Erdgeschoss')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (104, 4, 0, 'Zimmer hinter Badezimmer Erdgeschoss')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (105, 5, 0, 'Küche Erdgeschoss')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (106, 6, 0, 'Gästeklo Erdgeschoss')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (107, 1, 1, 'Badezimmer rechts von Treppe')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (108, 2, 1, 'Zimmer gegenüber Verbindungstür')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (109, 3, 1, 'Zimmer gegenüber Badezimmer (rechts)')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (110, 4, 1, 'Zimmer gegenüber Treppe')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (111, 5, 1, 'Zimmer Küche')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (112, 6, 1, 'Zimmer neben Küche')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (113, 7, 1, 'Zimmer neben Badezimmer')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (114, 8, 1, 'Badezimmer links von Treppe')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (115, 1, 2, 'Küche')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (116, 2, 2, 'Zimmer links von der Treppe')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (117, 3, 2, 'Zimmer in der Mitte')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (118, 4, 2, 'Zimmer rechts von der Treppe')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (119, 0, 0, 'Flur Erdgeschoss')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (120, 0, 1, 'Flur')
ON CONFLICT DO NOTHING;

INSERT INTO room (room_id, room_number, level, room_description)
VALUES (121, 0, 2, 'Flur')
ON CONFLICT DO NOTHING;


INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (200, 100, 105, 103, 103)
ON CONFLICT DO NOTHING;

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (201, 101, 105, 103, 103)
ON CONFLICT DO NOTHING;

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (202, 102, 105, 103, 103)
ON CONFLICT DO NOTHING;

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (203, 104, 105, 103, 103)
ON CONFLICT DO NOTHING;

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (205, 108, 111, 107, 120)
ON CONFLICT DO NOTHING;

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (206, 109, 111, 107, 120)
ON CONFLICT DO NOTHING;

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (207, 110, 111, 107, 120)
ON CONFLICT DO NOTHING;

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (208, 112, 111, 114, 120)
ON CONFLICT DO NOTHING;

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (209, 113, 111, 114, 120)
ON CONFLICT DO NOTHING;

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (210, 116, 115, 107, 120)
ON CONFLICT DO NOTHING;

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (211, 117, 115, 114, 120)
ON CONFLICT DO NOTHING;

INSERT INTO living_space (living_space_id, bedroom_id, kitchen_id, bathroom_id, storage_id)
VALUES (212, 118, 115, 107, 120)
ON CONFLICT DO NOTHING;


INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (500, 'Klopapier', 'BATHROOM_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (501, 'Küchenpapier', 'KITCHEN_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (502, 'Backpapier', 'KITCHEN_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (503, 'Badreiniger', 'BATHROOM_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (504, 'Bioabfalltüten', 'CLEANING_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (505, 'Desinfektionsspray', 'CLEANING_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (506, 'Gelbe Müllsäcke', 'CLEANING_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (507, 'Geschirrspülsalz', 'KITCHEN_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (508, 'Kalkreiniger', 'CLEANING_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (509, 'Lappen', 'CLEANING_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (510, 'Metallschwäme', 'CLEANING_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (511, 'Olivenöl', 'KITCHEN_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (512, 'Pfeffer (Körner)', 'KITCHEN_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (513, 'Rapsöl', 'KITCHEN_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (514, 'Salz', 'KITCHEN_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (515, 'Scheuermilch', 'CLEANING_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (516, 'Schwämme', 'CLEANING_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (517, 'Seife', 'CLEANING_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (518, 'Sonnenblumenöl', 'KITCHEN_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (519, 'Spüli', 'CLEANING_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (520, 'Natron', 'CLEANING_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (521, 'Backpulver', 'KITCHEN_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (522, 'Essigessenz', 'KITCHEN_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (523, 'Abflussreiniger', 'CLEANING_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (524, 'Bratöl', 'KITCHEN_SUPPLY')
ON CONFLICT DO NOTHING;

INSERT INTO item_preset (preset_id, preset_name, supply_category)
VALUES (525, 'Bioabfalltüten', 'KITCHEN_SUPPLY')
ON CONFLICT DO NOTHING;
