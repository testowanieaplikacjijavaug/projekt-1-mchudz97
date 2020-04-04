package ju5.test;

import SchoolSystem.SchoolSubject;
import SchoolSystem.SchoolSubjectEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SchoolSubjectTest {

    @Test
    @DisplayName("SchoolSystem.SchoolSubject getter test")
    public void getterTest(){

        SchoolSubject schoolSubject = new SchoolSubject(SchoolSubjectEnum.Wychowanie_fizyczne);

        Assertions.assertEquals(SchoolSubjectEnum.Wychowanie_fizyczne, schoolSubject.getName());

    }

    @Test
    @DisplayName("SchoolSystem.SchoolSubject setter test")
    public void getterTest(){

        SchoolSubject schoolSubject = new SchoolSubject(SchoolSubjectEnum.Wychowanie_fizyczne);
        schoolSubject.setName(SchoolSubjectEnum.Biologia);
        Assertions.assertEquals(SchoolSubjectEnum.Biologia, schoolSubject.getName());

    }






}
