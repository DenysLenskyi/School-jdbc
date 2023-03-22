INSERT INTO SCHOOL.course (id, name, description) VALUES
(1, 'Math', 'Math'),
(2, 'Bio', 'Bio'),
(3, 'Sport', 'Sport');
INSERT INTO SCHOOL.group (id, name) VALUES
(1, 'AA-13'),
(2, 'BB-50');
INSERT INTO SCHOOL.student (id, group_id, first_name, last_name) VALUES
(1, 2, 'Mark', 'Antony'),
(2, 1, 'Mark2', 'Antony'),
(3, 1, 'Mark3', 'Antony'),
(4, 1, 'Mark4', 'Antony'),
(5, 1, 'Mark5', 'Antony'),
(6, 1, 'Mark6', 'Antony'),
(7, 1, 'Mark7', 'Antony'),
(8, 1, 'Mark8', 'Antony');
INSERT INTO SCHOOL.student_course (student_id, course_id) VALUES
(1, 1),
(2, 2),
(3, 3);