package copapc.model.time;

import java.util.List;

/**
 * @author Guilherme Pacheco
 */
public interface TimeRepository {

  List<Time> times();

  List<Time> timesPorGrupo(char grupo);

  Time comNumero(int numero);

  Time comURL(String url);

  int quantidadeDeGrupos();

}
