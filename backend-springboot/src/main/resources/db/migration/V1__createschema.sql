CREATE DATABASE  IF NOT EXISTS tododb;
USE tododb;


DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id bigint NOT NULL AUTO_INCREMENT,
  username varchar(200) NOT NULL,
  password varchar(64) NOT NULL,
  enabled bit(1) NOT NULL,
  role varchar(255) NOT NULL,
  is_deleted bit(1) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS todo;
CREATE TABLE todo (
  id bigint NOT NULL AUTO_INCREMENT,
  completed bit(1) NOT NULL,
  name varchar(200) NOT NULL,
  user_id bigint NOT NULL,
  is_deleted bit(1) NOT NULL,
  PRIMARY KEY (id),
  KEY FK2ft3dfk1d3uw77pas3xqwymm7 (user_id),
  CONSTRAINT FK2ft3dfk1d3uw77pas3xqwymm7 FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS refresh_token;
CREATE TABLE refresh_token (
  id bigint NOT NULL,
  expiry_date datetime(6) NOT NULL,
  token varchar(255) NOT NULL,
  user_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_r4k4edos30bx9neoq81mdvwph (token),
  KEY FKfgk1klcib7i15utalmcqo7krt (user_id),
  CONSTRAINT FKfgk1klcib7i15utalmcqo7krt FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS hibernate_sequence;
CREATE TABLE hibernate_sequence (
  next_val bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `hibernate_sequence` WRITE;
INSERT INTO `hibernate_sequence` VALUES (0);
UNLOCK TABLES;

