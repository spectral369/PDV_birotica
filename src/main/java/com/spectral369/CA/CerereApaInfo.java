package com.spectral369.CA;


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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;

public class CerereApaInfo extends HorizontalLayout implements RouterLayout, AfterNavigationObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "CerereApaInfo";
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
    HorizontalLayout infoLayout2;
    VerticalLayout infoPart5;
  
    VerticalLayout infoPart11;

    TextField prenumeField;
    TextField numeField;
    ComboBox<String> localitate;
    TextField cnpField;
    TextField nrStrField;
    TextField nrTelefon;
    TextArea issueField;
 

    HorizontalLayout generateLayout;
    Button generate;
    CerereApaPDF pdf;
    Map<String, String> map;
    private Binder<CerereApaInfo> binder;

    public CerereApaInfo() {
		content = new VerticalLayout();
		map = new HashMap<String, String>();
		binder = new Binder<CerereApaInfo>(CerereApaInfo.class);
		backLayout = new HorizontalLayout();
		backLayout.setMargin(true);
		backbtn = new Button("Back", VaadinIcon.ARROW_CIRCLE_LEFT.create());
		backbtn.getClassNames().add("quiet");
			backbtn.addClickListener(evt ->{ 
			        RouteConfiguration.forSessionScope().removeRoute(NAME);
				UI.getCurrent().navigate(MainView.class);
				});
		backLayout.add(backbtn);
		content.add(backLayout);
		content.setAlignItems(Alignment.START);
		titlelLayout = new HorizontalLayout();
		IarbaTitle = new Button("Cerere Apa", VaadinIcon.FILE_TEXT_O.create());
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
		content.setAlignItems( Alignment.CENTER);
		infoLayout = new HorizontalLayout();
	        infoLayout.setVisible(false);
		/*infoPart1 = new VerticalLayout();
		prenumeField = new TextField("Prenume:");
		prenumeField.setRequiredIndicatorVisible(true);
	
		binder.forField(prenumeField).asRequired()
				// .withValidator(str -> str.length() > 2, "Prenumele sa fie mai mare decat 2
				// caractere")
				.withValidator(str -> str.matches("^([a-zA-z-]{2,20})$"), "Doar caractere si mai mare de 2 unitati")

				.bind(new ValueProvider<InchiriereUtilajeInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(InchiriereUtilajeInfo source) {
						return null;
					}
				},  new Setter<InchiriereUtilajeInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, String fieldvalue) {

					}
				});


		
		prenumeField.setErrorMessage("Prenume Required !");
		infoPart1.add(prenumeField);
		infoLayout.add(infoPart1);
		infoLayout.setAlignItems( Alignment.CENTER);*/

		infoPart2 = new VerticalLayout();
		numeField = new TextField("Nume Complet:");
		numeField.setRequiredIndicatorVisible(true);
		/*
		 * numeField.addListener(e->{
		 * numeField.setValue(numeField.getValue().replaceAll("[0-9]+", ""));
		 * 
		 * });
		 */
		binder.forField(numeField).asRequired()
				 .withValidator(str -> str.length() > 5, "Numele sa fie mai mare decat 5 caractere")
				//.withValidator(str -> str.matches("^([a-zA-z-]{2,20})$"), "Doar caractere si mai mare de 2 unitati")
				.bind(new ValueProvider<CerereApaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereApaInfo source) {
						return null;
					}
				}, new Setter<CerereApaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereApaInfo bean, String fieldvalue) {

					}
				});

	
		numeField.setErrorMessage("Nume Required !");
		numeField.setErrorMessage("Nume Required");
		numeField.setWidth("100%");
		numeField.setMaxLength(25);
		numeField.setMinWidth(20f, Unit.EM);//test
		infoPart2.add(numeField);
		infoLayout.add(infoPart2);
		infoLayout.setAlignItems( Alignment.CENTER);

		infoPart3 = new VerticalLayout();
		cnpField = new TextField("CNP:");
		cnpField.setRequiredIndicatorVisible(true);
		cnpField.addValueChangeListener(e -> {
			cnpField.setValue(cnpField.getValue().replaceAll("[a-zA-Z]+", ""));
		});
		/*binder.forField(cnpField).asRequired()
				.withValidator(str -> str.toString().length() == 13, "CNP-ul are 13 cifre")
				.withConverter(new StringToBigIntegerConverter("CNP-ul poate contine doar cifre"))

				.bind(new ValueProvider<CerereApaInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public BigInteger apply(CerereApaInfo source) {
						return null;
					}
				}, new Setter<CerereApaInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereApaInfo bean, BigInteger fieldvalue) {

					}
				});*/

	
		cnpField.setErrorMessage("CNP Required !");
		infoPart3.add(cnpField);
		infoLayout.add(infoPart3);
		infoLayout.setAlignItems( Alignment.CENTER);
		
		infoPart31 = new VerticalLayout();
		localitate = new ComboBox<String>("Localitate: ");
		localitate.setItems("Dudestii-Vechi", "Cheglevici","Colonia Bulgara");
		localitate.setValue("Dudestii-Vechi");
		localitate.setRequiredIndicatorVisible(true);
		binder.forField(localitate).asRequired()
				.bind(new ValueProvider<CerereApaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereApaInfo source) {
						return null;
					}
				}, new Setter<CerereApaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereApaInfo bean, String fieldvalue) {

					}
				});
	
		localitate.setErrorMessage("Localitate Required !");
		infoPart31.add(localitate);
		infoLayout.add(infoPart31);
		infoLayout.setAlignItems( Alignment.CENTER);
		
		
		

		infoPart4 = new VerticalLayout();
		nrStrField = new TextField("Numar Casa:");
		nrStrField.setRequiredIndicatorVisible(true);
		binder.forField(nrStrField).asRequired()
				// .withValidator(str -> Character.isDigit(str.charAt(0)), "Primul caracter
				// trebuie sa fie o cifra")
				.withValidator(str -> str.matches("^(\\d){1,4}[/]{0,1}[a-zA-Z]{0,1}$"), "Numar de casa invalid !")
				.bind(new ValueProvider<CerereApaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereApaInfo source) {
						return null;
					}
				}, new Setter<CerereApaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereApaInfo bean, String fieldvalue) {

					}
				});

	
		nrStrField.setErrorMessage("Model required !");
		infoPart4.add(nrStrField);
		infoLayout.add(infoPart4);
		infoLayout.setAlignItems( Alignment.CENTER);

		// nrtel ^([00|+]{0,1}[\d]{1,3}[0-9]{9,12})$

		infoPart11 = new VerticalLayout();
		nrTelefon = new TextField("Nr. Telefon:");
		nrTelefon.setRequiredIndicatorVisible(true);

		/*binder.forField(nrTelefon).asRequired()
				.withValidator(str -> str.matches("^([00]{0,1}[\\d]{1,3}[0-9]{9,12})$"), "Numar de telefon invalid !")
				.withConverter(new StringToBigIntegerConverter("Nr. telefon poate contine doar cifre"))

				.bind(new ValueProvider<CerereApaInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public BigInteger apply(CerereApaInfo source) {
						return null;
					}
				}, new Setter<CerereApaInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereApaInfo bean, BigInteger fieldvalue) {

					}
				});*/

		
		nrTelefon.setErrorMessage("Telefon Required !");
		infoPart11.add(nrTelefon);
		infoLayout.add(infoPart11);
		infoLayout.setAlignItems(Alignment.CENTER);

		/// info 2
		infoLayout2 = new HorizontalLayout();
		infoLayout2.setVisible(false);

		infoPart5 = new VerticalLayout();
		issueField = new TextArea();
		issueField.setLabel("Descriere:");
		issueField.setWidth("50em");
		issueField.setHeight("25em");
		issueField.setValueChangeMode(ValueChangeMode.EAGER);
		
		issueField.setRequiredIndicatorVisible(true);
		issueField.setPlaceholder("Descrie problema aici ....");

		binder.forField(issueField).asRequired()
			.withValidator(str -> str.toString().length() > 5, "Descrierea problemei trebuie sa contina mai mult de 5 caractere!")

				.bind(new ValueProvider<CerereApaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(CerereApaInfo source) {
						return null;
					}
				}, new Setter<CerereApaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(CerereApaInfo bean, String fieldvalue) {

					}
				});

	
		issueField.setErrorMessage("Descrierea problemei este necesara !");
		infoPart5.add(issueField);
		infoLayout2.add(infoPart5);
		infoLayout2.setAlignItems( Alignment.CENTER);

		content.add(infoLayout);
		content.setAlignItems( Alignment.CENTER);
		content.add(infoLayout2);
		content.setAlignItems( Alignment.CENTER);

		generateLayout = new HorizontalLayout();
		generate = new Button("Generate", VaadinIcon.FILE_PROCESS.create());
		generate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		generate.getClassNames().add("frendly");
		generate.addClickListener(evt -> {
			// String output = input.substring(0, 1).toUpperCase() + input.substring(1);
			if (complete.getValue()) {
			/*	String prenume = prenumeField.getValue().trim().substring(0, 1).toUpperCase()
						+ prenumeField.getValue().trim().substring(1);
				String nume = numeField.getValue().trim().substring(0, 1).toUpperCase()
						+ numeField.getValue().trim().substring(1);*/
				//map.put("prenume", prenume);
				map.put("nume",PDFHelper.capitalizeWords(numeField.getValue().trim()));
				map.put("cnp", cnpField.getValue().trim());
				map.put("localitate", localitate.getValue());
				map.put("telefon", nrTelefon.getValue().trim());
				map.put("nrCasaAddr", nrStrField.getValue().trim());
				map.put("problema", issueField.getValue().trim());
				

			}
			//prenumeField.clear();
			numeField.clear();
			nrStrField.clear();
			cnpField.clear();
			nrStrField.clear();
			issueField.clear();
		
			CerereApaCreator pdfcr =  new CerereApaCreator(map,Utils.getTimeStr());
		 	String fn =  pdfcr.getID();
		    
			 RouteConfiguration.forSessionScope().removeRoute(CerereApaPDF.class);
		 	 RouteConfiguration.forSessionScope().setRoute(CerereApaPDF.NAME, CerereApaPDF.class);
		Map <String,String> sss =  new HashMap<String,String>();
		sss.put("tm", fn);
		
		UI.getCurrent().navigate("CerereApaPDF",QueryParameters.simple(sss));
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
		content.setAlignItems( Alignment.CENTER);
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
