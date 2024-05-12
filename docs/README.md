## OOA

### System Sequence

```mermaid
sequenceDiagram
    actor User
    participant System

    rect rgb(0, 200, 200, 0.2)
        User ->>+ System: buyLottery

        rect rgb(200, 0, 200, 0.2)
            System -->> User: Money Request
            loop until get proper input
                User -->> System: Money
                opt wrong input
                    System -->> User: Why user input is wrong
                end
            end
        end

        System -->>- User: Payment Information
    end

    rect rgb(0, 200, 200, 0.2)
        User ->>+ System: draw

        rect rgb(200, 0, 200, 0.2)
            System -->> User: WinningNumbers Request
            loop until get proper input
                User -->> System: WinningNumbers
                opt wrong input
                    System -->> User: Why user input is wrong
                end
            end
        end

        rect rgb(200, 0, 200, 0.2)
            System -->> User: BonusNumber Request
            loop until get proper input
                User -->> System: BonusNumber
                opt wrong input
                    System -->> User: Why user input is wrong
                end
            end
            deactivate System
        end
    end

    rect rgb(0, 200, 200, 0.2)
        User ->>+ System: showResult
        System -->>- User: Result
    end
```

### Domain Model

```mermaid
classDiagram
    Application --* Payment
    Application --* LottoResult
    Payment --o Lotteries
    Payment --> WinningResults
    LottoResult ..> Lotto
    Lotteries --* Lotto
    WinningResults --* WinningResult

    class Application {
        -payment: Payment
        -lottoResult: LottoResult
    }

    class Payment {
        -price: Integer$
        -money: Integer
        -lotteries: Lotteries
    }

    class Lotto {
        -rangeStart: Integer$
        -rangeEnd: Integer$
        -numOfNumbers: Integer$
        -numbers: List<Integer>
    }

    class Lotteries {
        -lotteries: List<Lotto>
    }

    class LottoResult {
        -numOfBonuseNumbers: Integer$
        -winningNumbers: List<Integer>
        -bonuseNumbers: List<Integer>
    }

    class WinningResult {
        <<Enumeration>>
        -reward: Integer
        +FIRST_PRIZE$
        +SECOND_AND_BONUSE_PRIZE$
        +SECOND_PRIZE$
        +THIRD_PRIZE$
        +FOURTH_PRIZE$
        +FIFTH_PRIZE$
    }

    class WinningResults {
        -lottoResults: List<WinningResult>
    }
```

## OOD

### Operations

```mermaid
---
title: Purchase Lotteries
---
sequenceDiagram
    box System
        actor Application
        participant Payment
        participant Lotteries
        participant Lotto
    end
    participant User
    Application ->>+ Payment: Payment.fromUser(): Payment

    loop until get proper input
        rect rgb(0, 200, 200)
            Payment -->> User: 구입금액을 입력해 주세요.
            Payment ->>+ User: getMoney(): Integer
            User -->>- Payment: Money
        end

        alt If not an integer
            Payment -->> User: [ERROR] 구입금액은 숫자여야 합니다.
        else If out of range
            Payment -->> User: [ERROR] 구입금액은 1,000 이상의 숫자여야 합니다.
        else If not divisible by 1,000
            Payment -->> User: [ERROR] 구입금액의 기본 단위는 1,000원 입니다.
        end
    end

    Payment ->>+ Lotteries: Lotteries(amount): Lotteries
    loop amount
        rect rgb(0, 200, 200)
            Lotteries ->>+ Lotto: Lotto.rand(): Lotto
            Lotto -->>- Lotteries: Lotto
            Lotteries ->> Lotteries: add(lotto): void
        end
        Lotteries -->>- Payment: Lotteries
    end

    rect rgb(0, 200, 200)
        Payment -->> User: x개를 구매했습니다.
        Payment ->>+ Lotteries: showAll(): void
        loop amount
            Lotteries -->>- User: [a, b, c, d, e, f]
        end
    end

    Payment -->>- Application: Payment
```

```mermaid
---
title: Get Lotto Result
---
sequenceDiagram
    box System
        actor Application
        participant LottoResult
    end
    participant User
    Application ->>+ LottoResult: LottoResult.fromUser(): LottoResult

    loop until get proper input
        rect rgb(0, 200, 200)
            LottoResult -->> User: 당첨 번호를 입력해 주세요.
            LottoResult ->>+ User: getWinningNumbers(): String
            User -->>- LottoResult: WinningNumbers
        end

        alt If it can't be parsed as a list of integers
            LottoResult -->> User: [ERROR] 로또 번호는 숫자 배열이어야 합니다. ex) 1,2,3,4
        else If out of range
            LottoResult -->> User: [ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.
        else If the length is incorrect
            LottoResult -->> User: [ERROR] 로또 번호는 6개의 숫자로 이뤄져야 합니다.
        end
    end

    loop until get proper input
        rect rgb(0, 200, 200)
            LottoResult -->> User: 보너스 번호를 입력해 주세요.
            LottoResult ->>+ User: getBonusNumber(): String
            User -->>- LottoResult: BonusNumber
        end

        alt If it can't be parsed as a list of integers
            LottoResult -->> User: [ERROR] 보너스 번호는 숫자 배열이어야 합니다. ex) 1,2,3,4
        else If out of range
            LottoResult -->> User: [ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.
        else If the length is incorrect
            LottoResult -->> User: [ERROR] 보너스 번호는 1개의 숫자로 이뤄져야 합니다.
        end
    end

    LottoResult -->>- Application: LottoResult
```

```mermaid
---
title: Generate Winning Result
---
sequenceDiagram
    box System
        actor Application
        participant Payment
        participant LottoResult
    end

    Application ->>+ Payment: genWinningResults(lottoResult): WinningResults

    loop lotteries
        rect rgb(0, 200, 200)
            Payment ->>+ LottoResult: genWinningResult(lotto): WinningResult
            LottoResult -->>- Payment: WinningResult
        end
    end

    Payment -->>- Application: WinningResults
```

### Design Model

```mermaid
classDiagram
    Application --* Payment
    Application --* LottoResult
    Payment --o Lotteries
    Payment --> WinningResults
    LottoResult ..> Lotto
    Lotteries --* Lotto
    WinningResults --* WinningResult

    class Application {
        -payment: Payment
        -lottoResult: LottoResult
        +start() void
    }

    class Payment {
        -price: Integer$
        -money: Integer
        -lotteries: Lotteries
        +fromUser() Payment$
        -getMoney() Integer
        +getWinningResults(lottoResult: LottoResult) WinningResults
    }

    class Lotto {
        -rangeStart: Integer$
        -rangeEnd: Integer$
        -numOfNumbers: Integer$
        -numbers: List<Integer>
        +rand() Lotto$
    }

    class Lotteries {
        -lotteries: List<Lotto>
        +Lotteries(amount: Integer) Lotteries$
        +add(lotto) void
        +showAll() void
    }

    class LottoResult {
        -numOfBonuseNumbers: Integer$
        -winningNumbers: List<Integer>
        -bonuseNumbers: List<Integer>
        +fromUser() LottoResult$
        -getWinningNumbers() String
        -getBonusNumber() String
        +genWinningResult(lotto: Lotto) WinningResult
    }

    class WinningResult {
        <<Enumeration>>
        -reward: Integer
        +FIRST_PRIZE$
        +SECOND_AND_BONUSE_PRIZE$
        +SECOND_PRIZE$
        +THIRD_PRIZE$
        +FOURTH_PRIZE$
        +FIFTH_PRIZE$
    }

    class WinningResults {
        -lottoResults: List<WinningResult>
    }
```
