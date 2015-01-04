package copapc.model.time;

public class TimeBuilder {

  public static Time timeNaoDefinido(int numero) {
    return new Time(numero, "Não Definido");
  }

}
