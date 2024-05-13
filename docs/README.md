## OOA

### System Sequence

```mermaid
sequenceDiagram
    actor User
    participant System

    rect rgb(0, 200, 200, 0.2)
        User ->>+ System: buyLottery
        System -->> User: Money Request
        loop until get proper input
            rect rgb(200, 0, 000, 0.2)
                User -->> System: Money
            end
            opt wrong input
                System -->> User: Why user input is wrong
            end
        end

        System -->>- User: Payment Information
    end

    rect rgb(0, 200, 200, 0.2)
        User ->>+ System: draw
        System -->> User: WinningNumbers Request
        loop until get proper input
            rect rgb(200, 0, 000, 0.2)
                User -->> System: WinningNumbers
            end
            opt wrong input
                System -->> User: Why user input is wrong
            end
        end

        System -->> User: BonusNumber Request
        loop until get proper input
            rect rgb(200, 0, 000, 0.2)
                User -->> System: BonusNumber
            end
            opt wrong input
                System -->> User: Why user input is wrong
            end
            deactivate System
        end
    end

    rect rgb(0, 200, 200, 0.2)
        User ->>+ System: showPrize
        System -->>- User: Prize
    end
```

### Domain Model

```mermaid
classDiagram
    direction RL
    Payment --> Money
    Payment --> Draw
    Payment --o Lottery
    Lottery --* Lotto
    Lotto --* Result
    Payment --* Prize

    class Money {
        -MIN_VALUE: Integer$
        -UNIT: Integer$
        -value: Integer
    }

    class Payment {
        -amount: Money
    }

    class Lottery {
        -amount: Integer
    }

    class Lotto {
        -PRICE: Money$
        -NUM_LOWER: Integer$
        -NUM_UPPER: Integer$
        -LEN: Integer$
        -numbers: Integer[]
    }

    class Draw {
        -BONUS_LEN: Integer$
        -WINNING_LEN: Integer$
        -winningNumbers: Integer[]
        -bonuseNumbers: Integer[]
    }

    class Result {
        <<Enumeration>>
        +FIRST_PRIZE$
        +SECOND_AND_BONUSE_PRIZE$
        +SECOND_PRIZE$
        +THIRD_PRIZE$
        +FOURTH_PRIZE$
        +FIFTH_PRIZE$
        -reward: Integer
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
        participant Money
        participant Application
        participant Payment
        participant Lottery
        participant Lotto
    end

    User ->>+ Application: Buy Lotto
    rect rgb(0, 200, 200, 0.2)
        Application ->>+ Money: Money.fromUser(): Money

        rect rgb(200, 0, 0, 0.2)
            loop until get proper input
                Money -->> User: 구입금액을 입력해 주세요.
                User -->> Money: String
                Money ->> Money: parse(input: String): Integer

                alt If not an integer
                    Money -->> User: [ERROR] 구입금액은 숫자여야 합니다.
                else If out of range
                    Money -->> User: [ERROR] 구입금액은 1,000 이상의 숫자여야 합니다.
                else If not divisible by 1,000
                    Money -->> User: [ERROR] 구입금액의 기본 단위는 1,000원 입니다.
                end

                break
                    Money -->> Money: Integer
                end
            end
        end
        Money ->> Money: new(value: Integer): Money
        Money -->>- Application: Money
    end

    rect rgb(0, 200, 200, 0.2)
        Application ->>+ Payment: Payment.new(amount: Money): Payment
        Lotto -->> Payment: Lotto.PRICE:Money
        Payment ->>+ Money: divide(other: Money): Integer
        Money -->>- Payment: Integer
        Payment ->>+ Lottery: Lottery.new(amount: Integer): Lottery

        loop amount
            rect rgb(200, 0, 000, 0.2)
                Lottery ->>+ Lotto: Lotto.rand(): Lotto
                Lotto -->>- Lottery: Lotto
            end
            Lottery ->> Lottery: add(lotto: Lotto): void
        end

        Lottery -->>- Payment: Lottery
        Payment -->>- Application: Payment
    end

    rect rgb(0, 200, 200, 0.2)
        Application ->>+ Payment: toString(): String
        Payment ->>+ Lottery: toString(): String
        loop amount
            rect rgb(200, 0, 000, 0.2)
                Lottery ->>+ Lotto: toString(): String
                Lotto -->>- Lottery: String
            end
        end
        Lottery -->>- Payment: String
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
        participant Draw
        participant Application
    end

    User ->>+ Application: Draw
    rect rgb(0, 200, 200, 0.2)
        Application ->>+ Draw: Draw.fromUser(): Draw

        loop until get proper input
            rect rgb(200, 0, 0, 0.2)
                Draw -->> User: 당첨 번호를 입력해 주세요.
                User -->> Draw: String
                Draw ->> Draw: parse(input: String): Integer[]

                alt If it can't be parsed as a list of integers
                    Draw -->> User: [ERROR] 당첨 번호는 숫자 배열이어야 합니다. ex) 1,2,3,4
                else If out of range
                    Draw -->> User: [ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.
                else If the length is incorrect
                    Draw -->> User: [ERROR] 당첨 번호는 6개의 숫자로 이뤄져야 합니다.
                end

                break
                    Draw -->> Draw: Integer[]
                end
            end
        end

        loop until get proper input
            rect rgb(200, 0, 0, 0.2)
                Draw -->> User: 보너스 번호를 입력해 주세요.
                User -->> Draw: String
                Draw ->> Draw: parse(input: String): Integer[]

                alt If it can't be parsed as a list of integers
                    Draw -->> User: [ERROR] 보너스 번호는 숫자 배열이어야 합니다. ex) 1,2,3,4
                else If out of range
                    Draw -->> User: [ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.
                else If the length is incorrect
                    Draw -->> User: [ERROR] 보너스 번호는 1개의 숫자로 이뤄져야 합니다.
                end

                break
                    Draw -->> Draw: Integer[]
                end
            end
        end

        Draw ->> Draw: Draw.new(winningNumbers: Integer[], bonusNumbers: Integer[]): Draw
        Draw -->>- Application: Draw
    end

    deactivate Application
```

