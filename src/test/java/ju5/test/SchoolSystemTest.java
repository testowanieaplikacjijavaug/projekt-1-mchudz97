package ju5.test;

import Isolation.SchoolSubjectMock;
import SchoolSystem.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

    @Test
    @DisplayName("When we want to remove not existing student in system it throws exception")
    public void removeStudentExceptionTest(){

        Student testStudent1 = Mockito.mock(Student.class);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ss.removeStudent(testStudent1));


    }

    @Test
    @DisplayName("When student exists in system he will be removed")
    public void removeStudentTest(){

        Student testStudent1 = Mockito.mock(Student.class);
        ss.addStudent(testStudent1);
        ss.removeStudent(testStudent1);
        Assertions.assertFalse(ss.students.contains(testStudent1));

    }

    @Test
    @DisplayName("Adding subject to not existing student in system")
    public void addSubjectToStudentTest(){

        Student testStudent = Mockito.mock(Student.class);

        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ss.addSubjectToStudent(testStudent, iss));

    }

    @Test
    @DisplayName("Adding subject to not existing student")
    public void addSubjectToStudentTest2(){

        Student testStudent = Mockito.spy(Mockito.mock(Student.class));
        ss.addStudent(testStudent);
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        doNothing().when(testStudent).addSubject(iss);
        ss.addSubjectToStudent(testStudent, iss);

        verify(testStudent).addSubject(iss);

    }

    @Test
    @DisplayName("Editing subject of not existing in system student will cause exception")
    public void editStudentsSubjectEx(){

        Student testStudent = Mockito.mock(Student.class);
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ss.editStudentsSubject(testStudent, iss, SchoolSubjectEnum.Wychowanie_fizyczne));


    }
    @Test
    @DisplayName("Editing subject of existing in system student")
    public void editStudentsSubject(){

        Student testStudent = Mockito.spy(Mockito.mock(Student.class));
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        doNothing().when(testStudent).editSubject(iss, SchoolSubjectEnum.Wychowanie_fizyczne);
        ss.addStudent(testStudent);
        ss.addSubjectToStudent(testStudent, iss);
        ss.editStudentsSubject(testStudent, iss, SchoolSubjectEnum.Wychowanie_fizyczne);

        verify(testStudent).editSubject(iss, SchoolSubjectEnum.Wychowanie_fizyczne);

    }

    @Test
    @DisplayName("Removing subject of not existing in system student will cause exception")
    public void removeStudentsSubjectEx(){

        Student testStudent = Mockito.mock(Student.class);
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ss.removeStudentsSubject(testStudent, iss));


    }

    @Test
    @DisplayName("Removing subject of existing in system student")
    public void removeStudentsSubject(){

        Student testStudent = Mockito.spy(Mockito.mock(Student.class));
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        doNothing().when(testStudent).removeSubject(iss);
        ss.addStudent(testStudent);
        ss.addSubjectToStudent(testStudent, iss);
        ss.removeStudentsSubject(testStudent, iss);

        verify(testStudent).removeSubject(iss);

    }

    @Test
    @DisplayName("Adding grade to not existing in system student")
    public void addGradeToStudentTestEx(){

        Student testStudent = Mockito.mock(Student.class);
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade gmock = Mockito.mock(Grade.class);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ss.addGradeToStudent(testStudent, iss, gmock));



    }

    @Test
    @DisplayName("Adding grade to existing in system student")
    public void addGradeToStudentTest(){

        Student testStudent = Mockito.spy(Mockito.mock(Student.class));
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade gmock = Mockito.mock(Grade.class);
        ss.addStudent(testStudent);
        doNothing().when(testStudent).addGrade(iss, gmock);
        ss.addGradeToStudent(testStudent, iss, gmock);

        verify(testStudent).addGrade(iss, gmock);

    }


}
