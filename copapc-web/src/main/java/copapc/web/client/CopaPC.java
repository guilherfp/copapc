package copapc.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import copapc.web.client.ui.components.tabela.Classificacao;

public class CopaPC implements EntryPoint {

  // private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
  // private final Messages messages = GWT.create(Messages.class);

  @Override
  public void onModuleLoad() {
    RootPanel.get("main").add(new Classificacao());
  }

}
