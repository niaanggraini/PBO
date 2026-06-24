-- =====================================
-- DESTINATIONS
-- =====================================
DELETE FROM travel_package;
DELETE FROM destinations;

INSERT INTO destinations
(name, country, description, image, category)

VALUES

(
'Bali',
'Indonesia',
'Tropical paradise with beaches and vibrant culture.',
'bali.jpg',
'Island'
),

(
'Lombok',
'Indonesia',
'Beautiful beaches and Mount Rinjani.',
'lombok.jpg',
'Island'
),

(
'Raja Ampat',
'Indonesia',
'World class marine paradise experience.',
'raja-ampat.jpg',
'Coastal'
),

(
'Labuan Bajo',
'Indonesia',
'Gateway to Komodo National Park.',
'labuan-bajo.jpg',
'Adventure'
);



-- =====================================
-- TRAVEL PACKAGES
-- =====================================

INSERT INTO travel_package
(name, destination, package_type, description, duration, image, price)

VALUES

(
'Bali Adventure',
'Bali',
'Domestic',
'Explore Bali for 5 days',
5,
'bali.jpg',
3500000
),

(
'Bali Honeymoon',
'Bali',
'Domestic',
'Romantic getaway in Bali',
5,
'bali.jpg',
5000000
),

(
'Lombok Explorer',
'Lombok',
'Domestic',
'Discover beaches and Mount Rinjani',
4,
'lombok.jpg',
4200000
),

(
'Raja Ampat Explorer',
'Raja Ampat',
'Domestic',
'Experience paradise islands',
7,
'raja-ampat.jpg',
7500000
),

(
'Labuan Bajo Escape',
'Labuan Bajo',
'Domestic',
'Explore Komodo National Park',
3,
'labuan-bajo.jpg',
6200000
);

