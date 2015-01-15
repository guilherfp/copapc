package copapc.copa.web.controllers;

import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.model.jogador.Jogador;
import copapc.model.jogo.JogoRepository;
import copapc.service.jogador.JogadorService;

@Scope("request")
@Controller("artilhariaMB")
// @ManagedBean(name = "artilhariaMB")
public class ArtilhariaManagedBean extends AbstractManagedBean {
  private static final long serialVersionUID = 1L;

  private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();

  static {
    NUMBER_FORMAT.setMaximumFractionDigits(1);
    NUMBER_FORMAT.setMinimumFractionDigits(0);
  }

  @Autowired
  private JogadorService jogadorService;
  @Autowired
  private JogoRepository jogoRepository;

  private List<Jogador> artilherios;

  @Transactional
  public List<Jogador> getArtilheiros() {
    if (artilherios == null) {
      artilherios = jogadorService.artilheiros();
    }
    return artilherios;
  }

  public int posicao(Jogador jogador) {
    return getArtilheiros().indexOf(jogador) + 1;
  }

  @Transactional
  public String aproveitamento(Jogador jogador) {
    return NUMBER_FORMAT.format(jogador.getAproveitamento(jogoRepository));
  }

}
