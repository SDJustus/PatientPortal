package de.tud.view.MedPlan;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.navigator.View;

public class MedPlanView extends Composite implements View {
    private HorizontalLayout menuBar;
    private VerticalLayout verticalLayoutMain;
    private Panel panel;
    private FormLayout form;
    private Label captionLabel;
    private Button saveMedPlan;
    private ComboBox<String> comboBox;
    private TextField textfieldid;
    private TextField textfieldamount;


    public MedPlanView(){
        verticalLayoutMain = new VerticalLayout();
        menuBar = new HorizontalLayout();
        panel = new Panel();
        form = new FormLayout();
        captionLabel = new Label();
        saveMedPlan = new Button();

        //Caption

        captionLabel.setValue("Neue Medikation hinzufügen:");
        captionLabel.addStyleName(MaterialTheme.CARD_0_5);
        captionLabel.addStyleName(MaterialTheme.LABEL_H1);

        //Save-Button

        saveMedPlan.setCaption("Speichern");
        saveMedPlan.setDescription("Speichern der Medikation");
        saveMedPlan.setIcon(new ClassResource("/saveicon.png"));

        //Combobox

        comboBox = new ComboBox<>();
        comboBox.setCaption("Einnahme");
        comboBox.setItems("Morgens","Mittags","Abends");

        //Fields

        textfieldid = new TextField();
        textfieldid.setCaption("ID");
        textfieldamount = new TextField();
        textfieldamount.setCaption("Menge");

        //Panel

        panel.setHeight(""+0.67* Page.getCurrent().getBrowserWindowHeight());
        panel.setWidth(""+0.95*Page.getCurrent().getBrowserWindowWidth());

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            panel.setHeight(""+0.67*e.getHeight());
            panel.setWidth(""+0.95*e.getWidth());
        });

        panel.setContent(form);

        menuBar.addComponents(textfieldid, comboBox, textfieldamount, saveMedPlan);
        menuBar.setMargin(new MarginInfo(false, false, false, true ));

        verticalLayoutMain.addComponents(captionLabel, menuBar, panel);
        setCompositionRoot(verticalLayoutMain);
    }

}
