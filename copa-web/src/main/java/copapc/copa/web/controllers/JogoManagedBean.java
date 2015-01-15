package copapc.copa.web.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.service.jogo.JogoService;
import copapc.service.jogo.Rodada;

@Scope("request")
@Controller("jogoMB")
// @ManagedBean(name = "jogoMB")
public class JogoManagedBean extends AbstractManagedBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String JOGO = "jogo";

  @Autowired
  private JogoRepository jogoRepository;
  @Autowired
  private JogoService jogoService;

  private Jogo jogo;
  private List<Jogo> jogos;
  private List<Jogo> proximos;
  private List<Jogo> ultimos;
  private List<Rodada> rodadas;

  public List<Rodada> getRodadasFase1() {
    if (rodadas == null) {
      rodadas = jogoService.getRodadas(1);
    }
    return rodadas;
  }

  public List<Jogo> getJogos() {
    if (jogos == null) {
      jogos = jogoRepository.jogos();
    }
    return jogos;
  }

  public List<Jogo> getProximos() {
    if (proximos == null) {
      proximos = jogoRepository.jogosEmAberto();
    }
    return proximos;
  }

  public List<Jogo> getUltimos() {
    if (ultimos == null) {
      ultimos = jogoRepository.ultimosEncerrados();
    }
    return ultimos;
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
