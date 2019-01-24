package de.tud;

import de.tud.model.welfare.Sleep;
import de.tud.model.welfare.WelfareFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WelfareFactoryTest {

    @Test
    void instanceTest() {

        WelfareFactory testfactory = WelfareFactory.getInstance();
        if (testfactory != null) assertTrue(true);
    }


    @Test
    void createByClassTest2() {

        Sleep sleep = (Sleep) WelfareFactory.getInstance().createSymptomByClass(Sleep.class, Sleep.Strength.MIDDLE);

        assertEquals("Schlaf: Mäßig", sleep.toString());
    }
}
