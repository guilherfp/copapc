package copapc.web.client.ui.components.tabela;

import com.google.gwt.dom.client.Document;

import copapc.web.client.ui.components.Container;
import copapc.web.client.ui.components.TBody;
import copapc.web.client.ui.components.THead;
import copapc.web.client.ui.components.Th;
import copapc.web.shared.time.ResumoTime;

public class Classificacao extends Container {

  private ResumoTime resumoTime = new ResumoTime(1, "Os Bartira F.C.", 3, 1, 0, 30, 7);
  private TBody body = new TBody();

  public Classificacao() {
    super(Document.get().createTableElement());
    addStyleName("tabela-pontos");
    createHader();
    add(body);
    body.add(new TimeClassificacao(resumoTime));
    body.add(new TimeClassificacao(resumoTime));
    body.add(new TimeClassificacao(resumoTime));
    body.add(new TimeClassificacao(resumoTime));
    body.add(new TimeClassificacao(resumoTime));
    body.add(new TimeClassificacao(resumoTime));
    body.add(new TimeClassificacao(resumoTime));
    body.add(new TimeClassificacao(resumoTime));
    body.add(new TimeClassificacao(resumoTime));
  }

  private void createHader() {
    THead head = new THead();
    final Th thClassificacao = new Th("CLASSIFICAÇÃO");
    thClassificacao.getElement().setAttribute("colspan", "2");
    head.add(thClassificacao);
    head.add(new Th("P"));
    head.add(new Th("J"));
    head.add(new Th("V"));
    head.add(new Th("E"));
    head.add(new Th("D"));
    head.add(new Th("GP"));
    head.add(new Th("GC"));
    head.add(new Th("SG"));
    head.add(new Th("%"));
    add(head);
  }

}
