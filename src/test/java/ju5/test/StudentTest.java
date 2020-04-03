package ju5.test;

import Isolation.NoteMock;
import SchoolSystem.Grade;
import SchoolSystem.MySchoolSystem;
import SchoolSystem.Student;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;

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


        Assertions.assertEquals("byl niegrzeczny! Mon Jan 11 00:10:00 CET 2010", st.notes.get(0).readNote());

    }

    @Test
    @DisplayName("SchoolSystem.Student editStudentNoteMethod")
    public void studentEditNoteTest() throws ParseException {
        Student st = new Student("Adam", "Beks", "111111");
        NoteMock nm = new NoteMock("byl niegrzeczny!", "11-10-2010");
        st.addStudentNote(nm);
        st.editStudentNote(0, "jednak byl grzeczny!");

        Assertions.assertEquals("jednak byl grzeczny! Mon Jan 11 00:10:00 CET 2010", st.notes.get(0).readNote());



    }
    @Test
    @DisplayName("SchoolSystem.Student editStudentNoteMethod throws exception")
    public void studentEditNoteThrowsExceptionTest() {


        Student st = new Student("Adam", "Beks", "111111");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> st.editStudentNote(0, "jednak byl grzeczny!"));

    }








}
