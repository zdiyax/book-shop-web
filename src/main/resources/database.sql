CREATE TABLE `books` (
  "id" int NOT NULL AUTO_INCREMENT,
  "isbn" varchar(17),
  "language" varchar(64),
  "title" varchar(64),
  "description" varchar(256),
  "price" float,
  PRIMARY KEY (`id`)
);

CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `isbn` varchar(17),
  `language` varchar(64),
  `title` varchar(64),
  `description` varchar(256),
  `price` float,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS books (
  id int NOT NULL AUTO_INCREMENT,
  isbn varchar(17),
  language varchar(64),
  title varchar(64),
  description varchar(256),
  price float,
  PRIMARY KEY (id)
);
INSERT INTO books (isbn, language, title, description, price) VALUES (isbn,  language, title, description, 1000);
insert into books ("isbn", "language", "title", "description", "price") values (isbn, language, title, description, price);