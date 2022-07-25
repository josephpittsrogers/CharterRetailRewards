The purpose of this web service is to display calculate the rewards for a particular customer
last 3 months.

It is a maven project and the maven files are in the git project.
		
The h2 data base that is used for this unit testing is defined in the src/main/resources/data.sql file;
it drops the transaction table if necessary, creates the transaction table, and inserts 3 transactions 
for each customer, cust1 and cust2.

Once the spring boot project is deployed on the Tomcat server
there is one end point that is available.
http://localhost:8080/rewards/getRewards/{customerId) a path variable containing the customer id

To test this out the h2 in memory database is staged for two customers, cust1 and cust2
to return the following test expected results: 

http://localhost:8080/rewards/getRewards/cust1
returns the message: The number of rewards for customer: cust1 is 450

http://localhost:8080/rewards/getRewards/cust2
returns the message: The number of rewards for customer: cust2 is 352

The transactions are filtered to eliminate any transaction < $50.00 and older than 3 months
The expected test results eliminate on transaction older than 3 months and one not > $50 for cust1.

		
		