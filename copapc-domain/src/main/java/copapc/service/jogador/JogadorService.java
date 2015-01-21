package copapc.service.jogador;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

  public List<Artilheiro> artilheiros() {
    final List<Jogador> jogadores = jogadorRepository.jogadores();
    jogadores.removeIf(j -> j.isPossuiGolAFavor() == false);
    final Function<Jogador, Artilheiro> mapper = j -> new Artilheiro(j, jogoRepository);
    final List<Artilheiro> artilheiros = jogadores.stream().map(mapper).collect(Collectors.toList());
    artilheiros.sort(Comparator.comparingDouble(Artilheiro::getAproveitamento).reversed());
    artilheiros.sort(Comparator.comparingDouble(Artilheiro::getTotalDeGols).reversed());
    classificar(artilheiros);
    return artilheiros;
  }

  private void classificar(final List<Artilheiro> artilheiros) {
    for (int i = 0; i < artilheiros.size(); i++) {
      final Artilheiro atual = artilheiros.get(i);
      if (i == 0) {
        atual.setPosicao(i + 1);
      } else {
        final Artilheiro anterior = artilheiros.get(i - 1);
        if (anterior.mesmaPosicao(atual)) {
          atual.setPosicao(anterior.getPosicao());
        } else {
          atual.setPosicao(anterior.getPosicao() + 1);
        }
      }
    }
  }

}
