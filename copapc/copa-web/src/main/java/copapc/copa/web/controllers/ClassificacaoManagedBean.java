package copapc.copa.web.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.model.jogo.Jogo;
import copapc.model.resumoclassificacao.Classificacao;
import copapc.model.time.Time;
import copapc.service.classificacao.ClassificacaoService;

/**
 * @author Guilherme Pacheco
 */
@Scope("request")
@Controller("classificacaoMB")
@ManagedBean(name = "classificacaoMB")
public class ClassificacaoManagedBean extends AbstractManagedBean {
  private static final long serialVersionUID = 1L;

  private List<Classificacao> classificacoesFase1GrupoA;
  private List<Classificacao> classificacoesFase1GrupoB;
  private List<Classificacao> classificacoesFase2GrupoA;
  private List<Classificacao> classificacoesFase2GrupoB;
  private Jogo semifinalA;
  private Jogo semifinalB;
  private Jogo jogoFinalA;
  private Jogo jogoFinalB;

  @Autowired
  private ClassificacaoService classificacaoService;

  @Transactional
  public List<Classificacao> getClassificacoesFase1GrupoA() {
    if (classificacoesFase1GrupoA == null) {
      classificacoesFase1GrupoA = classificacaoService.classificacaoFase1PorGrupo('A');
    }
    return classificacoesFase1GrupoA;
  }

  @Transactional
  public List<Classificacao> getClassificacoesFase1GrupoB() {
    if (classificacoesFase1GrupoB == null) {
      classificacoesFase1GrupoB = classificacaoService.classificacaoFase1PorGrupo('B');
    }
    return classificacoesFase1GrupoB;
  }

  @Transactional
  public List<Classificacao> getClassificacoesFase2GrupoA() {
    if (classificacoesFase2GrupoA == null) {
      classificacoesFase2GrupoA = classificacaoService.classificacaoGrupoAFase2();
    }
    return classificacoesFase2GrupoA;
  }

  @Transactional
  public List<Classificacao> getClassificacoesFase2GrupoB() {
    if (classificacoesFase2GrupoB == null) {
      classificacoesFase2GrupoB = classificacaoService.classificacaoGrupoBFase2();
    }
    return classificacoesFase2GrupoB;
  }

  @Transactional
  public boolean isShowFase2() {
    return !getClassificacoesFase2GrupoA().isEmpty() && !getClassificacoesFase2GrupoB().isEmpty();
  }

  public boolean isShowSemiFinal() {
    return (getSemifinalA() != null) && (getSemifinalB() != null);
  }

  public Jogo getSemifinalA() {
    if (semifinalA == null) {
      semifinalA = classificacaoService.getPrimeiroJogoSemiFinal();
    }
    return semifinalA;
  }

  public Jogo getSemifinalB() {
    if (semifinalB == null) {
      semifinalB = classificacaoService.getSegundoJogoSemiFinal();
    }
    return semifinalB;
  }

  public boolean isShowFinal() {
    return (getJogoFinalA() != null) && (getJogoFinalB() != null);
  }

  public Jogo getJogoFinalA() {
    if (jogoFinalA == null) {
      jogoFinalA = classificacaoService.getPrimeiroJogoFinal();
    }
    return jogoFinalA;
  }

  public Jogo getJogoFinalB() {
    if (jogoFinalB == null) {
      jogoFinalB = classificacaoService.getSegundoJogoFinal();
    }
    return jogoFinalB;
  }

  public Time getCampeao() {
    return getJogoFinalB().getVencedor();
  }

  public String getImageClassificacao() {
    return getResource("/resources/images/classificacao.jpg");
  }
}
