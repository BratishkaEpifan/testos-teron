CREATE TABLE IF NOT EXISTS task (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    task_status VARCHAR(11),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);