package copapc.service.jogador;

import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;
import copapc.model.jogador.Posicao;
import copapc.model.time.Time;

public class JogadorService {

  private JogadorRepository jogadorRepository;

  public JogadorService(JogadorRepository jogadorRepository) {
    this.jogadorRepository = jogadorRepository;
  }

  public void cadastrar(String nome, String email, int pontuacao, Posicao posicao, Time time) {
    final Jogador jogador = new Jogador(nome, email);
    jogador.setPontuacao(pontuacao);
    jogador.setPosicao(posicao);
    jogador.setTime(time);
    jogadorRepository.salvar(jogador);
  }

}
