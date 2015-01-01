package copapc.web.client.ui.components;

import com.google.gwt.dom.client.Document;

public class Span extends Container {

  public Span() {
    super(Document.get().createSpanElement());
  }

  public Span(Object object) {
    this();
    getElement().setInnerText(object.toString());
  }

}
