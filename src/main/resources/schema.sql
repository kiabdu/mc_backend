SET SCHEMA 'PUBLIC';

CREATE TABLE IF NOT EXISTS RECIPES (
   id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255),
   ingredients TEXT,
   instructions TEXT,
   estimated_time TEXT,
   picture VARCHAR(255)
);

