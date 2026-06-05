package Entities.Estoque;

import Entities.Produto.Produto;

public class Estoque {
    private Produto[] produto = {
            new Produto("Teclado Mecânico RGB", 279.99),
            new Produto("Mouse Gamer 12000 DPI", 155.00),
            new Produto("Monitor 24\" Full HD", 849.50),
            new Produto("Headset com Cancelamento de Ruído", 320.00),
            new Produto("Webcam 1080p", 193.48)
    };
    private int produtoAtual;

    public Produto[] getProduto() {
        return produto;
    }

    public int getProdutoAtual() {
        return produtoAtual;
    }

    public void setProdutoAtual(int produtoAtual) {
        this.produtoAtual = produtoAtual;
    }

    public String exibirEstoque() {
        String estoque = "";

        for (int i = 0; i < produto.length; i++) {
            String stringEstoque = "";
            stringEstoque += (i + 1) + "- " + produto[i].getNomeProduto() + " R$ " + String.format("%.2f%n", produto[i].getPrecoProduto());
            estoque += stringEstoque;
        }

        return estoque;
    }
}