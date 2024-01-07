/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE  TABLE calender (
	id                   bigserial PRIMARY KEY NOT NULL  ,
	start_date           timestamp  NOT NULL,
	end_date           timestamp  NOT NULL
 );
