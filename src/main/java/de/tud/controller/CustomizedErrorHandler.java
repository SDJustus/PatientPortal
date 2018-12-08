package de.tud.controller;

import com.vaadin.server.*;
import com.vaadin.shared.ui.ErrorLevel;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Notification;

import java.sql.SQLException;


public class CustomizedErrorHandler implements ErrorHandler {
    @Override
    public void error(ErrorEvent event) {
        // Finds the original source of the error/exception
        AbstractComponent component = DefaultErrorHandler.findAbstractComponent(event);
        if (component != null) {
            ErrorMessage errorMessage = getErrorMessageForException(event.getThrowable());
            if (errorMessage != null) {
                component.setComponentError(errorMessage);
                new Notification(null, errorMessage.getFormattedHtmlMessage(),
                        Notification.Type.WARNING_MESSAGE, true).show(Page.getCurrent());
                return;
            }
        }
        DefaultErrorHandler.doDefault(event);
    }
    private static ErrorMessage getErrorMessageForException(Throwable t) {
        NullPointerException nullPointerException = getCauseOfType(t, NullPointerException.class);
        if (nullPointerException != null)
            return new UserError(nullPointerException.getLocalizedMessage(),
            AbstractErrorMessage.ContentMode.TEXT, ErrorLevel.ERROR);
        IllegalArgumentException illegalArgumentException = getCauseOfType(t, IllegalArgumentException.class);
        if (illegalArgumentException != null)
            return new UserError(illegalArgumentException.getLocalizedMessage(),
                    AbstractErrorMessage.ContentMode.TEXT, ErrorLevel.ERROR);
        SQLException sqlException = getCauseOfType(t, SQLException.class);
        if (sqlException != null) {
            return new UserError(sqlException.getLocalizedMessage(),
                    AbstractErrorMessage.ContentMode.TEXT, ErrorLevel.ERROR);
        }
        else{
            return new SystemError(t.getMessage(),t);
        }
    }

    private static <T extends Throwable> T getCauseOfType(Throwable th, Class<T> type) {
        while (th != null) {
            if (type.isAssignableFrom(th.getClass())) {
                return (T) th;
            } else {
                th = th.getCause();
            }
        }
        return null;
    }
}
