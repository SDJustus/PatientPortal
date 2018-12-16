package de.tud.controller;


import com.github.appreciated.app.layout.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.builders.SubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.interceptor.DefaultViewNameInterceptor;
import com.github.appreciated.material.MaterialTheme;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.TreeData;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.navigator.Navigator;
import de.tud.view.*;
import de.tud.view.DiaryEvaluation.DiaryEvaluationView;
import de.tud.view.Homework.HomeworkView;
import de.tud.view.VitalData.VitalDataView;
import de.tud.view.Welfare.WelfareView;


import javax.servlet.annotation.WebServlet;


@Theme("mytheme")


public class PatientPortalMenu extends UI {


    @Override
    public void init(VaadinRequest request) {

        //UI.getCurrent().setErrorHandler(new CustomizedErrorHandler());
        //CSS Befehle
        UI.getCurrent().getPage().getStyles().add(".v-button{text-align: left !important;}" +
                ".v-label{font-size: large !important; }"+
                "#smileybild:hover{transform: scale(1.2);}"+
                "#smileybild:{transition: transform .2s;}"+
                "#profilbild{display: block !important; margin: 0 auto !important;}");



        setContent(buildMenu());
        

    }
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = PatientPortalMenu.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    @Override
    protected void refresh(VaadinRequest request) {
        super.refresh(request);

    }
    private Component buildMenu(){
        DefaultNotificationHolder notifications = new DefaultNotificationHolder();
        DefaultBadgeHolder badge = new DefaultBadgeHolder();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Image profilbild = new Image("", new ClassResource("/profilbild.png"));
        profilbild.setHeight("120px");
        profilbild.setWidth("120px");

        horizontalLayout.addComponents(profilbild);
        horizontalLayout.setComponentAlignment(horizontalLayout.getComponent(0),Alignment.MIDDLE_CENTER);
        horizontalLayout.setWidth("256px");

        return AppLayout.getDefaultBuilder(Behaviour.LEFT_RESPONSIVE_OVERLAY_NO_APP_BAR)
                .withNavigator(container -> new Navigator(this, container))
                .withViewNameInterceptor(new DefaultViewNameInterceptor())
                .withDefaultNavigationView(StartView.class)
                .withDesign(AppLayoutDesign.DEFAULT)
                .withNavigatorConsumer(navigator -> {/* Do something with it */})
                .add(horizontalLayout)
                .add("Home", VaadinIcons.HOME, badge, StartView.class)
                .add(SubmenuBuilder.get("Patiententagebuch", VaadinIcons.BOOK)
                        .add("Auswertung", VaadinIcons.TABLE, DiaryEvaluationView.class)
                        .add("Vitaldaten", VaadinIcons.HEART, VitalDataView.class)
                        .add("Wohlbefinden", VaadinIcons.SMILEY_O, WelfareView.class)
                        .add("Symptomeintrag", VaadinIcons.DOCTOR_BRIEFCASE, DiaryView.class)
                        .build())
                .add("Hausaufgaben", VaadinIcons.LIST, HomeworkView.class)
                .add("Medikationsplan", VaadinIcons.PILLS, StartView.class)
                .build();
    }

}