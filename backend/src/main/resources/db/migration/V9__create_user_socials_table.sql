/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE TABLE user_socials (
    id  bigserial PRIMARY KEY NOT NULL,
    user_id BIGINT REFERENCES users(id),
    socials_id BIGINT REFERENCES socials(id),
    uri VARCHAR(255) NOT NULL UNIQUE
);