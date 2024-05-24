package lotto;

public enum Score {
    NONE(-1, false, 0),
    ZERO(0, false, 0),
    ONE(1, false, 0),
    TWO(2, false, 0),
    THREE(3, false, 5000),
    FOUR(4, false, 50000),
    FIVE(5, false, 1500000),
    FIVE_WITH_BONUS(5, true, 30000000),
    SIX(6, false, 2000000000);

    private final int match;
    private final boolean bonusMatch;
    private final int prize;

    Score(int match, boolean bonusMatch, int prize) {
        this.match = match;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
    }

    public int getMatch() {
        return match;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    public int getPrize() {
        return prize;
    }
}

