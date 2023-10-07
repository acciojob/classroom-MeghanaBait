package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    Map<String,Student> studentDb;
    Map<String,Teacher> teacherDb;
    Map<String, String> studentTeacherPairMap;
    Map<String, List<String>> teacherStudentMap;

    public StudentRepository() {
        this.studentDb = new HashMap<>();
        this.teacherDb = new HashMap<>();
        this.studentTeacherPairMap = new HashMap<>();
        this.teacherStudentMap = new HashMap<>();
    }

    public Map<String, Student> getStudentDb() {
        return studentDb;
    }

    public Map<String, Teacher> getTeacherDb() {
        return teacherDb;
    }

    public Map<String, String> getStudentTeacherPairMap() {
        return studentTeacherPairMap;
    }

    public Map<String, List<String>> getTeacherStudentMap() {
        return teacherStudentMap;
    }

    public String addStudent(Student student) {
        if(!getStudentDb().containsKey(student.getName())){
            getStudentDb().put(student.getName(),student);
            return "New student added successfully";
        }else{
            return "New student not added";
        }
    }

    public String addTeacher(Teacher teacher) {
        if (!getTeacherDb().containsKey(teacher.getName())){
            getTeacherDb().put(teacher.getName(),teacher);
            return "New Student added successfully";
        }else{
            return "New teacher not added";
        }
    }

    public String addStudentTeacherPair(String student, String teacher) {
        if(getStudentDb().containsKey(student) && getTeacherDb().containsKey(teacher)){
            List<String> studentList = getTeacherStudentMap().getOrDefault(teacher,new ArrayList<>());
            studentList.add(student);
            getTeacherStudentMap().put(teacher,studentList);

            if(!getStudentTeacherPairMap().containsKey(student)){
                getStudentTeacherPairMap().put(student,teacher);
            }

            getTeacherDb().get(teacher).setNumberOfStudents(getTeacherDb().get(teacher).getNumberOfStudents()+1);
            return "New Student Teacher pair added successfully";
        }else{
            return "Pair Not Added";
        }
    }

    public String deleteTeacherByName(String teacher) {
        if(getTeacherDb() == null || getTeacherStudentMap() == null || !getTeacherDb().containsKey(teacher)){
            return teacher+"not Found";
        }
        teacherDb.remove(teacher);
        for(String student : teacherStudentMap.get(teacher)){
            studentTeacherPairMap.remove(student);
        }
        teacherStudentMap.remove(teacher);
        return "remove successfully";
    }

    public String deleteAllTeachers() {
        if(teacherDb.isEmpty()) {
            return "Teachers not found";
        }
        getTeacherDb().clear();
        getStudentTeacherPairMap().clear();
        getTeacherStudentMap().clear();
        return "All teachers deleted successfully";
    }


}
