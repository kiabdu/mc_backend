create table IF NOT EXISTS recipes
(
    name         VARCHAR(10000) not null,
    instructions VARCHAR(10000) not null
);

INSERT INTO recipes(name, instructions) VALUES
    ('Kuchen', 'Mehl, Milch, Salz, Zucker, Backpulver, Kakaopulver'),
    ('Apfelsaft', 'Apfel, Saft'),
    ('Wasser', 'Wasser');

