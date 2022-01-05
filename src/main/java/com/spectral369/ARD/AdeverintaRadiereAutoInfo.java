package com.spectral369.ARD;

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
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;

public class AdeverintaRadiereAutoInfo extends HorizontalLayout implements RouterLayout, AfterNavigationObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "ADVRadiereAutoInfo";
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

    Map<String, String> map;
    private Binder<AdeverintaRadiereAutoInfo> binder;
    String fn = null;

    public AdeverintaRadiereAutoInfo() {

	content = new VerticalLayout();
	map = new HashMap<String, String>();
	binder = new Binder<AdeverintaRadiereAutoInfo>(AdeverintaRadiereAutoInfo.class);
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
	IarbaTitle = new Button("Adeverinta de Radiere Auto", VaadinIcon.FILE_TEXT_O.create());
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

	prenumeField.setErrorMessage("Prenume Required");

	infoPart1.add(prenumeField);
	infoLayout.add(infoPart1);
	infoLayout.setAlignItems(Alignment.CENTER);*/

	infoPart2 = new VerticalLayout();
	numeField = new TextField("Nume complet:");
	numeField.setRequiredIndicatorVisible(true);
	binder.forField(numeField).asRequired()
		.withValidator(str -> str.length() > 2, "Numele sa fie mai mare decat 2 caractere")
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

	numeField.setErrorMessage("Nume Required");
	numeField.setWidth("100%");
	numeField.setMaxLength(35);
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

	localitateField.setErrorMessage("Localitate Required !");
	infoPart3.add(localitateField);
	infoLayout.add(infoPart3);
	infoLayout.setAlignItems(Alignment.CENTER);

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

	nrStrField.setErrorMessage("Model Required!");
	infoPart4.add(nrStrField);
	infoLayout.add(infoPart4);
	infoLayout.setAlignItems(Alignment.CENTER);
	/// info 2
	infoLayout2 = new HorizontalLayout();
	infoLayout2.setVisible(false);

	infoPart5 = new VerticalLayout();
	marcaAutoField = new TextField("Marca Auto:");
	marcaAutoField.setRequiredIndicatorVisible(true);
	binder.forField(marcaAutoField).asRequired()
		.withValidator(str -> str.length() > 2, "Numele marcii sa fie mai mare decat 2 caractere")
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

	marcaAutoField.setErrorMessage("Marca REquired! ");
	infoPart5.add(marcaAutoField);
	infoLayout2.add(infoPart5);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart6 = new VerticalLayout();
	modelAutoField = new TextField("Modelul Auto:");
	modelAutoField.setRequiredIndicatorVisible(true);
	binder.forField(modelAutoField).asRequired()
		.withValidator(str -> str.length() > 2, "Modelul sa fie mai mare decat 2 caractere")
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

	modelAutoField.setErrorMessage("Model REequired!");
	infoPart6.add(modelAutoField);
	infoLayout2.add(infoPart6);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart7 = new VerticalLayout();
	capacitateCilField = new TextField("Capacitate Auto:");
	capacitateCilField.setRequiredIndicatorVisible(true);
	binder.forField(capacitateCilField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
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

	capacitateCilField.setErrorMessage("Capacitate  Required !");
	infoPart7.add(capacitateCilField);
	infoLayout2.add(infoPart7);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart8 = new VerticalLayout();
	serieMotorField = new TextField("Serie Motor:");

	infoPart8.add(serieMotorField);
	infoLayout2.add(infoPart8);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoLayout3 = new HorizontalLayout();
	infoLayout3.setVisible(false);

	infoPart9 = new VerticalLayout();
	serieSasiuField = new TextField("Serie Sasiu:");
	serieSasiuField.setRequiredIndicatorVisible(true);
	binder.forField(serieSasiuField).asRequired()
		.withValidator(str -> str.length() > 2, "Seria Sasiu sa fie mai mare decat 2 caractere")
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

	serieSasiuField.setErrorMessage("Serie Sasiu Required!");
	infoPart9.add(serieSasiuField);
	infoLayout3.add(infoPart9);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoPart10 = new VerticalLayout();
	nrInmatriculareField = new TextField("Nr. Inmatriculare:");

	infoPart10.add(nrInmatriculareField);
	infoLayout3.add(infoPart10);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoPart11 = new VerticalLayout();
	addrDepozitare = new TextField("Adresa Depozitare:");
	addrDepozitare.setRequiredIndicatorVisible(true);
	binder.forField(addrDepozitare).asRequired()
		.withValidator(str -> str.length() > 2, "Adresa sa fie mai mare decat 2 caractere")
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

	addrDepozitare.setErrorMessage("Adressa Depozitare Required!");
	infoPart11.add(addrDepozitare);
	infoLayout3.add(infoPart11);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoPart12 = new VerticalLayout();
	titlu = new ComboBox<String>("Titlul: ");
	titlu.setItems("Dl.", "D-na.", "Subscrisa");
	titlu.setRequired(true);
	titlu.setValue("Dl.");
	titlu.setRequiredIndicatorVisible(true);
	binder.forField(titlu).asRequired().bind(new ValueProvider<AdeverintaRadiereAutoInfo, String>() {

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

	titlu.setErrorMessage("Titlu Required !");
	infoPart12.add(titlu);
	infoLayout3.add(infoPart12);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoLayout4 = new HorizontalLayout();
	infoLayout4.setVisible(false);

	infoPart13 = new VerticalLayout();
	intocmitField = new TextField("Intocmit de catre:");
	intocmitField.setRequiredIndicatorVisible(true);
	binder.forField(intocmitField).asRequired()
		.withValidator(str -> str.length() > 2, "Numele persoanei sa fie mai mare decat 2 caractere")
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

	intocmitField.setErrorMessage("Persoana Required!");
	infoPart13.add(intocmitField);
	infoLayout4.add(infoPart13);
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

	    if (complete.getValue()) {
		/*String prenume = prenumeField.getValue().trim().substring(0, 1).toUpperCase()
			+ prenumeField.getValue().trim().substring(1);*/
		/*String nume = numeField.getValue().trim().substring(0, 1).toUpperCase()
			+ numeField.getValue().trim().substring(1);
		String localitate = localitateField.getValue().trim().substring(0, 1).toUpperCase()
			+ localitateField.getValue().trim().substring(1);*/
		//map.put("prenume", prenume);
		map.put("nume", PDFHelper.capitalizeWords(numeField.getValue().trim()));
		map.put("nrStrada", nrStrField.getValue().trim());
		map.put("localitate", PDFHelper.capitalizeWords(localitateField.getValue().trim()));
		String marca = new String();
		marca = marcaAutoField.getValue().trim();
		map.put("marca", marca);
		map.put("model", modelAutoField.getValue().trim());
		map.put("capacitate", capacitateCilField.getValue().trim());
		map.put("serieMotor", serieMotorField.getValue().trim());
		map.put("serieSasiu", serieSasiuField.getValue().trim());
		map.put("nrInmatriculare", nrInmatriculareField.getValue().trim());
		map.put("addrDepozitare", addrDepozitare.getValue().trim());
		map.put("titlu", titlu.getOptionalValue().get());
		map.put("intocmit",PDFHelper.capitalizeWords(intocmitField.getValue().trim()));
	    }
	  //  prenumeField.clear();
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

	    PDFCR pdfcr = new PDFCR(map, Utils.getTimeStr());
	    fn = pdfcr.getID();

	    RouteConfiguration.forSessionScope().removeRoute(AdeverintaRadiereAutoPDF.class);
	    RouteConfiguration.forSessionScope().setRoute(AdeverintaRadiereAutoPDF.NAME,
		    AdeverintaRadiereAutoPDF.class);
	    Map<String, String> sss = new HashMap<String, String>();
	    sss.put("tm", fn);

	    UI.getCurrent().navigate("ADVRadiereAutoPDF", QueryParameters.simple(sss));

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
	infoLayout2.setVisible(!infoLayout2.isVisible());
	infoLayout3.setVisible(!infoLayout3.isVisible());
	infoLayout4.setVisible(!infoLayout4.isVisible());

	generate.setEnabled(!generate.isEnabled());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
	// TODO Auto-generated method stub
	RouteConfiguration.forSessionScope().removeRoute(NAME);
    }

}
