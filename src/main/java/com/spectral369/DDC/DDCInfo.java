package com.spectral369.DDC;

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
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DDCInfo extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "DDC";
	VerticalLayout content;
	HorizontalLayout backLayout;
	Button backbtn;
	HorizontalLayout titlelLayout;
	Button DDCTitle;
	HorizontalLayout checkLayout;
	CheckBox complete;
	HorizontalLayout infoLayout;
	VerticalLayout infoPart1;
	VerticalLayout infoPart2;
	VerticalLayout infoPart3;
	VerticalLayout infoPart4;
	VerticalLayout infoPart5;
	HorizontalLayout infoLayout2;
	VerticalLayout infoPart6;
	VerticalLayout infoPart7;
	VerticalLayout infoPart8;
	VerticalLayout infoPart9;
	VerticalLayout infoPart10;
	HorizontalLayout infoLayout3;
	VerticalLayout infoPart11;
	VerticalLayout infoPart12;
	VerticalLayout infoPart13;
	VerticalLayout infoPart14;
	VerticalLayout infoPart15;
	HorizontalLayout infoLayout4;
	VerticalLayout infoPart16;
	VerticalLayout infoPart17;
	VerticalLayout infoPart18;
	VerticalLayout infoPart19;
	VerticalLayout infoPart20;
	HorizontalLayout infoLayout5;
	VerticalLayout infoPart21;
	VerticalLayout infoPart22;
	TextField prenumeField;
	TextField numeField;
	TextField CNPField;
	TextField cetatenieField;
	TextField serieField;
	TextField nrSerieField;
	TextField localitateField;
	TextField stradaField;
	TextField nrStrField;
	TextField blocField;
	TextField scaraField;
	TextField etajField;
	TextField apartamentField;
	TextField sectorField;
	TextField judetField;
	TextField CodPostalField;
	TextField telFixField;
	TextField telMobilField;
	TextField emailField;
	CheckBox acordTransmitere;
	DateField dataField;
	ComboBox<String> titlu;
	HorizontalLayout generateLayout;
	Button generate;
	DDCPDF pdf;
	Map<String, String> map;
	private Binder<DDCInfo> binder;

	public DDCInfo() {
		content = new VerticalLayout();
		map = new HashMap<String, String>();
		binder = new Binder<DDCInfo>(DDCInfo.class);
		backLayout = new HorizontalLayout();
		backLayout.setMargin(true);
		backbtn = new Button("Back",VaadinIcons.ARROW_CIRCLE_LEFT);
		backbtn.addStyleName("quiet");
		backbtn.addClickListener(evt -> UI.getCurrent().getNavigator().navigateTo("Index"));
		backLayout.addComponent(  backbtn);
		content.addComponent(  backLayout);
		content.setComponentAlignment(  backLayout, Alignment.MIDDLE_LEFT);
		titlelLayout = new HorizontalLayout();
		DDCTitle = new Button("Declaratie De Corespondenta",VaadinIcons.FILE_TEXT_O);
		DDCTitle.setEnabled(false);
		DDCTitle.setId("mainTitle");
		DDCTitle.addStyleNames(new String[] { "borderless", "clearDisabled" });
		titlelLayout.addComponent(  DDCTitle);
		content.addComponent(  titlelLayout);
		content.setComponentAlignment(  titlelLayout, Alignment.MIDDLE_CENTER);
		checkLayout = new HorizontalLayout();
		(complete = new CheckBox("Cu completare de date ?!", false)).addValueChangeListener(evt -> {
			toggleVisibility();
			UI.getCurrent().push();
		});
		checkLayout.addComponent(  complete);
		content.addComponent(  checkLayout);
		content.setComponentAlignment(  checkLayout, Alignment.MIDDLE_CENTER);
		(infoLayout = new HorizontalLayout()).setVisible(false);
		infoPart1 = new VerticalLayout();
		prenumeField = new TextField("Prenume:");
		prenumeField.setRequiredIndicatorVisible(true);
		binder.forField(prenumeField).asRequired()
				
				.bind(new ValueProvider<DDCInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(DDCInfo source) {
						return null;
					}
				}, new Setter<DDCInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(DDCInfo bean, String fieldvalue) {

					}
				});
		
		prenumeField.setResponsive(true);
		prenumeField.setComponentError((ErrorMessage) new UserError("Prenume Required !"));
		infoPart1.addComponent(  prenumeField);
		infoLayout.addComponent(  infoPart1);
		infoLayout.setComponentAlignment(  infoPart1, Alignment.MIDDLE_CENTER);
		infoPart2 = new VerticalLayout();
		numeField = new TextField("Nume:");
		numeField.setRequiredIndicatorVisible(true);
		binder.forField(numeField).asRequired()
				
				.bind(new ValueProvider<DDCInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(DDCInfo source) {
						return null;
					}
				}, new Setter<DDCInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(DDCInfo bean, String fieldvalue) {

					}
				});
		
		numeField.setResponsive(true);
		numeField.setComponentError((ErrorMessage) new UserError("Nume Required !"));
		infoPart2.addComponent(  numeField);
		infoLayout.addComponent(  infoPart2);
		infoLayout.setComponentAlignment(  infoPart2, Alignment.MIDDLE_CENTER);
		infoPart3 = new VerticalLayout();
		(CNPField = new TextField("CNP:")).setRequiredIndicatorVisible(true);
		binder.forField(CNPField).asRequired()
				.withValidator(str -> str.toString().length() == 13, "CNP-ul are 13 cifre")
				.withConverter(new StringToBigIntegerConverter("CNP-ul poate contine doar cifre"))
			
				.bind(new ValueProvider<DDCInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public BigInteger apply(DDCInfo source) {
						return null;
					}
				}, new Setter<DDCInfo, BigInteger>() {

				
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(DDCInfo bean, BigInteger fieldvalue) {

					}
				});
		
		CNPField.setResponsive(true);
		CNPField.setComponentError((ErrorMessage) new UserError("CNP Required !"));
		infoPart3.addComponent(  CNPField);
		infoLayout.addComponent(  infoPart3);
		infoLayout.setComponentAlignment(  infoPart3, Alignment.MIDDLE_CENTER);
		infoPart4 = new VerticalLayout();
		(cetatenieField = new TextField("Cetatenie:")).setRequiredIndicatorVisible(true);
		cetatenieField.setResponsive(true);
		infoPart4.addComponent(  cetatenieField);
		infoLayout.addComponent(  infoPart4);
		infoLayout.setComponentAlignment(  infoPart4, Alignment.MIDDLE_CENTER);
		infoPart5 = new VerticalLayout();
		(serieField = new TextField("Serie:")).setRequiredIndicatorVisible(true);
	binder.forField(serieField).asRequired()
				.withValidator(str -> str.toString().length() == 2, "Seria are 2 caractere")
				.bind(new ValueProvider<DDCInfo, String>() {

					 
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(DDCInfo source) {
						return null;
					}
				}, new Setter<DDCInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(DDCInfo bean, String fieldvalue) {

					}
				});
	
		serieField.setResponsive(true);
		serieField.setComponentError((ErrorMessage) new UserError("Serie ID Required !"));
		infoPart5.addComponent(  serieField);
		infoLayout.addComponent(  infoPart5);
		infoLayout.setComponentAlignment(  infoPart5, Alignment.MIDDLE_CENTER);
		(infoLayout2 = new HorizontalLayout()).setVisible(false);
		infoPart6 = new VerticalLayout();
		(nrSerieField = new TextField("Numar serie:")).setRequiredIndicatorVisible(true);
		binder.forField(nrSerieField).asRequired()
				.withValidator(str -> str.toString().length() == 6, "Seria poate avea 6 caractere")
				.withConverter(new StringToIntegerConverter("Must be Integer"))
				.bind(new ValueProvider<DDCInfo, Integer>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(DDCInfo source) {
						return null;
					}
				}, new Setter<DDCInfo, Integer>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(DDCInfo bean, Integer fieldvalue) {

					}
				});
	
		nrSerieField.setResponsive(true);
		nrSerieField.setComponentError((ErrorMessage) new UserError("NR.Serie Required !"));
		infoPart6.addComponent(  nrSerieField);
		infoLayout2.addComponent(  infoPart6);
		infoLayout2.setComponentAlignment(  infoPart6, Alignment.MIDDLE_CENTER);
		infoPart7 = new VerticalLayout();
		(localitateField = new TextField("Localitate:")).setRequiredIndicatorVisible(true);
		binder.forField(localitateField).asRequired()
		
				.bind(new ValueProvider<DDCInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(DDCInfo source) {
						return null;
					}
				}, new Setter<DDCInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(DDCInfo bean, String fieldvalue) {

					}
				});
	
		localitateField.setResponsive(true);
		localitateField.setComponentError((ErrorMessage) new UserError("Localitate Required !"));
		infoPart7.addComponent(  localitateField);
		infoLayout2.addComponent(  infoPart7);
		infoLayout2.setComponentAlignment(  infoPart7, Alignment.MIDDLE_CENTER);
		infoPart8 = new VerticalLayout();
		stradaField = new TextField("Strada:");
		stradaField.setRequiredIndicatorVisible(true);
		infoPart8.addComponent(  stradaField);
		infoLayout2.addComponent(  infoPart8);
		infoLayout2.setComponentAlignment(  infoPart8, Alignment.MIDDLE_CENTER);
		infoPart9 = new VerticalLayout();
		(nrStrField = new TextField("Numar:")).setRequiredIndicatorVisible(true);
		binder.forField(nrStrField).asRequired()
				
				.bind(new ValueProvider<DDCInfo, String>() {

				
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(DDCInfo source) {
						return null;
					}
				}, new Setter<DDCInfo, String>() {

										private static final long serialVersionUID = 1L;

					@Override
					public void accept(DDCInfo bean, String fieldvalue) {

					}
				});
		
		nrStrField.setResponsive(true);
		nrStrField.setComponentError((ErrorMessage) new UserError("NR.casa Required !"));
		infoPart9.addComponent(  nrStrField);
		infoLayout2.addComponent(  infoPart9);
		infoLayout2.setComponentAlignment(  infoPart9, Alignment.MIDDLE_CENTER);
		infoPart10 = new VerticalLayout();
		(blocField = new TextField("Bloc:")).setRequiredIndicatorVisible(true);
		infoPart10.addComponent(  blocField);
		infoLayout2.addComponent(  infoPart10);
		infoLayout2.setComponentAlignment(  infoPart10, Alignment.MIDDLE_CENTER);
		(infoLayout3 = new HorizontalLayout()).setVisible(false);
		infoPart11 = new VerticalLayout();
		(scaraField = new TextField("Scara:")).setRequiredIndicatorVisible(true);
		infoPart11.addComponent(  scaraField);
		infoLayout3.addComponent(  infoPart11);
		infoLayout3.setComponentAlignment(  infoPart11, Alignment.MIDDLE_CENTER);
		infoPart12 = new VerticalLayout();
		(etajField = new TextField("Etaj:")).setRequiredIndicatorVisible(true);
		infoPart12.addComponent(  etajField);
		infoLayout3.addComponent(  infoPart12);
		infoLayout3.setComponentAlignment(  infoPart12, Alignment.MIDDLE_CENTER);
		infoPart13 = new VerticalLayout();
		(apartamentField = new TextField("Apartament:")).setRequiredIndicatorVisible(true);
		infoPart13.addComponent(  apartamentField);
		infoLayout3.addComponent(  infoPart13);
		infoLayout3.setComponentAlignment(  infoPart13, Alignment.MIDDLE_CENTER);
		infoPart14 = new VerticalLayout();
		(sectorField = new TextField("Sector:")).setRequiredIndicatorVisible(true);
		infoPart14.addComponent(  sectorField);
		infoLayout3.addComponent(  infoPart14);
		infoLayout3.setComponentAlignment(  infoPart14, Alignment.MIDDLE_CENTER);
		infoPart15 = new VerticalLayout();
		(judetField = new TextField("Judet:")).setRequiredIndicatorVisible(true);
		binder.forField(judetField).asRequired()
				
				.bind(new ValueProvider<DDCInfo, String>() {

				
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(DDCInfo source) {
						return null;
					}
				}, new Setter<DDCInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(DDCInfo bean, String fieldvalue) {

					}
				});
	
		judetField.setResponsive(true);
		judetField.setComponentError((ErrorMessage) new UserError("Judet Required !"));
		infoPart15.addComponent(  judetField);
		infoLayout3.addComponent(  infoPart15);
		infoLayout3.setComponentAlignment(  infoPart15, Alignment.MIDDLE_CENTER);
		(infoLayout4 = new HorizontalLayout()).setVisible(false);
		infoPart16 = new VerticalLayout();
		(CodPostalField = new TextField("Cod Postal:")).setRequiredIndicatorVisible(true);
		infoPart16.addComponent(  CodPostalField);
		infoLayout4.addComponent(  infoPart16);
		infoLayout4.setComponentAlignment(  infoPart16, Alignment.MIDDLE_CENTER);
		infoPart17 = new VerticalLayout();
		(telFixField = new TextField("Telefon Fix:")).setRequiredIndicatorVisible(true);
		infoPart17.addComponent(  telFixField);
		infoLayout4.addComponent(  infoPart17);
		infoLayout4.setComponentAlignment(  infoPart17, Alignment.MIDDLE_CENTER);
		infoPart18 = new VerticalLayout();
		(telMobilField = new TextField("Telefon Mobil:")).setRequiredIndicatorVisible(true);
		infoPart18.addComponent(  telMobilField);
		infoLayout4.addComponent(  infoPart18);
		infoLayout4.setComponentAlignment(  infoPart18, Alignment.MIDDLE_CENTER);
		infoPart19 = new VerticalLayout();
		(emailField = new TextField("E-Mail:")).setRequiredIndicatorVisible(true);
		infoPart19.addComponent(  emailField);
		infoLayout4.addComponent(  infoPart19);
		infoLayout4.setComponentAlignment(  infoPart19, Alignment.MIDDLE_CENTER);
		infoPart20 = new VerticalLayout();
		(acordTransmitere = new CheckBox("Acord transmitere:")).setRequiredIndicatorVisible(true);
		infoPart20.addComponent(  acordTransmitere);
		infoLayout4.addComponent(  infoPart20);
		infoLayout4.setComponentAlignment(  infoPart20, Alignment.MIDDLE_CENTER);
		(infoLayout5 = new HorizontalLayout()).setVisible(false);
		infoPart21 = new VerticalLayout();
		(dataField = new DateField("Data:")).setDateFormat("dd-MM-yyyy");
		dataField.setRequiredIndicatorVisible(true);
		binder.forField(dataField).asRequired()
			
				.bind(new ValueProvider<DDCInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public LocalDate apply(DDCInfo source) {
						return null;
					}
				}, new Setter<DDCInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(DDCInfo bean, LocalDate fieldvalue) {

					}
				});
		
		dataField.setResponsive(true);
		dataField.setComponentError((ErrorMessage) new UserError("Date Required !"));
		infoPart21.addComponent(  dataField);
		infoLayout5.addComponent(  infoPart21);
		infoLayout5.setComponentAlignment(  infoPart21, Alignment.MIDDLE_CENTER);
		infoPart22 = new VerticalLayout();
		titlu = new ComboBox<String>("Titlul: ");
		titlu.setItems("DL.", "Dna.");
		titlu.setSelectedItem(null);
		titlu.setRequiredIndicatorVisible(true);
		binder.forField(titlu).asRequired()
			
				.bind(new ValueProvider<DDCInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(DDCInfo source) {
						return null;
					}
				}, new Setter<DDCInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(DDCInfo bean, String fieldvalue) {

					}
				});
	
		titlu.setResponsive(true);
		titlu.setComponentError((ErrorMessage) new UserError("Titlu Required !"));
		infoPart22.addComponent(  titlu);
		infoLayout5.addComponent(  infoPart22);
		infoLayout5.setComponentAlignment(  infoPart22, Alignment.MIDDLE_CENTER);
		content.addComponent(  infoLayout);
		content.setComponentAlignment(  infoLayout, Alignment.MIDDLE_CENTER);
		content.addComponent(  infoLayout2);
		content.setComponentAlignment(  infoLayout2, Alignment.MIDDLE_CENTER);
		content.addComponent(  infoLayout3);
		content.setComponentAlignment(  infoLayout3, Alignment.MIDDLE_CENTER);
		content.addComponent(  infoLayout4);
		content.setComponentAlignment(  infoLayout4, Alignment.MIDDLE_CENTER);
		content.addComponent(  infoLayout5);
		content.setComponentAlignment(  infoLayout5, Alignment.MIDDLE_CENTER);
		generateLayout = new HorizontalLayout();
		generate = new Button("Generate", VaadinIcons.FILE_PROCESS);
		generate.addStyleNames("primary", "friendly" );
		generate.addClickListener(evt -> {
			if (complete.getValue()) {
				map.put("prenume", prenumeField.getValue().trim());
				map.put("nume", numeField.getValue().trim());
				map.put("CNP", CNPField.getValue());
				map.put("cetatenie", cetatenieField.getValue().trim());
				map.put("serie", serieField.getValue().trim());
				map.put("nrSerie", nrSerieField.getValue().trim());
				map.put("localitate", localitateField.getValue().trim());
				map.put("strada", stradaField.getValue().trim());
				map.put("nrStrada", nrStrField.getValue().trim());
				map.put("bloc", blocField.getValue().trim());
				map.put("scara", scaraField.getValue().trim());
				map.put("etaj", etajField.getValue().trim());
				map.put("apartament", apartamentField.getValue().trim());
				map.put("sector", sectorField.getValue().trim());
				map.put("judet", judetField.getValue().trim());
				map.put("codPostal", CodPostalField.getValue().trim());
				map.put("fix", telFixField.getValue().trim());
				map.put("mobil", telMobilField.getValue().trim());
				map.put("email", emailField.getValue().trim());
				map.put("acord", acordTransmitere.getValue().toString());
				map.put("data", ((LocalDate) dataField.getValue()).format(DateTimeFormatter.ISO_LOCAL_DATE));
				map.put("titlu", titlu.getSelectedItem().get());
			}
			prenumeField.clear();
			numeField.clear();
			CNPField.clear();
			cetatenieField.clear();
			serieField.clear();
			nrSerieField.clear();
			localitateField.clear();
			stradaField.clear();
			nrStrField.clear();
			blocField.clear();
			scaraField.clear();
			etajField.clear();
			apartamentField.clear();
			sectorField.clear();
			judetField.clear();
			CodPostalField.clear();
			telFixField.clear();
			telMobilField.clear();
			emailField.clear();
			acordTransmitere.clear();
			dataField.clear();
			titlu.clear();
			pdf = new DDCPDF(map);
			UI.getCurrent().getNavigator().navigateTo("DDCPDF");
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
		content.addComponent(  generateLayout);
		content.setComponentAlignment(  generateLayout, Alignment.MIDDLE_CENTER);
		content.setMargin(false);
		setCompositionRoot( content);
		UI.getCurrent().getNavigator().addView("DDC",this);
	}

	private void toggleVisibility() {
		infoLayout.setVisible(!infoLayout.isVisible());
		infoLayout2.setVisible(!infoLayout2.isVisible());
		infoLayout3.setVisible(!infoLayout3.isVisible());
		infoLayout4.setVisible(!infoLayout4.isVisible());
		infoLayout5.setVisible(!infoLayout5.isVisible());
		generate.setEnabled(!generate.isEnabled());
	}
}
