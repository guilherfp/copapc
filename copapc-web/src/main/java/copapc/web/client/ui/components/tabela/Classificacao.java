package copapc.web.client.ui.components.tabela;

import copapc.web.client.ui.components.TBody;
import copapc.web.client.ui.components.THead;
import copapc.web.client.ui.components.Table;
import copapc.web.client.ui.components.Th;
import copapc.web.shared.time.ResumoTime;

public class Classificacao extends Table {

  private ResumoTime resumoTime = new ResumoTime(1, "Os Bartira F.C.", 3, 1, 0, 30, 7);
  private TBody body = new TBody();

  public Classificacao() {
    addStyleName("tabela-pontos");
    addStyleName("responsive");
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
    for (String coluna : new String[] { "P", "J", "V", "E", "D", "GP", "GC", "SG", "%" }) {
      head.add(new Th(coluna));
    }
    add(head);
  }

}
