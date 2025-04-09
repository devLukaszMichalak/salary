CREATE TABLE agencies
(
    id               INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    name             TEXT UNIQUE NOT NULL,
    creation_date    TEXT        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_date TEXT        NOT NULL,
    CHECK (TRIM(name) != ''),
    CHECK (TRIM(creation_date) != ''),
    CHECK (TRIM(last_update_date) != '')
) strict;

CREATE TABLE positions
(
    id               INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    title            TEXT UNIQUE NOT NULL,
    creation_date    TEXT        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_date TEXT        NOT NULL,
    CHECK (TRIM(title) != ''),
    CHECK (TRIM(creation_date) != ''),
    CHECK (TRIM(last_update_date) != '')

) strict;

CREATE TABLE employees
(
    id               INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    name             TEXT    NOT NULL,
    position_id      INTEGER NOT NULL,
    agency_id        INTEGER NOT NULL,
    creation_date    TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_date TEXT    NOT NULL,
    CHECK (TRIM(name) != ''),
    CHECK (TRIM(creation_date) != ''),
    CHECK (TRIM(last_update_date) != ''),
    FOREIGN KEY (position_id) REFERENCES positions (id),
    FOREIGN KEY (agency_id) REFERENCES agencies (id)
) strict;


CREATE TABLE salaries
(
    id               INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    year             INTEGER NOT NULL,
    yearly_gross_pay REAL    NOT NULL,
    employee_id      INTEGER NOT NULL,
    creation_date    TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_update_date TEXT    NOT NULL,
    CHECK (yearly_gross_pay > 0),
    CHECK (TRIM(creation_date) != ''),
    CHECK (TRIM(last_update_date) != ''),
    FOREIGN KEY (employee_id) REFERENCES employees (id),
    UNIQUE (year, employee_id)
) strict;
