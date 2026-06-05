package Applications.Menu;

import Entities.Carrinho.Carrinho;
import Entities.Cliente.Cliente;
import Entities.Estoque.Estoque;
import Entities.Pagamento.Pagamento;
import Entities.Enums.OpcaoMenuPrincipal; // Import dos Enums
import Entities.Enums.DecisaoCompra;      // Import dos Enums
import Entities.Enums.FormasPagamento;   // Import dos Enums

import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private Carrinho carrinho = new Carrinho();
    private Pagamento pagamento = new Pagamento();
    private Estoque estoque = new Estoque();

    // Metodo que exibe o primeiro menu e liga as ações aos outros
    public void exibirMenuPrincipal() {
        OpcaoMenuPrincipal opcao; // Uso do Enum OpcaoMenuPrincipal

        do {
            System.out.println(
                    "=================================" +
                            "\nSelecione a opção que você deseja:" +
                            "\n1- Adicionar produtos ao carrinho" +
                            "\n2- Ver carrinho" +
                            "\n3- Sair" +
                            "\n================================="
            );
            char resp = sc.next().charAt(0);
            opcao = OpcaoMenuPrincipal.deCodigo(resp);

            switch (opcao) {
                case ADICIONAR_PRODUTOS:
                    menuCompras();
                    break;
                case VER_CARRINHO:
                    menuCarrinho();
                    break;
                case SAIR:
                    // Apenas sai do bloco e encerra o loop
                    break;
                default:
                    // O código original não exibia mensagem aqui, mantendo o padrão
                    break;
            }

        } while (opcao != OpcaoMenuPrincipal.SAIR);

        sc.close();
    }

    // Metodo que exibe os produtos disponíveis para compra e armazena os itens escolhidos na ArrayList da classe Carrinho
    public void menuCompras() {
        int prod;

        do {
            System.out.println(
                    "=================================" +
                            "\nSelecione o produto que deseja comprar:"
            );
            System.out.print(estoque.exibirEstoque());

            prod = sc.nextInt();

            if (prod <= estoque.getProduto().length && prod > 0) {
                estoque.setProdutoAtual(prod - 1);
                carrinho.adicionarAoCarrinho(estoque);
            } else {
                System.out.println("Produto inválido, tente novamente.");
            }

        } while (prod > estoque.getProduto().length || prod <= 0);
    }

    // Metodo que exibe os produtos adicionados ao carrinho e realiza a decisão de finalizar ou não a compra
    public void menuCarrinho() {
        if (carrinho.getItensEscolhidos().isEmpty()) {
            System.out.println("O carrinho está vazio.");
            return;
        }
        else {
            System.out.println(
                    "=================================" +
                            "\nCarrinho:"
            );
            System.out.print(carrinho.exibirCarrinho(estoque));

            carrinho.verificarFreteGratis();
            System.out.printf("Preço total: R$ %.2f%n", carrinho.precoTotal());
            System.out.println("=================================");
        }

        DecisaoCompra finalizar; // Uso do Enum DecisaoCompra
        do {
            System.out.println(
                    "Deseja finalizar a compra?" +
                            "\n1- Sim" +
                            "\n2- Não"
            );
            char respFinalizar = sc.next().charAt(0);
            finalizar = DecisaoCompra.deCodigo(respFinalizar);

            if (finalizar == DecisaoCompra.FINALIZAR) {
                coletarDadosCliente();
                processarPagamento();
            }
            else if (finalizar == DecisaoCompra.INVALIDA) {
                System.out.println("Digite uma opção válida");
            }

        } while (finalizar != DecisaoCompra.FINALIZAR && finalizar != DecisaoCompra.CANCERLAR_VOLTAR);
    }

    // Metodo que coleta e armazena os dados do cliente
    public void coletarDadosCliente() {
        System.out.println("Digite seus dados para finalizar a compra:");
        System.out.println("Nome completo:");
        sc.nextLine(); // Limpar buffer
        String nome = sc.nextLine();

        System.out.println("CPF:");
        String cpf = sc.nextLine();

        System.out.println("Cidade:");
        String cidade = sc.nextLine();

        System.out.println("Estado:");
        String estado = sc.nextLine();

        System.out.println("Bairro:");
        String bairro = sc.nextLine();

        System.out.println("Rua:");
        String rua = sc.nextLine();

        System.out.println("Número:");
        int numero = sc.nextInt();
        sc.nextLine(); // Limpar buffer

        Cliente cliente = new Cliente(nome, cpf, cidade, estado, bairro, rua, numero);
    }

    // Metodo que processa a forma de pagamento escolhida
    public void processarPagamento() {
        FormasPagamento formaPagamento; // Uso do Enum FormasPagamento
        do {
            System.out.println(
                    "=================================" +
                            "\nSelecione a forma de formaPagamento" +
                            "\n1- Cartão de crédito" +
                            "\n2- PIX"
            );
            char respPagamento = sc.next().charAt(0);
            formaPagamento = FormasPagamento.deCodigo(respPagamento);

            if (formaPagamento == FormasPagamento.INVALIDA) {
                System.out.println("Digite uma opção válida!");
            }

        } while (formaPagamento == FormasPagamento.INVALIDA);

        if (formaPagamento == FormasPagamento.CARTAO_CREDITO) {
            System.out.println("Digite o número do cartão de crédito:");
            sc.nextLine(); // Limpar buffer
            String numeroCartao = sc.nextLine();

            if (pagamento.verificarCartao(numeroCartao)) {
                exibirCompra(FormasPagamento.CARTAO_CREDITO.getDescricao());
            }
            else
                System.out.println("Número de cartão inválido. Compra não finalizada.");
        }
        else if (formaPagamento == FormasPagamento.PIX) {
            System.out.println(
                    "Chave de formaPagamento via PIX:" +
                            "\ne8b2a1c4-5d9f-4b7e-a123-bc90d1f56789" +
                            "\nAperte ENTER para confirmar o formaPagamento!"
            );
            sc.nextLine(); // Limpar buffer
            sc.nextLine(); // Espera pressionar ENTER

            exibirCompra(FormasPagamento.PIX.getDescricao());
        }
    }

    // Metodo que exibe um resumo da compra finalizada
    public void exibirCompra(String formaPagamento) {
        System.out.println(
                "=================================" +
                        "\nCompra finalizada com sucesso" +
                        "\nNúmero do pedido: " + pagamento.gerarNumeroPedido() +
                        "\nDetalhes da compra:" +
                        "\n" + carrinho.exibirCarrinho(estoque) +
                        "\nPreço total: R$ " + String.format("%.2f%n", carrinho.precoTotal()) +
                        "\nForma de pagamento: " + formaPagamento +
                        "\n================================="
        );
    }
}