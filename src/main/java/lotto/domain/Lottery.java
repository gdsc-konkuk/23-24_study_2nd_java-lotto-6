package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottery {
  private List<Lotto> values;

  public Lottery(int amount) {
    // TODO
    this.values = new ArrayList<Lotto>();
  }

  public void add(Lotto lotto) {
    // TODO
    // need validation? how about `set` rather than `list`?
    this.values.add(lotto);
  }

  public int getAmount() {
    return this.values.size();
  }

  @Override
  public String toString() {
    // TODO
    return "lottery";
  }
}
