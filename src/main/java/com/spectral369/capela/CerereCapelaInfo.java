
package com.spectral369.capela;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.spectral369.birotica.MainView;
import com.spectral369.utils.PDFHelper;
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
import com.vaadin.flow.data.converter.StringToBigIntegerConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;

public class CerereCapelaInfo extends HorizontalLayout implements RouterLayout, AfterNavigationObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "CerereCapelaInfo";
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
    CerereCapelaPDF pdf;
    Map<String, String> map;
    private Binder<CerereCapelaInfo> binder;

    public CerereCapelaInfo() {
	content = new VerticalLayout();
	map = new HashMap<String, String>();
	binder = new Binder<CerereCapelaInfo>(CerereCapelaInfo.class);
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
	IarbaTitle = new Button("Cerere Capela", VaadinIcon.FILE_TEXT_O.create());
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
	/*infoPart1 = new VerticalLayout();
	prenumeField = new TextField("Prenume:");
	prenumeField.setRequiredIndicatorVisible(true);
	binder.forField(prenumeField).asRequired()
		.withValidator(str -> str.length() > 2, "Prenumele sa fie mai mare decat 2 caractere")

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

	prenumeField.setErrorMessage("Prenume Required");
	infoPart1.add(prenumeField);
	infoLayout.add(infoPart1);
	infoLayout.setAlignItems(Alignment.CENTER);
*/
	infoPart2 = new VerticalLayout();
	numeField = new TextField("Nume complet:");
	numeField.setRequiredIndicatorVisible(true);
	binder.forField(numeField).asRequired()
		.withValidator(str -> str.length() > 2, "Numele sa fie mai mare decat 2 caractere")
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

	numeField.setErrorMessage("Nume Required");
	numeField.setWidth("100%");
	numeField.setMaxLength(25);
	numeField.setMinWidth(20f, Unit.EM);//test
	infoPart2.add(numeField);
	infoLayout.add(infoPart2);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart3 = new VerticalLayout();
	localitateField = new TextField("Localitate:");
	localitateField.setValue("Dudestii-Vechi");
	localitateField.setRequiredIndicatorVisible(true);
	binder.forField(localitateField).asRequired()
		.withValidator(str -> str.length() > 2, "Numele localitatii sa fie mai mare decat 2 caractere")

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

	localitateField.setErrorMessage("Localitate Required");
	infoPart3.add(localitateField);
	infoLayout.add(infoPart3);
	infoLayout.setAlignItems(Alignment.CENTER);

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

	nrStrField.setErrorMessage("NrRequired");
	infoPart4.add(nrStrField);
	infoLayout.add(infoPart4);
	infoLayout.setAlignItems(Alignment.CENTER);
	/// info 2
	infoLayout2 = new HorizontalLayout();
	infoLayout2.setVisible(false);

	infoPart5 = new VerticalLayout();
	judetField = new TextField("Judet:");
	judetField.setRequiredIndicatorVisible(true);
	binder.forField(judetField).asRequired()
		.withValidator(str -> str.length() > 2, "Judetul trebuie sa aibe mai mult de 2 caractere")
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

	judetField.setErrorMessage("Judet Required");
	infoPart5.add(judetField);
	infoLayout2.add(infoPart5);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart6 = new VerticalLayout();
	cnpField = new TextField("CNP:");
	cnpField.setRequiredIndicatorVisible(true);
	cnpField.addValueChangeListener(e -> {
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

	cnpField.setErrorMessage("CNP Required");
	infoPart6.add(cnpField);
	infoLayout2.add(infoPart6);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart7 = new VerticalLayout();
	nrZileField = new TextField("Nr. zile inchiriere:");
	nrZileField.setRequiredIndicatorVisible(true);
	binder.forField(nrZileField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
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

	nrZileField.setErrorMessage("Nr. Zile Required");
	infoPart7.add(nrZileField);
	infoLayout2.add(infoPart7);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart8 = new VerticalLayout();
	dateField = new DatePicker("Data inceput:");
	dateField.setLocale(Locale.getDefault());
	// dateField.form("dd-MM-yyyy");
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

	dateField.setErrorMessage("Date Required");
	infoPart8.add(dateField);
	infoLayout2.add(infoPart8);
	infoLayout2.setAlignItems(Alignment.CENTER);

	content.add(infoLayout);
	content.setAlignItems(Alignment.CENTER);
	content.add(infoLayout2);
	content.setAlignItems(Alignment.CENTER);

	generateLayout = new HorizontalLayout();
	generate = new Button("Generate", VaadinIcon.FILE_PROCESS.create());
	generate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	generate.addClickListener(evt -> {
	    // String output = input.substring(0, 1).toUpperCase() + input.substring(1);
	    if (complete.getValue()) {
		/*String prenume = prenumeField.getValue().trim().substring(0, 1).toUpperCase()
			+ prenumeField.getValue().trim().substring(1);*/
		/*String nume = numeField.getValue().trim().substring(0, 1).toUpperCase()
			+ numeField.getValue().trim().substring(1);*/
		/*String localitate = localitateField.getValue().trim().substring(0, 1).toUpperCase()
			+ localitateField.getValue().trim().substring(1);*/
		//map.put("prenume", prenume);
		map.put("nume", PDFHelper.capitalizeWords(numeField.getValue().trim()));
		map.put("nrStrada", nrStrField.getValue().trim());
		map.put("localitate", PDFHelper.capitalizeWords(localitateField.getValue().trim()));
		map.put("judet", judetField.getValue().trim());
		map.put("cnp", cnpField.getValue().trim());
		LocalDate dt = dateField.getValue();
		dt.format(DateTimeFormatter.ISO_LOCAL_DATE);
		map.put("nrZile", nrZileField.getValue().trim());
		map.put("ziua", String.valueOf(dt.getDayOfMonth()));
		map.put("luna", String.valueOf(dt.getMonthValue()));
		map.put("anul", String.valueOf(dt.getYear()));

	    }
	  //  prenumeField.clear();
	    numeField.clear();
	    nrStrField.clear();
	    localitateField.clear();
	    judetField.clear();
	    cnpField.clear();
	    nrStrField.clear();
	    dateField.clear();
		  
	    RouteConfiguration.forSessionScope().setRoute(CerereCapelaPDF.NAME,
		    CerereCapelaPDF.class);
	    VaadinSession session = VaadinSession.getCurrent();
	    session.setAttribute("map", map);
	
	   
	    UI.getCurrent().navigate(CerereCapelaPDF.class);

	});
	generateLayout.add(generate);
	binder.addStatusChangeListener(event -> {
	   // System.out.println(binder.isValid());
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
	// TODO Auto-generated method stub
	RouteConfiguration.forSessionScope().removeRoute(NAME);
    }
}
