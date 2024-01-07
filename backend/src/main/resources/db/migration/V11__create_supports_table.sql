/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE TABLE support (
    id SERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL REFERENCES project(id),
    user_id BIGINT REFERENCES users(id)
);
