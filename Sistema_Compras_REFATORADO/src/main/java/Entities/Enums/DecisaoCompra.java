package Entities.Enums;

public enum DecisaoCompra {
    FINALIZAR('1'),
    CANCERLAR_VOLTAR('2'),
    INVALIDA('0');

    private final char codigo;
    //Construtor para atribuir um valor com o dado para inserir uma mensagem
    DecisaoCompra(char codigo) {
        this.codigo = codigo;
    }
    //Metodo para utilizar o objeto para instanciar a msg
    public char getCodigo() {
        return codigo;
    }
    //Metodo para passar a decisão do usuario em mensagem
    public static DecisaoCompra deCodigo(char codigo) {
        for (DecisaoCompra opcao : values()) {
            if (opcao.getCodigo() == codigo) {
                return opcao;
            }
        }
        return INVALIDA;
    }
}