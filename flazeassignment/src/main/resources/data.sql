DROP TABLE IF EXISTS transaction;
 
CREATE TABLE transaction (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  customer_id VARCHAR(25) NOT NULL,
  transaction_amount DECIMAL NOT NULL,
  transaction_date DATE NOT NULL
);
 
INSERT INTO transaction (customer_id, transaction_amount, transaction_date) VALUES
  ('cust1', 40.21, '2022-07-22');
INSERT INTO transaction (customer_id, transaction_amount, transaction_date) VALUES
  ('cust1', 80.91, '2022-01-22');
INSERT INTO transaction (customer_id, transaction_amount, transaction_date) VALUES
  ('cust1', 300.55, '2022-08-12');
INSERT INTO transaction (customer_id, transaction_amount, transaction_date) VALUES
  ('cust2', 51.45, '2022-07-12');
INSERT INTO transaction (customer_id, transaction_amount, transaction_date) VALUES
  ('cust2', 91.43, '2022-08-12');
INSERT INTO transaction (customer_id, transaction_amount, transaction_date) VALUES
  ('cust2', 230.11, '2022-06-12');
  
  