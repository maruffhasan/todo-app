DROP TABLE IF EXISTS users;
CREATE TABLE users (
   id       SERIAL PRIMARY KEY,
   username VARCHAR(30) UNIQUE NOT NULL,
   password VARCHAR(250) NOT NULL,
   name     VARCHAR(30),
   role     VARCHAR
);

DROP TABLE IF EXISTS todo;
CREATE TABLE todo (
  id          SERIAL PRIMARY KEY,
  user_id     INT,
  description VARCHAR(250),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
