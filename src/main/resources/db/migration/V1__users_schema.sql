CREATE TABLE users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (username)
);

CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username
  on authorities (username,authority);

-- User user/pass
INSERT INTO users (username, password, enabled)
  values ('user',
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
    1);

INSERT INTO users (username, password, enabled)
  values ('rama',
    '$2a$10$7DcYOVBn8vc05aubyzsuP.IC.vhuA/3aDj2Mo/RqJQbNIPpb/crau',
    1);


INSERT INTO authorities (username, authority)
  values ('user', 'ROLE_USER');

INSERT INTO authorities (username, authority)
  values ('rama', 'ROLE_ADMIN');

