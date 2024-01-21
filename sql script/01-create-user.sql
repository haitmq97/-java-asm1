-- Drop user first if they exist
DROP USER if exists 'prj321xasm1'@'localhost' ;

-- Now create user with prop privileges
CREATE USER 'prj321xasm1'@'localhost' IDENTIFIED BY 'prj321xasm1';

GRANT ALL PRIVILEGES ON * . * TO 'prj321xasm1'@'localhost';