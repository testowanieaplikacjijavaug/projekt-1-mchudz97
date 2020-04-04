package SchoolSystem;

public class Note implements INote {

    private String description;

    public static Note of(String desc){

        if(desc == null && desc == ""){

            throw new IllegalArgumentException("fescription got blank or null value!");

        }

        return new Note(desc);

    }

    private Note(String desc){
        this.description = desc;
    }

    @Override
    public String readNote() {
        return null;
    }

    @Override
    public void editNote(String description) {



    }
}
