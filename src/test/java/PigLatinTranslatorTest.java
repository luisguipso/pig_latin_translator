import org.example.PigLatinTranslator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PigLatinTranslatorTest {

    private PigLatinTranslator translator = new PigLatinTranslator();

    @Test
    void firstExerciseTests(){
        assertEquals("opstay", translator.translate("stop"));
        assertEquals("onay", translator.translate("no"));
        assertEquals("eoplepay", translator.translate("people"));
        assertEquals("ubblebay", translator.translate("bubble"));
        assertEquals("underay", translator.translate("under"));
        assertEquals("admitteday", translator.translate("admitted"));
        assertEquals("awayay", translator.translate("away"));
    }

    @Test
    void translateEmptyText(){
        assertEquals("", translator.translate(""));
    }

    @Test
    void translateNullText(){
        assertEquals(null, translator.translate(null));
    }

    @Test
    void preservePunctuations(){
        assertEquals("Onay irtsshay,",
                translator.translate("No shirts,"));
    }

    @Test
    void ifTheWordHasNoConsonantShouldNotAddThePosfix(){
        assertEquals("Iyay", translator.translate("I"));
    }

    @Test
    void aWordWithNoVowelShouldNotChange(){
        assertEquals("14", translator.translate("14"));
    }

    @Test
    void secondExerciseTest(){
        assertEquals("Opstay", translator.translate("Stop"));
        assertEquals("Onay itteringlay", translator.translate("No littering"));
        assertEquals("Onay irtsshay, onay oesshay, onay ervicesay", translator.translate("No shirts, no shoes, no service"));
        assertEquals("Onay ersonspay underay 14 admitteday", translator.translate("No persons under 14 admitted"));
        assertEquals("Eyhay uddybay, etgay awayay omfray ymay arcay!", translator.translate("Hey buddy, get away from my car!"));
    }
}
