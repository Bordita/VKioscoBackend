
INSERT INTO product (name, price, stock, descripcion, imagen) 
SELECT 'Coca cola lata', 20.0, 3, 'Bebida gaseosa cola en lata de 354ml', 'https://example.com/images/coca-cola-lata.jpg' WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Coca cola lata');

INSERT INTO product (name, price, stock, descripcion, imagen) 
SELECT 'Fanta botella', 120.0, 32, 'Bebida gaseosa sabor naranja en botella de 500ml', 'https://example.com/images/fanta-botella.jpg' WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Fanta botella');

INSERT INTO product (name, price, stock, descripcion, imagen) 
SELECT 'monster blanca', 45.5, 24, 'Bebida energética Monster Ultra White', 'https://example.com/images/monster-blanca.jpg' WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'monster blanca');

INSERT INTO product (name, price, stock, descripcion, imagen) 
SELECT 'Red Bull lata', 20.0, 98, 'Bebida energética Red Bull original', 'https://example.com/images/redbull-lata.jpg' WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Red Bull lata');

INSERT INTO beverages (id, volume_in_liters) 
SELECT p.id, 0.354 FROM product p WHERE p.name = 'Coca cola lata' AND NOT EXISTS (SELECT 1 FROM beverages b WHERE b.id = p.id);

INSERT INTO beverages (id, volume_in_liters) 
SELECT p.id, 0.5 FROM product p WHERE p.name = 'Fanta botella' AND NOT EXISTS (SELECT 1 FROM beverages b WHERE b.id = p.id);

INSERT INTO beverages (id, volume_in_liters) 
SELECT p.id, 0.473 FROM product p WHERE p.name = 'monster blanca' AND NOT EXISTS (SELECT 1 FROM beverages b WHERE b.id = p.id);

INSERT INTO beverages (id, volume_in_liters) 
SELECT p.id, 0.25 FROM product p WHERE p.name = 'Red Bull lata' AND NOT EXISTS (SELECT 1 FROM beverages b WHERE b.id = p.id);

INSERT INTO product (name, price, stock, descripcion, imagen) 
SELECT 'Barra de cereal', 12.3, 12, 'Barra de cereal con frutas y avena', 'https://example.com/images/barra-cereal.jpg' WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Barra de cereal');

INSERT INTO product (name, price, stock, descripcion, imagen) 
SELECT 'Alfajor oreo', 25.9, 5, 'Alfajor relleno de dulce de leche con galletitas Oreo', 'https://example.com/images/alfajor-oreo.jpg' WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Alfajor oreo');

INSERT INTO product (name, price, stock, descripcion, imagen) 
SELECT 'Chicles beldent', 7.5, 40, 'Chicles sabor menta de larga duración', 'https://example.com/images/chicles-beldent.jpg' WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Chicles beldent');

INSERT INTO product (name, price, stock, descripcion, imagen) 
SELECT 'Helado de agua', 40.0, 24, 'Helado de agua saborizado multicolor', 'https://example.com/images/helado-agua.jpg' WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Helado de agua');

INSERT INTO foods (id, expiration_date) 
SELECT p.id, '2025-08-12' FROM product p WHERE p.name = 'Barra de cereal' AND NOT EXISTS (SELECT 1 FROM foods f WHERE f.id = p.id);

INSERT INTO foods (id, expiration_date) 
SELECT p.id, '2025-09-12' FROM product p WHERE p.name = 'Alfajor oreo' AND NOT EXISTS (SELECT 1 FROM foods f WHERE f.id = p.id);

INSERT INTO foods (id, expiration_date) 
SELECT p.id, '2026-08-12' FROM product p WHERE p.name = 'Chicles beldent' AND NOT EXISTS (SELECT 1 FROM foods f WHERE f.id = p.id);

INSERT INTO foods (id, expiration_date) 
SELECT p.id, '2025-08-30' FROM product p WHERE p.name = 'Helado de agua' AND NOT EXISTS (SELECT 1 FROM foods f WHERE f.id = p.id);
