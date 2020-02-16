package br.com.bancoms.test;

import br.com.bancoms.model.Movimento;
import br.com.bancoms.service.MovimentoService;
import br.com.bancoms.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

class MovimentoServiceTest {

    @Test
    void listarMovimentosPorTipoTest() {

        MovimentoService.getInstance().listarMovimentosPorTipo(
                123456,
                Movimento.DEPOSITO,
                DateUtil.getDate(2020, Calendar.FEBRUARY, 14),
                DateUtil.getDate(2020, Calendar.FEBRUARY, 16)
        ).forEach(System.out::println);

    }

    @Test
    void listarTodosMovimentosTest() {

        MovimentoService.getInstance().listarTodosMovimentos(
                123456,
                DateUtil.getDate(2020, Calendar.FEBRUARY, 14),
                DateUtil.getDate(2020, Calendar.FEBRUARY, 16)
        ).forEach(System.out::println);

    }


}