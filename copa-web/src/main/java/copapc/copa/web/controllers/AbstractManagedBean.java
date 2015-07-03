package copapc.copa.web.controllers;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.ocpsoft.pretty.PrettyContext;

/**
 * @author Guilherme Pacheco
 */
public abstract class AbstractManagedBean implements Serializable {
  private static final long serialVersionUID = 1L;

  protected FacesContext getContext() {
    return FacesContext.getCurrentInstance();
  }

  protected String getURLParameterValue(String parameter) {
    FacesContext context = getContext();
    if (context != null) {
      ExternalContext externalContext = context.getExternalContext();
      String[] parameters = externalContext.getRequestParameterValuesMap().get(parameter);
      if (parameter != null) {
        return parameters[0];
      }
    }
    return null;
  }

  public final String getUrl() {
    HttpServletRequest req = (HttpServletRequest) getContext().getExternalContext().getRequest();
    String url = PrettyContext.getCurrentInstance().getRequestURL().toURL();
    return StringUtils.remove(req.getRequestURL().toString(), req.getRequestURI()).concat(url);
  }

  protected String getResource(final String url) {
    HttpServletRequest req = (HttpServletRequest) getContext().getExternalContext().getRequest();
    return StringUtils.remove(req.getRequestURL().toString(), req.getRequestURI()).concat(url);
  }
}
