/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    calender_id BIGINT REFERENCES calender(id),
    song_title VARCHAR(255) NOT NULL UNIQUE,
    artwork_uri VARCHAR(255) NOT NULL UNIQUE,
    song_uri VARCHAR(255) NOT NULL UNIQUE,
    status VARCHAR(50),
    vote_count BIGINT DEFAULT 0,
    support_count BIGINT DEFAULT 0,
    voted BOOLEAN DEFAULT FALSE,
    supported BOOLEAN DEFAULT FALSE
);
