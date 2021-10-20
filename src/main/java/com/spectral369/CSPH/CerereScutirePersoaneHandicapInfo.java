package com.spectral369.CSPH;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.spectral369.birotica.MainView;
import com.spectral369.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
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

public class CerereScutirePersoaneHandicapInfo extends HorizontalLayout
	implements RouterLayout, AfterNavigationObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "CerereScutirePersoaneHandicapInfo";
    VerticalLayout content;
    HorizontalLayout backLayout;
    Button backbtn;
    HorizontalLayout titlelLayout;
    Button IarbaTitle;
    HorizontalLayout checkLayout;
    Checkbox complete;
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
    DatePicker dateField;

    HorizontalLayout generateLayout;
    Button generate;
    CerereScutirePersoaneHandicapPDF pdf;
    Map<String, String> map;
    private Binder<CerereScutirePersoaneHandicapInfo> binder;

    public CerereScutirePersoaneHandicapInfo() {
	content = new VerticalLayout();
	map = new HashMap<String, String>();
	binder = new Binder<CerereScutirePersoaneHandicapInfo>(CerereScutirePersoaneHandicapInfo.class);
	backLayout = new HorizontalLayout();
	backLayout.setMargin(true);
	backbtn = new Button("Back", VaadinIcon.ARROW_CIRCLE_LEFT.create());
	backbtn.getClassNames().add("quiet");
	backbtn.addClickListener(evt -> {
	    RouteConfiguration.forSessionScope().removeRoute(NAME);
	    UI.getCurrent().navigate(MainView.class);
	});
	backLayout.add(backbtn);
	content.add(backLayout);
	content.setAlignItems(Alignment.START);
	titlelLayout = new HorizontalLayout();
	IarbaTitle = new Button("Cerere Scutire Persoane cu Handicap", VaadinIcon.FILE_TEXT_O.create());
	IarbaTitle.setEnabled(false);
	IarbaTitle.setId("mainTitle");
	IarbaTitle.addClassName("clearDisabled");
	titlelLayout.add(IarbaTitle);
	content.add(titlelLayout);
	content.setAlignItems(Alignment.CENTER);
	checkLayout = new HorizontalLayout();
	(complete = new Checkbox("Cu completare de date ?!", false)).addValueChangeListener(evt -> {
	    toggleVisibility();
	    UI.getCurrent().push();
	});
	complete.setEnabled(false);
	checkLayout.add(complete);
	content.add(checkLayout);
	content.setAlignItems(Alignment.CENTER);
	(infoLayout = new HorizontalLayout()).setVisible(false);
	infoPart1 = new VerticalLayout();
	prenumeField = new TextField("Prenume:");
	prenumeField.setRequiredIndicatorVisible(true);
	binder.forField(prenumeField).asRequired()
		.withValidator(str -> str.length() > 2, "Prenumele sa fie mai mare decat 2 caractere")

		.bind(new ValueProvider<CerereScutirePersoaneHandicapInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(CerereScutirePersoaneHandicapInfo source) {
			return null;
		    }
		}, new Setter<CerereScutirePersoaneHandicapInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereScutirePersoaneHandicapInfo bean, String fieldvalue) {

		    }
		});

	prenumeField.setErrorMessage("Prenume Required !");
	infoPart1.add(prenumeField);
	infoLayout.add(infoPart1);
	infoLayout.setAlignItems( Alignment.CENTER);

	infoPart2 = new VerticalLayout();
	numeField = new TextField("Nume:");
	numeField.setRequiredIndicatorVisible(true);
	binder.forField(numeField).asRequired()
		.withValidator(str -> str.length() > 2, "Numele sa fie mai mare decat 2 caractere")
		.bind(new ValueProvider<CerereScutirePersoaneHandicapInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(CerereScutirePersoaneHandicapInfo source) {
			return null;
		    }
		}, new Setter<CerereScutirePersoaneHandicapInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereScutirePersoaneHandicapInfo bean, String fieldvalue) {

		    }
		});

	numeField.setErrorMessage("Nume Required !");
	infoPart2.add(numeField);
	infoLayout.add(infoPart2);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart3 = new VerticalLayout();
	localitateField = new TextField("Localitate:");
	localitateField.setValue("Dudestii-Vechi");
	localitateField.setRequiredIndicatorVisible(true);
	binder.forField(localitateField).asRequired()
		.withValidator(str -> str.length() > 2, "Numele localitatii sa fie mai mare decat 2 caractere")

		.bind(new ValueProvider<CerereScutirePersoaneHandicapInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(CerereScutirePersoaneHandicapInfo source) {
			return null;
		    }
		}, new Setter<CerereScutirePersoaneHandicapInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereScutirePersoaneHandicapInfo bean, String fieldvalue) {

		    }
		});

	localitateField.setErrorMessage("Localitate required !");
	infoPart3.add(localitateField);
	infoLayout.add(infoPart3);
	infoLayout.setAlignItems( Alignment.CENTER);

	infoPart4 = new VerticalLayout();
	nrStrField = new TextField("Numar Casa:");
	nrStrField.setRequiredIndicatorVisible(true);
	binder.forField(nrStrField).asRequired()

		.bind(new ValueProvider<CerereScutirePersoaneHandicapInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(CerereScutirePersoaneHandicapInfo source) {
			return null;
		    }
		}, new Setter<CerereScutirePersoaneHandicapInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereScutirePersoaneHandicapInfo bean, String fieldvalue) {

		    }
		});

	nrStrField.setErrorMessage("Model required !");
	infoPart4.add(nrStrField);
	infoLayout.add(infoPart4);
	infoLayout.setAlignItems( Alignment.CENTER);
	/// info 2
	infoLayout2 = new HorizontalLayout();
	infoLayout2.setVisible(false);

	infoPart5 = new VerticalLayout();
	judetField = new TextField("Judet:");
	judetField.setRequiredIndicatorVisible(true);
	binder.forField(judetField).asRequired()
		.withValidator(str -> str.length() > 2, "Judetul trebuie sa aibe mai mult de 2 caractere")
		.bind(new ValueProvider<CerereScutirePersoaneHandicapInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(CerereScutirePersoaneHandicapInfo source) {
			return null;
		    }
		}, new Setter<CerereScutirePersoaneHandicapInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereScutirePersoaneHandicapInfo bean, String fieldvalue) {

		    }
		});

	judetField.setErrorMessage("Judet Required !");
	infoPart5.add(judetField);
	infoLayout2.add(infoPart5);
	infoLayout2.setAlignItems( Alignment.CENTER);

	infoPart6 = new VerticalLayout();
	cnpField = new TextField("CNP:");
	cnpField.setRequiredIndicatorVisible(true);
	cnpField.addValueChangeListener(e -> {
	    cnpField.setValue(cnpField.getValue().replaceAll("[a-zA-Z]+", ""));
	});
	binder.forField(cnpField).asRequired()
		.withValidator(str -> str.toString().length() == 13, "CNP-ul are 13 cifre")
		.withConverter(new StringToBigIntegerConverter("CNP-ul poate contine doar cifre"))

		.bind(new ValueProvider<CerereScutirePersoaneHandicapInfo, BigInteger>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public BigInteger apply(CerereScutirePersoaneHandicapInfo source) {
			return null;
		    }
		}, new Setter<CerereScutirePersoaneHandicapInfo, BigInteger>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereScutirePersoaneHandicapInfo bean, BigInteger fieldvalue) {

		    }
		});

	cnpField.setErrorMessage("CNP Required !");
	infoPart6.add(cnpField);
	infoLayout2.add(infoPart6);
	infoLayout2.setAlignItems( Alignment.CENTER);

	infoPart7 = new VerticalLayout();
	nrZileField = new TextField("Nr. zile inchiriere:");
	nrZileField.setRequiredIndicatorVisible(true);
	binder.forField(nrZileField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
		.bind(new ValueProvider<CerereScutirePersoaneHandicapInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Integer apply(CerereScutirePersoaneHandicapInfo source) {
			return null;
		    }
		}, new Setter<CerereScutirePersoaneHandicapInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereScutirePersoaneHandicapInfo bean, Integer fieldvalue) {

		    }
		});

	nrZileField.setErrorMessage("Nr. Zile Required !");
	infoPart7.add(nrZileField);
	infoLayout2.add(infoPart7);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart8 = new VerticalLayout();
	dateField = new DatePicker("Data inceput:");
	//dateField.setDateFormat("dd-MM-yyyy");
	dateField.setLocale(Locale.getDefault());
	dateField.setRequiredIndicatorVisible(true);

	binder.forField(dateField).asRequired()
		.withValidator(returnDate -> !returnDate.isBefore(LocalDate.now()),
			"Data nu poate fi anterioara zilei de azi")

		.bind(new ValueProvider<CerereScutirePersoaneHandicapInfo, LocalDate>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public LocalDate apply(CerereScutirePersoaneHandicapInfo source) {
			return null;
		    }
		}, new Setter<CerereScutirePersoaneHandicapInfo, LocalDate>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereScutirePersoaneHandicapInfo bean, LocalDate fieldvalue) {

		    }
		});

	dateField.setErrorMessage("Date Required !");
	infoPart8.add(dateField);
	infoLayout2.add(infoPart8);
	infoLayout2.setAlignItems( Alignment.CENTER);

	content.add(infoLayout);
	content.setAlignItems(Alignment.CENTER);
	content.add(infoLayout2);
	content.setAlignItems(Alignment.CENTER);

	generateLayout = new HorizontalLayout();
	generate = new Button("Generate", VaadinIcon.FILE_PROCESS.create());
	generate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	generate.getClassNames().add("frendly");
	generate.addClickListener(evt -> {
	    // String output = input.substring(0, 1).toUpperCase() + input.substring(1);
	    if (complete.getValue()) {
		String prenume = prenumeField.getValue().trim().substring(0, 1).toUpperCase()
			+ prenumeField.getValue().trim().substring(1);
		String nume = numeField.getValue().trim().substring(0, 1).toUpperCase()
			+ numeField.getValue().trim().substring(1);
		String localitate = localitateField.getValue().trim().substring(0, 1).toUpperCase()
			+ localitateField.getValue().trim().substring(1);
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

		PDFCSPHCreator pdfcr =  new PDFCSPHCreator(map,Utils.getTimeStr());
	 	String fn =  pdfcr.getID();
	    
		 RouteConfiguration.forSessionScope().removeRoute(CerereScutirePersoaneHandicapPDF.class);
	 	 RouteConfiguration.forSessionScope().setRoute(CerereScutirePersoaneHandicapPDF.NAME, CerereScutirePersoaneHandicapPDF.class);
	Map <String,String> sss =  new HashMap<String,String>();
	sss.put("tm", fn);
	
	UI.getCurrent().navigate("CerereScutirePersoaneHandicap",QueryParameters.simple(sss));
				
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
	content.add(generateLayout);
	content.setAlignItems(Alignment.CENTER);
	content.setMargin(false);
	add(content);

    }

    private void toggleVisibility() {
	infoLayout.setVisible(!infoLayout.isVisible());
	infoLayout2.setVisible(!infoLayout2.isVisible());

	generate.setEnabled(!generate.isEnabled());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {

	RouteConfiguration.forSessionScope().removeRoute(NAME);
    }
}
