package com.spectral369.AAI;

import java.math.BigInteger;
import java.util.HashMap;
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
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.converter.StringToBigIntegerConverter;
import com.vaadin.flow.data.converter.StringToFloatConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;

public class AdeverintaAjutorIncalzireInfo extends HorizontalLayout implements RouterLayout, AfterNavigationObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "AdeverintaAjutorIncalzireInfo";
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
    VerticalLayout infoPart31;
    VerticalLayout infoPart4;
    VerticalLayout infoPart11;

    HorizontalLayout infoLayout1;
    VerticalLayout infoPart5;

    HorizontalLayout infoLayout2;
    HorizontalLayout infoLayout3;
    HorizontalLayout infoLayout4;
    VerticalLayout infoPart6;
    VerticalLayout infoPart7;
    VerticalLayout infopart8;
    VerticalLayout infoPart9;
    VerticalLayout infoPart10;
    VerticalLayout infoPart12;
    VerticalLayout infoPart13;
    VerticalLayout infoPart14;
    VerticalLayout infoPart15;
    VerticalLayout infoPart16;

    TextField prenumeField;
    TextField numeField;
    ComboBox<String> localitate;
    TextField cnpField;
    TextField nrStrField;
    ComboBox<String> detinePamant;
    ComboBox<String> titlu;
    ComboBox<String> intocmit;

    HorizontalLayout generateLayout;
    Button generate;
    AdeverintaAjutorIncalzirePDF pdf;
    Map<String, String> map;
    private Binder<AdeverintaAjutorIncalzireInfo> binder;
    private TextField corpPrincipalField;
    private ComboBox<String> corpPrincipalTypeCombobox;
    private TextField anexaSupField;
    private ComboBox<String> anexaTypeCombobox;
    private TextField intravilanSupField;
    private TextField extravilanSupField;

    public AdeverintaAjutorIncalzireInfo() {
	content = new VerticalLayout();
	map = new HashMap<String, String>();
	binder = new Binder<AdeverintaAjutorIncalzireInfo>(AdeverintaAjutorIncalzireInfo.class);
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
	IarbaTitle = new Button("Adeverinta Ajutor Incalzire", VaadinIcon.FILE_TEXT_O.create());
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
	titlu = new ComboBox<String>("Titlul: ");
	titlu.setItems("Dl.", "D-na.");
	titlu.setRequired(true);
	titlu.setValue("Dl.");
	titlu.setRequiredIndicatorVisible(true);
	binder.forField(titlu).asRequired().bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public String apply(AdeverintaAjutorIncalzireInfo source) {
		return null;
	    }
	}, new Setter<AdeverintaAjutorIncalzireInfo, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void accept(AdeverintaAjutorIncalzireInfo bean, String fieldvalue) {

	    }
	});

	titlu.setErrorMessage("Titlu Required !");
	infoPart1.add(titlu);
	infoLayout.add(infoPart1);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart2 = new VerticalLayout();
	numeField = new TextField("Nume Complet:");
	numeField.setRequiredIndicatorVisible(true);

	binder.forField(numeField).asRequired()
		.withValidator(str -> str.length() > 5, "Numele sa fie mai mare decat 5 caractere")
		// .withValidator(str -> str.matches("^([a-zA-z-]{2,20})$"), "Doar caractere si
		// mai mare de 2 unitati")
		.bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdeverintaAjutorIncalzireInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaAjutorIncalzireInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaAjutorIncalzireInfo bean, String fieldvalue) {

		    }
		});

	numeField.setErrorMessage("Nume Required !");
	numeField.setWidth("100%");
	numeField.setMaxLength(25);
	numeField.setMinWidth(20f, Unit.EM);
	infoPart2.add(numeField);
	infoLayout.add(infoPart2);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart3 = new VerticalLayout();
	cnpField = new TextField("CNP:");
	cnpField.setRequiredIndicatorVisible(true);
	cnpField.addValueChangeListener(e -> {
	    cnpField.setValue(cnpField.getValue().replaceAll("[a-zA-Z]+", ""));
	});
	binder.forField(cnpField).asRequired()
		.withValidator(str -> str.toString().length() == 13, "CNP-ul are 13 cifre")
		.withConverter(new StringToBigIntegerConverter("CNP-ul poate contine doar cifre"))

		.bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, BigInteger>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public BigInteger apply(AdeverintaAjutorIncalzireInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaAjutorIncalzireInfo, BigInteger>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaAjutorIncalzireInfo bean, BigInteger fieldvalue) {

		    }
		});

	cnpField.setErrorMessage("CNP Required !");
	infoPart3.add(cnpField);
	infoLayout.add(infoPart3);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart31 = new VerticalLayout();
	localitate = new ComboBox<String>("Localitate: ");
	localitate.setItems("Dudestii-Vechi", "Cheglevici", "Colonia Bulgara");
	localitate.setValue("Dudestii-Vechi");
	localitate.setRequiredIndicatorVisible(true);
	binder.forField(localitate).asRequired().bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public String apply(AdeverintaAjutorIncalzireInfo source) {
		return null;
	    }
	}, new Setter<AdeverintaAjutorIncalzireInfo, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void accept(AdeverintaAjutorIncalzireInfo bean, String fieldvalue) {

	    }
	});

	localitate.setErrorMessage("Localitate Required !");
	infoPart31.add(localitate);
	infoLayout.add(infoPart31);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart4 = new VerticalLayout();
	nrStrField = new TextField("Numar Casa:");
	nrStrField.setRequiredIndicatorVisible(true);
	binder.forField(nrStrField).asRequired()
		.withValidator(str -> str.matches("^(\\d){1,4}[/]{0,1}[a-zA-Z]{0,1}$"), "Numar de casa invalid !")
		.bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdeverintaAjutorIncalzireInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaAjutorIncalzireInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaAjutorIncalzireInfo bean, String fieldvalue) {

		    }
		});

	nrStrField.setErrorMessage("Numar de casa required !");
	infoPart4.add(nrStrField);
	infoLayout.add(infoPart4);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart11 = new VerticalLayout();
	detinePamant = new ComboBox<String>("A servi la ");
	detinePamant.setItems("Ajutor Incalzire");
	detinePamant.setPlaceholder("A servi la ?");
	detinePamant.setRequiredIndicatorVisible(true);
	detinePamant.setRequired(true);

	binder.forField(detinePamant).asRequired();
	binder.forField(detinePamant).asRequired()

		.bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdeverintaAjutorIncalzireInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaAjutorIncalzireInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaAjutorIncalzireInfo bean, String fieldvalue) {

		    }
		});

	detinePamant.setErrorMessage("Selectare Optiune Required !");
	infoPart11.add(detinePamant);
	infoLayout.add(infoPart11);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoLayout1 = new HorizontalLayout();
	infoLayout1.setVisible(false);
	infoPart5 = new VerticalLayout();
	intocmit = new ComboBox<String>("Intocmit");
	intocmit.setItems(Utils.getSettingsInfo().get("intocmitRegAgr"));
	intocmit.setPlaceholder("Intocmit ?");
	intocmit.setRequiredIndicatorVisible(true);
	intocmit.setRequired(true);

	binder.forField(intocmit).asRequired();
	binder.forField(intocmit).asRequired()

		.bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdeverintaAjutorIncalzireInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaAjutorIncalzireInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaAjutorIncalzireInfo bean, String fieldvalue) {

		    }
		});

	intocmit.setErrorMessage("Selectare Optiune Required !");
	infoPart5.add(intocmit);
	infoLayout1.add(infoPart5);
	infoLayout1.setAlignItems(Alignment.CENTER);
	// new

	infoLayout2 = new HorizontalLayout();
	infoLayout2.setVisible(false);

	/*
	 * infoPart6 = new VerticalLayout(); Label casa = new
	 * Label("Casa - Suprafata corp principal- mp"); infoPart6.add(casa);
	 * infoPart6.setAlignItems(Alignment.CENTER); infoLayout2.add(infoPart6);
	 * infoLayout2.setAlignItems(Alignment.CENTER);
	 */

	infoPart7 = new VerticalLayout();
	corpPrincipalField = new TextField("Suprafata Cladire Corp Principal");
	corpPrincipalField.setRequiredIndicatorVisible(true);
	corpPrincipalField.setRequired(true);
	corpPrincipalField.setPlaceholder("Sup. corp principal");

	binder.forField(corpPrincipalField).asRequired();
	binder.forField(corpPrincipalField)
		.withConverter(new StringToIntegerConverter("Suprafata corpului poate contine doar cifre"))
		.withValidator(corp1 -> corp1 != null && corp1 > 10, "Suprafata corp cladire invalid !").asRequired()

		.bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Integer apply(AdeverintaAjutorIncalzireInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaAjutorIncalzireInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaAjutorIncalzireInfo bean, Integer fieldvalue) {

		    }
		});

	corpPrincipalField.setErrorMessage("Adaugare supr. corp principal required !");
	corpPrincipalField.setWidth("100%");
	corpPrincipalField.setMaxLength(25);
	corpPrincipalField.setMinWidth(20f, Unit.EM);
	infoPart7.add(corpPrincipalField);
	infoLayout2.add(infoPart7);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infopart8 = new VerticalLayout();
	corpPrincipalTypeCombobox = new ComboBox<String>("Material Cladire Corp Principal");
	corpPrincipalTypeCombobox.setItems("Caramida nearsa, alte materiale",
		"Caramida/material supus unui tratament termic", "Beton");
	corpPrincipalTypeCombobox.setPlaceholder("Material structura");
	corpPrincipalTypeCombobox.setRequiredIndicatorVisible(true);
	corpPrincipalTypeCombobox.setRequired(true);

	binder.forField(corpPrincipalTypeCombobox).asRequired();
	binder.forField(corpPrincipalTypeCombobox).asRequired()

		.bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdeverintaAjutorIncalzireInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaAjutorIncalzireInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaAjutorIncalzireInfo bean, String fieldvalue) {

		    }
		});

	corpPrincipalTypeCombobox.setErrorMessage("Selectare Optiune Required !");
	corpPrincipalTypeCombobox.setWidth("100%");
	corpPrincipalTypeCombobox.setMinWidth(20f, Unit.EM);
	infopart8.add(corpPrincipalTypeCombobox);
	infoLayout2.add(infopart8);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoLayout3 = new HorizontalLayout();
	infoLayout3.setVisible(false);
	/*
	 * infoPart9 = new VerticalLayout(); Label anexe = new
	 * Label("Anexe - Suprafata corpuri- mp"); infoPart9.add(anexe);
	 * infoPart9.setAlignItems(Alignment.CENTER); infoLayout3.add(infoPart9);
	 * infoLayout3.setAlignItems(Alignment.CENTER);
	 */

	infoPart10 = new VerticalLayout();
	anexaSupField = new TextField("Anexe - Suprafata corpuri- mp");
	anexaSupField.setRequiredIndicatorVisible(true);
	anexaSupField.setRequired(true);
	anexaSupField.setPlaceholder("Sup. anexe");

	binder.forField(anexaSupField).asRequired();
	binder.forField(anexaSupField)
		.withConverter(new StringToIntegerConverter("Suprafata anexelor poate contine doar cifre"))
		.withValidator(corp2 -> corp2 != null && corp2 > 5, "Suprafata anexe invalid !").asRequired()

		.bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Integer apply(AdeverintaAjutorIncalzireInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaAjutorIncalzireInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaAjutorIncalzireInfo bean, Integer fieldvalue) {

		    }
		});

	anexaSupField.setErrorMessage("Adaugare supr. anexe required !");
	anexaSupField.setWidth("100%");
	anexaSupField.setMaxLength(25);
	anexaSupField.setMinWidth(20f, Unit.EM);
	infoPart10.add(anexaSupField);
	infoLayout3.add(infoPart10);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoPart12 = new VerticalLayout();
	anexaTypeCombobox = new ComboBox<String>("Material Structura Anexa - mp");
	anexaTypeCombobox.setItems("Caramida nearsa, alte materiale", "Caramida/material supus unui tratament termic",
		"Beton");
	anexaTypeCombobox.setPlaceholder("Material structura");
	anexaTypeCombobox.setRequiredIndicatorVisible(true);
	anexaTypeCombobox.setRequired(true);

	binder.forField(anexaTypeCombobox).asRequired();
	binder.forField(anexaTypeCombobox).asRequired()

		.bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdeverintaAjutorIncalzireInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaAjutorIncalzireInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaAjutorIncalzireInfo bean, String fieldvalue) {

		    }
		});

	anexaTypeCombobox.setErrorMessage("Selectare Optiune Required !");
	anexaTypeCombobox.setWidth("100%");
	anexaTypeCombobox.setMinWidth(20f, Unit.EM);
	infoPart12.add(anexaTypeCombobox);
	infoLayout3.add(infoPart12);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoLayout4 = new HorizontalLayout();
	infoLayout4.setVisible(false);
	/*
	 * infoPart13 = new VerticalLayout(); Label intravilan = new
	 * Label("Suprafata intravilan -mp"); infoPart13.add(intravilan);
	 * infoPart13.setAlignItems(Alignment.CENTER); infoLayout4.add(infoPart13);
	 */

	infoPart14 = new VerticalLayout();
	intravilanSupField = new TextField("Suprafata Intravilan - mp");
	intravilanSupField.setRequiredIndicatorVisible(true);
	intravilanSupField.setRequired(true);
	intravilanSupField.setPlaceholder("Sup. intravilan");

	binder.forField(intravilanSupField).asRequired();
	binder.forField(intravilanSupField)
		.withConverter(new StringToIntegerConverter("Suprafata intravilan poate contine doar cifre"))
		.withValidator(corp3 -> corp3 != null && corp3 > 20, "Suprafata intravilan invalid !").asRequired()

		.bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Integer apply(AdeverintaAjutorIncalzireInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaAjutorIncalzireInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaAjutorIncalzireInfo bean, Integer fieldvalue) {

		    }
		});

	intravilanSupField.setErrorMessage("Adaugare supr. intravilan required !");
	intravilanSupField.setWidth("100%");
	intravilanSupField.setMaxLength(10);
	intravilanSupField.setMinWidth(20f, Unit.EM);
	infoPart14.add(intravilanSupField);
	infoLayout4.add(infoPart14);
	infoLayout4.setAlignItems(Alignment.CENTER);

	/*
	 * infoPart15 = new VerticalLayout(); Label extravilan = new
	 * Label("Suprafata extravilan -ha"); infoPart15.add(extravilan);
	 * infoPart15.setAlignItems(Alignment.CENTER); infoLayout4.add(infoPart15);
	 */

	infoPart16 = new VerticalLayout();
	extravilanSupField = new TextField("Suprafata extravilan - ha");
	extravilanSupField.setRequiredIndicatorVisible(true);
	extravilanSupField.setRequired(true);
	extravilanSupField.setPlaceholder("Sup. Extravilan");

	binder.forField(extravilanSupField).asRequired();
	binder.forField(extravilanSupField)
		.withConverter(new StringToFloatConverter("Suprafata extravilan poate contine doar cifre"))

		.withValidator(corp4 -> corp4 != null && corp4 >= 0.0f, "Suprafata extravilan invalid !").asRequired()

		.bind(new ValueProvider<AdeverintaAjutorIncalzireInfo, Float>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Float apply(AdeverintaAjutorIncalzireInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaAjutorIncalzireInfo, Float>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaAjutorIncalzireInfo bean, Float fieldvalue) {

		    }
		});

	extravilanSupField.setErrorMessage("Adaugare supr. extravilan required !");
	extravilanSupField.setWidth("100%");
	extravilanSupField.setMaxLength(25);
	extravilanSupField.setMinWidth(20f, Unit.EM);
	infoPart16.add(extravilanSupField);
	infoLayout4.add(infoPart16);
	infoLayout4.setAlignItems(Alignment.CENTER);

	content.add(infoLayout);
	content.setAlignItems(Alignment.CENTER);
	content.add(infoLayout1);
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

	    if (complete.getValue()) {
		map.put("titlu", titlu.getValue());
		map.put("nume", PDFHelper.capitalizeWords(numeField.getValue().trim()));
		map.put("cnp", cnpField.getValue().trim());
		map.put("localitate", localitate.getValue());
		map.put("nrCasaAddr", nrStrField.getValue().trim());
		map.put("detine", detinePamant.getValue());
		map.put("intocmit", intocmit.getValue());
		map.put("supCasa", corpPrincipalField.getValue());
		map.put("tipCasa", corpPrincipalTypeCombobox.getValue());
		map.put("supAnexe", anexaSupField.getValue());
		map.put("tipAnexe", anexaTypeCombobox.getValue());
		map.put("supIntravilan", intravilanSupField.getValue());
		map.put("supExtravilan", extravilanSupField.getValue());

	    }
	    numeField.clear();
	    nrStrField.clear();
	    cnpField.clear();
	    nrStrField.clear();

	    AdeverintaAjutorIncalzireCreator pdfcr = new AdeverintaAjutorIncalzireCreator(map, Utils.getTimeStr());
	    String fn = pdfcr.getID();

	    RouteConfiguration.forSessionScope().removeRoute(AdeverintaAjutorIncalzirePDF.class);
	    RouteConfiguration.forSessionScope().setRoute(AdeverintaAjutorIncalzirePDF.NAME,
		    AdeverintaAjutorIncalzirePDF.class);
	    Map<String, String> sss = new HashMap<String, String>();
	    sss.put("tm", fn);

	    UI.getCurrent().navigate("AdeverintaAjutorIncalzirePDF", QueryParameters.simple(sss));
	});
	generateLayout.add(generate);

	binder.addStatusChangeListener(event -> {

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
	infoLayout1.setVisible(!infoLayout1.isVisible());
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
