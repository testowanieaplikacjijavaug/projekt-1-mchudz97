package Isolation;

import SchoolSystem.Grade;
import SchoolSystem.ISchoolSubject;
import SchoolSystem.SchoolSubjectEnum;
import SchoolSystem.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SchoolSubjectMock implements ISchoolSubject {

    SchoolSubjectEnum subject;


    public SchoolSubjectMock(SchoolSubjectEnum subject){

        this.subject = subject;



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
