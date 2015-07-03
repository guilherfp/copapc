package copapc.copa.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import copapc.copa.web.dto.CartaoDTO;
import copapc.copa.web.dto.GolDTO;
import copapc.copa.web.dto.JogadorDTO;
import copapc.copa.web.dto.JogoDTO;
import copapc.model.jogador.Jogador;
import copapc.model.jogador.JogadorRepository;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.service.jogo.JogoService;

/**
 * @author Guilherme Pacheco
 */
@Service
@Transactional
public class JogoServiceApplication {

  @Autowired
  private JogadorRepository jogadorRepository;
  @Autowired
  private JogoRepository jogoRepository;
  @Autowired
  private JogoService jogoService;

  public List<JogadorDTO> jogadoresDoJogo(int numero) {
    Jogo jogo = jogoRepository.jogoComNumero(numero);
    if (jogo != null) {
      Predicate<Jogador> naoSuspensos = j -> !j.isSuspenso();
      Stream<Jogador> jogadores = jogo.getJogadores().stream().filter(naoSuspensos);
      return jogadores.map(JogadorDTO::new).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  public List<JogoDTO> jogosAbertos() {
    return jogoRepository.jogosEmAberto().stream().map(JogoDTO::new).collect(Collectors.toList());
  }

  public void adicionarGol(GolDTO golDTO) {
    Jogador jogador = jogadorRepository.comEmail(golDTO.getJogador());
    Validate.notNull(jogador, "Jogador inválido");
    Jogo jogo = jogoRepository.jogoComNumero(golDTO.getJogo());
    Validate.notNull(jogo, "Jogo inválido");
    if (golDTO.isContra() == false) {
      jogoService.marcarGol(jogador, jogo, golDTO.getMinuto());
    } else {
      jogoService.marcarGolContra(jogador, jogo, golDTO.getMinuto());
    }
  }

  public void aplicarCartao(CartaoDTO cartaoDTO) {
    Jogador jogador = jogadorRepository.comEmail(cartaoDTO.getJogador());
    Validate.notNull(jogador, "Jogador inválido");
    Jogo jogo = jogoRepository.jogoComNumero(cartaoDTO.getJogo());
    Validate.notNull(jogo, "Jogo inválido");
    jogoService.adicionarCartao(jogo, jogador, cartaoDTO.getCartao(), cartaoDTO.getMinuto());
  }

  public void iniciarJogo(int numeroDoJogo) {
    Jogo jogo = jogoRepository.jogoComNumero(numeroDoJogo);
    Validate.notNull(jogo, "Jogo inválido");
    jogoService.iniciarJogo(jogo);
  }

  public void iniciarSegundoTempo(int numeroDoJogo) {
    Jogo jogo = jogoRepository.jogoComNumero(numeroDoJogo);
    Validate.notNull(jogo, "Jogo inválido");
    jogoService.segundoTempo(jogo);
  }

  public void encerrarJogo(int numeroDoJogo) {
    Jogo jogo = jogoRepository.jogoComNumero(numeroDoJogo);
    Validate.notNull(jogo, "Jogo inválido");
    jogoService.encerrarJogo(jogo);
  }

}
