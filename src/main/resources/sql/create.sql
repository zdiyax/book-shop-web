CREATE TABLE "book" (
	"bookId" serial PRIMARY KEY NOT NULL,
	"isbn" varchar(17) NOT NULL UNIQUE,
	"language" varchar(17) NOT NULL UNIQUE REFERENCES language(languageName),
	"title" varchar(32) NOT NULL,
	"author" varchar(32) NOT NULL REFERENCES author(authorName),
	"domain" varchar(17) NOT NULL REFERENCES domain(domainName),
	"publisher" varchar(17) NOT NULL REFERENCES publisher(publisherName),
	"description" varchar(255),
	"price" FLOAT NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "language" (
	"languageId" serial PRIMARY KEY NOT NULL,
	"languageName" varchar(17) NOT NULL UNIQUE
) WITH (
  OIDS=FALSE
);



CREATE TABLE "author" (
	"authorId" serial PRIMARY KEY NOT NULL,
	"authorName" varchar(32) NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "domain" (
	"domainId" serial PRIMARY KEY NOT NULL,
	"domainName" varchar(32) NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "publisher" (
	"publisherId" serial PRIMARY KEY NOT NULL,
	"publisherName" varchar(32) NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order" (
	"orderId" serial PRIMARY KEY NOT NULL,
	"orderStatus" varchar(17) NOT NULL REFERENCES orderStatus(orderStatusName),
	"dateOrdered" DATE NOT NULL,
	"shippingAddressId" integer NOT NULL REFERENCES shippingAddress(shippingAddressId),
	"totalPrice" FLOAT NOT NULL
) WITH (
  OIDS=FALSE
);

CREATE TABLE "orderStatus" (
  "orderStatusId" serial PRIMARY KEY,
  "orderStatusName" varchar(17)
) WITH (
  OIDS = FALSE
;

CREATE TABLE "user" (
	"userId" serial PRIMARY KEY NOT NULL,
	"username" varchar(16) NOT NULL UNIQUE,
	"password" varchar(32) NOT NULL,
	"userInfo" integer NOT NULL REFERENCES userInfo(userInfoId),
	"shippingAddress" integer NOT NULL REFERENCES shippingAddress(shippingAddressId),
	"orderId" integer NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "userInfo" (
	"userInfoId" serial PRIMARY KEY NOT NULL,
	"name" varchar(32) NOT NULL,
	"surname" varchar(32) NOT NULL,
	"birthdate" DATE NOT NULL,
	"gender" varchar(17) NOT NULL REFERENCES gender(gender),
	"email" varchar(32) NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "gender" (
	"genderId" integer NOT NULL,
	"gender" varchar(16) NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "shippingAddress" (
	"shippingAddressId" integer NOT NULL PRIMARY KEY,
	"name" varchar(32) NOT NULL,
	"country" varchar(32) NOT NULL,
	"city" varchar(32) NOT NULL,
	"street" varchar(32) NOT NULL,
	"telephoneNumber" varchar(32) NOT NULL
) WITH (
  OIDS=FALSE
);

