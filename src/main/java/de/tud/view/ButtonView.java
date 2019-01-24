package de.tud.view;

import com.vaadin.server.ClassResource;
import com.vaadin.ui.*;

public class ButtonView {
    protected Image goodSmiley;
    protected Image middleSmiley;
    protected Image badSmiley;
    protected Label goodLabel;
    protected Label middleLabel;
    protected Label badLabel;
    protected HorizontalLayout horizontalLayout;
    protected HorizontalLayout horizontalLayout1;


    public ButtonView(){
        goodSmiley = new Image();
        goodSmiley.setWidth("60px");
        goodSmiley.setHeight("60px");
        goodSmiley.setStyleName("smileybild");


        middleSmiley = new Image();
        middleSmiley.setWidth("60px");
        middleSmiley.setHeight("60px");
        middleSmiley.setStyleName("smileybild");


        badSmiley = new Image();
        badSmiley.setWidth("60px");
        badSmiley.setHeight("60px");
        badSmiley.setStyleName("smileybild");

        goodSmiley.setSource(new ClassResource("/gut.png"));
        middleSmiley.setSource(new ClassResource("/mittel.png"));
        badSmiley.setSource(new ClassResource("/schlecht.png"));


        //Horizontal Layout für Smiley Bilder erzeugen
        horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("270px");
        horizontalLayout.setHeight("30px");


        horizontalLayout.addComponents(goodSmiley, middleSmiley, badSmiley);
        horizontalLayout.setComponentAlignment(goodSmiley, Alignment.TOP_CENTER);
        horizontalLayout.setComponentAlignment(middleSmiley, Alignment.TOP_CENTER);
        horizontalLayout.setComponentAlignment(badSmiley, Alignment.TOP_CENTER);
        horizontalLayout.setSpacing(true);
        horizontalLayout.setExpandRatio(goodSmiley, 1);
        horizontalLayout.setExpandRatio(middleSmiley, 1);
        horizontalLayout.setExpandRatio(badSmiley, 1);


        //Beschriftungen der Smileys
        horizontalLayout1 = new HorizontalLayout();
        horizontalLayout1.setWidth("270px");
        horizontalLayout1.setHeight("1px");


        goodLabel = new Label("gut");
        middleLabel = new Label("mäßig");
        badLabel = new Label("stark");

        horizontalLayout1.addComponents(goodLabel, middleLabel, badLabel);
        horizontalLayout1.setComponentAlignment(goodLabel, Alignment.MIDDLE_CENTER);
        horizontalLayout1.setComponentAlignment(middleLabel, Alignment.MIDDLE_CENTER);
        horizontalLayout1.setComponentAlignment(badLabel, Alignment.MIDDLE_CENTER);
        horizontalLayout1.setSpacing(true);


    }
    protected void iterateOverContainers(HasComponents component, int width){
        for(Component c: component){
            if(c instanceof  Label || c instanceof Button){
                continue;
            }
            if(c instanceof HasComponents){
                if(0.25*width < 300){
                    continue;
                }
                c.setWidth(""+0.25*width);
                iterateOverContainers((HasComponents) c, width);
            }else {
                c.setWidth(""+0.05*width);
                c.setHeight(""+0.04*width);
                if(c instanceof Image) {
                    c.setHeight("" + 0.05 * width);
                }
            }
        }
    }
    public Image getGoodSmiley() {
        return goodSmiley;
    }
    public Image getMiddleSmiley() {
        return middleSmiley;
    }
    public Image getBadSmiley() {
        return badSmiley;
    }
    public Label getGoodLabel() {
        return goodLabel;
    }
    public Label getMiddleLabel() {
        return middleLabel;
    }
    public Label getBadLabel() {
        return badLabel;
    }
}
