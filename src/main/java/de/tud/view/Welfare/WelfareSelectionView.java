package de.tud.view.Welfare;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import de.tud.controller.WelfareSelectionController;
import de.tud.model.welfare.PhysicalCondition;
import de.tud.model.welfare.Sleep;
import de.tud.model.welfare.Welfare;
import de.tud.view.ButtonView;


public class WelfareSelectionView extends ButtonView implements View  {

    private WelfareSelectionController welfareSelectionController;
    private HorizontalLayout mainContainer;
    private Image picture;
    private Label label;
    private String imagePath;
    private String name;

    private Class<? extends Welfare> className;


    public WelfareSelectionView(String name){
        this.welfareSelectionController = new WelfareSelectionController(this);

        switch (name){
            case "sleep":
                this.name = "Schlaf:";
                this.imagePath = "/welfareImages/sleep.png";
                this.className = Sleep.class;
                break;

            case "fitness":
                this.name = "Fitness:";
                this.imagePath = "/welfareImages/fitnesspicture.png";
                this.className = PhysicalCondition.class;
                break;
            case "concentration":
                this.name = "Konzentration:";
                this.imagePath = "/welfareImages/concentration.png";
                this.className =  Sleep.class;
                break;
        }

    }

    @Override
    public HorizontalLayout getViewComponent() {
        mainContainer = new HorizontalLayout();
        picture = new Image();
        picture.setSource(new ClassResource(imagePath));

        label = new Label();
        label.setValue(name);

        goodSmiley.setWidth("80px");
        goodSmiley.setHeight("80px");
        badSmiley.setHeight("80px");
        badSmiley.setWidth("80px");
        middleSmiley.setHeight("80px");
        middleSmiley.setWidth("80px");

        addClickListenerForSmileys();

        VerticalLayout goodLayout = new VerticalLayout();
        goodLayout.addComponents(goodSmiley, goodLabel);
        goodLayout.setSpacing(true);
        goodLayout.setComponentAlignment(goodLabel, Alignment.MIDDLE_CENTER);
        goodLayout.setComponentAlignment(goodSmiley, Alignment.MIDDLE_CENTER);
        goodLayout.setSizeUndefined();

        VerticalLayout middleLayout = new VerticalLayout();
        middleLayout.addComponents(middleSmiley, middleLabel);
        middleLayout.setComponentAlignment(middleSmiley, Alignment.MIDDLE_CENTER);
        middleLayout.setComponentAlignment(middleLabel, Alignment.MIDDLE_CENTER);
        middleLayout.setSpacing(true);
        middleLayout.setSizeUndefined();


        VerticalLayout badLayout = new VerticalLayout();
        badLayout.addComponents(badSmiley, badLabel);
        badLayout.setComponentAlignment(badLabel, Alignment.MIDDLE_CENTER);
        badLayout.setComponentAlignment(badSmiley, Alignment.MIDDLE_CENTER);
        badLayout.setSpacing(true);
        badLayout.setSizeUndefined();

        //GridLayout buttons = new GridLayout(3,1);
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(goodLayout, middleLayout, badLayout);


        HorizontalLayout leftContainer = new HorizontalLayout();
        leftContainer.addComponents(picture, label);
        leftContainer.addStyleName("fixed");
        leftContainer.setComponentAlignment(picture, Alignment.BOTTOM_CENTER);
        leftContainer.setComponentAlignment(label, Alignment.BOTTOM_LEFT);



        mainContainer.setMargin(true);
        mainContainer.addStyleName(MaterialTheme.CARD_1);

        HorizontalLayout spacer = new HorizontalLayout();
        spacer.setWidth("50px");


        mainContainer.addComponents(leftContainer, buttons, spacer);
        mainContainer.setWidth("85%");



        return mainContainer;
    }
    public Class<? extends Welfare> getWelfare() {
        return className;
    }
    private void addClickListenerForSmileys(){
        welfareSelectionController.addClickListenerForSmileys();
    }
    public WelfareSelectionController getWelfareSelectionController() {
        return welfareSelectionController;
    }
}
