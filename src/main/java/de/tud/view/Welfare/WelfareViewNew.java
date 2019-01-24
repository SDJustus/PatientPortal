package de.tud.view.Welfare;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.*;
import de.tud.controller.WelfareControllerNew;

import java.time.LocalDateTime;

public class WelfareViewNew implements View {

    private VerticalLayout mainLayout;
    private HorizontalLayout dateSaveLayout;
    private HorizontalLayout concentrationLayout;
    private HorizontalLayout fitnessLayout;
    private HorizontalLayout sleepLayout;
    private VerticalLayout cSmileyGoodLayout;
    private VerticalLayout fSmileyGoodLayout;
    private VerticalLayout sSmileyGoodLayout;
    private VerticalLayout cSmileyMiddleLayout;
    private VerticalLayout fSmileyMiddleLayout;
    private VerticalLayout sSmileyMiddleLayout;
    private VerticalLayout cSmileyBadLayout;
    private VerticalLayout fSmileyBadLayout;
    private VerticalLayout sSmileyBadLayout;
    private Label captionLabel;
    private DateTimeField dateField;
    private Button saveButton;
    private Button resetButton;
    private Image concentrationImage;
    private Image fitnessImage;
    private Image sleepImage;
    private Label concentrationLabel;
    private Label fitnessLabel;
    private Label sleepLabel;
    private Image cSmileyGood;
    private Image cSmileyMiddle;
    private Image cSmileyBad;
    private Image fSmileyGood;
    private Image fSmileyMiddle;
    private Image fSmileyBad;
    private Image sSmileyGood;
    private Image sSmileyMiddle;
    private Image sSmileyBad;
    private Label cLabelGood;
    private Label cLabelMiddle;
    private Label cLabelBad;
    private Label fLabelGood;
    private Label fLabelMiddle;
    private Label fLabelBad;
    private Label sLabelGood;
    private Label sLabelMiddle;
    private Label sLabelBad;

    private WelfareControllerNew welfareControllerNew;

    public WelfareViewNew() { this.welfareControllerNew = new WelfareControllerNew(this); }

    @Override
    /**
     * Setup all welfare components.
     */

