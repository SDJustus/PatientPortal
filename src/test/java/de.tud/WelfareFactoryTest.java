package de.tud;

import de.tud.model.welfare.PhysicalCondition;
import de.tud.model.welfare.Sleep;
import de.tud.model.welfare.Welfare;
import de.tud.model.welfare.WelfareFactory;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WelfareFactoryTest {

    @Test
    public void instanceTest() {

        WelfareFactory testfactory = WelfareFactory.getInstance();
        if (testfactory != null) assertTrue(true);
    }


    @Test
    public void createByClassTest2() {

        Sleep sleep = (Sleep) WelfareFactory.getInstance().createSymptomByClass(Sleep.class, Sleep.Strength.MIDDLE);

        assertEquals("Schlaf: Mäßig", sleep.toString());
    }
}
