CREATE TABLE "book" (
  "book_id"      SERIAL      NOT NULL,
  "isbn"        VARCHAR(17) NOT NULL UNIQUE,
  "title"       VARCHAR(32) NOT NULL,
  "description" VARCHAR(255),
  "price"       FLOAT       NOT NULL,
  CONSTRAINT book_pk PRIMARY KEY ("bookId")
) WITH (
OIDS = FALSE
);

CREATE TABLE "order" (
  "order_id"           SERIAL  NOT NULL,
  "user_id"            INTEGER NOT NULL,
  "order_status_id"     INTEGER NOT NULL,
  "date_ordered"       DATE    NOT NULL,
  "total_price"        FLOAT   NOT NULL,
  CONSTRAINT order_pk PRIMARY KEY ("orderId")
) WITH (
OIDS = FALSE
);


CREATE TABLE "user" (
  "user_id"            SERIAL      NOT NULL,
  "localeId"          INTEGER     NOT NULL,
  "username"          VARCHAR(16) NOT NULL UNIQUE,
  "password"          VARCHAR(64) NOT NULL,
  "userInfoId"        INTEGER,
  "userRoleId"        INTEGER     NOT NULL DEFAULT '1',
  CONSTRAINT user_pk PRIMARY KEY ("userId")
) WITH (
OIDS = FALSE
);


CREATE TABLE "userInfo" (
  "userInfoId" SERIAL      NOT NULL,
  "name"       VARCHAR(32) NOT NULL,
  "surname"    VARCHAR(32) NOT NULL,
  "birthdate"  DATE        NOT NULL,
  "gender"     VARCHAR(16) NOT NULL,
  "email"      VARCHAR(32) NOT NULL,
  CONSTRAINT userInfo_pk PRIMARY KEY ("userInfoId")
) WITH (
OIDS = FALSE
);


CREATE TABLE "gender" (
  "genderId"   SERIAL      NOT NULL,
  "genderName" VARCHAR(16) NOT NULL UNIQUE,
  CONSTRAINT gender_pk PRIMARY KEY ("genderId")
) WITH (
OIDS = FALSE
);


CREATE TABLE "userRole" (
  "userRoleId"   SERIAL      NOT NULL,
  "userRoleName" VARCHAR(16) NOT NULL UNIQUE,
  CONSTRAINT userRole_pk PRIMARY KEY ("userRoleId")
) WITH (
OIDS = FALSE
);


CREATE TABLE "locale" (
  "localeId"   SERIAL      NOT NULL,
  "localeName" VARCHAR(16) NOT NULL,
  CONSTRAINT locale_pk PRIMARY KEY ("localeId")
) WITH (
OIDS = FALSE
);


CREATE TABLE "orderStatus" (
  "orderStatusId"   SERIAL      NOT NULL,
  "orderStatusName" VARCHAR(32) NOT NULL UNIQUE,
  CONSTRAINT orderStatus_pk PRIMARY KEY ("orderStatusId")
) WITH (
OIDS = FALSE
);


CREATE TABLE "orderItems" (
  "orderId"  INTEGER NOT NULL,
  "bookId"   INTEGER NOT NULL,
  "quantity" INTEGER NOT NULL DEFAULT '1'
) WITH (
OIDS = FALSE
);


ALTER TABLE "order"
  ADD CONSTRAINT "order_fk0" FOREIGN KEY ("userId") REFERENCES "user" ("userId");
ALTER TABLE "order"
  ADD CONSTRAINT "order_fk1" FOREIGN KEY ("orderStatusId") REFERENCES "orderStatus" ("orderStatusId");

ALTER TABLE "user"
  ADD CONSTRAINT "user_fk0" FOREIGN KEY ("localeId") REFERENCES "locale" ("localeId");
ALTER TABLE "user"
  ADD CONSTRAINT "user_fk1" FOREIGN KEY ("userInfoId") REFERENCES "userInfo" ("userInfoId");
ALTER TABLE "user"
  ADD CONSTRAINT "user_fk3" FOREIGN KEY ("userRoleId") REFERENCES "userRole" ("userRoleId");

ALTER TABLE "userInfo"
  ADD CONSTRAINT "userInfo_fk0" FOREIGN KEY ("gender") REFERENCES "gender" ("genderName");


ALTER TABLE "orderItems"
  ADD CONSTRAINT "orderItems_fk0" FOREIGN KEY ("orderId") REFERENCES "order" ("orderId");
ALTER TABLE "orderItems"
  ADD CONSTRAINT "orderItems_fk1" FOREIGN KEY ("bookId") REFERENCES "book" ("bookId");
