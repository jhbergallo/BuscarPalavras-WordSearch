import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        
        WordTree arvore = new WordTree();
        arvore.lerArquivo("dicionario.csv");

        Scanner in = new Scanner(System.in);
        List<Palavra> palavras = new ArrayList<>();
        int escolha = 0;
        int index = 0;
        String palavra = "";
       
        while(escolha != 4){
             
            System.out.println();                                            
            System.out.println("======================================================");
            System.out.println("|                       |MENU|                       |");
            System.out.println("======================================================");
            System.out.println("|1 - Procurar uma palavra                            |");
            System.out.println("|2 - Lista com maior sinalizção                      |");
            System.out.println("|3 - Mês com maior implementação de placas           |");
            System.out.println("|4 - Sair do sistema                                 |");
            System.out.printf("|____________________________________________________|");
            System.out.println();
           
            escolha = in.nextInt();
            in.nextLine();

            switch(escolha){
                
                case 1: 
                System.out.println("Digite os caracteres a serem buscados");
                    palavra = in.nextLine();
                    palavras = arvore.searchAll(palavra);
                    for(Palavra pal : palavras){
                        System.out.println(pal.toString());
                    }
                break;
            }
        }

        

    }
 
}
