INSERT INTO addresses (customer_id, address_line_1, address_line_2, postcode)
VALUES
    -- John Smith (customer 1): 1 address
    (1, '14 High Street', NULL, 'B25 8AB'),

    -- Jane Doe (customer 2): 2 addresses
    (2, '22 Station Road', 'Kings Heath', 'B14 7SR'),
    (2, '8 Church Lane', NULL, 'B13 8JP'),

    -- Alex Taylor (customer 3): 0 addresses

    -- Emily Wilson (customer 4): 1 address
    (4, '35 Acocks Green Lane', NULL, 'B27 6RA'),

    -- Oliver Brown (customer 5): 2 addresses
    (5, '7 Pershore Road', 'Stirchley', 'B30 2YS'),
    (5, '19 Bristol Road', NULL, 'B29 6AU'),

    -- Sophie Davies (customer 6): 0 addresses

    -- Jack Thomas (customer 7): 1 address
    (7, '41 Coventry Road', NULL, 'B10 0SD'),

    -- Grace Roberts (customer 8): 3 addresses
    (8, '12 Stratford Road', 'Sparkhill', 'B11 4AB'),
    (8, '6 Yardley Road', NULL, 'B25 8LT'),
    (8, '28 Warwick Road', NULL, 'B11 2HA'),

    -- Harry Johnson (customer 9): 3 addresses
    (9, '53 Soho Road', 'Handsworth', 'B21 9SP'),
    (9, '16 Dudley Road', NULL, 'B18 7QY'),
    (9, '9 Cape Hill', NULL, 'B66 4SH'),

    -- Lucy Walker (customer 10): 3 addresses
    (10, '25 Sheldon Road', NULL, 'B26 3RU'),
    (10, '3 Fox Hollies Road', NULL, 'B27 7PY'),
    (10, '18 Olton Boulevard East', NULL, 'B27 7RR');