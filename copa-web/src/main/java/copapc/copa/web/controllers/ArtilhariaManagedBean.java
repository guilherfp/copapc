package copapc.copa.web.controllers;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;
import copapc.model.jogo.JogoRepository;

@Scope("request")
@Controller("artilhariaMB")
@ManagedBean(name = "artilhariaMB")
public class ArtilhariaManagedBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();

  static {
    NUMBER_FORMAT.setMaximumFractionDigits(1);
    NUMBER_FORMAT.setMinimumFractionDigits(0);
  }

  @Autowired
  private JogadorRepository jogadorRepository;
  @Autowired
  private JogoRepository jogoRepository;

  final Comparator<Jogador> compareAproveitamento = new Comparator<Jogador>() {
    @Override
    public int compare(Jogador o1, Jogador o2) {
      return Double.compare(o1.getAproveitamento(jogoRepository), o2.getAproveitamento(jogoRepository));
    }
  };

  @Transactional
  public List<Jogador> getArtilheiros() {
    final List<Jogador> artilheiros = jogadorRepository.artilharia();
    Collections.sort(artilheiros, compareAproveitamento.reversed());
    return artilheiros;
  }

  @Transactional
  public String aproveitamento(Jogador jogador) {
    return NUMBER_FORMAT.format(jogador.getAproveitamento(jogoRepository));
  }

}
