package copapc.copa.web.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.service.jogo.JogoService;
import copapc.service.jogo.Rodada;

@Scope("request")
@Controller("jogoMB")
@ManagedBean(name = "jogoMB")
public class JogoManagedBean extends AbstractManagedBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String JOGO = "jogo";

  @Autowired
  private JogoRepository jogoRepository;
  @Autowired
  private JogoService jogoService;

  private Jogo jogo;
  private List<Jogo> jogos;
  private List<Rodada> rodadasFase1;
  private List<Rodada> rodadasFase2;

  public List<Rodada> getRodadasFase1() {
    if (rodadasFase1 == null) {
      rodadasFase1 = jogoService.getRodadas(1);
    }
    return rodadasFase1;
  }

  public List<Rodada> getRodadasFase2() {
    if (rodadasFase2 == null) {
      rodadasFase2 = jogoService.getRodadas(2);
    }
    return rodadasFase2;
  }

  public List<Jogo> getJogos() {
    if (jogos == null) {
      jogos = jogoRepository.jogos();
    }
    return jogos;
  }

  public Jogo getJogo() {
    if (jogo == null) {
      final String timeUrl = getURLParameterValue(JOGO);
      jogo = jogoRepository.jogoComNumero(Integer.parseInt(timeUrl));
    }
    return jogo;
  }

  public boolean isShowPrimeiroTempo() {
    return (getJogo().isSegundoTempo() == false) && (getJogo().isEncerrado() == false);
  }

  public boolean isShowSegundoTempo() {
    return (getJogo().isSegundoTempo() == true) && (getJogo().isEncerrado() == false);
  }

}
