CREATE TABLE "book" (
  "book_id"     SERIAL      NOT NULL,
  "isbn"        VARCHAR(17) NOT NULL UNIQUE,
  "title"       VARCHAR(32) NOT NULL,
  "author"      VARCHAR(32) NOT NULL,
  "description" VARCHAR(255),
  "price"       INTEGER,
  "amount"      INTEGER DEFAULT 0,
  CONSTRAINT book_pk PRIMARY KEY ("book_id")
) WITH (
OIDS = FALSE
);

CREATE TABLE "order" (
  "order_id"        SERIAL  NOT NULL,
  "user_id"         INTEGER NOT NULL,
  "order_status_id" INTEGER NOT NULL,
  "date_ordered"    DATE    NOT NULL,
  "total_price"     FLOAT   NOT NULL,
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


CREATE TABLE "gender" (
  "gender_id"   SERIAL      NOT NULL,
  "gender_name" VARCHAR(16) NOT NULL UNIQUE,
  CONSTRAINT gender_pk PRIMARY KEY ("gender_id")
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
  "order_id" INTEGER NOT NULL,
  "book_id"  INTEGER NOT NULL,
  "quantity" INTEGER NOT NULL DEFAULT '1'
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
