package lotto.domain;

import java.util.Objects;
import lotto.common.exception.LottoException;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int LOTTO_START_NUM = 1;
    private static final int LOTTO_END_NUM = 45;

    private final int number;

    public LottoNumber(final int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private void validateNumberRange(final int number) {
        if (number < LOTTO_START_NUM || number > LOTTO_END_NUM) {
            throw new LottoException("복권의 숫자 범위가 유효하지 않습니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        if (this.number > lottoNumber.number) {
            return 1;
        }
        if (this.number == lottoNumber.number) {
            return 0;
        }

        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
