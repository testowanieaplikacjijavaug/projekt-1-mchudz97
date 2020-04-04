package ju5.test;

import SchoolSystem.MySchoolSystem;
import SchoolSystem.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class SchoolSystemTest {

    private MySchoolSystem ss;


    @BeforeEach
    public void reset(){

        ss = new MySchoolSystem();

    }


    @Test
    @DisplayName("simple add student MySchoolSystem test")
    public void addStudentTest(){

        Student st = Mockito.mock(Student.class);

        ss.addStudent(st);

        Assertions.assertEquals(st, ss.students.get(st));




    }
    @Test
    @DisplayName("Student index must be unique. In other case it will throw exception")
    public void addStudentWithException(){

        Student st1 = Mockito.mock(Student.class);
        Student st2 = Mockito.mock(Student.class);
        when(st1.getIndex()).thenReturn("111111");
        when(st2.getIndex()).thenReturn("111111");

        ss.addStudent(st1);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ss.addStudent(st2));


    }



}
