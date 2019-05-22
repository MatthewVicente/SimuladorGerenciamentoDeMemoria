package simuladorgerenciamentodememoria;

public class listaLigada {
    private No inicio;// endereço inicial da lista

    public listaLigada(int tamanho, boolean initializeEmpty){
        if (initializeEmpty) {
            this.inicio = null;
        } else {
            this.inicio = new No(0, null, tamanho);
        }        
    }
    
    public void addInicio( int elemento, int tamanho ){
        this.inicio = new No(elemento,this.inicio,tamanho);
    }
    
    public int remInicio(){
        //Antes de remover devemos verificar se temos pelo menos um Nó na lista,
        // não faz sentido remover algo que não existe, 
        if( !this.isEmpty()){ // se a lista nao esta vazia
            No ant = this.inicio; // guarda a celula que serah removida
            // avançar a referência que aponta para o primeiro  Nó (inicio) 
            // para o próximo Nó da lista
            this.inicio = this.inicio.getproxEnd();
            return ant.getEndereco();
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
    public boolean buscaInter(int x) {
        
        for(No aux=this.inicio; aux!=null;aux=aux.getproxEnd())
            if( aux.getEndereco()==x){
                System.out.println(aux.getTam());
                return true;
            }
        
        return false;
    }
    
    public int getTam(int x) {
        for(No aux=this.inicio; aux!=null;aux=aux.getproxEnd())
            if( aux.getEndereco() == x ){
                return aux.getTam();
            }
        return 0;
    }
    
    // versao recursiva
    public boolean buscaRec(int x ){
        return busca(x, this.inicio);
    }
    private boolean busca(int x, No aux){
        // condicoes de parada
        if( aux == null )
            return false;
        
        if( aux.getEndereco()==x)
            return true;
        
         return busca(x, aux.getproxEnd());
    }

    public void addOrdenado(int x, int tam) {
        No aux = this.inicio;
        No ant = null;
        while( aux != null && aux.getEndereco()<x){
            ant = aux;
            aux = aux.getproxEnd();
        }
        No novo = new No(x, aux,tam);
        if( ant == null )// insere  no inicio
            this.inicio = novo;
        else
            ant.setproxEnd(novo);
        
    }
    public void addFim(int x, int tam) {
        No aux = this.inicio;
        No ant=null;
        while( aux !=null){
            ant = aux;
            aux = aux.getproxEnd();
        }
        No novo = new No(x, null, tam);
        if( ant == null )// insere  no inicio
            this.inicio = novo;
        else
            ant.setproxEnd(novo);
        
    }
    
}

