package copapc.model.jogo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import copapc.model.gol.Gol;
import copapc.model.jogador.Jogador;
import copapc.model.time.Time;

public class JogoTest {

  private Jogo jogo;
  private Time mandante = new Time(1);
  private Time visitante = new Time(2);
  private List<Jogador> jogadoresMandante = new ArrayList<>();
  private List<Jogador> jogadoresVisitante = new ArrayList<>();

  @Before
  public void setUp() throws Exception {
    jogo = new Jogo(1, mandante, visitante);
    for (int i = 0; i < 5; i++) {
      Jogador jogadorMandante = new Jogador("Nome " + i, i + "@email.com");
      jogadoresMandante.add(jogadorMandante);
      mandante.adicionarJogador(jogadorMandante);
      Jogador jogadorVisitante = new Jogador("Nome " + (i * 2), (i * 2) + "@email.com");
      jogadoresVisitante.add(jogadorVisitante);
      visitante.adicionarJogador(jogadorVisitante);
    }
  }

  @Test
  public void testAdicionarGol() throws Exception {
    assertEquals(0, jogo.getTotalDeGols());
    jogo.adicionarGol(Gol.golAFavor(jogadoresMandante.get(0), jogo, 1));
    assertEquals(1, jogo.getTotalDeGols());
    assertEquals(mandante, jogo.getVencedor());
    assertEquals(1, jogo.getTotalDeGolsDoMandante());
    assertEquals(0, jogo.getTotalDeGolsDoVisitante());

    jogo.adicionarGol(Gol.golAFavor(jogadoresVisitante.get(0), jogo, 1));
    assertEquals(2, jogo.getTotalDeGols());
    assertEquals(null, jogo.getVencedor());
    assertEquals(1, jogo.getTotalDeGolsDoMandante());
    assertEquals(1, jogo.getTotalDeGolsDoVisitante());

    jogo.adicionarGol(Gol.golAFavor(jogadoresVisitante.get(0), jogo, 2));
    assertEquals(3, jogo.getTotalDeGols());
    assertEquals(visitante, jogo.getVencedor());
    assertEquals(1, jogo.getTotalDeGolsDoMandante());
    assertEquals(2, jogo.getTotalDeGolsDoVisitante());
  }

  @Test
  public void testAdicionarGolContra() throws Exception {
    assertEquals(0, jogo.getTotalDeGols());
    Jogador jogadorMandante = jogadoresMandante.get(0);
    jogo.adicionarGol(Gol.golContra(jogadorMandante, jogo, 3));
    assertEquals(1, jogo.getTotalDeGols());
    assertEquals(visitante, jogo.getVencedor());
    assertEquals(0, jogo.getTotalDeGolsDoMandante());
    assertEquals(1, jogo.getTotalDeGolsDoVisitante());
    assertEquals(0, jogadorMandante.getTotalDeGols());

    Jogador jogadorVisitante = jogadoresVisitante.get(0);
    jogo.adicionarGol(Gol.golAFavor(jogadorVisitante, jogo, 4));
    assertEquals(2, jogo.getTotalDeGols());
    assertEquals(visitante, jogo.getVencedor());
    assertEquals(0, jogo.getTotalDeGolsDoMandante());
    assertEquals(2, jogo.getTotalDeGolsDoVisitante());
  }
}
