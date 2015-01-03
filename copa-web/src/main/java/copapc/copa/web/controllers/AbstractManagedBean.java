package copapc.copa.web.controllers;

import java.io.Serializable;

import javax.faces.context.FacesContext;

public abstract class AbstractManagedBean implements Serializable {
  private static final long serialVersionUID = 1L;

  protected static FacesContext getContext() {
    return FacesContext.getCurrentInstance();
  }

  protected static String getURLParameterValue(String parameter) {
    final FacesContext context = getContext();
    if (context != null) {
      final String[] parameters = context.getExternalContext().getRequestParameterValuesMap().get(parameter);
      if (parameter != null) {
        return parameters[0];
      }
    }
    return null;
  }
}
