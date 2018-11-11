package de.tud.Model;


import de.tud.Model.DataModelDiary;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diary_entry")
public class Tagebucheintrag {

    public Tagebucheintrag(){

    }
    @Column(name = "SymptomID")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Symptom> symptoms = new ArrayList<>();

    @Column(name = "date")
    private LocalDateTime date;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private long person_id;



    public void setDate(LocalDateTime date) { this.date = date;
    }

    public void setSymptom(List<Symptom> list){ this.symptoms=list;
    }

    public LocalDateTime getDate(){ return date;
    }

    public List<Symptom> getSymptoms() { return symptoms;
    }

    public void setPersonId(long personId) { this.person_id = personId;
    }

    public long getPersonId() { return person_id;
    }


    public Tagebucheintrag(List<Symptom> list, LocalDateTime date){
        symptoms = new ArrayList<Symptom>();
        symptoms = list;
        this.date = date;
    }
}