    public Component getViewComponent() {

        UI.getCurrent().getPage().getStyles().add("#smileybild:hover{transform: scale(1.2);}"+
                "#smileybild:{transition: transform .2s;}+" +
                ".v-panel{padding-bottom: 80px;}"+
                "#header-label{font-weight: bold; font-size:40px;}" +
                "#greyscale{filter: grayscale(100%);" +
                "-webkit-filter: grayscale(100%);" +
                "-moz-filter: grayscale(100%);" +
                "-ms-filter: grayscale(100%);" +
                "-o-filter:grayscale(100%);" +
                "filter: url(desaturate.svg#greyscale);" +
                "filter: gray;" +
                "-webkit-filter: grayscale(1);}");

        mainLayout = new VerticalLayout();
        dateSaveLayout = new HorizontalLayout();
        concentrationLayout = new HorizontalLayout();
        fitnessLayout = new HorizontalLayout();
        sleepLayout = new HorizontalLayout();
        cSmileyGoodLayout = new VerticalLayout();
        fSmileyGoodLayout = new VerticalLayout();
        sSmileyGoodLayout = new VerticalLayout();
        cSmileyMiddleLayout = new VerticalLayout();
        fSmileyMiddleLayout = new VerticalLayout();
        sSmileyMiddleLayout = new VerticalLayout();
        cSmileyBadLayout = new VerticalLayout();
        fSmileyBadLayout = new VerticalLayout();
        sSmileyBadLayout = new VerticalLayout();
        captionLabel = new Label("Neuer Eintrag zum Wohlbefinden");
        dateField = new DateTimeField();
        saveButton = new Button("Speichern");
        resetButton = new Button("Alles zurücksetzen");
        concentrationImage = new Image();
        fitnessImage = new Image();
        sleepImage = new Image();
        concentrationLabel = new Label("Konzentration:");
        fitnessLabel = new Label("Fitness:");
        sleepLabel = new Label("Schlaf:");
        cSmileyGood = new Image();
        cSmileyMiddle = new Image();
        cSmileyBad = new Image();
        fSmileyGood = new Image();
        fSmileyMiddle = new Image();
        fSmileyBad = new Image();
        sSmileyGood = new Image();
        sSmileyMiddle = new Image();
        sSmileyBad = new Image();
        cLabelGood = new Label("Gut");
        cLabelMiddle = new Label("Mäßig");
        cLabelBad = new Label("Schlecht");
        fLabelGood = new Label("Gut");
        fLabelMiddle = new Label("Mäßig");
        fLabelBad = new Label("Schlecht");
        sLabelGood = new Label("Gut");
        sLabelMiddle = new Label("Mäßig");
        sLabelBad = new Label("Schlecht");

        captionLabel.setId("header-label");
        captionLabel.addStyleName(MaterialTheme.LABEL_H1);

        dateField.setTextFieldEnabled(false);
        dateField.setDefaultValue( LocalDateTime.now());
        dateField.setRangeEnd(LocalDateTime.now());
        welfareControllerNew.addDateFieldListener();

        saveButton.setDescription("Speichern des Wohlbefindens");
        saveButton.setIcon(new ClassResource("/saveicon.png"));
        saveButton.setEnabled(false);
        welfareControllerNew.addSaveButtonListener();

        resetButton.setDescription("Eingaben zurücksetzen");
        welfareControllerNew.addResetButtonListener();

        concentrationLayout.addStyleName("layoutwithborder");
        concentrationLayout.setSpacing(true);
        concentrationLayout.setMargin(true);
        concentrationLayout.addStyleName(MaterialTheme.CARD_1);

        fitnessLayout.addStyleName("layoutwithborder");
        fitnessLayout.setSpacing(true);
        fitnessLayout.setMargin(true);
        fitnessLayout.addStyleName(MaterialTheme.CARD_1);

        sleepLayout.addStyleName("layoutwithborder");
        sleepLayout.setSpacing(true);
        sleepLayout.setMargin(true);
        sleepLayout.addStyleName(MaterialTheme.CARD_1);

        concentrationImage.setSource(new ClassResource("/welfareImages/concentration.png"));
        fitnessImage.setSource(new ClassResource("/welfareImages/fitnesspicture.png"));
        sleepImage.setSource(new ClassResource("/welfareImages/sleep.png"));

        concentrationLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
        concentrationLabel.setWidth("100px");
        fitnessLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
        fitnessLabel.setWidth("100px");
        sleepLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
        sleepLabel.setWidth("100px");

        cSmileyGood.setWidth("90px");
        cSmileyGood.setHeight("90px");
        cSmileyMiddle.setWidth("90px");
        cSmileyMiddle.setHeight("90px");
        cSmileyBad.setWidth("90px");
        cSmileyBad.setHeight("90px");
        fSmileyGood.setWidth("90px");
        fSmileyGood.setHeight("90px");
        fSmileyMiddle.setWidth("90px");
        fSmileyMiddle.setHeight("90px");
        fSmileyBad.setWidth("90px");
        fSmileyBad.setHeight("90px");
        sSmileyGood.setWidth("90px");
        sSmileyGood.setHeight("90px");
        sSmileyMiddle.setWidth("90px");
        sSmileyMiddle.setHeight("90px");
        sSmileyBad.setWidth("90px");
        sSmileyBad.setHeight("90px");
        cSmileyGood.setStyleName("smileybild");
        cSmileyMiddle.setStyleName("smileybild");
        cSmileyBad.setStyleName("smileybild");
        fSmileyGood.setStyleName("smileybild");
        fSmileyMiddle.setStyleName("smileybild");
        fSmileyBad.setStyleName("smileybild");
        sSmileyGood.setStyleName("smileybild");
        sSmileyMiddle.setStyleName("smileybild");
        sSmileyBad.setStyleName("smileybild");
        cSmileyGood.setSource(new ClassResource("/gut.png"));
        cSmileyMiddle.setSource(new ClassResource("/mittel.png"));
        cSmileyBad.setSource(new ClassResource("/schlecht.png"));
        fSmileyGood.setSource(new ClassResource("/gut.png"));
        fSmileyMiddle.setSource(new ClassResource("/mittel.png"));
        fSmileyBad.setSource(new ClassResource("/schlecht.png"));
        sSmileyGood.setSource(new ClassResource("/gut.png"));
        sSmileyMiddle.setSource(new ClassResource("/mittel.png"));
        sSmileyBad.setSource(new ClassResource("/schlecht.png"));
        welfareControllerNew.addcSmileyClickListener();
        welfareControllerNew.addfSmileyClickListener();
        welfareControllerNew.addsSmileyClickListener();

        dateSaveLayout.addComponents(dateField, saveButton, resetButton);
        cSmileyGoodLayout.addComponents(cSmileyGood, cLabelGood);
        cSmileyMiddleLayout.addComponents(cSmileyMiddle, cLabelMiddle);
        cSmileyBadLayout.addComponents(cSmileyBad, cLabelBad);
        fSmileyGoodLayout.addComponents(fSmileyGood, fLabelGood);
        fSmileyMiddleLayout.addComponents(fSmileyMiddle, fLabelMiddle);
        fSmileyBadLayout.addComponents(fSmileyBad, fLabelBad);
        sSmileyGoodLayout.addComponents(sSmileyGood, sLabelGood);
        sSmileyMiddleLayout.addComponents(sSmileyMiddle, sLabelMiddle);
        sSmileyBadLayout.addComponents(sSmileyBad, sLabelBad);
        cSmileyGoodLayout.setComponentAlignment(cLabelGood, Alignment.BOTTOM_CENTER);
        cSmileyMiddleLayout.setComponentAlignment(cLabelMiddle, Alignment.BOTTOM_CENTER);
        cSmileyBadLayout.setComponentAlignment(cLabelBad, Alignment.BOTTOM_CENTER);
        fSmileyGoodLayout.setComponentAlignment(fLabelGood, Alignment.BOTTOM_CENTER);
        fSmileyMiddleLayout.setComponentAlignment(fLabelMiddle, Alignment.BOTTOM_CENTER);
        fSmileyBadLayout.setComponentAlignment(fLabelBad, Alignment.BOTTOM_CENTER);
        sSmileyGoodLayout.setComponentAlignment(sLabelGood, Alignment.BOTTOM_CENTER);
        sSmileyMiddleLayout.setComponentAlignment(sLabelMiddle, Alignment.BOTTOM_CENTER);
        sSmileyBadLayout.setComponentAlignment(sLabelBad, Alignment.BOTTOM_CENTER);
        concentrationLayout.addComponents(concentrationImage, concentrationLabel, cSmileyGoodLayout, cSmileyMiddleLayout, cSmileyBadLayout);
        concentrationLayout.setComponentAlignment(concentrationLabel, Alignment.MIDDLE_LEFT);
        concentrationLayout.setComponentAlignment(concentrationImage, Alignment.MIDDLE_LEFT);
        fitnessLayout.addComponents(fitnessImage, fitnessLabel, fSmileyGoodLayout, fSmileyMiddleLayout, fSmileyBadLayout);
        fitnessLayout.setComponentAlignment(fitnessLabel, Alignment.MIDDLE_LEFT);
        fitnessLayout.setComponentAlignment(fitnessImage, Alignment.MIDDLE_LEFT);
        sleepLayout.addComponents(sleepImage, sleepLabel, sSmileyGoodLayout, sSmileyMiddleLayout, sSmileyBadLayout);
        sleepLayout.setComponentAlignment(sleepLabel, Alignment.MIDDLE_LEFT);
        sleepLayout.setComponentAlignment(sleepImage, Alignment.MIDDLE_LEFT);
        mainLayout.addComponents(captionLabel, dateSaveLayout, concentrationLayout, fitnessLayout, sleepLayout);

        return mainLayout;

    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public Image getcSmileyGood() {
        return cSmileyGood;
    }

    public Image getcSmileyMiddle() {
        return cSmileyMiddle;
    }

    public Image getcSmileyBad() {
        return cSmileyBad;
    }

    public Image getfSmileyGood() {
        return fSmileyGood;
    }

    public Image getfSmileyMiddle() {
        return fSmileyMiddle;
    }

    public Image getfSmileyBad() {
        return fSmileyBad;
    }

    public Image getsSmileyGood() {
        return sSmileyGood;
    }

    public Image getsSmileyMiddle() {
        return sSmileyMiddle;
    }

    public Image getsSmileyBad() {
        return sSmileyBad;
    }

    public Label getcLabelGood() {
        return cLabelGood;
    }

    public Label getcLabelMiddle() {
        return cLabelMiddle;
    }

    public Label getcLabelBad() {
        return cLabelBad;
    }

    public Label getfLabelGood() {
        return fLabelGood;
    }

    public Label getfLabelMiddle() {
        return fLabelMiddle;
    }

    public Label getfLabelBad() {
        return fLabelBad;
    }

    public Label getsLabelGood() {
        return sLabelGood;
    }

    public Label getsLabelMiddle() {
        return sLabelMiddle;
    }

    public Label getsLabelBad() {
        return sLabelBad;
    }

    public DateTimeField getDateField() { return dateField; }

}
