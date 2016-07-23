package copapc.shared;

/**
 * @author Guilherme Pacheco
 */
public abstract class Entity {

  private int id;

  final int id() {
    return id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Entity other = (Entity) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
