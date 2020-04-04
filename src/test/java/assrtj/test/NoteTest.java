package assrtj.test;

import SchoolSystem.Note;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class NoteTest {

    private Note n;

    @Test
    @DisplayName("When note have a null description")
    public void noteOfTestEx1(){

        assertThatThrownBy(() -> {Note.of(null);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("description got blank or null value!");

    }
    @Test
    @DisplayName("When note have a blank description")
    public void noteOfTestEx2(){

        assertThatThrownBy(() -> {Note.of("");}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("description got blank or null value!");
    }

    @Test
    @DisplayName("Note readNote method test")
    public void readNoteTest(){

        assertThat(Note.of("Hi").readNote()).isEqualTo("Hi");

    }
    @Test
    @DisplayName("Note editNote method when blank value")
    public void editNoteTestEx1(){

        assertThatThrownBy(() -> {Note.of("A").editNote("");}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("description got blank or null value!");

    }

    @Test
    @DisplayName("Note editNote method when null value")
    public void editNoteTestEx2(){

        assertThatThrownBy(() -> {Note.of("A").editNote(null);}).
                isInstanceOf(IllegalArgumentException.class).hasMessage("description got blank or null value!");

    }

    @Test
    @DisplayName("Note editNote method when valid value")
    public void editNoteTest(){

        String test = "Nowa wartosc";
        Note n = Note.of("A");
        n.editNote(test);

        assertThat(n.readNote()).isEqualTo(test);

    }


}
