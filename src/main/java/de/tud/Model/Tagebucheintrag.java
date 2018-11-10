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

    @Column(name = "symptoms")
    @ElementCollection(targetClass = DataModelDiary.class)
    private List<DataModelDiary> symptoms = new ArrayList<>();

    @Column(name = "date")
    private LocalDateTime date;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private long person_id;



    public void setDate(LocalDateTime date) { this.date = date;
    }

    public void setSymptom(List<DataModelDiary> list){ this.symptoms=list;
    }

    public LocalDateTime getDate(){ return date;
    }

    public List<DataModelDiary> getSymptoms() { return symptoms;
    }

    public void setPersonId(long personId) { this.person_id = personId;
    }

    public long getPersonId() { return person_id;
    }


    public Tagebucheintrag(List<DataModelDiary> list, LocalDateTime date){
        symptoms = new ArrayList<DataModelDiary>();
        symptoms = list;
        this.date = date;
    }
}

