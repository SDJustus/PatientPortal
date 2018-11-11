package de.tud.Model;

import javax.persistence.Entity;

@Entity
public class Depression extends Symptom {


    public Depression(Strength s)
    {
        super(s);

    }
    public Depression(){

    }


}
