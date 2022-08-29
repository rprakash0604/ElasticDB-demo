# Getting Started

### Reference Documentation
This is a sample project for Elastic Search.

Step 1: Install Docker desktop if not already done and then run elastic search instance locally using the below command :

docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2

this will install and run elastic search instanc eon your local.


Step 2: Run ElasticDB-demo app

Below are rest endpoint implemented :

1) Insert Default Data: http://localhost:8081/insertDefaultData
2) Insert Data Manually: http://localhost:8081/insertData?name=Sachin&age=30&dob=08-06-2020
3) Fetch All the Data: http://localhost:8081/fetchData
4) Fetch Data by ID: http://localhost:8081/fetchDataById?id=toQV6oIB1TjgA1ozo53I
5) Update Data: http://localhost:8081/updateData?id=s4RP3IIB1TjgA1ozLJ0K&name=Demo&age=34&dob=04-07-2001
6) Delete Data: http://localhost:8081/deleteData?id=tIRe3YIB1TjgA1oz2J14
