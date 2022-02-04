package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.LottoAutoGenerator;

public class Lotto {

    private final List<LottoNumber> lotto;

    public Lotto(final List<LottoNumber> lotto) {
        validateNumbersSize(lotto);
        validateDuplicateNumber(lotto);

        this.lotto = lotto;
    }

    public Lotto() {
        this(LottoAutoGenerator.getInstance().generateLotto());
    }

    private void validateDuplicateNumber(final List<LottoNumber> lotto) {
        Set<LottoNumber> lottoDuplicate = new HashSet<>(lotto);
        if (lotto.size() != lottoDuplicate.size()) {
            throw new IllegalArgumentException("복권에 중복된 숫자가 존재합니다.");
        }
    }

    private void validateNumbersSize(final List<LottoNumber> lotto) {
        if (lotto.size() != 6) {
            throw new IllegalArgumentException("복권의 숫자 개수가 유효하지 않습니다.");
        }
    }

    public List<LottoNumber> getLotto() {
        return Collections.unmodifiableList(lotto);
    }
}
