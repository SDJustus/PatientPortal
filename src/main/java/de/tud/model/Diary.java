package de.tud.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "diary")
public class Diary extends EntityObject {

    @Id
    @Column(name="person_id", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="person")})
    private long id;

    @OneToMany(fetch = FetchType.LAZY,
                orphanRemoval = true,
                cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id")
    private Set<DiaryEntry> diaryEntries;


    private Person person;

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
