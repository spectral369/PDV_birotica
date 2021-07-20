package com.spectral369.CDR;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.spectral369.birotica.MainView;
import com.spectral369.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
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



public class CDRInfo extends HorizontalLayout implements RouterLayout, AfterNavigationObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "CDR";
	VerticalLayout content;
	HorizontalLayout backLayout;
	Button backbtn;
	HorizontalLayout titlelLayout;
	Button CDRTitle;
	HorizontalLayout checkLayout;
	Checkbox complete;

	HorizontalLayout infoLayout;
	VerticalLayout infoPart1;
	VerticalLayout infoPart2;
	VerticalLayout infoPart3;
	VerticalLayout infoPart4;

	TextField nr1Field;
	DatePicker dateField;
	TextField catreField;
	TextField denumireaAutoritatii;

	HorizontalLayout generateLayout;
	Button generate;

	CDRPDF pdf;
	Map<String, String> map;

	 private Binder<CDRInfo> binder;

	public CDRInfo() {

		content = new VerticalLayout();
		map = new HashMap<>();
		binder = new Binder<>(CDRInfo.class);
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
		CDRTitle = new Button("Cerere de Restituire", VaadinIcon.FILE_TEXT_O.create());
		CDRTitle.setEnabled(false);
		CDRTitle.setId("mainTitle");
		CDRTitle.addClassName("clearDisabled");
		titlelLayout.add(CDRTitle);
		content.add(titlelLayout);
		content.setAlignItems( Alignment.CENTER);

		checkLayout = new HorizontalLayout();
		complete = new Checkbox("Cu completare de date ?!", false);
		complete.setEnabled(false);
		complete.addValueChangeListener(evt -> {
			toggleVisibility();
			UI.getCurrent().push();
		});

		checkLayout.add(complete);
		content.add(checkLayout);
		content.setAlignItems( Alignment.CENTER);

		// info
		infoLayout = new HorizontalLayout();
		infoLayout.setVisible(false);

		infoPart1 = new VerticalLayout();
		nr1Field = new TextField("Nr:");
		nr1Field.setRequiredIndicatorVisible(true);
		infoPart1.add(nr1Field);
		infoLayout.add(infoPart1);
		infoLayout.setAlignItems( Alignment.CENTER);
		// todo

		infoPart2 = new VerticalLayout();
		dateField = new DatePicker("Data:");
		dateField.setRequiredIndicatorVisible(true);
		dateField.setLocale(Locale.getDefault());
		//dateField.setDateFormat("dd-MM-yyyy");
		binder.forField(dateField).asRequired()
/*withValidator(str -> str.toString()>4, "Password must be at least 4 chars long")*/
		.bind(new ValueProvider<CDRInfo, LocalDate>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public LocalDate apply(CDRInfo source) {
				return null;
			}
		}, new Setter<CDRInfo, LocalDate>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(CDRInfo bean, LocalDate fieldvalue) {

			}
		});
		// dateField.setErrorHandler(event -> generate.setEnabled(false));
	     dateField.addValueChangeListener(event -> generate.setEnabled(event.getValue() != null));
	 
	    dateField.setErrorMessage("Date Required !");
		infoPart2.add(dateField);
		infoLayout.add(infoPart2);
		infoLayout.setAlignItems( Alignment.CENTER);

		///
		infoPart3 = new VerticalLayout();
		catreField = new TextField("Catre:");
		catreField.setRequiredIndicatorVisible(true);
		infoPart3.add(catreField);
		infoLayout.add(infoPart3);
		infoLayout.setAlignItems( Alignment.CENTER);
		//

		infoPart4 = new VerticalLayout();
		denumireaAutoritatii = new TextField("Denumirea Autoritatii:");
		denumireaAutoritatii.setRequiredIndicatorVisible(true);
		infoPart4.add(denumireaAutoritatii);
		infoLayout.add(infoPart4);
		infoLayout.setAlignItems( Alignment.CENTER);

		content.add(infoLayout);
		content.setAlignItems(Alignment.CENTER);

		generateLayout = new HorizontalLayout();
		generate = new Button("Generate", VaadinIcon.FILE_PROCESS.create());
		generate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		generate.getClassNames().add("frendly");
		generate.addClickListener(evt -> {
			if (complete.getValue()) {
				map.put("nr1", nr1Field.getValue().trim());
				map.put("data", dateField.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
				map.put("catre", catreField.getValue());
				map.put("autoritateSuma", denumireaAutoritatii.getValue());
			}
			nr1Field.clear();
			dateField.clear();
			catreField.clear();
			denumireaAutoritatii.clear();
			
			PDFCDRCreator pdfcr =  new PDFCDRCreator(map,Utils.getTimeStr());
		 	String fn =  pdfcr.getID();
		    
			 RouteConfiguration.forSessionScope().removeRoute(CDRPDF.class);
		 	 RouteConfiguration.forSessionScope().setRoute(CDRPDF.NAME, CDRPDF.class);
		Map <String,String> sss =  new HashMap<String,String>();
		sss.put("tm", fn);
		
		UI.getCurrent().navigate("CDRPDF",QueryParameters.simple(sss));
			
		});
		generateLayout.add(generate);
	
		 binder.addStatusChangeListener(event -> {
	            if (!complete.getValue() || binder.isValid()) {
	                this.generate.setEnabled(true);
	            }
	            else {
	                this.generate.setEnabled(false);
	            }
	        });
		content.add(generateLayout);
		content.setAlignItems(Alignment.CENTER);

		content.setMargin(false);
		add(content);
	}

	private void toggleVisibility() {
		infoLayout.setVisible(!infoLayout.isVisible());

		generate.setEnabled(!generate.isEnabled());
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
	    RouteConfiguration.forSessionScope().removeRoute(NAME);
	    
	}
}
