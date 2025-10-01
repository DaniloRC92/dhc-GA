// Classe principal do algoritmo genético (execução, seleção por roleta, geração, etc).
// Fonte: Algoritmos Genéticos - Ricardo Linden.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GA {
    protected List<ElementoGA> populacao;
    protected double somaAvaliacoes;
    protected double chance_mutacao;
    protected List<ElementoGA> nova_populacao;
    protected int numero_geracoes, tamanho_populacao;
    
    // Instância de Random reutilizável para melhor performance
    private static final Random random = new Random();

    private double calculaSomaAvaliacoes() {
        this.somaAvaliacoes = 0;
        for (ElementoGA elemento : populacao) {
            this.somaAvaliacoes += elemento.getAvaliacao();
        }
        return this.somaAvaliacoes;
    }

    public int roleta() {
        int i;
        double aux = 0;
        calculaSomaAvaliacoes();
        double limite = random.nextDouble() * this.somaAvaliacoes;
        
        for (i = 0; (i < this.populacao.size()) && (aux < limite); i++) {
            aux += this.populacao.get(i).getAvaliacao();
        }
        
        /*Como somamos antes de testar, então tiramos 1 de i pois
           o anterior ao valor final consiste no elemento escolhido*/
        i--;
        System.out.println("Escolhi o elemento de indice " + i);
        return i;
    }

    public void inicializaPopulacao(int tamanho) {
        /*Esta funcao tem que ser substituida por uma que inicialize a 
          populacao com a subclasse apropriada de ElementoGA*/
        this.populacao = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            ElementoGA e = new ElementoGA();
            e.inicializaElemento(44); // default: 44 bits (ou adaptar)
            this.populacao.add(e);
        }
        this.tamanho_populacao = tamanho;
    }

    public void geracao() {
        nova_populacao = new ArrayList<>();
        ElementoGA pai1, pai2, filho;
        
        System.out.println("Calculando nova geracao.\n");
        for (int i = 0; i < this.populacao.size(); i++) {
            pai1 = populacao.get(this.roleta());
            pai2 = populacao.get(this.roleta());
            filho = pai1.crossoverUmPonto(pai2);
            filho.mutacao(chance_mutacao);
            System.out.println("Vou adicionar.");
            nova_populacao.add(filho);
        }
    }

    public void moduloPopulacao() {
        populacao.clear();
        populacao.addAll(nova_populacao);
    }

    private int determinaMelhor() {
        int ind_melhor = 0;
        this.avaliaTodos();
        double aval_melhor = this.populacao.get(0).getAvaliacao();
        
        for (int i = 1; i < this.populacao.size(); i++) {
            ElementoGA aux = this.populacao.get(i);
            System.out.println("i=" + i + " aval=" + aux.getAvaliacao());
            if (aux.getAvaliacao() > aval_melhor) {
                aval_melhor = aux.getAvaliacao();
                ind_melhor = i;
            }
        }
        return ind_melhor;
    }

    private void avaliaTodos() {
        System.out.println("Avaliando todos.\n");
        for (ElementoGA aux : this.populacao) {
            aux.calculaAvaliacao();
        }
        this.somaAvaliacoes = calculaSomaAvaliacoes();
        System.out.println("A soma das avaliacoes e " + this.somaAvaliacoes);
    }

    /* Construtores */
    public GA(int num_geracoes, int tamanho_populacao, double prob_mut) {
        this.chance_mutacao = prob_mut;
        this.numero_geracoes = num_geracoes;
        this.tamanho_populacao = tamanho_populacao;
        
        // inicializa população e executa ciclo (conforme trechos do livro)
        this.inicializaPopulacao(tamanho_populacao);
        for (int i = 0; i < num_geracoes; i++) {
            System.out.println("Geracao " + i + "\n");
            this.avaliaTodos();
            this.geracao();
            this.moduloPopulacao();
        }
        int melhor = this.determinaMelhor();
        System.out.println(this.populacao.get(melhor));
    }

    public GA(int tam_pop, double prob_mut) {
        this(60, tam_pop, prob_mut);
    }

    public GA(double prob_mut) {
        this(60, 100, prob_mut);
    }

    public GA() {
        this(60, 100, 0.001);
    }

    /* versão alternativa de execução (executa()) mostrada no livro */
    public void executa() {
        this.inicializaPopulacao(this.tamanho_populacao);
        for (int i = 0; i < this.numero_geracoes; i++) {
            System.out.println("Geracao " + i + "\n");
            this.avaliaTodos();
            this.geracao();
            this.moduloPopulacao();
        }
        int melhor = this.determinaMelhor();
        System.out.println(this.populacao.get(melhor));
    }
}
