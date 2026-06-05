package Entities.Enums;

public enum OpcaoMenuPrincipal {
    ADICIONAR_PRODUTOS('1'),
    VER_CARRINHO('2'),
    SAIR('3'),
    INVALIDA('0');

    private final char codigo;

    OpcaoMenuPrincipal(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return codigo;
    }

    public static OpcaoMenuPrincipal deCodigo(char codigo) {
        for (OpcaoMenuPrincipal opcao : values()) {
            if (opcao.getCodigo() == codigo) {
                return opcao;
            }
        }
        return INVALIDA;
    }
}