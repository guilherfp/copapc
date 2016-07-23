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
abstract class AbstractBean implements Serializable {
  private static final long serialVersionUID = 1L;

  protected final FacesContext getContext() {
    return FacesContext.getCurrentInstance();
  }

  protected final String urlValue(String parameter) {
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
    HttpServletRequest req = servletRequest();
    String url = PrettyContext.getCurrentInstance().getRequestURL().toURL();
    return StringUtils.remove(requestUrl(req), req.getRequestURI()).concat(url);
  }

  protected final String getResource(final String url) {
    HttpServletRequest req = servletRequest();
    return StringUtils.remove(requestUrl(req), req.getRequestURI()).concat(url);
  }

  private HttpServletRequest servletRequest() {
    return (HttpServletRequest) getContext().getExternalContext().getRequest();
  }

  private String requestUrl(HttpServletRequest req) {
    return req.getRequestURL().toString();
  }

}
