/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL UNIQUE,
    user_id BIGINT REFERENCES users(id),
    calender_id BIGINT REFERENCES calender(id),
    song_title VARCHAR(255) NOT NULL,
    song_uri VARCHAR(255) NOT NULL,
    social_id BIGINT REFERENCES socials(id),
    content_uri VARCHAR(255) NOT NULL,
    support_count BIGINT DEFAULT 0
);
