package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoShop;
import lotto.view.LottoInputView;
import lotto.view.LottoResultView;

public class LottoApplication {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoInputView(),
            new LottoResultView(),
            new LottoShop());
        lottoController.run();
    }
}
