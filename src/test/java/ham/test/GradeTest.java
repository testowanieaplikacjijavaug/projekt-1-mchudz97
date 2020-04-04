package ham.test;

import SchoolSystem.Grade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GradeTest {


    @ParameterizedTest(name = "{index} Parameter {0} throws argument exception")
    @DisplayName("SchoolSystem.Grade class of method test if invalid value")
    @CsvSource({"0", "10", "-3", "1", "2.25", "5.5"})
    public void gradeOfMethodTestException(String val){

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> SchoolSystem.Grade.of(Float.parseFloat(val)));

    }

    @ParameterizedTest(name = "{index} Parameter {0} throws argument exception")
    @DisplayName("SchoolSystem.Grade class correctGrade method test if invalid value")
    @CsvSource({"0", "10", "-3", "1", "2.25", "5.5"})
    public void correctGradeMethodTestException(String val){

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> SchoolSystem.Grade.of(3).correctGradeTo(Float.parseFloat(val)));

    }

    @Test
    public void getCurrentGradeMethodTest1(){

        assertThat(Grade.of(3.0f).getCurrentGrade(), is(3.0f));

    }

    @Test
    public void getCurrentGradeMethodTest2(){

        Grade gr = Grade.of(3.0f);
        gr.correctGradeTo(3.5f);

        assertThat(gr.getCurrentGrade(), is(3.5f));

    }


}
