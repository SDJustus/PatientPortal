package de.tud.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diary_entry")
@Deprecated
public class Tagebucheintrag {

    public Tagebucheintrag(){

    }

    @Column(name = "symptoms")
    @ElementCollection(targetClass = Diary.class)
    private List<Diary> symptoms = new ArrayList<>();

    @Column(name = "date")
    private LocalDateTime date;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private long person_id;



    public void setDate(LocalDateTime date) { this.date = date;
    }

    public void setSymptom(List<Diary> list){ this.symptoms=list;
    }

    public LocalDateTime getDate(){ return date;
    }

    public List<Diary> getSymptoms() { return symptoms;
    }

    public void setPersonId(long personId) { this.person_id = personId;
    }

    public long getPersonId() { return person_id;
    }


    public Tagebucheintrag(List<Diary> list, LocalDateTime date){
        symptoms = new ArrayList<Diary>();
        symptoms = list;
        this.date = date;
    }
}

