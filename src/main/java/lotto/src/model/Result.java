package lotto.src.model;

import java.util.List;

public class Result {
    private final List<List<Integer>> lottos;
    private final List<Integer> winningNumber;
    private final Integer bonusNumber;

    public Result(List<List<Integer>> lottos, List<Integer> winningNumber, Integer bonusNumber) {
        this.lottos = lottos;
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }
}
