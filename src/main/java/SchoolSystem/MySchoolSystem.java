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

    public void removeStudent(Student student){

        if(!this.students.contains(student)){

            throw new IllegalArgumentException("that student doesn't exists in system!");

        }

        this.students.remove(student);

    }


    public void addStudent(Student st){

        if(this.duplicateFinder(st.getIndex())){

            throw new IllegalArgumentException("student with that index already exists!");

        }

        this.students.add(st);

    }

    public void editStudent(Student student, String index, String name, String pername){

        if(this.duplicateFinder(index)){

            throw new IllegalArgumentException("student with that index already exists!");

        }

        student.changeIndex(index);
        student.changeName(name);
        student.changePername(pername);

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
