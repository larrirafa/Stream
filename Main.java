import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> lista = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o nome e sexo separados por traço (Ex: Maria-Feminino).");

        // Loop que roda continuamente até o usuário decidir parar
        while (true) {
            System.out.print("Digite os dados: ");
            String nomeESexo = scan.nextLine();
            lista.add(nomeESexo); // Adiciona na lista principal

            System.out.print("Deseja adicionar alguém mais? (SIM/NAO): ");
            String confirmacao = scan.nextLine().toUpperCase();

            // Se não for "SIM", quebra o laço de repetição
            if (!confirmacao.equals("SIM")) {
                break;
            }
        }

        ArrayList<String> listaMulheres = filtrarMulheres(lista);

        // Exibindo o resultado
        System.out.println("\n--- Lista de Mulheres ---");
        listaMulheres.forEach(System.out::println);

        scan.close(); // Boa prática fechar o scanner
    }

    /**
     * Filtra uma lista de entradas no formato "Nome-Sexo" e retorna apenas
     * aquelas cujo sexo comece com "f" (ex: "Feminino", "F", "feminino").
     * Extraído do main() para poder ser testado isoladamente com JUnit.
     */
    public static ArrayList<String> filtrarMulheres(List<String> lista) {
        return new ArrayList<>(
                lista.stream()
                        .filter(p -> {
                            String[] partes = p.split("-");
                            return partes.length > 1
                                    && partes[1].trim().toLowerCase().startsWith("f");
                        })
                        .toList()
        );
    }
}
