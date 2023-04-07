INSERT INTO SCHOOL.course (id, name, description) VALUES
(default, 'Math', 'Math'),
(default, 'Bio', 'Bio'),
(default, 'Sport', 'Sport');
INSERT INTO SCHOOL.group (id, name) VALUES
(default, 'AA-13'),
(default, 'BB-50');
INSERT INTO SCHOOL.student (id, group_id, first_name, last_name) VALUES
(default, 2, 'Mark', 'Antony'),
(default, 1, 'Mark2', 'Antony'),
(default, 1, 'Mark3', 'Antony'),
(default, 1, 'Mark4', 'Antony'),
(default, 1, 'Mark5', 'Antony'),
(default, 1, 'Mark6', 'Antony'),
(default, 1, 'Mark7', 'Antony'),
(default, 1, 'Mark8', 'Antony');
INSERT INTO SCHOOL.student_course (student_id, course_id) VALUES
(1, 1),
(2, 2),
(3, 3);