package de.tud.model.manager;

import de.tud.model.Diary;
import de.tud.model.DiaryEntry;

import org.hibernate.Session;

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
        List<Diary> diary = session.createQuery("FROM Diary").list();
        session.close();
        System.out.println("Found " + diary.size() + " diaries");

        return diary;
    }

    public Set<DiaryEntry> readDiaryEntriesByDiary(Long diaryId) {
        Session session = getSessionFactory().openSession();
        Set<DiaryEntry> diaryEntries = session.get(Diary.class, diaryId).getDiaryEntries();
        session.close();
        //System.out.println("Found " + diary.size() + " diaries");

        return diaryEntries;
    }

    //Bei Erstellung von einem
    @Deprecated
    public void addDiary(Diary diary){
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(diary);

        session.getTransaction().commit();
        session.close();
    }

    public void addDiaryEntry(DiaryEntry diaryEntry, Long diaryId){
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(diaryEntry);
        Set<DiaryEntry> diaryEntries = session.get(Diary.class, diaryId).getDiaryEntries();
        diaryEntries.add(diaryEntry);
        session.getTransaction().commit();
        session.close();
    }

    public void removeDiaryEntry(DiaryEntry diaryEntry, Long diaryId){
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.get(Diary.class, diaryId).getDiaryEntries().remove(diaryEntry);
        session.getTransaction().commit();
        session.close();
    }

    public DiaryEntry getDiaryEntryById(Long diaryId, Long diaryEntryId){
        Set<DiaryEntry> diaryEntries = findByID(diaryId).getDiaryEntries();
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