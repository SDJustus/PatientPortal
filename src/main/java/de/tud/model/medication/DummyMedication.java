package de.tud.model.medication;

import de.tud.model.EntityObject;
import de.tud.model.exceptions.EmptyDataBaseException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dummy_medication")
public class DummyMedication extends EntityObject {

    /**
     * Holds the ID value of the persistent DummyMedication object.
     */
    @Id
    private long id;
    /**
     * Holds the substance value of the persistent DummyMedication object.
     */
    @Column (name = "substance")
    private String substance;
    /**
     * Holds the trade name value of the persistent DummyMedication object.
     */
    @Column (name = "trade_name")
    private String tradeName;
    /**
     * Holds the strength value of the persistent DummyMedication object.
     */
    @Column (name = "strength")
    private String strength;
    /**
     * Holds the form value of the persistent DummyMedication object.
     */
    @Column (name = "form")
    private String form;
    /**
     * Holds the incompatibility value of the persistent DummyMedication object.
     */
    @Column (name = "incompatible_with")
    private String incompatipleWith;

    public DummyMedication(long id, String substance, String tradeName, String strength, String form, String incompatipleWith) {
        this.id = id;
        this.substance = substance;
        this.tradeName = tradeName;
        this.strength = strength;
        this.form = form;
        this.incompatipleWith = incompatipleWith;
    }

    public DummyMedication(){

    }

    /**
     * Returns the ID of the persistent DummyMedication object.
     * @return
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Returns the substance value of the DummyMedication object.
     * @return
     */
    public String getSubstance() {
        return substance;
    }

    /**
     * Returns the tradeName value of the DummyMedication object.
     * @return
     */
    public String getTradeName() {
        return tradeName;
    }

    /**
     * Returns the strength value of the DummyMedication object.
     * @return
     */
    public String getStrength() {
        return strength;
    }

    /**
     * Returns the form value of the DummyMedication object.
     * @return
     */
    public String getForm() {
        return form;
    }

    /**
     * Returns the value of incompatibleWith value of the DummyMedication object
     * @return
     */
    public String getIncompatibleWithAsString(){
        return incompatipleWith;
    }

    /**
     * Returns the ID values of DummyMedication objects which are incompatible with a particular DummyMedication object.
     * @return
     */
    public List<Long> getIncompatipleWith(){
        List<Long> dummyIds = new ArrayList<>();
        for (String s : incompatipleWith.split(",")){
            dummyIds.add(Long.valueOf(s));
        }
        if (dummyIds.isEmpty()) throw new EmptyDataBaseException("create the dummyMedication table");
        return dummyIds;
    }
}
