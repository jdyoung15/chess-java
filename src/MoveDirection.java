public class MoveDirection {

  private Direction direction;
  private int limit;
  private boolean attackOnly;

  public MoveDirection(Direction direction, int limit, boolean attackOnly) {
    this.direction = direction;
    this.limit = limit;
    this.attackOnly = attackOnly;
  }

  public Direction getDirection() {
    return direction;
  }

  public int getLimit() {
    return limit;
  }

  public boolean getAttackOnly() {
    return attackOnly;
  }
}
