package de.tud.view.Welfare;

import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import de.tud.controller.WelfareController;

public class WelfareView extends Composite implements View {

    private HorizontalLayout menuBar;
    private VerticalLayout verticalLayoutMain;
    private VerticalLayout contentLayout;
    private Panel panel;
    private Label captionLabel;
    private Button save;
    private DateTimeField dateTimeField;
    private Button resetButton;

    private WelfareController welfareController;

    public WelfareView(){
        welfareController = new WelfareController(this);

        //CSS //TODO: outsource CSS
        UI.getCurrent().getPage().getStyles().add("#greyscale{filter: grayscale(100%);" +
                "-webkit-filter: grayscale(100%);" +
                "-moz-filter: grayscale(100%);" +
                "-ms-filter: grayscale(100%);" +
                "-o-filter:grayscale(100%);" +
                "filter: url(desaturate.svg#greyscale);" +
                "filter: gray;" +
                "-webkit-filter: grayscale(1);}");

        verticalLayoutMain = new VerticalLayout();
        menuBar = new HorizontalLayout();
        panel = new Panel();
        save = new Button();
        captionLabel = new Label();
        dateTimeField = new DateTimeField();
        dateTimeField.setTextFieldEnabled(false);

        contentLayout = new VerticalLayout();
        contentLayout.setMargin(true);
        contentLayout.setSpacing(true);

        resetButton = new Button("Alles Zurücksetzen");

        menuBar.addComponents(dateTimeField, save, resetButton);
        menuBar.setMargin(new MarginInfo(false, false, false, true ));

        save.setCaption("Speichern");
        save.setDescription("Speichern der Vitaldaten");
        save.setIcon(new ClassResource("/saveicon.png"));
        panel.setContent(contentLayout);
        panel.setSizeFull();

        verticalLayoutMain.addComponents(captionLabel, menuBar, panel);


        addSaveButtonListener();
        addDateTimeFieldChangeListener();
        setCompositionRoot(verticalLayoutMain);
        addResetButtonListener();
        welfareController.initWelfare();

    }


    public VerticalLayout getContentLayout() {
        return contentLayout;
    }

    public Button getSave() {
        return save;
    }

    public DateTimeField getDateTimeField() {
        return dateTimeField;
    }

    public Panel getPanel() {
        return panel;
    }

    private void addSaveButtonListener(){
        welfareController.addSaveButtonListener();
    }

    private void addDateTimeFieldChangeListener(){
        welfareController.addDateTimeFieldChangeListener();
    }

    public Button getResetButton() {
        return resetButton;
    }
    private void addResetButtonListener(){
        welfareController.addResetButtonListener();
    }
}
