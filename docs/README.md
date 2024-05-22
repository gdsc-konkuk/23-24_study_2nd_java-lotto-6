## OOA

### System Sequence

```mermaid
sequenceDiagram
    actor User
    participant System

    rect rgb(0, 200, 200, 0.2)
        User ->>+ System: Buy Lottery
        System -->> User: Money Request
        loop until get proper input
            rect rgb(200, 0, 000, 0.2)
                User -->> System: Amount
                opt wrong input
                    System -->> User: Why user input is wrong
                end
            end
        end

        System -->>- User: Payment Information
    end

    rect rgb(0, 200, 200, 0.2)
        User ->>+ System: Draw
        System -->> User: WinningNumbers Request
        loop until get proper input
            rect rgb(200, 0, 000, 0.2)
                User -->> System: WinningNumbers
                opt wrong input
                    System -->> User: Why user input is wrong
                end
            end
        end

        System -->> User: BonusNumber Request
        loop until get proper input
            rect rgb(200, 0, 000, 0.2)
                User -->> System: BonusNumber
                opt wrong input
                    System -->>- User: Why user input is wrong
                end
            end
        end
    end

    rect rgb(0, 200, 200, 0.2)
        User ->>+ System: Show Result
        System -->>- User: Result
    end
```

### Domain Model

```mermaid
classDiagram
    direction RL
    Payment --* Lotto
    Draw --* WinningNumbers
    Draw --* BonusNumber
    Result --* Prize

    class Money {
        -UNIT: Integer$
        -value: Integer
    }

    class Payment {
        -MIN_AMOUNT: Money$
        -amount: Money
        -numBought: Integer
    }

    class Lotto {
        +NUM_LOWER: Integer$
        +NUM_UPPER: Integer$
        -LEN: Integer$
        -PRICE: Money$
        -numbers: Integer[]
    }

    class WinningNumbers {
        -LEN: Integer$
        -numbers: Integer[]
    }

    class BonusNumber {
        -number: Integer
    }

    class Prize {
        <<Enumeration>>
        +FIRST$
        +SECOND$
        +THIRD$
        +FOURTH$
        +FIFTH$
        +NOT_WIN$
        -reward: Money
    }

    class Result {
        -prizes: Prize[]
        -returnRate: Float
    }
```

## OOD

### Operations

```mermaid
---
title: Buy Lottery
---
sequenceDiagram
    actor User
    box System
        participant Application
        participant Money
        participant Payment
        participant Lotto
    end

    User ->>+ Application: Buy Lottery
    rect rgb(0, 200, 200, 0.2)
        loop until get proper input
            rect rgb(200, 0, 0, 0.2)
                Application ->>+ Money: Money.fromUser()
                Money -->> User: 금액을 입력해 주세요.
                User -->> Money: String

                alt If not an integer
                    Money -->> User: [ERROR] 금액은 숫자여야 합니다.
                else If not divisible by 1,000
                    Money -->> User: [ERROR] 금액 단위는 1,000원입니다.
                end

                Money -->>- Application: Money
                Application ->>+ Payment: Payment.new(amount: Money)
                Payment ->>+ Money: gte(other: Money)
                Money -->>- Payment: Boolean
                alt If under minimum amount
                    Payment -->> User: [ERROR] 최소 주문 금액은 1000원입니다.
                end
            end
        end

        Lotto -->> Payment: Lotto.PRICE
        Payment ->>+ Money: divide(other: Money)
        Money -->>- Payment: Float

        loop num bought
            rect rgb(200, 0, 000, 0.2)
                Payment ->>+ Lotto: Lotto.new(numbers: Integer[])
                Lotto -->>- Payment: Lotto
            end
        end

        Payment -->>- Application: Payment
    end

    rect rgb(0, 200, 200, 0.2)
        Application ->>+ Payment: toString()

        loop num bought
            rect rgb(200, 0, 000, 0.2)
                Payment ->>+ Lotto: toString()
                Lotto -->>- Payment: String
            end
        end

        Payment -->>- Application: String
    end
    Application -->>- User: String
```

```mermaid
---
title: Draw
---
sequenceDiagram
    actor User
    box System
        participant Application
        participant WinningNumbers
        participant BonusNumber
        participant Draw
        participant Lotto
    end

    User ->>+ Application: Draw
    rect rgb(0, 200, 200, 0.2)
        Application ->>+ WinningNumbers: WinningNumbers.fromUser()

        loop until get proper input
            rect rgb(200, 0, 0, 0.2)
                WinningNumbers -->> User: 당첨 번호를 입력해 주세요.
                User -->> WinningNumbers: String
                Lotto -->> WinningNumbers: Lotto.NUM_LOWER
                Lotto -->> WinningNumbers: Lotto.NUM_UPPER

                alt If it can't be parsed as a list of integers
                    WinningNumbers -->> User: [ERROR] 당첨 번호는 숫자 배열이어야 합니다. ex) 1,2,3,4
                else If out of range
                    WinningNumbers -->> User: [ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.
                else If the length is incorrect
                    WinningNumbers -->> User: [ERROR] 당첨 번호는 6개의 숫자로 이뤄져야 합니다.
                else If duplicated
                    WinningNumbers -->> User: [ERROR] 당첨 번호는 중복이 없어야 합니다.
                end

            end
        end

        WinningNumbers -->>- Application: WinningNumbers

        loop until get proper input
            rect rgb(200, 0, 0, 0.2)
                Application ->>+ BonusNumber: BonusNumber.fromUser()
                BonusNumber -->> User: 보너스 번호를 입력해 주세요.
                User -->> BonusNumber: String
                Lotto -->> BonusNumber: Lotto.NUM_LOWER
                Lotto -->> BonusNumber: Lotto.NUM_UPPER

                alt If not an integer
                    BonusNumber -->> User: [ERROR] 보너스 번호는 숫자여야 합니다.
                else If out of range
                    BonusNumber -->> User: [ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.
                end

                BonusNumber -->>- Application: BonusNumber
                Application ->>+ Draw: new(winningNumbers: WinningNumbers, bonusNumber: BonusNumber)
                Draw ->>+ WinningNumbers: overlap(bonusNumber: BonusNumber)
                WinningNumbers ->>+ BonusNumber: equals(number: Integer)
                BonusNumber -->>- WinningNumbers: Boolean
                WinningNumbers -->>- Draw: Boolean
                alt If overlap with winning nums
                    Draw -->> User: [ERROR] 보너스 번호는 당첨 번호와 겹치지 않아야 합니다.
                end
            end
        end

        Draw -->>- Application: Draw
    end

    deactivate Application
```

