INSERT INTO "userRole" ("userRoleName") VALUES ('CUSTOMER'), ('OPERATOR');

INSERT INTO author VALUES (1, 'Bruce Eckel');
INSERT INTO language ("languageName") VALUES ('English'), ('Russian');
INSERT INTO domain VALUES (1, 'IT/Computers');
INSERT INTO publisher VALUES (1, 'SAMS PH');
INSERT INTO book VALUES (1, '143134-34', 'English', 'Thinking in Java', 'Bruce Eckel', 'IT/Computers', 'SAMS PH', 'Some description', 13314);

INSERT INTO "user" ("username", "password", "userRoleId") VALUES ('username', '5f4dcc3b5aa765d61d8327deb882cf99', 1);

INSERT INTO gender ("genderName") VALUES ('Male'), ('Female'), ('Do not specify');
INSERT INTO author ("authorName") VALUES ('Cay Horstmann');
