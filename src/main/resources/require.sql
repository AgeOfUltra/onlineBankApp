use online_banking;
use online_banking;
CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20)
);

CREATE TABLE account (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    account_type VARCHAR(50) NOT NULL,
    balance DECIMAL(12, 2) DEFAULT 0.00,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);
CREATE TABLE transaction (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    amount DECIMAL(12, 2) NOT NULL,
    transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(255),
    FOREIGN KEY (account_id) REFERENCES account(account_id)
);
ALTER TABLE transaction
ADD CONSTRAINT fk_account_id
FOREIGN KEY (account_id)
REFERENCES account(account_id)
ON DELETE CASCADE;

ALTER TABLE account
ADD CONSTRAINT fk_user_id
FOREIGN KEY (user_id)
REFERENCES user(user_id)
ON DELETE CASCADE;


INSERT INTO users (username, password, full_name, email, phone_number)
VALUES
('john_doe', 'password123', 'John Doe', 'johndoe@example.com', '123-456-7890'),
('jane_smith', 'letmein', 'Jane Smith', 'janesmith@example.com', '987-654-3210'),
('mike_jackson', 'securepass', 'Mike Jackson', 'mikejackson@example.com', '555-123-4567'),
('amy_wong', 'password', 'Amy Wong', 'amywong@example.com', '777-888-9999'),
('chris_evans', 'avengers', 'Chris Evans', 'chrisevans@example.com', '222-333-4444');

INSERT INTO accounts (user_id, account_number, account_type, balance)
VALUES
(1, '123456789', 'Savings', 1500.00),
(2, '987654321', 'Checking', 2500.00),
(3, '555555555', 'Savings', 3000.00),
(4, '777777777', 'Checking', 500.00),
(5, '999999999', 'Savings', 2000.00);

INSERT INTO transactions (account_id, transaction_type, amount, transaction_date, description)
VALUES
(1, 'Deposit', 500.00, '2024-05-01 10:00:00', 'Initial deposit'),
(1, 'Withdrawal', 200.00, '2024-05-05 15:30:00', 'ATM withdrawal'),
(2, 'Deposit', 1000.00, '2024-05-02 12:00:00', 'Paycheck deposit'),
(3, 'Withdrawal', 500.00, '2024-05-03 11:45:00', 'Online bill payment'),
(4, 'Deposit', 300.00, '2024-05-04 09:00:00', 'Cash deposit');

