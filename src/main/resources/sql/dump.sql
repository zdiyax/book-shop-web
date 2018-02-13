INSERT INTO "user_role" ("user_role_name") VALUES ('CUSTOMER'), ('OPERATOR');
INSERT INTO "locale" ("locale_name") VALUES ('en'), ('ru');
INSERT INTO "gender" ("gender_name") VALUES ('Male'), ('Female'), ('Do not specify');
INSERT INTO "order_status" VALUES (1, 'waiting'), (2, 'ready');

INSERT INTO "user" ("locale_id", "username", "password", "user_role_id") VALUES (1, 'operator', '01f1b85bfc0ec175495902a6b735dd73', 2);


INSERT INTO "book" ("isbn", "title", "author", "description", "price")
VALUES
  ('978-3-16-148410-0', 'Clean Code', 'Robert Martin',
   'Even bad code can function. But if code isn’t clean, it can bring a development organization to its knees. ' ||
   'Every year, countless hours and significant resources are lost because of poorly written code. But it doesn’t have to be that way.',
   1000),
  ('978-3-16-148410-1', 'Effective Java', 'Joshua Bloch',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1500),
  ('978-3-16-148410-2', 'Fake Book title 1', 'Fake Author 1',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999),
  ('978-3-16-148410-3', 'Fake Book title 2', 'Fake Author 2',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999),
  ('978-3-16-148410-4', 'Fake Book title 3', 'Fake Author 3',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999),
  ('978-3-16-148410-5', 'Fake Book title 4', 'Fake Author 4',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999),
  ('978-3-16-148410-6', 'Fake Book title 5', 'Fake Author 5',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999),
  ('978-3-16-148410-7', 'Fake Book title 6', 'Fake Author 6',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999),
  ('978-3-16-148410-8', 'Fake Book title 7', 'Fake Author 7',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999),
  ('978-3-16-148410-9', 'Fake Book title 8', 'Fake Author 8',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999),
  ('978-3-16-148410', 'Fake Book title 9', 'Fake Author 9',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999),
  ('978-3-16-148411', 'Fake Book title 10', 'Fake Author 10',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ',
   1999);