```mermaid
---
title: Show Result
---
sequenceDiagram
    actor User
    box System
        participant Application
        participant Result
        participant Payment
        participant Draw
        participant WinningNumbers
        participant BonusNumber
        participant Lotto
        participant Money
        participant Prize
    end

    User ->>+ Application: Show Result
    rect rgb(0, 200, 200, 0.2)
        Application ->>+ Result: Result.new(draw: Draw, payment: Payment)
        Result ->>+ Payment: getPrizes(draw: Draw)

        loop num bought
            rect rgb(200, 0, 0, 0.2)
                Payment ->>+ Draw: compare(lotto: Lotto)
                Draw ->>+ WinningNumbers: compare(lotto: Lotto)

                loop winning numbers
                    WinningNumbers ->>+ Lotto: has(number: Integer)
                    Lotto -->>- WinningNumbers: Boolean
                end

                WinningNumbers -->>- Draw: Integer
                Draw ->>+ BonusNumber: belonged(lotto: Lotto)
                BonusNumber ->>+ Lotto: has(number: Integer)
                Lotto -->>- BonusNumber: Boolean
                BonusNumber -->>- Draw: Boolean
                Draw -->>- Payment: Prize
            end
        end

        Payment -->>- Result: Prize[]
        Result ->>+ Payment: getAmount()
        Payment -->>- Result: Money
        rect rgb(200, 0, 0, 0.2)
            loop prizes
                Result ->>+ Prize: getReward()
                Prize -->>- Result: Money
            end
        end

        Result ->>+ Money: Money.total(sources: Money[])
        Money -->>- Result: Money
        Result ->>+ Money: divide(other: Money)
        Money -->>- Result: Float
        Result -->>- Application: Result
    end

    rect rgb(0, 200, 200, 0.2)
        Application ->>+ Result: toString()
        rect rgb(200, 0, 0, 0.2)
            loop Prize.values
                Result ->>+ Prize: toString()
                Prize -->>- Result: String
            end
        end
        Result -->>- Application: String
    end

    Application -->>- User: String
```

### Design Model

```mermaid
classDiagram
    direction RL
    Payment --* Lotto
    Draw --* WinningNumbers
    Draw --* BonusNumber
    Result --* Prize

    class Money {
        -UNIT: Integer$
        -value: Integer
        +fromUser() Money$
        +total(sources: Money[]) Money$
        +new(value: Integer) Money$
        +divide(other: Money) Float
        +gte(other: Money) Boolean
        -validate(value: Integer) void
    }

    class Payment {
        -MIN_AMOUNT: Money$
        -amount: Money
        -numBought: Integer
        +new(amount: Money) Payment$
        +getAmount() Money
        +getPrizes(draw: Draw) Prize[]
        +toString() String
        -validate(amount: Money) void
        -issueLottery(numBought: Integer) void
    }

    class Lotto {
        +NUM_LOWER: Integer$
        +NUM_UPPER: Integer$
        -LEN: Integer$
        -PRICE: Money$
        -numbers: Integer[]
        +new(numbers: Integer[]) Lotto
        +has(number: Integer) Boolean
        +toString() String
        -validate(numbers: Integer[]) void
    }

    class Draw {
        +new(winningNumbers: WinningNumbers, bonusNumber: BonusNumber) Draw$
        +compare(lotto: Lotto) Prize
        -validate(winningNumbers: WinningNumbers, bonusNumber: BonusNumber) void
    }

    class WinningNumbers {
        -LEN: Integer$
        -numbers: Integer[]
        +fromUser() WinningNumbers$
        +new(numbers: Integer[]) WinningNumbers
        +overlap(bonusNumber: BonusNumber) Boolean
        +compare(lotto: Lotto) Integer
        -validate(numbers: Integer[]) void
    }

    class BonusNumber {
        -number: Integer
        +fromUser() BonusNumber$
        +new(number: Integer) BonusNumber
        +belonged(lotto: Lotto) Boolean
        +equals(number: Integer) Boolean
        -validate(number: Integer) void
    }

    class Prize {
        <<Enumeration>>
        +FIRST$
        +SECOND$
        +THIRD$
        +FOURTH$
        +FIFTH$
        +NOT_WIN$
        -reward: Money
        +from(winningCount: Integer, hitBonus: Boolean) Prize$
        +new(reward: Integer) Prize$
        +getReward() Money
        +toString() String
    }

    class Result {
        -prizes: Prize[]
        -returnRate: Float
        +new(draw: Draw, payment: Payment) Result$
        +toString() String
        -calcReturnRate(rewards: Money[], principalAmount: Money) Float
    }
```
