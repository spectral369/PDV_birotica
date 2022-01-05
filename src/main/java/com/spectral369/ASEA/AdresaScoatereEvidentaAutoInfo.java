package com.spectral369.ASEA;

import java.util.HashMap;
import java.util.Map;

import com.spectral369.birotica.MainView;
import com.spectral369.utils.PDFHelper;
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
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;

public class AdresaScoatereEvidentaAutoInfo extends HorizontalLayout implements RouterLayout, AfterNavigationObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "AdresaScoatereEvidentaAutoInfo";
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
    HorizontalLayout infoLayout31;
    VerticalLayout infopart14;
    VerticalLayout infopart15;
    VerticalLayout infopart16;
    VerticalLayout infopart17;
    HorizontalLayout infoLayout5;
    VerticalLayout infopart18;
    VerticalLayout infopart19;
    VerticalLayout infopart20;
    VerticalLayout infopart21;
    HorizontalLayout infoLayout4;
    VerticalLayout infoPart13;

    HorizontalLayout generateLayout;
    Button generate;

    Map<String, String> map;
    private Binder<AdresaScoatereEvidentaAutoInfo> binder;
    String fn = null;
    private TextField catreField;
    private TextField act1Field;
    private TextField dataAct1Field;
    private TextField contribuabilField;
    private TextField taraField;
    private TextField judetField;
    private TextField codulPostal;
    private TextField localitateField;
    private TextField satField;
    private TextField stradaField;
    private TextField nrStrField;
    private ComboBox<String> titlu;
    private TextField intocmitField;
    private TextField blField;
    private TextField scField;
    private TextField etField;
    private TextField apField;
    private TextField serieCIField;
    private TextField nrCIField;

    public AdresaScoatereEvidentaAutoInfo() {

	content = new VerticalLayout();
	map = new HashMap<String, String>();
	binder = new Binder<AdresaScoatereEvidentaAutoInfo>(AdresaScoatereEvidentaAutoInfo.class);
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
	IarbaTitle = new Button("Adresa Scoatere Evidenta Auto", VaadinIcon.FILE_TEXT_O.create());
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
	catreField = new TextField("Catre :");
	catreField.setRequiredIndicatorVisible(true);
	binder.forField(catreField).asRequired()
		.withValidator(str -> str.length() > 2, "Numele trebuie sa fie mai mare decat 2 caractere")
		.bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdresaScoatereEvidentaAutoInfo source) {
			return null;
		    }
		}, new Setter<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdresaScoatereEvidentaAutoInfo bean, String fieldvalue) {

		    }
		});

	catreField.setErrorMessage("Nume institutie Required");
	catreField.setWidth("100%");
	catreField.setMaxLength(35);
	catreField.setMinWidth(20f, Unit.EM);// test
	infoPart1.add(catreField);
	infoLayout.add(infoPart1);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart2 = new VerticalLayout();
	act1Field = new TextField("Act 1:");
	// act1Field.setValue("Dudestii-Vechi");
	act1Field.setRequiredIndicatorVisible(true);
	binder.forField(act1Field).asRequired()
		.withValidator(str -> str.length() > 2, "Act1 sa fie mai mare decat 2 caractere")
		.bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdresaScoatereEvidentaAutoInfo source) {
			return null;
		    }
		}, new Setter<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdresaScoatereEvidentaAutoInfo bean, String fieldvalue) {

		    }
		});

	act1Field.setErrorMessage("Localitate Required !");
	infoPart2.add(act1Field);
	infoLayout.add(infoPart2);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart3 = new VerticalLayout();
	dataAct1Field = new TextField("Numar Casa:");
	dataAct1Field.setRequiredIndicatorVisible(true);
	binder.forField(dataAct1Field).asRequired()

		.bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdresaScoatereEvidentaAutoInfo source) {
			return null;
		    }
		}, new Setter<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdresaScoatereEvidentaAutoInfo bean, String fieldvalue) {

		    }
		});

	dataAct1Field.setErrorMessage("Model Required!");
	infoPart3.add(dataAct1Field);
	infoLayout.add(infoPart3);
	infoLayout.setAlignItems(Alignment.CENTER);

	infoPart3 = new VerticalLayout();
	contribuabilField = new TextField("Contribuabilul :");
	contribuabilField.setRequiredIndicatorVisible(true);
	binder.forField(contribuabilField).asRequired()
		.withValidator(str -> str.length() > 2, "Numele trebuie sa fie mai mare decat 2 caractere")
		.bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdresaScoatereEvidentaAutoInfo source) {
			return null;
		    }
		}, new Setter<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdresaScoatereEvidentaAutoInfo bean, String fieldvalue) {

		    }
		});

	contribuabilField.setErrorMessage("Nume Contribuabil Required");
	contribuabilField.setWidth("100%");
	contribuabilField.setMaxLength(35);
	contribuabilField.setMinWidth(20f, Unit.EM);// test
	infoPart4 = new VerticalLayout();
	infoPart4.add(contribuabilField);
	infoLayout.add(infoPart4);
	infoLayout.setAlignItems(Alignment.CENTER);

	/// info 2
	infoLayout2 = new HorizontalLayout();
	infoLayout2.setVisible(false);

	infoPart5 = new VerticalLayout();
	taraField = new TextField("Tara:");
	taraField.setValue("---");
	taraField.setRequiredIndicatorVisible(false);
	infoPart5.add(taraField);
	infoLayout2.add(infoPart5);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart6 = new VerticalLayout();
	judetField = new TextField("Judet/Sector:");
	judetField.setRequiredIndicatorVisible(true);
	binder.forField(judetField).asRequired()
		.withValidator(str -> str.length() > 2, "Judetul trebuie sa fie mai mare decat 2 caractere")
		.bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdresaScoatereEvidentaAutoInfo source) {
			return null;
		    }
		}, new Setter<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdresaScoatereEvidentaAutoInfo bean, String fieldvalue) {

		    }
		});

	judetField.setErrorMessage("Judet Reequired!");
	infoPart6.add(judetField);
	infoLayout2.add(infoPart6);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart7 = new VerticalLayout();
	codulPostal = new TextField("Codul Postal:");
	codulPostal.setRequiredIndicatorVisible(true);
	binder.forField(codulPostal).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
		.bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Integer apply(AdresaScoatereEvidentaAutoInfo source) {
			return null;
		    }
		}, new Setter<AdresaScoatereEvidentaAutoInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdresaScoatereEvidentaAutoInfo bean, Integer fieldvalue) {

		    }
		});

	codulPostal.setErrorMessage("Cod Postal Required !");
	infoPart7.add(codulPostal);
	infoLayout2.add(infoPart7);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoPart8 = new VerticalLayout();
	localitateField = new TextField("Municipiul/Orasul sau comuna:");
	localitateField.setRequiredIndicatorVisible(true);
	binder.forField(localitateField).asRequired()
		.withValidator(str -> str.length() > 2,
			"Municipiul/Orasul sau comuna trebuie sa fie mai mare decat 2 caractere")
		.bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdresaScoatereEvidentaAutoInfo source) {
			return null;
		    }
		}, new Setter<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdresaScoatereEvidentaAutoInfo bean, String fieldvalue) {

		    }
		});
	localitateField.setErrorMessage("Municipiul/Orasul sau comuna Required !");
	infoPart8.add(localitateField);
	infoLayout2.add(infoPart8);
	infoLayout2.setAlignItems(Alignment.CENTER);

	infoLayout3 = new HorizontalLayout();
	infoLayout3.setVisible(false);

	infoPart9 = new VerticalLayout();
	satField = new TextField("Satul:");
	/// set to option combo box dv/ch/col
	infoPart9.add(satField);
	infoLayout3.add(infoPart9);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoPart10 = new VerticalLayout();
	stradaField = new TextField("Strada:");
	stradaField.setRequiredIndicatorVisible(true);
	stradaField.setValue("Nespecificata");
	binder.forField(stradaField).asRequired()
		.withValidator(str -> str.length() > 2, "Strada trebuie sa fie mai mare decat 2 caractere")
		.bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdresaScoatereEvidentaAutoInfo source) {
			return null;
		    }
		}, new Setter<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdresaScoatereEvidentaAutoInfo bean, String fieldvalue) {

		    }
		});
	stradaField.setErrorMessage("Strada Required !");
	infoPart10.add(stradaField);
	infoLayout3.add(infoPart10);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoPart11 = new VerticalLayout();
	nrStrField = new TextField("Nr. casa:");
	nrStrField.setRequiredIndicatorVisible(true);
	binder.forField(nrStrField).asRequired()
		.withValidator(str -> str.length() > 2, "Nr. casa sa fie mai mare decat 2 caractere")
		.bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdresaScoatereEvidentaAutoInfo source) {
			return null;
		    }
		}, new Setter<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdresaScoatereEvidentaAutoInfo bean, String fieldvalue) {

		    }
		});

	nrStrField.setErrorMessage("Nr. casa Required!");
	infoPart11.add(nrStrField);
	infoLayout3.add(infoPart11);
	infoLayout3.setAlignItems(Alignment.CENTER);

	infoLayout31 = new HorizontalLayout();
	infoLayout31.setVisible(false);
	infopart14 = new VerticalLayout();
	blField = new TextField("Bloc :");
	infopart14.add(blField);
	infoLayout31.add(infopart14);
	infoLayout31.setAlignItems(Alignment.CENTER);

	infopart15 = new VerticalLayout();
	scField = new TextField("Scara :");
	infopart15.add(scField);
	infoLayout31.add(infopart15);
	infoLayout31.setAlignItems(Alignment.CENTER);

	infopart16 = new VerticalLayout();
	etField = new TextField("Etaj :");
	infopart16.add(etField);
	infoLayout31.add(infopart16);
	infoLayout31.setAlignItems(Alignment.CENTER);

	infopart17 = new VerticalLayout();
	apField = new TextField("Apartament :");
	infopart17.add(apField);
	infoLayout31.add(infopart17);
	infoLayout31.setAlignItems(Alignment.CENTER);

	infoLayout5 = new HorizontalLayout();
	infopart18 = new VerticalLayout();
	serieCIField = new TextField("Serie  :");
	serieCIField.setRequiredIndicatorVisible(true);
	binder.forField(serieCIField).asRequired()
		.withValidator(str -> str.length() == 2, "Seria trebuie sa fie de 2 charactere")
		.bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdresaScoatereEvidentaAutoInfo source) {
			return null;
		    }
		}, new Setter<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdresaScoatereEvidentaAutoInfo bean, String fieldvalue) {

		    }
		});

	serieCIField.setErrorMessage("Serie Required!");
	infopart18.add(serieCIField);
	infoLayout5.add(infopart18);
	infoLayout5.setAlignItems(Alignment.CENTER);

	infopart19 = new VerticalLayout();
	nrCIField = new TextField("Numar CI  :");
	nrCIField.setRequiredIndicatorVisible(true);
	binder.forField(nrCIField).asRequired()
		.withValidator(str -> str.length() == 6, "Numar CI trebuie sa fie de 6 cifre")
		.withConverter(new StringToIntegerConverter("Must be Integer"))
		.bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public Integer apply(AdresaScoatereEvidentaAutoInfo source) {
			return null;
		    }
		}, new Setter<AdresaScoatereEvidentaAutoInfo, Integer>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdresaScoatereEvidentaAutoInfo bean, Integer fieldvalue) {

		    }
		});

	serieCIField.setErrorMessage("Numar CI Required!");
	infopart19.add(nrCIField);
	infoLayout5.add(infopart19);
	infoLayout5.setAlignItems(Alignment.CENTER);

	/// add here

	// add here

	infoPart12 = new VerticalLayout();
	titlu = new ComboBox<String>("Titlul: ");
	titlu.setItems("Dl.", "D-na.", "Subscrisa");
	titlu.setRequired(true);
	titlu.setValue("Dl.");
	titlu.setRequiredIndicatorVisible(true);
	binder.forField(titlu).asRequired().bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public String apply(AdresaScoatereEvidentaAutoInfo source) {
		return null;
	    }
	}, new Setter<AdresaScoatereEvidentaAutoInfo, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void accept(AdresaScoatereEvidentaAutoInfo bean, String fieldvalue) {

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
		.bind(new ValueProvider<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public String apply(AdresaScoatereEvidentaAutoInfo source) {
			return null;
		    }
		}, new Setter<AdresaScoatereEvidentaAutoInfo, String>() {

		    private static final long serialVersionUID = 1L;

		    @Override
		    public void accept(AdresaScoatereEvidentaAutoInfo bean, String fieldvalue) {

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
	content.add(infoLayout31);
	content.setAlignItems(Alignment.CENTER);
	content.add(infoLayout4);
	content.setAlignItems(Alignment.CENTER);

	generateLayout = new HorizontalLayout();
	generate = new Button("Generate", VaadinIcon.FILE_PROCESS.create());

	generate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	generate.getClassNames().add("frendly");
	generate.addClickListener(evt -> {

	    if (complete.getValue()) {
		map.put("nume", PDFHelper.capitalizeWords(catreField.getValue().trim()));
		map.put("nrStrada", dataAct1Field.getValue().trim());
		map.put("localitate", PDFHelper.capitalizeWords(act1Field.getValue().trim()));
		String marca = new String();
		marca = taraField.getValue().trim();
		map.put("marca", marca);
		map.put("model", judetField.getValue().trim());
		map.put("capacitate", codulPostal.getValue().trim());
		map.put("serieMotor", localitateField.getValue().trim());
		map.put("serieSasiu", satField.getValue().trim());
		map.put("nrInmatriculare", stradaField.getValue().trim());
		map.put("nrStrField", nrStrField.getValue().trim());
		map.put("titlu", titlu.getOptionalValue().get());
		map.put("intocmit", PDFHelper.capitalizeWords(intocmitField.getValue().trim()));
	    }
	    catreField.clear();
	    dataAct1Field.clear();
	    contribuabilField.clear();
	    act1Field.clear();
	    taraField.clear();
	    judetField.clear();
	    codulPostal.clear();
	    localitateField.clear();
	    satField.clear();
	    stradaField.clear();
	    nrStrField.clear();
	    titlu.clear();
	    intocmitField.clear();

	    RouteConfiguration.forSessionScope().setRoute(AdresaScoatereEvidentaAutoPDF.NAME,
		    AdresaScoatereEvidentaAutoPDF.class);
	    VaadinSession session = VaadinSession.getCurrent();
	    session.setAttribute("map", map);

	    UI.getCurrent().navigate(AdresaScoatereEvidentaAutoPDF.class);

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
	infoLayout31.setVisible(!infoLayout31.isVisible());
	infoLayout4.setVisible(!infoLayout4.isVisible());

	generate.setEnabled(!generate.isEnabled());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
	// TODO Auto-generated method stub
	RouteConfiguration.forSessionScope().removeRoute(NAME);
    }

}