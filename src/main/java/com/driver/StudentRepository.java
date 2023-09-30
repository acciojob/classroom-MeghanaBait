package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    Map<String,Student> studentDb = new HashMap<>();
    Map<String,Teacher> teacherDb = new HashMap<>();
    Map<String, String> studentTeacherPairMap = new HashMap<>();
    Map<String, List<String>> teacherStudentMap = new HashMap<>();
    public void addStudent(Student student) {
        studentDb.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        //adding into studentTeacherPairMap
        studentTeacherPairMap.put(student,teacher);
        //adding into teacherStudentMap
        List<String> studentList = teacherStudentMap.getOrDefault(teacher,new ArrayList<>());
        studentList.add(student);
        teacherStudentMap.put(teacher,studentList);
        teacherDb.get(teacher).setAge(teacherDb.get(teacher).getAge() + 1);
    }


    public Student getStudentByName(String name) {
        return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDb.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return teacherStudentMap.get(teacher);
    }

    public List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        for(String student : studentDb.keySet()){
            studentList.add(student);
        }
        return studentList;
    }

    public void deleteTeacherByName(String teacher) {
        if (teacherDb.containsKey(teacher)) {
            teacherDb.remove(teacher);
        }
        for(String student : teacherStudentMap.get(teacher)){
            if(studentTeacherPairMap.containsKey(student)){
                studentTeacherPairMap.remove(student);
            }
        }
        teacherStudentMap.remove(teacher);
    }

    public void deleteAllTeachers() {
        teacherDb.clear();
        studentTeacherPairMap.clear();
        teacherStudentMap.clear();
    }


}
