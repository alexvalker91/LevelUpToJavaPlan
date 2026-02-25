CREATE TABLE IF NOT EXISTS subscription_table (
    id SERIAL PRIMARY KEY,
    subscription_user_id INT NOT NULL,
    subscription_start_date DATE
);