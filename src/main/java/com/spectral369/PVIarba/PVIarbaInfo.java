package com.spectral369.PVIarba;

import java.util.HashMap;
import java.util.Map;

import com.spectral369.birotica.MyUI;
import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Setter;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PVIarbaInfo extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "PVIarba";
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

	TextField prenumeField;
	TextField numeField;
	TextField localitateField;
	TextField nrStrField;

	HorizontalLayout generateLayout;
	Button generate;
	PVIarbaPDF pdf;
	Map<String, String> map;
	private Binder<PVIarbaInfo> binder;

	public PVIarbaInfo() {
		content = new VerticalLayout();
		map = new HashMap<String, String>();
		binder = new Binder<PVIarbaInfo>(PVIarbaInfo.class);
		backLayout = new HorizontalLayout();
		backLayout.setMargin(true);
		backbtn = new Button("Back", VaadinIcons.ARROW_CIRCLE_LEFT);
		backbtn.addStyleName("quiet");
		backbtn.addClickListener(evt -> MyUI.navigator.navigateTo("Index"));
		backLayout.addComponent(backbtn);
		content.addComponent(backLayout);
		content.setComponentAlignment(backLayout, Alignment.MIDDLE_LEFT);
		titlelLayout = new HorizontalLayout();
		IarbaTitle = new Button("Proces Verbal - Iarba", VaadinIcons.FILE_TEXT_O);
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
		complete.setEnabled(false);// AICI pentru compeltare
		content.addComponent(checkLayout);
		content.setComponentAlignment(checkLayout, Alignment.MIDDLE_CENTER);
		(infoLayout = new HorizontalLayout()).setVisible(false);
		infoPart1 = new VerticalLayout();
		prenumeField = new TextField("Prenume:");
		prenumeField.setRequiredIndicatorVisible(true);
		binder.forField(prenumeField).asRequired()
		.withValidator(str -> str.length() >2, "Prenumele sa fie mai mare decat 2 caractere")

				.bind(new ValueProvider<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, String fieldvalue) {

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
				.bind(new ValueProvider<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, String fieldvalue) {

					}
				});

		numeField.setResponsive(true);
		numeField.setComponentError((ErrorMessage) new UserError("Nume Required !"));
		infoPart2.addComponent(numeField);
		infoLayout.addComponent(infoPart2);
		infoLayout.setComponentAlignment(infoPart2, Alignment.MIDDLE_CENTER);
		infoPart3 = new VerticalLayout();
		nrStrField = new TextField("Numar:");
		nrStrField.setRequiredIndicatorVisible(true);
		binder.forField(nrStrField).asRequired()

				.bind(new ValueProvider<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, String fieldvalue) {

					}
				});

		nrStrField.setResponsive(true);
		nrStrField.setComponentError((ErrorMessage) new UserError("NR.casa Required !"));
		infoPart3.addComponent(nrStrField);
		infoLayout.addComponent(infoPart3);
		infoLayout.setComponentAlignment(infoPart3, Alignment.MIDDLE_CENTER);

		infoPart4 = new VerticalLayout();
		localitateField = new TextField("Localitate:");
		localitateField.setValue("Dudestii-Vechi");
		localitateField.setRequiredIndicatorVisible(true);
		binder.forField(localitateField).asRequired()
		.withValidator(str -> str.length() >2, "Numele localitatii sa fie mai mare decat 2 caractere")

				.bind(new ValueProvider<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, String fieldvalue) {

					}
				});

		localitateField.setResponsive(true);
		localitateField.setComponentError((ErrorMessage) new UserError("Localitate required !"));
		infoPart4.addComponent(localitateField);
		infoLayout.addComponent(infoPart4);
		infoLayout.setComponentAlignment(infoPart4, Alignment.MIDDLE_CENTER);

		content.addComponent(infoLayout);
		content.setComponentAlignment(infoLayout, Alignment.MIDDLE_CENTER);

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
			}
			prenumeField.clear();
			numeField.clear();
			nrStrField.clear();
			
			pdf = new PVIarbaPDF(map);
			MyUI.navigator.navigateTo("PVIarbaPDF");
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
		MyUI.navigator.addView("PVIarba", this);
	}

	private void toggleVisibility() {
		infoLayout.setVisible(!infoLayout.isVisible());

		generate.setEnabled(!generate.isEnabled());
	}
}
