package copapc.copa.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.copa.web.shared.Lazy;
import copapc.model.jogo.Jogo;
import copapc.model.resumoclassificacao.Classificacao;
import copapc.model.time.Time;
import copapc.service.classificacao.ClassificacaoService;

/**
 * @author Guilherme Pacheco
 */
@Scope("request")
@Controller("classificacaoMB")
public class ClassificacaoBean extends AbstractBean {
  private static final long serialVersionUID = 1L;

  private static final char GA = 'A';
  private static final char GB = 'B';

  private final Lazy<List<Classificacao>> classificacoesFase1GrupoA = Lazy.empty();
  private final Lazy<List<Classificacao>> classificacoesFase1GrupoB = Lazy.empty();
  private final Lazy<List<Classificacao>> classificacoesFase2GrupoA = Lazy.empty();
  private final Lazy<List<Classificacao>> classificacoesFase2GrupoB = Lazy.empty();
  private final Lazy<Jogo> semifinalA = Lazy.empty();
  private final Lazy<Jogo> semifinalB = Lazy.empty();
  private final Lazy<Jogo> jogoFinalA = Lazy.empty();
  private final Lazy<Jogo> jogoFinalB = Lazy.empty();

  @Autowired
  private ClassificacaoService service;

  @Transactional
  public List<Classificacao> getClassificacoesFase1GrupoA() {
    return classificacoesFase1GrupoA.get(() -> service.classificacaoFase1PorGrupo(GA));
  }

  @Transactional
  public List<Classificacao> getClassificacoesFase1GrupoB() {
    return classificacoesFase1GrupoB.get(() -> service.classificacaoFase1PorGrupo(GB));
  }

  @Transactional
  public List<Classificacao> getClassificacoesFase2GrupoA() {
    return classificacoesFase2GrupoA.get(() -> service.classificacaoGrupoAFase2());
  }

  @Transactional
  public List<Classificacao> getClassificacoesFase2GrupoB() {
    return classificacoesFase2GrupoB.get(() -> service.classificacaoGrupoBFase2());
  }

  @Transactional
  public boolean isShowFase2() {
    return !getClassificacoesFase2GrupoA().isEmpty() && !getClassificacoesFase2GrupoB().isEmpty();
  }

  public boolean isShowSemiFinal() {
    return getSemifinalA() != null && getSemifinalB() != null;
  }

  public Jogo getSemifinalA() {
    return semifinalA.get(() -> service.getPrimeiroJogoSemiFinal());
  }

  public Jogo getSemifinalB() {
    return semifinalB.get(() -> service.getSegundoJogoSemiFinal());
  }

  public boolean isShowFinal() {
    return getJogoFinalA() != null && getJogoFinalB() != null;
  }

  public Jogo getJogoFinalA() {
    return jogoFinalA.get(() -> service.getPrimeiroJogoFinal());
  }

  public Jogo getJogoFinalB() {
    return jogoFinalB.get(() -> service.getSegundoJogoFinal());
  }

  public Time getCampeao() {
    return getJogoFinalB().getVencedor();
  }

  public String getImageClassificacao() {
    return getResource("/resources/images/classificacao.jpg");
  }

}
