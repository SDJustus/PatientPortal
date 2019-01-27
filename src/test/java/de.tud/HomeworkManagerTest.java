package de.tud;

import de.tud.model.Homework;
import de.tud.model.manager.HomeworkManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HomeworkManagerTest {

    private Homework hw1;
    private Homework hw2;
    private Homework hw3;
    private Homework hw4;
    private HomeworkManager hwm;
    private List<Homework> readList;
    private long hwID;
    private ZonedDateTime zonedDateTime;



    @BeforeEach
    void setup(){
        hw1 = new Homework(Homework.Type.DOCUMENT,"Statusbericht","Bericht über den Verlauf der letzten Woche", ZonedDateTime.now().withHour(1).withMinute(0));
        hw2 = new Homework(Homework.Type.EXERCISE,"Kniebeugen", "Übung Kniebeugen gegen Blutdruck", ZonedDateTime.now().withHour(1).withMinute(0));
        hw3 = new Homework(Homework.Type.QUESTIONNAIRE, "Umfrage", "Umfrage über Kundenzufriedenheit ausfüllen", ZonedDateTime.now().plusDays(2).withHour(1).withMinute(0));
        hw4 = new Homework(Homework.Type.DOCUMENT, "neuer Statusbericht", "Bericht über Verlauf der letzten Tage", ZonedDateTime.now().plusDays(4));
        hwm = HomeworkManager.getInstance();

    }

    @AfterAll
    void tearDown(){
        hwm.deleteAll();
        hwm.create(hw1);
        hwm.create(hw2);
        hwm.create(hw3);
        hwm.create(hw4);
    }




    @Test
    void readTest(){
        readList = new ArrayList<>();

        hwm.create(hw1);
        hwm.create(hw2);

        readList = hwm.read();

        for(Homework hw : readList){
            if(hw.getType().equals(hw1.getType()) && hw.getDescription().equals(hw1.getDescription())){
                        assertTrue(true);
                }
            }
        }


    @Test
    void deleteTest(){

        hwID = hwm.create(hw1);

        hwm.delete(hwID);

        for(Homework hw : hwm.read()){
           if(hw.getId().equals(hwID)) {
               fail();
           }
        }


    }

    @Test
    void findByIdTest(){

        hwID = hwm.create(hw1);

        Homework hwTest = hwm.findByID(hwID);

        assertTrue(hwTest.getType().equals(hw1.getType()) && hwTest.getDescription().equals(hw1.getDescription()));

    }

    @Test
    void updateHomeworkTest(){

        hwID = hwm.create(hw1);

        hwm.updateHomework(hwID, hw2);

        for(Homework hw : hwm.read()){
            if(hw.getId().equals(hwID)) {
                assertEquals(hw.getType(), hw2.getType());
                assertEquals(hw.getDescription(), hw2.getDescription());
                assertEquals(hw.getName(), hw2.getName());
                assertEquals(hw.getDescription(), hw2.getDescription());
                assertNotEquals(hw.getType(), Homework.Type.DOCUMENT);
            }
        }



    }

}
