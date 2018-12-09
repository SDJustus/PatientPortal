package de.tud.model.manager;

import de.tud.model.Diary;
import de.tud.model.DiaryEntry;

import org.hibernate.Session;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiaryManager extends EntityManager<Diary> {

    private DiaryManager(){

    }

    private static final Logger LOGGER = Logger.getLogger(DiaryManager.class.getName());

    private static final DiaryManager INSTANCE = new DiaryManager();

    public static DiaryManager getInstance(){
        return INSTANCE;
    }


    @Override
    public List<Diary> read() {
        Session session = getSessionFactory().openSession();
        List<Diary> diary = session.createQuery("FROM Diary").list();
        if (diary.isEmpty()){
            throw new NullPointerException("There was no Diary in the database.");
        }
        session.close();
        LOGGER.log(Level.INFO, "Read " + diary.size() + " from the database!");
        return diary;
    }

    public Set<DiaryEntry> readDiaryEntriesByDiary(Long diaryId) {
        if (diaryId<=0 || diaryId == null) throw new IllegalArgumentException("Expected diary ID ");
        Session session = getSessionFactory().openSession();
        Set<DiaryEntry> diaryEntries = session.get(Diary.class, diaryId).getDiaryEntries();
        if (diaryEntries == null) throw new NullPointerException("There was no Diary with the given ID!");
        session.close();

        return diaryEntries;
    }

    @Deprecated
    public void addDiary(Diary diary){
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(diary);

        session.getTransaction().commit();
        session.close();
    }

    public void addDiaryEntry(DiaryEntry diaryEntry, Long diaryId){
        if (diaryEntry == null) throw new IllegalArgumentException("Expect DiaryEntry to be not null!");
        if (diaryId == null|| diaryId <= 0) throw new IllegalArgumentException("Expect DiaryID not to be null!");
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(diaryEntry);
        Set<DiaryEntry> diaryEntries = session.get(Diary.class, diaryId).getDiaryEntries();
        if (diaryEntries == null) throw new NullPointerException("There was no Diary with the given ID!");
        diaryEntries.add(diaryEntry);
        session.getTransaction().commit();
        session.close();
    }

    public void removeDiaryEntry(DiaryEntry diaryEntry, Long diaryId){
        if (diaryEntry == null) throw new IllegalArgumentException("Expect DiaryEntry to be not null!");
        if (diaryId == null|| diaryId <= 0) throw new IllegalArgumentException("Expect DiaryID not to be null!");
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Set<DiaryEntry> diaryEntries = session.get(Diary.class, diaryId).getDiaryEntries();
        if (diaryEntries == null) throw new NullPointerException("There was no Diary with the given ID!");
        diaryEntries.remove(diaryEntry);
        session.getTransaction().commit();
        session.close();
    }

    public DiaryEntry getDiaryEntryById(Long diaryId, Long diaryEntryId){
        if (diaryEntryId == null|| diaryEntryId <= 0) throw new IllegalArgumentException("Expect DiaryEntryID to be not null!");
        if (diaryId == null|| diaryId <= 0) throw new IllegalArgumentException("Expect DiaryID not to be null!");
        Set<DiaryEntry> diaryEntries = findByID(diaryId).getDiaryEntries();
        DiaryEntry diaryEntry = null;
        for(DiaryEntry diaryEntry1: diaryEntries) {
            if (diaryEntry1.getId().equals(diaryEntryId))
                diaryEntry = diaryEntry1;
        }

        if(diaryEntry == null)
            throw new NullPointerException("No DiaryEntry exists with the given ID: " + diaryEntryId);
        return diaryEntry;
    }

    @Override
    public void delete(Long id) {
        if (id == null|| id <= 0) throw new IllegalArgumentException("Expect DiaryID to be not null!");
        Session session = getSessionFactory().openSession();

        try{
            session.beginTransaction();
            Diary diary = findByID(id);
            session.delete(diary);
            session.getTransaction().commit();
            LOGGER.log(Level.INFO, "Successfully deleted " + diary.toString());
        }
        catch (Exception e){
            if (session.getTransaction() != null){
                session.getTransaction().rollback();
                LOGGER.log(Level.INFO, "Deletion failed");
            }
        }
        finally {
            session.close();
        }
    }

    @Override
    public Diary findByID(Long id) {
        if (id == null|| id <= 0) throw new IllegalArgumentException("Expect DiaryID to be not null!");
        Session session = getSessionFactory().openSession();
        Diary diary = session.get(Diary.class, id);
        session.close();
        if (diary == null) throw new IllegalArgumentException("There was no Diary with the given ID!");
        return diary;
    }

    public void deleteAll() {
        Session session = getSessionFactory().openSession();
        session.getTransaction().begin();
        List<Diary> diaryList = read();
        for(Diary diary : diaryList){
            session.delete(diary);
        }
        session.getTransaction().commit();
       session.close();
    }
}