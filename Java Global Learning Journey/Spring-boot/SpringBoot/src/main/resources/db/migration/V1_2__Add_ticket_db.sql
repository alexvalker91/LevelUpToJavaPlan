CREATE TYPE ticket_category AS ENUM ('STANDARD', 'PREMIUM', 'BAR');

CREATE TABLE ticket_db (
    id SERIAL PRIMARY KEY,
    event_id INT REFERENCES event_db(id),
    user_id INT REFERENCES user_db(id),
    category ticket_category,
    ticket_place INTEGER
);