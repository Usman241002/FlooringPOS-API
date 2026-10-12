CREATE TABLE carpet_details
(
    product_id          INTEGER PRIMARY KEY,
    roll_width_m        DECIMAL(5, 2)  NOT NULL,
    stock_linear_metres DECIMAL(10, 2) NOT NULL DEFAULT 0,

    CONSTRAINT fk_carpet_details_product
        FOREIGN KEY (product_id)
            REFERENCES products (id)
            ON DELETE CASCADE,

    CONSTRAINT chk_carpet_details_roll_width
        CHECK (roll_width_m > 0),

    CONSTRAINT chk_carpet_details_stock
        CHECK (stock_linear_metres >= 0)
);

CREATE TABLE laminate_details
(
    product_id   INTEGER PRIMARY KEY,
    thickness_mm DECIMAL(5, 2),
    style        VARCHAR(30)   NOT NULL DEFAULT 'STANDARD',
    m2_per_box   DECIMAL(8, 3) NOT NULL,
    stock_boxes  INTEGER       NOT NULL DEFAULT 0,

    CONSTRAINT fk_laminate_details_product
        FOREIGN KEY (product_id)
            REFERENCES products (id)
            ON DELETE CASCADE,

    CONSTRAINT chk_laminate_details_thickness
        CHECK (thickness_mm IS NULL OR thickness_mm > 0),

    CONSTRAINT chk_laminate_details_m2_per_box
        CHECK (m2_per_box > 0),

    CONSTRAINT chk_laminate_details_stock
        CHECK (stock_boxes >= 0)
);

CREATE TABLE lino_details
(
    product_id          INTEGER PRIMARY KEY,
    roll_width_m        DECIMAL(5, 2)  NOT NULL,
    stock_linear_metres DECIMAL(10, 2) NOT NULL DEFAULT 0,

    CONSTRAINT fk_lino_details_product
        FOREIGN KEY (product_id)
            REFERENCES products (id)
            ON DELETE CASCADE,

    CONSTRAINT chk_lino_details_roll_width
        CHECK (roll_width_m > 0),

    CONSTRAINT chk_lino_details_stock
        CHECK (stock_linear_metres >= 0)
);
