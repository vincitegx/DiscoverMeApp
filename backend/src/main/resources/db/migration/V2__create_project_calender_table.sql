/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE  TABLE project_calender (
	id                   bigserial PRIMARY KEY NOT NULL  ,
	name           varchar(255)  NOT NULL,
	status           varchar(255)  NOT NULL,
	CONSTRAINT valid_status CHECK (status IN ('SUBMISSION', 'VOTING', 'SUPPORT'))
 );
