package Isolation;

import SchoolSystem.INote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteMock implements INote {

    private String description;
    private SimpleDateFormat parser;
    private Date fakeDate;


    public NoteMock(String description, String date) throws ParseException {

        this.description = description;
        parser = new SimpleDateFormat("dd-mm-yyyy");
        fakeDate = parser.parse(date);

    }

    @Override
    public String readNote() {
        return description +" " + fakeDate.toString();
    }

    @Override
    public void editNote(String description) {

    }
}
