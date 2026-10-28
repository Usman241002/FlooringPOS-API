CREATE TABLE product_colours
(
    id         integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    product_id integer      NOT NULL REFERENCES products (id),
    name       varchar(100) NOT NULL, -- "Natural Oak", "Grey"
    is_active  boolean      NOT NULL DEFAULT true,
    UNIQUE (product_id, name)
);