package simuladorgerenciamentodememoria;

public class listaLigada {
    private No inicio;// endereço inicial da lista

    public listaLigada(int tamanho){
        this.inicio = new No(0, null, tamanho);
    }
    public void addInicio( int elemento ){
        this.inicio = new No(elemento,this.inicio);
    }
    
    public int remInicio(){
        //Antes de remover devemos verificar se temos pelo menos um Nó na lista,
        // não faz sentido remover algo que não existe, 
        if( !this.isEmpty()){ // se a lista nao esta vazia
            No ant = this.inicio; // guarda a celula que serah removida
            // avançar a referência que aponta para o primeiro  Nó (inicio) 
            // para o próximo Nó da lista
            this.inicio = this.inicio.getProx();
            return ant.getElemento();
        }
        else
            throw new RuntimeException("lista vazia!");

    }
    public boolean isEmpty(){
        return this.inicio == null;
    } 

    @Override
    public String toString() {
        return this.inicio+"";
    }   
    // versao iterativa
    public boolean busca(int x) {
        
        for(No aux=this.inicio; aux!=null;aux=aux.getProx())
            if( aux.getElemento()==x)
                return true;
        
        return false;
    }
    // versao recursiva
    public boolean buscaRec(int x ){
        return busca(x, this.inicio);
    }
    private boolean busca(int x, No aux){
        // condicoes de parada
        if( aux == null )
            return false;
        
        if( aux.getElemento()==x)
            return true;
        
         return busca(x, aux.getProx());
    }

    public void addOrdenado(int x) {
        No aux = this.inicio;
        No ant=null;
        while( aux !=null&&aux.getElemento()<x){
            ant = aux;
            aux = aux.getProx();
        }
        No novo = new No(x, aux);
        if( ant == null )// insere  no inicio
            this.inicio = novo;
        else
            ant.setProx(novo);
        
    }
    public void addFim(int x) {
        No aux = this.inicio;
        No ant=null;
        while( aux !=null){
            ant = aux;
            aux = aux.getProx();
        }
        No novo = new No(x, null);
        if( ant == null )// insere  no inicio
            this.inicio = novo;
        else
            ant.setProx(novo);
        
    }
    
}

