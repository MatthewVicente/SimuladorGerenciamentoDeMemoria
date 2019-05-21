
package simuladorgerenciamentodememoria;

public class No {
    private int endereco;
    private int tam;
    private No proxEnd;

    public No(int endereco, No proxEnd, int tam) {
        this.endereco = endereco;
        this.proxEnd = proxEnd;
        this.tam = tam;
    }

    public int getendereco() {
        return endereco;
    }

    public No getproxEnd() {
        return proxEnd;
    }

    public void setendereco(int endereco) {
        this.endereco = endereco;
    }

    public void setproxEnd(No proxEnd) {
        this.proxEnd = proxEnd;
    }

    @Override
    public String toString() {
        return "{" + this.endereco + "," + this.proxEnd + '}';
    }    
}
