package com.spectral369.PVIarba;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.spectral369.birotica.MainView;
import com.spectral369.utils.Utils;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.HasValue.ValueChangeEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.converter.StringToBigIntegerConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;

public class PVIarbaInfo extends HorizontalLayout implements RouterLayout, AfterNavigationObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "PVIarba";
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
    VerticalLayout infoPart21;
    VerticalLayout infoPart22;
    HorizontalLayout infoLayout2;
    VerticalLayout infoPart3;
    HorizontalLayout infoLayout3;
    VerticalLayout infoPart4;
    VerticalLayout infoPart5;
    VerticalLayout infoPart6;
    VerticalLayout infoPart7;
    HorizontalLayout infoLayout4;
    VerticalLayout infoPart8;
    VerticalLayout infoPart9;
//infoPart1

    DatePicker dateField;
    // InlineDateTimeField timeField;
    TextField whenField;
    // infoPart2
    TextArea jobField;
    // infopart3
    TextField satField;
    TextField nrCasaField;
    TextField adresaLucrareField;
    TextField sumaTotalaField;
    // infoPart4
    TextField intocmitField;
    TextField sefServicuField;

    TextField numeField;
    TextField cnpField;

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
	IarbaTitle = new Button("Proces Verbal - Iarba", VaadinIcon.FILE_TEXT_O.create());
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
	checkLayout.add(complete);
	content.add(checkLayout);
	content.setAlignItems(Alignment.CENTER);
	(infoLayout = new HorizontalLayout()).setVisible(false);
	infoPart1 = new VerticalLayout();
	dateField = new DatePicker("Data:");
	// dateField.setDateFormat("dd-MM-yyyy");
	dateField.setLocale(Locale.getDefault());
	dateField.setRequiredIndicatorVisible(true);
	binder.forField(dateField).asRequired()

		.bind(new ValueProvider<PVIarbaInfo, LocalDate>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public LocalDate apply(PVIarbaInfo source) {
			return null;
		    }
		}, new Setter<PVIarbaInfo, LocalDate>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(PVIarbaInfo bean, LocalDate fieldvalue) {

		    }
		});

	dateField.setErrorMessage("Date Required !");
	infoPart1.add(dateField);
	infoLayout.add(infoPart1);
	infoLayout.setAlignItems(Alignment.CENTER);
	/// test

	whenField = new TextField("Time:");
	whenField.setValue(
		LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.US)));
	whenField.setValueChangeMode(ValueChangeMode.ON_BLUR);

	whenField.addValueChangeListener(new HasValue.ValueChangeListener<ValueChangeEvent<String>>() {

	    /**
	     * 
	     */
	    private static final long serialVersionUID = 1L;

	    @Override
	    public void valueChanged(ValueChangeEvent<String> event) {
		String input = whenField.getValue();
		try {
		    DateTimeFormatter f = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.US); // Automatically
		    // localize.
		    LocalTime localTime = LocalTime.parse(input, f);
		    whenField.setPlaceholder(localTime.toString());
		} catch (DateTimeParseException e) {
		    System.out.println("PVIarbaInfo ERROR - failed to parse input: " + input);
		}
	    }

	});

	infoPart2 = new VerticalLayout();

	infoPart2.add(whenField);
	infoLayout.add(infoPart2);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart21 = new VerticalLayout();
	numeField = new TextField("Nume:");
	numeField.setPlaceholder("Nume Complet");
	numeField.setRequiredIndicatorVisible(true);
	binder.forField(numeField).asRequired()

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

	numeField.setErrorMessage("Full name Required !");
	infoPart21.add(numeField);
	infoLayout.add(infoPart21);
	infoLayout.setAlignItems(Alignment.CENTER);
	//
	infoPart22 = new VerticalLayout();
	cnpField = new TextField("CNP:");
	cnpField.setRequiredIndicatorVisible(true);
	binder.forField(cnpField).asRequired()
		.withValidator(str -> str.toString().length() == 13, "CNP-ul are 13 cifre")
		.withConverter(new StringToBigIntegerConverter("CNP-ul poate contine doar cifre"))

		.bind(new ValueProvider<PVIarbaInfo, BigInteger>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public BigInteger apply(PVIarbaInfo source) {
			return null;
		    }
		}, new Setter<PVIarbaInfo, BigInteger>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(PVIarbaInfo bean, BigInteger fieldvalue) {

		    }
		});

	cnpField.setErrorMessage("CNP Required !");
	infoPart22.add(cnpField);
	infoLayout.add(infoPart22);
	infoLayout.setAlignItems(Alignment.CENTER);

	///
	/*
	 * infoPart2 = new VerticalLayout(); timeField = new
	 * InlineDateTimeField("Time:"); timeField.setLocale(Locale.getDefault());
	 * timeField.setResolution(DateTimeResolution.MINUTE);
	 * timeField.setRequiredIndicatorVisible(true);
	 * timeField.addStyleName("time-only");
	 * 
	 * infoPart2.add(timeField); infoLayout.add(infoPart2);
	 * infoLayout.setAlignItems(infoPart2, Alignment.CENTER);
	 */
	///

	infoLayout2 = new HorizontalLayout();
	infoLayout2.setWidth("45%");
	infoLayout2.setVisible(false);
	infoPart3 = new VerticalLayout();
	jobField = new TextArea("Lucrari: ");
	jobField.setRequiredIndicatorVisible(true);

	jobField.setWidth("100%");

	// jobField.addStyleName("resizable");
	// jobField.setWordWrap(true); //auto new line
	binder.forField(jobField).asRequired()

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

	jobField.setErrorMessage("NR.casa Required !");
	infoPart3.add(jobField);
	infoLayout2.add(infoPart3);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoLayout3 = new HorizontalLayout();
	infoLayout3.setVisible(false);
	infoPart4 = new VerticalLayout();
	satField = new TextField("Localitate:");
	satField.setValue("Dudestii-Vechi");
	satField.setRequiredIndicatorVisible(true);
	binder.forField(satField).asRequired()
		.withValidator(str -> str.length() > 2, "Numele localitatii sa fie mai mare decat 2 caractere")

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

	// satField.setErrorMessage("Localitate required !"));
	infoPart4.add(satField);
	infoLayout3.add(infoPart4);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoPart5 = new VerticalLayout();
	nrCasaField = new TextField("Numar Casa:");
	nrCasaField.setRequiredIndicatorVisible(true);
	binder.forField(nrCasaField).asRequired()

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

	nrCasaField.setErrorMessage("Nr Casa required !");
	infoPart5.add(nrCasaField);
	infoLayout3.add(infoPart5);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoPart6 = new VerticalLayout();
	adresaLucrareField = new TextField("Adresa lucrare:");
	adresaLucrareField.setRequiredIndicatorVisible(true);
	binder.forField(adresaLucrareField).asRequired()
		.withValidator(str -> str.length() > 2, "Adresa lucrare trebuie sa fie mai mare decat 2 caractere")
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

	adresaLucrareField.setErrorMessage("Adresa lucrare required !");
	infoPart6.add(adresaLucrareField);
	infoLayout3.add(infoPart6);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoPart7 = new VerticalLayout();
	sumaTotalaField = new TextField("Suma totala:");
	sumaTotalaField.setRequiredIndicatorVisible(true);
	binder.forField(sumaTotalaField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
		.bind(new ValueProvider<PVIarbaInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Integer apply(PVIarbaInfo source) {
			return null;
		    }
		}, new Setter<PVIarbaInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(PVIarbaInfo bean, Integer fieldvalue) {

		    }
		});

	sumaTotalaField.setErrorMessage("Suma totala required !");
	infoPart7.add(sumaTotalaField);
	infoLayout3.add(infoPart7);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoLayout4 = new HorizontalLayout();
	infoLayout4.setVisible(false);
	infoPart8 = new VerticalLayout();
	sefServicuField = new TextField("Sef Serviciu:");
	sefServicuField.setRequiredIndicatorVisible(true);
	binder.forField(sefServicuField).asRequired()
		.withValidator(str -> str.length() > 2, "Nume Sef Serviciu trebuie sa fie mai mare decat 2 caractere")
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

	sefServicuField.setErrorMessage("Nume Sef Serviciu required !");
	infoPart8.add(sefServicuField);
	infoLayout4.add(infoPart8);
	infoLayout4.setAlignItems(Alignment.CENTER);

	infoPart9 = new VerticalLayout();
	intocmitField = new TextField("Intocmit:");
	intocmitField.setRequiredIndicatorVisible(true);
	binder.forField(intocmitField).asRequired()
		.withValidator(str -> str.length() > 2, "Trebuie sa fie mai mare decat 2 caractere")
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

	intocmitField.setErrorMessage("IntocmitField required !");
	infoPart9.add(intocmitField);
	infoLayout4.add(infoPart9);
	infoLayout4.setAlignItems(Alignment.CENTER);

	content.add(infoLayout);
	content.setAlignItems(Alignment.CENTER);
	content.add(infoLayout2);
	content.setAlignItems(Alignment.CENTER);
	content.add(infoLayout3);
	content.setAlignItems(Alignment.CENTER);
	content.add(infoLayout4);
	content.setAlignItems(Alignment.CENTER);

	generateLayout = new HorizontalLayout();
	generate = new Button("Generate", VaadinIcon.FILE_PROCESS.create());
	generate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	generate.getClassNames().add("frendly");
	generate.addClickListener(evt -> {
	    // String output = input.substring(0, 1).toUpperCase() + input.substring(1);
	    if (complete.getValue()) {
		LocalDate dt = dateField.getValue();
		dt.format(DateTimeFormatter.ISO_LOCAL_DATE);
		map.put("ziua", String.valueOf(dt.getDayOfMonth()));
		map.put("luna", String.valueOf(dt.getMonthValue()));
		map.put("anul", String.valueOf(dt.getYear()));
		// LocalDate time = hmField.getValue();
		// time.format(DateTimeFormatter.ISO_LOCAL_TIME);
		map.put("time", whenField.getValue().toString());
		map.put("job", jobField.getValue());
		map.put("localitate", satField.getValue());
		map.put("nrCasa", nrCasaField.getValue());
		map.put("adresa", adresaLucrareField.getValue());
		map.put("suma", sumaTotalaField.getValue());
		map.put("intocmit", intocmitField.getValue());
		map.put("sef", sefServicuField.getValue());
		map.put("contribuabil", numeField.getValue());
		map.put("cnp", cnpField.getValue());

	    }
	    dateField.clear();

	    whenField.clear();
	    jobField.clear();
	    satField.clear();
	    nrCasaField.clear();
	    adresaLucrareField.clear();
	    sumaTotalaField.clear();
	    intocmitField.clear();
	    sefServicuField.clear();
	    numeField.clear();
	    cnpField.clear();

		PDFPVIarbaCreator pdfcr =  new PDFPVIarbaCreator(map,Utils.getTimeStr());
	 	String fn =  pdfcr.getID();
	    
		 RouteConfiguration.forSessionScope().removeRoute(PVIarbaPDF.class);
	 	 RouteConfiguration.forSessionScope().setRoute(PVIarbaPDF.NAME, PVIarbaPDF.class);
	Map <String,String> sss =  new HashMap<String,String>();
	sss.put("tm", fn);
	
	UI.getCurrent().navigate("PVIarbaPDF",QueryParameters.simple(sss));
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
	infoLayout3.setVisible(!infoLayout3.isVisible());
	infoLayout4.setVisible(!infoLayout4.isVisible());

	generate.setEnabled(!generate.isEnabled());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
	RouteConfiguration.forSessionScope().removeRoute(NAME);

    }
}
