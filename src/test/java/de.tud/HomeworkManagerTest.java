package de.tud;

import de.tud.model.Homework;
import de.tud.model.manager.HomeworkManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class HomeworkManagerTest {

    Homework hw1;
    Homework hw2;
    HomeworkManager hwm;
    List<Homework> readlist;
    long hwID;



    @BeforeEach
    public void setup(){
        hw1 = new Homework(Homework.Type.DOCUMENT,"Statusbericht","Bericht über den Verlauf der letzten Woche", "Bericht über letzte Woche, die Symptome und das Wohlbefinden");
        hw2 = new Homework(Homework.Type.EXERCISE,"Kniebeugen", "Übung Kniebeugen gegen Blutdruck", "Sportübung um den Blutdruck zu senken");
        hwm = new HomeworkManager();

    }




    @Test
    public void readTest(){
        readlist = new ArrayList<>();

        hwm.create(hw1);
        hwm.create(hw2);

        readlist = hwm.read();

        for(Homework hw : readlist){
            if(hw.getType().equals(hw1.getType()) && hw.getLongDescription().equals(hw1.getLongDescription())){
                for(Homework howo : readlist){
                    if(hw.getType().equals(hw1.getType()) && hw.getLongDescription().equals(hw1.getLongDescription()));
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

        assertTrue(hwtest.getType().equals(hw1.getType()) && hwtest.getLongDescription().equals(hw1.getLongDescription()));

    }

    @Test
    public void updateHomeworkTest(){

        hwID = hwm.create(hw1);

        hwm.updateHomework(hwID, hw2);

        for(Homework hw : hwm.read()){
            if(hw.getId().equals(hwID)) {
                assertTrue(hw.getType().equals(hw2.getType()) && hw.getLongDescription().equals(hw2.getLongDescription()) && hw.getName().equals(hw2));
            }
        }



    }

}
