package de.tud.model.medication;

import de.tud.model.EntityObject;

import javax.persistence.*;

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
}
