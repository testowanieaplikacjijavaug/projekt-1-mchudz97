package Isolation;

import SchoolSystem.Grade;
import SchoolSystem.ISchoolSubject;
import SchoolSystem.SchoolSubjectEnum;
import SchoolSystem.Student;

import java.util.ArrayList;
import java.util.List;

public class SchoolSubjectMock implements ISchoolSubject {

    SchoolSubjectEnum subject;
    List<Student> students;

    public SchoolSubjectMock(SchoolSubjectEnum subject){

        this.subject = subject;


    }

    @Override
    public Student getStudentByIndex(String index) {
        return null;
    }

    @Override
    public void addStudent(Student student) {

    }

    @Override
    public void removeStudent(Student student) {

    }

    @Override
    public void updateStudent(Student student, String index, String name, String pername) {

    }

    @Override
    public void editGrade(Student student, int gradeIndex, float correctedGrade) {

    }

    @Override
    public String getName() {
        return this.subject.name();
    }

    @Override
    public void setName(SchoolSubjectEnum name) {
        this.subject = name;
    }


}
