CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `isbn` varchar(17),
  `language` varchar(64),
  `title` varchar(64),
  `description` varchar(256),
  `price` float,
  PRIMARY KEY (`id`);
)
