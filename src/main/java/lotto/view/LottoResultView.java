package lotto.view;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResults;
import lotto.domain.Lottos;
import lotto.domain.Ranking;

public class LottoResultView {

    private static final String LOTTO_STATISTICS_MESSAGE = "당첨 통계";
    private static final String HYPHEN_MESSAGE = "---------";
    private static final String TOTAL_YIELD_MESSAGE = "총 수익률은 ";
    private static final String FINISH_MESSAGE = "입니다.";
    private static final String OPENING_PARENTHESIS = "(";
    private static final String MATCH_AMOUNT_MESSAGE = "개 일치";
    private static final String BONUS_BALL_MATCH_MESSAGE = "보너스 볼 일치";
    private static final String WON_MESSAGE = "원) - ";
    private static final String AMOUNT_MESSAGE = "개";
    private static final String SPACE = " ";
    private static final String COMMA = ",";
    private static final String LOTTO_AMOUNT_MESSAGE = "개를 구매했습니다.";
    private static final String HAND_OPERATED_LOTTO_MESSAGE = "수동으로";
    private static final String AUTO_LOTTO_MESSAGE = "자동으로";

    public void printLottos(final Lottos lottos, final int autoLottoAmount,
        final int handOperatedLottoAmount) {
        System.out.println(HAND_OPERATED_LOTTO_MESSAGE + SPACE + handOperatedLottoAmount
            + AMOUNT_MESSAGE + COMMA
            + AUTO_LOTTO_MESSAGE + SPACE +
            autoLottoAmount + LOTTO_AMOUNT_MESSAGE);

        for (Lotto lotto : lottos.getLottos()) {
            printLotto(lotto.getLottoNumbers());
        }
    }

    private void printLotto(final List<LottoNumber> lottoNumbers) {
        final List<Integer> numbers = lottoNumbers.stream()
            .map(LottoNumber::getNumber)
            .collect(Collectors.toList());

        System.out.println(numbers);
    }

    public void finishGame(final LottoResults lottoResults, final BigDecimal totalWinnerPrize) {
        System.out.println(LOTTO_STATISTICS_MESSAGE);
        System.out.println(HYPHEN_MESSAGE);

        final List<Ranking> rankings = new LinkedList<>(lottoResults.getTotalResult().keySet());
        Collections.sort(rankings, Comparator.reverseOrder());

        rankings.forEach(ranking ->
            printLottoResult(ranking, lottoResults.getTotalResult().get(ranking)));
        printYield(totalWinnerPrize);
    }

    private void printYield(final BigDecimal totalWinnerPrice) {
        System.out.printf(TOTAL_YIELD_MESSAGE + totalWinnerPrice + FINISH_MESSAGE);
    }

    private void printLottoResult(final Ranking ranking, final Integer count) {
        final StringBuilder stringBuilder = new StringBuilder();

        if (ranking.isFail()) {
            return;
        }

        stringBuilder.append(OPENING_PARENTHESIS)
            .append(ranking.getWinnerPrice())
            .append(WON_MESSAGE)
            .append(count)
            .append(AMOUNT_MESSAGE);

        if (ranking.equals(Ranking.SECOND)) {
            stringBuilder.insert(0,
                ranking.getNormalNumberMatchCount() + MATCH_AMOUNT_MESSAGE + COMMA + SPACE
                    + BONUS_BALL_MATCH_MESSAGE);
            System.out.println(stringBuilder);
            return;
        }

        stringBuilder.insert(0, ranking.getNormalNumberMatchCount()
            + MATCH_AMOUNT_MESSAGE + SPACE);
        System.out.println(stringBuilder);
    }
}
