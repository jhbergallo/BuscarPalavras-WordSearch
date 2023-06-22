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
        public CharNode(char character, boolean isFinal) {
            this.character = character;
            this.significado = null;
            this.isFinal = isFinal;
            this.father = null;
            this.children = new ArrayList<CharNode>();
        }

        
        //Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
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
        
        public int getNumberOfChildren () {
            return -1;
        }
        
        public CharNode getChild (int index) {
            return null;
        }

        /**
         * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
         * @return a palavra
         */
        private String getWord() {
            return null;
        }
        
        /**
        * Encontra e retorna o nodo que tem determinado caracter.
        * @param character - caracter a ser encontrado.
        */
        public CharNode findChildChar (char character) {
            return null;
        }
        
    }


    
    // Atributos
    private CharNode root;
    private int totalNodes = 0;
    private int totalWords = 0;
    


    // Construtor
    public WordTree() {
      
    }


    
    // Metodos
    public int getTotalWords() {
        return 1;
    }

    public int getTotalNodes() {
        return 1;
    }
    
    /**
    *Adiciona palavra na estrutura em árvore
    *@param word
    */
    public void addWord(String word) {
        
    }
    
    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     * @param word
     * @return o nodo final encontrado
     */
    private CharNode findCharNodeForWord(String word) {
        return null;
    }

    /**
    * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
    * Tipicamente, um método recursivo.
    * @param prefix
    */
    public List<String> searchAll(String prefix) {
        return null;
    }   

}
