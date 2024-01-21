
USE `asm1_donation_project`;

    insert into `role` (`id`,`role_name`) values
		(1,'admin'),
        (2,'user'),
        (3,'user'),
        (4,'user'),
        (5,'user'),
        (6,'user'),
        (7,'user'),
        (8,'user'),
        (9,'user'),
        (10,'user'),
        (11,'user'),
        (12,'user'),
        (13,'user'),
        (14,'user'),
        (15,'user'),
        (16,'user'),
        (17,'user'),
        (18,'user'),
        (19,'user'),
        (20,'user'),
        (21,'user'),
        (22,'user');

INSERT INTO `user` (`full_name`, `user_name`, `password`, `email`, `phone_number`, `status`, `created` ,`role_id`) VALUES 
	('hai tran', 'haitran123', 'haitran123', 'haitran123@gmail.com', '0123456789', 1, '01/01/2024' ,1),
	('juan liroberto', 'juan147', 'juan147', 'juanliro@example.com', '0123478945', 1, '01/01/2024', 2),
    ('nam le', 'namle123', 'namele123', 'namledinh@sofe.de', '0324455667', 0, '01/01/2024', 3),
	('jake graham', 'jakethedog', 'jakethedog', 'jakey333@treehouse.dev' , '0011235567', 1, '01/01/2024', 4),
    ('handsome jack', 'handsomejack', 'handsomejack', 'handsomejack@test.dev' , '0011235128', 1, '01/01/2024', 5),
    ('maximilu huu', 'maxili123', 'maxili123', 'maxili123@test.dev' , '0011235123', 1, '01/01/2024', 6),
    ('joe doe', 'joedoe123', 'joedoe123', 'joedoe123@test.dev' , '0011235478', 1, '01/01/2024', 7),
    ('anna dewitt', 'annadew', 'annadew', 'annadew@test.dev' , '', 1, '01/01/2024', 8),
    ('booker dewitt', 'bookerdew', 'bookerdew', 'bookerdew@test.dev' , '0011235128', 1, '01/01/2024', 9),
    ('andrew rian', 'andrewr', 'andrewr', 'andrewr456@test.dev' , '0447788991', 0, '01/01/2024', 10),
    ('bruce wayne', 'batmanhere', 'batmanhere', 'batmanhere@test.dev' , '0011778128', 1, '01/01/2024', 11),
    ('clark kent', 'supremanthere', 'supremanthere', 'supremanthere@test.dev' , '', 1, '01/01/2024', 12),
    ('lana croft', 'laura15', 'laura15', 'laura15@test.dev' , '0231235128', 1, '01/01/2024', 13),
    ('hill jonash', 'mrhill123', 'mrhill123', 'mrhill123@test.dev' , '', 1, '01/01/2024', 14),
    ('frank senetra', 'franksinger', 'franksinger', 'franksinger@test.dev' , '', 1, '01/01/2024', 15),
    ('solomon extrelian', 'solomon123', 'solomon123', 'solomon123@test.dev' , '0777235128', 1, '01/01/2024', 16),
    ('kilian murphy', 'kilian343', 'kilian343', 'kilian343@test.dev' , '0788235128', 1, '01/01/2024', 17),
    ('jack sparrow', 'captainjack123', 'captainjack123', 'captainjack123@test.dev' , '0011238828', 1, '01/01/2024', 18),
    ('jeremy turkey', 'jeremy789', 'jeremy789', 'jeremy789@test.dev' , '', 0, '01/01/2024', 19),
    ('malfoy deli', 'malfoy456', 'malfoy456', 'malfoy456@test.dev' , '', 1, '01/01/2024', 20),
    ('harry porter', 'harryp741', 'harryp741', 'harryp741@test.dev' , '0055235128', 1, '01/01/2024', 21),
    ('selena mystirious', 'selena789', 'selena789', 'selena789@test.dev' , '0011239999', 0, '01/01/2024', 22);
    


INSERT INTO `donation` (`code`, `name`, `phone_number`, `organization`, `created`, `status` ,`start_date`,`end_date`) VALUES 
	('QG001', 'donation 1', '0123456789', 'organization 1' , '01/01/2024', 0, '2024-01-01', '2024-01-10'),
    ('QG002', 'donation 2', '0123456799', 'organization 2' , '02/01/2024', 1, '02/01/2024', '15/01/2024'),
    ('QG003', 'donation 3', '0123456799', 'organization 2' , '01/01/2024', 2, '06/01/2024', '15/01/2024'),
    ('QG004', 'donation 4', '0123456666', 'organization 3' , '01/01/2024', 4, '06/01/2024', '15/01/2024');