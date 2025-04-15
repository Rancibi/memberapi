-- Adresser
INSERT INTO addresses (id, street, postal_code, city) VALUES
    (1, 'Storgatan 1', '12345', 'Stockholm'),
    (2, 'Lillvägen 1', '23456', 'Göteborg'),
    (3, 'Bergsvägen 1', '34567', 'Malmö'),
    (4, 'Skogsstigen 1', '45678', 'Uppsala'),
    (5, 'Sjövägen 1', '56789', 'Lund');

-- Medlemmar
INSERT INTO members (first_name, last_name, address_id, email, phone, date_of_birth) VALUES
    ('Anna', 'Svensson', 1, 'anna@example.com', '0701234567', '2000-01-01'),
    ('Erik', 'Karlsson', 2, 'erik@example.com', null, '1998-05-12'),
    ('Maria', 'Johansson', 3, 'maria@example.com', '0739876543', '1995-09-30'),
    ('Oskar', 'Lindgren', 4, 'oskar@example.com', '0761112233', '2002-12-15'),
    ('Emma', 'Andersson', 5, 'emma@example.com', null, '1999-07-07');