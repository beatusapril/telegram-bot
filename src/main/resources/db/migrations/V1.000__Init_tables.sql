CREATE  SCHEMA IF NOT EXISTS app;
CREATE TABLE app.users (
 id MEDIUMINT NOT NULL AUTO_INCREMENT,
 login VARCHAR(20) NOT NULL,
 password VARCHAR(40),
  PRIMARY KEY (id)
);