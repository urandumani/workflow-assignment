INSERT INTO roles (id, name)
VALUES ('1', 'Admin'),
       ('2', 'Analytics'),
       ('3', 'Analysts'),
       ('4', 'Category Managers');

INSERT INTO users (id, username, email, password)
VALUES ('dafbf409-62b0-46a0-9e4d-475f0757141b', 'uran', 'uran@assignment.com',
        '$2a$10$UQsQUBs5f1wPl5M2Fa00ienEXKIJ8sPeeYiTzfO6mfmwhfp.q442S'); -- unencrypted value is 'password'. I used bcrypt for one way encryption

INSERT INTO users (id, username, email, password)
VALUES ('aa434462-0b69-481a-8c6a-fe4bc8136879', 'sinan', 'sinan@dobreva.com',
        '$2a$10$UQsQUBs5f1wPl5M2Fa00ienEXKIJ8sPeeYiTzfO6mfmwhfp.q442S'); -- same password just for testing purposes

INSERT INTO users (id, username, email, password)
VALUES ('307df63a-c4dd-4769-89ab-20cabdf2cc77', 'nuredin', 'nuredin@vokrri.com',
        '$2a$10$UQsQUBs5f1wPl5M2Fa00ienEXKIJ8sPeeYiTzfO6mfmwhfp.q442S'); -- same password just for testing purposes

INSERT INTO users_roles (user_id, role_id)
VALUES ('dafbf409-62b0-46a0-9e4d-475f0757141b', '1'),
       ('aa434462-0b69-481a-8c6a-fe4bc8136879', '3'),
       ('307df63a-c4dd-4769-89ab-20cabdf2cc77', '4');


-- instead of using UUID, I am inserting more readable strings as ID to make it simpler, for testing only
INSERT INTO workflows (id, name, description, created_at)
VALUES ('w1', 'Example Workflow 1', 'Multiple task and nothing else', NOW()),
       ('w2', 'Example Workflow 2', 'Two notes, one task and three attachments', NOW()),
       ('w3', 'Example Workflow 3', 'Five attachments only', NOW()),
       ('w4', 'Example Workflow 4', 'Two attachments and two task', NOW());

INSERT INTO nodes (id, type, status, node_order, workflow_id)
VALUES ('n1', 'INIT', 'PENDING', 1, 'w1'),
       ('n2', 'CONDITION', 'PENDING', 2, 'w1'),
       ('n3', 'MODIFY_MESSAGE', 'PENDING', 3, 'w1'),
       ('n4', 'INIT', 'PENDING', 1, 'w2'),
       ('n5', 'STORE_MESSAGE', 'PENDING', 2, 'w2'),
       ('n6', 'INIT', 'PENDING', 1,'w3'),
       ('n7', 'INIT', 'PENDING', 1,'w4'),
       ('n8', 'CONDITION', 'PENDING', 2,'w4');


-- Multiple task and nothing else
INSERT INTO workflow_contents (id, workflow_id, type, title, assignee_id, reporter_id, content_order, time, priority, url, text)
VALUES
    ('wc1', 'w1', 'TASK', 'Task 1', 'dafbf409-62b0-46a0-9e4d-475f0757141b', 'aa434462-0b69-481a-8c6a-fe4bc8136879', 1, NOW(), 'HIGH', NULL, NULL),
    ('wc2', 'w1', 'TASK', 'Task 2', '307df63a-c4dd-4769-89ab-20cabdf2cc77', 'dafbf409-62b0-46a0-9e4d-475f0757141b', 2, NOW(), 'MEDIUM', NULL, NULL),
    ('wc3', 'w1', 'TASK', 'Task 3', 'aa434462-0b69-481a-8c6a-fe4bc8136879', '307df63a-c4dd-4769-89ab-20cabdf2cc77', 3, NOW(), 'LOW', NULL, NULL);

-- Two notes, one task and three attachments
INSERT INTO workflow_contents (id, workflow_id, type, title, assignee_id, reporter_id, content_order, time, priority, url, text)
VALUES
    ('wc4', 'w2', 'NOTE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Note for w2'),
    ('wc5', 'w2', 'NOTE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Note for w2'),
    ('wc6', 'w2', 'TASK', 'Task for w2', '307df63a-c4dd-4769-89ab-20cabdf2cc77', 'dafbf409-62b0-46a0-9e4d-475f0757141b', 1, NOW(), 'MEDIUM', NULL, NULL),
    ('wc7', 'w2', 'ATTACHMENT', NULL, NULL, NULL, NULL, NULL, NULL, 'https://example.com/a1.pdf', NULL),
    ('wc8', 'w2', 'ATTACHMENT', NULL, NULL, NULL, NULL, NULL, NULL, 'https://example.com/a2.pdf', NULL),
    ('wc9', 'w2', 'ATTACHMENT', NULL, NULL, NULL, NULL, NULL, NULL, 'https://example.com/a3.pdf', NULL);

-- Five attachments only
INSERT INTO workflow_contents (id, workflow_id, type, title, assignee_id, reporter_id, content_order, time, priority, url, text)
VALUES
    ('wc10', 'w3', 'ATTACHMENT', NULL, NULL, NULL, NULL, NULL, NULL, 'https://example.com/w3-a1.pdf', NULL),
    ('wc11', 'w3', 'ATTACHMENT', NULL, NULL, NULL, NULL, NULL, NULL, 'https://example.com/w3-a2.pdf', NULL),
    ('wc12', 'w3', 'ATTACHMENT', NULL, NULL, NULL, NULL, NULL, NULL, 'https://example.com/w3-a3.pdf', NULL),
    ('wc13', 'w3', 'ATTACHMENT', NULL, NULL, NULL, NULL, NULL, NULL, 'https://example.com/w3-a4.pdf', NULL),
    ('wc14', 'w3', 'ATTACHMENT', NULL, NULL, NULL, NULL, NULL, NULL, 'https://example.com/w3-a5.pdf', NULL);

-- Two attachments and two task
INSERT INTO workflow_contents (id, workflow_id, type, title, assignee_id, reporter_id, content_order, time, priority, url, text)
VALUES
    ('wc15', 'w4', 'TASK', 'Task A', 'dafbf409-62b0-46a0-9e4d-475f0757141b', 'aa434462-0b69-481a-8c6a-fe4bc8136879', 1, NOW(), 'LOW', NULL, NULL),
    ('wc16', 'w4', 'TASK', 'Task B', 'aa434462-0b69-481a-8c6a-fe4bc8136879', 'dafbf409-62b0-46a0-9e4d-475f0757141b', 2, NOW(), 'HIGH', NULL, NULL),
    ('wc17', 'w4', 'ATTACHMENT', NULL, NULL, NULL, NULL, NULL, NULL, 'https://example.com/w4-a1.pdf', NULL),
    ('wc18', 'w4', 'ATTACHMENT', NULL, NULL, NULL, NULL, NULL, NULL, 'https://example.com/w4-a2.pdf', NULL);


-- assuming admins and analytics can read/write everything, I am inserting read roles only for analysts and category managers
INSERT INTO workflows_read_roles (workflow_id, role_id)
VALUES ('w1', '3'),
       ('w2', '4'),
       ('w3', '3'),
       ('w4', '4');
