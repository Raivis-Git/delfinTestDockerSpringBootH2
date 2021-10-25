DROP TABLE IF EXISTS r_client;

CREATE TABLE r_client (
  r_client_id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  personal_no VARCHAR(250) NOT NULL
);