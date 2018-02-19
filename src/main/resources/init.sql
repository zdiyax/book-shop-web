ALTER TABLE "order"
  DROP CONSTRAINT IF EXISTS "order_fk0";
ALTER TABLE "order"
  DROP CONSTRAINT IF EXISTS "order_fk1";
ALTER TABLE "user"
  DROP CONSTRAINT IF EXISTS "user_fk0";
ALTER TABLE "user"
  DROP CONSTRAINT IF EXISTS "user_fk1";
ALTER TABLE "user"
  DROP CONSTRAINT IF EXISTS "user_fk3";
ALTER TABLE "order_items"
  DROP CONSTRAINT IF EXISTS "order_items_fk0";
ALTER TABLE "order_items"
  DROP CONSTRAINT IF EXISTS "order_items_fk1";

DROP TABLE IF EXISTS "order_items";
DROP TABLE IF EXISTS "order_status";
DROP TABLE IF EXISTS "locale";
DROP TABLE IF EXISTS "user_role";
DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS "order";
DROP TABLE IF EXISTS "book";

-- ____________________________________________________________________________________________________

CREATE TABLE "book" (
  "book_id"     SERIAL            NOT NULL,
  "isbn"        VARCHAR(17)       NOT NULL UNIQUE,
  "title"       VARCHAR(32)       NOT NULL,
  "author"      VARCHAR(32)       NOT NULL,
  "description" VARCHAR(255),
  "price"       INTEGER,
  "amount"      INTEGER DEFAULT 0 NOT NULL,
  "is_active"   BOOLEAN DEFAULT TRUE,
  CONSTRAINT book_pk PRIMARY KEY ("book_id")
) WITH (
OIDS = FALSE
);

CREATE TABLE "order" (
  "order_id"        SERIAL  NOT NULL,
  "user_id"         INTEGER NOT NULL,
  "order_status_id" INTEGER NOT NULL,
  "total_price"     INTEGER NOT NULL,
  "is_active"       BOOLEAN DEFAULT TRUE,
  CONSTRAINT order_pk PRIMARY KEY ("order_id")
) WITH (
OIDS = FALSE
);

CREATE TABLE "user" (
  "user_id"          SERIAL      NOT NULL,
  "locale_id"        INTEGER     NOT NULL,
  "username"         VARCHAR(16) NOT NULL UNIQUE,
  "password"         VARCHAR(64) NOT NULL,
  "full_name"        VARCHAR(32),
  "email"            VARCHAR(32),
  "address"          VARCHAR(64),
  "telephone_number" VARCHAR(16),
  "user_role_id"     INTEGER     NOT NULL DEFAULT '1',
  CONSTRAINT user_pk PRIMARY KEY ("user_id")
) WITH (
OIDS = FALSE
);

CREATE TABLE "user_role" (
  "user_role_id"   SERIAL      NOT NULL,
  "user_role_name" VARCHAR(16) NOT NULL UNIQUE,
  CONSTRAINT userRole_pk PRIMARY KEY ("user_role_id")
) WITH (
OIDS = FALSE
);

CREATE TABLE "locale" (
  "locale_id"   SERIAL      NOT NULL,
  "locale_name" VARCHAR(16) NOT NULL,
  CONSTRAINT locale_pk PRIMARY KEY ("locale_id")
) WITH (
OIDS = FALSE
);

CREATE TABLE "order_status" (
  "order_status_id"   SERIAL      NOT NULL,
  "order_status_name" VARCHAR(32) NOT NULL UNIQUE,
  CONSTRAINT order_status_pk PRIMARY KEY ("order_status_id")
) WITH (
OIDS = FALSE
);

CREATE TABLE "order_items" (
  "order_items_id" SERIAL  NOT NULL,
  "order_id"       INTEGER NOT NULL,
  "book_id"        INTEGER NOT NULL,
  "quantity"       INTEGER NOT NULL
) WITH (
OIDS = FALSE
);


ALTER TABLE "order"
  ADD CONSTRAINT "order_fk0" FOREIGN KEY ("user_id") REFERENCES "user" ("user_id");
ALTER TABLE "order"
  ADD CONSTRAINT "order_fk1" FOREIGN KEY ("order_status_id") REFERENCES "order_status" ("order_status_id");
ALTER TABLE "user"
  ADD CONSTRAINT "user_fk0" FOREIGN KEY ("locale_id") REFERENCES "locale" ("locale_id");
ALTER TABLE "user"
  ADD CONSTRAINT "user_fk3" FOREIGN KEY ("user_role_id") REFERENCES "user_role" ("user_role_id");
ALTER TABLE "order_items"
  ADD CONSTRAINT "order_items_fk0" FOREIGN KEY ("order_id") REFERENCES "order" ("order_id");
ALTER TABLE "order_items"
  ADD CONSTRAINT "order_items_fk1" FOREIGN KEY ("book_id") REFERENCES "book" ("book_id");


INSERT INTO "user_role" ("user_role_name") VALUES ('CUSTOMER'), ('OPERATOR');
INSERT INTO "locale" ("locale_name") VALUES ('en'), ('ru');
INSERT INTO "order_status" VALUES (1, 'waiting'), (2, 'ready'), (3, 'completed'), (4, 'cancelled');

INSERT INTO "user" ("locale_id", "username", "password", "user_role_id")
VALUES (1, 'customer', '01f1b85bfc0ec175495902a6b735dd73', 1);
INSERT INTO "user" ("locale_id", "username", "password", "user_role_id")
VALUES (1, 'operator', '01f1b85bfc0ec175495902a6b735dd73', 2);

-- ____________________________________________________________________________________________________


INSERT INTO "book" ("isbn", "title", "author", "description", "price", "amount")
VALUES
  ('978-3-16-148410-0', 'Clean Code', 'Robert Martin',
   'Even bad code can function. But if code isn’t clean, it can bring a development organization to its knees. ' ||
   'Every year, countless hours and significant resources are lost because of poorly written code. But it does not ' ||
   'have to be that way.', 1000, 50),
  ('978-3-16-148410-1', 'Effective Java', 'Joshua Bloch',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release' ||
   ' of Java 6. ', 1500, 50),
  ('978-3-16-148410-2', 'Fake Book title 1', 'Fake Author 1',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999, 50),
  ('978-3-16-148410-3', 'Fake Book title 2', 'Fake Author 2',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999, 50),
  ('978-3-16-148410-4', 'Fake Book title 3', 'Fake Author 3',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999, 50),
  ('978-3-16-148410-5', 'Fake Book title 4', 'Fake Author 4',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999, 50),
  ('978-3-16-148410-6', 'Fake Book title 5', 'Fake Author 5',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999, 50),
  ('978-3-16-148410-7', 'Fake Book title 6', 'Fake Author 6',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999, 50),
  ('978-3-16-148410-8', 'Fake Book title 7', 'Fake Author 7',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999, 50),
  ('978-3-16-148410-9', 'Fake Book title 8', 'Fake Author 8',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999, 50),
  ('978-3-16-148410', 'Fake Book title 9', 'Fake Author 9',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ', 1999, 50),
  ('978-3-16-148411', 'Fake Book title 10', 'Fake Author 10',
   'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. ',
   1999, 50);

