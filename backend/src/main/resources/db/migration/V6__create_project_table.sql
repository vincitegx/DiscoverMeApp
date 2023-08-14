/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    project_tag_id BIGINT REFERENCES project_tag(id),
    song_title VARCHAR(255) NOT NULL UNIQUE,
    artwork_uri VARCHAR(255) NOT NULL UNIQUE,
    song_uri VARCHAR(255) NOT NULL UNIQUE,
    status VARCHAR(50),
    CONSTRAINT valid_status CHECK (status IN ('APPROVED', 'PENDING', 'REJECTED'))
);
