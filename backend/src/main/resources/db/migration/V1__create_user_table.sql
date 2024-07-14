CREATE  TABLE users (
	id                   bigserial PRIMARY KEY NOT NULL  ,
	user_name           varchar(255)  NOT NULL ,
	email         varchar(255)  NOT NULL ,
	password             varchar(255)  NOT NULL,
	role             varchar(255)  NOT NULL,
	created_at           timestamp  NOT NULL  ,
	non_locked           boolean  NOT NULL ,
	enabled              boolean  NOT NULL
 );
