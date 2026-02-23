CREATE TABLE user_account_db (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES user_db(id),
    user_amount INTEGER
);