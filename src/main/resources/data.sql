create table IF NOT EXISTS recipes
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(10000) not null,
    instructions VARCHAR(10000) not null
);

INSERT INTO recipes(name, instructions) VALUES
    ('Salz', 'Salz');
    /*
    ('Kuchen', 'Mehl, Milch, Salz, Zucker, Backpulver, Kakaopulver'),
    ('Apfelsaft', 'Apfel, Saft'),
    ('Wasser', 'Wasser');
    */
