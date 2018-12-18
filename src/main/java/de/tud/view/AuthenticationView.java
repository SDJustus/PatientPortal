package de.tud.view;


import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.*;
import de.tud.controller.PatientPortalController;


public class AuthenticationView extends Composite implements View {
    HorizontalLayout horizontalLayout = new HorizontalLayout();

    VerticalLayout verticalLayout = new VerticalLayout();
    TextField username = new TextField();
    PasswordField passwordField = new PasswordField();
    Label user = new Label("Benutzername:");
    Label pw = new Label("Passwort:");
    Button login = new Button("Login");
    Image people = new Image("", new ClassResource("/people.png"));

    private PatientPortalController patientPortalController;


    public AuthenticationView(PatientPortalController patientPortalController){
       this.patientPortalController = patientPortalController;
    }

    @Override
    public Component getViewComponent() {
        horizontalLayout.addComponents(verticalLayout);
        username.setPlaceholder("Benutzername");
        passwordField.setPlaceholder("Passwort");

        people.setWidth("200px");
        people.setHeight("200px");

        username.setWidth("250px");
        passwordField.setWidth("250px");


        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        addLoginButtonListener();


        verticalLayout.addComponents(people, user,username,pw ,passwordField, login);

        verticalLayout.setComponentAlignment(people, Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(login, Alignment.MIDDLE_CENTER);

        verticalLayout.setSpacing(true);
        verticalLayout.setSizeUndefined();
        horizontalLayout.setComponentAlignment(verticalLayout, Alignment.MIDDLE_CENTER);


        horizontalLayout.setHeight("100%");
        horizontalLayout.setWidth("100%");

        return  horizontalLayout;
    }

    private void addLoginButtonListener(){
        patientPortalController.addLoginButtonListener();
    }
    public TextField getUsername() {
        return username;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLogin() {
        return login;
    }

}
