package de.tud.view.Welfare;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import de.tud.controller.WelfareSelectionController;
import de.tud.view.ButtonView;


public class WelfareSelectionView extends ButtonView implements View  {

    private WelfareSelectionController welfareSelectionController;
    private HorizontalLayout mainContainer;
    private Image picture;
    private Label label;
    private String imagePath;
    private String name;



    public WelfareSelectionView(String name){
        //this.welfareSelectionController = new WelfareSelectionController();

        switch (name){
            case "sleep":
                this.name = "Schlaf:";
                this.imagePath = "/welfareImages/sleep.png";
                break;

            case "fitness":
                this.name = "Fitness:";
                this.imagePath = "/welfareImages/fitnesspicture.png";
                break;
            case "concentration":
                this.name = "Konzentration:";
                this.imagePath = "/welfareImages/concentration.png";
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

        VerticalLayout goodLayout = new VerticalLayout();
        goodLayout.addComponents(goodSmiley, goodLabel);
        goodLayout.setSpacing(true);
        goodLayout.setComponentAlignment(goodLabel, Alignment.MIDDLE_CENTER);
        goodLayout.setComponentAlignment(goodSmiley, Alignment.MIDDLE_CENTER);
        goodLayout.setStyleName("fixed");

        VerticalLayout middleLayout = new VerticalLayout();
        middleLayout.addComponents(middleSmiley, middleLabel);
        middleLayout.setComponentAlignment(middleSmiley, Alignment.MIDDLE_CENTER);
        middleLayout.setComponentAlignment(middleLabel, Alignment.MIDDLE_CENTER);
        middleLayout.setSpacing(true);
        middleLayout.setStyleName("fixed");


        VerticalLayout badLayout = new VerticalLayout();
        badLayout.addComponents(badSmiley, badLabel);
        badLayout.setComponentAlignment(badLabel, Alignment.MIDDLE_CENTER);
        badLayout.setComponentAlignment(badSmiley, Alignment.MIDDLE_CENTER);
        badLayout.setSpacing(true);
        badLayout.setStyleName("fixed");


        HorizontalLayout leftContainer = new HorizontalLayout();
        leftContainer.addComponents(picture, label);
        leftContainer.setComponentAlignment(picture, Alignment.MIDDLE_CENTER);
        leftContainer.setComponentAlignment(label, Alignment.BOTTOM_CENTER);


        mainContainer.addStyleName("layoutwithborder");
        mainContainer.setMargin(true);
        mainContainer.addStyleName(MaterialTheme.CARD_1);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponents(goodLayout, middleLayout, badLayout);


        mainContainer.addComponents(leftContainer, goodLayout, middleLayout, badLayout, new HorizontalLayout());
        mainContainer.setWidth("100%");



        return mainContainer;
    }
}
