/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE TABLE project_socials (
    project_id BIGINT REFERENCES project(id),
    socials_id BIGINT REFERENCES socials(id),
    PRIMARY KEY (project_id, socials_id)
);
