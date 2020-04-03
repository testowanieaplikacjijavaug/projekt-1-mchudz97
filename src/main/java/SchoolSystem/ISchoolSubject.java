package SchoolSystem;

public interface ISchoolSubject {

    public Student getStudentByIndex(String index);

    public void removeStudent(Student student);

    public void updateStudent(Student student, String index, String name, String pername);

    public void editGrade(Student student, Grade grade, float correctedGrade);

}
