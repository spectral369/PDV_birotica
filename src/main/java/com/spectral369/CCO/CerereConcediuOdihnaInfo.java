
package com.spectral369.CCO;

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
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;

public class CerereConcediuOdihnaInfo extends HorizontalLayout implements RouterLayout, AfterNavigationObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "CerereConcediuOdihnaInfo";
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
    HorizontalLayout infoLayout3;
    VerticalLayout infoPart9;

    TextField prenumeField;
    TextField numeField;
    TextField functiaField;
    TextField nrZileField;
    TextField anField;
    DatePicker dataStartField;
    DatePicker dataEndField;
    TextField inloctiitorField;
    DatePicker dataCerere;

    HorizontalLayout generateLayout;
    Button generate;
    CerereConcediuOdihnaPDF pdf;
    Map<String, String> map;
    private Binder<CerereConcediuOdihnaInfo> binder;

    public CerereConcediuOdihnaInfo() {
	content = new VerticalLayout();
	map = new HashMap<String, String>();
	binder = new Binder<CerereConcediuOdihnaInfo>(CerereConcediuOdihnaInfo.class);
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
	IarbaTitle = new Button("Cerere Concediu", VaadinIcon.FILE_TEXT_O.create());

	IarbaTitle.setEnabled(false);
	IarbaTitle.setId("mainTitle");
	IarbaTitle.addClassName("clearDisabled");
	titlelLayout.add(IarbaTitle);
	content.add(titlelLayout);
	content.setAlignItems(Alignment.CENTER);
	checkLayout = new HorizontalLayout();
	complete = new Checkbox("Cu completare de date ?!", false);
	complete.addValueChangeListener(evt -> {
	    toggleVisibility();
	    UI.getCurrent().push();
	});
	checkLayout.add(complete);
	content.add(checkLayout);
	content.setAlignItems(Alignment.CENTER);
	(infoLayout = new HorizontalLayout()).setVisible(false);
	/*
	 * infoPart1 = new VerticalLayout(); prenumeField = new TextField("Prenume:");
	 * prenumeField.setRequiredIndicatorVisible(true);
	 * 
	 * binder.forField(prenumeField).asRequired() .withValidator(str -> str.length()
	 * > 2, "Prenumele sa fie mai mare decat 2 caractere") .bind(new
	 * ValueProvider<CerereConcediuOdihnaInfo, String>() {
	 * 
	 * private static final long serialVersionUID = 1L;
	 * 
	 * @Override public String apply(CerereConcediuOdihnaInfo source) { return null;
	 * 
	 * } }, new Setter<CerereConcediuOdihnaInfo, String>() {
	 * 
	 * private static final long serialVersionUID = 1L;
	 * 
	 * @Override public void accept(CerereConcediuOdihnaInfo bean, String
	 * fieldvalue) {
	 * 
	 * } });
	 * 
	 * 
	 * prenumeField.setErrorMessage("Prenume Required !");
	 * infoPart1.add(prenumeField); infoLayout.add(infoPart1);
	 * infoLayout.setAlignItems(Alignment.CENTER);
	 */
	infoPart2 = new VerticalLayout();
	numeField = new TextField("Nume complet:");
	numeField.setRequiredIndicatorVisible(true);
	binder.forField(numeField).asRequired()
		.withValidator(str -> str.length() > 2, "Numele sa fie mai mare decat 2 caractere")
		.bind(new ValueProvider<CerereConcediuOdihnaInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(CerereConcediuOdihnaInfo source) {
			return null;
		    }
		}, new Setter<CerereConcediuOdihnaInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereConcediuOdihnaInfo bean, String fieldvalue) {

		    }
		});

	numeField.setErrorMessage("Nume Required !");
	numeField.setWidth("100%");
	numeField.setMaxLength(25);
	numeField.setMinWidth(20f, Unit.EM);// test
	infoPart2.add(numeField);
	infoLayout.add(infoPart2);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart3 = new VerticalLayout();
	functiaField = new TextField("Functia:");

	functiaField.setRequiredIndicatorVisible(true);
	binder.forField(functiaField).asRequired()
		.withValidator(str -> str.length() > 2, "Functia necesita mai mult de 2 caractere")

		.bind(new ValueProvider<CerereConcediuOdihnaInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(CerereConcediuOdihnaInfo source) {
			return null;
		    }
		}, new Setter<CerereConcediuOdihnaInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereConcediuOdihnaInfo bean, String fieldvalue) {

		    }
		});

	functiaField.setErrorMessage("Functie required !");
	infoPart3.add(functiaField);
	infoLayout.add(infoPart3);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart4 = new VerticalLayout();
	nrZileField = new TextField("Nr. Zile Concediu:");
	nrZileField.setRequiredIndicatorVisible(true);
	binder.forField(nrZileField).asRequired()

		.bind(new ValueProvider<CerereConcediuOdihnaInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(CerereConcediuOdihnaInfo source) {
			return null;
		    }
		}, new Setter<CerereConcediuOdihnaInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereConcediuOdihnaInfo bean, String fieldvalue) {

		    }
		});

	nrZileField.setErrorMessage("Nr. Zile !");
	infoPart4.add(nrZileField);
	infoLayout.add(infoPart4);
	infoLayout.setAlignItems(Alignment.CENTER); /// info
	infoLayout2 = new HorizontalLayout();
	infoLayout2.setVisible(false);

	infoPart5 = new VerticalLayout();
	anField = new TextField("An Concediu:");
	anField.setRequiredIndicatorVisible(true);
	binder.forField(anField).asRequired().bind(new ValueProvider<CerereConcediuOdihnaInfo, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public String apply(CerereConcediuOdihnaInfo source) {
		return null;
	    }
	}, new Setter<CerereConcediuOdihnaInfo, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void accept(CerereConcediuOdihnaInfo bean, String fieldvalue) {

	    }
	});

	anField.setErrorMessage("Zile Concediu din an Required !");
	infoPart5.add(anField);
	infoLayout2.add(infoPart5);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart6 = new VerticalLayout();
	dataStartField = new DatePicker("Data Start:");
	dataStartField.setLocale(Locale.getDefault());
	dataStartField.setRequiredIndicatorVisible(true);
	binder.forField(dataStartField).asRequired()

		.bind(new ValueProvider<CerereConcediuOdihnaInfo, LocalDate>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public LocalDate apply(CerereConcediuOdihnaInfo source) {
			return null;
		    }
		}, new Setter<CerereConcediuOdihnaInfo, LocalDate>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereConcediuOdihnaInfo bean, LocalDate fieldvalue) {

		    }
		});

	dataStartField.setErrorMessage("Data de start Required !");
	infoPart6.add(dataStartField);
	infoLayout2.add(infoPart6);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart7 = new VerticalLayout();
	dataEndField = new DatePicker("Data Sfarsit:");
	// dataEndField.setDateFormat("dd-MM-yyyy");
	dataEndField.setLocale(Locale.getDefault());
	dataEndField.setRequiredIndicatorVisible(true);
	binder.forField(dataEndField).asRequired()

		.bind(new ValueProvider<CerereConcediuOdihnaInfo, LocalDate>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public LocalDate apply(CerereConcediuOdihnaInfo source) {
			return null;
		    }
		}, new Setter<CerereConcediuOdihnaInfo, LocalDate>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereConcediuOdihnaInfo bean, LocalDate fieldvalue) {

		    }
		});

	dataEndField.setErrorMessage("Data de incheiere Required !");
	infoPart7.add(dataEndField);
	infoLayout2.add(infoPart7);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart8 = new VerticalLayout();
	inloctiitorField = new TextField("Inlocuitor:");
	inloctiitorField.setRequiredIndicatorVisible(true);
	binder.forField(inloctiitorField).asRequired()
		.withValidator(str -> str.length() > 2, "Nume inlocuitor sa fie mai mare decat 2 caractere")
		.bind(new ValueProvider<CerereConcediuOdihnaInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(CerereConcediuOdihnaInfo source) {
			return null;
		    }
		}, new Setter<CerereConcediuOdihnaInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereConcediuOdihnaInfo bean, String fieldvalue) {

		    }
		});

	inloctiitorField.setErrorMessage("Inlocuitor Required !");
	infoPart8.add(inloctiitorField);
	infoLayout2.add(infoPart8);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoLayout3 = new HorizontalLayout();
	infoLayout3.setVisible(false);
	infoPart9 = new VerticalLayout();
	dataCerere = new DatePicker("Data Cerere:");
	dataCerere.setLocale(Locale.getDefault());
	dataCerere.setRequiredIndicatorVisible(true);
	binder.forField(dataCerere).asRequired()

		.bind(new ValueProvider<CerereConcediuOdihnaInfo, LocalDate>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public LocalDate apply(CerereConcediuOdihnaInfo source) {
			return null;
		    }
		}, new Setter<CerereConcediuOdihnaInfo, LocalDate>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(CerereConcediuOdihnaInfo bean, LocalDate fieldvalue) {

		    }
		});

	dataCerere.setErrorMessage("Data Cerere Required !");
	infoPart9.add(dataCerere);
	infoLayout3.add(infoPart9);
	infoLayout3.setAlignItems(Alignment.CENTER);

	content.add(infoLayout);
	content.setAlignItems(Alignment.CENTER);
	content.add(infoLayout2);
	content.setAlignItems(Alignment.CENTER);
	content.add(infoLayout3);
	content.setAlignItems(Alignment.CENTER);

	generateLayout = new HorizontalLayout();
	generate = new Button("Generate", VaadinIcon.FILE_PROCESS.create());
	generate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	generate.getClassNames().add("frendly");
	generate.addClickListener(evt -> { // String output = input.substring(0, 1).toUpperCase() + input.substring(1);
	    if (complete.getValue()) {
		map.put("nume", PDFHelper.capitalizeWords(numeField.getValue().trim()));
		map.put("functia", functiaField.getValue().trim());
		map.put("nrZile", nrZileField.getValue());
		LocalDate dt = dataStartField.getValue();
		dt.format(DateTimeFormatter.ISO_LOCAL_DATE);
		map.put("ziStart", String.valueOf(dt.getDayOfMonth()));
		map.put("lunaStart", String.valueOf(dt.getMonthValue()));
		map.put("anStart", String.valueOf(dt.getYear()));
		dt = dataEndField.getValue();
		dt.format(DateTimeFormatter.ISO_LOCAL_DATE);
		map.put("ziEnd", String.valueOf(dt.getDayOfMonth()));
		map.put("lunaEnd", String.valueOf(dt.getMonthValue()));
		map.put("anEnd", String.valueOf(dt.getYear()));

		map.put("anConcediu", anField.getValue().trim());
		map.put("inlocuitor", PDFHelper.capitalizeWords(inloctiitorField.getValue().trim()));
		dt = dataCerere.getValue();
		dt.format(DateTimeFormatter.ISO_LOCAL_DATE);
		map.put("ziCerere", String.valueOf(dt.getDayOfMonth()));
		map.put("lunaCerere", String.valueOf(dt.getMonthValue()));
		map.put("anCerere", String.valueOf(dt.getYear()));
	    }
	    numeField.clear();
	    functiaField.clear();
	    anField.clear();
	    nrZileField.clear();
	    dataEndField.clear();
	    dataStartField.clear();
	    inloctiitorField.clear();
	    dataCerere.clear();

	    PDFCCOCreator pdfcr = new PDFCCOCreator(map, Utils.getTimeStr());
	    String fn = pdfcr.getID();

	    RouteConfiguration.forSessionScope().removeRoute(CerereConcediuOdihnaPDF.class);
	    RouteConfiguration.forSessionScope().setRoute(CerereConcediuOdihnaPDF.NAME, CerereConcediuOdihnaPDF.class);

	    Map<String, String> sss = new HashMap<String, String>();
	    sss.put("tm", fn);
	    try {
		UI.getCurrent().navigate("CerereConcediuOdihnaPDF", QueryParameters.simple(sss));
	    } catch (Exception e) {
		System.out.println("errr cerer");
	    }
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

	generate.setEnabled(!generate.isEnabled());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
	RouteConfiguration.forSessionScope().removeRoute(NAME);

    }
}
