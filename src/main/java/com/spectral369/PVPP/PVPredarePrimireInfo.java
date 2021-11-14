package com.spectral369.PVPP;


import java.math.BigInteger;
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
import com.vaadin.flow.component.combobox.ComboBox;
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

public class PVPredarePrimireInfo extends HorizontalLayout implements RouterLayout, AfterNavigationObserver {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "PVPredarePrimireInfo";
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
    VerticalLayout infoPart6;
    VerticalLayout infoPart7;
    VerticalLayout infoPart8;
    HorizontalLayout infoLayout3;
    VerticalLayout infoPart9;
    VerticalLayout infoPart10;
    VerticalLayout infoPart11;

    TextField prenumeField;
    TextField numeField;
    ComboBox<String> localitate;
    TextField cnpField;
    TextField nrStrField;
    TextField nrTelefon;
    DatePicker dateField;
    TextField nrStrField2;
    TextField tractorRemField;
    TextField ifronField;
    TextField tractorUtilajField;
    TextField minicastorField;

    HorizontalLayout generateLayout;
    Button generate;
    PVPredarePrimirePDF pdf;
    Map<String, String> map;
    private Binder<PVPredarePrimireInfo> binder;

    public PVPredarePrimireInfo() {
		content = new VerticalLayout();
		map = new HashMap<String, String>();
		binder = new Binder<PVPredarePrimireInfo>(PVPredarePrimireInfo.class);
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
		IarbaTitle = new Button("PV Predare Primire", VaadinIcon.FILE_TEXT_O.create());
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
		content.setAlignItems( Alignment.CENTER);
		(infoLayout = new HorizontalLayout()).setVisible(false);
		/*infoPart1 = new VerticalLayout();
		prenumeField = new TextField("Prenume:");
		prenumeField.setRequiredIndicatorVisible(true);
	
		binder.forField(prenumeField).asRequired()
				// .withValidator(str -> str.length() > 2, "Prenumele sa fie mai mare decat 2
				// caractere")
				.withValidator(str -> str.matches("^([a-zA-z-]{2,20})$"), "Doar caractere si mai mare de 2 unitati")

				.bind(new ValueProvider<PVPredarePrimireInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVPredarePrimireInfo source) {
						return null;
					}
				},  new Setter<PVPredarePrimireInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVPredarePrimireInfo bean, String fieldvalue) {

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
				.bind(new ValueProvider<PVPredarePrimireInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVPredarePrimireInfo source) {
						return null;
					}
				}, new Setter<PVPredarePrimireInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVPredarePrimireInfo bean, String fieldvalue) {

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
		binder.forField(cnpField).asRequired()
				.withValidator(str -> str.toString().length() == 13, "CNP-ul are 13 cifre")
				.withConverter(new StringToBigIntegerConverter("CNP-ul poate contine doar cifre"))

				.bind(new ValueProvider<PVPredarePrimireInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public BigInteger apply(PVPredarePrimireInfo source) {
						return null;
					}
				}, new Setter<PVPredarePrimireInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVPredarePrimireInfo bean, BigInteger fieldvalue) {

					}
				});

	
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
				.bind(new ValueProvider<PVPredarePrimireInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVPredarePrimireInfo source) {
						return null;
					}
				}, new Setter<PVPredarePrimireInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVPredarePrimireInfo bean, String fieldvalue) {

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
				.bind(new ValueProvider<PVPredarePrimireInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVPredarePrimireInfo source) {
						return null;
					}
				}, new Setter<PVPredarePrimireInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVPredarePrimireInfo bean, String fieldvalue) {

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

		binder.forField(nrTelefon).asRequired()
				.withValidator(str -> str.matches("^([00]{0,1}[\\d]{1,3}[0-9]{9,12})$"), "Numar de telefon invalid !")
				.withConverter(new StringToBigIntegerConverter("Nr. telefon poate contine doar cifre"))

				.bind(new ValueProvider<PVPredarePrimireInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public BigInteger apply(PVPredarePrimireInfo source) {
						return null;
					}
				}, new Setter<PVPredarePrimireInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVPredarePrimireInfo bean, BigInteger fieldvalue) {

					}
				});

		
		nrTelefon.setErrorMessage("Telefon Required !");
		infoPart11.add(nrTelefon);
		infoLayout.add(infoPart11);
		infoLayout.setAlignItems(Alignment.CENTER);

		/// info 2
		infoLayout2 = new HorizontalLayout();
		infoLayout2.setVisible(false);

		infoPart5 = new VerticalLayout();
		dateField = new DatePicker("Data Lucrarii:");
		//dateField.setDateFormat("dd-MM-yyyy");
		dateField.setLocale(Locale.getDefault());
		dateField.setRequiredIndicatorVisible(true);

		binder.forField(dateField).asRequired()
				.withValidator(returnDate -> !returnDate.isBefore(LocalDate.now()),
						"Data nu poate fi anterioara zilei de azi")

				.bind(new ValueProvider<PVPredarePrimireInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public LocalDate apply(PVPredarePrimireInfo source) {
						return null;
					}
				}, new Setter<PVPredarePrimireInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVPredarePrimireInfo bean, LocalDate fieldvalue) {

					}
				});

	
		dateField.setErrorMessage("Date Required !");
		infoPart5.add(dateField);
		infoLayout2.add(infoPart5);
		infoLayout2.setAlignItems( Alignment.CENTER);

		infoPart6 = new VerticalLayout();
		nrStrField2 = new TextField("Numar Casa:");
		nrStrField2.setRequiredIndicatorVisible(true);
		binder.forField(nrStrField2).asRequired()
				// .withValidator(str -> Character.isDigit(str.charAt(0)), "Primul caracter
				// trebuie sa fie o cifra")
				.withValidator(str -> str.matches("^(\\d){1,4}[/]{0,1}[a-zA-Z]{0,1}$"), "Numar de casa invalid !")
				.bind(new ValueProvider<PVPredarePrimireInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVPredarePrimireInfo source) {
						return null;
					}
				}, new Setter<PVPredarePrimireInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVPredarePrimireInfo bean, String fieldvalue) {

					}
				});

	
		nrStrField2.setErrorMessage("Nr. casa required !");
		infoPart6.add(nrStrField2);
		infoLayout2.add(infoPart6);
		infoLayout2.setAlignItems( Alignment.CENTER);

		infoLayout3 = new HorizontalLayout();
		infoLayout3.setVisible(false);

		infoPart7 = new VerticalLayout();
		tractorRemField = new TextField("Ore Tractor+Remorca:");
		tractorRemField.setRequiredIndicatorVisible(true);
		tractorRemField.setPlaceholder("Ore Tractor + Remorca");
		tractorRemField.setValue("0");
		binder.forField(tractorRemField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
				.withValidator(str -> str >= 0, "Nr. nu poate fi negativ")
				.bind(new ValueProvider<PVPredarePrimireInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(PVPredarePrimireInfo source) {
						return null;
					}
				}, new Setter<PVPredarePrimireInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVPredarePrimireInfo bean, Integer fieldvalue) {
						tractorRemField.setValue("0");

					}
				});

		
		// tractorRemField.setErrorMessage("Ore Lucrate
		// Necesare !"));
		infoPart7.add(tractorRemField);
		infoLayout3.add(infoPart7);
		infoLayout3.setAlignItems( Alignment.CENTER);

		infoPart8 = new VerticalLayout();
		ifronField = new TextField("Ore Ifron:");
		ifronField.setRequiredIndicatorVisible(true);
		ifronField.setPlaceholder("Ore Ifron");
		ifronField.setValue("0");
		binder.forField(ifronField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
				.withValidator(str -> str >= 0, "Nr. nu poate fi negativ")
				.bind(new ValueProvider<PVPredarePrimireInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(PVPredarePrimireInfo source) {
						return null;
					}
				}, new Setter<PVPredarePrimireInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVPredarePrimireInfo bean, Integer fieldvalue) {

					}
				});

		
		// ifronField.setErrorMessage("Ore Lucrate
		// Necesare !"));
		infoPart8.add(ifronField);
		infoLayout3.add(infoPart8);
		infoLayout3.setAlignItems( Alignment.CENTER);

		infoPart9 = new VerticalLayout();
		tractorUtilajField = new TextField("Ore Tractor+Utilaj:");
		tractorUtilajField.setRequiredIndicatorVisible(true);
		tractorUtilajField.setPlaceholder("Ore Ifron");
		tractorUtilajField.setValue("0");
		binder.forField(tractorUtilajField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
				.withValidator(str -> str >= 0, "Nr. nu poate fi negativ")
				.bind(new ValueProvider<PVPredarePrimireInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(PVPredarePrimireInfo source) {
						return null;
					}
				}, new Setter<PVPredarePrimireInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVPredarePrimireInfo bean, Integer fieldvalue) {

					}
				});

		
		// tractorUtilajField.setErrorMessage("Ore
		// Lucrate Necesare !"));
		infoPart9.add(tractorUtilajField);
		infoLayout3.add(infoPart9);
		infoLayout3.setAlignItems(Alignment.CENTER);

		infoPart10 = new VerticalLayout();
		minicastorField = new TextField("Ore Minicastor:");
		minicastorField.setRequiredIndicatorVisible(true);
		minicastorField.setPlaceholder("Ore Ifron");
		minicastorField.setValue("0");
		binder.forField(minicastorField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
				.withValidator(str -> str >= 0, "Nr. nu poate fi negativ")
				.bind(new ValueProvider<PVPredarePrimireInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(PVPredarePrimireInfo source) {
						return null;
					}
				}, new Setter<PVPredarePrimireInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVPredarePrimireInfo bean, Integer fieldvalue) {

					}
				});

		
		// minicastorField.setErrorMessage("Ore Lucrate
		// Necesare !"));
		infoPart10.add(minicastorField);
		infoLayout3.add(infoPart10);
		infoLayout3.setAlignItems( Alignment.CENTER);

		content.add(infoLayout);
		content.setAlignItems( Alignment.CENTER);
		content.add(infoLayout2);
		content.setAlignItems( Alignment.CENTER);
		content.add(infoLayout3);
		content.setAlignItems(Alignment.CENTER);

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
				map.put("nrCasaAddr", nrStrField.getValue().trim());
				map.put("telefon", nrTelefon.getValue().trim());
				LocalDate dt = dateField.getValue();
				dt.format(DateTimeFormatter.ISO_LOCAL_DATE);
				map.put("ziuaLuc", String.valueOf(dt.getDayOfMonth()));
				map.put("lunaLuc", String.valueOf(dt.getMonthValue()));
				map.put("anulLuc", String.valueOf(dt.getYear()));
				map.put("nrCasaAddrLuc", nrStrField2.getValue().trim());
				map.put("tractorRemorca", String.valueOf(tractorRemField.getValue()));
				map.put("ifron", String.valueOf(ifronField.getValue()));
				map.put("tractorUtilaj", String.valueOf(tractorUtilajField.getValue()));
				map.put("minicastor", String.valueOf(minicastorField.getValue()));
				int oreTracRem = Integer.parseInt(tractorRemField.getValue());
				int oreIfron = Integer.parseInt(ifronField.getValue());
				int oreTracUtilaj = Integer.parseInt(tractorUtilajField.getValue());
				int oreMinicastor = Integer.parseInt(minicastorField.getValue());

				int totalOre = Integer.parseInt(tractorRemField.getValue()) + Integer.parseInt(ifronField.getValue())
						+ Integer.parseInt(tractorUtilajField.getValue())
						+ Integer.parseInt(minicastorField.getValue());
				map.put("totalOre", String.valueOf(totalOre));

				int sumaTotal = (oreTracRem * 80) + (oreIfron * 80) + (oreTracUtilaj * 80) + (oreMinicastor * 70);
				map.put("sumaTotal", String.valueOf(sumaTotal));

			}
			//prenumeField.clear();
			numeField.clear();
			nrStrField.clear();
			cnpField.clear();
			nrStrField.clear();
			dateField.clear();
			nrStrField2.clear();
			tractorRemField.clear();
			ifronField.clear();
			tractorUtilajField.clear();
			minicastorField.clear();
			PVPredarePrimireCreator pdfcr =  new PVPredarePrimireCreator(map,Utils.getTimeStr());
		 	String fn =  pdfcr.getID();
		    
			 RouteConfiguration.forSessionScope().removeRoute(PVPredarePrimirePDF.class);
		 	 RouteConfiguration.forSessionScope().setRoute(PVPredarePrimirePDF.NAME, PVPredarePrimirePDF.class);
		Map <String,String> sss =  new HashMap<String,String>();
		sss.put("tm", fn);
		
		UI.getCurrent().navigate("PVPredarePrimirePDF",QueryParameters.simple(sss));
		});
		generateLayout.add(generate);

		binder.addStatusChangeListener(event -> {

			if (!complete.getValue() || (binder.isValid())) {
				if (complete.getValue().equals(Boolean.TRUE)) {

					if (!tractorRemField.isEmpty() && !ifronField.isEmpty() && !tractorUtilajField.isEmpty()
							&& !minicastorField.isEmpty()) {

						int totalOre = Integer.parseInt(tractorRemField.getValue())
								+ Integer.parseInt(ifronField.getValue())
								+ Integer.parseInt(tractorUtilajField.getValue())
								+ Integer.parseInt(minicastorField.getValue());
						if (totalOre > 0) {
							generate.setEnabled(true);

						} else
							generate.setEnabled(false);
					}

				} else
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
	infoLayout3.setVisible(!infoLayout3.isVisible());

	generate.setEnabled(!generate.isEnabled());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
	RouteConfiguration.forSessionScope().removeRoute(NAME);
    }
}
