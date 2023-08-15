/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE  TABLE users ( 
	id                   bigserial PRIMARY KEY NOT NULL  ,
	stage_name           varchar(255)  NOT NULL ,
	phone_number         varchar(255)  NOT NULL ,
	password             varchar(255)  NOT NULL,
	role             varchar(255)  NOT NULL,
	created_at           timestamp  NOT NULL  ,
	non_locked           boolean  NOT NULL ,
	enabled              boolean  NOT NULL
 );
