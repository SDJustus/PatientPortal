package de.tud.model;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "diary")
public class Diary extends EntityObject {

    /**
     * Holds the ID of the persistent Diary object.
     */
    @Id
    @SequenceGenerator(name = "DiaryGenerator", sequenceName = "DiarySequence", allocationSize = 1)
    @GeneratedValue(generator = "DiaryGenerator")
    private long id;

    /**
     * Holds DiaryEntry objects belonging to the Diary object as a set.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "diary_id")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<DiaryEntry> diaryEntries;


    //private Person person; //TODO: correct this (unused)

    /**
     * Constructor of Diary.
     */
    public Diary() {
    }

    /**
     * Returns the ID of the persistent Diary object.
     * @return value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the held DiaryEntry objects as a set.
     * @return Set of DiaryEntry
     */
    public Set<DiaryEntry> getDiaryEntries() {
        return diaryEntries;
    }

    /**
     * Sets the diaryEntry value as a set of DiaryEntry objects.
     * @param diaryEntries
     */
    public void setDiaryEntries(Set<DiaryEntry> diaryEntries) {
        this.diaryEntries = diaryEntries;
    }
}
