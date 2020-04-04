package assrtj.test;
import SchoolSystem.Grade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.*;
public class GradeTest {





    @ParameterizedTest(name = "{index} Parameter {0} throws argument exception")
    @DisplayName("SchoolSystem.Grade class of method test if invalid value")
    @CsvSource({"0", "10", "-3", "1", "2.25", "5.5"})
    public void gradeOfMethodTestException(String val){

        assertThatThrownBy(() -> {SchoolSystem.Grade.of(Float.parseFloat(val));}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("SchoolSystem.Grade value is out of range.");

    }

    @ParameterizedTest(name = "{index} Parameter {0} throws argument exception")
    @DisplayName("SchoolSystem.Grade class correctGrade method test if invalid value")
    @CsvSource({"0", "10", "-3", "1", "2.25", "5.5"})
    public void correctGradeMethodTestException(String val){


        assertThatThrownBy(() -> {SchoolSystem.Grade.of(3).correctGradeTo(Float.parseFloat(val));}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("SchoolSystem.Grade value is out of range.");

    }

    @Test
    public void getCurrentGradeMethodTest1(){

        assertThat(Grade.of(3.0f).getCurrentGrade()).isEqualTo(3.0f);

    }

    @Test
    public void getCurrentGradeMethodTest2(){

        Grade gr = Grade.of(3.0f);
        gr.correctGradeTo(3.5f);

        assertThat(gr.getCurrentGrade()).isEqualTo(3.5f);

    }





}
