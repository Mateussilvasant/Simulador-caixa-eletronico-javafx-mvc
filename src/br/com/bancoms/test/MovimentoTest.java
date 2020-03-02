package br.com.bancoms.test;

import br.com.bancoms.dto.MovimentoBuscaDTO;
import br.com.bancoms.model.EMovimento;
import br.com.bancoms.service.MovimentoService;
import br.com.bancoms.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

class MovimentoServiceTest {

    @Test
    void listarMovimentosPorTipoTest() {

        MovimentoBuscaDTO movimento = new MovimentoBuscaDTO(
                123456,
                EMovimento.DEPOSITO.getValue(),
                DateUtil.getDate(2020, Calendar.FEBRUARY, 14),
                DateUtil.getDate(2020, Calendar.FEBRUARY, 17)
        );

        MovimentoService.getInstance().listarMovimentosPorTipo(movimento
        ).get().forEach(System.out::println);

    }

    @Test
    void getSaldoDiferencial() {

        MovimentoBuscaDTO dto = new MovimentoBuscaDTO();

        Calendar dataInicio = DateUtil.getCurrentCalendar();
        dataInicio.set(Calendar.DAY_OF_MONTH, dataInicio.getActualMinimum(Calendar.DAY_OF_MONTH));
        dataInicio.set(Calendar.HOUR_OF_DAY, 0);
        dataInicio.set(Calendar.MINUTE, 0);
        dataInicio.set(Calendar.SECOND, 0);

        Calendar dataFim = DateUtil.getCurrentCalendar();
        dataFim.set(Calendar.DAY_OF_MONTH, dataFim.getActualMaximum(Calendar.DAY_OF_MONTH));
        dataFim.set(Calendar.HOUR_OF_DAY, 23);
        dataFim.set(Calendar.MINUTE, 59);
        dataFim.set(Calendar.SECOND, 59);


        dto.setDataInicio(DateUtil.parseDefaultUSA(dataInicio));
        dto.setDataFim(DateUtil.parseDefaultUSA(dataFim));

        dto.setNumeroConta(123456);

        double saldo = MovimentoService.getInstance().getSaldoDiferencial(dto);

        System.out.println("saldo = [" + saldo + "]");
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
        ).get().forEach(System.out::println);

    }


}