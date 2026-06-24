SET FOREIGN_KEY_CHECKS = 0;
DELETE FROM travel_package;
DELETE FROM destinations;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO destinations (name, country, description, image, category) VALUES
('Bali',        'Indonesia', 'Tropical paradise with beaches and vibrant culture.',  'bali.jpg',       'Island'),
('Lombok',      'Indonesia', 'Beautiful beaches and Mount Rinjani.',                 'lombok.jpg',     'Island'),
('Raja Ampat',  'Indonesia', 'World class marine paradise experience.',              'raja-ampat.jpg', 'Coastal'),
('Labuan Bajo', 'Indonesia', 'Gateway to Komodo National Park.',                     'raja-ampat.jpg', 'Adventure');

INSERT INTO travel_package (name, destination, description, price, image, duration, package_type, rating) VALUES
('Bali Adventure',      'Bali',        'Explore Bali for 5 days including Uluwatu dan Nusa Penida.',     3500000, 'bali.jpg',       5, 'Domestic', 4.9),
('Bali Honeymoon',      'Bali',        'Romantic getaway in Bali with private villa dan sunset dinner.', 5000000, 'bali.jpg',       5, 'Domestic', 4.8),
('Lombok Explorer',     'Lombok',      'Discover beaches and Mount Rinjani trekking adventure.',         4200000, 'lombok.jpg',     4, 'Domestic', 4.7),
('Raja Ampat Explorer', 'Raja Ampat',  'Experience paradise islands with world class snorkeling.',       7500000, 'raja-ampat.jpg', 7, 'Domestic', 4.9),
('Labuan Bajo Escape',  'Labuan Bajo', 'Explore Komodo National Park and see the Komodo dragon.',        6200000, 'raja-ampat.jpg', 3, 'Domestic', 4.6);
