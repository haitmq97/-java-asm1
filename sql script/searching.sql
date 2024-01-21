USE `asm1_donation_project`;
set @input_searching := 'h';
SELECT *
FROM user u
WHERE u.email LIKE CONCAT(@input_searching, '%') OR u.phone_number LIKE CONCAT(@input_searching, '%');