package com.spectral369.CSPH;

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
    VerticalLayout infoPart9;

    TextField marcaField;
    TextField numeField;
    TextField localitateField;
    TextField nrStrField;
    TextField serieField;
    TextField cnpField;
    TextField NrCI;
    TextField telefonField;
    ComboBox<String> titlu;

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
	//complete.setEnabled(false);
	checkLayout.add(complete);
	content.add(checkLayout);
	content.setAlignItems(Alignment.CENTER);
	
	infoLayout =  new HorizontalLayout();
	infoLayout.setVisible(false);
	infoPart2 = new VerticalLayout();
	numeField = new TextField("Nume complet:");
	numeField.setRequiredIndicatorVisible(true);
	binder.forField(numeField).asRequired()
		.withValidator(str -> str.length() > 4, "Numele sa fie mai mare decat 4 caractere")
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

	nrStrField.setErrorMessage("Nr. Casa Required !");
	infoPart4.add(nrStrField);
	infoLayout.add(infoPart4);
	infoLayout.setAlignItems( Alignment.CENTER);
	
	/// info 2
	infoLayout2 = new HorizontalLayout();
	infoLayout2.setVisible(false);

	infoPart5 = new VerticalLayout();
	serieField = new TextField("Serie:");
	serieField.setValue("TZ");
	serieField.setRequiredIndicatorVisible(true);
	binder.forField(serieField).asRequired()
		.withValidator(str -> str.length() == 2, "Serie C.I trebuie sa fie de 2 caractere")
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

	serieField.setErrorMessage("Serie C.I Required !");
	infoPart5.add(serieField);
	infoLayout2.add(infoPart5);
	infoLayout2.setAlignItems( Alignment.CENTER);
	
	
	infoPart6 = new VerticalLayout();
	NrCI = new TextField("Nr. C.I :");
	NrCI.setRequiredIndicatorVisible(true);
	binder.forField(NrCI).asRequired()
	.withValidator(str -> str.length() == 6, "Serie C.I trebuie sa fie de 6 caractere")
	.withConverter(new StringToIntegerConverter("Must be Integer"))
	
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

	NrCI.setErrorMessage("Nr. C.I Required !");
	infoPart6.add(NrCI);
	infoLayout2.add(infoPart6);
	infoLayout2.setAlignItems(Alignment.CENTER);
	
	
	
	

	infoPart7 = new VerticalLayout();
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
	infoPart7.add(cnpField);
	infoLayout2.add(infoPart7);
	infoLayout2.setAlignItems( Alignment.CENTER);

	infoPart8 = new VerticalLayout();
	telefonField = new TextField("Telefon:");
	telefonField.setRequiredIndicatorVisible(true);

	binder.forField(telefonField).asRequired()
	.withValidator(str -> str.matches("^([00]{0,1}[\\d]{1,3}[0-9]{9,12})$"), "Numar de telefon invalid !")
	.withConverter(new StringToBigIntegerConverter("Nr. telefon poate contine doar cifre"))

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

	telefonField.setErrorMessage("Date Required !");
	infoPart8.add(telefonField);
	infoLayout2.add(infoPart8);
	infoLayout2.setAlignItems( Alignment.CENTER);
	
	infoPart1 = new VerticalLayout();
	marcaField = new TextField("Marca Masinii:");
	marcaField.setRequiredIndicatorVisible(true);
	binder.forField(marcaField).asRequired()
		.withValidator(str -> str.length() > 2, "Marca masinii trebuie sa fie mai mare decat 2 caractere")

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

	marcaField.setErrorMessage("Marca masinii Required !");
	infoPart1.add(marcaField);
	infoLayout2.add(infoPart1);
	infoLayout2.setAlignItems( Alignment.CENTER);
	
	
	
	infoPart9 = new VerticalLayout();
	titlu = new ComboBox<String>("Titlul: ");
	titlu.setItems("Dl.", "D-na.");
	titlu.setRequired(true);
	titlu.setValue("Dl.");
	titlu.setRequiredIndicatorVisible(true);
	binder.forField(titlu).asRequired().bind(new ValueProvider<CerereScutirePersoaneHandicapInfo, String>() {



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

	titlu.setErrorMessage("Titlu Required !");
	infoPart9.add(titlu);
	infoLayout2.add(infoPart9);
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
	    if (complete.getValue()) {
		
	
		
		map.put("nume", PDFHelper.capitalizeWords(numeField.getValue()));
		map.put("localitate", localitateField.getValue().trim());
		map.put("nrStrada", nrStrField.getValue().trim());
		map.put("serie", serieField.getValue().trim());
		map.put("nrSerie",NrCI.getValue().trim());
		map.put("cnp", cnpField.getValue().trim());
		map.put("telefon", telefonField.getValue().trim());
		map.put("marcaAuto",PDFHelper.capitalizeWords(marcaField.getValue().trim()));
		map.put("titlu", titlu.getOptionalValue().get());
		

	    }
	    numeField.clear();
	    localitateField.clear();
	    nrStrField.clear();
	    serieField.clear();
	    NrCI.clear();
	    cnpField.clear();
	    telefonField.clear();
	    marcaField.clear();
	    titlu.clear();
	    
	  

	  

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
