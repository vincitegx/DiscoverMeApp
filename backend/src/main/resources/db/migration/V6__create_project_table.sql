CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    code VARCHAR(255) NOT NULL UNIQUE,
    user_id BIGINT REFERENCES users(id),
    calender_id BIGINT REFERENCES calender(id),
    title VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    social_id BIGINT REFERENCES socials(id),
    content_uri VARCHAR(255),
    support_count BIGINT DEFAULT 0,
    reaction_count BIGINT DEFAULT 0,
    video_id VARCHAR(255),
    post_id VARCHAR(255),
    publish_date timestamp
);
