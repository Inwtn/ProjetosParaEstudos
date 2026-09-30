import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.*;

/**
 * Arquivo de estudo de Java (requer Java 17+).
 *
 * Compilar: javac EstudoJava.java
 * Executar: java EstudoJava
 *
 * Cada método "topicoXX" demonstra um assunto diferente.
 * Comente/descomente as chamadas no main() para focar no que quiser estudar.
 */
public class EstudoJava {

    // ------------------------------------------------------------------
    // Constantes e variáveis estáticas
    // ------------------------------------------------------------------
    static final double PI_APROX = 3.14159;
    static int contadorGlobal = 0;

    public static void main(String[] args) throws Exception {
        topico01VariaveisETipos();
        topico02Operadores();
        topico03Condicionais();
        topico04Lacos();
        topico05Arrays();
        topico06Strings();
        topico07Metodos();
        topico08OrientacaoAObjetos();
        topico09EnumERecord();
        topico10Generics();
        topico11Colecoes();
        topico12LambdasEStreams();
        topico13Optional();
        topico14Excecoes();
        topico15ClassesInternasEAnonimas();
        topico16Concorrencia();
        topico17DatasEMath();
        topico18SealedEPatternMatching();
        System.out.println("\n=== Fim do estudo! ===");
    }

    // Utilitário para imprimir títulos
    static void titulo(String texto) {
        System.out.println("\n========== " + texto + " ==========");
    }

    // ------------------------------------------------------------------
    // 01 - Variáveis e tipos
    // ------------------------------------------------------------------
    static void topico01VariaveisETipos() {
        titulo("01 - Variáveis e tipos");

        // Tipos primitivos
        byte b = 127;
        short s = 32_000;
        int i = 1_000_000;
        long l = 9_000_000_000L;
        float f = 3.14f;
        double d = 2.718281828;
        char c = 'J';
        boolean ok = true;

        System.out.println("byte=" + b + ", short=" + s + ", int=" + i + ", long=" + l);
        System.out.println("float=" + f + ", double=" + d + ", char=" + c + ", boolean=" + ok);

        // Limites
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);

        // Overflow
        int estouro = Integer.MAX_VALUE + 1;
        System.out.println("MAX_VALUE + 1 (overflow) = " + estouro);

        // Cast explícito e implícito
        double pi = 3.99;
        int truncado = (int) pi; // perde a parte decimal
        long promovido = i;      // cast implícito (int -> long)
        System.out.println("(int) 3.99 = " + truncado + " | int->long = " + promovido);

        // var (inferência de tipo, Java 10+)
        var mensagem = "Olá, var!";
        var lista = new ArrayList<String>();
        lista.add(mensagem);
        System.out.println(lista);

        // Wrappers e autoboxing
        Integer boxed = 42;       // autoboxing
        int unboxed = boxed;      // unboxing
        System.out.println("Integer.parseInt(\"123\") + 1 = " + (Integer.parseInt("123") + 1));
        System.out.println("boxed=" + boxed + ", unboxed=" + unboxed);

