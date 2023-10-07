package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
//    @Autowired
//    StudentRepository studentRepository;

    StudentRepository studentRepository = new StudentRepository();

    Map<String,Student> studentDb = studentRepository.getStudentDb();
    Map<String,Teacher> teacherDb = studentRepository.getTeacherDb();
    Map<String, String> studentTeacherPairMap = studentRepository.getStudentTeacherPairMap();
    Map<String, List<String>> teacherStudentMap = studentRepository.getTeacherStudentMap();

    public String addStudent(Student student) {
        if(!studentDb.containsKey(student.getName())){
            return studentRepository.addStudent(student);
        }else{
            return null;
        }
    }

    public String addTeacher(Teacher teacher) {
        if(!teacherDb.containsKey(teacher.getName())){
            return studentRepository.addTeacher(teacher);
        }
        else {
            return null;
        }
    }

    public String addStudentTeacherPair(String student, String teacher) {
        return studentRepository.addStudentTeacherPair(student,teacher);
    }

    public Student getStudentByName(String name) {
       if (studentDb.containsKey(name)){
           return studentDb.get(name);
       }else{
           return null;
       }
    }

    public Teacher getTeacherByName(String name) {
        return teacherDb.getOrDefault(name,null);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return teacherStudentMap.getOrDefault(teacher,new ArrayList<>());
    }

    public List<String> getAllStudents() {
        if(!studentDb.isEmpty()){
            List<String> studentList = new ArrayList<>();
            for(Student student : studentDb.values()){
                studentList.add(student.getName());
            }
            return studentList;
        }else {
            return new ArrayList<>();
        }
    }

    public String deleteTeacherByName(String teacher) {
        return studentRepository.deleteTeacherByName(teacher);
    }

    public String deleteAllTeachers() {
        return studentRepository.deleteAllTeachers();
    }


}
