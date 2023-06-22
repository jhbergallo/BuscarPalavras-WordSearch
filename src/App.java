import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
public class App {
    public static void main(String[] args) {
        
        ArrayList<Palavra> lista = new ArrayList<>();
        String linhas[] = new String[200];
        int numlinhas = 0;
        Path filePath = Paths.get("dicionario.csv");
        
        //ler o arquivo
        try(BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("UTF-8"))){
            String line = reader.readLine();
            while(line != null){
                linhas[numlinhas] = line;
                numlinhas++;
                line = reader.readLine();
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.err.format("Erro na leitura do arquivo");
        }
        for(int i = 0; i < numlinhas; i++){

            String[] campos = linhas[i].split(";");

            String palavra = campos[0];
            String significado = campos[1];

            Palavra palavras = new Palavra(palavra, significado);
            lista.add(palavras);

        }
        
        System.out.println(lista);
        
    }
 
}
