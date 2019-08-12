package com.spectral369.CDR;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.spectral369.birotica.Index;
import com.spectral369.birotica.MyUI;
import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Setter;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class CDRInfo extends CustomComponent implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "CDR";
	VerticalLayout content;
	HorizontalLayout backLayout;
	Button backbtn;
	HorizontalLayout titlelLayout;
	Button CDRTitle;
	HorizontalLayout checkLayout;
	CheckBox complete;

	HorizontalLayout infoLayout;
	VerticalLayout infoPart1;
	VerticalLayout infoPart2;
	VerticalLayout infoPart3;
	VerticalLayout infoPart4;

	TextField nr1Field;
	DateField dateField;
	TextField catreField;
	TextField denumireaAutoritatii;

	HorizontalLayout generateLayout;
	Button generate;

	CDRPDF pdf;
	Map<String, String> map;

	 private Binder<CDRInfo> binder;

	public CDRInfo() {

		content = new VerticalLayout();
		map = new HashMap<>();
		binder = new Binder<>(CDRInfo.class);
		backLayout = new HorizontalLayout();
		backLayout.setMargin(true);
		backbtn = new Button("Back", VaadinIcons.ARROW_CIRCLE_LEFT);
		backbtn.addStyleName(ValoTheme.BUTTON_QUIET);
		backbtn.addClickListener(evt -> {

			MyUI.navigator.navigateTo(Index.NAME);
		});
		backLayout.addComponent(backbtn);
		content.addComponent(backLayout);
		content.setComponentAlignment(backLayout, Alignment.MIDDLE_LEFT);

		titlelLayout = new HorizontalLayout();
		CDRTitle = new Button("Cerere de Restituire", VaadinIcons.FILE_TEXT_O);
		CDRTitle.setEnabled(false);
		CDRTitle.setId("mainTitle");
		CDRTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		titlelLayout.addComponent(CDRTitle);
		content.addComponent(titlelLayout);
		content.setComponentAlignment(titlelLayout, Alignment.MIDDLE_CENTER);

		checkLayout = new HorizontalLayout();
		complete = new CheckBox("Cu completare de date ?!", false);
		complete.addValueChangeListener(evt -> {
			toggleVisibility();
			UI.getCurrent().push();
		});

		checkLayout.addComponent(complete);
		content.addComponent(checkLayout);
		content.setComponentAlignment(checkLayout, Alignment.MIDDLE_CENTER);

		// info
		infoLayout = new HorizontalLayout();
		infoLayout.setVisible(false);

		infoPart1 = new VerticalLayout();
		nr1Field = new TextField("Nr:");
		nr1Field.setRequiredIndicatorVisible(true);
		infoPart1.addComponent(nr1Field);
		infoLayout.addComponent(infoPart1);
		infoLayout.setComponentAlignment(infoPart1, Alignment.MIDDLE_CENTER);
		// todo

		infoPart2 = new VerticalLayout();
		dateField = new DateField("Data:");
		dateField.setRequiredIndicatorVisible(true);
		dateField.setDateFormat("dd-MM-yyyy");
		binder.forField(dateField).asRequired()
/*withValidator(str -> str.toString()>4, "Password must be at least 4 chars long")*/
		.bind(new ValueProvider<CDRInfo, LocalDate>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public LocalDate apply(CDRInfo source) {
				return null;
			}
		}, new Setter<CDRInfo, LocalDate>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(CDRInfo bean, LocalDate fieldvalue) {

			}
		});
		 dateField.setErrorHandler(event -> generate.setEnabled(false));
	     dateField.addValueChangeListener(event -> generate.setEnabled(event.getValue() != null));
	    dateField.setResponsive(true);
	    dateField.setComponentError(new UserError("Date Required !"));
		infoPart2.addComponent(dateField);
		infoLayout.addComponent(infoPart2);
		infoLayout.setComponentAlignment(infoPart2, Alignment.MIDDLE_CENTER);

		///
		infoPart3 = new VerticalLayout();
		catreField = new TextField("Catre:");
		catreField.setRequiredIndicatorVisible(true);
		infoPart3.addComponent(catreField);
		infoLayout.addComponent(infoPart3);
		infoLayout.setComponentAlignment(infoPart3, Alignment.MIDDLE_CENTER);
		//

		infoPart4 = new VerticalLayout();
		denumireaAutoritatii = new TextField("Denumirea Autoritatii:");
		denumireaAutoritatii.setRequiredIndicatorVisible(true);
		infoPart4.addComponent(denumireaAutoritatii);
		infoLayout.addComponent(infoPart4);
		infoLayout.setComponentAlignment(infoPart4, Alignment.MIDDLE_CENTER);

		content.addComponent(infoLayout);
		content.setComponentAlignment(infoLayout, Alignment.MIDDLE_CENTER);

		generateLayout = new HorizontalLayout();
		generate = new Button("Generate", VaadinIcons.FILE_PROCESS);
		generate.addStyleNames(ValoTheme.BUTTON_PRIMARY, ValoTheme.BUTTON_FRIENDLY);
		generate.addClickListener(evt -> {
			if (complete.getValue()) {
				map.put("nr1", nr1Field.getValue().trim());
				map.put("data", dateField.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
				map.put("catre", catreField.getValue());
				map.put("autoritateSuma", denumireaAutoritatii.getValue());
			}
			nr1Field.clear();
			dateField.clear();
			catreField.clear();
			denumireaAutoritatii.clear();

			pdf = new CDRPDF(map);
			MyUI.navigator.navigateTo(CDRPDF.NAME);
		});
		generateLayout.addComponent(generate);
		 generate.setEnabled(false);
		 binder.addStatusChangeListener(event -> {
	            if (!this.complete.getValue() || this.binder.isValid()) {
	                this.generate.setEnabled(true);
	            }
	            else {
	                this.generate.setEnabled(false);
	            }
	        });
		content.addComponent(generateLayout);
		content.setComponentAlignment(generateLayout, Alignment.MIDDLE_CENTER);

		content.setMargin(false);
		setCompositionRoot(content);
		MyUI.navigator.addView(CDRInfo.NAME, this);
	}

	private void toggleVisibility() {

		infoLayout.setVisible(!infoLayout.isVisible());
	}
}
