package copapc.service.jogador;

import java.util.Comparator;
import java.util.List;

import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;
import copapc.model.jogador.Posicao;
import copapc.model.jogo.JogoRepository;
import copapc.model.time.Time;

public class JogadorService {

  private JogadorRepository jogadorRepository;
  private JogoRepository jogoRepository;

  public JogadorService(JogadorRepository jogadorRepository, JogoRepository jogoRepository) {
    this.jogadorRepository = jogadorRepository;
    this.jogoRepository = jogoRepository;
  }

  public void cadastrar(String nome, String email, int pontuacao, Posicao posicao, Time time) {
    final Jogador jogador = new Jogador(nome, email);
    jogador.setPosicao(posicao);
    jogador.setTime(time);
    jogadorRepository.salvar(jogador);
  }

  private final Comparator<Jogador> compareAproveitamento = new Comparator<Jogador>() {
    @Override
    public int compare(Jogador o1, Jogador o2) {
      return Double.compare(o1.getAproveitamento(jogoRepository), o2.getAproveitamento(jogoRepository));
    }
  };

  public List<Jogador> artilheiros() {
    final List<Jogador> artilheiros = jogadorRepository.jogadores();
    artilheiros.sort(compareAproveitamento.reversed());
    artilheiros.sort(Comparator.comparingInt(Jogador::getTotalDeGols).reversed());
    artilheiros.removeIf(time -> time.getTotalDeGols() == 0);
    return artilheiros;
  }

}
