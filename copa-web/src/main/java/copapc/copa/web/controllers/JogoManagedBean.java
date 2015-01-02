package copapc.copa.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;

import copapc.model.jogo.Jogo;
import copapc.model.time.Time;

@ManagedBean(name = "jogoMB")
@Controller("jogoMB")
public class JogoManagedBean {

  private List<Jogo> jogos;

  private Jogo getJogo() {
    final Time visitante = new Time(1, "Time Visitante");
    final Time mandante = new Time(2, "Time Mandante");
    final Jogo jogo = new Jogo(mandante, visitante);
    jogo.setHorario(new LocalDateTime());
    return jogo;
  }

  public List<Jogo> getJogos() {
    if (jogos == null) {
      jogos = new ArrayList<>();
      for (int i = 0; i < 20; i++) {
        jogos.add(getJogo());
      }
    }
    return jogos;
  }

}
