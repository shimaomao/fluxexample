--
-- Initial Load for the test database
--

CREATE TABLE PERSON (
  id        BIGINT PRIMARY KEY AUTO_INCREMENT,
  firstname VARCHAR(255) NOT NULL,
  lastname  VARCHAR(255) NOT NULL,
  alias     VARCHAR(100),
  birthsday TIMESTAMP,
  email     VARCHAR(255) NOT NULL,
  created   TIMESTAMP    NOT NULL,
  updated   TIMESTAMP
);


INSERT INTO PERSON (firstname, lastname, alias, email, created)
VALUES ('Hans', 'Muster', 'Test1', 'hans.muster@test.com', now());

INSERT INTO PERSON (firstname, lastname, alias, email, created)
VALUES ('Peter', 'Meier', 'Test2', 'peter.meier@test.com', now());

INSERT INTO PERSON (firstname, lastname, alias, email, created)
VALUES ('Robert', 'Wellinger', 'robwell', 'robert.wellinger@gmail.com', now());

INSERT INTO PERSON (firstname, lastname, alias, email, created)
VALUES ('Eupratisa', 'Wellinger', 'tess', 'tess@test.com', now());
