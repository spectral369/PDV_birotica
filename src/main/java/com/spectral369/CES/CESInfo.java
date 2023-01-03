package com.spectral369.CES;


import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.spectral369.birotica.MainView;
import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.converter.StringToBigIntegerConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;



public class CESInfo extends  HorizontalLayout implements RouterLayout, AfterNavigationObserver  {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "CES";
	VerticalLayout content;
	HorizontalLayout backLayout;
	Button backbtn;
	HorizontalLayout titlelLayout;
	Button DDCTitle;
	HorizontalLayout checkLayout;
	Checkbox complete;
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
	TextField serieField;
	TextField nrSerieField;
	TextField localitateField;
	TextField stradaField;
	TextField nrStrField;
	TextField blocField;
	TextField scaraField;
	TextField etajField;
	TextField apartamentField;
	TextField judetField;
	TextField addrCorespondenta;
	TextField telMobilField;

	Checkbox incadrareHandicap;
	Checkbox reevaluareHandicap ;
	Checkbox obtinereOrientare;
	DatePicker dataField;
	ComboBox<String> titlu;
	HorizontalLayout generateLayout;
	Button generate;
	CESPDF pdf;
	Map<String, String> map;
	private Binder<CESInfo> binder;

	public CESInfo() {
		content = new VerticalLayout();
		map = new HashMap<String, String>();
		binder = new Binder<CESInfo>(CESInfo.class);
		backLayout = new HorizontalLayout();
		backLayout.setMargin(true);
		backbtn = new Button("Back",VaadinIcon.ARROW_CIRCLE_LEFT.create());
		backbtn.getClassNames().add("quiet");
		backbtn.addClickListener(evt ->{ 
		        RouteConfiguration.forSessionScope().removeRoute(NAME);
			UI.getCurrent().navigate(MainView.class);
			});
		backLayout.add(  backbtn);
		content.add(  backLayout);
		content.setAlignItems(  Alignment.START);
		titlelLayout = new HorizontalLayout();
		DDCTitle = new Button("Declaratie Examinare Sociala",VaadinIcon.FILE_TEXT_O.create());
		DDCTitle.setEnabled(false);
		DDCTitle.setId("mainTitle");
		DDCTitle.addClassName("clearDisabled");

		titlelLayout.add(  DDCTitle);
		content.add(  titlelLayout);
		content.setAlignItems(  Alignment.CENTER);
		checkLayout = new HorizontalLayout();
		(complete = new Checkbox("Cu completare de date ?!", false)).addValueChangeListener(evt -> {
			toggleVisibility();
			UI.getCurrent().push();
		});
		checkLayout.add(  complete);
		content.add(  checkLayout);
		content.setAlignItems(  Alignment.CENTER);
		(infoLayout = new HorizontalLayout()).setVisible(false);
		infoPart1 = new VerticalLayout();
		prenumeField = new TextField("Nume Complet Solicitant:");
		prenumeField.setRequiredIndicatorVisible(true);
		binder.forField(prenumeField).asRequired()
				
				.bind(new ValueProvider<CESInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CESInfo source) {
						return null;
					}
				}, new Setter<CESInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CESInfo bean, String fieldvalue) {

					}
				});
		
	
		prenumeField.setErrorMessage("Nume Solicitant Required !");
		prenumeField.setWidth("100%");
		prenumeField.setMaxLength(35);
		prenumeField.setMinWidth(20f, Unit.EM);//test
		infoPart1.add(prenumeField);
		infoLayout.add(infoPart1);
		infoLayout.setAlignItems(  Alignment.CENTER);
		
		
		infoPart2 = new VerticalLayout();
		numeField = new TextField("Nume Complet Pers. Examinata:");
		numeField.setRequiredIndicatorVisible(true);
		binder.forField(numeField).asRequired()
				
				.bind(new ValueProvider<CESInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CESInfo source) {
						return null;
					}
				}, new Setter<CESInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CESInfo bean, String fieldvalue) {

					}
				});
		
	
		numeField.setErrorMessage("Nume Persoana Examinata Required !");
		numeField.setWidth("100%");
		numeField.setMaxLength(35);
		numeField.setMinWidth(20f, Unit.EM);//test
		infoPart2.add(  numeField);
		infoLayout.add(  infoPart2);
		infoLayout.setAlignItems(   Alignment.CENTER);
		
		infoPart3 = new VerticalLayout();
		localitateField = new TextField("Localitate:");
		localitateField.setRequiredIndicatorVisible(true);
		binder.forField(localitateField).asRequired()
		
				.bind(new ValueProvider<CESInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CESInfo source) {
						return null;
					}
				}, new Setter<CESInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CESInfo bean, String fieldvalue) {

					}
				});
	
		
		localitateField.setErrorMessage("Localitate Required !");
		infoPart3.add(localitateField);
		infoLayout.add(infoPart3);
		infoLayout.setAlignItems(Alignment.CENTER);
		
		infoPart4 = new VerticalLayout();
		stradaField = new TextField("Nume Strada:");
		stradaField.setRequiredIndicatorVisible(true);
		infoPart4.add(  stradaField);
		infoLayout.add(  infoPart4);
		infoLayout.setAlignItems(  Alignment.CENTER);
		
		
		infoPart5 = new VerticalLayout();
		(nrStrField = new TextField("Numar Casa:")).setRequiredIndicatorVisible(true);
		binder.forField(nrStrField).asRequired()
				
				.bind(new ValueProvider<CESInfo, String>() {

				
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CESInfo source) {
						return null;
					}
				}, new Setter<CESInfo, String>() {

										private static final long serialVersionUID = 1L;

					@Override
					public void accept(CESInfo bean, String fieldvalue) {

					}
				});
		
		
		nrStrField.setErrorMessage("NR.casa Required !");
		infoPart5.add(  nrStrField);
		infoLayout.add(  infoPart5);
		infoLayout.setAlignItems(  Alignment.CENTER);
		
		(infoLayout2 = new HorizontalLayout()).setVisible(false);
		infoPart6 = new VerticalLayout();
		(blocField = new TextField("Bloc:")).setRequiredIndicatorVisible(true);
		infoPart6.add(  blocField);
		infoLayout2.add(  infoPart6);
		infoLayout2.setAlignItems(  Alignment.CENTER);
		
		infoPart7 = new VerticalLayout();
		(scaraField = new TextField("Scara:")).setRequiredIndicatorVisible(true);
		infoPart7.add(  scaraField);
		infoLayout2.add(  infoPart7);
		infoLayout2.setAlignItems(  Alignment.CENTER);
		
		infoPart8 = new VerticalLayout();
		(etajField = new TextField("Etaj:")).setRequiredIndicatorVisible(true);
		infoPart8.add(  etajField);
		infoLayout2.add(  infoPart8);
		infoLayout2.setAlignItems(  Alignment.CENTER);
		
		infoPart9 = new VerticalLayout();
		(apartamentField = new TextField("Apartament:")).setRequiredIndicatorVisible(true);
		infoPart9.add(  apartamentField);
		infoLayout2.add(  infoPart9);
		infoLayout2.setAlignItems(  Alignment.CENTER);
		
		infoPart10 = new VerticalLayout();
		(judetField = new TextField("Judet/Sector:")).setRequiredIndicatorVisible(true);
		binder.forField(judetField).asRequired()
				
				.bind(new ValueProvider<CESInfo, String>() {

				
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CESInfo source) {
						return null;
					}
				}, new Setter<CESInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CESInfo bean, String fieldvalue) {

					}
				});
	
	
		judetField.setErrorMessage("Judet Required !");
		infoPart10.add(  judetField);
		infoLayout2.add(  infoPart10);
		infoLayout2.setAlignItems(  Alignment.CENTER);
		
		
		
		
		(infoLayout3 = new HorizontalLayout()).setVisible(false);
		infoPart11 = new VerticalLayout();
		(telMobilField = new TextField("Telefon Mobil:")).setRequiredIndicatorVisible(true);
		binder.forField(telMobilField).asRequired()
		.withValidator(str -> str.matches("^([00]{0,1}[\\d]{1,3}[0-9]{9,12})$"), "Numar de telefon invalid !")
		.withConverter(new StringToBigIntegerConverter("Nr. telefon poate contine doar cifre"))
	
		.bind(new ValueProvider<CESInfo, BigInteger>() {

			private static final long serialVersionUID = 1L;

			@Override
			public BigInteger apply(CESInfo source) {
				return null;
			}
		}, new Setter<CESInfo, BigInteger>() {

		
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(CESInfo bean, BigInteger fieldvalue) {

			}
		});
		infoPart11.add(telMobilField);
		infoLayout3.add(infoPart11);
		infoLayout3.setAlignItems(Alignment.CENTER);
		
		infoPart12 = new VerticalLayout();
		(serieField = new TextField("Serie:")).setRequiredIndicatorVisible(true);
	binder.forField(serieField).asRequired()
				.withValidator(str -> str.toString().length() == 2, "Seria are 2 caractere")
				.bind(new ValueProvider<CESInfo, String>() {

					 
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CESInfo source) {
						return null;
					}
				}, new Setter<CESInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CESInfo bean, String fieldvalue) {

					}
				});
	
		
		serieField.setErrorMessage("Serie ID Required !");
		infoPart12.add(serieField);
		infoLayout3.add(infoPart12);
		infoLayout3.setAlignItems(Alignment.CENTER);
		
		infoPart13 = new VerticalLayout();
		(nrSerieField = new TextField("Numar C.I:")).setRequiredIndicatorVisible(true);
		binder.forField(nrSerieField).asRequired()
				.withValidator(str -> str.toString().length() == 6, "Seria poate avea 6 caractere")
				.withConverter(new StringToIntegerConverter("Must be Integer"))
				.bind(new ValueProvider<CESInfo, Integer>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(CESInfo source) {
						return null;
					}
				}, new Setter<CESInfo, Integer>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CESInfo bean, Integer fieldvalue) {

					}
				});
	
		
		nrSerieField.setErrorMessage("Nr. C.I Required !");
		infoPart13.add(nrSerieField);
		infoLayout3.add(infoPart13);
		infoLayout3.setAlignItems(Alignment.CENTER);
		
		
		
		infoPart14 = new VerticalLayout();
		(CNPField = new TextField("CNP:")).setRequiredIndicatorVisible(true);
		binder.forField(CNPField).asRequired()
				.withValidator(str -> str.toString().length() == 13, "CNP-ul are 13 cifre")
				.withConverter(new StringToBigIntegerConverter("CNP-ul poate contine doar cifre"))
			
				.bind(new ValueProvider<CESInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public BigInteger apply(CESInfo source) {
						return null;
					}
				}, new Setter<CESInfo, BigInteger>() {

				
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CESInfo bean, BigInteger fieldvalue) {

					}
				});
		
		
		CNPField.setErrorMessage("CNP Required !");
		infoPart14.add(  CNPField);
		infoLayout3.add(  infoPart14);
		infoLayout3.setAlignItems(  Alignment.CENTER);
		
		(infoLayout4 = new HorizontalLayout()).setVisible(false);
		infoPart15 = new VerticalLayout();
		(incadrareHandicap = new Checkbox("Incadrarii intr-un grad de handicap")).setRequiredIndicatorVisible(true);
		infoPart15.add(  incadrareHandicap);
		infoLayout4.add(  infoPart15);
		infoLayout4.setAlignItems(   Alignment.CENTER);
		
		infoPart16 = new VerticalLayout();
		(reevaluareHandicap = new Checkbox("Reevaliarii incadrarii in grad de handicap")).setRequiredIndicatorVisible(true);
		infoPart16.add(reevaluareHandicap);
		infoLayout4.add(infoPart16);
		infoLayout4.setAlignItems(Alignment.CENTER);
		
		infoPart17 = new VerticalLayout();
		(obtinereOrientare = new Checkbox("Obtinerii certificatului de orientare profesionala")).setRequiredIndicatorVisible(true);
		infoPart17.add(obtinereOrientare);
		infoLayout4.add(infoPart17);
		infoLayout4.setAlignItems(Alignment.CENTER);
		
		
		infoPart18 = new VerticalLayout();
		addrCorespondenta = new TextField("Adresa completa de corespondenta:");
		addrCorespondenta .setRequiredIndicatorVisible(true);
		binder.forField(addrCorespondenta).asRequired()
				
				.bind(new ValueProvider<CESInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CESInfo source) {
						return null;
					}
				}, new Setter<CESInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CESInfo bean, String fieldvalue) {

					}
				});
		
	
		addrCorespondenta .setErrorMessage("Adresa completa de corespondenta Required !");
		addrCorespondenta .setWidth("100%");
		addrCorespondenta.setMaxLength(50);
		addrCorespondenta .setMinWidth(30f, Unit.EM);//test
		infoPart18.add(addrCorespondenta);
		infoLayout4.add(infoPart18);
		infoLayout4.setAlignItems(  Alignment.CENTER);
		

		(infoLayout5 = new HorizontalLayout()).setVisible(false);
		infoPart21 = new VerticalLayout();
		dataField = new DatePicker("Data:");
		dataField.setLocale(Locale.getDefault());
		dataField.setRequiredIndicatorVisible(true);
		binder.forField(dataField).asRequired()
			
		.bind(new ValueProvider<CESInfo, LocalDate>() {

			private static final long serialVersionUID = 1L;

			@Override
			public LocalDate apply(CESInfo source) {
				return null;
			}
		},  new Setter<CESInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CESInfo bean, LocalDate fieldvalue) {

					}
				});
		
	
		dataField.setErrorMessage("Date Required !");
		infoPart21.add(  dataField);
		infoLayout5.add(  infoPart21);
		infoLayout5.setAlignItems(  Alignment.CENTER);
		infoPart22 = new VerticalLayout();
		titlu = new ComboBox<String>("Titlul: ");
		titlu.setItems("DL.", "Dna.");
		titlu.setValue("DL.");
		titlu.setRequiredIndicatorVisible(true);
		binder.forField(titlu).asRequired()
			
				.bind(new ValueProvider<CESInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CESInfo source) {
						return null;
					}
				}, new Setter<CESInfo, String>() {

					
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CESInfo bean, String fieldvalue) {

					}
				});
	
		
		titlu.setErrorMessage("Titlu Required !");
		infoPart22.add(  titlu);
		infoLayout5.add(  infoPart22);
		infoLayout5.setAlignItems(  Alignment.CENTER);
		content.add(  infoLayout);
		content.setAlignItems(  Alignment.CENTER);
		content.add(  infoLayout2);
		content.setAlignItems(  Alignment.CENTER);
		content.add(  infoLayout3);
		content.setAlignItems(  Alignment.CENTER);
		content.add(  infoLayout4);
		content.setAlignItems( Alignment.CENTER);
		content.add(  infoLayout5);
		content.setAlignItems( Alignment.CENTER);
		generateLayout = new HorizontalLayout();
		generate = new Button("Generate", VaadinIcon.FILE_PROCESS.create());
		generate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		generate.getClassNames().add("frendly");
		generate.addClickListener(evt -> {
			if (complete.getValue()) {
				map.put("prenume",PDFHelper.capitalizeWords(prenumeField.getValue().trim()));
				map.put("nume",PDFHelper.capitalizeWords(numeField.getValue().trim()));
				map.put("CNP", CNPField.getValue());
				map.put("serie", serieField.getValue().trim());
				map.put("nrSerie", nrSerieField.getValue().trim());
				map.put("localitate", localitateField.getValue().trim());
				map.put("strada", stradaField.getValue().trim());
				map.put("nrStrada", nrStrField.getValue().trim());
				map.put("bloc", blocField.getValue().trim());
				map.put("scara", scaraField.getValue().trim());
				map.put("etaj", etajField.getValue().trim());
				map.put("apartament", apartamentField.getValue().trim());
				
				map.put("judet", judetField.getValue().trim());
				map.put("addrCorespondenta", addrCorespondenta.getValue().trim());
				map.put("incadrareHandicap", incadrareHandicap.getValue().toString());
				map.put("reevaluareHandicap", reevaluareHandicap.getValue().toString());
				map.put("obtinereOrientare", obtinereOrientare.getValue().toString());
				map.put("mobil", telMobilField.getValue().trim());
				
				map.put("data", ((LocalDate) dataField.getValue()).format(DateTimeFormatter.ISO_LOCAL_DATE));
				map.put("titlu", titlu.getOptionalValue().get());
			}
			prenumeField.clear();
			numeField.clear();
			CNPField.clear();
			addrCorespondenta.clear();
			serieField.clear();
			nrSerieField.clear();
			localitateField.clear();
			stradaField.clear();
			nrStrField.clear();
			blocField.clear();
			scaraField.clear();
			etajField.clear();
			apartamentField.clear();
			judetField.clear();
			incadrareHandicap.clear();
			reevaluareHandicap.clear();
			obtinereOrientare.clear();
			telMobilField.clear();
			dataField.clear();
			titlu.clear();
			PDFCESCreator pdfcr =  new PDFCESCreator(map,Utils.getTimeStr());
		 	String fn =  pdfcr.getID();
		    
			 RouteConfiguration.forSessionScope().removeRoute(CESPDF.class);
		 	 RouteConfiguration.forSessionScope().setRoute(CESPDF.NAME, CESPDF.class);
		Map <String,String> sss =  new HashMap<String,String>();
		sss.put("tm", fn);
		
		UI.getCurrent().navigate("CESPDF",QueryParameters.simple(sss));
		});
		generateLayout.add(generate);
		binder.addStatusChangeListener(event -> {
			System.out.println(binder.isValid());
			if (!complete.getValue() || binder.isValid()) {
				generate.setEnabled(true);
			} else {
				generate.setEnabled(false);
			}
		});
		content.add(  generateLayout);
		content.setAlignItems(   Alignment.CENTER);
		content.setMargin(false);
		add(content);
	}

	private void toggleVisibility() {
		infoLayout.setVisible(!infoLayout.isVisible());
		infoLayout2.setVisible(!infoLayout2.isVisible());
		infoLayout3.setVisible(!infoLayout3.isVisible());
		infoLayout4.setVisible(!infoLayout4.isVisible());
		infoLayout5.setVisible(!infoLayout5.isVisible());
		generate.setEnabled(!generate.isEnabled());
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		RouteConfiguration.forSessionScope().removeRoute(NAME);
	    
	}
}
