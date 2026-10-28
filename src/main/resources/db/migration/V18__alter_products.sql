ALTER TABLE products
    DROP COLUMN fitting_rule_id;

ALTER TABLE products
    DROP COLUMN cost_price_m2;

ALTER TABLE products
    DROP COLUMN selling_price_m2;

ALTER TABLE products
    ADD COLUMN is_herringbone boolean NOT NULL DEFAULT false;