package com.spectral369.birotica;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.spectral369.utils.Models;
import com.spectral369.utils.Utils;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Index extends CustomComponent implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "Index";
	VerticalLayout content;
	HorizontalLayout logoLayout;
	Image logo;
	HorizontalLayout titleLayout;
	HorizontalLayout comboLayout;
	Button title;
	ComboBox<String> combo;
	HorizontalLayout footerLayout;
	Button footer;
	HorizontalLayout submitLayout;
	Button submit;
	Component currSel;

	public Index() {
		content = new VerticalLayout();

		logoLayout = new HorizontalLayout();
		logo = new Image("", new FileResource(Utils.getImage("dvlogo")));
		logo.setDescription("DVlogo");
		logo.setHeight(Page.getCurrent().getBrowserWindowHeight() / 8, Unit.PIXELS);
		logoLayout.addComponent(logo);
		content.addComponent(logoLayout);
		content.setComponentAlignment(logoLayout, Alignment.MIDDLE_CENTER);

		titleLayout = new HorizontalLayout();
		title = new Button("MODELE CERERI, DECLARATII SI ADEVERINTE", VaadinIcons.PAPERCLIP);
		title.setEnabled(false);
		title.setId("mainTitle");
		title.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		titleLayout.addComponent(title);
		titleLayout.setMargin(true);/*
									 * pentru margini custom e nevoie de css button.addStyleName("spacey-button");
									 * .spacey-button { margin-top: 20em; }
									 */
		content.addComponent(titleLayout);
		content.setComponentAlignment(titleLayout, Alignment.MIDDLE_CENTER);

		comboLayout = new HorizontalLayout();
		combo = new ComboBox<>();
		combo.setTextInputAllowed(true);
		combo.setRequiredIndicatorVisible(true);
		combo.setResponsive(true);
		combo.setId("combo");
		// combo.setEmptySelectionCaption("Cauta ...");
		combo.setPlaceholder("Cauta ...");
		combo.setIcon(VaadinIcons.SEARCH);
		// combo.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);
		combo.setItems(Models.getNames());
		// combo.setEmptySelectionAllowed(false);
		combo.setWidth(Page.getCurrent().getBrowserWindowWidth() / 3, Unit.PIXELS);
		combo.setItemIconGenerator(item -> VaadinIcons.FILE_TEXT_O);// VaadinIcons.DIPLOMA
		combo.addSelectionListener(evt -> {
			if(evt.getValue()==null) {
				submit.setEnabled(false);
			}
			if (evt.getValue() != null) {
				submit.setEnabled(true);
				
				int cid =  Models.getIDFromName(evt.getValue());
				Class<?> clazz;
				try {
					clazz = Class.forName(Models.getClassName(cid));
				
				Constructor<?> constructor = clazz.getConstructor();
				Object obj = constructor.newInstance();
				if(obj instanceof CustomComponent  ob)
					currSel =  ob;
				} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				
					e.printStackTrace();
				}
				
			}
		});
		comboLayout.addComponent(combo);
		content.addComponent(comboLayout);
		// comboLayout.setComponentAlignment(combo, Alignment.MIDDLE_CENTER);
		// comboLayout.setSizeFull();
		// comboLayout.setHeight(Page.getCurrent().getBrowserWindowHeight(),Unit.PIXELS);

		content.setComponentAlignment(comboLayout, Alignment.TOP_CENTER);

		submitLayout = new HorizontalLayout();
		submit = new Button("OK", VaadinIcons.CHECK_CIRCLE);
		submit.addStyleName(ValoTheme.BUTTON_PRIMARY);
		submit.setEnabled(false);
		submit.addClickListener(evt -> {
			
			String value = "";
			try {
				Field field = currSel.getClass().getDeclaredField("NAME");
				field.setAccessible(true);
				value =(String)  field.get(currSel);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				
				e.printStackTrace();
			}
			if(value!="")
				UI.getCurrent().getNavigator().navigateTo(value);
			
		});
		submitLayout.addComponent(submit);
		content.addComponent(submitLayout);
		content.setComponentAlignment(submitLayout, Alignment.MIDDLE_CENTER);

		footerLayout = new HorizontalLayout();
		footer = new Button("spectral369 2021", VaadinIcons.COPYRIGHT);
		footer.setEnabled(false);
		footer.setId("footer");
		footer.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		footerLayout.addComponent(footer);
		content.addComponent(footerLayout);
		content.setComponentAlignment(footerLayout, Alignment.BOTTOM_CENTER);

		content.setWidth(Page.getCurrent().getBrowserWindowWidth(), Unit.PIXELS);
		content.setHeight(Page.getCurrent().getBrowserWindowHeight(), Unit.PIXELS);
		content.setExpandRatio(logoLayout, 1);
		content.setExpandRatio(titleLayout, 1);
		content.setExpandRatio(comboLayout, 1);
		content.setExpandRatio(submitLayout, 1);
		content.setExpandRatio(footerLayout, 1);

		content.setMargin(false);

		setCompositionRoot(content);

	}

	public void resetCombo() {
		// UI.getCurrent().getPage().getJavaScript().execute("$('#combo')[0].selectedIndex
		// = 0;");
		combo.setValue(null);
	}

}
