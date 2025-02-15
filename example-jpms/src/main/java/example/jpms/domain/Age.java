package example.jpms.domain;

public class Age {
  private final int value;

  public Age(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Age{" + "value=" + value + '}';
  }
}
