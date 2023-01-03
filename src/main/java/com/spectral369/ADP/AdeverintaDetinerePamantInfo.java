package com.spectral369.ADP;

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
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;

public class AdeverintaDetinerePamantInfo extends HorizontalLayout implements RouterLayout, AfterNavigationObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "AdeverintaDetinerePamantInfo";
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
    AdeverintaDetinerePamantPDF pdf;
    Map<String, String> map;
    private Binder<AdeverintaDetinerePamantInfo> binder;

    public AdeverintaDetinerePamantInfo() {
	content = new VerticalLayout();
	map = new HashMap<String, String>();
	binder = new Binder<AdeverintaDetinerePamantInfo>(AdeverintaDetinerePamantInfo.class);
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
	IarbaTitle = new Button("Adeverinta Detinere Teren Extravilan", VaadinIcon.FILE_TEXT_O.create());
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
	binder.forField(titlu).asRequired().bind(new ValueProvider<AdeverintaDetinerePamantInfo, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public String apply(AdeverintaDetinerePamantInfo source) {
		return null;
	    }
	}, new Setter<AdeverintaDetinerePamantInfo, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void accept(AdeverintaDetinerePamantInfo bean, String fieldvalue) {

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
		.bind(new ValueProvider<AdeverintaDetinerePamantInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdeverintaDetinerePamantInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaDetinerePamantInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaDetinerePamantInfo bean, String fieldvalue) {

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

		.bind(new ValueProvider<AdeverintaDetinerePamantInfo, BigInteger>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public BigInteger apply(AdeverintaDetinerePamantInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaDetinerePamantInfo, BigInteger>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaDetinerePamantInfo bean, BigInteger fieldvalue) {

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
	binder.forField(localitate).asRequired().bind(new ValueProvider<AdeverintaDetinerePamantInfo, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public String apply(AdeverintaDetinerePamantInfo source) {
		return null;
	    }
	}, new Setter<AdeverintaDetinerePamantInfo, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void accept(AdeverintaDetinerePamantInfo bean, String fieldvalue) {

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
		.bind(new ValueProvider<AdeverintaDetinerePamantInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdeverintaDetinerePamantInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaDetinerePamantInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaDetinerePamantInfo bean, String fieldvalue) {

		    }
		});

	nrStrField.setErrorMessage("Numar de casa required !");
	infoPart4.add(nrStrField);
	infoLayout.add(infoPart4);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart11 = new VerticalLayout();
	detinePamant = new ComboBox<String>("Detine Teren? ");
	detinePamant.setItems("DETINE", "NU DETINE");
	detinePamant.setPlaceholder("Detine pamant ?");
	detinePamant.setRequiredIndicatorVisible(true);
	detinePamant.setRequired(true);

	binder.forField(detinePamant).asRequired();
	binder.forField(detinePamant).asRequired()

		.bind(new ValueProvider<AdeverintaDetinerePamantInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdeverintaDetinerePamantInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaDetinerePamantInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaDetinerePamantInfo bean, String fieldvalue) {

		    }
		});

	detinePamant.setErrorMessage("Selectare Optiune Required !");
	infoPart11.add(detinePamant);
	infoLayout.add(infoPart11);
	infoLayout.setAlignItems(Alignment.CENTER);
	
	infoLayout1 =  new HorizontalLayout();
	infoLayout1.setVisible(false);
	infoPart5= new VerticalLayout();
	intocmit = new ComboBox<String>("Intocmit");
	intocmit.setItems(Utils.getSettingsInfo().get("intocmitRegAgr"));
	intocmit.setPlaceholder("Intocmit ?");
	intocmit.setRequiredIndicatorVisible(true);
	intocmit.setRequired(true);

	binder.forField(intocmit).asRequired();
	binder.forField(intocmit).asRequired()

		.bind(new ValueProvider<AdeverintaDetinerePamantInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdeverintaDetinerePamantInfo source) {
			return null;
		    }
		}, new Setter<AdeverintaDetinerePamantInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdeverintaDetinerePamantInfo bean, String fieldvalue) {

		    }
		});

	intocmit.setErrorMessage("Selectare Optiune Required !");
	infoPart5.add(intocmit);
	infoLayout1.add(infoPart5);
	infoLayout1.setAlignItems(Alignment.CENTER);

	content.add(infoLayout);
	content.setAlignItems(Alignment.CENTER);
	content.add(infoLayout1);
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

	    }
	    numeField.clear();
	    nrStrField.clear();
	    cnpField.clear();
	    nrStrField.clear();

	    AdeverintaDetinerePamantCreator pdfcr = new AdeverintaDetinerePamantCreator(map, Utils.getTimeStr());
	    String fn = pdfcr.getID();

	    RouteConfiguration.forSessionScope().removeRoute(AdeverintaDetinerePamantPDF.class);
	    RouteConfiguration.forSessionScope().setRoute(AdeverintaDetinerePamantPDF.NAME,
		    AdeverintaDetinerePamantPDF.class);
	    Map<String, String> sss = new HashMap<String, String>();
	    sss.put("tm", fn);

	    UI.getCurrent().navigate("AdeverintaDetinerePamantPDF", QueryParameters.simple(sss));
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
	generate.setEnabled(!generate.isEnabled());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
	RouteConfiguration.forSessionScope().removeRoute(NAME);
    }
}
