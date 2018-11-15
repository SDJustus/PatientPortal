package de.tud.model.manager;

import de.tud.model.Diary;
import de.tud.model.DiaryEntry;

import org.hibernate.Session;


import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DiaryManager extends EntityManager<Diary> {

    private static final DiaryManager INSTANCE = new DiaryManager();

    public static DiaryManager getInstance(){
        return INSTANCE;
    }
    @Override
    public List<Diary> read() {
        Session session = getSessionFactory().openSession();
        List<DiaryEntry> diary = session.createQuery("FROM DiaryEntry").list();
        session.close();
        System.out.println("Found " + diary.size() + " diaries");

        return new ArrayList<>();
    }

    public List<DiaryEntry> readDiaryEntry() {
        Session session = getSessionFactory().openSession();
        List<DiaryEntry> diary = session.createQuery("FROM DiaryEntry").list();
        session.close();
        System.out.println("Found " + diary.size() + " diaries");

        return diary;
    }




    //TODO: test this!
    public void addDiaryEntry(DiaryEntry diaryEntry, Long diaryId){
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(diaryEntry);
        //Set<DiaryEntry> diaryEntries = session.get(Diary.class, diaryId).getDiaryEntries();
        /*
        for (DiaryEntry diaryEntry1 : diaryEntries){
            if (diaryEntry.getId().equals(diaryEntry1.getId()))
                diaryEntries.remove(diaryEntry1);
        }*/
        //diaryEntries.add(diaryEntry);
        session.getTransaction().commit();
        session.close();
    }

    //TODO: See if this works
    public void removeDiaryEntry(DiaryEntry diaryEntry, Long diaryId){
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.get(Diary.class, diaryId).getDiaryEntries().remove(diaryEntry);
        session.getTransaction().commit();
        session.close();
    }

    public DiaryEntry getDiaryEntryById(Long diaryId, Long diaryEntryId){
        Session session = getSessionFactory().openSession();
        Set<DiaryEntry> diaryEntries = session.get(Diary.class, diaryId).getDiaryEntries();
        session.close();
        DiaryEntry diaryEntry = null;
        for(DiaryEntry diaryEntry1: diaryEntries) {
            if (diaryEntry1.getId().equals(diaryEntryId))
                diaryEntry = diaryEntry1;
        }

        if(diaryEntry == null)
            throw new IllegalArgumentException("No DiaryEntry exists with the given ID: " + diaryEntryId);
        return diaryEntry;
    }

    @Override
    public void delete(Long id) {
        Session session = getSessionFactory().openSession();

        try{
            session.beginTransaction();
            Diary diary = findByID(id);
            session.delete(diary);
            session.getTransaction().commit();
            System.out.println("Successfully deleted " + diary.toString());
        }
        catch (Exception e){
            if (session.getTransaction() != null){
                session.getTransaction().rollback();
                System.out.println("Deletion failed");
            }
        }
        finally {
            session.close();
        }
    }

    @Override
    public Diary findByID(Long id) {
        Session session = getSessionFactory().openSession();
        Diary diary = session.load(Diary.class, id);
        session.close();
        return diary;
    }

    public void deleteAll() {
        Session session = getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Diary ");
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Successfully deleted all diaries.");
        }
        catch (Exception e){
            session.getTransaction().rollback();
            System.out.println("Deletion of all diaries failed");
        }
        finally {
            session.close();
        }
    }
}