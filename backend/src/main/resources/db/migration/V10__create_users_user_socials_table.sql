CREATE TABLE users_user_socials (
    users_id BIGINT REFERENCES users(id),
    user_socials_id BIGINT REFERENCES user_socials(id)
);
