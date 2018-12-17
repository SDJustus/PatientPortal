package de.tud;

import de.tud.model.*;
import de.tud.model.manager.HomeworkManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class EntitiesTests {

    private Address testadress = new Address("Grove Street", "47", 71773, "Los Santos", "United States of America");
    private static LocalDate birth = LocalDate.of(1995,05,25);


    @Test
    public void adressTestComplete() {


        testadress.setStreet("Testgasse");
        testadress.setNumber("42x");
        testadress.setPostCode(29811);
        testadress.setCity("Entenhausen");
        testadress.setCountry("Germanistan");

        assertTrue(testadress.getStreet() == "Testgasse" && testadress.getNumber() == "42x" && testadress.getPostCode() == 29811
                && testadress.getCity() == "Entenhausen" && testadress.getCountry() == "Germanistan");
    }

    @Test
    public void getEntityIdTest(){
        long testId = 0;

        Homework hw = new Homework(Homework.Type.DOCUMENT, "Krankenakte", "Krankenakte Patient 586", "Krankenakte Herr Mueller inkl. Symptome");

        HomeworkManager hm = new HomeworkManager();

        testId =  hm.create(hw);

        if(hm.findByID(testId).getId()!=0) assertTrue(true);
    }

    @Test
    public void diaryEntrySetterTest(){
        LocalDateTime ldt = LocalDateTime.now();
        VitalData vd = new VitalData();
        vd.setWeight(166);
        vd.setHeight(178);
        vd.setBloodPressureFirstValue(145);
        vd.setBloodPressureSecondValue(97);
        vd.setHeartRate(26);

        DiaryEntry de = new DiaryEntry();

        if(de.getDate()!=null) fail();

        de.setDate(ldt);

        assertEquals(ldt , de.getDate());

        de.setVitalData(vd);

        assertTrue(de.getVitalData().equals(vd));
    }

    @Test
    public void personTest(){


        Person pers = new Person();

        pers.setGivenName("Jeff");
        pers.setFamilyName("Jefferson");
        pers.setAddress(testadress);
        pers.setBirthday(birth);
        pers.setEmail("jayjay@gmail.com");
        pers.setGender(Person.Gender.MALE);
        pers.setPhone("0669-115857577");
        pers.setDiary(new Diary());

                assertTrue(pers.getGivenName().equals("Jeff"));
                assertTrue(     pers.getFamilyName().equals("Jefferson"));
                assertTrue(  pers.getAddress().equals(testadress));
                assertTrue(  pers.getBirthday()==birth);
                assertTrue(  pers.getEmail()=="jayjay@gmail.com");
                assertTrue(  pers.getGender().equals(Person.Gender.MALE));
                assertTrue( pers.getPhone()=="0669-115857577");
                assertTrue(  pers.getDiary().getDiaryEntries()==null);
    }

    @Test
    public void patientTest(){

        Patient patient = new Patient("Jeff", "Jefferson", Person.Gender.MALE, "jayjay@gmail.com", birth, "0669-115857577", testadress);

        assertTrue(patient.getGivenName().equals("Jeff"));
        assertTrue(     patient.getFamilyName().equals("Jefferson"));
        assertTrue(  patient.getAddress().equals(testadress));
        assertTrue(  patient.getBirthday()==birth);
        assertTrue(  patient.getEmail()=="jayjay@gmail.com");
        assertTrue(  patient.getGender().equals(Person.Gender.MALE));
        assertTrue( patient.getPhone()=="0669-115857577");
    }
}


