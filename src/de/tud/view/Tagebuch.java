import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;

/**
 * !! DO NOT EDIT THIS FILE !!
 * <p>
 * This class is generated by Vaadin Designer and will be overwritten.
 * <p>
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class Tagebuch extends HorizontalLayout {
    protected DateTimeField datePicker;
    protected VerticalLayout verticalLayout;
    protected Image goodSmiley;
    protected Image middleSmiley;
    protected Image badSmiley;
    protected Label goodlabel;
    protected Label middlelabel;
    protected Label badlabel;
    protected Label moodLabel;
    protected Button saveButton;
    protected Grid<de.tud.model.Diary> table;

    public Tagebuch() {
        Design.read(this);
    }
}
