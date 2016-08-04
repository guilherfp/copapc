package copapc.web.rest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import copapc.domain.jogador.Jogador;
import copapc.domain.jogador.JogadorRepository;
import copapc.domain.jogo.Jogo;
import copapc.domain.jogo.JogoRepository;
import copapc.domain.jogo.JogoService;
import copapc.web.dto.CartaoDto;
import copapc.web.dto.GolDto;
import copapc.web.dto.JogadorDto;
import copapc.web.dto.JogoDto;

/**
 * @author Guilherme Pacheco
 */
@Service
@Transactional
public class JogoApplication {

  @Autowired
  private JogadorRepository jogadorRepository;
  @Autowired
  private JogoRepository jogoRepository;
  @Autowired
  private JogoService jogoService;

  public List<JogadorDto> jogadoresDoJogo(int numero) {
    Jogo jogo = jogoRepository.jogo(numero);
    return jogo != null ? jogadoresNaoSuspenso(jogo) : Collections.emptyList();
  }

  private List<JogadorDto> jogadoresNaoSuspenso(Jogo jogo) {
    List<Jogador> jogadores = jogo.getJogadoresNaoSuspensos();
    return jogadores.stream().map(JogadorDto::new).collect(Collectors.toList());
  }

  public List<JogoDto> jogosAbertos() {
    return jogoRepository.jogosEmAberto().stream().map(JogoDto::new).collect(Collectors.toList());
  }

  public void adicionarGol(GolDto dto) {
    Jogador jogador = jogador(dto.getJogador());
    Jogo jogo = jogo(dto.getJogo());
    if (!dto.isContra()) {
      jogoService.marcarGol(jogador, jogo, dto.getMinuto());
    } else {
      jogoService.marcarGolContra(jogador, jogo, dto.getMinuto());
    }
  }

  private Jogador jogador(String email) {
    Jogador jogador = jogadorRepository.comEmail(email);
    Validate.notNull(jogador, "Jogador inválido");
    return jogador;
  }

  public void aplicarCartao(CartaoDto dto) {
    Jogo jogo = jogo(dto.getJogo());
    Jogador jogador = jogador(dto.getJogador());
    jogoService.adicionarCartao(jogo, jogador, dto.getCartao(), dto.getMinuto());
  }

  public void iniciarJogo(int numeroDoJogo) {
    Jogo jogo = jogo(numeroDoJogo);
    jogoService.iniciarJogo(jogo);
  }

  public void iniciarSegundoTempo(int numeroDoJogo) {
    Jogo jogo = jogo(numeroDoJogo);
    jogoService.segundoTempo(jogo);
  }

  public void encerrarJogo(int numeroDoJogo) {
    Jogo jogo = jogo(numeroDoJogo);
    jogoService.encerrarJogo(jogo);
  }

  private Jogo jogo(int numero) {
    Jogo jogo = jogoRepository.jogo(numero);
    Validate.notNull(jogo, "Jogo inválido");
    return jogo;
  }

}
