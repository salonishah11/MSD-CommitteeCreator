# Team2

## Authors
* Rushabh Shah
* Priyanka B H 
* Vikas Janardhanan
* Saloni Shah


##System Components
* Front End: Responsible for analyzing publication data

* Query Engine: Responsible for executing queries against the representation that the front end creates.

* User Interface: Responsible for providing end users facilities for entering and executing queries and then displaying the results

Installation Instructions:
How to build database schema and run parser:

* Create the database schema by running Phase4/DBLPParser/src/main/java/com/msd/DBLPParser/db/NewDBScript.sql using MySQLWorkBench to create the dblp_full schema that contains all the required tables.
 Ensure that the DB credentials are as expected in Phase4/DBLPParser/src/main/java/com/msd/DBLPParser/db/db.Properties file.
*	Ensure that the DB credentials are as expected in Phase4/DBLPParser/src/main/java/com/msd/DBLPParser/db/db.Properties file.
(This will be the Database that the application will try to connect to when you run the jar).
*	Right click on project directory. Run As -> Run Configuration-> Maven Build
*	Enter compile assembly:single (as the goal)
*	Click on run.
*	The application jar will be generated in target directory
*	Run the parser as java –jar DBLPParser-0.0.1-SNAPSHOT-jar-with-dependencies.jar dblp.xml_file_path committeedir_file_path csrankings_filepath csrankings_univregion_filepath
*	Next step is to de-normalize the database tables created. For this, run Phase4/DBLPParser/src/main/java/com/msd/DBLPParser/db/Normalize.sql from MySQLWorkBench

How to build application jar:
*	Ensure that the DB credentials are as expected in MSD_TEAM2_PROJECT/src/main/java/com/msd/db/db.Properties file.
(This will be the Database that the application will try to connect to when you run the jar).
*	Right click on project directory. Run as -> Run Configuration-> Maven Build
*	Enter compile assembly:single (as the goal)
*	Click on run.
*	The application jar will be generated as team2-0.0.1- SNAPSHOT-jar- with-
dependencies.jar in target directory
*	Double click on the generated jar to start the application.
*	
**Documentation** : Once you take the checkout of project navigate to Phase4/MSD_TEAM2_Project/doc/index.html to view the code documentation
:
