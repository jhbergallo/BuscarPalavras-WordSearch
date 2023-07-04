import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        int index = 0;
        Scanner in = new Scanner(System.in);
        
        String caracteres = "";
        while(index != 1 && index != 2){
            System.out.println("\nEscolha sua língua: / Choose your language:");
            System.out.println("\n1. Português     2. English");
            index = in.nextInt();
            in.nextLine();
            if(index != 1 && index != 2){
                System.out.println("\nEscolha entre os dois! / Choose between the two!\n");   
            }
        }
        
        if(index == 1){
            WordTree arvore = new WordTree();
            arvore.lerArquivo("dicionario.csv");
            while (!caracteres.equalsIgnoreCase("N")) {
                System.out.println("\n----- Busca de Palavras -----\n");
                System.out.print("Digite os caracteres para pesquisa: ");
                caracteres = in.nextLine();

                List<Palavra> palavras = arvore.searchAll(caracteres);

                if (caracteres.matches(".*\\d.*")) {
                    System.out.println("\nVocê inseriu um número! Insira apenas letras.\n");
                } else {
                    if (palavras.isEmpty()) {
                        System.out.println("\nNenhuma palavra encontrada com os caracteres fornecidos!\n");
                        System.out.print("Deseja procurar outra palavra? (S/N): ");
                        caracteres = in.nextLine();
                    } else {
                        System.out.println("\nPalavras encontradas:");
                        for (int i = 0; i < palavras.size(); i++) {
                            System.out.println((i + 1) + ". " + palavras.get(i).getPalavra());
                        }
                        System.out.println("\nEscolha o número da palavra para ver o significado (0 para procurar outra palavra ou digite N para sair):\n");
                        System.out.print("Opção: ");
                        String n = in.nextLine();
                        if (n.equalsIgnoreCase("N")) {
                            break;
                        }
                        int escolha = Integer.parseInt(n);
                        if (escolha == 0) {
                            continue;
                        } else if (escolha > 0 && escolha <= palavras.size()) {
                            Palavra palavraEscolhida = palavras.get(escolha - 1);
                            System.out.println("\nSignificado de " + palavraEscolhida.getPalavra() + ":");
                            System.out.println(palavraEscolhida.getSignificado() + "\n");
                        } else {
                            System.out.println("\nEscolha inválida!");
                        }

                        System.out.print("Deseja procurar outra palavra? (S/N):\n");
                        System.out.print("Opção: ");
                        caracteres = in.nextLine();
                        System.out.println("\n\n");
                    }
                }
            } 
            System.out.println("\n------Fechando a busca... Volte novamente!------\n");
        } else if(index == 2){
            WordTree arvore = new WordTree();
            arvore.lerArquivo("dictionary.csv");
            while (!caracteres.equalsIgnoreCase("N")) {
                System.out.println("\n----- Word Search -----\n");
                System.out.print("Type the characters for the search: ");
                caracteres = in.nextLine();

                List<Palavra> palavras = arvore.searchAll(caracteres);

                if (caracteres.matches(".*\\d.*")) {
                    System.out.println("\nYou typed a number! Insert only letters.\n");
                } else {
                    if (palavras.isEmpty()) {
                        System.out.println("\nThere were no words found with the characters provided!\n");
                        System.out.print("Do you want to search for another word? (Y/N): ");
                        caracteres = in.nextLine();
                    } else {
                        System.out.println("\nWords found:");
                        for (int i = 0; i < palavras.size(); i++) {
                            System.out.println((i + 1) + ". " + palavras.get(i).getPalavra());
                        }
                        System.out.println("\nChoose the number for the word to see its meaning(Type 0 to search for another word or N to close)\n");
                        System.out.print("Option: ");
                        String n = in.nextLine();
                        if (n.equalsIgnoreCase("N")) {
                            break;
                        }
                        int escolha = Integer.parseInt(n);
                        if (escolha == 0) {
                            continue;
                        } else if (escolha > 0 && escolha <= palavras.size()) {
                            Palavra palavraEscolhida = palavras.get(escolha - 1);
                            System.out.println("\nMeaning of " + palavraEscolhida.getPalavra() + ":");
                            System.out.println(palavraEscolhida.getSignificado() + "\n");
                        } else {
                            System.out.println("\nInvalid choice!");
                        }

                        System.out.print("Do you want to search for another word? (Y/N):\n");
                        System.out.print("Option: ");
                        caracteres = in.nextLine();
                        System.out.println("\n\n");
                    }
                }
            }
            System.out.println("\n------Closing the search... Come back again!------\n");
        }
        in.close();

    }
 
}
