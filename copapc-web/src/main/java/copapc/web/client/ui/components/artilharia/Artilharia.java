package copapc.web.client.ui.components.artilharia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.BorderStyle;

import copapc.web.client.ui.components.Container;
import copapc.web.client.ui.components.Span;
import copapc.web.client.ui.components.TBody;
import copapc.web.client.ui.components.THead;
import copapc.web.client.ui.components.Td;
import copapc.web.client.ui.components.Th;
import copapc.web.client.ui.components.Tr;
import copapc.web.shared.jogador.Artilheiro;
import copapc.web.shared.jogador.Jogador;
import copapc.web.shared.time.Time;

public class Artilharia extends Container {

  private int posicao = 1;
  private TBody body = new TBody();
  private Artilheiro ultimeAdicionado;

  public Artilharia() {
    super(Document.get().createTableElement());
    addStyleName("artilheiros");
    createHader();
    add(body);
    Time time = new Time(1, "Time A", "#992233");
    List<Artilheiro> artilheiros = new ArrayList<>();
    artilheiros.add(new Artilheiro(new Jogador("Jogador A", "a@email.com", 4, "PIVO", time), 25));
    artilheiros.add(new Artilheiro(new Jogador("Jogador B", "b@email.com", 4, "ALA", time), 23));
    artilheiros.add(new Artilheiro(new Jogador("Jogador C", "c@email.com", 4, "ALA", time), 21));
    artilheiros.add(new Artilheiro(new Jogador("Jogador D", "d@email.com", 4, "FIXO", time), 21));
    artilheiros.add(new Artilheiro(new Jogador("Jogador E", "e@email.com", 4, "PIVO", time), 20));
    loadArtilheiros(artilheiros);
  }

  public void loadArtilheiros(List<Artilheiro> artilheiros) {
    Collections.sort(artilheiros);
    for (Artilheiro artilheiro : artilheiros) {
      body.add(createLinha(artilheiro));
    }
  }

  private void createHader() {
    THead head = new THead();
    final Th thRanking = new Th("RANKING");
    thRanking.getElement().setAttribute("colspan", "2");
    head.add(thRanking);
    final Th thGols = new Th("GOLS");
    thGols.addStyleName("coluna-gols");
    head.add(thGols);
    add(head);
  }

  private Tr createLinha(Artilheiro artilheiro) {
    Tr tr = new Tr();
    tr.addStyleName("artilheiros-linha-corpo");
    if (empateComOAnterior(artilheiro) == false) {
      tr.add(getPosicao(posicao++));
    } else {
      tr.add(colunaVazia());
    }
    tr.add(getJogador(artilheiro.getJogador()));
    tr.add(getGols(artilheiro.getGols()));
    ultimeAdicionado = artilheiro;
    return tr;
  }

  private Td colunaVazia() {
    final Td td = new Td();
    td.addStyleName("coluna-colocacao-vazia");
    td.getElement().getStyle().setBorderStyle(BorderStyle.NONE);
    return td;
  }

  private boolean empateComOAnterior(Artilheiro artilheiro) {
    return (ultimeAdicionado != null) && (ultimeAdicionado.getGols() == artilheiro.getGols());
  }

  private Td getGols(int gols) {
    final Td td = new Td();
    td.addStyleName("coluna-gols");
    Span span = new Span(gols);
    span.addStyleName("artilheiro-gols");
    td.add(span);
    return td;
  }

  public Td getJogador(Jogador jogador) {
    final Td td = new Td();
    P pJogador = new P(jogador);
    pJogador.getElement().setInnerText(jogador.getNome());
    Span spanPosicao = new Span(jogador.getPosicao());
    spanPosicao.addStyleName("artilheiro-posicao");
    pJogador.add(spanPosicao);
    td.add(pJogador);
    return td;
  }

  public Td getPosicao(int posicao) {
    final Td td = new Td(posicao);
    td.addStyleName("coluna-colocacao");
    return td;
  }

  private class P extends Container {

    public P(Jogador jogador) {
      super(Document.get().createPElement());
    }
  }

}
