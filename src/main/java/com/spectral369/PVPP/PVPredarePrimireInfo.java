package com.spectral369.PVPP;


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

    TextField numeField;
    TextField sumaField;
    TextField casierField;
    TextField foaieVarsamant1;
    TextField foaieVarsamant2;


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
		//complete.setEnabled(false);
		checkLayout.add(complete);
		content.add(checkLayout);
		content.setAlignItems( Alignment.CENTER);
		(infoLayout = new HorizontalLayout()).setVisible(false);
		
		infoPart2 = new VerticalLayout();
		numeField = new TextField("Subsemnatul Nume Complet:");
		numeField.setRequiredIndicatorVisible(true);
		binder.forField(numeField).asRequired()
				 .withValidator(str -> str.length() > 5, "Numele sa fie mai mare decat 5 caractere")
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
		numeField.setWidth("100%");
		numeField.setMaxLength(25);
		numeField.setMinWidth(20f, Unit.EM);//test
		infoPart2.add(numeField);
		infoLayout.add(infoPart2);
		infoLayout.setAlignItems( Alignment.CENTER);

		infoPart3 = new VerticalLayout();
		sumaField = new TextField("Suma Primita:");
		sumaField.setRequiredIndicatorVisible(true);
		sumaField.addValueChangeListener(e -> {
			sumaField.setValue(sumaField.getValue().replaceAll("[a-zA-Z]+", ""));
		});
		binder.forField(sumaField).asRequired()
				//.withValidator(str -> str.toString().length() == 13, "CNP-ul are 13 cifre")
				.withConverter(new StringToBigIntegerConverter("Suma poate contine doar cifre"))

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

	
		sumaField.setErrorMessage("Suma Primita Required !");
		infoPart3.add(sumaField);
		infoLayout.add(infoPart3);
		infoLayout.setAlignItems( Alignment.CENTER);
		
		infoPart31 = new VerticalLayout();
		
		casierField = new TextField("Casier Nume Complet:");
		casierField.setRequiredIndicatorVisible(true);
		binder.forField(casierField).asRequired()
				 .withValidator(str -> str.length() > 5, "Numele sa fie mai mare decat 5 caractere")
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

	
		casierField.setErrorMessage("Nume Required !");
		casierField.setWidth("100%");
		casierField.setMaxLength(25);
		casierField.setMinWidth(20f, Unit.EM);//test
		
		
		infoPart31.add(casierField);
		infoLayout.add(infoPart31);
		infoLayout.setAlignItems( Alignment.CENTER);
		
		
		/// info 2
		infoLayout2 = new HorizontalLayout();
		infoLayout2.setVisible(false);

		infoPart6 = new VerticalLayout();
		foaieVarsamant1 = new TextField("Foaie varsamant de la Nr.:");
		foaieVarsamant1.setRequiredIndicatorVisible(true);

		binder.forField(foaieVarsamant1).asRequired()
				.withConverter(new StringToBigIntegerConverter("Nr. foaie varsamant poate contine doar cifre"))

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

		
		foaieVarsamant1.setErrorMessage("Nr. Foaie Varsamant Required !");
		infoPart6.add(foaieVarsamant1);
		infoLayout2.add(infoPart6);
		infoLayout2.setAlignItems(Alignment.CENTER);

	

		infoPart5 = new VerticalLayout();
		
		foaieVarsamant2 = new TextField("Foaie varsamant de la Nr.:");
		foaieVarsamant2.setRequiredIndicatorVisible(true);

		binder.forField(foaieVarsamant2).asRequired()
				.withConverter(new StringToBigIntegerConverter("Nr. foaie varsamant poate contine doar cifre"))

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

		
		foaieVarsamant2.setErrorMessage("Nr. Foaie Varsamant Required !");
		infoPart5.add(foaieVarsamant2);
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
			
			if (complete.getValue()) {
			
				map.put("nume",PDFHelper.capitalizeWords(numeField.getValue().trim()));
				map.put("suma", PDFHelper.capitalizeWords(sumaField.getValue().trim()));
				map.put("casier", PDFHelper.capitalizeWords(casierField.getValue().trim()));
				map.put("foaie1", foaieVarsamant1.getValue().toString().trim());
				map.put("foaie2",foaieVarsamant2.getValue().toString().trim());
			

			}
			numeField.clear();
			sumaField.clear();
			casierField.clear();
			foaieVarsamant1.clear();
			foaieVarsamant2.clear();
			
			
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
