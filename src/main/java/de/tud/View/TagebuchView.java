package de.tud.View;
import com.vaadin.ui.*;
import com.vaadin.navigator.View;
import de.tud.Controller.TagebuchImplementierung;

public class TagebuchView extends Composite implements View {


    public TagebuchView(){

        //tbl.setStyleName(".v-horizontal-layout{margin-left: 0 !important; left: 0px !important;}");
        //setCompositionRoot(new TagebucheintragImpl());
        setCompositionRoot(new TagebuchImplementierung());


    }
}
