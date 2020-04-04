package Isolation;
import SchoolSystem.ISchoolSubject;
import SchoolSystem.SchoolSubjectEnum;


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
