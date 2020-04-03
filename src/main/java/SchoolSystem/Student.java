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

}
