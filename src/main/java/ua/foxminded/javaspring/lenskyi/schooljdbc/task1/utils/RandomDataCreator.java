package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.StringConstant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Course;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.StudentCourse;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDataCreator {

    private static Random rand;

    static {
        try {
            rand = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static FileReader reader = new FileReader();

    private static String[] names = reader.readFile(StringConstant.NAMES_TXT).split(StringConstant.SEMICOLON);
    private static final String SON = "son";

    private RandomDataCreator() {
    }

    public static List<Group> generateGroups(int numGroups) {
        List<Group> groups = new ArrayList<>();
        for (int i = 1; i <= numGroups; i++) {
            Group group = new Group();
            group.setId(i);
            group.setName(generateGroupName());
            groups.add(group);
        }
        return groups;
    }

    private static String generateGroupName() {
        StringBuilder groupName = new StringBuilder();
        int minNumberInGroupName = 10;
        int maxNumberInGroupName = 99;
        return groupName.append(getRandomCharactersUpperCase(2))
                .append(StringConstant.HYPHEN)
                .append(rand.nextInt(minNumberInGroupName, maxNumberInGroupName + 1))
                .toString();
    }

    private static String getRandomCharactersUpperCase(int numOfChars) {
        byte[] array = new byte[256];
        rand.nextBytes(array);
        String randomString = new String(array, StandardCharsets.UTF_8);
        StringBuilder r = new StringBuilder();
        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            if ((ch >= 'A' && ch <= 'Z') && (numOfChars > 0)) {
                r.append(ch);
                numOfChars--;
            }
        }
        return r.toString();
    }

    public static List<Student> generateStudents(int numStudents) {
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= numStudents; i++) {
            Student student = new Student();
            student.setId(i);
            student.setGroupId(0);
            student.setFirstName(generateStudentFirstName());
            student.setLastName(generateStudentLastName());
            students.add(student);
        }
        assignStudentsToGroups(students, 10);
        return students;
    }

    private static void assignStudentsToGroups(List<Student> students, int numOfGroups) {
        int numOfAssignedStudents = 0;
        int numStudentsToAssign = students.size();
        int minNumStudentsForGroup = 10;
        int maxNumStudentsForGroup = 30;
        for (int i = 1; i <= numOfGroups; i++) {
            int randomNumOfStudentsForOneGroup = rand.nextInt(minNumStudentsForGroup, maxNumStudentsForGroup + 1);
            numOfAssignedStudents += randomNumOfStudentsForOneGroup;
            if (numOfAssignedStudents > students.size()) {
                break;
            } else {
                while (randomNumOfStudentsForOneGroup > 0) {
                    students.get((numStudentsToAssign) - 1).setGroupId(i);
                    randomNumOfStudentsForOneGroup--;
                    numStudentsToAssign--;
                }
            }
        }
    }

    private static String generateStudentFirstName() {
        int maxNumInNamesArray = 40;
        return names[rand.nextInt(maxNumInNamesArray)];
    }

    private static String generateStudentLastName() {
        int maxNumInNamesArray = 40;
        StringBuilder studentLastName = new StringBuilder();
        return studentLastName.append(names[rand.nextInt(maxNumInNamesArray)]).append(SON).toString();
    }

    public static List<StudentCourse> generateStudentCourseRelation(int numStudents) {
        List<StudentCourse> studentsCourses = new ArrayList<>();
        int minCourseId = 1;
        int maxCourseId = 10;
        for (int i = 1; i <= numStudents; i++) {
            StudentCourse studentCourse = new StudentCourse();
            int numCourses = rand.nextInt(1, 4);
            while (numCourses > 0) {
                studentCourse.setStudentId(i);
                studentCourse.setCourseId(rand.nextInt(minCourseId, maxCourseId + 1));
                studentsCourses.add(studentCourse);
                numCourses--;
            }
        }
        return studentsCourses;
    }

    public static List<Course> getCoursesFromResources() {
        List<Course> courses = new ArrayList<>();
        String[] coursesWithDescription = reader.readFile(StringConstant.COURSES).split(StringConstant.SEMICOLON);
        int id = 0;
        for (String courseWithDescription : coursesWithDescription) {
            String[] str = courseWithDescription.split(StringConstant.WHITESPACE_HYPHEN_WHITESPACE);
            Course course = new Course();
            course.setId(id++);
            course.setName(str[0]);
            course.setDescription(str[1]);
            courses.add(course);
        }
        return courses;
    }
}