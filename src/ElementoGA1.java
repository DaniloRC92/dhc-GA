// Implementação do elemento específico do exemplo (função f6(x,y) do livro).
// Fonte: Algoritmos Genéticos - Ricardo Linden.

public class ElementoGA1 extends ElementoGA {
    
    // Constantes para melhor legibilidade e manutenção
    private static final int TAMANHO_CROMOSSOMO = 44;
    private static final int BITS_POR_VARIAVEL = 22;
    private static final double FATOR_CONVERSAO = 0.00004768372718899898;
    private static final double VALOR_MINIMO = -100.0;

    public ElementoGA1() {
        super();
    }

    /* inicializa com tamanho padrão (44 bits) */
    public void inicializaElemento() {
        this.inicializaElemento(TAMANHO_CROMOSSOMO);
    }

    /* converte os bits para x e y e calcula a avaliação como no exemplo f6(x,y) do livro */
    @Override
    public double calculaAvaliacao() {
        // espera 44 bits: primeiros 22 = x, próximos 22 = y
        double x = this.converteBooleano(0, BITS_POR_VARIAVEL - 1);
        double y = this.converteBooleano(BITS_POR_VARIAVEL, TAMANHO_CROMOSSOMO - 1);
        
        x = x * FATOR_CONVERSAO + VALOR_MINIMO;
        y = y * FATOR_CONVERSAO + VALOR_MINIMO;
        
        double numerador = Math.sin(Math.sqrt(x * x + y * y)) - 0.5;
        double denominador = (1.0 + 0.001 * (x * x + y * y));
        denominador *= denominador;
        
        this.avaliacao = 0.5 - numerador / denominador;
        return this.avaliacao;
    }
}
