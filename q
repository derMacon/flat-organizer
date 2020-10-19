[1mdiff --git a/secure-webapp/src/main/resources/application.properties b/secure-webapp/src/main/resources/application.properties[m
[1mindex e0df7cb..2658c53 100644[m
[1m--- a/secure-webapp/src/main/resources/application.properties[m
[1m+++ b/secure-webapp/src/main/resources/application.properties[m
[36m@@ -7,3 +7,5 @@[m [mspring.datasource.password=A;pKcpQ/Y*Gq42apnJb8agP.p;=&A)gsfq[m
 #spring.datasource.password=password[m
 spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver[m
 server.port=8090[m
[32m+[m[32m#spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl[m
[32m+[m[32mspring.jpa.hibernate.naming-strategy = org.hibernate.cfg.DefaultComponentSafeNamingStrategy[m
\ No newline at end of file[m
[1mdiff --git a/secure-webapp/src/main/resources/schema.sql b/secure-webapp/src/main/resources/schema.sql[m
[1mindex 31b577a..ecfcc67 100644[m
[1m--- a/secure-webapp/src/main/resources/schema.sql[m
[1m+++ b/secure-webapp/src/main/resources/schema.sql[m
[36m@@ -28,7 +28,7 @@[m [mCREATE TABLE flatmate ([m
 );[m
 [m
 CREATE TABLE item ([m
[31m-  item_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,[m
[32m+[m[32m  itemId INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,[m
   item_count INT(6) UNSIGNED NOT NULL,[m
   flatmate_id INT(6) UNSIGNED,[m
   FOREIGN KEY (flatmate_id) REFERENCES flatmate (flatmate_id),[m
[36m@@ -62,6 +62,6 @@[m [mVALUES (6, 2, 4, "firstname u2", "surname u2", '2018-10-20');[m
 INSERT INTO flatmate (flatmate_id, user_id, room_id, firstname, surname, birthday)[m
 VALUES (7, 20, 4, "firstname short u", "surname short u", '2018-10-20');[m
 [m
[31m-INSERT INTO item (item_id, item_count, flatmate_id, item_name) VALUES (8, 5, 5, "product 1");[m
[31m-INSERT INTO item (item_id, item_count, flatmate_id, item_name) VALUES (9, 5, 5, "product 2");[m
[32m+[m[32mINSERT INTO item (itemId, item_count, flatmate_id, item_name) VALUES (8, 5, 5, "product 1");[m
[32m+[m[32mINSERT INTO item (itemId, item_count, flatmate_id, item_name) VALUES (9, 5, 5, "product 2");[m
 [m
