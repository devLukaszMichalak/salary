CREATE TABLE users
(
    id               INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    email            TEXT    NOT NULL UNIQUE,
    password         TEXT    NOT NULL,
    creation_date    TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_date TEXT    NOT NULL,
    CHECK (TRIM(creation_date) != ''),
    CHECK (TRIM(last_update_date) != '')
) strict;

CREATE INDEX idx_users_email ON users (email);