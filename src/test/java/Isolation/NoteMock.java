package Isolation;

import SchoolSystem.INote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteMock implements INote {

    private String description;



    public NoteMock(String description){

        this.description = description;


    }

    @Override
    public String readNote() {
        return description;
    }

    @Override
    public void editNote(String description) {
        this.description = description;
    }
}
