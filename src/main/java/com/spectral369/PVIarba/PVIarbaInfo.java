package com.spectral369.PVIarba;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.converter.StringToBigIntegerConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Setter;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PVIarbaInfo extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "PVIarba";
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
	VerticalLayout infoPart21;
	VerticalLayout infoPart22;
	HorizontalLayout infoLayout2;
	VerticalLayout infoPart3;
	HorizontalLayout infoLayout3;
	VerticalLayout infoPart4;
	VerticalLayout infoPart5;
	VerticalLayout infoPart6;
	VerticalLayout infoPart7;
	HorizontalLayout infoLayout4;
	VerticalLayout infoPart8;
	VerticalLayout infoPart9;
//infoPart1

	DateField dateField;
	//InlineDateTimeField timeField;
	final TextField whenField; 
	// infoPart2
	TextArea jobField;
	// infopart3
	TextField satField;
	TextField nrCasaField;
	TextField adresaLucrareField;
	TextField sumaTotalaField;
	// infoPart4
	TextField intocmitField;
	TextField sefServicuField;
	
	TextField numeField;
	TextField cnpField;

	HorizontalLayout generateLayout;
	Button generate;
	PVIarbaPDF pdf;
	Map<String, String> map;
	private Binder<PVIarbaInfo> binder;

	public PVIarbaInfo() {
		content = new VerticalLayout();
		map = new HashMap<String, String>();
		binder = new Binder<PVIarbaInfo>(PVIarbaInfo.class);
		backLayout = new HorizontalLayout();
		backLayout.setMargin(true);
		backbtn = new Button("Back", VaadinIcons.ARROW_CIRCLE_LEFT);
		backbtn.addStyleName("quiet");
		backbtn.addClickListener(evt -> UI.getCurrent().getNavigator().navigateTo("index"));
		backLayout.addComponent(backbtn);
		content.addComponent(backLayout);
		content.setComponentAlignment(backLayout, Alignment.MIDDLE_LEFT);
		titlelLayout = new HorizontalLayout();
		IarbaTitle = new Button("Proces Verbal - Iarba", VaadinIcons.FILE_TEXT_O);
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
		dateField = new DateField("Data:");
		dateField.setDateFormat("dd-MM-yyyy");
		dateField.setRequiredIndicatorVisible(true);
		binder.forField(dateField).asRequired()

				.bind(new ValueProvider<PVIarbaInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public LocalDate apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, LocalDate>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, LocalDate fieldvalue) {

					}
				});
		
		dateField.setResponsive(true);
		dateField.setComponentError((ErrorMessage) new UserError("Date Required !"));
		infoPart1.addComponent(dateField);
		infoLayout.addComponent(infoPart1);
		infoLayout.setComponentAlignment(infoPart1, Alignment.MIDDLE_CENTER);
		///test
	
	whenField= new TextField("Time:");
		whenField.setValue(LocalTime.now().format(DateTimeFormatter.ofLocalizedTime( FormatStyle.SHORT ).withLocale( Locale.US )));
		whenField.setValueChangeMode( ValueChangeMode.BLUR );
		whenField.addValueChangeListener( new HasValue.ValueChangeListener < String >( )
		{
		    static final long serialVersionUID = 201710132100L;

		    @Override
		    public void valueChange ( HasValue.ValueChangeEvent < String > valueChangeEvent )
		    {
		        String input = whenField.getValue( );
		   ///     System.out.println( "input: " + input );
		        try
		        {
		            // DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_TIME; // Constant ISO_LOCAL_TIME is for time-of-day in standard ISO 8601 format.
		            // DateTimeFormatter f = DateTimeFormatter.ofLocalizedTime( FormatStyle.SHORT ).withLocale( Locale.CANADA_FRENCH ); // Automatically localize.
		            DateTimeFormatter f = DateTimeFormatter.ofLocalizedTime( FormatStyle.SHORT ).withLocale( Locale.US ); // Automatically localize.
		            LocalTime localTime = LocalTime.parse( input , f );
		            //String timeIso8601 = localTime.toString( );
		            whenField.setPlaceholder(localTime.toString());
		        
		            
		        } catch ( DateTimeParseException e )
		        {
		            System.out.println( "PVIarbaInfo ERROR - failed to parse input: " + input );
		        }
		    }
		} );
	
		infoPart2 = new VerticalLayout();
		
		infoPart2.addComponents(whenField); 
		infoLayout.addComponent(infoPart2);
		infoLayout.setComponentAlignment(infoPart2, Alignment.MIDDLE_CENTER);
		
		infoPart21 = new VerticalLayout();
		numeField = new TextField("Nume:");
		numeField.setPlaceholder("Nume Complet");
		numeField.setRequiredIndicatorVisible(true);
		binder.forField(numeField).asRequired()
				

		.bind(new ValueProvider<PVIarbaInfo, String>() {

			private static final long serialVersionUID = 1L;

			@Override
			public String apply(PVIarbaInfo source) {
				return null;
			}
		}, new Setter<PVIarbaInfo, String>() {

			private static final long serialVersionUID = 1L;

			@Override
			public void accept(PVIarbaInfo bean, String fieldvalue) {

			}
		});
		numeField.setResponsive(true);
		numeField.setComponentError((ErrorMessage) new UserError("Full name Required !"));
		infoPart21.addComponent(numeField);
		infoLayout.addComponent(infoPart21);
		infoLayout.setComponentAlignment(infoPart21, Alignment.MIDDLE_CENTER);
		//
		infoPart22 = new VerticalLayout();
		cnpField = new TextField("CNP:");
		cnpField.setRequiredIndicatorVisible(true);
		binder.forField(cnpField).asRequired()
				.withValidator(str -> str.toString().length() == 13, "CNP-ul are 13 cifre")
				.withConverter(new StringToBigIntegerConverter("CNP-ul poate contine doar cifre"))
			
				.bind(new ValueProvider<PVIarbaInfo, BigInteger>() {

					private static final long serialVersionUID = 1L;

					@Override
					public BigInteger apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, BigInteger>() {

				
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, BigInteger fieldvalue) {

					}
				});
		
		cnpField.setResponsive(true);
		cnpField.setComponentError((ErrorMessage) new UserError("CNP Required !"));
		infoPart22.addComponent(cnpField);
		infoLayout.addComponent(infoPart22);
		infoLayout.setComponentAlignment(infoPart22, Alignment.MIDDLE_CENTER);
		
		
		
		
		
		///
		/*
		 * infoPart2 = new VerticalLayout(); timeField = new
		 * InlineDateTimeField("Time:"); timeField.setLocale(Locale.getDefault());
		 * timeField.setResolution(DateTimeResolution.MINUTE);
		 * timeField.setRequiredIndicatorVisible(true);
		 * timeField.addStyleName("time-only");
		 * 
		 * infoPart2.addComponent(timeField); infoLayout.addComponent(infoPart2);
		 * infoLayout.setComponentAlignment(infoPart2, Alignment.MIDDLE_CENTER);
		 */
		///

		infoLayout2 = new HorizontalLayout();
		infoLayout2.setWidth("45%");
		infoLayout2.setVisible(false);
		infoPart3 = new VerticalLayout();
		jobField = new TextArea("Lucrari: ");  
		jobField.setRequiredIndicatorVisible(true);
		jobField.setWordWrap(false);
		jobField.setWidth("100%");

		
		//jobField.addStyleName("resizable");
		// jobField.setWordWrap(true); //auto new line
		binder.forField(jobField).asRequired()

				.bind(new ValueProvider<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, String fieldvalue) {

					}
				});

		jobField.setResponsive(true);
		jobField.setComponentError((ErrorMessage) new UserError("NR.casa Required !"));
		infoPart3.addComponent(jobField);
		infoLayout2.addComponent(infoPart3);
		infoLayout2.setComponentAlignment(infoPart3, Alignment.MIDDLE_CENTER);

		infoLayout3 = new HorizontalLayout();
		infoLayout3.setVisible(false);
		infoPart4 = new VerticalLayout();
		satField = new TextField("Localitate:");
		satField.setValue("Dudestii-Vechi");
		satField.setRequiredIndicatorVisible(true);
		binder.forField(satField).asRequired()
				.withValidator(str -> str.length() > 2, "Numele localitatii sa fie mai mare decat 2 caractere")

				.bind(new ValueProvider<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, String fieldvalue) {

					}
				});

		satField.setResponsive(true);
		//satField.setComponentError((ErrorMessage) new UserError("Localitate required !"));
		infoPart4.addComponent(satField);
		infoLayout3.addComponent(infoPart4);
		infoLayout3.setComponentAlignment(infoPart4, Alignment.MIDDLE_CENTER);

		infoPart5 = new VerticalLayout();
		nrCasaField = new TextField("Numar Casa:");
		nrCasaField.setRequiredIndicatorVisible(true);
		binder.forField(nrCasaField).asRequired()

				.bind(new ValueProvider<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, String fieldvalue) {

					}
				});

		nrCasaField.setResponsive(true);
		nrCasaField.setComponentError((ErrorMessage) new UserError("Nr Casa required !"));
		infoPart5.addComponent(nrCasaField);
		infoLayout3.addComponent(infoPart5);
		infoLayout3.setComponentAlignment(infoPart5, Alignment.MIDDLE_CENTER);

		infoPart6 = new VerticalLayout();
		adresaLucrareField = new TextField("Adresa lucrare:");
		adresaLucrareField.setRequiredIndicatorVisible(true);
		binder.forField(adresaLucrareField).asRequired()
				.withValidator(str -> str.length() > 2, "Adresa lucrare trebuie sa fie mai mare decat 2 caractere")
				.bind(new ValueProvider<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, String fieldvalue) {

					}
				});

		adresaLucrareField.setResponsive(true);
		adresaLucrareField.setComponentError((ErrorMessage) new UserError("Adresa lucrare required !"));
		infoPart6.addComponent(adresaLucrareField);
		infoLayout3.addComponent(infoPart6);
		infoLayout3.setComponentAlignment(infoPart6, Alignment.MIDDLE_CENTER);

		infoPart7 = new VerticalLayout();
		sumaTotalaField = new TextField("Suma totala:");
		sumaTotalaField.setRequiredIndicatorVisible(true);
		binder.forField(sumaTotalaField).asRequired().withConverter(new StringToIntegerConverter("Must be Integer"))
				.bind(new ValueProvider<PVIarbaInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Integer apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, Integer>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, Integer fieldvalue) {

					}
				});

		sumaTotalaField.setResponsive(true);
		sumaTotalaField.setComponentError((ErrorMessage) new UserError("Suma totala required !"));
		infoPart7.addComponent(sumaTotalaField);
		infoLayout3.addComponent(infoPart7);
		infoLayout3.setComponentAlignment(infoPart7, Alignment.MIDDLE_CENTER);

		infoLayout4 = new HorizontalLayout();
		infoLayout4.setVisible(false);
		infoPart8 = new VerticalLayout();
		sefServicuField = new TextField("Sef Serviciu:");
		sefServicuField.setRequiredIndicatorVisible(true);
		binder.forField(sefServicuField).asRequired()
				.withValidator(str -> str.length() > 2, "Nume Sef Serviciu trebuie sa fie mai mare decat 2 caractere")
				.bind(new ValueProvider<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, String fieldvalue) {

					}
				});

		sefServicuField.setResponsive(true);
		sefServicuField.setComponentError((ErrorMessage) new UserError("Nume Sef Serviciu required !"));
		infoPart8.addComponent(sefServicuField);
		infoLayout4.addComponent(infoPart8);
		infoLayout4.setComponentAlignment(infoPart8, Alignment.MIDDLE_CENTER);

		infoPart9 = new VerticalLayout();
		intocmitField = new TextField("Intocmit:");
		intocmitField.setRequiredIndicatorVisible(true);
		binder.forField(intocmitField).asRequired()
				.withValidator(str -> str.length() > 2, "Trebuie sa fie mai mare decat 2 caractere")
				.bind(new ValueProvider<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(PVIarbaInfo source) {
						return null;
					}
				}, new Setter<PVIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(PVIarbaInfo bean, String fieldvalue) {

					}
				});

		intocmitField.setResponsive(true);
		intocmitField.setComponentError((ErrorMessage) new UserError("IntocmitField required !"));
		infoPart9.addComponent(intocmitField);
		infoLayout4.addComponent(infoPart9);
		infoLayout4.setComponentAlignment(infoPart9, Alignment.MIDDLE_CENTER);

		content.addComponent(infoLayout);
		content.setComponentAlignment(infoLayout, Alignment.MIDDLE_CENTER);
		content.addComponent(infoLayout2);
		content.setComponentAlignment(infoLayout2, Alignment.MIDDLE_CENTER);
		content.addComponent(infoLayout3);
		content.setComponentAlignment(infoLayout3, Alignment.MIDDLE_CENTER);
		content.addComponent(infoLayout4);
		content.setComponentAlignment(infoLayout4, Alignment.MIDDLE_CENTER);

		generateLayout = new HorizontalLayout();
		generate = new Button("Generate", VaadinIcons.FILE_PROCESS);
		generate.addStyleNames("primary", "friendly");
		generate.addClickListener(evt -> {
			// String output = input.substring(0, 1).toUpperCase() + input.substring(1);
			if (complete.getValue()) {
				LocalDate dt = dateField.getValue();
				dt.format(DateTimeFormatter.ISO_LOCAL_DATE);
				map.put("ziua", String.valueOf(dt.getDayOfMonth()));
				map.put("luna", String.valueOf(dt.getMonthValue()));
				map.put("anul", String.valueOf(dt.getYear()));
			//	LocalDate time = hmField.getValue();
			//	time.format(DateTimeFormatter.ISO_LOCAL_TIME);
				map.put("time", whenField.getValue().toString());
				map.put("job", jobField.getValue());
				map.put("localitate", satField.getValue());
				map.put("nrCasa", nrCasaField.getValue());
				map.put("adresa", adresaLucrareField.getValue());
				map.put("suma", sumaTotalaField.getValue());
				map.put("intocmit", intocmitField.getValue());
				map.put("sef", sefServicuField.getValue());
				map.put("contribuabil", numeField.getValue());
				map.put("cnp", cnpField.getValue());

			}
			dateField.clear();
		
			whenField.clear();
			jobField.clear();
			satField.clear();
			nrCasaField.clear();
			adresaLucrareField.clear();
			sumaTotalaField.clear();
			intocmitField.clear();
			sefServicuField.clear();
			numeField.clear();
			cnpField.clear();

			pdf = new PVIarbaPDF(map);
			UI.getCurrent().getNavigator().navigateTo("PVIarbaPDF");
		});
		generateLayout.addComponent(generate);
		binder.addStatusChangeListener(event -> {
			System.out.println(binder.isValid());
			if (!complete.getValue() || binder.isValid()) {
				generate.setEnabled(true);
			} else {
				generate.setEnabled(false);
			}
		});
		content.addComponent(generateLayout);
		content.setComponentAlignment(generateLayout, Alignment.MIDDLE_CENTER);
		content.setMargin(false);
		setCompositionRoot(content);
		UI.getCurrent().getNavigator().addView("PVIarba", this);
	}

	private void toggleVisibility() {
		infoLayout.setVisible(!infoLayout.isVisible());
		infoLayout2.setVisible(!infoLayout2.isVisible());
		infoLayout3.setVisible(!infoLayout3.isVisible());
		infoLayout4.setVisible(!infoLayout4.isVisible());

		generate.setEnabled(!generate.isEnabled());
	}
}