```mermaid
---
title: Show Prize
---
sequenceDiagram
    actor User
    box System
        participant Application
        participant Payment
        participant Lottery
        participant Lotto
        participant Prize
    end

    User ->>+ Application: Show Prize
    rect rgb(0, 200, 200, 0.2)
        Application ->>+ Payment: getPrize(draw: Draw): Prize
        Payment ->>+ Lottery: getResults(draw: Draw): Result[]

        rect rgb(200, 0, 0, 0.2)
            loop lottery
                Lottery ->>+ Lotto: getResult(draw: Draw): Result
                Lotto -->>- Lottery: Result
            end
        end
        Lottery -->>- Payment: Result[]

        rect rgb(200, 0, 0, 0.2)
            Payment ->>+ Prize: Prize.new(results: Result[]): Prize
            Prize -->>- Payment: Prize
        end
        Payment -->>- Application: Prize

        rect rgb(200, 0, 0, 0.2)
            Application ->>+ Prize: toString(): String
            Prize -->>- Application: String
        end
    end
    Application -->>- User: String
```

### Design Model

```mermaid
classDiagram
    direction RL
    Application --> Money
    Application --> Draw
    Application --> Payment
    Payment --o Lottery
    Lottery --* Lotto
    Lotto --* Result
    Payment --* Prize

    class Application {
    }

    class Money {
        -MIN_VALUE: Integer$
        -UNIT: Integer$
        -value: Integer
        +fromUser() Money$
        +parse(input: String) Integer$
        -new(value: Integer) Money
        +divide(other: Money) Integer
    }

    class Payment {
        -amount: Money
        +new(amount: Money) Payment$
        +toString() String
        +getPrize(draw: Draw) Prize
    }

    class Lottery {
        -amount: Integer
        +new(amount: Integer) Lottery$
        +add(lotto: Lotto) void
        +toString() String
        +getResults(draw: Draw) Result[]
    }

    class Lotto {
        -PRICE: Money$
        -NUM_LOWER: Integer$
        -NUM_UPPER: Integer$
        -LEN: Integer$
        -numbers: Integer[]
        +rand() Lotto$
        +toString() String
        +getResult(draw: Draw) Result
    }

    class Draw {
        -BONUS_LEN: Integer$
        -WINNING_LEN: Integer$
        -winningNumbers: Integer[]
        -bonuseNumbers: Integer[]
        +fromUser() Draw$
        +parse(input: String) Integer[]$
        -new(winningNumbers: Integer[], bonusNumbers: Integer[]) Draw$
    }

    class Result {
        <<Enumeration>>
        +FIRST_PRIZE$
        +SECOND_AND_BONUSE_PRIZE$
        +SECOND_PRIZE$
        +THIRD_PRIZE$
        +FOURTH_PRIZE$
        +FIFTH_PRIZE$
        -reward: Integer
    }

    class Prize {
        +new(results: Result[]) Prize$
        +toString() String
    }
```
