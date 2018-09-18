DROP TABLE IF EXISTS tbl_book;

CREATE TABLE tbl_book (
	id INT NOT NULL AUTO_INCREMENT,
	tittle varchar(80) NOT NULL,
	description varchar(2000) NOT NULL,
	isbn varchar(13) NOT NULL,
	language text NOT NULL,
	PRIMARY KEY (id)
);