package copapc.web.client.ui.components;

import com.google.gwt.dom.client.Document;

public class Td extends Container {

  public Td() {
    super(Document.get().createTDElement());
  }

  public Td(String text) {
    this();
    getElement().setInnerText(text);
  }

  public Td(Object object) {
    this(object.toString());
  }
}
