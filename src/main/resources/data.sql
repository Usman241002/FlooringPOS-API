-- ============================================================
-- CATEGORIES
-- ============================================================

INSERT INTO categories (name,
                        minimum_fitting_charge)
VALUES ('Carpet',
        35.00);


-- ============================================================
-- BRANDS
-- ============================================================

INSERT INTO brands (name)
VALUES ('Kellers'),
       ('F&C'),
       ('MAK');


-- ============================================================
-- PRODUCTS
-- ============================================================

INSERT INTO products (category_id,
                      brand_id,
                      name,
                      selling_price_m2,
                      cost_price_m2,
                      fitting_price_m2)
VALUES

-- ============================================================
-- KELLERS
-- ============================================================

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Elevate',
 7.90,
 3.49,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Maestro',
 11.50,
 7.15,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Maxima',
 16.75,
 9.24,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Aries',
 15.90,
 9.86,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Nobility',
 19.95,
 12.09,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Texture',
 19.90,
 12.33,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Sumtuous',
 22.00,
 13.56,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Euphora',
 23.00,
 14.18,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Rostico Supreme',
 23.00,
 14.80,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Allure',
 23.00,
 14.80,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Seduction',
 27.00,
 16.99,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Envy',
 28.00,
 17.27,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Opulance',
 30.00,
 19.73,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Helios',
 33.00,
 20.97,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'Kellers'),
 'Escot',
 44.95,
 25.00,
 2.50),


-- ============================================================
-- F&C
-- ============================================================

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Infinity Twist',
 7.00,
 3.60,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Kent',
 10.00,
 5.20,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Oblix',
 10.00,
 5.75,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'New Kingston',
 11.50,
 6.30,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Chevron',
 12.50,
 6.90,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Supreme Luxury',
 13.00,
 7.30,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Rochester',
 14.00,
 8.35,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Carlisle',
 14.95,
 9.20,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Ludlow',
 14.95,
 9.20,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Gold Whisper',
 16.95,
 9.80,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Soft Supreme',
 15.50,
 10.05,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Eternal Rose',
 17.70,
 10.65,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Banthra',
 18.00,
 10.95,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Capri',
 18.00,
 11.50,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Aura',
 16.95,
 11.50,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Pure Charisma',
 24.00,
 14.00,
 2.50),

((SELECT id FROM categories WHERE name = 'Carpet'),
 (SELECT id FROM brands WHERE name = 'F&C'),
 'Patina',
 29.95,
 19.00,
 2.50);