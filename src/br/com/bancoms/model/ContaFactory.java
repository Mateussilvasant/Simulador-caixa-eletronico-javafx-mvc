package br.com.bancoms.model;

public enum ContaFactory {

    CORRENTE {
        @Override
        public Conta getConta() {
            return new ContaCorrente();
        }

    },

    POUPANCA {
        @Override
        public Conta getConta() {
            return new ContaPoupanca();
        }

    },

    INVESTIMENTO {
        @Override
        public Conta getConta() {
            return new ContaInvestimento();
        }

    };

    public abstract Conta getConta();
}
