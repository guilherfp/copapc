package copapc.model.gol;

import java.util.List;

/**
 * @author Guilherme Pacheco
 */
public interface GolRepository {

  List<Gol> gols();

  void salvar(Gol gol);

}
