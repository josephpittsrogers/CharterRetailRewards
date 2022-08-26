The purpose of this web service is to display calculate the rewards for a particular customer
last 3 months.

It is a Maven project, and the Maven POM is in the GIT project, Rewards.
		
The h2 data base that is used for this unit testing is defined in the src/main/resources/data.sql file;
it drops the transaction table if necessary, creates the transaction table, and inserts 3 transactions 
for each customer, cust1 and cust2.

Once the spring boot project is deployed on the Tomcat server
there is one end point that is available.
http://localhost:8080/rewards/getRewards/{customerId) a path variable containing the customer id

To test this out the h2 in memory database is staged for two customers, cust1 and cust2
to return the following test expected results: 

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
http://localhost:8080/v2/api-docs   Json Api Documentation also link in swagger-ui

Actuator URLs
http://localhost:8080/actuator/health
http://localhost:8080/actuator/info



		
		