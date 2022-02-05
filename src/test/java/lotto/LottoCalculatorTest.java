package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoCalculator;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import org.junit.jupiter.api.Test;

class LottoCalculatorTest {

    @Test
    void 당첨_번호와_몇개가_일치하는지_판단할_수_있다() {
        // given
        LottoCalculator calculator = LottoCalculator.getInstance();
        List<LottoNumber> lotteryNumbers = Arrays.asList(1, 2, 3, 4, 5, 6).stream()
            .map(LottoNumber::from)
            .collect(Collectors.toList());
        int bonusNum = 6;
        List<LottoNumber> userNumbers = Arrays.asList(1, 2, 3, 4, 5, 8).stream()
            .map(LottoNumber::from)
            .collect(Collectors.toList());

        // when
        LottoResult lottoResult = calculator.countLotteryNumber(lotteryNumbers, userNumbers, bonusNum);

        // then
        assertThat(lottoResult.getNormalSuccessCount()).isEqualTo(5);
        assertThat(lottoResult.getBonusSuccessCount()).isEqualTo(0);
    }
}
