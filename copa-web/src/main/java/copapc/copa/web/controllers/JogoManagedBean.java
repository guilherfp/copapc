package copapc.copa.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;

// @ManagedBean(name = "jogoMB")
@Controller("jogoMB")
public class JogoManagedBean {

  @Autowired
  private JogoRepository jogoRepository;

  private List<Jogo> jogos;

  @Transactional
  public List<Jogo> getJogos() {
    if (jogos == null) {
      jogos = jogoRepository.jogos();
    }
    return jogos;
  }

}
