package com.spectral369.utils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;

@Tag("div")
public class PdfView extends Component {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void add(String name) {

	com.vaadin.flow.dom.Element el = new com.vaadin.flow.dom.Element("iframe");

	el.setAttribute("src", "pdfs/" + name);

	Element childWrapper = ElementFactory.createDiv();

	el.setAttribute("style", "width:100%; height:100%");
	el.setAttribute("id", "pdfmain");
	childWrapper.appendChild(el);

	childWrapper.setAttribute("style", "width:100%; height:100%");
	childWrapper.setAttribute("id", "pdfext1");

	getElement().appendChild(childWrapper);

	getElement().setAttribute("style", "width:100%; height:100%");
	getElement().setAttribute("id", "pdfext2");

    }

}