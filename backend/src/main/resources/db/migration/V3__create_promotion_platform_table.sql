/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE  TABLE promotion_platform (
	id                   bigserial PRIMARY KEY NOT NULL  ,
    name           varchar(255)  NOT NULL UNIQUE
 );
