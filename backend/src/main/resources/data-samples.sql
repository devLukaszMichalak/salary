INSERT OR IGNORE INTO agencies (name)
VALUES ('AGRICULTURE');

INSERT OR IGNORE INTO positions (title)
VALUES ('ACCOUNTANT II'),
       ('ACCOUNTANT'),
       ('ACCOUNTING GENERALIST II'),
       ('ACCOUNTS SUPERVISOR'),
       ('ADMIN OFFICE SUPPORT ASSISTANT'),
       ('ADMIN SUPPORT ASSISTANT'),
       ('ADMIN SUPPORT PROFESSIONAL');

INSERT OR IGNORE INTO employees (name, position_id, agency_id)
VALUES ('KLEINDIENST, ANGELA F', (SELECT id FROM positions WHERE title = 'ACCOUNTANT II'),
        (SELECT id FROM agencies WHERE name = 'AGRICULTURE')),
       ('WALKER, JOE E.', (SELECT id FROM positions WHERE title = 'ACCOUNTANT II'),
        (SELECT id FROM agencies WHERE name = 'AGRICULTURE')),
       ('KLEINDIENST, ANGELA F', (SELECT id FROM positions WHERE title = 'ACCOUNTANT'),
        (SELECT id FROM agencies WHERE name = 'AGRICULTURE')),
       ('WOOD, KAREN M.', (SELECT id FROM positions WHERE title = 'ACCOUNTANT'),
        (SELECT id FROM agencies WHERE name = 'AGRICULTURE')),
       ('ARNOLD, CAROL ANN', (SELECT id FROM positions WHERE title = 'ACCOUNTING GENERALIST II'),
        (SELECT id FROM agencies WHERE name = 'AGRICULTURE'));

INSERT OR IGNORE INTO salaries (year, yearly_gross_pay, employee_id)
VALUES (2020, 23769.77, (SELECT id
                         FROM employees
                         WHERE name = 'KLEINDIENST, ANGELA F'
                           AND position_id = (SELECT id FROM positions WHERE title = 'ACCOUNTANT II')
                           AND agency_id = (SELECT id FROM agencies WHERE name = 'AGRICULTURE'))),
       (2020, 29087.84, (SELECT id
                         FROM employees
                         WHERE name = 'WALKER, JOE E.'
                           AND position_id = (SELECT id FROM positions WHERE title = 'ACCOUNTANT II')
                           AND agency_id = (SELECT id FROM agencies WHERE name = 'AGRICULTURE'))),
       (2020, 20158.05, (SELECT id
                         FROM employees
                         WHERE name = 'KLEINDIENST, ANGELA F'
                           AND position_id = (SELECT id FROM positions WHERE title = 'ACCOUNTANT')
                           AND agency_id = (SELECT id FROM agencies WHERE name = 'AGRICULTURE')));

