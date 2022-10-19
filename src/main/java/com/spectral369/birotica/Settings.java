package com.spectral369.birotica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spectral369.utils.PDFHelper;
import com.spectral369.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.Scroller.ScrollDirection;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.ValueProvider;

public class Settings extends Dialog {

    private static final long serialVersionUID = 4025762446805160330L;
    private VerticalLayout root;
    private TextField primarField;
    private VerticalLayout content;
    private HorizontalLayout part1;
    private TextField secretarField;
    private HorizontalLayout part2;
    private VerticalLayout part2Agricol;
    private Button closebtn;
    private Button savebtn;
    private ListBox<String> listBox;
    private List<String> items_agr = new ArrayList<String>();

    private Map<String, String[]> map;
    
    private Binder<Settings> binder;

    public Settings() {
	
	if(Utils.getSettingsInfo()!= null) {
	    map = Utils.getSettingsInfo();
	}

	root = new VerticalLayout();
	root.setAlignItems(Alignment.CENTER);
	binder = new Binder<Settings>(Settings.class);
	Button title = new Button("Setup", VaadinIcon.COG_O.create());
	title.setEnabled(false);
	title.addClassName("clearDisabled");
	root.add(title);

	content = new VerticalLayout();
	content.setAlignItems(Alignment.CENTER);
	part1 = new HorizontalLayout();
	part1.setAlignItems(Alignment.CENTER);

	primarField = new TextField();
	primarField.setLabel("Nume Primar");
	primarField.setHelperText("Format: Ion Vasile Marin");
	primarField.setSizeFull();
	primarField.setMaxLength(25);
	primarField.setMinWidth(20f, Unit.EM);
	primarField.setAllowedCharPattern("[a-zA-Z\s]");
	primarField.setValueChangeMode(ValueChangeMode.TIMEOUT);
	primarField.setValueChangeTimeout(1850);
	primarField.setRequired(true);
	primarField.setRequiredIndicatorVisible(true);
	primarField.addValueChangeListener(val -> {
	    if (primarField.getValue().length() > 3)
		primarField.setValue(PDFHelper.capitalizeWords(primarField.getValue()));
	});
	
	binder.forField(primarField).asRequired()
	
	.withValidator(str -> str.length() > 5, "Numele sa fie mai mare decat 5 caractere")
	.bind(new ValueProvider<Settings, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public String apply(Settings source) {
		return null;
	    }
	}, new Setter<Settings, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void accept(Settings bean, String fieldvalue) {

	    }
	});
	
	if(map!=null || map.get("primar")[0]!=null) {
	    primarField.setValue(map.get("primar")[0]);
	}

	secretarField = new TextField();
	secretarField.setLabel("Nume Secretar");
	secretarField.setSizeFull();
	secretarField.setMaxLength(25);
	secretarField.setMinWidth(20f, Unit.EM);
	secretarField.setHelperText("Format: Ion Vasile Marin");
	secretarField.setAllowedCharPattern("[a-zA-Z\s]");// tested ^[a-zA-Z\s]+$
	secretarField.setValueChangeMode(ValueChangeMode.TIMEOUT);
	secretarField.setValueChangeTimeout(1850);
	secretarField.setRequired(true);
	secretarField.setRequiredIndicatorVisible(true);
	secretarField.addValueChangeListener(val -> {
	    if (secretarField.getValue().length() > 3)
		secretarField.setValue(PDFHelper.capitalizeWords(secretarField.getValue()));
	});
	
	binder.forField(secretarField).asRequired()
	
	.withValidator(str -> str.length() > 5, "Numele sa fie mai mare decat 5 caractere")
	.bind(new ValueProvider<Settings, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public String apply(Settings source) {
		return null;
	    }
	}, new Setter<Settings, String>() {

	    private static final long serialVersionUID = 1L;

	    @Override
	    public void accept(Settings bean, String fieldvalue) {

	    }
	});
	if(map!=null || map.get("secretar")[0]!=null) {
	    secretarField.setValue(map.get("secretar")[0]);
	}
	
	
	part1.add(primarField, secretarField);
	root.add(part1);

	part2 = new HorizontalLayout();
	part2.setAlignItems(Alignment.CENTER);
	part2.setSizeFull();

	part2Agricol = new VerticalLayout();
	part2Agricol.setAlignItems(Alignment.CENTER);
	part2Agricol.setJustifyContentMode(JustifyContentMode.CENTER);
	part2Agricol.getStyle().set("border-style", "solid");
	part2Agricol.getStyle().set("border-width", "thin");
	Button agrLabel = new Button("Intocmit Registrul Agricol");
	agrLabel.setEnabled(false);
	agrLabel.addClassName("clearDisabled");

	HorizontalLayout agrAdd = new HorizontalLayout();
	agrAdd.setAlignItems(Alignment.CENTER);
	agrAdd.setJustifyContentMode(JustifyContentMode.CENTER);
	agrAdd.getStyle().set("display", "flow-root");
	TextField intocmitAgrField = new TextField("Adauga persoana 'Intocmit' ", "Nume Intocmit");
	intocmitAgrField.setAllowedCharPattern("[a-zA-Z\s]");
	intocmitAgrField.setValueChangeMode(ValueChangeMode.TIMEOUT);
	intocmitAgrField.setValueChangeTimeout(1000);
	intocmitAgrField.addValueChangeListener(val -> {
	    if (intocmitAgrField.getValue().length() > 3)
		intocmitAgrField.setValue(PDFHelper.capitalizeWordsWithSpace(intocmitAgrField.getValue()));
	});
	
	

	
	
	Button adaugaAgr = new Button(VaadinIcon.PLUS_CIRCLE.create());
	adaugaAgr.addClickListener(evt -> {
	    if (!intocmitAgrField.getValue().isBlank()) {
		items_agr.add(intocmitAgrField.getValue().trim());

		listBox.setItems(items_agr);
		intocmitAgrField.clear();
		binder.validate();
	    }
	});
	
	Button delAgr =  new Button(VaadinIcon.MINUS_CIRCLE.create());
	delAgr.addClickListener( evt->{
	 // listBox.remove(listBox.getValue());
	    items_agr.remove(items_agr.indexOf(listBox.getValue()));
	listBox.getListDataView().refreshAll();
	});
	
	agrAdd.add(intocmitAgrField, adaugaAgr,delAgr );

	listBox = new ListBox<String>();
	Scroller scroll = new Scroller(new Div(listBox));
	scroll.setScrollDirection(ScrollDirection.VERTICAL);

	part2Agricol.add(agrLabel, agrAdd, scroll);
	part2Agricol.setHeightFull();
	part2.add(part2Agricol);
	root.add(part2);
	
	if(map!=null || map.get("intocmitRegAgr")[0]!=null) {
		  for(String item : map.get("intocmitRegAgr"))
		      items_agr.add(item);
		  listBox.setItems(items_agr);
		}
		

	HorizontalLayout footerBtnLayout = new HorizontalLayout();
	footerBtnLayout.setAlignItems(Alignment.CENTER);
	closebtn = new Button("Close", VaadinIcon.CLOSE_CIRCLE.create());
	closebtn.addClickListener(evt -> {
	    this.close();
	});
	savebtn = new Button("Save", VaadinIcon.USER_CHECK.create());
	savebtn.setEnabled(false);
	binder.addStatusChangeListener(event -> {
	
	    if (binder.isValid() && items_agr.size()>0) {
		savebtn.setEnabled(true);
	    } else {
		savebtn.setEnabled(false);
	    }
	});

	
	savebtn.addClickListener(evt -> {
	    map = new HashMap<String, String[]>();
	    map.put("primar", new String[] { primarField.getValue().trim() });
	    map.put("secretar", new String[] { secretarField.getValue().trim() });
	    map.put("intocmitRegAgr", items_agr.stream().toArray(String[]::new));
	    boolean isSuccessful = Utils.saveSettings(map);
	    Notification.show("Settings saved(overrided)... " + isSuccessful, 5000, Position.MIDDLE);
	    this.close();
	});
	footerBtnLayout.add(closebtn, savebtn);

	root.add(footerBtnLayout);
	root.setSizeFull();

	add(root);

	setResizable(false);
	setModal(true);
	setDraggable(false);
	setCloseOnEsc(false);
	setSizeFull();

	setCloseOnOutsideClick(false);
	UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> {
	    setWidth(details.getScreenWidth() / 1.2f, Unit.PIXELS);
	    setHeight(details.getScreenHeight() / 1.2f, Unit.PIXELS);
	    scroll.setHeight(details.getScreenHeight() / 3f, Unit.PIXELS);
	});

    }
}
