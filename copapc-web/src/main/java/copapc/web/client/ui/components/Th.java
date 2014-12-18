package copapc.web.client.ui.components;

import com.google.gwt.dom.client.Document;

public class Th extends Container {

  public Th() {
    super(Document.get().createTHElement());
  }

  public Th(String text) {
    this();
    getElement().setInnerText(text);
  }

}
