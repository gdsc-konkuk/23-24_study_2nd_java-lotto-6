package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottery {
  private final List<Lotto> values;

  public Lottery(int amount) {
    this.values = new ArrayList<Lotto>();
    for (int i = 0; i < amount; i++) {
      this.values.add(Lotto.rand());
    }
  }

  public void add(Lotto lotto) {
    this.values.add(lotto);
  }

  public int getAmount() {
    return this.values.size();
  }

  @Override
  public String toString() {
    return this.values.stream().map(Lotto::toString).reduce("", (ret, el) -> ret + "\n" + el);
  }
}
