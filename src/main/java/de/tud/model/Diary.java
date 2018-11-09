package de.tud.model;


import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(fetch = FetchType.LAZY,
                orphanRemoval = true,
                cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id")
    private Set<DiaryEntry> diaryEntries;
/*
    private Person person;
*/
    public Diary() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<DiaryEntry> getDiaryEntries() {
        return diaryEntries;
    }

    public void setDiaryEntries(Set<DiaryEntry> diaryEntries) {
        this.diaryEntries = diaryEntries;
    }
}
