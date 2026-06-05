package Entities.Enums;

public enum FormasPagamento {
    CARTAO_CREDITO('1', "Cartão de crédito"),
    PIX('2', "PIX"),
    INVALIDA('0', "Invalido");

    private final char codigo;
    private final String descricao;
    //Construtor sendo criado para instanciar uma mensagem de pronome
    FormasPagamento(char codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    //Utilizando um get para atribuir a msg
    public char getCodigo() {
        return codigo;
    }
    // Atribuindo um get para atribuir msg
    public String getDescricao() {
        return descricao;
    }
    //Metodo para atribuir a mensagem dentro do ENUM passando de seu respectiva declaração passando a ser mostrado ao usuario a String
    public static FormasPagamento deCodigo(char codigo) {
        for (FormasPagamento fp : values()) {
            if (fp.getCodigo() == codigo) {
                return fp;
            }
        }
        return INVALIDA;
    }
}