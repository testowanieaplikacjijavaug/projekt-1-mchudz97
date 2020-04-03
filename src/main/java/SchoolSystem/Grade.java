package SchoolSystem;

import java.util.Date;

public class Grade {

    private float grade;
    private float correctedGrade;
    private Date dateWhenAdded;
    private Date dateWhenCorrected;



    public static final Grade of(float val){

        if(val > 5.0 || val<2.0){

            throw new IllegalArgumentException("SchoolSystem.Grade value is out of range.");

        }

        return new Grade(val);

    }

    private Grade(float val){

        this.grade = val;
        dateWhenAdded = new Date(System.currentTimeMillis());

    }

}