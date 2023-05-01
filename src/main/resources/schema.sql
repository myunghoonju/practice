# noinspection SqlNoDataSourceInspectionForFile
;
drop table if exists example.users;
drop table if exists example.country;
drop table if exists example.BOOK;

create table example.USERS (
  ID int not null AUTO_INCREMENT,
  NAME varchar(100) not null,
  STATUS int,
  PRIMARY KEY ( ID )
);

CREATE TABLE example.country (
  id   INTEGER      NOT NULL AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL,
  PRIMARY KEY (id)
);

create table example.BOOK(
  ID int not null AUTO_INCREMENT,
  NAME varchar(128) not null,
  PRIMARY KEY ( ID )
);