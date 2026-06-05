# Sistema de Compras Eletrônicas (E-Commerce)

Uma aplicação interativa via console que simula a experiência completa de navegação, seleção de produtos e checkout de uma loja virtual de periféricos de informática.

## 🛠️ Tecnologias e Conceitos
* **Linguagem:** Java
* **Estruturas de Dados:** `ArrayList` dinâmico para manipulação dos itens do carrinho
* **Geração de Dados:** Utilização da classe `Random` para simulação de número de pedidos
* **Controle de Fluxo:** Enums (`OpcaoMenuPrincipal`, `DecisaoCompra`, `FormasPagamento`)

## 🚀 Funcionalidades
* **Catálogo de Produtos:** Listagem atualizada com preços formatados.
* **Carrinho Inteligente:** Adição dinâmica de itens e cálculo automatizado de frete (adiciona taxa de R$ 25,00 ou concede frete grátis para compras acima de R$ 500,00).
* **Checkout e Gateway Simulado:** Coleta detalhada de dados de entrega e validação de métodos de pagamento (Cartão de Crédito de 16 dígitos ou chave PIX).
