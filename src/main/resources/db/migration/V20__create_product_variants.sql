CREATE TABLE product_variants
(
    id            integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    product_id    integer        NOT NULL REFERENCES products (id),
    name          varchar(100)   NOT NULL,              -- "8mm", "4m roll", "2.7m door bar"
    thickness_mm  integer CHECK (thickness_mm IS NULL OR thickness_mm > 0),
    roll_width_m  numeric(5, 2) CHECK (roll_width_m IS NULL OR roll_width_m > 0),
    sqm_per_box   numeric(8, 3) CHECK (sqm_per_box IS NULL OR sqm_per_box > 0),
    unit          varchar(10)    NOT NULL CHECK (unit IN ('m2', 'item')),
    cost_price    numeric(12, 2) CHECK (cost_price IS NULL OR cost_price >= 0),
    selling_price numeric(12, 2) NOT NULL CHECK (selling_price >= 0),
    is_active     boolean        NOT NULL DEFAULT true,
    UNIQUE (product_id, name),
    CHECK (roll_width_m IS NULL OR sqm_per_box IS NULL) -- a variant is a roll or a box, not both
);

CREATE INDEX ON product_variants (product_id);