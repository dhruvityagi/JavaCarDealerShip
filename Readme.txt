Project 1 Unit 6
===================================

The folder consists of following material:
1. Client folder- which consists of the code developed in Eclipse for Client
2. Server folder- which consists of the code developed in Eclipse for Server
3. Project1Unit6.pdf - which consists of the class diagram for the project.
4. text_output_6_Server - Output of server
5. Lessons learned

The client project consists of 8 packages:
1. driver
2. adapter
3. exception
4. model
5. util
6. scale
7. client
8. com.dt -> For servelets

The server project consists of 8 packages:
1. driver
2. adapter
3. exception
4. model
5. util
6. scale
7. server
8. database

Instructions to run
Import both the code to eclipse and run once.
1. Run Server. The sql userName and password are to be entered in DataBaseBasic.java
2. Run Client
3. Open the web page associated with client.
4. First load config file to server through client. Config files at Client are FordProperties.txt and TeslaProperties.txt
5. Then configure a car at client
6. Then check console of server and follow the text_output_6_Server.txt file to edit automobile features in database.

Following tests are included in text_output_6_Server.txt:
1. display cars available in the record
2. change Make/Model of the car
3. change Base Price of the car
4. change Option Set name for a given car
5. see Options of a given OptionSet in car
6. change Option name for a given car and OptionSet
7. change Option price for a given car,OptionSet and Option
8. delete Option for a given car,OptionSet
9. delete OptionSet for a given car
10. delete a car record
11. delete all car record
12. Load car configuration from client to database

