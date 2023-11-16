import org.example.PigLatinTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PigLatinTranslatorTest {

    @Test
    void translateStop(){
//        PigLatinTranslator translator = new PigLatinTranslator();
//        Assertions.assertEquals("opstay", translator.translate("stop"));
//        Assertions.assertEquals("onay", translator.translate("no"));
//        Assertions.assertEquals("eoplepay", translator.translate("people"));
//        Assertions.assertEquals("ubblebay", translator.translate("bubble"));
//        Assertions.assertEquals("underay", translator.translate("under"));
//        Assertions.assertEquals("admitteday", translator.translate("admitted"));
//        Assertions.assertEquals("awayay", translator.translate("away"));
    }

    @Test
    void translateEmpty(){
        PigLatinTranslator translator = new PigLatinTranslator();
        assertEquals("", translator.translate(""));
    }

    @Test
    void translateNull(){
        PigLatinTranslator translator = new PigLatinTranslator();
        assertEquals(null, translator.translate(null));
    }

    @Test
    void preservePunctuations(){
        PigLatinTranslator translator = new PigLatinTranslator();
        assertEquals("Onay irtsshay,",
                translator.translate("No shirts,"));
    }

    @Test
    void translateAll(){
        PigLatinTranslator translator = new PigLatinTranslator();
        assertEquals("Opstay", translator.translate("Stop"));
        assertEquals("Onay itteringlay", translator.translate("No littering"));
        assertEquals("Onay irtsshay, onay oesshay, onay ervicesay", translator.translate("No shirts, no shoes, no service"));
        assertEquals("Onay ersonspay underay 14 admitteday", translator.translate("No persons under 14 admitted"));
        assertEquals("Eyhay uddybay, etgay awayay omfray ymay arcay!", translator.translate("Hey buddy, get away from my car!"));
    }
}
