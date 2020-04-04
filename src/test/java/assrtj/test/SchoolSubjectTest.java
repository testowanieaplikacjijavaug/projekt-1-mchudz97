package assrtj.test;

import SchoolSystem.SchoolSubject;
import SchoolSystem.SchoolSubjectEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SchoolSubjectTest {


    @Test
    @DisplayName("SchoolSystem.SchoolSubject getter test")
    public void getterTest(){

        SchoolSubject schoolSubject = new SchoolSubject(SchoolSubjectEnum.Wychowanie_fizyczne);

        assertThat(schoolSubject.getName()).isEqualTo(SchoolSubjectEnum.Wychowanie_fizyczne.name());

    }

    @Test
    @DisplayName("SchoolSystem.SchoolSubject setter test")
    public void setterTest(){

        SchoolSubject schoolSubject = new SchoolSubject(SchoolSubjectEnum.Wychowanie_fizyczne);
        schoolSubject.setName(SchoolSubjectEnum.Biologia);

        assertThat(schoolSubject.getName()).isEqualTo(SchoolSubjectEnum.Biologia.name());

    }

}
