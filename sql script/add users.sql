
USE `asm1_donation_project`;

insert into `role` (`id`,`role_name`) values
		(1,'ADMIN'),
        (2,'USER'),
        (3,'USER'),
        (4,'USER'),
        (5,'USER'),
        (6,'USER'),
        (7,'USER'),
        (8,'USER'),
        (9,'USER'),
        (10,'USER'),
        (11,'USER'),
        (12,'USER'),
        (13,'USER'),
        (14,'USER'),
        (15,'USER'),
        (16,'USER'),
        (17,'USER'),
        (18,'USER'),
        (19,'USER'),
        (20,'USER'),
        (21,'USER'),
        (22,'USER');    

INSERT INTO `user` (`full_name`, `user_name`, `password`, `email`, `phone_number`, `status`, `created_date` ,`role_id`) VALUES 
	('hai tran', 'haitran123', 'haitran123', 'haitran123@gmail.com', '0123456789', 'ACTIVE', '01-01-2024' ,1),
	('juan liroberto', 'juan147', 'juan147', 'juanliro@example.com', '0123478945', 'ACTIVE', '01/01/2024', 2),
    ('nam le', 'namle123', 'namele123', 'namledinh@sofe.de', '0324455667', 'LOCKED', '01/01/2024', 3),
	('jake graham', 'jakethedog', 'jakethedog', 'jakey333@treehouse.dev' , '0011235567', 'ACTIVE', '01/01/2024', 4),
    ('handsome jack', 'handsomejack', 'handsomejack', 'handsomejack@test.dev' , '0011235128', 'ACTIVE', '01/01/2024', 5),
    ('maximilu huu', 'maxili123', 'maxili123', 'maxili123@test.dev' , '0011235123', 'ACTIVE', '01/01/2024', 6),
    ('joe doe', 'joedoe123', 'joedoe123', 'joedoe123@test.dev' , '0011235478', 'ACTIVE', '01/01/2024', 7),
    ('anna dewitt', 'annadew', 'annadew', 'annadew@test.dev' , '', 'ACTIVE', '01/01/2024', 8),
    ('booker dewitt', 'bookerdew', 'bookerdew', 'bookerdew@test.dev' , '0011235128', 'ACTIVE', '01/01/2024', 9),
    ('andrew rian', 'andrewr', 'andrewr', 'andrewr456@test.dev' , '0447788991', 'LOCKED', '01/01/2024', 10),
    ('bruce wayne', 'batmanhere', 'batmanhere', 'batmanhere@test.dev' , '0011778128', 'ACTIVE', '01/01/2024', 11),
    ('clark kent', 'supremanthere', 'supremanthere', 'supremanthere@test.dev' , '', 'ACTIVE', '01/01/2024', 12),
    ('lana croft', 'laura15', 'laura15', 'laura15@test.dev' , '0231235128', 'ACTIVE', '01/01/2024', 13),
    ('hill jonash', 'mrhill123', 'mrhill123', 'mrhill123@test.dev' , '', 'ACTIVE', '01/01/2024', 14),
    ('frank senetra', 'franksinger', 'franksinger', 'franksinger@test.dev' , '', 'ACTIVE', '01/01/2024', 15),
    ('solomon extrelian', 'solomon123', 'solomon123', 'solomon123@test.dev' , '0777235128', 'ACTIVE', '01/01/2024', 16),
    ('kilian murphy', 'kilian343', 'kilian343', 'kilian343@test.dev' , '0788235128', 'ACTIVE', '01/01/2024', 17),
    ('jack sparrow', 'captainjack123', 'captainjack123', 'captainjack123@test.dev' , '0011238828', 'ACTIVE', '01/01/2024', 18),
    ('jeremy turkey', 'jeremy789', 'jeremy789', 'jeremy789@test.dev' , '', 'LOCKED', '01/01/2024', 19),
    ('malfoy deli', 'malfoy456', 'malfoy456', 'malfoy456@test.dev' , '', 'ACTIVE', '01/01/2024', 20),
    ('harry porter', 'harryp741', 'harryp741', 'harryp741@test.dev' , '0055235128', 'ACTIVE', '01/01/2024', 21),
    ('selena mystirious', 'selena789', 'selena789', 'selena789@test.dev' , '0011239999', 'LOCKED', '01/01/2024', 22);
    


