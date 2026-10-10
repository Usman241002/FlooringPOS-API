INSERT INTO categories (name)
VALUES ('Carpet'),
       ('Laminate'),
       ('Vinyl/Linor');

INSERT INTO brands (name)
VALUES ('Kellers'),
       ('F&C'),
       ('MAK');

-- Fitting rules: carpet and vinyl/linor
INSERT INTO fitting_rules (category_id,
                           code,
                           name,
                           fitting_price_m2,
                           minimum_fitting_charge)
VALUES ((SELECT id FROM categories WHERE name = 'Carpet'),
        'STANDARD',
        'Standard carpet fitting',
        2.50,
        35.00),
       ((SELECT id FROM categories WHERE name = 'Vinyl/Linor'),
        'STANDARD',
        'Standard vinyl/linor fitting',
        2.50,
        35.00);

-- Carpet products
INSERT INTO products (category_id,
                      brand_id,
                      fitting_rule_id,
                      name,
                      selling_price_m2,
                      cost_price_m2)
VALUES ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Elevate', 7.90, 3.49),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Maestro', 11.50, 7.15),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Maxima', 16.75, 9.24),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Aries', 15.90, 9.86),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Nobility', 19.95, 12.09),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Texture', 19.90, 12.33),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Sumtuous', 22.00, 13.56),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Euphora', 23.00, 14.18),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Rostico Supreme', 23.00, 14.80),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Allure', 23.00, 14.80),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Seduction', 27.00, 16.99),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Envy', 28.00, 17.27),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Opulance', 30.00, 19.73),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Helios', 33.00, 20.97),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'Kellers'), (SELECT id
                                                                                                            FROM fitting_rules
                                                                                                            WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                              AND code = 'STANDARD'),
        'Escot', 44.95, 25.00),

       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Infinity Twist', 7.00, 3.60),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Kent', 10.00, 5.20),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Oblix', 10.00, 5.75),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'New Kingston', 11.50, 6.30),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Chevron', 12.50, 6.90),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Supreme Luxury', 13.00, 7.30),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Rochester', 14.00, 8.35),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Carlisle', 14.95, 9.20),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Ludlow', 14.95, 9.20),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Gold Whisper', 16.95, 9.80),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Soft Supreme', 15.50, 10.05),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Eternal Rose', 17.70, 10.65),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Banthra', 18.00, 10.95),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Capri', 18.00, 11.50),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Aura', 16.95, 11.50),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Pure Charisma', 24.00, 14.00),
       ((SELECT id FROM categories WHERE name = 'Carpet'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                        FROM fitting_rules
                                                                                                        WHERE category_id = (SELECT id FROM categories WHERE name = 'Carpet')
                                                                                                          AND code = 'STANDARD'),
        'Patina', 29.95, 19.00);

-- Vinyl/Linor products
INSERT INTO products (category_id,
                      brand_id,
                      fitting_rule_id,
                      name,
                      selling_price_m2,
                      cost_price_m2)
VALUES ((SELECT id FROM categories WHERE name = 'Vinyl/Linor'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                             FROM fitting_rules
                                                                                                             WHERE category_id = (SELECT id FROM categories WHERE name = 'Vinyl/Linor')
                                                                                                               AND code = 'STANDARD'),
        'New Pandora', 16.49, 8.55),
       ((SELECT id FROM categories WHERE name = 'Vinyl/Linor'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                             FROM fitting_rules
                                                                                                             WHERE category_id = (SELECT id FROM categories WHERE name = 'Vinyl/Linor')
                                                                                                               AND code = 'STANDARD'),
        'Kipling', 14.00, 6.90),
       ((SELECT id FROM categories WHERE name = 'Vinyl/Linor'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                             FROM fitting_rules
                                                                                                             WHERE category_id = (SELECT id FROM categories WHERE name = 'Vinyl/Linor')
                                                                                                               AND code = 'STANDARD'),
        'Dalor', 13.00, 7.15),
       ((SELECT id FROM categories WHERE name = 'Vinyl/Linor'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                             FROM fitting_rules
                                                                                                             WHERE category_id = (SELECT id FROM categories WHERE name = 'Vinyl/Linor')
                                                                                                               AND code = 'STANDARD'),
        'Panther', 11.95, 6.30),
       ((SELECT id FROM categories WHERE name = 'Vinyl/Linor'), (SELECT id FROM brands WHERE name = 'F&C'), (SELECT id
                                                                                                             FROM fitting_rules
                                                                                                             WHERE category_id = (SELECT id FROM categories WHERE name = 'Vinyl/Linor')
                                                                                                               AND code = 'STANDARD'),
        'Caspian', 12.90, 7.45),

       ((SELECT id FROM categories WHERE name = 'Vinyl/Linor'), (SELECT id FROM brands WHERE name = 'Kellers'),
        (SELECT id
         FROM fitting_rules
         WHERE category_id = (SELECT id FROM categories WHERE name = 'Vinyl/Linor') AND code = 'STANDARD'), 'Colosus',
        18.90, 11.54),
       ((SELECT id FROM categories WHERE name = 'Vinyl/Linor'), (SELECT id FROM brands WHERE name = 'Kellers'),
        (SELECT id
         FROM fitting_rules
         WHERE category_id = (SELECT id FROM categories WHERE name = 'Vinyl/Linor') AND code = 'STANDARD'), 'Lotus',
        13.50, 6.78),
       ((SELECT id FROM categories WHERE name = 'Vinyl/Linor'), (SELECT id FROM brands WHERE name = 'Kellers'),
        (SELECT id
         FROM fitting_rules
         WHERE category_id = (SELECT id FROM categories WHERE name = 'Vinyl/Linor') AND code = 'STANDARD'), 'Stellar',
        19.95, 11.26),
       ((SELECT id FROM categories WHERE name = 'Vinyl/Linor'), (SELECT id FROM brands WHERE name = 'Kellers'),
        (SELECT id
         FROM fitting_rules
         WHERE category_id = (SELECT id FROM categories WHERE name = 'Vinyl/Linor') AND code = 'STANDARD'), 'Aurora',
        14.45, 7.55);

-- Test customer
INSERT INTO customers (first_name, last_name, phone)
VALUES ('Test', 'Customer', '07123456789');
