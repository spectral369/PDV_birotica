package com.spectral369.birotica;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@PushStateNavigation
@Push(transport = Transport.WEBSOCKET_XHR)
@Theme("mytheme")
public class MyUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Index index;
	Panel viewContainer;
	VerticalLayout layout;
	public Navigator navigator;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Page.getCurrent().setTitle("Birotica");
		

		
		
		 layout = new VerticalLayout();
		layout.setMargin(false);
		index = new Index();
		layout.addComponent(index);
		viewContainer = new Panel(layout);
		navigator = new Navigator(this, viewContainer);
		navigator.addView("", index);
		navigator.addView(Index.NAME, index);
		navigator.setErrorView(index);
		navigator.addViewChangeListener(new ViewChangeListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				// TODO Auto-generated method stub
				//System.out.println(event.getViewName());
				if(event.getViewName()==Index.NAME) {
					index.resetCombo();
				}
			
				
				return true;
			}
		});

		setContent(viewContainer);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
}
