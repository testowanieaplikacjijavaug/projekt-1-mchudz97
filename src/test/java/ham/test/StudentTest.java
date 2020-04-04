package ham.test;
import Isolation.NoteMock;
import Isolation.SchoolSubjectMock;
import SchoolSystem.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.text.ParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class StudentTest {


    private String nullConventer(String val){

        if(val == "null" || val ==  "Null"){

            return null;

        }

        return val;

    }

    @ParameterizedTest(name = "{index} Parameter {0} throws argument exception")
    @DisplayName("SchoolSystem.Student class constructor test if invalid name")
    @CsvSource({"null,Baa,111111", "'',Baa,111111", "A,Baa,111111", "aa,Baa,111111",
            "111,Baa,111111", "AmmA,Baa,111111", "Aaaaaaaaaaaaaaaaaaaaaaaaaaaa,Baa,111111"})
    public void studentConstructorTestException1(String name, String pername, String index){

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Student(nullConventer(name), pername, index));

    }

    @ParameterizedTest(name = "{index} Parameter {1} throws argument exception")
    @DisplayName("SchoolSystem.Student class constructor test if invalid pername")
    @CsvSource({"Baa,null,111111", "Baa,'',111111", "Baa,A,111111", "Baa,aa,111111",
            "Baa,111,111111","Baa,AmmA,111111", "Baa,Aaaaaaaaaaaaaaaaaaaaaaaaaaaa,111111"})
    public void studentConstructorTestException2(String name, String pername, String index){

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Student( name, nullConventer(pername), index));

    }

    @ParameterizedTest(name = "{index} Parameter {2} throws argument exception")
    @DisplayName("SchoolSystem.Student class constructor test if invalid index")
    @CsvSource({"Aaa,Baa,null", "Aaa,Baa,33333", "Aaa,Baa,3333333", "Aaa,Baa,AAAAAA", "Aaa,Baa,B53214!"})
    public void studentConstructorTestException3(String name, String pername, String index){

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Student(name, pername, nullConventer(index)));

    }

    @ParameterizedTest(name = "{index} Parameter {0} throws argument exception")
    @DisplayName("SchoolSystem.Student changeName method test if invalid name")
    @CsvSource({"A","AA", "aa", "Ma0", "''", "null"})
    public void studentChangeNameException(String val){

        Student st = new Student("Adam", "Beks", "111111");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.changeName(nullConventer(val)));

    }

    @Test
    @DisplayName("SchoolSystem.Student changeName method test if valid name")
    public void studentChangeNameTest(){
        Student st = new Student("Adam", "Beks", "111111");
        st.changeName("Janusz");

        assertThat(st.getName(), is("Janusz"));

    }

    @ParameterizedTest(name = "{index} Parameter {0} throws argument exception")
    @DisplayName("SchoolSystem.Student changePername method test if invalid name")
    @CsvSource({"A","AA", "aa", "Ma0", "''", "null"})
    public void studentChangePernameException(String val){

        Student st = new Student("Adam", "Beks", "111111");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.changePername(nullConventer(val)));

    }

    @Test
    @DisplayName("SchoolSystem.Student changePername method test if valid name")
    public void studentChangePernameTest(){

        Student st = new Student("Adam", "Beks", "111111");
        st.changePername("Nowak");

        assertThat(st.getPername(), is("Nowak"));

    }

    @ParameterizedTest(name = "{index} Parameter {0} throws argument exception")
    @DisplayName("SchoolSystem.Student changeIndex method test if invalid name")
    @CsvSource({"33333","3333333", "333a33", "''", "AAAAAA", "null"})
    public void studentChangeIndexException(String val){

        Student st = new Student("Adam", "Beks", "111111");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.changeIndex(nullConventer(val)));

    }

    @Test
    @DisplayName("SchoolSystem.Student changeIndex method test if valid name")
    public void studentChangeIndexTest(){

        Student st = new Student("Adam", "Beks", "111111");
        st.changeIndex("300003");

        assertThat(st.getIndex(), is("300003"));

    }

    @Test
    @DisplayName("SchoolSystem.Student addStudentNoteMethod")
    public void studentReadNoteTest() throws ParseException {

        Student st = new Student("Adam", "Beks", "111111");
        NoteMock nm = new NoteMock("byl niegrzeczny!");
        st.addStudentNote(nm);

        assertThat(st.notes.get(0).readNote(), is("byl niegrzeczny!"));

    }

    @Test
    @DisplayName("SchoolSystem.Student editStudentNoteMethod")
    public void studentEditNoteTest() throws ParseException {
        Student st = new Student("Adam", "Beks", "111111");
        NoteMock nm = new NoteMock("byl niegrzeczny!");
        st.addStudentNote(nm);
        st.editStudentNote(nm, "jednak byl grzeczny!");

        assertThat(st.notes.get(0).readNote(), is("jednak byl grzeczny!"));

    }
    @Test
    @DisplayName("SchoolSystem.Student editStudentNoteMethod throws exception")
    public void studentEditNoteThrowsExceptionTest() throws ParseException {

        Student st = new Student("Adam", "Beks", "111111");
        INote nm = new NoteMock("byl niegrzeczny!");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.editStudentNote(nm, "jednak byl grzeczny!"));

    }

    @Test
    @DisplayName("SchoolSystem.Student addSubject method test")
    public void studentAddSubjectTest(){

        ISchoolSubject ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Student st = new Student("Adam", "Beks", "111111");
        st.addSubject(ssm);

        assertThat(st.subjects.containsKey(ssm), is(true));

    }

    @Test
    @DisplayName("SchoolSystem.Student editSubject method test")
    public void studentEditSubjectTest(){

        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Student st = new Student("Adam", "Beks", "111111");
        st.addSubject(ssm);
        st.editSubject(ssm, SchoolSubjectEnum.Wychowanie_fizyczne);

        assertThat(ssm.getName(), is(SchoolSubjectEnum.Wychowanie_fizyczne.name()));

    }

    @Test
    @DisplayName("SchoolSystem.Student editSubject method if subject not found test ")
    public void studentEditSubjectTest2(){

        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Student st = new Student("Adam", "Beks", "111111");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.editSubject(ssm, SchoolSubjectEnum.Wychowanie_fizyczne));

    }

    @Test
    @DisplayName("SchoolSystem.Student removeSubject method if subject not found test ")
    public void studentRemoveSubjectTest1(){

        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Student st = new Student("Adam", "Beks", "111111");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.removeSubject(ssm));

    }

    @Test
    @DisplayName("SchoolSystem.Student removeSubject method test ")
    public void studentRemoveSubjectTest2(){

        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Student st = new Student("Adam", "Beks", "111111");
        st.addSubject(ssm);
        st.removeSubject(ssm);

        assertThat(st.subjects.containsKey(ssm), is(false));

    }

    @Test
    @DisplayName("SchoolSystem.Student addGrade method test if no subject")
    public void studentAddGradeIfNoSub(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade testGrade1 = Mockito.mock(Grade.class);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.addGrade(ssm, testGrade1));

    }


    @Test
    @DisplayName("SchoolSystem.Student addGrade method test")
    public void studentAddGrade(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        st.addSubject(ssm);
        Grade testGrade1 = Mockito.mock(Grade.class);
        st.addGrade(ssm, testGrade1);

        assertThat(st.subjects.get(ssm).get(0), is(testGrade1));

    }

    @Test
    @DisplayName("SchoolSystem.Student editGrade method when no subject in student test")
    public void studentEditGradeWithException1(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade testGrade1 = Mockito.mock(Grade.class);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.editGrade(ssm, testGrade1, 4.5f));

    }
    @Test
    @DisplayName("SchoolSystem.Student editGrade method when student hasnt that grade test")
    public void studentEditGradeWithException2(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        st.addSubject(ssm);
        Grade testGrade1 = Mockito.mock(Grade.class);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.editGrade(ssm, testGrade1, 4.5f));

    }

    @Test
    @DisplayName("SchoolSystem.Student editGrade method test")
    public void studentEditGrade(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        st.addSubject(ssm);
        Grade testGrade1 = Mockito.spy(Mockito.mock(Grade.class));
        doNothing().when(testGrade1).correctGradeTo(4.5f);
        st.addGrade(ssm, testGrade1);
        st.editGrade(ssm, testGrade1, 4.0f);

        verify(testGrade1).correctGradeTo(4.0f);

    }

    @Test
    @DisplayName("SchoolSystem.Student removeGrade method when no subject ")
    public void studentRemoveGradeWhenException1(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade testGrade1 = Mockito.mock(Grade.class);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.removeGrade(ssm, testGrade1));

    }

    @Test
    @DisplayName("SchoolSystem.Student removeGrade method when no grade ")
    public void studentRemoveGradeWhenException2(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        st.addSubject(ssm);
        Grade testGrade1 = Mockito.mock(Grade.class);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.removeGrade(ssm, testGrade1));

    }

    @Test
    @DisplayName("SchoolSystem.Student removeGrade method test")
    public void studentRemoveGrade(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        st.addSubject(ssm);
        Grade testGrade1 = Mockito.mock(Grade.class);
        st.addGrade(ssm, testGrade1);
        st.removeGrade(ssm, testGrade1);

        assertThat(st.subjects.get(ssm).contains(testGrade1), is(false));

    }

    @Test
    @DisplayName("Average student grade of not existing subject")
    public void studentAverageGradeOfEx(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade testGrade1 = Mockito.mock(Grade.class);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.averageGradeOf(ssm));

    }

    @ParameterizedTest(name = "{index}  avg test")
    @DisplayName("SchoolSystem.Student averageGradeOf method test ")
    @CsvFileSource(resources = "../../testData.csv")
    public void studentAverageGradeOf(float v1, float v2, float v3, float result){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        st.addSubject(ssm);
        Grade testGrade1 = Mockito.mock(Grade.class);
        Grade testGrade2 = Mockito.mock(Grade.class);
        Grade testGrade3 = Mockito.mock(Grade.class);
        when(testGrade1.getCurrentGrade()).thenReturn(v1);
        when(testGrade2.getCurrentGrade()).thenReturn(v2);
        when(testGrade3.getCurrentGrade()).thenReturn(v3);
        st.addGrade(ssm, testGrade1);
        st.addGrade(ssm, testGrade2);
        st.addGrade(ssm, testGrade3);

        assertThat(st.averageGradeOf(ssm), is(result));

    }

    @Test
    @DisplayName("Average student grade of all subjects")
    public void studentAverageGradeOfAll(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        ISchoolSubject  ssm2 = new SchoolSubjectMock(SchoolSubjectEnum.Wychowanie_fizyczne);
        ISchoolSubject  ssm3 = new SchoolSubjectMock(SchoolSubjectEnum.Chemia);
        Grade testGrade1 = Mockito.mock(Grade.class);
        Grade testGrade2 = Mockito.mock(Grade.class);
        Grade testGrade3 = Mockito.mock(Grade.class);
        Grade testGrade4 = Mockito.mock(Grade.class);
        Grade testGrade5 = Mockito.mock(Grade.class);
        Grade testGrade6 = Mockito.mock(Grade.class);
        when(testGrade1.getCurrentGrade()).thenReturn(2.0f);
        when(testGrade2.getCurrentGrade()).thenReturn(2.0f);
        when(testGrade3.getCurrentGrade()).thenReturn(3.0f);
        when(testGrade4.getCurrentGrade()).thenReturn(3.0f);
        when(testGrade5.getCurrentGrade()).thenReturn(4.0f);
        when(testGrade6.getCurrentGrade()).thenReturn(4.0f);

        st.addSubject(ssm);
        st.addSubject(ssm2);
        st.addSubject(ssm3);
        st.addGrade(ssm, testGrade1);
        st.addGrade(ssm, testGrade2);
        st.addGrade(ssm2, testGrade3);
        st.addGrade(ssm2, testGrade4);
        st.addGrade(ssm3, testGrade5);
        st.addGrade(ssm3, testGrade6);

        assertThat(st.averageGradeOfAll(), is(3.0f));

    }

}
