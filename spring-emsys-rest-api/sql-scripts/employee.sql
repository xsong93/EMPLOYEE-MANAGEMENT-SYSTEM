CREATE DATABASE  IF NOT EXISTS web_employee_tracker;
USE web_employee_tracker;

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(45) DEFAULT NULL,
  last_name varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);

LOCK TABLES employee WRITE;

INSERT INTO employee VALUES 
	(1,'Joe','Green','joe.green@one.com'),
	(2,'Hob','Hawk','Hob.Hawk@one.com'),
	(3,'John','Jacob','jjacob@one.com'),
	(4,'Tom','Snow','snow@one.com'),
	(5,'Pop','White','pop.white@one.com');

UNLOCK TABLES;