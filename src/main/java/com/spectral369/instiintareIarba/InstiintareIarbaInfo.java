package com.spectral369.instiintareIarba;

import java.util.HashMap;
import java.util.Map;

import com.spectral369.birotica.MainView;
import com.spectral369.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;



public class InstiintareIarbaInfo extends HorizontalLayout implements RouterLayout, AfterNavigationObserver  {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "InstiitareIarba";
	VerticalLayout content;
	HorizontalLayout backLayout;
	Button backbtn;
	HorizontalLayout titlelLayout;
	Button IarbaTitle;
	HorizontalLayout checkLayout;
	Checkbox complete;
	Checkbox catreHide;
	Checkbox sumaHide;
	Checkbox hideHCL;
	HorizontalLayout infoLayout;
	VerticalLayout infoPart1;
	VerticalLayout infoPart2;
	VerticalLayout infoPart3;
	VerticalLayout infoPart4;

	TextField prenumeField;
	TextField numeField;
	TextField localitateField;
	TextField nrStrField;

	HorizontalLayout generateLayout;
	Button generate;
	InstiitareIarbaPDF pdf;
	Map<String, String> map;
	private Binder<InstiintareIarbaInfo> binder;

	public InstiintareIarbaInfo() {
		content = new VerticalLayout();
		map = new HashMap<String, String>();
		binder = new Binder<InstiintareIarbaInfo>(InstiintareIarbaInfo.class);
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
		content.setAlignItems( Alignment.START);
		titlelLayout = new HorizontalLayout();
		IarbaTitle = new Button("Instiintare Iarba", VaadinIcon.FILE_TEXT_O.create());
		IarbaTitle.setEnabled(false);
		IarbaTitle.setId("mainTitle");
		IarbaTitle.addClassName("clearDisabled");
		titlelLayout.add(IarbaTitle);
		content.add(titlelLayout);
		content.setAlignItems( Alignment.CENTER);
		checkLayout = new HorizontalLayout();
		complete = new Checkbox("Cu completare de date ?!", false);
		complete.addValueChangeListener(evt -> {
			toggleVisibility();
			UI.getCurrent().push();
		});
		checkLayout.add(complete);
		content.add(checkLayout);
		content.setAlignItems( Alignment.CENTER);
		catreHide =  new Checkbox("Afisare 'catre'?!", true);
		catreHide.addValueChangeListener(evt -> {
			toggleNameVisibility();
			UI.getCurrent().push();
		});
		checkLayout.add(catreHide);
		content.add(catreHide);
		content.setAlignItems( Alignment.CENTER);
		
		sumaHide =  new Checkbox("Afisare 'sume'?!", false);
		sumaHide.addValueChangeListener(evt -> {
			
			UI.getCurrent().push();
		});
		checkLayout.add(sumaHide);
		content.add(sumaHide);
		content.setAlignItems(Alignment.CENTER);
		
		hideHCL =  new Checkbox("Afisare 'HCL'?!", false);
		hideHCL .addValueChangeListener(evt -> {
			
			UI.getCurrent().push();
		});
		checkLayout.add(hideHCL );
		content.add(hideHCL);
		content.setAlignItems(Alignment.CENTER);
		
		
		
		
		
		
		(infoLayout = new HorizontalLayout()).setVisible(false);
		infoPart1 = new VerticalLayout();
		prenumeField = new TextField("Prenume:");
		prenumeField.setVisible(false);
		prenumeField.setRequiredIndicatorVisible(true);
	/*	binder.forField(prenumeField).asRequired()
		.withValidator(str -> str.length() >2, "Prenumele sa fie mai mare decat 2 caractere")

				.bind(new ValueProvider<InstiintareIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(InstiintareIarbaInfo source) {
						return null;
					}
				}, new Setter<InstiintareIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InstiintareIarbaInfo bean, String fieldvalue) {

					}
				});*/

		
		prenumeField.setErrorMessage("Prenume Required !");
		infoPart1.add(prenumeField);
		infoLayout.add(infoPart1);
		infoLayout.setAlignItems( Alignment.CENTER);
		infoPart2 = new VerticalLayout();
		numeField = new TextField("Nume:");
		numeField.setVisible(false);
		numeField.setRequiredIndicatorVisible(true);
	/*	binder.forField(numeField).asRequired()
		.withValidator(str -> str.length() >2, "Numele sa fie mai mare decat 2 caractere")
				.bind(new ValueProvider<InstiintareIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(InstiintareIarbaInfo source) {
						return null;
					}
				}, new Setter<InstiintareIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InstiintareIarbaInfo bean, String fieldvalue) {

					}
				});
*/
		
		numeField.setErrorMessage("Nume Required !");
		infoPart2.add(numeField);
		infoLayout.add(infoPart2);
		infoLayout.setAlignItems( Alignment.CENTER);
		infoPart3 = new VerticalLayout();
		nrStrField = new TextField("Numar:");
		nrStrField.setRequiredIndicatorVisible(true);
		binder.forField(nrStrField).asRequired()

		.bind(new ValueProvider<InstiintareIarbaInfo, String>() {

			private static final long serialVersionUID = 1L;

			@Override
			public String apply(InstiintareIarbaInfo source) {
				return null;
			}
		},new Setter<InstiintareIarbaInfo, String>() {

			private static final long serialVersionUID = 1L;

			@Override
			public void accept(InstiintareIarbaInfo bean, String fieldvalue) {

			}
		});


	
		nrStrField.setErrorMessage("NR.casa Required !");
		infoPart3.add(nrStrField);
		infoLayout.add(infoPart3);
		infoLayout.setAlignItems(Alignment.CENTER);

		infoPart4 = new VerticalLayout();
		localitateField = new TextField("Localitate:");
		localitateField.setValue("Dudestii-Vechi");
		localitateField.setRequiredIndicatorVisible(true);
		binder.forField(localitateField).asRequired()
		.withValidator(str -> str.length() >2, "Numele localitatii sa fie mai mare decat 2 caractere")

				.bind(new ValueProvider<InstiintareIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(InstiintareIarbaInfo source) {
						return null;
					}
				}, new Setter<InstiintareIarbaInfo, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(InstiintareIarbaInfo bean, String fieldvalue) {

					}
				});

		
		localitateField.setErrorMessage("Localitate required !");
		infoPart4.add(localitateField);
		infoLayout.add(infoPart4);
		infoLayout.setAlignItems( Alignment.CENTER);

		content.add(infoLayout);
		content.setAlignItems(Alignment.CENTER);

		generateLayout = new HorizontalLayout();
		generate = new Button("Generate", VaadinIcon.FILE_PROCESS.create());
		generate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		generate.getClassNames().add("frendly");
		generate.addClickListener(evt -> {
			if (complete.getValue().equals(Boolean.TRUE)) {
				if(catreHide.getValue().equals(Boolean.TRUE)) {
				String prenume = prenumeField.getValue().trim().substring(0,1).toUpperCase()+prenumeField.getValue().trim().substring(1);
				String nume = numeField.getValue().trim().substring(0,1).toUpperCase()+numeField.getValue().trim().substring(1);
			
				map.put("prenume", prenume);
				map.put("nume", nume);
				}
				String localitate = localitateField.getValue().trim().substring(0,1).toUpperCase()+localitateField.getValue().trim().substring(1);
				map.put("nrStrada", nrStrField.getValue().trim());
				map.put("localitate", localitate);
			}
			prenumeField.clear();
			numeField.clear();
			nrStrField.clear();
			
			PDFIICreator pdfcr =  new PDFIICreator(map,Utils.getTimeStr(),catreHide.getValue(),sumaHide.getValue(),hideHCL.getValue());
		 	String fn =  pdfcr.getID();
		    
			 RouteConfiguration.forSessionScope().removeRoute(InstiitareIarbaPDF.class);
		 	 RouteConfiguration.forSessionScope().setRoute(InstiitareIarbaPDF.NAME, InstiitareIarbaPDF.class);
		Map <String,String> sss =  new HashMap<String,String>();
		sss.put("tm", fn);
		
		UI.getCurrent().navigate("InstiitareIarbaPDF",QueryParameters.simple(sss));
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
		prenumeField.setVisible(catreHide.getValue());
		numeField.setVisible(catreHide.getValue());
		generate.setEnabled(!generate.isEnabled());
	}
	
	private void toggleNameVisibility() {
		prenumeField.setVisible(catreHide.getValue());
		numeField.setVisible(catreHide.getValue());
		
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
	    RouteConfiguration.forSessionScope().removeRoute(NAME);
	    
	}
}
