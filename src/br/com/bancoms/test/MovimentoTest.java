package br.com.bancoms.test;

import br.com.bancoms.dto.MovimentoBuscaDTO;
import br.com.bancoms.model.Movimento;
import br.com.bancoms.service.MovimentoService;
import br.com.bancoms.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

class MovimentoServiceTest {

    @Test
    void listarMovimentosPorTipoTest() {

        MovimentoBuscaDTO movimento = new MovimentoBuscaDTO(
                123456,
                Movimento.EMovimento.DEPOSITO.getValue(),
                DateUtil.getDate(2020, Calendar.FEBRUARY, 14),
                DateUtil.getDate(2020, Calendar.FEBRUARY, 17)
        );

        MovimentoService.getInstance().listarMovimentosPorTipo(movimento
        ).forEach(System.out::println);

    }

    @Test
    void listarTodosMovimentosTest() {

        MovimentoBuscaDTO movimento = new MovimentoBuscaDTO(
                123456,
                DateUtil.getDate(2020, Calendar.FEBRUARY, 14),
                DateUtil.getDate(2020, Calendar.FEBRUARY, 17)
        );

        MovimentoService.getInstance().listarTodosMovimentos(
                movimento
        ).forEach(System.out::println);

    }


}