The purpose of this web service is to display calculate the rewards for a particular customer
last 3 months.

It is a Maven project, and the Maven POM is in the GIT project, Rewards.
		
The h2 data base that is used for this unit testing is defined in the src/main/resources/data.sql file;
it drops the transaction table if necessary, creates the transaction table, and inserts 3 transactions 
for each customer, cust1 and cust2.

The following are the contents of data.sql.  The in memory table is dropped, created and records are inserted 
to create the test scenarios required. In the future the dates may have to be changed as they are based on
today's date.  In the monthly breakdown the months may change but the totals will be consistent with 
expected test results for the application running local 
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
  
Note: The implemented Swagger would be a better way to test the application but the 
endpoints are also available.

Once the spring boot project is deployed
there are two end points that is available, one for total and one for monthly breakdowns.

http://localhost:8080/rewards/getRewards/{customerId) a path variable containing the customer id

To test this out the h2 in memory database is staged for two customers, cust1 and cust2
to return the following test expected results with the current date criteria.

http://localhost:8080/rewards/getAllRewards/cust1
returns { customer: cust1 rewards: 451.10}
one transaction was earlier than 3 months and another had a transaction amount < 50 so they were filtered

http://localhost:8080/rewards/getAllRewards/cust2
returns { customer: cust2 rewards: 353.10}

http://localhost:8080/rewards/getRewardsByMonth/cust1
returns { { customer: cust1 month: August rewards:451.10 } }
one transaction was earlier than 3 months and another had a transaction amount < 50 so they were filtered

http://localhost:8080/rewards/getRewardsByMonth/cust2
returns { { customer: cust2 month: June rewards:310.22 }{ customer: cust2 month: July rewards:1.45 }{ customer: cust2 month: August rewards:41.43 } }

The transactions are filtered to eliminate any transaction < $50.00 and older than 3 months
The expected test results eliminate on transaction older than 3 months and one not > $50 for cust1.

Swagger URLs
All of the above testing can be done via swagger-ui and testing the endoints with the appropriate parameters.  The results would be returned in the response body.
http://localhost:8080/swagger-ui/   Api Documentaton and test rewards-controller with curl
http://localhost:8080/v2/api-docs   Json Api Documentation also link in swagger-ui (also found on the swagger-ui page)

The actuator is function and may be enhanced as needed.
Actuator URLs
http://localhost:8080/actuator/health
http://localhost:8080/actuator/info



		
		