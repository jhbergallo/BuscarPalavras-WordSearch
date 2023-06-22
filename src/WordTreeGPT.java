import java.util.ArrayList;
import java.util.List;

public class WordTreeGPT {
    // Classe interna
    private class CharNode {
        
        private char character;
        private String significado;
        private boolean isFinal;
        private CharNode father;
        private List<CharNode> children;

        public CharNode(char character) {
            this.character = character;
            this.significado = null;
            this.isFinal = false;
            this.father = null;
            this.children = new ArrayList<>();
        }

        public CharNode(char character, boolean isFinal) {
            this.character = character;
            this.significado = null;
            this.isFinal = isFinal;
            this.father = null;
            this.children = new ArrayList<>();
        }

        /**
         * Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
         *
         * @param character - caracter a ser adicionado
         * @param isfinal   - se é final da palavra ou não
         */
        public CharNode addChild(char character, boolean isfinal) {
            CharNode child = findChildChar(character);
            if (child != null) {
                // O caractere já existe como filho
                if (child.isFinal && !isfinal) {
                    // Se o filho existente for final da palavra e o novo não for,
                    // atualiza o estado de final da palavra do filho existente
                    child.isFinal = false;
                }
                return child;
            } else {
                // O caractere não existe como filho, cria um novo filho
                CharNode newChild = new CharNode(character, isfinal);
                newChild.father = this;
                children.add(newChild);
                return newChild;
            }
        }

        public int getNumberOfChildren() {
            return children.size();
        }

        public CharNode getChild(int index) {
            if (index >= 0 && index < children.size()) {
                return children.get(index);
            } else {
                return null;
            }
        }

        /**
         * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore.
         *
         * @return a palavra
         */
        private String getWord() {
            StringBuilder wordBuilder = new StringBuilder();
            CharNode current = this;
            while (current != null) {
                wordBuilder.insert(0, current.character);
                current = current.father;
            }
            return wordBuilder.toString();
        }

        /**
         * Encontra e retorna o nodo que tem determinado caracter.
         *
         * @param character - caracter a ser encontrado.
         */
        public CharNode findChildChar(char character) {
            for (CharNode child : children) {
                if (child.character == character) {
                    return child;
                }
            }
            return null;
        }

        public String getSignificado() {
            return significado;
        }

        public void setSignificado(String significado) {
            this.significado = significado;
        }

        public boolean isFinal() {
            return isFinal;
        }

        public void setFinal(boolean isFinal) {
            this.isFinal = isFinal;
        }
    }

    // Atributos
    private CharNode root;
    private int totalNodes = 0;
    private int totalWords = 0;

    // Construtor
    public WordTree() {
        this.root = new CharNode('\0');
    }

    // Métodos
    public int getTotalWords() {
        return totalWords;
    }

    public int getTotalNodes() {
        return totalNodes;
    }

    /**
     * Adiciona uma palavra na estrutura em árvore.
     *
     * @param word - palavra a ser adicionada
     */
    public void addWord(String word) {
        CharNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            boolean isFinal = (i == word.length() - 1);
            current = current.addChild(character, isFinal);
            totalNodes++;
        }
        if (!current.isFinal) {
            // Se o último caractere não for final da palavra, atualiza para final
            current.isFinal = true;
            totalWords++;
        }
    }

    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra.
     *
     * @param word - palavra a ser encontrada
     * @return o nodo final encontrado
     */
    private CharNode findCharNodeForWord(String word) {
        CharNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            current = current.findChildChar(character);
            if (current == null) {
                // Caractere não encontrado, palavra não existe na árvore
                return null;
            }
        }
        return current;
    }

    /**
     * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
     * Tipicamente, um método recursivo.
     *
     * @param prefix - prefixo das palavras a serem pesquisadas
     * @return lista de palavras com o prefixo dado
     */
    public List<String> searchAll(String prefix) {
        List<String> result = new ArrayList<>();
        CharNode prefixNode = findCharNodeForWord(prefix);
        if (prefixNode != null) {
            traverseWords(prefixNode, result);
        }
        return result;
    }

    private void traverseWords(CharNode node, List<String> result) {
        if (node.isFinal) {
            result.add(node.getWord());
        }
        for (CharNode child : node.children) {
            traverseWords(child, result);
        }
    }
}

}
