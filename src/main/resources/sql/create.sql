CREATE TABLE "book" (
  "bookId" serial NOT NULL,
  "isbn" varchar(17) NOT NULL UNIQUE,
  "languageId" varchar(17) NOT NULL UNIQUE,
  "title" varchar(32) NOT NULL,
  "author" varchar(32) NOT NULL,
  "domain" varchar(32) NOT NULL,
  "publisher" varchar(32) NOT NULL,
  "description" varchar(255),
  "price" FLOAT NOT NULL,
  CONSTRAINT book_pk PRIMARY KEY ("bookId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "language" (
  "languageId" serial NOT NULL,
  "languageName" varchar(20) NOT NULL UNIQUE,
  CONSTRAINT language_pk PRIMARY KEY ("languageId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "author" (
  "authorId" serial NOT NULL,
  "authorName" varchar(32) NOT NULL UNIQUE,
  CONSTRAINT author_pk PRIMARY KEY ("authorId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "domain" (
  "domainId" serial NOT NULL,
  "domainName" varchar(32) NOT NULL UNIQUE,
  CONSTRAINT domain_pk PRIMARY KEY ("domainId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "publisher" (
  "publisherId" serial NOT NULL,
  "publisherName" varchar(32) NOT NULL UNIQUE,
  CONSTRAINT publisher_pk PRIMARY KEY ("publisherId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "order" (
  "orderId" serial NOT NULL,
  "orderStatusId" integer NOT NULL,
  "dateOrdered" DATE NOT NULL,
  "shippingAddressId" integer NOT NULL,
  "bookOrdered1" integer,
  "bookOrdered2" integer,
  "bookOrdered3" integer,
  "bookOrdered4" integer,
  "bookOrdered5" integer,
  "totalPrice" FLOAT NOT NULL,
  CONSTRAINT order_pk PRIMARY KEY ("orderId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "user" (
  "userId" serial NOT NULL,
  "username" varchar(16) NOT NULL UNIQUE,
  "password" varchar(64) NOT NULL,
  "userInfoId" integer,
  "shippingAddressId" integer,
  "orderId" integer,
  "userRoleId" integer NOT NULL DEFAULT '1',
  CONSTRAINT user_pk PRIMARY KEY ("userId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "userInfo" (
  "userInfoId" serial NOT NULL,
  "name" varchar(32) NOT NULL,
  "surname" varchar(32) NOT NULL,
  "birthdate" DATE NOT NULL,
  "gender" varchar(16) NOT NULL,
  "email" varchar(32) NOT NULL,
  CONSTRAINT userInfo_pk PRIMARY KEY ("userInfoId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "gender" (
  "genderId" serial NOT NULL,
  "genderName" varchar(16) NOT NULL UNIQUE,
  CONSTRAINT gender_pk PRIMARY KEY ("genderId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "shippingAddress" (
  "shippingAddressId" serial NOT NULL,
  "name" varchar(32) NOT NULL,
  "country" varchar(32) NOT NULL,
  "city" varchar(32) NOT NULL,
  "street" varchar(32) NOT NULL,
  "telephoneNumber" varchar(32) NOT NULL,
  CONSTRAINT shippingAddress_pk PRIMARY KEY ("shippingAddressId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "bookOrdered" (
  "bookOrderedId" serial NOT NULL,
  "bookId" serial NOT NULL,
  "quantity" integer NOT NULL DEFAULT '1',
  CONSTRAINT bookOrdered_pk PRIMARY KEY ("bookOrderedId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "userRole" (
  "userRoleId" serial NOT NULL,
  "userRoleName" VARCHAR(16) NOT NULL UNIQUE,
  CONSTRAINT userRole_pk PRIMARY KEY ("userRoleId")
) WITH (
OIDS=FALSE
);



ALTER TABLE "book" ADD CONSTRAINT "book_fk0" FOREIGN KEY ("languageId") REFERENCES "language"("languageName");
ALTER TABLE "book" ADD CONSTRAINT "book_fk1" FOREIGN KEY ("author") REFERENCES "author"("authorName");
ALTER TABLE "book" ADD CONSTRAINT "book_fk2" FOREIGN KEY ("domain") REFERENCES "domain"("domainName");
ALTER TABLE "book" ADD CONSTRAINT "book_fk3" FOREIGN KEY ("publisher") REFERENCES "publisher"("publisherName");





ALTER TABLE "order" ADD CONSTRAINT "order_fk0" FOREIGN KEY ("shippingAddressId") REFERENCES "shippingAddress"("shippingAddressId");
ALTER TABLE "order" ADD CONSTRAINT "order_fk1" FOREIGN KEY ("bookOrdered1") REFERENCES "bookOrdered"("bookOrderedId");
ALTER TABLE "order" ADD CONSTRAINT "order_fk2" FOREIGN KEY ("bookOrdered2") REFERENCES "bookOrdered"("bookOrderedId");
ALTER TABLE "order" ADD CONSTRAINT "order_fk3" FOREIGN KEY ("bookOrdered3") REFERENCES "bookOrdered"("bookOrderedId");
ALTER TABLE "order" ADD CONSTRAINT "order_fk4" FOREIGN KEY ("bookOrdered4") REFERENCES "bookOrdered"("bookOrderedId");
ALTER TABLE "order" ADD CONSTRAINT "order_fk5" FOREIGN KEY ("bookOrdered5") REFERENCES "bookOrdered"("bookOrderedId");

ALTER TABLE "user" ADD CONSTRAINT "user_fk0" FOREIGN KEY ("userInfoId") REFERENCES "userInfo"("userInfoId");
ALTER TABLE "user" ADD CONSTRAINT "user_fk1" FOREIGN KEY ("shippingAddressId") REFERENCES "shippingAddress"("shippingAddressId");
ALTER TABLE "user" ADD CONSTRAINT "user_fk2" FOREIGN KEY ("userRoleId") REFERENCES "userRole"("userRoleId");

ALTER TABLE "userInfo" ADD CONSTRAINT "userInfo_fk0" FOREIGN KEY ("gender") REFERENCES "gender"("genderName");



ALTER TABLE "bookOrdered" ADD CONSTRAINT "bookOrdered_fk0" FOREIGN KEY ("bookId") REFERENCES "book"("bookId");

