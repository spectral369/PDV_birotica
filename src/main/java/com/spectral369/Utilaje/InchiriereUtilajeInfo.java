package com.spectral369.Utilaje;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.converter.StringToBigIntegerConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Setter;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class InchiriereUtilajeInfo extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "InchiriereUtilajeInfo";
	VerticalLayout content;
	HorizontalLayout backLayout;
	Button backbtn;
	HorizontalLayout titlelLayout;
	Button IarbaTitle;
	HorizontalLayout checkLayout;
	CheckBox complete;
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
	DateField dateField;
	TextField nrStrField2;
	TextField tractorRemField;
	TextField ifronField;
	TextField tractorUtilajField;
	TextField minicastorField;

	HorizontalLayout generateLayout;
	Button generate;
	InchiriereUtilajePDF pdf;
	Map<String, String> map;
	private Binder<InchiriereUtilajeInfo> binder;

	public InchiriereUtilajeInfo() {
		content = new VerticalLayout();
		map = new HashMap<String, String>();
		binder = new Binder<InchiriereUtilajeInfo>(InchiriereUtilajeInfo.class);
		backLayout = new HorizontalLayout();
		backLayout.setMargin(true);
		backbtn = new Button("Back", VaadinIcons.ARROW_CIRCLE_LEFT);
		backbtn.addStyleName("quiet");
		backbtn.addClickListener(evt -> UI.getCurrent().getNavigator().navigateTo("Index"));
		backLayout.addComponent(backbtn);
		content.addComponent(backLayout);
		content.setComponentAlignment(backLayout, Alignment.MIDDLE_LEFT);
		titlelLayout = new HorizontalLayout();
		IarbaTitle = new Button("Inchiriere Utilaje", VaadinIcons.FILE_TEXT_O);
		IarbaTitle.setEnabled(false);
		IarbaTitle.setId("mainTitle");
		IarbaTitle.addStyleNames(new String[] { "borderless", "clearDisabled" });
		titlelLayout.addComponent(IarbaTitle);
		content.addComponent(titlelLayout);
		content.setComponentAlignment(titlelLayout, Alignment.MIDDLE_CENTER);
		checkLayout = new HorizontalLayout();
		(complete = new CheckBox("Cu completare de date ?!", false)).addValueChangeListener(evt -> {
			toggleVisibility();
			UI.getCurrent().push();
		});
		checkLayout.addComponent(complete);
		content.addComponent(checkLayout);
		content.setComponentAlignment(checkLayout, Alignment.MIDDLE_CENTER);
		(infoLayout = new HorizontalLayout()).setVisible(false);
		infoPart1 = new VerticalLayout();
		prenumeField = new TextField("Prenume:");
		prenumeField.setRequiredIndicatorVisible(true);
		/*
		 * prenumeField.addListener(e->{
		 * prenumeField.setValue(prenumeField.getValue().replaceAll("[0-9]+", "")); });
		 */
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
				}, new Setter<InchiriereUtilajeInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, String fieldvalue) {

					}
				});

		prenumeField.setResponsive(true);
		prenumeField.setComponentError((ErrorMessage) new UserError("Prenume Required !"));
		infoPart1.addComponent(prenumeField);
		infoLayout.addComponent(infoPart1);
		infoLayout.setComponentAlignment(infoPart1, Alignment.MIDDLE_CENTER);

		infoPart2 = new VerticalLayout();
		numeField = new TextField("Nume:");
		numeField.setRequiredIndicatorVisible(true);
		/*
		 * numeField.addListener(e->{
		 * numeField.setValue(numeField.getValue().replaceAll("[0-9]+", ""));
		 * 
		 * });
		 */
		binder.forField(numeField).asRequired()
				// .withValidator(str -> str.length() > 2, "Numele sa fie mai mare decat 2
				// caractere")
				.withValidator(str -> str.matches("^([a-zA-z-]{2,20})$"), "Doar caractere si mai mare de 2 unitati")
				.bind(new ValueProvider<InchiriereUtilajeInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(InchiriereUtilajeInfo source) {
						return null;
					}
				}, new Setter<InchiriereUtilajeInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, String fieldvalue) {

					}
				});

		numeField.setResponsive(true);
		numeField.setComponentError((ErrorMessage) new UserError("Nume Required !"));
		infoPart2.addComponent(numeField);
		infoLayout.addComponent(infoPart2);
		infoLayout.setComponentAlignment(infoPart2, Alignment.MIDDLE_CENTER);

		infoPart3 = new VerticalLayout();
		cnpField = new TextField("CNP:");
		cnpField.setRequiredIndicatorVisible(true);
		cnpField.addListener(e -> {
			cnpField.setValue(cnpField.getValue().replaceAll("[a-zA-Z]+", ""));
		});
		binder.forField(cnpField).asRequired()
				.withValidator(str -> str.toString().length() == 13, "CNP-ul are 13 cifre")
				.withConverter(new StringToBigIntegerConverter("CNP-ul poate contine doar cifre"))

				.bind(new ValueProvider<InchiriereUtilajeInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public BigInteger apply(InchiriereUtilajeInfo source) {
						return null;
					}
				}, new Setter<InchiriereUtilajeInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, BigInteger fieldvalue) {

					}
				});

		cnpField.setResponsive(true);
		cnpField.setComponentError((ErrorMessage) new UserError("CNP Required !"));
		infoPart3.addComponent(cnpField);
		infoLayout.addComponent(infoPart3);
		infoLayout.setComponentAlignment(infoPart3, Alignment.MIDDLE_CENTER);
		
		infoPart31 = new VerticalLayout();
		localitate = new ComboBox<String>("Localitate: ");
		localitate.setItems("Dudestii-Vechi", "Cheglevici","Colonia Bulgara");
		localitate.setSelectedItem("Dudestii-Vechi");
		localitate.setRequiredIndicatorVisible(true);
		binder.forField(localitate).asRequired()
				.bind(new ValueProvider<InchiriereUtilajeInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(InchiriereUtilajeInfo source) {
						return null;
					}
				}, new Setter<InchiriereUtilajeInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, String fieldvalue) {

					}
				});
		localitate.setResponsive(true);
		localitate.setComponentError((ErrorMessage) new UserError("Localitate Required !"));
		infoPart31.addComponent(localitate);
		infoLayout.addComponent(infoPart31);
		infoLayout.setComponentAlignment(infoPart31, Alignment.MIDDLE_CENTER);
		
		
		

		infoPart4 = new VerticalLayout();
		nrStrField = new TextField("Numar Casa:");
		nrStrField.setRequiredIndicatorVisible(true);
		binder.forField(nrStrField).asRequired()
				// .withValidator(str -> Character.isDigit(str.charAt(0)), "Primul caracter
				// trebuie sa fie o cifra")
				.withValidator(str -> str.matches("^(\\d){1,4}[/]{0,1}[a-zA-Z]{0,1}$"), "Numar de casa invalid !")
				.bind(new ValueProvider<InchiriereUtilajeInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(InchiriereUtilajeInfo source) {
						return null;
					}
				}, new Setter<InchiriereUtilajeInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, String fieldvalue) {

					}
				});

		nrStrField.setResponsive(true);
		nrStrField.setComponentError((ErrorMessage) new UserError("Model required !"));
		infoPart4.addComponent(nrStrField);
		infoLayout.addComponent(infoPart4);
		infoLayout.setComponentAlignment(infoPart4, Alignment.MIDDLE_CENTER);

		// nrtel ^([00|+]{0,1}[\d]{1,3}[0-9]{9,12})$

		infoPart11 = new VerticalLayout();
		nrTelefon = new TextField("Nr. Telefon:");
		nrTelefon.setRequiredIndicatorVisible(true);

		binder.forField(nrTelefon).asRequired()
				.withValidator(str -> str.matches("^([00]{0,1}[\\d]{1,3}[0-9]{9,12})$"), "Numar de telefon invalid !")
				.withConverter(new StringToBigIntegerConverter("Nr. telefon poate contine doar cifre"))

				.bind(new ValueProvider<InchiriereUtilajeInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public BigInteger apply(InchiriereUtilajeInfo source) {
						return null;
					}
				}, new Setter<InchiriereUtilajeInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, BigInteger fieldvalue) {

					}
				});

		nrTelefon.setResponsive(true);
		nrTelefon.setComponentError((ErrorMessage) new UserError("Telefon Required !"));
		infoPart11.addComponent(nrTelefon);
		infoLayout.addComponent(infoPart11);
		infoLayout.setComponentAlignment(infoPart11, Alignment.MIDDLE_CENTER);

		/// info 2
		infoLayout2 = new HorizontalLayout();
		infoLayout2.setVisible(false);

		infoPart5 = new VerticalLayout();
		dateField = new DateField("Data Lucrarii:");
		dateField.setDateFormat("dd-MM-yyyy");
		dateField.setRequiredIndicatorVisible(true);

		binder.forField(dateField).asRequired()
				.withValidator(returnDate -> !returnDate.isBefore(LocalDate.now()),
						"Data nu poate fi anterioara zilei de azi")

				.bind(new ValueProvider<InchiriereUtilajeInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public LocalDate apply(InchiriereUtilajeInfo source) {
						return null;
					}
				}, new Setter<InchiriereUtilajeInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, LocalDate fieldvalue) {

					}
				});

		dateField.setResponsive(true);
		dateField.setComponentError((ErrorMessage) new UserError("Date Required !"));
		infoPart5.addComponent(dateField);
		infoLayout2.addComponent(infoPart5);
		infoLayout2.setComponentAlignment(infoPart5, Alignment.MIDDLE_CENTER);

		infoPart6 = new VerticalLayout();
		nrStrField2 = new TextField("Numar Casa:");
		nrStrField2.setRequiredIndicatorVisible(true);
		binder.forField(nrStrField2).asRequired()
				// .withValidator(str -> Character.isDigit(str.charAt(0)), "Primul caracter
				// trebuie sa fie o cifra")
				.withValidator(str -> str.matches("^(\\d){1,4}[/]{0,1}[a-zA-Z]{0,1}$"), "Numar de casa invalid !")
				.bind(new ValueProvider<InchiriereUtilajeInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(InchiriereUtilajeInfo source) {
						return null;
					}
				}, new Setter<InchiriereUtilajeInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, String fieldvalue) {

					}
				});

		nrStrField2.setResponsive(true);
		nrStrField2.setComponentError((ErrorMessage) new UserError("Model required !"));
		infoPart6.addComponent(nrStrField2);
		infoLayout2.addComponent(infoPart6);
		infoLayout2.setComponentAlignment(infoPart6, Alignment.MIDDLE_CENTER);

		infoLayout3 = new HorizontalLayout();
		infoLayout3.setVisible(false);

		infoPart7 = new VerticalLayout();
		tractorRemField = new TextField("Ore Tractor+Remorca:");
		tractorRemField.setRequiredIndicatorVisible(true);
		tractorRemField.setPlaceholder("Ore Tractor + Remorca");
		tractorRemField.setValue("0");
		binder.forField(tractorRemField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
				.withValidator(str -> str >= 0, "Nr. nu poate fi negativ")
				.bind(new ValueProvider<InchiriereUtilajeInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(InchiriereUtilajeInfo source) {
						return null;
					}
				}, new Setter<InchiriereUtilajeInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, Integer fieldvalue) {
						tractorRemField.setValue("0");

					}
				});

		tractorRemField.setResponsive(true);
		// tractorRemField.setComponentError((ErrorMessage) new UserError("Ore Lucrate
		// Necesare !"));
		infoPart7.addComponent(tractorRemField);
		infoLayout3.addComponent(infoPart7);
		infoLayout3.setComponentAlignment(infoPart7, Alignment.MIDDLE_CENTER);

		infoPart8 = new VerticalLayout();
		ifronField = new TextField("Ore Ifron:");
		ifronField.setRequiredIndicatorVisible(true);
		ifronField.setPlaceholder("Ore Ifron");
		ifronField.setValue("0");
		binder.forField(ifronField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
				.withValidator(str -> str >= 0, "Nr. nu poate fi negativ")
				.bind(new ValueProvider<InchiriereUtilajeInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(InchiriereUtilajeInfo source) {
						return null;
					}
				}, new Setter<InchiriereUtilajeInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, Integer fieldvalue) {

					}
				});

		ifronField.setResponsive(true);
		// ifronField.setComponentError((ErrorMessage) new UserError("Ore Lucrate
		// Necesare !"));
		infoPart8.addComponent(ifronField);
		infoLayout3.addComponent(infoPart8);
		infoLayout3.setComponentAlignment(infoPart8, Alignment.MIDDLE_CENTER);

		infoPart9 = new VerticalLayout();
		tractorUtilajField = new TextField("Ore Tractor+Utilaj:");
		tractorUtilajField.setRequiredIndicatorVisible(true);
		tractorUtilajField.setPlaceholder("Ore Ifron");
		tractorUtilajField.setValue("0");
		binder.forField(tractorUtilajField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
				.withValidator(str -> str >= 0, "Nr. nu poate fi negativ")
				.bind(new ValueProvider<InchiriereUtilajeInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(InchiriereUtilajeInfo source) {
						return null;
					}
				}, new Setter<InchiriereUtilajeInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, Integer fieldvalue) {

					}
				});

		tractorUtilajField.setResponsive(true);
		// tractorUtilajField.setComponentError((ErrorMessage) new UserError("Ore
		// Lucrate Necesare !"));
		infoPart9.addComponent(tractorUtilajField);
		infoLayout3.addComponent(infoPart9);
		infoLayout3.setComponentAlignment(infoPart9, Alignment.MIDDLE_CENTER);

		infoPart10 = new VerticalLayout();
		minicastorField = new TextField("Ore Minicastor:");
		minicastorField.setRequiredIndicatorVisible(true);
		minicastorField.setPlaceholder("Ore Ifron");
		minicastorField.setValue("0");
		binder.forField(minicastorField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
				.withValidator(str -> str >= 0, "Nr. nu poate fi negativ")
				.bind(new ValueProvider<InchiriereUtilajeInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(InchiriereUtilajeInfo source) {
						return null;
					}
				}, new Setter<InchiriereUtilajeInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InchiriereUtilajeInfo bean, Integer fieldvalue) {

					}
				});

		minicastorField.setResponsive(true);
		// minicastorField.setComponentError((ErrorMessage) new UserError("Ore Lucrate
		// Necesare !"));
		infoPart10.addComponent(minicastorField);
		infoLayout3.addComponent(infoPart10);
		infoLayout3.setComponentAlignment(infoPart10, Alignment.MIDDLE_CENTER);

		content.addComponent(infoLayout);
		content.setComponentAlignment(infoLayout, Alignment.MIDDLE_CENTER);
		content.addComponent(infoLayout2);
		content.setComponentAlignment(infoLayout2, Alignment.MIDDLE_CENTER);
		content.addComponent(infoLayout3);
		content.setComponentAlignment(infoLayout3, Alignment.MIDDLE_CENTER);

		generateLayout = new HorizontalLayout();
		generate = new Button("Generate", VaadinIcons.FILE_PROCESS);
		generate.addStyleNames("primary", "friendly");
		generate.addClickListener(evt -> {
			// String output = input.substring(0, 1).toUpperCase() + input.substring(1);
			if (complete.getValue()) {
				String prenume = prenumeField.getValue().trim().substring(0, 1).toUpperCase()
						+ prenumeField.getValue().trim().substring(1);
				String nume = numeField.getValue().trim().substring(0, 1).toUpperCase()
						+ numeField.getValue().trim().substring(1);
				map.put("prenume", prenume);
				map.put("nume", nume);
				map.put("cnp", cnpField.getValue().trim());
				map.put("localitate", localitate.getSelectedItem().get());
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
			prenumeField.clear();
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
			pdf = new InchiriereUtilajePDF(map);
			UI.getCurrent().getNavigator().navigateTo("InchiriereUtilajePDF");
		});
		generateLayout.addComponent(generate);

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

		content.addComponent(generateLayout);
		content.setComponentAlignment(generateLayout, Alignment.MIDDLE_CENTER);
		content.setMargin(false);
		setCompositionRoot(content);
		UI.getCurrent().getNavigator().addView("InchiriereUtilajeInfo", this);
	}

	private void toggleVisibility() {
		infoLayout.setVisible(!infoLayout.isVisible());
		infoLayout2.setVisible(!infoLayout2.isVisible());
		infoLayout3.setVisible(!infoLayout3.isVisible());

		generate.setEnabled(!generate.isEnabled());
	}
}
