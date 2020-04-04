package ju5.test;

import SchoolSystem.Grade;
import SchoolSystem.MySchoolSystem;
import SchoolSystem.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

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

        Assertions.assertTrue( ss.students.contains(st));


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

    @Test
    @DisplayName("Editing full student variables from MySchoolSystem class")
    public void editStudent1(){

        Student testStudent = Mockito.spy(Mockito.mock(Student.class));

        doNothing().when(testStudent).changeName("Oskar");
        doNothing().when(testStudent).changePername("Rak");
        doNothing().when(testStudent).changeIndex("111111");




        ss.editStudent(testStudent, "111111","Oskar", "Rak");

        verify(testStudent).changeName("Oskar");
        verify(testStudent).changePername("Rak");
        verify(testStudent).changeIndex("111111");



    }
    @Test
    @DisplayName("Editing throws exception when changed index already exists")
    public void editStudentThrowsException(){

        Student testStudent1 = Mockito.mock(Student.class);
        Student testStudent2 = Mockito.mock(Student.class);
        when(testStudent1.getIndex()).thenReturn("111111");
        when(testStudent2.getIndex()).thenReturn("111112");
        ss.addStudent(testStudent1);

        ss.addStudent(testStudent2);


            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> ss.editStudent(testStudent2, "111111", "Jan", "Janusz"));


    }




}
