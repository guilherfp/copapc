package copapc.copa.web.controllers;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import copapc.model.jogo.Jogo;
import copapc.model.time.Time;
import copapc.model.time.TimeRepository;

@Scope("request")
@Controller("classificacaoMB")
// @ManagedBean(name = "classificacaoMB")
public class ClassificacaoManagedBean {

  private Jogo semifinalA;
  private Jogo semifinalB;
  private Jogo jogoFinalA;
  private Jogo jogoFinalB;

  @Autowired
  private TimeRepository timeRepository;

  public Jogo getSemifinalA() {
    if (semifinalA == null) {
      semifinalA = new Jogo(new Time(1, "1º Grupo A"), new Time(2, "2º Grupo B"));
      semifinalA.setHorario(DateTime.parse("2015-02-11T20:00"));
    }
    return semifinalA;
  }

  public Jogo getSemifinalB() {
    if (semifinalB == null) {
      semifinalB = new Jogo(new Time(1, "1º Grupo B"), new Time(2, "2º Grupo A"));
      semifinalB.setHorario(DateTime.parse("2015-02-11T21:00"));
    }
    return semifinalB;
  }

  public Jogo getJogoFinalA() {
    if (jogoFinalA == null) {
      jogoFinalA = new Jogo(new Time(1, "Perd jogo 1"), new Time(2, "Perd jogo 2"));
      jogoFinalA.setHorario(DateTime.parse("2015-02-21T09:00"));
    }
    return jogoFinalA;
  }

  public Jogo getJogoFinalB() {
    if (jogoFinalB == null) {
      jogoFinalB = new Jogo(new Time(1, "Venc jogo 1"), new Time(2, "Venc jogo 2"));
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
}
