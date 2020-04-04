package SchoolSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student {

    private String name;
    private String pername;
    private String index;

    public List<INote> notes;
    public HashMap<ISchoolSubject, ArrayList<Grade>> subjects;

    public Student(String name, String pername, String index){

        if(!name.matches("[A-Z][a-z]{1,16}") || !pername.matches("[A-Z][a-z]{1,16}")
        || !index.matches("[0-9]{6}")){

            throw new IllegalArgumentException("There is some wrong data!");

        }
        this.name = name;
        this.index = index;
        this.pername = pername;
        this.notes = new ArrayList<INote>(0);
        this.subjects = new HashMap<ISchoolSubject, ArrayList<Grade>>(0);

    }

    public float averageGradeOfAll(){

        float avgAll = 0;

        for (ISchoolSubject iss : this.subjects.keySet()) {

            avgAll += this.averageGradeOf(iss);

        }

        return avgAll/this.subjects.keySet().size();

    }


    public float averageGradeOf(ISchoolSubject subject){

        if(!this.subjects.containsKey(subject)){

            throw new IllegalArgumentException("invalid subject!");

        }

        float avg = 0;

        for (Grade g: this.subjects.get(subject)) {

            avg += g.getCurrentGrade();

        }

        return avg/this.subjects.get(subject).size();

    }

    public void removeGrade(ISchoolSubject subject, Grade grade){

        if(!this.subjects.containsKey(subject)){

            throw new IllegalArgumentException("invalid subject!");

        }


        if(!this.subjects.get(subject).contains(grade)){

            throw new IllegalArgumentException("grade not found!");

        }

        this.subjects.get(subject).remove(grade);


    }


    public void editGrade(ISchoolSubject subject, Grade grade, float val){

        if(!this.subjects.containsKey(subject)){

            throw new IllegalArgumentException("invalid subject!");

        }


        if(!this.subjects.get(subject).contains(grade)){

            throw new IllegalArgumentException("Grade not found!");

        }

        grade.correctGradeTo(val);



    }

    public void addGrade(ISchoolSubject subject, Grade grade){

        if(!this.subjects.containsKey(subject)){

            throw new IllegalArgumentException("subject not found!");

        }

        this.subjects.get(subject).add(grade);

    }

    public void editSubject(ISchoolSubject subject, SchoolSubjectEnum name){

        for (ISchoolSubject iss: this.subjects.keySet()
        ) {

            if(subject == iss){

                iss.setName(name);
                return;

            }

        }

        throw new IllegalArgumentException("subject not found!");

    }


    public void removeSubject(ISchoolSubject subject){

        for (ISchoolSubject iss: this.subjects.keySet()
        ) {

            if(subject == iss){

                this.subjects.remove(iss);
                return;

            }

        }

        throw new IllegalArgumentException("subject not found!");

    }

    public void addSubject(ISchoolSubject subject){


        this.subjects.put(subject, new ArrayList<Grade>(0));

    }

    public void addStudentNote(INote note){

        this.notes.add(note);

    }

    public void editStudentNote(INote note, String description){

        if(!this.notes.contains(note)){
            throw new IllegalArgumentException("That note doesn't exists!");
        }

        note.editNote(description);

    }

    public void changeName(String name){

        if(!name.matches("[A-Z][a-z]{1,16}")){

            throw new IllegalArgumentException("Wrong name!");

        }

        this.name = name;

    }

    public void changePername(String pername){

        if(!pername.matches("[A-Z][a-z]{1,16}")){

            throw new IllegalArgumentException("Wrong pername!");

        }

        this.pername = pername;

    }

    public void changeIndex(String index){

        if(!index.matches("[0-9]{6}")){

            throw new IllegalArgumentException("Wrong index!");

        }

        this.index = index;

    }

    public String getName(){

        return this.name;

    }

    public String getPername(){

        return this.pername;

    }

    public String getIndex(){

        return this.index;

    }

}
