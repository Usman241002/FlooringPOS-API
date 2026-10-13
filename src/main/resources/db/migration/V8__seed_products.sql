INSERT INTO products (category_id,
                      brand_id,
                      name,
                      selling_price_m2,
                      cost_price_m2)
VALUES ((SELECT id FROM categories WHERE name = 'Carpet'),
        (SELECT id FROM brands WHERE name = 'Kellers'),
        'Elevate', 7.90, 3.49),
       ((SELECT id FROM categories WHERE name = 'Carpet'),
        (SELECT id FROM brands WHERE name = 'Kellers'),
        'Maestro', 11.50, 7.15);

INSERT INTO products (category_id,
                      brand_id,
                      name,
                      selling_price_m2,
                      cost_price_m2)
VALUES ((SELECT id FROM categories WHERE name = 'Lino'),
        (SELECT id FROM brands WHERE name = 'F&C'),
        'New Pandora', 16.49, 8.55),
       ((SELECT id FROM categories WHERE name = 'Lino'),
        (SELECT id FROM brands WHERE name = 'F&C'),
        'Kipling', 14.00, 6.90);