INSERT INTO `donation` (`code`, `name`, `phone_number`, `organization`, `created_date`, `status` ,`start_date`,`end_date`) VALUES 
	('QG001', 'donation 1', '0123456789', 'organization 1' , '2024-01-01', 'NEW', '2024-06-03', '2024-06-16'),
    ('QG002', 'donation 2', '0123456799', 'organization 2' , '2024-01-01', 'NEW', '2024-01-05', '2024-01-10'),
    ('QG003', 'donation 3', '0123456799', 'organization 4' , '2024-01-01', 'NEW', '2024-01-17', '2024-02-01'),
    ('QG004', 'donation 4', '0123456799', 'organization 3' , '2024-01-01', 'NEW', '2024-06-12', '2024-06-15'),
    ('QA005', 'donation 5', '0123456799', 'organization 6' , '2024-01-01', 'NEW', '2024-06-15', '2024-06-17'),
    ('QG006', 'donation 6', '0123456799', 'organization 7' , '2024-01-01', 'NEW', '2024-03-01', '2024-04-01'),
    ('QG007', 'donation 7', '0123456799', 'organization 8' , '2024-01-01', 'CLOSED', '2024-02-24', '2024-05-11'),
    ('QA008', 'donation 8', '0123456799', 'organization 4' , '2024-01-01', 'NEW', '2024-04-01', '2024-04-30'),
    ('QG009', 'donation 9', '0123456666', 'organization 3' , '2024-01-01', 'NEW', '2024-05-21', '2024-06-14');
    

/*
INSERT INTO `donation` (`code`, `name`, `phone_number`, `organization`, `created_date`, `status` ,`start_date`,`end_date`) VALUES 
	('QG001', 'donation 1', '0123456789', 'organization 1' , '01/01/2024', 'NEW', '03/06/2024', '16/06/2024'),
    ('QG002', 'donation 2', '0123456799', 'organization 2' , '01/01/2024', 'NEW', '05/01/2024', '10/01/2024'),
    ('QG003', 'donation 3', '0123456799', 'organization 4' , '01/01/2024', 'NEW', '17/01/2024', '01/02/2024'),
    ('QG004', 'donation 4', '0123456799', 'organization 3' , '01/01/2024', 'NEW', '12/06/2024', '15/06/2024'),
    ('QA005', 'donation 5', '0123456799', 'organization 6' , '01/01/2024', 'NEW', '15/06/2024', '05/07/2024'),
    ('QG006', 'donation 6', '0123456799', 'organization 7' , '01/01/2024', 'NEW', '01/03/2024', '01/04/2024'),
    ('QG007', 'donation 7', '0123456799', 'organization 8' , '01/01/2024', 'CLOSED', '24/02/2024', '11/05/2024'),
    ('QA008', 'donation 8', '0123456799', 'organization 4' , '01/01/2024', 'NEW', '01/04/2024', '30/04/2024'),
    ('QG009', 'donation 9', '0123456666', 'organization 3' , '01/01/2024', 'NEW', '21/05/2024', '14/06/2024');
    
*/
--     
--     
-- insert into `user_donation`(`created_date`, `money`, `name`, `status`, `user_id`, `donation_id`)
-- 	value ('12/01/2024', 100000, 'jake graham', 'CONFIRMED', 4, 1),
--     ('24/01/2024', 50000, 'hansome jack', 'CONFIRMED', 5, 1),
--     ('20/01/2024', 2000000, 'frank senetra', 'WAITING', 15, 1),
--     ('12/01/2024', 250000, 'jake graham', 'WAITING', 4, 2),
--     ('04/02/2024', 150000, 'lana croft', 'CONFIRMED', 13, 2),
--     ('23/01/2024', 5000000, 'harry porter', 'WAITING', 21, 3);