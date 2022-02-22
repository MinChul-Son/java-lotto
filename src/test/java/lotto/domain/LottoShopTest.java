package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoShopTest {

    @Test
    void 로또를_사기위한_최소금액은_1000원_이상이다() {
        final LottoShop lottoShop = LottoShop.getInstance();

        assertThatThrownBy(() -> lottoShop.countPossibleLottoAmount(new Money(500)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("최소 1000원이어야 합니다.");
    }

    @Test
    void 자동_로또를_살_수_있다() {
        final LottoShop lottoShop = LottoShop.getInstance();

        assertThat(lottoShop.buyAutoLotto()).isInstanceOf(Lotto.class);
    }

    @Test
    void 수동_로또를_살_수_있다() {
        final LottoShop lottoShop = LottoShop.getInstance();
        final String[] lottoNumbers = {"1", "2", "3", "4", "5", "6"};

        Lotto handOperatedLotto = lottoShop.buyManualLotto(lottoNumbers);

        Assertions.assertAll(
            () ->assertThat(handOperatedLotto.getLottoNumbers().get(0).getNumber()).isEqualTo(1),
            () ->assertThat(handOperatedLotto.getLottoNumbers().get(1).getNumber()).isEqualTo(2),
            () ->assertThat(handOperatedLotto.getLottoNumbers().get(2).getNumber()).isEqualTo(3),
            () ->assertThat(handOperatedLotto.getLottoNumbers().get(3).getNumber()).isEqualTo(4),
            () ->assertThat(handOperatedLotto.getLottoNumbers().get(4).getNumber()).isEqualTo(5),
            () ->assertThat(handOperatedLotto.getLottoNumbers().get(5).getNumber()).isEqualTo(6)
        );
    }

}
