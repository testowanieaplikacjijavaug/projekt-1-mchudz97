package ju5.test;

import SchoolSystem.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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


}
