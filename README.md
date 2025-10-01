# Algoritmo Genético - Otimização da Função f6

Este projeto implementa um **Algoritmo Genético** para otimização da função matemática f6, uma função de benchmark amplamente utilizada em otimização global. O código foi modernizado mantendo a funcionalidade original descrita no livro "Algoritmos Genéticos" de Ricardo Linden.

## 📋 Índice

- [O que é este projeto](#o-que-é-este-projeto)
- [A Função de Schaffer f6](#a-função-de-schaffer-f6)
- [Como funciona o Algoritmo Genético](#como-funciona-o-algoritmo-genético)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como executar](#como-executar)
- [Parâmetros configuráveis](#parâmetros-configuráveis)
- [Exemplo de execução](#exemplo-de-execução)

## 🎯 O que é este projeto

Este projeto implementa um **algoritmo genético** para encontrar o **máximo global** da função de Schaffer f6. É um exemplo clássico de como técnicas de computação evolutiva podem ser aplicadas para resolver problemas de otimização complexos.

### Características principais:
- ✅ **Otimização global**: Encontra o máximo da função f6 no domínio [-100, 100] x [-100, 100]
- ✅ **Representação binária**: Cromossomos codificados como strings binárias de 44 bits
- ✅ **Operadores genéticos**: Seleção por roleta, crossover de um ponto, mutação bit-a-bit
- ✅ **Código modernizado**: Utiliza boas práticas atuais do Java

## 🧮 A Função f6

A **função f6** é definida como:

```
f6(x,y) = 0.5 - (sin²(√(x² + y²)) - 0.5) / (1 + 0.001(x² + y²))²
```

### Características da função:
- **Domínio**: x, y ∈ [-100, 100]
- **Máximo global**: f6(0,0) = 1.0
- **Tipo**: Função multimodal com muitos máximos locais
- **Dificuldade**: Alta (muitas armadilhas para algoritmos de busca local)

### Por que é desafiadora?
A função f6 possui **múltiplos máximos locais** que podem "enganar" algoritmos de otimização simples. O algoritmo genético, por trabalhar com uma **população de soluções**, consegue explorar diferentes regiões do espaço de busca simultaneamente.

## 🔧 Como funciona o Algoritmo Genético

### 1. **Representação**
- Cada solução (x,y) é codificada como uma string binária de **44 bits**
- Primeiros 22 bits = coordenada x
- Últimos 22 bits = coordenada y
- Conversão: binário → decimal → intervalo [-100, 100]

### 2. **População Inicial**
- Gera uma população de soluções aleatórias
- Cada cromossomo é uma string binária aleatória

### 3. **Avaliação**
- Cada cromossomo é decodificado para (x,y)
- Calcula-se f6(x,y) como fitness
- Quanto maior o valor, melhor a solução

### 4. **Seleção**
- **Seleção por Roleta**: Probabilidade proporcional ao fitness
- Soluções melhores têm maior chance de reprodução

### 5. **Reprodução**
- **Crossover de um ponto**: Combina dois pais em um filho
- **Mutação**: Inverte bits com pequena probabilidade

### 6. **Evolução**
- Processo se repete por várias gerações
- População evolui em direção a soluções melhores

## 📁 Estrutura do Projeto

```
dhc-GA/
├── src/
│   ├── App.java           # Aplicação principal
│   ├── ElementoGA.java    # Classe base para cromossomos
│   ├── ElementoGA1.java   # Cromossomo específico para f6
│   ├── GA.java           # Algoritmo genético base
│   └── GA1.java          # AG específico para f6
├── bin/                  # Arquivos compilados
├── lib/                  # Dependências (vazio)
├── README.md            # Esta documentação
└── MODERNIZACAO.md      # Detalhes das modernizações
```

### Descrição das classes:

- **`App.java`**: Ponto de entrada, configura e executa o algoritmo
- **`ElementoGA.java`**: Classe base que define um cromossomo genérico
- **`ElementoGA1.java`**: Especialização para a função f6 de Schaffer
- **`GA.java`**: Implementação geral do algoritmo genético
- **`GA1.java`**: AG especializado que usa ElementoGA1

## 🚀 Como executar

### Pré-requisitos
- Java 8 ou superior
- JDK instalado e configurado

### Compilação
```bash
cd dhc-GA
javac -d bin src/*.java
```

### Execução
```bash
java -cp bin App
```

## ⚙️ Parâmetros configuráveis

No arquivo `App.java`, você pode ajustar:

```java
int numeroGeracoes = 100;        // Número de gerações
int tamanhoPopulacao = 50;       // Tamanho da população
double probabilidadeMutacao = 0.01;  // Taxa de mutação (1%)
```

### Recomendações:
- **Gerações**: 50-200 (mais gerações = busca mais refinada)
- **População**: 30-100 (maior população = mais diversidade)
- **Mutação**: 0.001-0.01 (muito alta pode destruir boas soluções)

## 📊 Exemplo de execução

```
=== Algoritmo Genético - Otimização da Função f6(x,y) ===

Parâmetros:
- Número de gerações: 100
- Tamanho da população: 50
- Probabilidade de mutação: 0.01

Executando algoritmo genético...

Geracao 0
Avaliando todos.
A soma das avaliacoes eh 45.23
Calculando nova geracao.
...

Geracao 99
Avaliando todos.
A soma das avaliacoes eh 74.61
ElementoGA [valor=10000100101110010010100111110001000101101010 aval=1.9357754886894762]

=== Execução concluída ===
```

### Interpretação dos resultados:
- **valor**: Representação binária da melhor solução encontrada
- **aval**: Valor da função f6 para essa solução (quanto mais próximo de 1.0, melhor)

## 🎓 Conceitos demonstrados

Este projeto é excelente para aprender sobre:

- **Algoritmos Genéticos**: Conceitos fundamentais de computação evolutiva
- **Otimização Global**: Como encontrar soluções em espaços complexos
- **Codificação Binária**: Representação de números reais em binário
- **Operadores Genéticos**: Seleção, crossover e mutação
- **Funções de Benchmark**: Uso de funções padrão para teste de algoritmos

## 📈 Possíveis melhorias

- Implementar outros tipos de seleção (torneio, ranking)
- Adicionar crossover de dois pontos ou uniforme
- Implementar estratégias de diversidade
- Adicionar critérios de parada adaptativos
- Visualização gráfica da evolução

---

**Referência**: Baseado no livro "Algoritmos Genéticos" de Ricardo Linden

