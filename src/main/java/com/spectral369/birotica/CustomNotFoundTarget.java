package com.spectral369.birotica;


import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouteNotFoundError;

@ParentLayout(MainView.class)
public class CustomNotFoundTarget extends RouteNotFoundError {

    /**
     * 
     */
    private static final long serialVersionUID = 3039412172140255224L;

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
	System.out.println("not found " + event.getErrorParameter() + " " + parameter.getCaughtException());
	Notification.show("Path Not available!", 3000, Position.BOTTOM_END);

	return jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
    }
}