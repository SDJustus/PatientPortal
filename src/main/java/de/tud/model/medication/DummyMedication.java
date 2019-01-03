package de.tud.model.medication;

import de.tud.model.EntityObject;
import de.tud.model.exceptions.EmptyDataBaseException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dummy_medication")
public class DummyMedication extends EntityObject {

    @Id
    private long id;
    @Column (name = "substance")
    private String substance;
    @Column (name = "trade_name")
    private String tradeName;
    @Column (name = "strength")
    private String strength;
    @Column (name = "form")
    private String form;
    @Column (name = "incompatible_with")
    private String incompatipleWith;

    @Override
    public Long getId() {
        return id;
    }

    public String getSubstance() {
        return substance;
    }

    public String getTradeName() {
        return tradeName;
    }

    public String getStrength() {
        return strength;
    }

    public String getForm() {
        return form;
    }

    public String getIncompatibleWithAsString(){
        return incompatipleWith;
    }

    public List<Long> getIncompatipleWith(){
        List<Long> dummyIds = new ArrayList<>();
        for (String s : incompatipleWith.split(",")){
            dummyIds.add(Long.valueOf(s));
        }
        if (dummyIds.isEmpty()) throw new EmptyDataBaseException("create the dummyMedication table");
        return dummyIds;
    }
}
