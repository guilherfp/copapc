package copapc.service.jogo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import copapc.model.gol.Gol;
import copapc.model.gol.GolRepository;
import copapc.model.jogador.Cartao;
import copapc.model.jogador.Jogador;
import copapc.model.jogo.CartaoDoJogo;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;
import copapc.model.time.TimeRepository;

public class JogoService {

  private final GolRepository golRepository;
  private final JogoRepository jogoRepository;
  private final TimeRepository timeRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(JogoService.class);

  public JogoService(GolRepository golRepository, JogoRepository jogoRepository, TimeRepository timeRepository) {
    this.jogoRepository = jogoRepository;
    this.timeRepository = timeRepository;
    this.golRepository = golRepository;
  }

  public void marcarGol(final Jogador jogador, final Jogo jogo) {
    final Gol gol = Gol.gol(jogador, jogo);
    jogo.adicionarGol(gol);
    jogador.adicionarGol(gol);
    validarInicializacaoDoJogo(jogo);
    golRepository.salvar(gol);
    LOGGER.info("Gol marcado: {}", gol);
  }

  public void marcarGolContra(final Jogador jogador, final Jogo jogo) {
    final Gol gol = Gol.golContra(jogador, jogo);
    jogo.adicionarGol(gol);
    jogador.adicionarGol(gol);
    validarInicializacaoDoJogo(jogo);
    golRepository.salvar(gol);
    LOGGER.info("Gol marcado: {}", gol);
  }

  public void iniciarJogo(Jogo jogo) {
    Validate.isTrue(jogo.isIniciado() == false, "Jogo já foi iniciado");
    Validate.isTrue(jogo.isEncerrado() == false, "Jogo já foi encerrado");
    jogo.iniciar();
    jogoRepository.atualizar(jogo);
    LOGGER.info("Jogo iniciado: {}", jogo);
  }

  public void validarInicializacaoDoJogo(Jogo jogo) {
    if (jogo.isIniciado() == false) {
      jogo.iniciarNoHorario();
    }
  }

  public void encerrarJogo(Jogo jogo) {
    Validate.isTrue(jogo.isEncerrado() == false, "Jogo já foi encerrado");
    Validate.isTrue(jogo.isIniciado(), "O Jogo não iniciado");
    jogo.encerrar();
    jogoRepository.atualizar(jogo);
    LOGGER.info("Jogo encerrado: {}", jogo);
  }

  public void adicionarCartao(Jogo jogo, Jogador jogador, Cartao cartao) {
    validarInicializacaoDoJogo(jogo);
    Validate.isTrue(jogador.isSuspenso() == false, "Jogador está suspenso");
    if (jogo.getCartoesDoJogador(jogador).contains(Cartao.AMARELO)) {
      jogador.setCartao(Cartao.VERMELHO);
      cartao = Cartao.VERMELHO;
    } else if (jogador.getCartao() == null) {
      jogador.setCartao(Cartao.AMARELO_1);
    } else if (jogador.getCartao().equals(Cartao.AMARELO_1)) {
      jogador.setCartao(Cartao.AMARELO_2);
    } else if (cartao.equals(Cartao.VERMELHO)) {
      jogador.setCartao(Cartao.VERMELHO);
    }
    final CartaoDoJogo cartaoDoJogo = new CartaoDoJogo(cartao, jogo, jogador);
    jogoRepository.salvarCartao(cartaoDoJogo);
    LOGGER.info("Cartão marcado: {}", cartaoDoJogo);
  }

  public List<Rodada> getRodadas(int fase) {
    final List<Jogo> jogos = jogoRepository.jogosPorFase(fase);
    final int fases = jogos.size() / timeRepository.quantidadeDeGrupos();
    final List<Rodada> rodadas = new ArrayList<>();
    for (int i = 0; i < fases; i++) {
      int end = 0 + fases;
      if (end > jogos.size()) {
        end = jogos.size() - 1;
      }
      rodadas.add(new Rodada(i + 1, jogos.subList(end * i, (end * i) + end)));
    }
    return rodadas;
  }

  public boolean faseFinalizada(int fase) {
    final List<Jogo> jogos = jogoRepository.jogosPorFase(fase);
    return jogos.stream().allMatch(Jogo::isEncerrado);
  }

}
