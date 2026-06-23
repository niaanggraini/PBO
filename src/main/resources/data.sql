-- =====================================
-- DESTINATIONS
-- =====================================

INSERT INTO destinations
(name, country, description, image)

VALUES

(
'Bali',
'Indonesia',
'Island of Gods with beautiful beaches',
'bali.jpg'
),

(
'Lombok',
'Indonesia',
'Beautiful beaches and Mount Rinjani',
'lombok.jpg'
),

(
'Raja Ampat',
'Indonesia',
'World class marine paradise',
'raja-ampat.jpg'
),

(
'Labuan Bajo',
'Indonesia',
'Gateway to Komodo National Park',
'labuan-bajo.jpg'
);



-- =====================================
-- TRAVEL PACKAGES
-- =====================================

INSERT INTO travel_package
(name, destination, description, image, price)

VALUES

(
'Bali Adventure',
'Bali',
'Explore Bali for 5 days',
'bali.jpg',
3500000
),

(
'Bali Honeymoon',
'Bali',
'Romantic getaway in Bali',
'bali.jpg',
5000000
),

(
'Lombok Explorer',
'Lombok',
'Discover beaches and Mount Rinjani',
'lombok.jpg',
4200000
),

(
'Raja Ampat Explorer',
'Raja Ampat',
'Experience paradise islands',
'raja-ampat.jpg',
7500000
),

(
'Labuan Bajo Escape',
'Labuan Bajo',
'Explore Komodo National Park',
'labuan-bajo.jpg',
6200000
);