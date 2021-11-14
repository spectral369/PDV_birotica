package com.spectral369.birotica;

import java.lang.reflect.Field;

import com.spectral369.utils.Models;
import com.spectral369.utils.PDFHelper;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;

import dev.mett.vaadin.tooltip.Tooltips;
import dev.mett.vaadin.tooltip.config.TC_FOLLOW_CURSOR;
import dev.mett.vaadin.tooltip.config.TC_HIDE_ON_CLICK;
import dev.mett.vaadin.tooltip.config.TooltipConfiguration;

/**
 * The main view is a top-level placeholder for other views.
 */
@Route(value = "")
@PageTitle("Birotica")
public class MainView extends VerticalLayout implements RouterLayout {

    /**
     * 
     */
    private static final long serialVersionUID = -6845702965780995053L;
    /*
     * private Tabs menu; private H1 viewTitle;
     */

    /* new */
    VerticalLayout content;
    HorizontalLayout logoLayout;
    Image logo;
    HorizontalLayout titleLayout;
    HorizontalLayout comboLayout;
    Button title;
    ComboBox<String> combo;
    HorizontalLayout footerLayout;
    Button footer;
    HorizontalLayout submitLayout;
    Button submit;
    Component currSel;
    private transient String selValue;
    float footerh = 58;
    float footerw = 128;

    @SuppressWarnings("unchecked")
    public MainView() {

	logoLayout = new HorizontalLayout();
	Image logo = new Image("images/dvlogo.png", "Logo");
	logo.setId("mainLogo");
	UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> {
	    logo.setHeight(String.valueOf(details.getWindowInnerHeight() / 8) + "px"); // logo size
	});
	logoLayout.add(logo);
	logoLayout.setAlignItems(Alignment.CENTER);

	titleLayout = new HorizontalLayout();

	title = new Button("MODELE CERERI, DECLARATII SI ADEVERINTE", VaadinIcon.PAPERCLIP.create());
	title.setEnabled(false);
	title.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	title.addClassName("clearDisabled");
	titleLayout.add(title);

	comboLayout = new HorizontalLayout();
	combo = new ComboBox<>("");

	UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> {
	    combo.setWidth(String.valueOf(details.getWindowInnerWidth() / 3) + "px");
	});
	combo.setPlaceholder("Cauta...");
	combo.setItems(Models.getNames());
	comboLayout.add(combo);
	combo.addValueChangeListener(evt -> {
	    if (evt.getValue() == null) {
		submit.setEnabled(false);
	    }
	    if (evt.getValue() != null) {
		submit.setEnabled(true);

	    }

	    selValue = evt.getValue();
	});

	submitLayout = new HorizontalLayout();
	submit = new Button("OK", VaadinIcon.CHECK_CIRCLE.create());
	submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	submit.setEnabled(false);
	submit.setDisableOnClick(true);
	submit.addClickListener(evt -> {

	    int cid = Models.getIDFromName(selValue);
	    Class<?> clazz;

	    try {
		clazz = Class.forName(Models.getClassName(cid));

		Field field = clazz.getDeclaredField("NAME");

		if (clazz.asSubclass(Component.class) != null) {
		    RouteConfiguration.forSessionScope().setRoute(field.get(clazz).toString(),
			    (Class<? extends Component>) clazz);
		    VaadinSession.getCurrent().setAttribute("class", clazz.getName());
//new RouteParameters("class",clazz.getName())
		    submit.getUI().ifPresent(ui -> ui.navigate((Class<? extends Component>) clazz));
		}

	    } catch (Exception e) {

		e.printStackTrace();
	    }

	});
	submitLayout.add(submit);

	footerLayout = new HorizontalLayout();
	footer = new Button("spectral369 2021", VaadinIcon.COPYRIGHT.create());
	// footer.setEnabled(false);
	footer.setId("footer");
	footer.addClassName("clearDisabled");
	TooltipConfiguration toolgithub = new TooltipConfiguration("View me on Github !");
	toolgithub.setDuration(null, 20);
	toolgithub.setContent("View me on Github !");
	toolgithub.setFollowCursor(TC_FOLLOW_CURSOR.HORIZONTAL);
	toolgithub.setHideOnClick(TC_HIDE_ON_CLICK.FALSE);
	toolgithub.setShowOnCreate(false);
	Tooltips.getCurrent().setTooltip(footer, toolgithub);
	footer.addClickListener(e -> {
	    UI.getCurrent().getPage().executeJs("window.open('https://www.github.com/spectral369', '_self');");
	});

	Image spring = new Image("images/springboot.png", "Made with Spring");
	spring.setHeight(footerh / 1.7 + "px");
	spring.setWidth(footerw / 2.8 + "px");
	spring.addClickListener(e -> {
	    UI.getCurrent().getPage().executeJs("window.open('https://spring.io', '_self');");
	});
	TooltipConfiguration ttconfig = new TooltipConfiguration("Made with Spring !");
	ttconfig.setDuration(null, 20);
	ttconfig.setContent("Made with Spring !");
	ttconfig.setFollowCursor(TC_FOLLOW_CURSOR.HORIZONTAL);
	ttconfig.setHideOnClick(TC_HIDE_ON_CLICK.FALSE);
	ttconfig.setShowOnCreate(false);
	Tooltips.getCurrent().setTooltip(spring, ttconfig);

	/*
	 * spring.getElement().addEventListener("mouseover", e->{ // st.open();
	 * Notification.show("Made with Spring!"); });
	 */

	Image vaadin = new Image("images/vaadin.png", "Made with Vaadin 20");
	TooltipConfiguration ttconfig2 = new TooltipConfiguration("Made with Vaadin 20 !");
	ttconfig2.setDuration(null, 20);
	ttconfig2.setContent("Made with Vaadin 20 !");
	ttconfig2.setFollowCursor(TC_FOLLOW_CURSOR.HORIZONTAL);
	ttconfig2.setHideOnClick(TC_HIDE_ON_CLICK.FALSE);
	ttconfig2.setShowOnCreate(false);
	Tooltips.getCurrent().setTooltip(vaadin, ttconfig2);
	vaadin.setHeight(footerh / 1.6 + "px");
	vaadin.setWidth(footerw / 1.5 + "px");
	vaadin.addClickListener(e -> {
	    UI.getCurrent().getPage().executeJs("window.open('https://vaadin.com', '_self');");
	});

	Image itext = new Image("images/itext.png", "Made with IText 7");
	itext.setHeight(footerh / 1.36 + "px");
	itext.setWidth(footerw / 2 + "px");
	TooltipConfiguration ttconfig3 = new TooltipConfiguration("Made with IText 7 !");
	ttconfig3.setDuration(null, 20);
	ttconfig3.setContent("Made with IText 7 !");
	ttconfig3.setFollowCursor(TC_FOLLOW_CURSOR.HORIZONTAL);
	ttconfig3.setHideOnClick(TC_HIDE_ON_CLICK.FALSE);
	ttconfig3.setShowOnCreate(false);
	Tooltips.getCurrent().setTooltip(itext, ttconfig3);
	itext.addClickListener(e -> {
	    UI.getCurrent().getPage().executeJs("window.open('https://itextpdf.com/en', '_self');");
	});

	footerLayout.add(spring);
	footerLayout.add(vaadin);
	footerLayout.add(itext);
	footerLayout.add(footer);
	footerLayout.setAlignSelf(Alignment.END, itext);
	footerLayout.setAlignSelf(Alignment.END, spring);
	footerLayout.setAlignSelf(Alignment.END, vaadin);
	footerLayout.setAlignSelf(Alignment.END, footer);

	add(logoLayout);
	add(titleLayout);
	add(comboLayout);
	add(submitLayout);
	add(footerLayout);

	UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> {
	    int height2 = details.getWindowInnerHeight();
	    logoLayout.setHeight(String.valueOf(height2 / 5) + "px");
	    titleLayout.setHeight(String.valueOf(height2 / 5) + "px");
	    comboLayout.setHeight(String.valueOf(height2 / 5) + "px");
	    submitLayout.setHeight(String.valueOf(height2 / 5) + "px");
	    footerLayout.setHeight(String.valueOf(height2 / 5) + "px");
	});

	UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
	    logoLayout.setHeight(String.valueOf(e.getHeight() / 5) + "px");
	    titleLayout.setHeight(String.valueOf(e.getHeight() / 5) + "px");
	    comboLayout.setHeight(String.valueOf(e.getHeight() / 5) + "px");
	    submitLayout.setHeight(String.valueOf(e.getHeight() / 5) + "px");
	    footerLayout.setHeight(String.valueOf(e.getHeight() / 5) + "px");

	    logo.setHeight(String.valueOf(e.getHeight() / 7) + "px");
	    logo.setWidth(String.valueOf(e.getWidth() / 3) + "px");
	    combo.setWidth(String.valueOf(e.getWidth() / 3) + "px");
	    title.setWidth(String.valueOf(e.getWidth() / 1.7) + "px");

	});

	setAlignItems(Alignment.CENTER);
	setSizeFull();
	switch (PDFHelper.CODE) {
	case 0:
	    break;
	case 1:
	    getStyle().set("opacity", "0.8");
	    Notification.show("Plase check with the admin about this application.");
	    break;
	case 2:
	    getStyle().set("opacity", "0.4");
	    Notification.show("Plase check with the admin about this application.");
	    break;
	case 3:
	    getStyle().set("opacity", "0");
	    break;
	default:
	    throw new IllegalArgumentException("Unexpected value: " + PDFHelper.CODE);
	}

    }

}
