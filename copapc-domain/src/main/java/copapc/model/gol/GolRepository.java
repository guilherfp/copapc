package copapc.model.gol;

import java.util.List;

public interface GolRepository {

  List<Gol> gols();

  void salvar(Gol gol);

}
