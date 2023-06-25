import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class WordTree {
    // Classe interna
    private class CharNode {
        private char character;
	    private String significado;
        private boolean isFinal;
        private CharNode father;
        private List<CharNode> children;

        public CharNode(char character){
            this.character = character;
            this.significado = null;
            this.isFinal = false;
            this.father = null;
            this.children = new ArrayList<CharNode>();
        }
        public CharNode(char character, boolean isFinal){
            this.character = character;
            this.significado = null;
            this.isFinal = isFinal;
            this.father = null;
            this.children = new ArrayList<CharNode>();
        }

        //Adiciona um filho(caracter) no nodo. Não pode aceitar caracteres repetidos.
        //@param character - caracter a ser adicionado
        //@param isfinal - se é final da palavra ou não
        public CharNode addChild (char character, boolean isfinal) {
            CharNode child = findChildChar(character);
            if (child != null) { // O caractere já existe como filho 
                if (child.isFinal && !isfinal) {
                    // Se o filho for final da palavra e o novo não for,
                    // atualiza o estado de final da palavra do filho existente
                    child.isFinal = false;
                }
                return child;
            } 
            else { // O caractere não existe como filho 
                CharNode newChild = new CharNode(character, isfinal);
                newChild.father = this;
                children.add(newChild);
                return newChild;
            }
        }
        
        public int getNumberOfChildren (){
            return this.children.size();
        }

        public CharNode getChild (int index){
            if(index>=0 && index<children.size()){ // indice valido ?
                return children.get(index);
            } 
            else {
                return null;
            }
        }

        //Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
        //@return a palavra
        private Palavra getWord(){
            StringBuilder word = new StringBuilder();
            CharNode current = this;
            while (current != null) {
                word.insert(0, current.character);
                current = current.father;
            }
            Palavra palavra = new Palavra(word.toString(), current.toString());
            return palavra;
        }
        
        //Encontra e retorna o nodo que tem determinado caracter.
        //@param character - caracter a ser encontrado.
        public CharNode findChildChar (char character){
            for (CharNode child : children){
                if (child.character == character) {
                    return child;
                }
            }
            return null;
        }
       
        public String getSignificado(){
            return significado;
        }

        public void setSignificado(String significado){
            this.significado = significado;
        }

        public boolean isFinal(){
            return isFinal;
        }

        public void setFinal(boolean isFinal){
            this.isFinal = isFinal;
        }
    }

    //Atributos
    private CharNode root;
    private int totalNodes = 0;
    private int totalWords = 0;
    
    //Construtor
    public WordTree(){
      this.root = new CharNode(' ');
    }

    //Metodos
    public int getTotalWords(){
        return totalWords;
    }
    public int getTotalNodes(){
        return totalNodes;
    }
    
    //Adiciona palavra na estrutura em árvore
    //param word
    public void addWord(String palavra) {
        CharNode current = root;
        
        for(int i = 0; i < palavra.length(); i++){
            char character = palavra.charAt(i);
            boolean isFinal = (i == palavra.length() - 1);
            current = current.addChild(character, isFinal);
            totalNodes++;
        }
        
        if(!current.isFinal){ // Se o último caractere não for final da palavra, atualiza para final
            current.isFinal = true;
            totalWords++;
        }
    }
    
    //Vai descendo na árvore até onde conseguir encontrar a palavra
    //@param word
    //@return o nodo final encontrado
    private CharNode findCharNodeForWord(String palavra) {
        CharNode current = root;
        
        for (int i = 0; i < palavra.length(); i++){
            char character = palavra.charAt(i);
            current = current.findChildChar(character);
            if (current == null){ // Caractere não encontrado, palavra não existe na árvore
                return null;
            }
        }
        return current;
    }

    //Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
    //Tipicamente, um método recursivo.
    //@param prefix
    public List<Palavra> searchAll(String prefix) {
        List<Palavra> lista = new ArrayList<>();
        
        CharNode prefixNode = findCharNodeForWord(prefix);
        if (prefixNode != null){
            searchAll(prefixNode, lista);
        }
        return lista;
    }
    private void searchAll(CharNode node, List<Palavra> lista) {
        if(node.isFinal){
            lista.add(node.getWord());
        }
        for (CharNode child : node.children){
            searchAll(child, lista);
        }
    } 

    public void lerArquivo(String arquivo){
        String linhas[] = new String[200];
        int numlinhas = 0;
        Path filePath = Paths.get(arquivo);
        
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
            addWord(palavra);
        
        }
        
    }

}
