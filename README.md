Do the next steps to run APP:
1. Download and install JDK or JRE last version from Oracle official website oracle.com
2. Download and install PostgreSql pgAdmin from postgresql.org
3. Open pgAdmin and create new database - db_school_lenskyi
4. Create new user for this database:
   User name: lenskyi
   Password: lenskyi
5. Open commandline\terminal and execute SchoolJDBC-1-jar-with-dependencies.jar with command -java -jar SchoolJDBC-1-jar-with-dependencies.jar
   You'll need to be in the jar directory or type full path to jar
   for example: -java -jar /Users/deka/foxminded/SchoolJDBC/target/SchoolJDBC-1-jar-with-dependencies.jar
6. APP runs, you could use available commands like find_course --course_id=7, find_groups --num_students=20 etc.
   use 'info' to get full commands list

Task description.

Create a sql-jdbc-school application  that inserts/updates/deletes data in the database using JDBC.

Use PostgreSQL DB.

Tables (the given types are Java types, use SQL analogs that fit best:

groups(
	group_id int,
	group_name string
)
students(
	student_id int,
	group_id int,
	first_name string,
	last_name string
)
courses(
	course_id int,
	course_name string,
	course_description string
)
1. Create SQL files with data:

a. create a user and database. Assign all privileges on the database to the user. (DB and the user should be created before application runs)

b. create a file with tables creation

2. Create a java application

a. On startup, it should run SQL script with table creation from previously created files. If tables already exist - drop them.

b. Generate the test data:
* 10 groups with randomly generated names. The name should contain 2 characters, hyphen, 2 numbers
* Create 10 courses (math, biology, etc)
* 200 students. Take 20 first names and 20 last names and randomly combine them to generate students.
* Randomly assign students to groups. Each group could contain from 10 to 30 students. It is possible that some groups will be without students or students without groups
* Create the MANY-TO-MANY relation  between STUDENTS and COURSES tables. Randomly assign from 1 to 3 courses for each student
3. Write SQL Queries, it should be available from the console menu:
a. Find all groups with less or equal students’ number

b. Find all students related to the course with the given name

c. Add a new student

d. Delete a student by the STUDENT_ID

e. Add a student to the course (from a list)

f. Remove the student from one of their courses.