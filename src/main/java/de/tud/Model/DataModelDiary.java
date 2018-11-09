package de.tud.Model;


import javax.persistence.*;
import java.time.LocalDateTime;
import de.tud.Model.symptom.Symptom;


@Entity
@Table(name = "Diary")

public class DataModelDiary {

    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "symptom_id")
    private Symptom symptom;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diary_id")
    private long diaryID;

    public DataModelDiary(LocalDateTime date, Symptom symptom){
        this.date = date;
        this.symptom = symptom;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }
}
