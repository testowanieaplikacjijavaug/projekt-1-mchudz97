package SchoolSystem;

public class SchoolSubject implements ISchoolSubject {

    private  SchoolSubjectEnum name;
    public SchoolSubject(SchoolSubjectEnum schoolSubjectEnum){

       this.name = schoolSubjectEnum;

    }


    @Override
    public String getName() {
        return this.name.name();
    }

    @Override
    public void setName(SchoolSubjectEnum name) {
        this.name = name;
    }


}
