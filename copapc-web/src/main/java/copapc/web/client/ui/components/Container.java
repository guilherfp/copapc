package copapc.web.client.ui.components;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author guilherme.pacheco
 */
public abstract class Container extends ComplexPanel {

  private final Element el;

  public Element getEl() {
    return el;
  }

  public Container(Element el) {
    this.el = el;
    setElement(el);
  }

  @Override
  @SuppressWarnings("deprecation")
  public void add(Widget w) {
    add(w, getElement());
  }
}
