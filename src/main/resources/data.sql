
INSERT INTO product (name, price, stock) 
SELECT 'Coca cola lata', 20.0, 3 WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Coca cola lata');

INSERT INTO product (name, price, stock) 
SELECT 'Fanta botella', 120.0, 32 WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Fanta botella');

INSERT INTO product (name, price, stock) 
SELECT 'monster blanca', 45.5, 24 WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'monster blanca');

INSERT INTO product (name, price, stock) 
SELECT 'Red Bull lata', 20.0, 98 WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Red Bull lata');

INSERT INTO beverages (id, volume_in_liters) 
SELECT p.id, 0.354 FROM product p WHERE p.name = 'Coca cola lata' AND NOT EXISTS (SELECT 1 FROM beverages b WHERE b.id = p.id);

INSERT INTO beverages (id, volume_in_liters) 
SELECT p.id, 0.5 FROM product p WHERE p.name = 'Fanta botella' AND NOT EXISTS (SELECT 1 FROM beverages b WHERE b.id = p.id);

INSERT INTO beverages (id, volume_in_liters) 
SELECT p.id, 0.473 FROM product p WHERE p.name = 'monster blanca' AND NOT EXISTS (SELECT 1 FROM beverages b WHERE b.id = p.id);

INSERT INTO beverages (id, volume_in_liters) 
SELECT p.id, 0.25 FROM product p WHERE p.name = 'Red Bull lata' AND NOT EXISTS (SELECT 1 FROM beverages b WHERE b.id = p.id);

INSERT INTO product (name, price, stock) 
SELECT 'Barra de cereal', 12.3, 12 WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Barra de cereal');

INSERT INTO product (name, price, stock) 
SELECT 'Alfajor oreo', 25.9, 5 WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Alfajor oreo');

INSERT INTO product (name, price, stock) 
SELECT 'Chicles beldent', 7.5, 40 WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Chicles beldent');

INSERT INTO product (name, price, stock) 
SELECT 'Helado de agua', 40.0, 24 WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Helado de agua');

INSERT INTO foods (id, expiration_date) 
SELECT p.id, '2025-08-12' FROM product p WHERE p.name = 'Barra de cereal' AND NOT EXISTS (SELECT 1 FROM foods f WHERE f.id = p.id);

INSERT INTO foods (id, expiration_date) 
SELECT p.id, '2025-09-12' FROM product p WHERE p.name = 'Alfajor oreo' AND NOT EXISTS (SELECT 1 FROM foods f WHERE f.id = p.id);

INSERT INTO foods (id, expiration_date) 
SELECT p.id, '2026-08-12' FROM product p WHERE p.name = 'Chicles beldent' AND NOT EXISTS (SELECT 1 FROM foods f WHERE f.id = p.id);

INSERT INTO foods (id, expiration_date) 
SELECT p.id, '2025-08-30' FROM product p WHERE p.name = 'Helado de agua' AND NOT EXISTS (SELECT 1 FROM foods f WHERE f.id = p.id);
