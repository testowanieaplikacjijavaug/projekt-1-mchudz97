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

    public float avgOfStudentSubject(Student st, ISchoolSubject subject){

        if(!this.students.contains(st)){

            throw new IllegalArgumentException("that student doesnt exist in system");

        }

        return st.averageGradeOf(subject);

    }

    public void editStudentsNote(Student st, INote note, String description){

        if(!this.students.contains(st)){

            throw new IllegalArgumentException("that student doesnt exist in system");

        }

        st.editStudentNote(note, description);

    }

    public void addNoteToStudent(Student st, INote note){

        if(!this.students.contains(st)){

            throw new IllegalArgumentException("that student doesnt exist in system");

        }

        st.addStudentNote(note);

    }


    public void removeStudentsGrade(Student st, ISchoolSubject subject, Grade g){

        if(!this.students.contains(st)){

            throw new IllegalArgumentException("that student doesnt exist in system");

        }

        st.removeGrade(subject, g);


    }

    public void editStudentsGrade(Student st, ISchoolSubject subject, Grade g, float val){

        if(!this.students.contains(st)){

            throw new IllegalArgumentException("that student doesnt exist in system");

        }

        st.editGrade(subject, g, val);

    }

    public void addGradeToStudent(Student st, ISchoolSubject subject, Grade g){

        if(!this.students.contains(st)){

            throw new IllegalArgumentException("that student doesnt exist in system");

        }

        st.addGrade(subject, g);

    }


    public void removeStudentsSubject(Student st, ISchoolSubject subject){

        if(!this.students.contains(st)){

            throw new IllegalArgumentException("that student doesnt exist in system");

        }

        st.removeSubject(subject);

    }

    public void editStudentsSubject(Student st, ISchoolSubject subject, SchoolSubjectEnum name){

        if(!this.students.contains(st)){

            throw new IllegalArgumentException("that student doesnt exist in system");

        }

        st.editSubject(subject, name);


    }

    public void addSubjectToStudent(Student st, ISchoolSubject subject){

        if(!this.students.contains(st)){

            throw new IllegalArgumentException("that student doesnt exist in system");

        }

        st.addSubject(subject);

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
