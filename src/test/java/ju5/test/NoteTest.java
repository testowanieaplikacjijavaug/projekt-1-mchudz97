package ju5.test;
import SchoolSystem.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NoteTest {

    private Note n;

    @Test
    @DisplayName("When note have a null description")
    public void noteOfTestEx1(){

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Note.of(null));

    }
    @Test
    @DisplayName("When note have a blank description")
    public void noteOfTestEx2(){

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Note.of(""));

    }

    @Test
    @DisplayName("Note readNote method test")
    public void readNoteTest(){

        Assertions.assertEquals("Hi", Note.of("Hi").readNote());

    }
    @Test
    @DisplayName("Note editNote method when blank value")
    public void editNoteTestEx1(){

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Note.of("A").editNote(""));

    }

    @Test
    @DisplayName("Note editNote method when null value")
    public void editNoteTestEx2(){

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Note.of("A").editNote(null));

    }

    @Test
    @DisplayName("Note editNote method when valid value")
    public void editNoteTest(){

        String test = "Nowa wartosc";
        n = Note.of("A");
        n.editNote(test);

        Assertions.assertEquals(test, n.readNote());

    }

}
