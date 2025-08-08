CREATE TABLE roles
(
    id   VARCHAR(36) PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users
(
    id       VARCHAR(36)  PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    email    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE users_roles
(
    user_id VARCHAR(255),
    role_id VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE workflows
(
    id          VARCHAR(36) PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    created_at  TIMESTAMP NULL
);

CREATE TABLE workflows_read_roles
(
    workflow_id VARCHAR(36) NOT NULL,
    role_id     VARCHAR(36) NOT NULL,
    PRIMARY KEY (workflow_id, role_id),
    CONSTRAINT fk_workflow_read_roles_workflow FOREIGN KEY (workflow_id) REFERENCES workflows (id) ON DELETE CASCADE,
    CONSTRAINT fk_workflow_read_roles_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

CREATE TABLE nodes
(
    id          VARCHAR(36) PRIMARY KEY,
    type        VARCHAR(20) NOT NULL,
    status      VARCHAR(20) NOT NULL,
    node_order  INT NOT NULL,
    workflow_id VARCHAR(36) NOT NULL,
    CONSTRAINT fk_nodes_workflow FOREIGN KEY (workflow_id) REFERENCES workflows (id) ON DELETE CASCADE
);

CREATE TABLE workflow_contents
(
    id            VARCHAR(36) PRIMARY KEY,
    workflow_id   VARCHAR(36) NOT NULL,
    type          VARCHAR(20) NOT NULL,
    title         VARCHAR(255),
    assignee_id   VARCHAR(36),
    reporter_id   VARCHAR(36),
    content_order INT,
    time          TIMESTAMP NULL,
    priority      VARCHAR(20),
    url           VARCHAR(500),
    text          TEXT,
    CONSTRAINT fk_workflow FOREIGN KEY (workflow_id) REFERENCES workflows (id) ON DELETE CASCADE
);

