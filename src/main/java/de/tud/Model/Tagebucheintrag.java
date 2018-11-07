package de.tud.Model;


import de.tud.Model.DataModelDiary;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diary_entry")
public class Tagebucheintrag {

    @Column(name = "symptoms")
    @ElementCollection(targetClass = DataModelDiary.class)
    private List<DataModelDiary> symptoms = new ArrayList<>();

    @Column(name = "date")
    private String date;

    @Id
    @Column(name = "person_id")
    private long person_id;



    public void setDate(String date) { this.date = date;
    }

    public void setSymptom(List<DataModelDiary> list){ this.symptoms=list;
    }

    public String getDate(){ return date;
    }

    public List<DataModelDiary> getSymptoms() { return symptoms;
    }

    public void setPersonId(long personId) { this.person_id = personId;
    }

    public long getPersonId() { return person_id;
    }


    public Tagebucheintrag(List<DataModelDiary> list, int person_id, String date){
        symptoms = new ArrayList<DataModelDiary>();
        symptoms = list;
        this.person_id = person_id;
        this.date = date;
    }
}

