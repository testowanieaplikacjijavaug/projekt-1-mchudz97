package ju5.test;

import SchoolSystem.MySchoolSystem;
import SchoolSystem.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SchoolSystemTest {

    private MySchoolSystem ss;


    @BeforeEach
    public void reset(){

        ss = new MySchoolSystem();

    }


    @Test
    @DisplayName("")
    public void addStudentTest(){

        Student st = Mockito.mock(Student.class);

        ss.addStudent(st);

        Assertions.assertEquals(st, ss.students.get(st));




    }



}
