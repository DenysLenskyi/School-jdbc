DROP TABLE IF EXISTS SCHOOL.course CASCADE;
DROP TABLE IF EXISTS SCHOOL.group CASCADE;
DROP TABLE IF EXISTS SCHOOL.student CASCADE;
DROP TABLE IF EXISTS SCHOOL.student_course CASCADE;

DROP SCHEMA IF EXISTS SCHOOL;

CREATE SCHEMA IF NOT EXISTS SCHOOL;

CREATE TABLE IF NOT EXISTS SCHOOL.course (
    ID SERIAL PRIMARY KEY,
    NAME TEXT,
    DESCRIPTION TEXT
);
CREATE TABLE IF NOT EXISTS SCHOOL.group (
    ID SERIAL PRIMARY KEY,
    NAME TEXT
);
CREATE TABLE IF NOT EXISTS SCHOOL.student (
    ID SERIAL PRIMARY KEY,
    GROUP_ID INT,
    FIRST_NAME TEXT,
    LAST_NAME TEXT,
    CONSTRAINT GROUP_ID_FK
    FOREIGN KEY (GROUP_ID)
    REFERENCES SCHOOL.group (ID)
);
CREATE TABLE IF NOT EXISTS SCHOOL.student_course (
    STUDENT_ID INT,
    COURSE_ID INT,
    CONSTRAINT STUDENT_ID_FK
    FOREIGN KEY (STUDENT_ID)
    REFERENCES student (ID)
    ON DELETE CASCADE,
    CONSTRAINT COURSE_ID_FK
    FOREIGN KEY (COURSE_ID)
    REFERENCES course (ID)
    ON DELETE CASCADE
);