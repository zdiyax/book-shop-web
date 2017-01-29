CREATE TABLE "book" (
	"bookId" serial NOT NULL,
	"isbn" varchar(17) NOT NULL UNIQUE,
	"languageId" varchar(17) NOT NULL UNIQUE,
	"title" varchar(32) NOT NULL,
	"authorId" integer NOT NULL,
	"domainId" integer NOT NULL,
	"publisherId" integer NOT NULL,
	"description" varchar(255),
	"price" FLOAT NOT NULL,
	CONSTRAINT book_pk PRIMARY KEY ("bookId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "language" (
	"languageId" serial NOT NULL,
	"language" varchar(20) NOT NULL UNIQUE,
	CONSTRAINT language_pk PRIMARY KEY ("languageId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "author" (
	"authorId" serial NOT NULL,
	"authorName" varchar(32) NOT NULL,
	CONSTRAINT author_pk PRIMARY KEY ("authorId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "domain" (
	"domainId" serial NOT NULL,
	"domain" varchar(32) NOT NULL,
	CONSTRAINT domain_pk PRIMARY KEY ("domainId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "publisher" (
	"publisherId" serial NOT NULL,
	"publisher" varchar(32) NOT NULL,
	CONSTRAINT publisher_pk PRIMARY KEY ("publisherId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "order" (
	"orderId" serial NOT NULL,
	"orderStatusId" integer NOT NULL,
	"dateOrdered" DATE NOT NULL,
	"shippingAddressId" integer NOT NULL,
	"totalPrice" FLOAT NOT NULL,
	CONSTRAINT order_pk PRIMARY KEY ("orderId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "user" (
	"userId" serial NOT NULL,
	"username" varchar(16) NOT NULL UNIQUE,
	"password" varchar(32) NOT NULL,
	"userInfoId" integer NOT NULL,
	"shippingAddressId" integer NOT NULL,
	"orderId" integer NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY ("userId")
) WITH (
OIDS=FALSE
);



CREATE TABLE "userInfo" (
	"userInfoId" serial NOT NULL,
	"name" varchar(32) NOT NULL,
	"surname" varchar(32) NOT NULL,
	"birthdate" DATE NOT NULL,
	"genderId" integer NOT NULL,
	"email" varchar(32) NOT NULL,
	CONSTRAINT userInfo_pk PRIMARY KEY ("userInfoId")
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
	"shippingAddressId" integer NOT NULL,
	"name" varchar(32) NOT NULL,
	"country" varchar(32) NOT NULL,
	"city" varchar(32) NOT NULL,
	"street" varchar(32) NOT NULL,
	"telephoneNumber" varchar(32) NOT NULL,
	CONSTRAINT shippingAddress_pk PRIMARY KEY ("shippingAddressId")
) WITH (
OIDS=FALSE
);
