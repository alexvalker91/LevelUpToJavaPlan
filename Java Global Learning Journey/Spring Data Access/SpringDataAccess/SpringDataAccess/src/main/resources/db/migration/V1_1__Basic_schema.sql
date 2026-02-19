CREATE TABLE event_db (
    id SERIAL PRIMARY KEY,
    event_title VARCHAR(255) NOT NULL,
    event_date TIMESTAMP NOT NULL,
    event_ticket_price INTEGER
);

CREATE TABLE user_db (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) NOT NULL
);