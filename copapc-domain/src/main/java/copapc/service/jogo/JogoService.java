package copapc.service.jogo;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import copapc.model.gol.Gol;
import copapc.model.gol.GolRepository;
import copapc.model.jogador.Cartao;
import copapc.model.jogador.Jogador;
import copapc.model.jogo.Jogo;
import copapc.model.jogo.JogoRepository;

public class JogoService {

  private final GolRepository golRepository;
  private final JogoRepository jogoRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(JogoService.class);

  public JogoService(GolRepository golRepository, JogoRepository jogoRepository) {
    this.golRepository = golRepository;
    this.jogoRepository = jogoRepository;
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
    jogo.adicionarCartao(jogador, cartao);
    jogoRepository.atualizar(jogo);
  }

}
