CREATE TABLE IF NOT EXISTS books (
  id int NOT NULL AUTO_INCREMENT,
  isbn varchar(17),
  language varchar(64),
  title varchar(64),
  description varchar(256),
  price float,
  PRIMARY KEY (id)
);
INSERT INTO books (isbn, language, title, description, price) VALUES ('isbn',  'language', 'title', 'description', '1000');

-- H2:
INSERT INTO books ("isbn", "language", "title", "description", "price") VALUES ('978-0321356680',  'English', 'Effective Java', 'Some Description', '50');
