INSERT INTO agencies (name, last_update_date)
VALUES ('AGRICULTURE', CURRENT_TIMESTAMP);

INSERT INTO positions (title, last_update_date)
VALUES ('ACCOUNTANT II', CURRENT_TIMESTAMP),
       ('ACCOUNTANT', CURRENT_TIMESTAMP),
       ('ACCOUNTING GENERALIST II', CURRENT_TIMESTAMP),
       ('ACCOUNTS SUPERVISOR', CURRENT_TIMESTAMP),
       ('ADMIN OFFICE SUPPORT ASSISTANT', CURRENT_TIMESTAMP),
       ('ADMIN SUPPORT ASSISTANT', CURRENT_TIMESTAMP),
       ('ADMIN SUPPORT PROFESSIONAL', CURRENT_TIMESTAMP);

INSERT INTO employees (name, position_id, agency_id, last_update_date)
VALUES ('KLEINDIENST, ANGELA F', (SELECT id FROM positions WHERE title = 'ACCOUNTANT II'),
        (SELECT id FROM agencies WHERE name = 'AGRICULTURE'), CURRENT_TIMESTAMP),
       ('WALKER, JOE E.', (SELECT id FROM positions WHERE title = 'ACCOUNTANT II'),
        (SELECT id FROM agencies WHERE name = 'AGRICULTURE'), CURRENT_TIMESTAMP),
       ('KLEINDIENST, ANGELA F', (SELECT id FROM positions WHERE title = 'ACCOUNTANT'),
        (SELECT id FROM agencies WHERE name = 'AGRICULTURE'), CURRENT_TIMESTAMP),
       ('WOOD, KAREN M.', (SELECT id FROM positions WHERE title = 'ACCOUNTANT'),
        (SELECT id FROM agencies WHERE name = 'AGRICULTURE'), CURRENT_TIMESTAMP),
       ('ARNOLD, CAROL ANN', (SELECT id FROM positions WHERE title = 'ACCOUNTING GENERALIST II'),
        (SELECT id FROM agencies WHERE name = 'AGRICULTURE'), CURRENT_TIMESTAMP);

INSERT INTO salaries (year, yearly_gross_pay, employee_id, last_update_date)
VALUES (2020, 23769.77, (SELECT id
                         FROM employees
                         WHERE name = 'KLEINDIENST, ANGELA F'
                           AND position_id = (SELECT id FROM positions WHERE title = 'ACCOUNTANT II')
                           AND agency_id = (SELECT id FROM agencies WHERE name = 'AGRICULTURE')), CURRENT_TIMESTAMP),
       (2020, 29087.84, (SELECT id
                         FROM employees
                         WHERE name = 'WALKER, JOE E.'
                           AND position_id = (SELECT id FROM positions WHERE title = 'ACCOUNTANT II')
                           AND agency_id = (SELECT id FROM agencies WHERE name = 'AGRICULTURE')), CURRENT_TIMESTAMP),
       (2020, 20158.05, (SELECT id
                         FROM employees
                         WHERE name = 'KLEINDIENST, ANGELA F'
                           AND position_id = (SELECT id FROM positions WHERE title = 'ACCOUNTANT')
                           AND agency_id = (SELECT id FROM agencies WHERE name = 'AGRICULTURE')), CURRENT_TIMESTAMP);

