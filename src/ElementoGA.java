// Extraído / reconstruído a partir do PDF. Fonte: Algoritmos Genéticos - Ricardo Linden.
import java.util.Random;

public class ElementoGA {
    protected String valor;            // representação binária como string
    protected double avaliacao;
    
    // Instância de Random reutilizável para melhor performance
    private static final Random random = new Random();

    public ElementoGA() {
        this.valor = "";
        this.avaliacao = 0.0;
    }

    /* =========================
       Métodos de acesso básicos
       ========================= */
    public String getValor() { return this.valor; }
    public void setValor(String v) { this.valor = v; }
    public double getAvaliacao() { return this.avaliacao; }
    public void setAvaliacao(double a) { this.avaliacao = a; }

    /* inicializa aleatoriamente um cromossomo (string de 0/1) */
    public void inicializaElemento(int tamanho) {
        StringBuilder sb = new StringBuilder(tamanho);
        for (int i = 0; i < tamanho; i++) {
            if (random.nextBoolean()) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        this.valor = sb.toString();
    }

    /* conversão de substring binária para número (auxiliar) */
    protected double converteBooleano(int inicio, int fim) {
        double aux = 0;
        String s = this.getValor();
        for (int i = inicio; i <= fim; i++) {
            aux *= 2;
            if (s.charAt(i) == '1') {
                aux += 1;
            }
        }
        return aux;
    }

    /* Função de cálculo de avaliação padrão (pode ser sobrescrita) */
    public double calculaAvaliacao() {
        // implementação genérica: interpreta toda a string como integer e usa como avaliação.
        // No livro, para o exemplo específico este método é sobrescrito em ElementoGA1.
        double val = 0.0;
        try {
            // converte string binária em inteiro (cuidado com tamanho)
            val = (double) Long.parseLong(this.valor, 2);
        } catch (Exception e) {
            val = 0.0;
        }
        this.avaliacao = val;
        return this.avaliacao;
    }

    /* crossover de um ponto (retorna um filho - instancia da mesma classe que o outroPai) */
    public ElementoGA crossoverUmPonto(ElementoGA outroPai) {
        int pontoCorte = random.nextInt(this.valor.length());
        String aux1;
        
        if (random.nextBoolean()) {
            aux1 = this.valor.substring(0, pontoCorte)
                 + outroPai.getValor().substring(pontoCorte);
        } else {
            aux1 = outroPai.getValor().substring(0, pontoCorte)
                 + this.valor.substring(pontoCorte);
        }
        
        ElementoGA retorno;
        try {
            retorno = outroPai.getClass().getDeclaredConstructor().newInstance();
            retorno.setValor(aux1);
        } catch (Exception e) {
            // se falhar, criar um ElementoGA simples
            retorno = new ElementoGA();
            retorno.setValor(aux1);
        }
        return retorno;
    }

    /* mutação bit-a-bit com probabilidade prob (prob entre 0 e 1) */
    public void mutacao(double prob) {
        StringBuilder aux = new StringBuilder(this.valor.length());
        for (int i = 0; i < this.valor.length(); i++) {
            if (random.nextDouble() < prob) {
                // flip bit
                if (this.valor.charAt(i) == '1') {
                    aux.append('0');
                } else {
                    aux.append('1');
                }
            } else {
                aux.append(this.valor.charAt(i));
            }
        }
        this.valor = aux.toString();
    }

    /* representação textual para impressão */
    public String toString() {
        return "ElementoGA [valor=" + this.valor + " aval=" + this.avaliacao + "]";
    }
}
