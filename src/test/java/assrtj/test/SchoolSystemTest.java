package assrtj.test;
import Isolation.NoteMock;
import Isolation.SchoolSubjectMock;
import SchoolSystem.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.*;

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

        assertThat(ss.students.contains(st)).isTrue();

    }

    @Test
    @DisplayName("Student index must be unique. In other case it will throw exception")
    public void addStudentWithException(){

        Student st1 = Mockito.mock(Student.class);
        Student st2 = Mockito.mock(Student.class);
        when(st1.getIndex()).thenReturn("111111");
        when(st2.getIndex()).thenReturn("111111");

        ss.addStudent(st1);

        assertThatThrownBy(() -> {ss.addStudent(st2);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("student with that index already exists!");

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

        assertThatThrownBy(() -> {ss.editStudent(testStudent2, "111111", "Jan", "Janusz");}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("student with that index already exists!");

    }

    @Test
    @DisplayName("When we want to remove not existing student in system it throws exception")
    public void removeStudentExceptionTest(){

        Student testStudent1 = Mockito.mock(Student.class);

        assertThatThrownBy(() -> {ss.removeStudent(testStudent1);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("that student doesn't exists in system!");

    }

    @Test
    @DisplayName("When student exists in system he will be removed")
    public void removeStudentTest(){

        Student testStudent1 = Mockito.mock(Student.class);
        ss.addStudent(testStudent1);
        ss.removeStudent(testStudent1);

        assertThat(ss.students.contains(testStudent1)).isFalse();
    }

    @Test
    @DisplayName("Adding subject to not existing student in system")
    public void addSubjectToStudentTest(){

        Student testStudent = Mockito.mock(Student.class);
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);

        assertThatThrownBy(() -> {ss.addSubjectToStudent(testStudent, iss);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("that student doesnt exist in system");

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

        assertThatThrownBy(() -> {ss.editStudentsSubject(testStudent, iss, SchoolSubjectEnum.Wychowanie_fizyczne);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("that student doesnt exist in system");

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

        assertThatThrownBy(() -> {ss.removeStudentsSubject(testStudent, iss);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("that student doesnt exist in system");

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

        assertThatThrownBy(() -> {ss.addGradeToStudent(testStudent, iss, gmock);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("that student doesnt exist in system");

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


    @Test
    @DisplayName("Editing subject of not existing in system student will cause exception")
    public void editStudentsGradeEx(){

        Student testStudent = Mockito.mock(Student.class);
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade gmock = Mockito.mock(Grade.class);

        assertThatThrownBy(() -> {ss.editStudentsGrade(testStudent, iss, gmock, 4.5f);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("that student doesnt exist in system");

    }

    @Test
    @DisplayName("Editing grade of existing in system student")
    public void editStudentsGrade(){

        Student testStudent = Mockito.spy(Mockito.mock(Student.class));
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade gmock = Mockito.mock(Grade.class);
        doNothing().when(testStudent).editGrade(iss, gmock, 4.5f);
        ss.addStudent(testStudent);
        ss.editStudentsGrade(testStudent, iss, gmock, 4.5f);

        verify(testStudent).editGrade(iss, gmock, 4.5f);

    }

    @Test
    @DisplayName("Removing grade of not existing student")
    public void removeStudentsGradeEx(){

        Student testStudent = Mockito.mock(Student.class);
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade gmock = Mockito.mock(Grade.class);

        assertThatThrownBy(() -> {ss.removeStudentsGrade(testStudent, iss, gmock);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("that student doesnt exist in system");

    }

    @Test
    @DisplayName("Removing grade of existing student")
    public void removeStudentsGrade(){

        Student testStudent = Mockito.spy(Mockito.mock(Student.class));
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade gmock = Mockito.mock(Grade.class);
        ss.addStudent(testStudent);
        doNothing().when(testStudent).removeGrade(iss, gmock);
        ss.removeStudentsGrade(testStudent, iss, gmock);

        verify(testStudent).removeGrade(iss, gmock);

    }

    @Test
    @DisplayName("Adding note to not existing student")
    public void addNoteToStudentEx() {

        Student testStudent = Mockito.mock(Student.class);
        INote noteMock = new NoteMock("hello");

        assertThatThrownBy(() -> {ss.addNoteToStudent(testStudent, noteMock);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("that student doesnt exist in system");

    }

    @Test
    @DisplayName("Adding note to existing in system student")
    public void addNoteToStudent() {

        Student testStudent = Mockito.spy(Mockito.mock(Student.class));
        INote noteMock = new NoteMock("hello");
        ss.addStudent(testStudent);
        doNothing().when(testStudent).addStudentNote(noteMock);
        ss.addNoteToStudent(testStudent, noteMock);

        verify(testStudent).addStudentNote(noteMock);

    }

    @Test
    @DisplayName("Editing note of not existing student")
    public void editStudentsNoteEx(){

        Student testStudent = Mockito.mock(Student.class);
        INote noteMock = new NoteMock("hello");

        assertThatThrownBy(() -> {ss.editStudentsNote(testStudent, noteMock, "edit");}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("that student doesnt exist in system");

    }

    @Test
    @DisplayName("Editing note of existing in system student")
    public void editStudentsNote(){

        Student testStudent = Mockito.spy(Mockito.mock(Student.class));
        INote noteMock = new NoteMock("hello");
        ss.addStudent(testStudent);
        ss.addNoteToStudent(testStudent, noteMock);
        doNothing().when(testStudent).editStudentNote(noteMock, "edit");
        ss.editStudentsNote(testStudent, noteMock, "edit");

        verify(testStudent).editStudentNote(noteMock, "edit");

    }

    @Test
    @DisplayName("Average grade of subject from non existing student will throw exception")
    public void avgOfStudentSubjectEx(){

        Student testStudent = Mockito.mock(Student.class);
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Chemia);

        assertThatThrownBy(() -> {ss.avgOfStudentSubject(testStudent, iss);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("that student doesnt exist in system");

    }

    @Test
    @DisplayName("Average grade of subject from existing student ")
    public void avgOfStudentSubject(){

        Student testStudent = Mockito.spy(Mockito.mock(Student.class));
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Chemia);
        ss.addStudent(testStudent);
        doReturn(4.5f).when(testStudent).averageGradeOf(iss);

        assertThat(ss.avgOfStudentSubject(testStudent, iss)).isEqualTo(4.5f);

    }

    @Test
    @DisplayName("Average grade of all subjects from non existing student will throw exception")
    public void avgOfAllStudentSubjectEx(){

        Student testStudent = Mockito.mock(Student.class);
        ISchoolSubject iss = new SchoolSubjectMock(SchoolSubjectEnum.Chemia);

        assertThatThrownBy(() -> {ss.avgOfAllStudentSubjects(testStudent);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("that student doesnt exist in system");

    }

    @Test
    @DisplayName("Average grade of all subjects from existing student ")
    public void avgOfAllStudentSubject(){

        Student testStudent = Mockito.spy(Mockito.mock(Student.class));
        ss.addStudent(testStudent);
        doReturn(4.5f).when(testStudent).averageGradeOfAll();

        assertThat(ss.avgOfAllStudentSubjects(testStudent)).isEqualTo(4.5f);

    }

}
