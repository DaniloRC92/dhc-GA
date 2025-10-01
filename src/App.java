/**
 * Aplicação principal demonstrando o uso do Algoritmo Genético
 * para otimizar a função f6(x,y) de Schaffer
 * 
 * Exemplo modernizado baseado no livro "Algoritmos Genéticos" - Ricardo Linden
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== Algoritmo Genético - Otimização da Função f6(x,y) ===\n");
        
        try {
            // Parâmetros do algoritmo genético
            int numeroGeracoes = 100;
            int tamanhoPopulacao = 50;
            double probabilidadeMutacao = 0.01;
            
            System.out.println("Parâmetros:");
            System.out.println("- Número de gerações: " + numeroGeracoes);
            System.out.println("- Tamanho da população: " + tamanhoPopulacao);
            System.out.println("- Probabilidade de mutação: " + probabilidadeMutacao);
            System.out.println("\nExecutando algoritmo genético...\n");
            
            // Executa o algoritmo genético
            GA1 ga = new GA1();

            ga.executa();
            
            System.out.println("\n=== Execução concluída ===");
            
        } catch (Exception e) {
            System.err.println("Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
