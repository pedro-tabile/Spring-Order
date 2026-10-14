CREATE TABLE users_audit(
    id INT PRIMARY KEY AUTO_INCREMENT,
    operation CHAR(1) NOT NULL,
    old_username VARCHAR(255) NOT NULL,
    old_password VARCHAR(255) NOT NULL,
    old_role ENUM('MANAGER', 'CUSTOMER', 'CASHIER', 'STOCKER') NOT NULL,
    new_username VARCHAR(255),
    new_password VARCHAR(255),
    new_role ENUM('MANAGER', 'CUSTOMER', 'CASHIER', 'STOCKER'),
    datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);