        // Cuidado com == em wrappers
        Integer x1 = 127, x2 = 127, y1 = 1000, y2 = 1000;
        System.out.println("127 == 127 (cache): " + (x1 == x2));
        System.out.println("1000 == 1000 (objetos diferentes): " + (y1 == y2));
        System.out.println("1000 equals 1000: " + y1.equals(y2));
    }

    // ------------------------------------------------------------------
    // 02 - Operadores
    // ------------------------------------------------------------------
    static void topico02Operadores() {
        titulo("02 - Operadores");

        int a = 17, b = 5;
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b) + "  (divisão inteira)");
        System.out.println("a % b = " + (a % b) + "  (resto)");
        System.out.println("(double) a / b = " + ((double) a / b));

        // Incremento
        int n = 5;
        System.out.println("n++ = " + (n++) + ", agora n = " + n);
        System.out.println("++n = " + (++n));

        // Atribuição composta
        n += 10;
        n *= 2;
        System.out.println("n depois de += 10 e *= 2: " + n);

        // Lógicos e relacionais
        boolean r1 = (a > b) && (b > 0);
        boolean r2 = (a < b) || (b == 5);
        boolean r3 = !(a == b);
        System.out.println("&&: " + r1 + " | ||: " + r2 + " | !: " + r3);

        // Bit a bit
        System.out.println("5 & 3 = " + (5 & 3) + ", 5 | 3 = " + (5 | 3) + ", 5 ^ 3 = " + (5 ^ 3));
        System.out.println("1 << 4 = " + (1 << 4) + ", 64 >> 2 = " + (64 >> 2));
        System.out.println("Binário de 42: " + Integer.toBinaryString(42));

        // Ternário
        String par = (a % 2 == 0) ? "par" : "ímpar";
        System.out.println(a + " é " + par);
    }

    // ------------------------------------------------------------------
    // 03 - Condicionais
    // ------------------------------------------------------------------
    static void topico03Condicionais() {
        titulo("03 - Condicionais");

        int nota = 78;
        if (nota >= 90) {
            System.out.println("Conceito A");
        } else if (nota >= 70) {
            System.out.println("Conceito B");
        } else {
            System.out.println("Conceito C");
        }

        // switch clássico
        int dia = 3;
        switch (dia) {
            case 1:
                System.out.println("Domingo");
                break;
            case 2:
                System.out.println("Segunda");
                break;
            case 3:
                System.out.println("Terça");
                break;
            default:
                System.out.println("Outro dia");
        }

        // switch expression (Java 14+)
        String tipoDia = switch (DayOfWeek.SATURDAY) {
            case SATURDAY, SUNDAY -> "Fim de semana";
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Dia útil";
        };
        System.out.println("Sábado é: " + tipoDia);

        // switch com yield
        int mes = 2;
        int diasNoMes = switch (mes) {
            case 2 -> {
                boolean bissexto = false;
                yield bissexto ? 29 : 28;
            }
            case 4, 6, 9, 11 -> 30;
            default -> 31;
        };
        System.out.println("Dias no mês " + mes + ": " + diasNoMes);
    }

    // ------------------------------------------------------------------
    // 04 - Laços
    // ------------------------------------------------------------------
    static void topico04Lacos() {
        titulo("04 - Laços de repetição");

        // for
        System.out.print("for: ");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // while
        int n = 1;
        System.out.print("while (potências de 2): ");
        while (n <= 64) {
            System.out.print(n + " ");
            n *= 2;
        }
        System.out.println();

        // do-while (executa pelo menos uma vez)
        int k = 10;
        do {
            System.out.println("do-while executou com k=" + k);
            k++;
        } while (k < 10);

        // for-each
        String[] frutas = {"maçã", "banana", "uva"};
        for (String fruta : frutas) {
            System.out.print(fruta + " ");
        }
        System.out.println();

        // break, continue e label
        externo:
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) continue;            // pula j == 2
                if (i == 3) break externo;       // sai dos dois laços
                System.out.println("i=" + i + ", j=" + j);
            }
        }

        // Tabuada
        System.out.println("Tabuada do 7:");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("7 x %2d = %2d%n", i, 7 * i);
        }
    }

    // ------------------------------------------------------------------
    // 05 - Arrays
    // ------------------------------------------------------------------
    static void topico05Arrays() {
        titulo("05 - Arrays");

        int[] numeros = {5, 3, 9, 1, 7};
        System.out.println("Original: " + Arrays.toString(numeros));

        Arrays.sort(numeros);
        System.out.println("Ordenado: " + Arrays.toString(numeros));
        System.out.println("Índice do 7 (busca binária): " + Arrays.binarySearch(numeros, 7));

        int[] copia = Arrays.copyOf(numeros, 8); // completa com zeros
        System.out.println("Cópia maior: " + Arrays.toString(copia));

        int[] preenchido = new int[5];
        Arrays.fill(preenchido, 42);
        System.out.println("Fill: " + Arrays.toString(preenchido));

        // Matriz 2D
        int[][] matriz = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("Matriz:");
        for (int[] linha : matriz) {
            System.out.println(Arrays.toString(linha));
        }
        System.out.println("Diagonal principal:");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print(matriz[i][i] + " ");
        }
        System.out.println();

        // Array irregular (jagged)
        int[][] irregular = new int[3][];
        irregular[0] = new int[]{1};
        irregular[1] = new int[]{1, 2};
        irregular[2] = new int[]{1, 2, 3};
        System.out.println("Irregular: " + Arrays.deepToString(irregular));

        // Soma e média com stream
        System.out.println("Soma: " + Arrays.stream(numeros).sum()
                + " | Média: " + Arrays.stream(numeros).average().orElse(0));
    }

    // ------------------------------------------------------------------
    // 06 - Strings
    // ------------------------------------------------------------------
    static void topico06Strings() {
        titulo("06 - Strings");

        String s = "  Estudando Java é divertido!  ";
        System.out.println("length: " + s.length());
        System.out.println("trim: [" + s.trim() + "]");
        System.out.println("strip: [" + s.strip() + "]");
        System.out.println("upper: " + s.trim().toUpperCase());
        System.out.println("lower: " + s.trim().toLowerCase());
        System.out.println("contains 'Java': " + s.contains("Java"));
        System.out.println("indexOf('J'): " + s.indexOf('J'));
        System.out.println("replace: " + s.trim().replace("Java", "Python"));
        System.out.println("substring(0, 9): " + s.trim().substring(0, 9));
        System.out.println("charAt(0): " + s.trim().charAt(0));

        // split e join
        String csv = "ana,bruno,carla,diego";
        String[] partes = csv.split(",");
        System.out.println("split: " + Arrays.toString(partes));
        System.out.println("join: " + String.join(" | ", partes));

        // Comparação
        String a = "java";
        String b = new String("java");
        System.out.println("a == b: " + (a == b));
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println("equalsIgnoreCase: " + "JAVA".equalsIgnoreCase(a));
        System.out.println("compareTo(\"kotlin\"): " + a.compareTo("kotlin"));

        // StringBuilder (mutável, eficiente em concatenações em laço)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i).append(',');
        }
        sb.setLength(sb.length() - 1); // remove a última vírgula
        System.out.println("StringBuilder: " + sb);
        System.out.println("Reverso: " + sb.reverse());

        // Formatação
        System.out.println(String.format("Nome: %s | Idade: %d | Altura: %.2f", "Maria", 30, 1.6789));
        System.out.println("formatted: " + "%05d".formatted(42));

        // Text block (Java 15+)
        String json = """
                {
                  "nome": "Java",
                  "versao": 17
                }
                """;
        System.out.print(json);

        // Verificar palíndromo
        String palavra = "arara";
        boolean palindromo = new StringBuilder(palavra).reverse().toString().equals(palavra);
        System.out.println("'" + palavra + "' é palíndromo? " + palindromo);

        // Contagem de caracteres
        String texto = "banana";
        Map<Character, Integer> freq = new TreeMap<>();
        for (char ch : texto.toCharArray()) {
            freq.merge(ch, 1, Integer::sum);
        }
        System.out.println("Frequência em 'banana': " + freq);
    }

    // ------------------------------------------------------------------
    // 07 - Métodos (sobrecarga, varargs, recursão)
    // ------------------------------------------------------------------
    static int somar(int a, int b) {
        return a + b;
    }

    static double somar(double a, double b) { // sobrecarga
        return a + b;
    }

    static int somar(int... valores) { // varargs
        int total = 0;
        for (int v : valores) total += v;
        return total;
    }

    static long fatorial(int n) { // recursão
        return (n <= 1) ? 1 : n * fatorial(n - 1);
    }

    static int fibonacci(int n) {
        return (n < 2) ? n : fibonacci(n - 1) + fibonacci(n - 2);
    }

    static boolean ehPrimo(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static void modificarPrimitivo(int x) { // passagem por valor
        x = 999;
    }

    static void modificarArray(int[] arr) { // referência é passada por valor
        arr[0] = 999;
    }

    static void topico07Metodos() {
        titulo("07 - Métodos");

        System.out.println("somar(2, 3) = " + somar(2, 3));
        System.out.println("somar(2.5, 3.5) = " + somar(2.5, 3.5));
        System.out.println("somar(1,2,3,4,5) = " + somar(1, 2, 3, 4, 5));
        System.out.println("fatorial(10) = " + fatorial(10));

        System.out.print("Fibonacci: ");
        for (int i = 0; i < 10; i++) System.out.print(fibonacci(i) + " ");
        System.out.println();

        System.out.print("Primos até 30: ");
        for (int i = 1; i <= 30; i++) if (ehPrimo(i)) System.out.print(i + " ");
        System.out.println();

        int x = 1;
        int[] arr = {1, 2, 3};
        modificarPrimitivo(x);
        modificarArray(arr);
        System.out.println("Primitivo após método: " + x + " (não mudou)");
        System.out.println("Array após método: " + Arrays.toString(arr) + " (mudou!)");

        contadorGlobal++;
        System.out.println("contadorGlobal (static) = " + contadorGlobal);
    }

    // ------------------------------------------------------------------
    // 08 - Orientação a objetos
    // ------------------------------------------------------------------
    static void topico08OrientacaoAObjetos() {
        titulo("08 - Orientação a objetos");

        // Encapsulamento
        Pessoa p = new Pessoa("Carlos", 25);
        p.aniversario();
        System.out.println(p);

        // Herança + polimorfismo
        List<Animal> animais = List.of(new Cachorro("Rex"), new Gato("Mimi"), new Passaro("Piu"));
        for (Animal a : animais) {
            System.out.print(a.getNome() + " diz: ");
            a.emitirSom();
            if (a instanceof Voador v) { // pattern matching
                v.voar();
            }
        }

        // Interface com método default e estático
        Calculadora soma = (x, y) -> x + y;
        System.out.println("Calculadora lambda: " + soma.calcular(4, 6));
        System.out.println("Dobro (default): " + soma.dobro(4, 6));
        System.out.println("Estático: " + Calculadora.info());

        // Static vs instância
        ContadorInstancias.total = 0;
        new ContadorInstancias();
        new ContadorInstancias();
        new ContadorInstancias();
        System.out.println("Instâncias criadas: " + ContadorInstancias.total);

        // equals / hashCode
        Ponto p1 = new Ponto(1, 2);
        Ponto p2 = new Ponto(1, 2);
        System.out.println("p1 == p2: " + (p1 == p2) + " | p1.equals(p2): " + p1.equals(p2)
                + " | mesmo hashCode: " + (p1.hashCode() == p2.hashCode()));

        // Conta bancária com exceção
        ContaBancaria conta = new ContaBancaria("Ana", 100);
        conta.depositar(50);
        try {
            conta.sacar(500);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }
        System.out.println(conta);
    }

    // Classe simples com encapsulamento
    static class Pessoa {
        private final String nome;
        private int idade;

        Pessoa(String nome, int idade) {
            this.nome = nome;
            this.idade = idade;
        }

        void aniversario() {
            idade++;
        }

        @Override
        public String toString() {
            return "Pessoa{nome='" + nome + "', idade=" + idade + "}";
        }
    }

    // Classe abstrata
    static abstract class Animal {
        private final String nome;

        Animal(String nome) {
            this.nome = nome;
        }

        String getNome() {
            return nome;
        }

        abstract void emitirSom();
    }

    interface Voador {
        void voar();
    }

    static class Cachorro extends Animal {
        Cachorro(String nome) {
            super(nome);
        }

        @Override
        void emitirSom() {
            System.out.println("Au au!");
        }
    }

    static class Gato extends Animal {
        Gato(String nome) {
            super(nome);
        }

        @Override
        void emitirSom() {
            System.out.println("Miau!");
        }
    }

    static class Passaro extends Animal implements Voador {
        Passaro(String nome) {
            super(nome);
        }

        @Override
        void emitirSom() {
            System.out.println("Piu piu!");
        }

        @Override
        public void voar() {
            System.out.println("   ...e está voando!");
        }
    }

    @FunctionalInterface
    interface Calculadora {
        int calcular(int a, int b);

        default int dobro(int a, int b) {
            return calcular(a, b) * 2;
        }

        static String info() {
            return "Interface funcional de exemplo";
        }
    }

    static class ContadorInstancias {
        static int total = 0;

        ContadorInstancias() {
            total++;
        }
    }

    static class Ponto {
        final int x, y;

        Ponto(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Ponto)) return false;
            Ponto outro = (Ponto) o;
            return x == outro.x && y == outro.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // Exceção personalizada (checked)
    static class SaldoInsuficienteException extends Exception {
        SaldoInsuficienteException(String msg) {
            super(msg);
        }
    }

    static class ContaBancaria {
        private final String titular;
        private double saldo;

        ContaBancaria(String titular, double saldoInicial) {
            this.titular = titular;
            this.saldo = saldoInicial;
        }

        void depositar(double valor) {
            if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
            saldo += valor;
        }

        void sacar(double valor) throws SaldoInsuficienteException {
            if (valor > saldo) {
                throw new SaldoInsuficienteException(
                        "Saldo insuficiente: tentou sacar " + valor + " mas tem " + saldo);
            }
            saldo -= valor;
        }

        @Override
        public String toString() {
            return String.format("Conta[%s, saldo=%.2f]", titular, saldo);
        }
    }

    // ------------------------------------------------------------------
    // 09 - Enum e Record
    // ------------------------------------------------------------------
    enum Planeta {
        MERCURIO(3.303e+23, 2.4397e6),
        TERRA(5.976e+24, 6.37814e6),
        MARTE(6.421e+23, 3.3972e6);

        private final double massa;
        private final double raio;

        Planeta(double massa, double raio) {
            this.massa = massa;
            this.raio = raio;
        }

        double gravidadeSuperficie() {
            final double G = 6.67300E-11;
            return G * massa / (raio * raio);
        }
    }

    enum Nivel {BAIXO, MEDIO, ALTO}

    // Record: classe imutável concisa (Java 16+)
    record Produto(String nome, double preco, int quantidade) {
        // construtor compacto com validação
        Produto {
            if (preco < 0) throw new IllegalArgumentException("Preço negativo");
        }

        double total() {
            return preco * quantidade;
        }
    }

    static void topico09EnumERecord() {
        titulo("09 - Enum e Record");

        for (Planeta p : Planeta.values()) {
            System.out.printf("%s -> gravidade %.2f m/s²%n", p, p.gravidadeSuperficie());
        }

        Nivel n = Nivel.valueOf("MEDIO");
        System.out.println("Nível: " + n + " | ordinal: " + n.ordinal());

        String msg = switch (n) {
            case BAIXO -> "Tudo tranquilo";
            case MEDIO -> "Atenção";
            case ALTO -> "Alerta!";
        };
        System.out.println(msg);

        // EnumMap
        EnumMap<Nivel, String> mapa = new EnumMap<>(Nivel.class);
        mapa.put(Nivel.ALTO, "vermelho");
        mapa.put(Nivel.BAIXO, "verde");
        System.out.println("EnumMap: " + mapa);

        // Record
        Produto prod = new Produto("Caderno", 12.5, 4);
        System.out.println(prod + " | total = " + prod.total());
        System.out.println("nome() = " + prod.nome());
        System.out.println("equals: " + prod.equals(new Produto("Caderno", 12.5, 4)));
    }

    // ------------------------------------------------------------------
    // 10 - Generics
    // ------------------------------------------------------------------
    static class Caixa<T> {
        private T conteudo;

        Caixa(T conteudo) {
            this.conteudo = conteudo;
        }

        T get() {
            return conteudo;
        }

        void set(T conteudo) {
            this.conteudo = conteudo;
        }
    }

    static class Par<A, B> {
        final A primeiro;
        final B segundo;

        Par(A primeiro, B segundo) {
            this.primeiro = primeiro;
            this.segundo = segundo;
        }

        @Override
        public String toString() {
            return "(" + primeiro + ", " + segundo + ")";
        }
    }

    // Método genérico com limite (bounded type)
    static <T extends Comparable<T>> T maior(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    // Wildcard
    static double somarLista(List<? extends Number> lista) {
        double soma = 0;
        for (Number n : lista) soma += n.doubleValue();
        return soma;
    }

    static void topico10Generics() {
        titulo("10 - Generics");

        Caixa<String> cx = new Caixa<>("texto");
        Caixa<Integer> ci = new Caixa<>(123);
        System.out.println(cx.get() + " | " + ci.get());

        Par<String, Integer> par = new Par<>("idade", 30);
        System.out.println("Par: " + par);

        System.out.println("maior(3, 9) = " + maior(3, 9));
        System.out.println("maior(\"abc\", \"xyz\") = " + maior("abc", "xyz"));

        System.out.println("Soma de List<Integer>: " + somarLista(List.of(1, 2, 3)));
        System.out.println("Soma de List<Double>: " + somarLista(List.of(1.5, 2.5)));
    }

    // ------------------------------------------------------------------
    // 11 - Coleções
    // ------------------------------------------------------------------
    static void topico11Colecoes() {
        titulo("11 - Coleções");

        // List
        List<String> nomes = new ArrayList<>(List.of("Zé", "Ana", "Bia", "Caio"));
        nomes.add("Duda");
        nomes.remove("Zé");
        Collections.sort(nomes);
        System.out.println("ArrayList: " + nomes + " | get(1) = " + nomes.get(1));

        LinkedList<Integer> ll = new LinkedList<>(List.of(1, 2, 3));
        ll.addFirst(0);
        ll.addLast(4);
        System.out.println("LinkedList: " + ll);

        // Set
        Set<Integer> hashSet = new HashSet<>(List.of(3, 1, 2, 3, 1));
        Set<Integer> treeSet = new TreeSet<>(List.of(30, 10, 20, 10));
        Set<String> linked = new LinkedHashSet<>(List.of("c", "a", "b", "a"));
        System.out.println("HashSet: " + hashSet);
        System.out.println("TreeSet (ordenado): " + treeSet);
        System.out.println("LinkedHashSet (inserção): " + linked);

        // Map
        Map<String, Integer> idades = new HashMap<>();
        idades.put("Ana", 30);
        idades.put("Bruno", 25);
        idades.put("Carla", 28);
        idades.putIfAbsent("Ana", 99); // não sobrescreve
        idades.computeIfPresent("Bruno", (k, v) -> v + 1);
        System.out.println("Map: " + idades);
        System.out.println("getOrDefault: " + idades.getOrDefault("Zeca", -1));
        for (Map.Entry<String, Integer> e : new TreeMap<>(idades).entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }

        // Queue / Deque / Stack
        Queue<String> fila = new LinkedList<>();
        fila.offer("primeiro");
        fila.offer("segundo");
        System.out.println("Fila poll: " + fila.poll() + " | peek: " + fila.peek());

        Deque<Integer> pilha = new ArrayDeque<>();
        pilha.push(1);
        pilha.push(2);
        pilha.push(3);
        System.out.println("Pilha pop: " + pilha.pop() + " | restante: " + pilha);

        // PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.addAll(List.of(5, 1, 8, 3));
        System.out.print("PriorityQueue (maior primeiro): ");
        while (!pq.isEmpty()) System.out.print(pq.poll() + " ");
        System.out.println();

        // Comparator
        List<Pessoa2> gente = new ArrayList<>(List.of(
                new Pessoa2("Ana", 30), new Pessoa2("Bruno", 25), new Pessoa2("Carla", 30)));
        gente.sort(Comparator.comparingInt(Pessoa2::idade).reversed().thenComparing(Pessoa2::nome));
        System.out.println("Ordenado por idade desc e nome: " + gente);

        // Iterator (remoção segura)
        List<Integer> numeros = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Iterator<Integer> it = numeros.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0) it.remove();
        }
        System.out.println("Sem pares: " + numeros);

        // Coleções imutáveis
        List<String> imutavel = List.of("a", "b");
        try {
            imutavel.add("c");
        } catch (UnsupportedOperationException e) {
            System.out.println("List.of() é imutável -> UnsupportedOperationException");
        }
    }

    record Pessoa2(String nome, int idade) {
    }

    // ------------------------------------------------------------------
    // 12 - Lambdas e Streams
    // ------------------------------------------------------------------
    static void topico12LambdasEStreams() {
        titulo("12 - Lambdas e Streams");

        // Interfaces funcionais
        Function<Integer, Integer> quadrado = x -> x * x;
        Predicate<Integer> ehPar = x -> x % 2 == 0;
        Consumer<String> imprimir = s -> System.out.println("Consumer: " + s);
        Supplier<Double> aleatorio = () -> 42.0;
        BiFunction<Integer, Integer, Integer> mult = (a, b) -> a * b;
        UnaryOperator<String> maiusculo = String::toUpperCase;

        System.out.println("quadrado(7) = " + quadrado.apply(7));
        System.out.println("ehPar(4) = " + ehPar.test(4));
        imprimir.accept("olá");
        System.out.println("Supplier = " + aleatorio.get());
        System.out.println("mult(6,7) = " + mult.apply(6, 7));
        System.out.println("maiusculo = " + maiusculo.apply("java"));
        System.out.println("Composição andThen: " + quadrado.andThen(x -> x + 1).apply(5));

        // Streams
        List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> paresAoQuadrado = numeros.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Pares ao quadrado: " + paresAoQuadrado);

        int soma = numeros.stream().mapToInt(Integer::intValue).sum();
        double media = numeros.stream().mapToInt(Integer::intValue).average().orElse(0);
        int max = numeros.stream().max(Integer::compare).get();
        System.out.println("soma=" + soma + ", média=" + media + ", max=" + max);

        System.out.println("reduce (produto 1..5): "
                + IntStream.rangeClosed(1, 5).reduce(1, (a, b) -> a * b));

        System.out.println("anyMatch >9: " + numeros.stream().anyMatch(n -> n > 9)
                + " | allMatch >0: " + numeros.stream().allMatch(n -> n > 0));

        // Trabalhando com objetos
        List<Produto> produtos = List.of(
                new Produto("Caneta", 3.5, 100),
                new Produto("Caderno", 15.0, 40),
                new Produto("Mochila", 120.0, 5),
                new Produto("Lápis", 1.5, 200));

        System.out.println("Nomes ordenados por preço: " + produtos.stream()
                .sorted(Comparator.comparingDouble(Produto::preco))
                .map(Produto::nome)
                .collect(Collectors.joining(", ")));

        System.out.println("Valor total do estoque: " + produtos.stream()
                .mapToDouble(Produto::total).sum());

        Map<Boolean, List<String>> baratosECaros = produtos.stream()
                .collect(Collectors.partitioningBy(p -> p.preco() < 10,
                        Collectors.mapping(Produto::nome, Collectors.toList())));
        System.out.println("partitioningBy (preço < 10): " + baratosECaros);

        // groupingBy
        List<String> palavras = List.of("java", "kotlin", "go", "rust", "c", "python", "ruby");
        Map<Integer, List<String>> porTamanho = palavras.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Agrupado por tamanho: " + porTamanho);

        Map<Integer, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.counting()));
        System.out.println("Contagem por tamanho: " + contagem);

        // Outras operações
        System.out.println("distinct + limit + skip: "
                + Stream.of(1, 1, 2, 2, 3, 4, 5, 5).distinct().skip(1).limit(3).toList());
        System.out.println("flatMap: " + Stream.of(List.of(1, 2), List.of(3), List.of(4, 5))
                .flatMap(List::stream).toList());
        System.out.println("iterate: " + Stream.iterate(1, x -> x * 3).limit(6).toList());
        System.out.println("IntStream.range: " + IntStream.range(0, 5).boxed().toList());
    }

    // ------------------------------------------------------------------
    // 13 - Optional
    // ------------------------------------------------------------------
    static Optional<String> buscarUsuario(int id) {
        Map<Integer, String> banco = Map.of(1, "Ana", 2, "Bruno");
        return Optional.ofNullable(banco.get(id));
    }

    static void topico13Optional() {
        titulo("13 - Optional");

        System.out.println("Usuário 1: " + buscarUsuario(1).orElse("desconhecido"));
        System.out.println("Usuário 9: " + buscarUsuario(9).orElse("desconhecido"));
        System.out.println("map: " + buscarUsuario(2).map(String::toUpperCase).orElse("?"));

        buscarUsuario(1).ifPresent(u -> System.out.println("ifPresent: " + u));
        buscarUsuario(9).ifPresentOrElse(
                u -> System.out.println("achou " + u),
                () -> System.out.println("ifPresentOrElse: não achou usuário 9"));

        try {
            buscarUsuario(9).orElseThrow(() -> new NoSuchElementException("Usuário não existe"));
        } catch (NoSuchElementException e) {
            System.out.println("orElseThrow: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    // 14 - Exceções
    // ------------------------------------------------------------------
    static class Recurso implements AutoCloseable {
        private final String nome;

        Recurso(String nome) {
            this.nome = nome;
            System.out.println("  Abrindo " + nome);
        }

        void usar() {
            System.out.println("  Usando " + nome);
        }

        @Override
        public void close() {
            System.out.println("  Fechando " + nome);
        }
    }

    static int dividir(int a, int b) {
        return a / b;
    }

    static void topico14Excecoes() {
        titulo("14 - Exceções");

        // try / catch / finally
        try {
            System.out.println(dividir(10, 0));
        } catch (ArithmeticException e) {
            System.out.println("Erro aritmético: " + e.getMessage());
        } finally {
            System.out.println("finally sempre executa");
        }

        // Múltiplos catch
        String[] entradas = {"42", "abc", null};
        for (String entrada : entradas) {
            try {
                int valor = Integer.parseInt(entrada);
                System.out.println("Convertido: " + valor);
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido: " + entrada);
            }
        }

        // Multi-catch
        try {
            Object o = "texto";
            Integer i = (Integer) o;
        } catch (ClassCastException | NullPointerException e) {
            System.out.println("Multi-catch: " + e.getClass().getSimpleName());
        }

        // Índice fora do limite
        try {
            int[] arr = new int[3];
            arr[5] = 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array: " + e.getMessage());
        }

        // NullPointerException
        try {
            String s = null;
            s.length();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException capturada");
        }

        // try-with-resources
        System.out.println("try-with-resources:");
        try (Recurso r1 = new Recurso("A"); Recurso r2 = new Recurso("B")) {
            r1.usar();
            r2.usar();
        }

        // Relançar com causa
        try {
            try {
                throw new IllegalStateException("erro interno");
            } catch (IllegalStateException e) {
                throw new RuntimeException("erro externo", e);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + " <- causa: " + e.getCause().getMessage());
        }
    }

    // ------------------------------------------------------------------
    // 15 - Classes internas e anônimas
    // ------------------------------------------------------------------
    private int valorInstancia = 10;

    class InternaNaoEstatica {
        int dobro() {
            return valorInstancia * 2; // acessa membros da classe externa
        }
    }

    static class InternaEstatica {
        String oi() {
            return "Olá da classe interna estática";
        }
    }

    interface Saudacao {
        String saudar(String nome);
    }

    static void topico15ClassesInternasEAnonimas() {
        titulo("15 - Classes internas e anônimas");

        EstudoJava externa = new EstudoJava();
        EstudoJava.InternaNaoEstatica interna = externa.new InternaNaoEstatica();
        System.out.println("Interna não estática: " + interna.dobro());
        System.out.println(new InternaEstatica().oi());

        // Classe anônima
        Saudacao anonima = new Saudacao() {
            @Override
            public String saudar(String nome) {
                return "Olá, " + nome + " (classe anônima)";
            }
        };
        System.out.println(anonima.saudar("Maria"));

        // Mesma coisa com lambda
        Saudacao lambda = nome -> "Olá, " + nome + " (lambda)";
        System.out.println(lambda.saudar("João"));

        // Classe local
        class Local {
            String dizer() {
                return "Classe local dentro de método";
            }
        }
        System.out.println(new Local().dizer());
    }

    // ------------------------------------------------------------------
    // 16 - Concorrência
    // ------------------------------------------------------------------
    static int contadorInseguro = 0;
    static synchronized void incrementarSeguro() {
        contadorGlobal++;
    }

    static void topico16Concorrencia() throws Exception {
        titulo("16 - Concorrência");

        // Thread básica
        Thread t = new Thread(() ->
                System.out.println("Executando na thread: " + Thread.currentThread().getName()));
        t.start();
        t.join();

        // synchronized
        contadorGlobal = 0;
        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) incrementarSeguro();
            });
            threads[i].start();
        }
        for (Thread th : threads) th.join();
        System.out.println("Contador com synchronized (esperado 4000): " + contadorGlobal);

        // AtomicInteger
        AtomicInteger atomico = new AtomicInteger();
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futuros = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            final int n = i;
            futuros.add(pool.submit(() -> {
                atomico.addAndGet(n);
                return n * n;
            }));
        }
        int somaQuadrados = 0;
        for (Future<Integer> f : futuros) somaQuadrados += f.get();
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Soma dos quadrados (Future): " + somaQuadrados);
        System.out.println("AtomicInteger (esperado 15): " + atomico.get());

        // CompletableFuture
        CompletableFuture<String> cf = CompletableFuture
                .supplyAsync(() -> "Java")
                .thenApply(s -> s + " assíncrono")
                .thenCombine(CompletableFuture.supplyAsync(() -> 17), (s, v) -> s + " v" + v);
        System.out.println("CompletableFuture: " + cf.get());

        // ConcurrentHashMap
        ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();
        chm.merge("a", 1, Integer::sum);
        chm.merge("a", 1, Integer::sum);
        System.out.println("ConcurrentHashMap: " + chm);

        // Stream paralelo
        long qtdPrimos = IntStream.rangeClosed(1, 10_000).parallel().filter(EstudoJava::ehPrimo).count();
        System.out.println("Primos até 10.000 (parallel stream): " + qtdPrimos);
    }

    // ------------------------------------------------------------------
    // 17 - Datas e Math
    // ------------------------------------------------------------------
    static void topico17DatasEMath() {
        titulo("17 - Datas e Math");

        LocalDate hoje = LocalDate.now();
        LocalDate natal = LocalDate.of(hoje.getYear(), 12, 25);
        System.out.println("Hoje: " + hoje + " (" + hoje.getDayOfWeek() + ")");
        System.out.println("Dias até o Natal: " + ChronoUnit.DAYS.between(hoje, natal));
        System.out.println("Daqui a 30 dias: " + hoje.plusDays(30));
        System.out.println("Ano bissexto? " + hoje.isLeapYear());

        // Math
        System.out.println("Math.sqrt(144) = " + Math.sqrt(144));
        System.out.println("Math.pow(2, 10) = " + Math.pow(2, 10));
        System.out.println("Math.abs(-7) = " + Math.abs(-7));
        System.out.println("Math.round(3.6) = " + Math.round(3.6));
        System.out.println("Math.ceil(3.1) = " + Math.ceil(3.1) + ", floor(3.9) = " + Math.floor(3.9));
        System.out.println("Math.max(3, 8) = " + Math.max(3, 8));
        System.out.println("Área do círculo r=5: " + (PI_APROX * 5 * 5));

        // Random com seed (resultado reproduzível)
        Random rnd = new Random(42);
        System.out.println("Random(42): " + rnd.nextInt(100) + ", " + rnd.nextInt(100) + ", " + rnd.nextInt(100));

        // Overflow seguro
        try {
            Math.addExact(Integer.MAX_VALUE, 1);
        } catch (ArithmeticException e) {
            System.out.println("Math.addExact detectou overflow: " + e.getMessage());
        }

        // Tempo de execução
        long inicio = System.nanoTime();
        long soma = 0;
        for (int i = 0; i < 1_000_000; i++) soma += i;
        long fim = System.nanoTime();
        System.out.println("Soma até 1 milhão = " + soma + " (levou " + (fim - inicio) / 1_000 + " µs)");
    }

    // ------------------------------------------------------------------
    // 18 - Sealed classes e pattern matching
    // ------------------------------------------------------------------
    sealed interface Forma permits Circulo, Retangulo, Triangulo {
    }

    record Circulo(double raio) implements Forma {
    }

    record Retangulo(double largura, double altura) implements Forma {
    }

    record Triangulo(double base, double altura) implements Forma {
    }

    static double area(Forma forma) {
        if (forma instanceof Circulo c) {
            return Math.PI * c.raio() * c.raio();
        } else if (forma instanceof Retangulo r) {
            return r.largura() * r.altura();
        } else if (forma instanceof Triangulo t) {
            return t.base() * t.altura() / 2;
        }
        throw new IllegalArgumentException("Forma desconhecida");
    }

    static void topico18SealedEPatternMatching() {
        titulo("18 - Sealed classes e pattern matching");

        List<Forma> formas = List.of(new Circulo(2), new Retangulo(3, 4), new Triangulo(6, 5));
        for (Forma f : formas) {
            System.out.printf("%s -> área = %.2f%n", f, area(f));
        }

        // Pattern matching com instanceof e condição
        Object[] objetos = {42, "texto", 3.14, List.of(1, 2), null};
        for (Object o : objetos) {
            String descricao;
            if (o instanceof Integer i && i > 10) {
                descricao = "Inteiro grande: " + i;
            } else if (o instanceof String s) {
                descricao = "String de tamanho " + s.length();
            } else if (o instanceof Double d) {
                descricao = "Double: " + d;
            } else if (o instanceof List<?> l) {
                descricao = "Lista com " + l.size() + " itens";
            } else {
                descricao = "Outro (ou null)";
            }
            System.out.println(descricao);
        }
    }
}