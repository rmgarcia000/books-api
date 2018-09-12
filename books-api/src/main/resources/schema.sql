DROP TABLE IF EXISTS tbl_book;

CREATE TABLE tbl_book (
	id INT NOT NULL AUTO_INCREMENT,
	tittle varchar(80) NOT NULL,
	description varchar(800) NOT NULL,
	isbn varchar(13) NOT NULL UNIQUE,
	languagem text NOT NULL,
	PRIMARY KEY (id)
);