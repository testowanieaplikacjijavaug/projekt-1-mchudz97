package ju5.test;

import Isolation.NoteMock;
import Isolation.SchoolSubjectMock;
import SchoolSystem.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.text.ParseException;

import static org.mockito.Mockito.*;

public class StudentTest {


    private String nullConventer(String val){

        if(val == "null" || val ==  "Null"){

            return null;

        }

        return val;

    }


//    public Student studentTest
    public Grade grade;
    public Student student;
    MySchoolSystem mss;
    @BeforeEach
    void setup(){

        grade = Grade.of(4.0f);

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

        Assertions.assertEquals("Janusz", st.getName());
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

        Assertions.assertEquals("Nowak", st.getPername());
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

        Assertions.assertEquals("300003", st.getIndex());
    }

    @Test
    @DisplayName("SchoolSystem.Student addStudentNoteMethod")
    public void studentReadNoteTest() throws ParseException {
        Student st = new Student("Adam", "Beks", "111111");
        NoteMock nm = new NoteMock("byl niegrzeczny!", "11-10-2010");
        st.addStudentNote(nm);


        Assertions.assertEquals("byl niegrzeczny! 11-10-2010", st.notes.get(0).readNote());

    }

    @Test
    @DisplayName("SchoolSystem.Student editStudentNoteMethod")
    public void studentEditNoteTest() throws ParseException {
        Student st = new Student("Adam", "Beks", "111111");
        NoteMock nm = new NoteMock("byl niegrzeczny!", "11-10-2010");
        st.addStudentNote(nm);
        st.editStudentNote(0, "jednak byl grzeczny!");

        Assertions.assertEquals("jednak byl grzeczny! 11-10-2010", st.notes.get(0).readNote());



    }
    @Test
    @DisplayName("SchoolSystem.Student editStudentNoteMethod throws exception")
    public void studentEditNoteThrowsExceptionTest() {


        Student st = new Student("Adam", "Beks", "111111");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.editStudentNote(0, "jednak byl grzeczny!"));

    }

    @Test
    @DisplayName("SchoolSystem.Student addSubject method test")
    public void studentAddSubjectTest(){

        ISchoolSubject ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Student st = new Student("Adam", "Beks", "111111");
        st.addSubject(ssm);

        Assertions.assertTrue(st.subjects.containsKey(ssm));


    }

    @Test
    @DisplayName("SchoolSystem.Student editSubject method test")
    public void studentEditSubjectTest(){

        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Student st = new Student("Adam", "Beks", "111111");
        st.addSubject(ssm);
        st.editSubject(ssm, SchoolSubjectEnum.Wychowanie_fizyczne);

        Assertions.assertEquals(SchoolSubjectEnum.Wychowanie_fizyczne.name(), ssm.getName());



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

        Assertions.assertFalse(st.subjects.containsKey(ssm));



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

        Assertions.assertEquals(testGrade1, st.subjects.get(ssm).get(0));




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
        Assertions.assertFalse(st.subjects.get(ssm).contains(testGrade1));


    }






}
