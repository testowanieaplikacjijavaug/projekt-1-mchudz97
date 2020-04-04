package assrtj.test;

import Isolation.NoteMock;
import Isolation.SchoolSubjectMock;
import SchoolSystem.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.text.ParseException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

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

        assertThatThrownBy(() -> {new Student(nullConventer(name), pername, index);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("There is some wrong data!");

    }


    @ParameterizedTest(name = "{index} Parameter {1} throws argument exception")
    @DisplayName("SchoolSystem.Student class constructor test if invalid pername")
    @CsvSource({"Baa,null,111111", "Baa,'',111111", "Baa,A,111111", "Baa,aa,111111",
            "Baa,111,111111","Baa,AmmA,111111", "Baa,Aaaaaaaaaaaaaaaaaaaaaaaaaaaa,111111"})
    public void studentConstructorTestException2(String name, String pername, String index){

        assertThatThrownBy(() -> {new Student( name, nullConventer(pername), index);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("There is some wrong data!");

    }



    @ParameterizedTest(name = "{index} Parameter {2} throws argument exception")
    @DisplayName("SchoolSystem.Student class constructor test if invalid index")
    @CsvSource({"Aaa,Baa,null", "Aaa,Baa,33333", "Aaa,Baa,3333333", "Aaa,Baa,AAAAAA", "Aaa,Baa,B53214!"})
    public void studentConstructorTestException3(String name, String pername, String index){

        assertThatThrownBy(() -> {new Student(name, pername, nullConventer(index));}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("There is some wrong data!");

    }

    @ParameterizedTest(name = "{index} Parameter {0} throws argument exception")
    @DisplayName("SchoolSystem.Student changeName method test if invalid name")
    @CsvSource({"A","AA", "aa", "Ma0", "''", "null"})
    public void studentChangeNameException(String val){

        Student st = new Student("Adam", "Beks", "111111");

        assertThatThrownBy(() -> {st.changeName(nullConventer(val));}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("Wrong name!");

    }

    @Test
    @DisplayName("SchoolSystem.Student changeName method test if valid name")
    public void studentChangeNameTest(){
        Student st = new Student("Adam", "Beks", "111111");
        st.changeName("Janusz");

        assertThat(st.getName()).isEqualTo("Janusz");

    }

    @ParameterizedTest(name = "{index} Parameter {0} throws argument exception")
    @DisplayName("SchoolSystem.Student changePername method test if invalid name")
    @CsvSource({"A","AA", "aa", "Ma0", "''", "null"})
    public void studentChangePernameException(String val){

        Student st = new Student("Adam", "Beks", "111111");

        assertThatThrownBy(() -> {st.changePername(nullConventer(val));}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("Wrong pername!");

    }

    @Test
    @DisplayName("SchoolSystem.Student changePername method test if valid name")
    public void studentChangePernameTest(){
        Student st = new Student("Adam", "Beks", "111111");
        st.changePername("Nowak");

        assertThat(st.getPername()).isEqualTo("Nowak");

    }

    @ParameterizedTest(name = "{index} Parameter {0} throws argument exception")
    @DisplayName("SchoolSystem.Student changeIndex method test if invalid name")
    @CsvSource({"33333","3333333", "333a33", "''", "AAAAAA", "null"})
    public void studentChangeIndexException(String val){

        Student st = new Student("Adam", "Beks", "111111");

        assertThatThrownBy(() -> {st.changeIndex(nullConventer(val));}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("Wrong index!");

    }

    @Test
    @DisplayName("SchoolSystem.Student changeIndex method test if valid name")
    public void studentChangeIndexTest(){
        Student st = new Student("Adam", "Beks", "111111");
        st.changeIndex("300003");

        assertThat(st.getIndex()).isEqualTo("300003");

    }

    @Test
    @DisplayName("SchoolSystem.Student addStudentNoteMethod")
    public void studentReadNoteTest() throws ParseException {
        Student st = new Student("Adam", "Beks", "111111");
        NoteMock nm = new NoteMock("byl niegrzeczny!");
        st.addStudentNote(nm);

        assertThat(st.notes.get(0).readNote()).isEqualTo("byl niegrzeczny!");

    }

    @Test
    @DisplayName("SchoolSystem.Student editStudentNoteMethod")
    public void studentEditNoteTest() throws ParseException {
        Student st = new Student("Adam", "Beks", "111111");
        NoteMock nm = new NoteMock("byl niegrzeczny!");
        st.addStudentNote(nm);
        st.editStudentNote(nm, "jednak byl grzeczny!");

        assertThat(st.notes.get(0).readNote()).isEqualTo("jednak byl grzeczny!");

    }
    @Test
    @DisplayName("SchoolSystem.Student editStudentNoteMethod throws exception")
    public void studentEditNoteThrowsExceptionTest() throws ParseException {


        Student st = new Student("Adam", "Beks", "111111");
        INote nm = new NoteMock("byl niegrzeczny!");

        assertThatThrownBy(() -> {st.editStudentNote(nm, "jednak byl grzeczny!");}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("That note doesn't exists!");

    }

    @Test
    @DisplayName("SchoolSystem.Student addSubject method test")
    public void studentAddSubjectTest(){

        ISchoolSubject ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Student st = new Student("Adam", "Beks", "111111");
        st.addSubject(ssm);

        assertThat(st.subjects.containsKey(ssm)).isTrue();

    }

    @Test
    @DisplayName("SchoolSystem.Student editSubject method test")
    public void studentEditSubjectTest(){

        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Student st = new Student("Adam", "Beks", "111111");
        st.addSubject(ssm);
        st.editSubject(ssm, SchoolSubjectEnum.Wychowanie_fizyczne);

        assertThat(ssm.getName()).isEqualTo(SchoolSubjectEnum.Wychowanie_fizyczne.name());

    }

    @Test
    @DisplayName("SchoolSystem.Student editSubject method if subject not found test ")
    public void studentEditSubjectTest2(){

        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Student st = new Student("Adam", "Beks", "111111");

        assertThatThrownBy(() -> {st.editSubject(ssm, SchoolSubjectEnum.Wychowanie_fizyczne);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("subject not found!");

    }

    @Test
    @DisplayName("SchoolSystem.Student removeSubject method if subject not found test ")
    public void studentRemoveSubjectTest1(){


        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Student st = new Student("Adam", "Beks", "111111");

        assertThatThrownBy(() -> {st.removeSubject(ssm);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("subject not found!");

    }


    @Test
    @DisplayName("SchoolSystem.Student removeSubject method test ")
    public void studentRemoveSubjectTest2(){


        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Student st = new Student("Adam", "Beks", "111111");
        st.addSubject(ssm);
        st.removeSubject(ssm);

        assertThat(st.subjects.containsKey(ssm)).isFalse();

    }


    @Test
    @DisplayName("SchoolSystem.Student addGrade method test if no subject")
    public void studentAddGradeIfNoSub(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade testGrade1 = Mockito.mock(Grade.class);

        assertThatThrownBy(() -> {st.addGrade(ssm, testGrade1);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("subject not found!");

    }

    @Test
    @DisplayName("SchoolSystem.Student addGrade method test")
    public void studentAddGrade(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        st.addSubject(ssm);
        Grade testGrade1 = Mockito.mock(Grade.class);
        st.addGrade(ssm, testGrade1);

        assertThat(st.subjects.get(ssm).get(0)).isEqualTo(testGrade1);

    }

    @Test
    @DisplayName("SchoolSystem.Student editGrade method when no subject in student test")
    public void studentEditGradeWithException1(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade testGrade1 = Mockito.mock(Grade.class);

        assertThatThrownBy(() -> {st.editGrade(ssm, testGrade1, 4.5f);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("invalid subject!");

    }
    @Test
    @DisplayName("SchoolSystem.Student editGrade method when student hasnt that grade test")
    public void studentEditGradeWithException2(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        st.addSubject(ssm);
        Grade testGrade1 = Mockito.mock(Grade.class);

        assertThatThrownBy(() -> {st.editGrade(ssm, testGrade1, 4.5f);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("Grade not found!");

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

        assertThatThrownBy(() -> {st.removeGrade(ssm, testGrade1);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("invalid subject!");

    }

    @Test
    @DisplayName("SchoolSystem.Student removeGrade method when no grade ")
    public void studentRemoveGradeWhenException2(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        st.addSubject(ssm);
        Grade testGrade1 = Mockito.mock(Grade.class);

        assertThatThrownBy(() -> {st.removeGrade(ssm, testGrade1);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("grade not found!");


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

        assertThat(st.subjects.get(ssm).contains(testGrade1)).isFalse();

    }

    @Test
    @DisplayName("Average student grade of not existing subject")
    public void studentAverageGradeOfEx(){

        Student st = new Student("Adam", "Beks", "111111");
        ISchoolSubject  ssm = new SchoolSubjectMock(SchoolSubjectEnum.Biologia);
        Grade testGrade1 = Mockito.mock(Grade.class);

        assertThatThrownBy(() -> {st.averageGradeOf(ssm);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("invalid subject!");

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

        assertThat(st.averageGradeOf(ssm)).isEqualTo(result);

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

        assertThat(st.averageGradeOfAll()).isEqualTo(3.0f);

    }

}
