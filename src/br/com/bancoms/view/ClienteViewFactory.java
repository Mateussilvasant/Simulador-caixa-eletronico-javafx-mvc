package br.com.bancoms.view;

import br.com.bancoms.controller.ClienteController;

public enum ClienteViewFactory {

    CORRENTE {
        @Override
        public void iniciarView(ClienteController controller) {
            controller.viewClient.iniciarCorrenteView(controller);
        }

    },

    POUPANCA {
        @Override
        public void iniciarView(ClienteController controller) {
            controller.viewClient.iniciarPoupancaView(controller);
        }

    },

    INVESTIMENTO {
        @Override
        public void iniciarView(ClienteController controller) {
            controller.viewClient.iniciarInvestimento(controller);
        }

    };

    public abstract void iniciarView(ClienteController controller);

}
