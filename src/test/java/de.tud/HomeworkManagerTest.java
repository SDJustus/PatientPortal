package de.tud;

import de.tud.model.Homework;
import de.tud.model.manager.HomeworkManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HomeworkManagerTest {

    Homework hw1;
    Homework hw2;
    HomeworkManager hwm;
    List<Homework> readlist;
    long hwID;



    @BeforeEach
    public void setup(){
        hw1 = new Homework(Homework.Type.DOCUMENT,"Statusbericht","Bericht über den Verlauf der letzten Woche", ZonedDateTime.now());
        hw2 = new Homework(Homework.Type.EXERCISE,"Kniebeugen", "Übung Kniebeugen gegen Blutdruck", ZonedDateTime.now());
        hwm = new HomeworkManager();

    }




    @Test
    public void readTest(){
        readlist = new ArrayList<>();

        hwm.create(hw1);
        hwm.create(hw2);

        readlist = hwm.read();

        for(Homework hw : readlist){
            if(hw.getType().equals(hw1.getType()) && hw.getDescription().equals(hw1.getDescription())){
                for(Homework howo : readlist){
                    if(hw.getType().equals(hw1.getType()) && hw.getDescription().equals(hw1.getDescription()));
                        assertTrue(true);
                }
            }
        }
    }

    @Test
    public void deleteTest(){

        hwID = hwm.create(hw1);

        hwm.delete(hwID);

        for(Homework hw : hwm.read()){
           if(hw.getId().equals(hwID)) {
               fail();
           }
        }


    }

    @Test
    public void findByIdTest(){

        hwID = hwm.create(hw1);

        Homework hwtest = hwm.findByID(hwID);

        assertTrue(hwtest.getType().equals(hw1.getType()) && hwtest.getDescription().equals(hw1.getDescription()));

    }

    @Test
    public void updateHomeworkTest(){

        hwID = hwm.create(hw1);

        hwm.updateHomework(hwID, hw2);

        for(Homework hw : hwm.read()){
            if(hw.getId().equals(hwID)) {
                assertEquals(hw.getType(), hw2.getType());
                assertEquals(hw.getDescription(), hw2.getDescription());
                assertEquals(hw.getName(), hw2.getName());
                assertEquals(hw.getDescription(), hw2.getDescription());
                assertFalse(hw.getType().equals(Homework.Type.DOCUMENT));
            }
        }



    }

}
