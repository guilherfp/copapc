package copapc.copa.web.controllers.adm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import copapc.model.jogador.Jogador;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;

// @Scope("request")
@Controller("golMB")
@ManagedBean(name = "golMB")
public class GolManagedBean implements Serializable {
  private static final long serialVersionUID = 1L;

  @Autowired
  private JogoRepository jogoRepository;

  private List<Jogo> jogosEmAberto;
  private List<Jogador> jogadores;
  private Jogo jogoSelecionado;
  private Jogador jogadorSelecionado;

  public List<Jogo> getJogosEmAberto() {
    if (jogosEmAberto == null) {
      jogosEmAberto = jogoRepository.jogosEmAberto();
    }
    return jogosEmAberto;
  }

  public List<Jogador> getJogadores() {
    if ((jogoSelecionado != null) && (jogadores == null)) {
      jogadores = new ArrayList<>();
      jogadores.addAll(jogoSelecionado.getMandante().getJogadores());
      jogadores.addAll(jogoSelecionado.getVisitante().getJogadores());
    } else {
      return new ArrayList<>();
    }
    return jogadores;
  }

  public void changeJogo(final AjaxBehaviorEvent event) {
    System.out.println(event.getSource());
    jogadores = null;
  }

  public Jogo getJogoSelecionado() {
    return jogoSelecionado;
  }

  public void setJogoSelecionado(Jogo jogoSelecionado) {
    this.jogoSelecionado = jogoSelecionado;
  }

  public Jogador getJogadorSelecionado() {
    return jogadorSelecionado;
  }

  public void setJogadorSelecionado(Jogador jogadorSelecionado) {
    this.jogadorSelecionado = jogadorSelecionado;
  }

  public void inserirGol() {
    System.out.println("GOOOL");
  }

}
