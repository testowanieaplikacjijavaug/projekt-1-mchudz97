package ju5.test;

import SchoolSystem.Grade;
import SchoolSystem.MySchoolSystem;
import SchoolSystem.Student;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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




}
