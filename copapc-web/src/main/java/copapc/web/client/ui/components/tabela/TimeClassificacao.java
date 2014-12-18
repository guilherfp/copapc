package copapc.web.client.ui.components.tabela;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

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
    final Td tdPosicao = new Td(String.valueOf(time.getPosicao()));
    tdPosicao.getElement().getStyle().setColor("gray");
    tdPosicao.getElement().getStyle().setBackgroundColor("white");
    tdPosicao.getElement().getStyle().setPadding(6, Unit.PX);
    tdPosicao.getElement().getStyle().setFontWeight(FontWeight.BOLDER);
    add(tdPosicao);
    add(new Td(time.getNomeDoTime().toUpperCase()));
    final Td tdPontos = new Td(String.valueOf(time.getPontos()));
    tdPontos.getElement().getStyle().setFontWeight(FontWeight.BOLDER);
    add(tdPontos);
    add(new Td(String.valueOf(time.getJogos())));
    add(new Td(String.valueOf(time.getVitorias())));
    add(new Td(String.valueOf(time.getEmpates())));
    add(new Td(String.valueOf(time.getDerrotas())));
    add(new Td(String.valueOf(time.getGolsPros())));
    add(new Td(String.valueOf(time.getGolsContra())));
    add(new Td(String.valueOf(time.getSaldoDeGols())));
    add(new Td(time.getAproveitamento()));
  }

  public ResumoTime getTime() {
    return time;
  }

  @Override
  public void onClick(ClickEvent event) {
    // TODO Auto-generated method stub

  }

}
