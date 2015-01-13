package copapc.copa.web.controllers;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Comparator;
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
public class ArtilhariaManagedBean implements Serializable {
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

  final Comparator<Jogador> compareAproveitamento = new Comparator<Jogador>() {
    @Override
    public int compare(Jogador o1, Jogador o2) {
      return Double.compare(o1.getAproveitamento(jogoRepository), o2.getAproveitamento(jogoRepository));
    }
  };

  @Transactional
  public List<Jogador> getArtilheiros() {
    if (artilherios == null) {
      artilherios = jogadorService.artilheiros();
    }
    return artilherios;
  }

  @Transactional
  public String aproveitamento(Jogador jogador) {
    return NUMBER_FORMAT.format(jogador.getAproveitamento(jogoRepository));
  }

}
