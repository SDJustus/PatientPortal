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

    /*@Id
    @Column(name="person_id", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="person")})   */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "diary_id")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<DiaryEntry> diaryEntries;


    //private Person person; //TODO: correct this (unused)

    public Diary() {
    }

    public Long getId() {
        return id;
    }

    public Set<DiaryEntry> getDiaryEntries() {
        return diaryEntries;
    }

    public void setDiaryEntries(Set<DiaryEntry> diaryEntries) {
        this.diaryEntries = diaryEntries;
    }
}
