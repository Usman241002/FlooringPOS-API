CREATE TABLE fitting_rates
(
    id             integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    category_id    integer        NOT NULL REFERENCES categories (id),
    thickness_mm   integer CHECK (thickness_mm IS NULL OR thickness_mm > 0), -- NULL = any thickness
    is_herringbone boolean        NOT NULL DEFAULT false,
    price_m2       numeric(12, 2) NOT NULL CHECK (price_m2 >= 0),
    min_charge     numeric(12, 2) NOT NULL CHECK (min_charge >= 0),
    UNIQUE NULLS NOT DISTINCT (category_id, thickness_mm, is_herringbone)    -- PG 15+
);

INSERT INTO fitting_rates (category_id, thickness_mm, is_herringbone, price_m2, min_charge)
SELECT c.id, v.thickness_mm, v.is_herringbone, v.price_m2, v.min_charge
FROM (VALUES ('Carpet', NULL, false, 2.50, 35.00),
             ('Lino', NULL, false, 2.50, 35.00),
             ('Laminate', 7, false, 6.00, 70.00),
             ('Laminate', 8, false, 6.00, 70.00),
             ('Laminate', 10, false, 6.00, 70.00),
             ('Laminate', 12, false, 7.00, 70.00),
             ('Laminate', 12, true, 15.00, 110.00),
             ('SPC', NULL, false, 7.00, 70.00),
             ('SPC', NULL, true, 15.00, 110.00)) AS v(category, thickness_mm, is_herringbone, price_m2, min_charge)
         JOIN categories c ON c.name = v.category;