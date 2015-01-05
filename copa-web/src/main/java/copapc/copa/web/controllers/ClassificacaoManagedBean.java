package copapc.copa.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.model.jogo.Jogo;
import copapc.model.resumoclassificacao.Classificacao;
import copapc.model.time.Time;
import copapc.model.time.TimeBuilder;
import copapc.service.classificacao.ClassificacaoService;

@Scope("request")
@Controller("classificacaoMB")
// @ManagedBean(name = "classificacaoMB")
public class ClassificacaoManagedBean {

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
      classificacoesFase1GrupoA = classificacaoService.classificacaoFase1('A');
    }
    return classificacoesFase1GrupoA;
  }

  @Transactional
  public List<Classificacao> getClassificacoesFase1GrupoB() {
    if (classificacoesFase1GrupoB == null) {
      classificacoesFase1GrupoB = classificacaoService.classificacaoFase1('B');
    }
    return classificacoesFase1GrupoB;
  }

  @Transactional
  public List<Classificacao> getClassificacoesFase2GrupoA() {
    if (classificacoesFase2GrupoA == null) {
      classificacoesFase2GrupoA = new ArrayList<>();
      // classificacoesFase2GrupoA = classificacaoService.classificacaoFase2GrupoA();
    }
    return classificacoesFase2GrupoA;
  }

  @Transactional
  public List<Classificacao> getClassificacoesFase2GrupoB() {
    if (classificacoesFase2GrupoB == null) {
      classificacoesFase2GrupoB = new ArrayList<>();
      // classificacoesFase2GrupoB = classificacaoService.classificacaoFase2GrupoB();
    }
    return classificacoesFase2GrupoB;
  }

  public Jogo getSemifinalA() {
    if (semifinalA == null) {
      semifinalA = new Jogo(1, new Time(1, "1º Grupo A"), new Time(2, "2º Grupo B"));
      semifinalA.setHorario(DateTime.parse("2015-02-11T20:00"));
    }
    return semifinalA;
  }

  public Jogo getSemifinalB() {
    if (semifinalB == null) {
      semifinalB = new Jogo(1, new Time(1, "1º Grupo B"), new Time(2, "2º Grupo A"));
      semifinalB.setHorario(DateTime.parse("2015-02-11T21:00"));
    }
    return semifinalB;
  }

  public Jogo getJogoFinalA() {
    if (jogoFinalA == null) {
      jogoFinalA = new Jogo(1, new Time(1, "Perd jogo 1"), new Time(2, "Perd jogo 2"));
      jogoFinalA.setHorario(DateTime.parse("2015-02-21T09:00"));
    }
    return jogoFinalA;
  }

  public Jogo getJogoFinalB() {
    if (jogoFinalB == null) {
      jogoFinalB = new Jogo(1, new Time(1, "Venc jogo 1"), new Time(2, "Venc jogo 2"));
      jogoFinalB.setHorario(DateTime.parse("2015-02-21T11:00"));
    }
    return jogoFinalB;
  }

  public boolean isShowEscudoSemiFinalA() {
    return false;
  }

  public boolean isShowEscudoSemiFinalB() {
    return false;
  }

  public boolean isShowEscudoFinalA() {
    return false;
  }

  public boolean isShowEscudoFinalB() {
    return false;
  }

  public Time getCampeao() {
    return TimeBuilder.timeNaoDefinido(1);
  }
}
