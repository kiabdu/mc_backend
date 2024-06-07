DROP TABLE RECIPES;

CREATE SEQUENCE RECIPES_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS recipes (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ingredients TEXT NOT NULL,
    instructions TEXT NOT NULL,
    estimated_time TEXT,
    picture VARCHAR(255)
    //review INTEGER CHECK (review >= 1 AND review <= 5),
    //category VARCHAR(50) CHECK (category IN ('Vegetarisch', 'Vegan', 'Lamm',
    //                                         'Huhn', 'Rind', 'Frühstück',
    //                                         'Mittagessen', 'Abendessen', 'Nachtisch'))
);
