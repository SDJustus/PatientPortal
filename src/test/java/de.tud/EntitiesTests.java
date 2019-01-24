package de.tud;

import de.tud.model.*;
import de.tud.model.manager.HomeworkManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class EntitiesTests {

    private Address testAddress = new Address("Grove Street", "47", 71773, "Los Santos", "United States of America");
    private static LocalDate birth = LocalDate.of(1995,05,25);


    @Test
    public void adressTestComplete() {


        testAddress.setStreet("Testgasse");
        testAddress.setNumber("42x");
        testAddress.setPostCode(29811);
        testAddress.setCity("Entenhausen");
        testAddress.setCountry("Germanistan");

        assertTrue(testAddress.getStreet() == "Testgasse" && testAddress.getNumber() == "42x" && testAddress.getPostCode() == 29811
                && testAddress.getCity() == "Entenhausen" && testAddress.getCountry() == "Germanistan");
    }

    @Test
    public void getEntityIdTest(){
        long testId = 0;

        Homework hw = new Homework(Homework.Type.DOCUMENT, "Krankenakte", "Krankenakte Patient 586");
        hw.setDate(ZonedDateTime.now());
        HomeworkManager hm = HomeworkManager.getInstance();

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

        assertEquals(de.getVitalData(), vd);
    }

    @Test
    public void personTest(){


        Person pers = new Person();

        pers.setGivenName("Jeff");
        pers.setFamilyName("Jefferson");
        pers.setAddress(testAddress);
        pers.setBirthday(birth);
        pers.setEmail("jayjay@gmail.com");
        pers.setGender(Person.Gender.MALE);
        pers.setPhone("0669-115857577");
        pers.setDiary(new Diary());

        assertEquals("Jeff", pers.getGivenName());
        assertEquals("Jefferson", pers.getFamilyName());
        assertEquals(pers.getAddress(), testAddress);
        assertSame(pers.getBirthday(), birth);
        assertSame("jayjay@gmail.com", pers.getEmail());
        assertEquals(pers.getGender(), Person.Gender.MALE);
        assertSame("0669-115857577", pers.getPhone());
        assertNull(pers.getDiary().getDiaryEntries());
    }

    @Test
    public void patientTest(){

        Patient patient = new Patient("Jeff", "Jefferson", Person.Gender.MALE,
                "jayjay@gmail.com", birth, "0669-115857577", testAddress);

        assertEquals(patient.getGivenName(), "Jeff");
        assertEquals(patient.getFamilyName(), "Jefferson");
        assertEquals(patient.getAddress(), testAddress);
        assertEquals(patient.getBirthday(),birth);
        assertEquals(patient.getEmail(),"jayjay@gmail.com");
        assertEquals(patient.getGender(),Person.Gender.MALE);
        assertEquals(patient.getPhone(),"0669-115857577");
    }
}


