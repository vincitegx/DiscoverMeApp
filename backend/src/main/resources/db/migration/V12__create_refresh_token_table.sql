CREATE TABLE refresh_token (
    id SERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL UNIQUE,
    user_id BIGINT REFERENCES users(id),
    created_at           timestamp  NOT NULL  ,
    expiry_at           timestamp  NOT NULL
    );
