import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        
        WordTree arvore = new WordTree();
        arvore.lerArquivo("dicionario.csv");


        Scanner in = new Scanner(System.in);
        
        String caracteres = "";

        

        while (!caracteres.equalsIgnoreCase("N")) {
            System.out.print("\nDigite os caracteres para pesquisa:\n");
            caracteres = in.nextLine();

            List<Palavra> palavras = arvore.searchAll(caracteres);

            if (palavras.isEmpty()) {
                System.out.println("\nNenhuma palavra encontrada com os caracteres fornecidos!\n");
                System.out.println("\nDeseja procurar outra palavra? (S/N)");
                caracteres = in.nextLine();
            } else {
                System.out.println("\nPalavras encontradas:");
                for (int i = 0; i < palavras.size(); i++) {
                    System.out.println((i + 1) + ". " + palavras.get(i).getPalavra());
                }
                System.out.println("Escolha o número da palavra para ver o significado (ou 0 para procurar outra palavra):");
                int escolha = Integer.parseInt(in.nextLine());

                if (escolha == 0) {
                    continue;
                } else if (escolha > 0 && escolha <= palavras.size()) {
                    Palavra palavraEscolhida = palavras.get(escolha - 1);
                    System.out.println("\nSignificado de " + palavraEscolhida.getPalavra() + ":");
                    System.out.println(palavraEscolhida.getSignificado());
                } else {
                    System.out.println("Escolha inválida!");
                }

                System.out.println("\nDeseja procurar outra palavra? (S/N)");
                caracteres = in.nextLine();
            }
        }

        in.close();

        

    }
 
}
