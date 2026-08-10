import java.util.ArrayList;
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

        // Filtragem com Lambda (corrigido o operador ->)
        ArrayList<String> listaMulheres = new ArrayList<>(
            lista.stream()
                .filter(p -> {
                    String[] partes = p.split("-");
                    return partes.length > 1 && partes[1].trim().toLowerCase().startsWith("f");
                })
                .toList()
        );

        // Exibindo o resultado (corrigido o nome da variável para listaMulheres)
        System.out.println("\n--- Lista de Mulheres ---");
        listaMulheres.forEach(System.out::println);

        scan.close(); // Boa prática fechar o scanner
    }
}
