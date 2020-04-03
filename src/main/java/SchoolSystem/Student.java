package SchoolSystem;

public class Student {

    private String name;
    private String pername;
    private String index;

    public Student(String name, String pername, String index){

        if(!name.matches("[A-Z][a-z]{1,16}") || !pername.matches("[A-Z][a-z]{1,16}")
        || !index.matches("[0-9]{6}")){

            throw new IllegalArgumentException("There is some wrong data!");

        }

    }


    public void changeName(String name){

        if(!name.matches("[A-Z][a-z]{1,16}")){

            throw new IllegalArgumentException("Wrong name!");

        }

        this.name = name;

    }

    public void changePername(String pername){

        if(!pername.matches("[A-Z][a-z]{1,16}")){

            throw new IllegalArgumentException("Wrong name!");

        }

        this.pername = pername;

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
