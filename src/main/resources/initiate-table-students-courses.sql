CREATE TABLE IF NOT EXISTS school.students_courses
(STUDENT_ID INT,
 COURSE_ID INT,
 CONSTRAINT STUDENT_ID_FK
 FOREIGN KEY (STUDENT_ID)
 REFERENCES school.students (STUDENT_ID)
 ON DELETE CASCADE,
 CONSTRAINT COURSE_ID_FK
 FOREIGN KEY (COURSE_ID)
 REFERENCES school.courses (ID)
 ON DELETE CASCADE);