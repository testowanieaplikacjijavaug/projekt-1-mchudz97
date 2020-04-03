package SchoolSystem;

import java.util.Date;

public class Grade {

    private float grade;
    private float correctedGrade;
    private Date dateWhenAdded;
    private Date dateWhenCorrected;

    private static final float AVAILABLE_GRADES[] = {2.0f, 2.5f, 3.0f, 3.5f, 4.0f, 4.5f, 5.0f};


    public static final Grade of(float val){

        if(!isCorrect(val)){

            throw new IllegalArgumentException("SchoolSystem.Grade value is out of range.");

        }

        return new Grade(val);

    }

    private Grade(float val){

        this.grade = val;
        dateWhenAdded = new Date(System.currentTimeMillis());

    }

    private static boolean isCorrect(float val){

        for(int i=0; i<AVAILABLE_GRADES.length; i++){

            if( AVAILABLE_GRADES[i] == val){
                return true;
            }

        }

        return false;
    }

}