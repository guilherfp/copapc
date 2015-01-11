package copapc.copa.web.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.service.jogo.JogoService;
import copapc.service.jogo.Rodada;

@Scope("request")
@Controller("jogoMB")
@ManagedBean(name = "jogoMB")
public class JogoManagedBean implements Serializable {
  private static final long serialVersionUID = 1L;

  @Autowired
  private JogoRepository jogoRepository;
  @Autowired
  private JogoService jogoService;

  private List<Jogo> jogos;
  private List<Jogo> proximos;
  private List<Rodada> rodadas;

  public List<Rodada> getRodadasFase1() {
    if (rodadas == null) {
      rodadas = jogoService.getRodadas(1);
    }
    return rodadas;
  }

  @Transactional
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

}
