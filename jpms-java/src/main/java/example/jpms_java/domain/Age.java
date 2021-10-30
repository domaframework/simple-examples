package example.jpms_java.domain;

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
