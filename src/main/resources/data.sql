DROP TABLE RECIPES;

CREATE TABLE IF NOT EXISTS recipes (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ingredients TEXT NOT NULL,
    instructions TEXT NOT NULL,
    estimated_time INTERVAL HOUR TO MINUTE,
    picture VARCHAR(255),
    review INTEGER CHECK (review >= 1 AND review <= 5),
    category VARCHAR(50) CHECK (category IN ('Vegetarisch', 'Vegan', 'Lamm',
                                             'Huhn', 'Rind', 'Frühstück',
                                             'Mittagessen', 'Abendessen', 'Nachtisch'))
);


INSERT INTO recipes(name, instructions) VALUES
    ('Salz', 'Salz'),
    ('Salzkuchen', 'Sehr viel Salz einsalzen');
    /*
    ('Kuchen', 'Mehl, Milch, Salz, Zucker, Backpulver, Kakaopulver'),
    ('Apfelsaft', 'Apfel, Saft'),
    ('Wasser', 'Wasser');
    */
