[Back to Main Documentation](../README.md)
# Logs


### 2024.06.21
I had tzo start making logs to better keep track of what I did and still had to do on the next day. 
I completely reworked the Blogs, Comments and Accounts. Comments are now linked to a foreign key in the Blog database. 
I still have to test everything. The keycloack service is creating an error after exactly 30 minutes. If left run, despite quarkus not responding anymore, it reproduces the error every 30 minutes. Maby some token that can't be renewed?
