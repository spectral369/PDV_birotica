package com.spectral369.ADVRADAUTO;

import java.util.HashMap;
import java.util.Map;

import com.spectral369.birotica.MyUI;
import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Setter;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class AdeverintaRadiereAutoInfo extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "ADVRadiereAutoInfo";
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
	HorizontalLayout infoLayout3;
	VerticalLayout infoPart9;
	VerticalLayout infoPart10;
	VerticalLayout infoPart11;
	VerticalLayout infoPart12;
	HorizontalLayout infoLayout4;
	VerticalLayout infoPart13;

	TextField prenumeField;
	TextField numeField;
	TextField localitateField;
	TextField nrStrField;
	TextField marcaAutoField;
	TextField modelAutoField;
	TextField capacitateCilField;
	TextField serieMotorField;
	TextField serieSasiuField;
	TextField nrInmatriculareField;
	TextField addrDepozitare;
	ComboBox<String> titlu;
	TextField intocmitField;

	HorizontalLayout generateLayout;
	Button generate;
	AdeverintaRadiereAutoPDF pdf;
	Map<String, String> map;
	private Binder<AdeverintaRadiereAutoInfo> binder;

	public AdeverintaRadiereAutoInfo() {
		content = new VerticalLayout();
		map = new HashMap<String, String>();
		binder = new Binder<AdeverintaRadiereAutoInfo>(AdeverintaRadiereAutoInfo.class);
		backLayout = new HorizontalLayout();
		backLayout.setMargin(true);
		backbtn = new Button("Back", VaadinIcons.ARROW_CIRCLE_LEFT);
		backbtn.addStyleName("quiet");
		backbtn.addClickListener(evt -> MyUI.navigator.navigateTo("Index"));
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

				.bind(new ValueProvider<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(AdeverintaRadiereAutoInfo source) {
						return null;
					}
				}, new Setter<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(AdeverintaRadiereAutoInfo bean, String fieldvalue) {

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
				.bind(new ValueProvider<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(AdeverintaRadiereAutoInfo source) {
						return null;
					}
				}, new Setter<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(AdeverintaRadiereAutoInfo bean, String fieldvalue) {

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

				.bind(new ValueProvider<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(AdeverintaRadiereAutoInfo source) {
						return null;
					}
				}, new Setter<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(AdeverintaRadiereAutoInfo bean, String fieldvalue) {

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

				.bind(new ValueProvider<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(AdeverintaRadiereAutoInfo source) {
						return null;
					}
				}, new Setter<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(AdeverintaRadiereAutoInfo bean, String fieldvalue) {

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
		marcaAutoField = new TextField("Marca Auto:");
		marcaAutoField.setRequiredIndicatorVisible(true);
		binder.forField(marcaAutoField).asRequired()
		.withValidator(str -> str.length() >2, "Numele marcii sa fie mai mare decat 2 caractere")
				.bind(new ValueProvider<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(AdeverintaRadiereAutoInfo source) {
						return null;
					}
				}, new Setter<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(AdeverintaRadiereAutoInfo bean, String fieldvalue) {

					}
				});

		marcaAutoField.setResponsive(true);
		marcaAutoField.setComponentError((ErrorMessage) new UserError("Marca Required !"));
		infoPart5.addComponent(marcaAutoField);
		infoLayout2.addComponent(infoPart5);
		infoLayout2.setComponentAlignment(infoPart5, Alignment.MIDDLE_CENTER);
		
		
		infoPart6 = new VerticalLayout();
		modelAutoField = new TextField("Modelul Auto:");
		modelAutoField.setRequiredIndicatorVisible(true);
		binder.forField(modelAutoField).asRequired()
		.withValidator(str -> str.length() >2, "Modelul sa fie mai mare decat 2 caractere")
				.bind(new ValueProvider<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(AdeverintaRadiereAutoInfo source) {
						return null;
					}
				}, new Setter<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(AdeverintaRadiereAutoInfo bean, String fieldvalue) {

					}
				});

		modelAutoField.setResponsive(true);
		modelAutoField.setComponentError((ErrorMessage) new UserError("Model Required !"));
		infoPart6.addComponent(modelAutoField);
		infoLayout2.addComponent(infoPart6);
		infoLayout2.setComponentAlignment(infoPart6, Alignment.MIDDLE_CENTER);
		
		infoPart7 = new VerticalLayout();
		capacitateCilField = new TextField("Capacitate Auto:");
		capacitateCilField.setRequiredIndicatorVisible(true);
		binder.forField(capacitateCilField).asRequired()
		.withConverter(new StringToIntegerConverter("Must be Integer"))
				.bind(new ValueProvider<AdeverintaRadiereAutoInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(AdeverintaRadiereAutoInfo source) {
						return null;
					}
				}, new Setter<AdeverintaRadiereAutoInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(AdeverintaRadiereAutoInfo bean, Integer fieldvalue) {

					}
				});

		capacitateCilField.setResponsive(true);
		capacitateCilField.setComponentError((ErrorMessage) new UserError("Capacitate Required !"));
		infoPart7.addComponent(capacitateCilField);
		infoLayout2.addComponent(infoPart7);
		infoLayout2.setComponentAlignment(infoPart7, Alignment.MIDDLE_CENTER);
		
		infoPart8 = new VerticalLayout();
		serieMotorField = new TextField("Serie Motor:");

		serieMotorField.setResponsive(true);
		//serieMotorField.setComponentError((ErrorMessage) new UserError("Model Required !"));
		infoPart8.addComponent(serieMotorField);
		infoLayout2.addComponent(infoPart8);
		infoLayout2.setComponentAlignment(infoPart8, Alignment.MIDDLE_CENTER);
		
		infoLayout3 =  new HorizontalLayout();
		infoLayout3.setVisible(false);
		
		infoPart9 = new VerticalLayout();
		serieSasiuField = new TextField("Serie Sasiu:");
		serieSasiuField.setRequiredIndicatorVisible(true);
		binder.forField(serieSasiuField).asRequired()
		.withValidator(str -> str.length() >2, "Seria Sasiu sa fie mai mare decat 2 caractere")
				.bind(new ValueProvider<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(AdeverintaRadiereAutoInfo source) {
						return null;
					}
				}, new Setter<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(AdeverintaRadiereAutoInfo bean, String fieldvalue) {

					}
				});

		serieSasiuField.setResponsive(true);
		serieSasiuField.setComponentError((ErrorMessage) new UserError("Serie Sasiu Required !"));
		infoPart9.addComponent(serieSasiuField);
		infoLayout3.addComponent(infoPart9);
		infoLayout3.setComponentAlignment(infoPart9, Alignment.MIDDLE_CENTER);
		
		infoPart10 = new VerticalLayout();
		nrInmatriculareField = new TextField("Nr. Inmatriculare:");

		nrInmatriculareField.setResponsive(true);
		//serieMotorField.setComponentError((ErrorMessage) new UserError("Model Required !"));
		infoPart10.addComponent(nrInmatriculareField);
		infoLayout3.addComponent(infoPart10);
		infoLayout3.setComponentAlignment(infoPart10, Alignment.MIDDLE_CENTER);
		
		infoPart11 = new VerticalLayout();
		addrDepozitare = new TextField("Adresa Depozitare:");
		addrDepozitare.setRequiredIndicatorVisible(true);
		binder.forField(addrDepozitare).asRequired()
		.withValidator(str -> str.length() >2, "Adresa sa fie mai mare decat 2 caractere")
				.bind(new ValueProvider<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(AdeverintaRadiereAutoInfo source) {
						return null;
					}
				}, new Setter<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(AdeverintaRadiereAutoInfo bean, String fieldvalue) {

					}
				});

		addrDepozitare.setResponsive(true);
		addrDepozitare.setComponentError((ErrorMessage) new UserError("adresa Depozitare Required !"));
		infoPart11.addComponent(addrDepozitare);
		infoLayout3.addComponent(infoPart11);
		infoLayout3.setComponentAlignment(infoPart11, Alignment.MIDDLE_CENTER);
		
		
		infoPart12 = new VerticalLayout();
		titlu = new ComboBox<String>("Titlul: ");
		titlu.setItems("Dl.", "D-na.");
		titlu.setSelectedItem(null);
		titlu.setRequiredIndicatorVisible(true);
		binder.forField(titlu).asRequired()
				.bind(new ValueProvider<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(AdeverintaRadiereAutoInfo source) {
						return null;
					}
				}, new Setter<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(AdeverintaRadiereAutoInfo bean, String fieldvalue) {

					}
				});
		titlu.setResponsive(true);
		titlu.setComponentError((ErrorMessage) new UserError("Titlu Required !"));
		infoPart12.addComponent(titlu);
		infoLayout3.addComponent(infoPart12);
		infoLayout3.setComponentAlignment(infoPart12, Alignment.MIDDLE_CENTER);
		
		infoLayout4 =  new HorizontalLayout();
		infoLayout4.setVisible(false);
		
		infoPart13 = new VerticalLayout();
		intocmitField = new TextField("Intocmit de catre:");
		intocmitField.setRequiredIndicatorVisible(true);
		binder.forField(intocmitField).asRequired()
		.withValidator(str -> str.length() >2, "Numele persoanei sa fie mai mare decat 2 caractere")
				.bind(new ValueProvider<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(AdeverintaRadiereAutoInfo source) {
						return null;
					}
				}, new Setter<AdeverintaRadiereAutoInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(AdeverintaRadiereAutoInfo bean, String fieldvalue) {

					}
				});

		intocmitField.setResponsive(true);
		intocmitField.setComponentError((ErrorMessage) new UserError("Persoana Required !"));
		infoPart13.addComponent(intocmitField);
		infoLayout4.addComponent(infoPart13);
		infoLayout4.setComponentAlignment(infoPart13, Alignment.MIDDLE_CENTER);
		
		

	

		content.addComponent(infoLayout);
		content.setComponentAlignment(infoLayout, Alignment.MIDDLE_CENTER);
		content.addComponent(infoLayout2);
		content.setComponentAlignment(infoLayout2, Alignment.MIDDLE_CENTER);
		content.addComponent(infoLayout3);
		content.setComponentAlignment(infoLayout3, Alignment.MIDDLE_CENTER);
		content.addComponent(infoLayout4);
		content.setComponentAlignment(infoLayout4, Alignment.MIDDLE_CENTER);
		
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
				map.put("marca", marcaAutoField.getValue().trim());
				map.put("model", modelAutoField.getValue().trim());
				map.put("capacitate", capacitateCilField.getValue().trim());
				map.put("serieMotor", serieMotorField.getValue().trim());
				map.put("serieSasiu", serieSasiuField.getValue().trim());
				map.put("nrInmatriculare", nrInmatriculareField.getValue().trim());
				map.put("addrDepozitare", addrDepozitare.getValue().trim());
				map.put("titlu", titlu.getSelectedItem().get());
				map.put("intocmit", intocmitField.getValue().trim());
			}
			prenumeField.clear();
			numeField.clear();
			nrStrField.clear();
			localitateField.clear();
			marcaAutoField.clear();
			modelAutoField.clear();
			capacitateCilField.clear();
			serieMotorField.clear();
			serieSasiuField.clear();
			nrInmatriculareField.clear();
			addrDepozitare.clear();
			titlu.clear();
			intocmitField.clear();
			pdf = new AdeverintaRadiereAutoPDF(map);
			MyUI.navigator.navigateTo("ADVRadiereAutoPDF");
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
		MyUI.navigator.addView("ADVRadiereAutoInfo", this);
	}

	private void toggleVisibility() {
		infoLayout.setVisible(!infoLayout.isVisible());
		infoLayout2.setVisible(!infoLayout2.isVisible());
		infoLayout3.setVisible(!infoLayout3.isVisible());
		infoLayout4.setVisible(!infoLayout4.isVisible());

		generate.setEnabled(!generate.isEnabled());
	}
}
