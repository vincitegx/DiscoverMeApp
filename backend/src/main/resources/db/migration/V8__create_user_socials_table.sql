/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE TABLE user_socials (
    id  bigserial PRIMARY KEY NOT NULL,
    user_id BIGINT REFERENCES users(id),
    social_id BIGINT REFERENCES socials(id),
    social_user_name VARCHAR(255) NOT NULL,
    social_user_id VARCHAR(255) NOT NULL,
    access_token VARCHAR(255) NOT NULL
);
