// Classe que usa GA para um problema específico (gera população de ElementoGA1).
// Fonte: Algoritmos Genéticos - Ricardo Linden.

import java.util.ArrayList;

public class GA1 extends GA {

    @Override
    public void inicializaPopulacao(int tamanho) {
        this.populacao = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            ElementoGA1 e = new ElementoGA1();
            e.inicializaElemento(); // inicializa com 44 bits
            this.populacao.add(e);  // Corrigido: estava adicionando novo elemento vazio
        }
        this.tamanho_populacao = tamanho;
    }

    public GA1(int num_geracoes, int tamanho_populacao, double prob_mut) {
        super(num_geracoes, tamanho_populacao, prob_mut);
    }

    public GA1(int tamanho_populacao, double prob_mut) {
        super(60, tamanho_populacao, prob_mut);
    }

    public GA1(double prob_mut) {
        super(60, 100, prob_mut);
    }

    public GA1() {
        super(60, 100, 0.001);
    }
}
