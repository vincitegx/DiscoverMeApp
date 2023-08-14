/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE TABLE project_platform (
    project_id BIGINT REFERENCES project(id),
    platform_id BIGINT REFERENCES promotion_platform(id),
    PRIMARY KEY (project_id, platform_id)
);
