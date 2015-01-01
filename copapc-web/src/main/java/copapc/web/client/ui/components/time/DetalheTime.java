package copapc.web.client.ui.components.time;

import org.gwtbootstrap3.client.ui.Panel;
import org.gwtbootstrap3.client.ui.PanelBody;
import org.gwtbootstrap3.client.ui.PanelHeader;

import copapc.web.client.ui.components.TBody;
import copapc.web.client.ui.components.THead;
import copapc.web.client.ui.components.Table;
import copapc.web.client.ui.components.Td;
import copapc.web.client.ui.components.Th;
import copapc.web.client.ui.components.Tr;
import copapc.web.shared.jogador.Jogador;
import copapc.web.shared.time.Time;

public class DetalheTime extends Panel {

  private TBody body = new TBody();

  private Time time = new Time(1, "Time TESTE", "#234645");
  Jogador jogadorA = new Jogador("Jogador A", "a@email.com", 4, "PIVO", time);
  Jogador jogadorB = new Jogador("Jogador B", "b@email.com", 4, "ALA", time);
  Jogador jogadorC = new Jogador("Jogador C", "c@email.com", 4, "ALA", time);
  Jogador jogadorD = new Jogador("Jogador D", "d@email.com", 4, "FIXO", time);
  Jogador jogadorE = new Jogador("Jogador E", "e@email.com", 4, "GOLEIRO", time);

  public DetalheTime() {
    createHeader();
    setTime(time);
    PanelBody panelBody = new PanelBody();
    panelBody.add(getTable());
    add(panelBody);
    addJogador(jogadorA);
    addJogador(jogadorB);
    addJogador(jogadorC);
    addJogador(jogadorD);
    addJogador(jogadorE);
  }

  public Table getTable() {
    final Table table = new Table();
    table.addStyleName("table");
    table.add(createHeader());
    table.add(body);
    return table;
  }

  private THead createHeader() {
    final THead head = new THead();
    for (String coluna : new String[] { "NOME", "POSIÇÃO", "PONTUAÇÃO" }) {
      head.add(new Th(coluna));
    }
    return head;
  }

  public void addJogador(Jogador jogador) {
    final Tr tr = new Tr();
    tr.add(new Td(jogador.getNome()));
    tr.add(new Td(jogador.getPosicao()));
    tr.add(new Td(jogador.getPontuacao()));
    body.add(tr);
  }

  public void setTime(Time time) {
    PanelHeader header = new PanelHeader();
    header.setText(time.getNome());
    header.setTitle(time.getNome());
    add(header);
  }

}
