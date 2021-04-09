package com.spectral369.capela;


import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.converter.StringToBigIntegerConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ErrorMessage;
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

public class CerereCapelaInfo extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "CerereCapelaInfo";
	VerticalLayout content;
	HorizontalLayout backLayout;
	Button backbtn;
	HorizontalLayout titlelLayout;
	Button IarbaTitle;
	HorizontalLayout checkLayout;
	CheckBox complete;
	HorizontalLayout infoLayout;
	VerticalLayout infoPart1;
	VerticalLayout infoPart2;
	VerticalLayout infoPart3;
	VerticalLayout infoPart4;
	HorizontalLayout infoLayout2;
	VerticalLayout infoPart5;
	VerticalLayout infoPart6;
	VerticalLayout infoPart7;
	VerticalLayout infoPart8;

	TextField prenumeField;
	TextField numeField;
	TextField localitateField;
	TextField nrStrField;
	TextField judetField;
	TextField cnpField;
	TextField nrZileField;
	DateField dateField;


	HorizontalLayout generateLayout;
	Button generate;
	CerereCapelaPDF pdf;
	Map<String, String> map;
	private Binder<CerereCapelaInfo> binder;

	public CerereCapelaInfo() {
		content = new VerticalLayout();
		map = new HashMap<String, String>();
		binder = new Binder<CerereCapelaInfo>(CerereCapelaInfo.class);
		backLayout = new HorizontalLayout();
		backLayout.setMargin(true);
		backbtn = new Button("Back", VaadinIcons.ARROW_CIRCLE_LEFT);
		backbtn.addStyleName("quiet");
		backbtn.addClickListener(evt -> UI.getCurrent().getNavigator().navigateTo("Index"));
		backLayout.addComponent(backbtn);
		content.addComponent(backLayout);
		content.setComponentAlignment(backLayout, Alignment.MIDDLE_LEFT);
		titlelLayout = new HorizontalLayout();
		IarbaTitle = new Button("Adeverinta de Radiere Auto", VaadinIcons.FILE_TEXT_O);
		IarbaTitle.setEnabled(false);
		IarbaTitle.setId("mainTitle");
		IarbaTitle.addStyleNames(new String[] { "borderless", "clearDisabled" });
		titlelLayout.addComponent(IarbaTitle);
		content.addComponent(titlelLayout);
		content.setComponentAlignment(titlelLayout, Alignment.MIDDLE_CENTER);
		checkLayout = new HorizontalLayout();
		(complete = new CheckBox("Cu completare de date ?!", false)).addValueChangeListener(evt -> {
			toggleVisibility();
			UI.getCurrent().push();
		});
		checkLayout.addComponent(complete);
		content.addComponent(checkLayout);
		content.setComponentAlignment(checkLayout, Alignment.MIDDLE_CENTER);
		(infoLayout = new HorizontalLayout()).setVisible(false);
		infoPart1 = new VerticalLayout();
		prenumeField = new TextField("Prenume:");
		prenumeField.setRequiredIndicatorVisible(true);
		binder.forField(prenumeField).asRequired()
		.withValidator(str -> str.length() >2, "Prenumele sa fie mai mare decat 2 caractere")

				.bind(new ValueProvider<CerereCapelaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereCapelaInfo source) {
						return null;
					}
				}, new Setter<CerereCapelaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereCapelaInfo bean, String fieldvalue) {

					}
				});

		prenumeField.setResponsive(true);
		prenumeField.setComponentError((ErrorMessage) new UserError("Prenume Required !"));
		infoPart1.addComponent(prenumeField);
		infoLayout.addComponent(infoPart1);
		infoLayout.setComponentAlignment(infoPart1, Alignment.MIDDLE_CENTER);
		
		infoPart2 = new VerticalLayout();
		numeField = new TextField("Nume:");
		numeField.setRequiredIndicatorVisible(true);
		binder.forField(numeField).asRequired()
		.withValidator(str -> str.length() >2, "Numele sa fie mai mare decat 2 caractere")
				.bind(new ValueProvider<CerereCapelaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereCapelaInfo source) {
						return null;
					}
				}, new Setter<CerereCapelaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereCapelaInfo bean, String fieldvalue) {

					}
				});

		numeField.setResponsive(true);
		numeField.setComponentError((ErrorMessage) new UserError("Nume Required !"));
		infoPart2.addComponent(numeField);
		infoLayout.addComponent(infoPart2);
		infoLayout.setComponentAlignment(infoPart2, Alignment.MIDDLE_CENTER);
		
		infoPart3 = new VerticalLayout();
		localitateField = new TextField("Localitate:");
		localitateField.setValue("Dudestii-Vechi");
		localitateField.setRequiredIndicatorVisible(true);
		binder.forField(localitateField).asRequired()
		.withValidator(str -> str.length() >2, "Numele localitatii sa fie mai mare decat 2 caractere")

				.bind(new ValueProvider<CerereCapelaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereCapelaInfo source) {
						return null;
					}
				}, new Setter<CerereCapelaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereCapelaInfo bean, String fieldvalue) {

					}
				});

		localitateField.setResponsive(true);
		localitateField.setComponentError((ErrorMessage) new UserError("Localitate required !"));
		infoPart3.addComponent(localitateField);
		infoLayout.addComponent(infoPart3);
		infoLayout.setComponentAlignment(infoPart3, Alignment.MIDDLE_CENTER);
		
		infoPart4 = new VerticalLayout();
		nrStrField = new TextField("Numar Casa:");
		nrStrField.setRequiredIndicatorVisible(true);
		binder.forField(nrStrField).asRequired()

				.bind(new ValueProvider<CerereCapelaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereCapelaInfo source) {
						return null;
					}
				}, new Setter<CerereCapelaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereCapelaInfo bean, String fieldvalue) {

					}
				});

		nrStrField.setResponsive(true);
		nrStrField.setComponentError((ErrorMessage) new UserError("Model required !"));
		infoPart4.addComponent(nrStrField);
		infoLayout.addComponent(infoPart4);
		infoLayout.setComponentAlignment(infoPart4, Alignment.MIDDLE_CENTER);
		///info 2
		infoLayout2 =  new HorizontalLayout();
		infoLayout2.setVisible(false);
		

		infoPart5 = new VerticalLayout();
		judetField = new TextField("Judet:");
		judetField.setRequiredIndicatorVisible(true);
		binder.forField(judetField).asRequired()
		.withValidator(str -> str.length() >2, "Judetul trebuie sa aibe mai mult de 2 caractere")
				.bind(new ValueProvider<CerereCapelaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereCapelaInfo source) {
						return null;
					}
				}, new Setter<CerereCapelaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereCapelaInfo bean, String fieldvalue) {

					}
				});

		judetField.setResponsive(true);
		judetField.setComponentError((ErrorMessage) new UserError("Judet Required !"));
		infoPart5.addComponent(judetField);
		infoLayout2.addComponent(infoPart5);
		infoLayout2.setComponentAlignment(infoPart5, Alignment.MIDDLE_CENTER);
		
		
		infoPart6 = new VerticalLayout();
		cnpField = new TextField("CNP:");
		cnpField.setRequiredIndicatorVisible(true);
		cnpField.addListener(e -> {
			cnpField.setValue(cnpField.getValue().replaceAll("[a-zA-Z]+", ""));
		});
		binder.forField(cnpField).asRequired()
				.withValidator(str -> str.toString().length() == 13, "CNP-ul are 13 cifre")
				.withConverter(new StringToBigIntegerConverter("CNP-ul poate contine doar cifre"))

				.bind(new ValueProvider<CerereCapelaInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public BigInteger apply(CerereCapelaInfo source) {
						return null;
					}
				}, new Setter<CerereCapelaInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereCapelaInfo bean, BigInteger fieldvalue) {

					}
				});

		cnpField.setResponsive(true);
		cnpField.setComponentError((ErrorMessage) new UserError("CNP Required !"));
		infoPart6.addComponent(cnpField);
		infoLayout2.addComponent(infoPart6);
		infoLayout2.setComponentAlignment(infoPart6, Alignment.MIDDLE_CENTER);
		
		infoPart7 = new VerticalLayout();
		nrZileField = new TextField("Nr. zile inchiriere:");
		nrZileField.setRequiredIndicatorVisible(true);
		binder.forField(nrZileField).asRequired()
		.withConverter(new StringToIntegerConverter("Must be Integer"))
				.bind(new ValueProvider<CerereCapelaInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(CerereCapelaInfo source) {
						return null;
					}
				}, new Setter<CerereCapelaInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereCapelaInfo bean, Integer fieldvalue) {

					}
				});

		nrZileField.setResponsive(true);
		nrZileField.setComponentError((ErrorMessage) new UserError("Nr. Zile Required !"));
		infoPart7.addComponent(nrZileField);
		infoLayout2.addComponent(infoPart7);
		infoLayout2.setComponentAlignment(infoPart7, Alignment.MIDDLE_CENTER);
		
		infoPart8 = new VerticalLayout();
		dateField = new DateField("Data inceput:");
		dateField.setDateFormat("dd-MM-yyyy");
		dateField.setRequiredIndicatorVisible(true);

		binder.forField(dateField).asRequired()
				.withValidator(returnDate -> !returnDate.isBefore(LocalDate.now()),
						"Data nu poate fi anterioara zilei de azi")

				.bind(new ValueProvider<CerereCapelaInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public LocalDate apply(CerereCapelaInfo source) {
						return null;
					}
				}, new Setter<CerereCapelaInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereCapelaInfo bean, LocalDate fieldvalue) {

					}
				});

		dateField.setResponsive(true);
		dateField.setComponentError((ErrorMessage) new UserError("Date Required !"));
		infoPart8.addComponent(dateField);
		infoLayout2.addComponent(infoPart8);
		infoLayout2.setComponentAlignment(infoPart8, Alignment.MIDDLE_CENTER);
		
	

	

		content.addComponent(infoLayout);
		content.setComponentAlignment(infoLayout, Alignment.MIDDLE_CENTER);
		content.addComponent(infoLayout2);
		content.setComponentAlignment(infoLayout2, Alignment.MIDDLE_CENTER);


		
		generateLayout = new HorizontalLayout();
		generate = new Button("Generate", VaadinIcons.FILE_PROCESS);
		generate.addStyleNames("primary", "friendly");
		generate.addClickListener(evt -> {
			//String output = input.substring(0, 1).toUpperCase() + input.substring(1);
			if (complete.getValue()) {
				String prenume = prenumeField.getValue().trim().substring(0,1).toUpperCase()+prenumeField.getValue().trim().substring(1);
				String nume = numeField.getValue().trim().substring(0,1).toUpperCase()+numeField.getValue().trim().substring(1);
				String localitate = localitateField.getValue().trim().substring(0,1).toUpperCase()+localitateField.getValue().trim().substring(1);
				map.put("prenume", prenume);
				map.put("nume", nume);
				map.put("nrStrada", nrStrField.getValue().trim());
				map.put("localitate", localitate);
				map.put("judet", judetField.getValue().trim());
				map.put("cnp", cnpField.getValue().trim());
				LocalDate dt = dateField.getValue();
				dt.format(DateTimeFormatter.ISO_LOCAL_DATE);
				map.put("nrZile", nrZileField.getValue().trim());
				map.put("ziua", String.valueOf(dt.getDayOfMonth()));
				map.put("luna", String.valueOf(dt.getMonthValue()));
				map.put("anul", String.valueOf(dt.getYear()));
			
			}
			prenumeField.clear();
			numeField.clear();
			nrStrField.clear();
			localitateField.clear();
			judetField.clear();
			cnpField.clear();
			nrStrField.clear();
			dateField.clear();
			
			pdf = new CerereCapelaPDF(map);
			UI.getCurrent().getNavigator().navigateTo("CerereCapelaPDF");
		});
		generateLayout.addComponent(generate);
		binder.addStatusChangeListener(event -> {
			System.out.println(binder.isValid());
			if (!complete.getValue() || binder.isValid()) {
				generate.setEnabled(true);
			} else {
				generate.setEnabled(false);
			}
		});
		content.addComponent(generateLayout);
		content.setComponentAlignment(generateLayout, Alignment.MIDDLE_CENTER);
		content.setMargin(false);
		setCompositionRoot(content);
		UI.getCurrent().getNavigator().addView("CerereCapelaInfo", this);
	}

	private void toggleVisibility() {
		infoLayout.setVisible(!infoLayout.isVisible());
		infoLayout2.setVisible(!infoLayout2.isVisible());

		generate.setEnabled(!generate.isEnabled());
	}
}
