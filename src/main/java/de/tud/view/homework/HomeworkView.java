package de.tud.view.homework;

import com.vaadin.ui.*;
import com.vaadin.navigator.View;

public class HomeworkView extends Composite implements View {


    public HomeworkView(){


        setCompositionRoot(new HomeworkSetup());


    }
}
