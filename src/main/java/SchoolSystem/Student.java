package SchoolSystem;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private String pername;
    private String index;

    public List<INote> notes;

    public Student(String name, String pername, String index){

        if(!name.matches("[A-Z][a-z]{1,16}") || !pername.matches("[A-Z][a-z]{1,16}")
        || !index.matches("[0-9]{6}")){

            throw new IllegalArgumentException("There is some wrong data!");

        }
        this.name = name;
        this.index = index;
        this.pername = pername;
        this.notes = new ArrayList<INote>(0);

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
