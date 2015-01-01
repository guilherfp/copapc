package copapc.web.client.ui.components.tabela;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

import copapc.web.client.ui.components.Td;
import copapc.web.client.ui.components.Tr;
import copapc.web.shared.time.ResumoTime;

public class TimeClassificacao extends Tr implements ClickHandler {

  private ResumoTime time;

  public TimeClassificacao(ResumoTime time) {
    addStyleName("tabela-body-linha");
    setTime(time);
  }

  private void setTime(ResumoTime time) {
    add(tdPosicao(time));
    add(tdTime(time));
    add(tdPontos(time));
    add(new Td(time.getJogos()));
    add(new Td(time.getVitorias()));
    add(new Td(time.getEmpates()));
    add(new Td(time.getDerrotas()));
    add(new Td(time.getGolsPros()));
    add(new Td(time.getGolsContra()));
    add(new Td(time.getSaldoDeGols()));
    add(new Td(time.getAproveitamento()));
    this.time = time;
  }

  public Td tdPontos(ResumoTime time) {
    final Td td = new Td(String.valueOf(time.getPontos()));
    td.getElement().getStyle().setFontWeight(FontWeight.BOLDER);
    return td;
  }

  public Td tdTime(ResumoTime time) {
    final Td td = new Td(time.getNomeDoTime().toUpperCase());
    td.addStyleName("tabela-nome-time");
    td.addDomHandler(this, ClickEvent.getType());
    return td;
  }

  public Td tdPosicao(ResumoTime time) {
    final Td td = new Td(String.valueOf(time.getPosicao()));
    td.addStyleName("tabela-body-time");
    td.getElement().getStyle().setColor("gray");
    td.getElement().getStyle().setBackgroundColor("white");
    td.getElement().getStyle().setPadding(6, Unit.PX);
    td.getElement().getStyle().setFontWeight(FontWeight.BOLDER);
    return td;
  }

  public ResumoTime getTime() {
    return time;
  }

  @Override
  public void onClick(ClickEvent event) {
    Window.alert(time.getNomeDoTime());
  }

}
