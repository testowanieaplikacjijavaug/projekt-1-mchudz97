package SchoolSystem;

import java.util.ArrayList;
import java.util.List;

public class MySchoolSystem {

    public List<Student> students;
    public List<ISchoolSubject> schoolSubjects;

    public MySchoolSystem(){

        this.students = new ArrayList<Student>(0);
        this.schoolSubjects = new ArrayList<ISchoolSubject>(0);


    }

    public void addStudent(Student st){

        if(this.duplicateFinder(st.getIndex())){

            throw new IllegalArgumentException("student with that index already exists!");

        }

        this.students.add(st);

    }


    private boolean duplicateFinder(String index){

        for (Student s: students) {

            if(s.getIndex() == index){
                return true;
            }

        }

        return false;

    }




}
