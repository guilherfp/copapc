package copapc.copa.web.controllers;

import javax.faces.bean.ManagedBean;

import org.springframework.stereotype.Controller;

import copapc.model.jogador.Jogador;
import copapc.model.jogador.Posicao;
import copapc.model.time.Time;

@ManagedBean(name = "timeMB")
@Controller("timeMB")
public class TimeManagedBean {

  private Jogador getJogador(int numero) {
    final Jogador jogador = new Jogador("Jogador " + numero, numero + "@email.com.br");
    jogador.setPosicao(Posicao.ALA);
    return jogador;
  }

  public Time getTime() {
    final Time time = new Time(1, "Bartira FC");
    for (int numero = 1; numero <= 10; numero++) {
      time.adicionarJogador(getJogador(numero));
    }
    return time;
  }

}
