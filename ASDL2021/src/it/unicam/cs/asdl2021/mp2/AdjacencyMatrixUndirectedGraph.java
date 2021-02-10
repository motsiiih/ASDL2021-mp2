/**
 * 
 */
package it.unicam.cs.asdl2021.mp2;

import java.util.*;

/**
 * Classe che implementa un grafo non orientato tramite matrice di adiacenza.
 * Non sono accettate etichette dei nodi null e non sono accettate etichette
 * duplicate nei nodi (che in quel caso sono lo stesso nodo).
 * 
 * I nodi sono indicizzati da 0 a nodeCoount() - 1 seguendo l'ordine del loro
 * inserimento (0 è l'indice del primo nodo inserito, 1 del secondo e così via)
 * e quindi in ogni istante la matrice di adiacenza ha dimensione nodeCount() *
 * nodeCount(). La matrice, sempre quadrata, deve quindi aumentare di dimensione
 * ad ogni inserimento di un nodo. Per questo non è rappresentata tramite array
 * ma tramite ArrayList.
 * 
 * Gli oggetti GraphNode<L>, cioè i nodi, sono memorizzati in una mappa che
 * associa ad ogni nodo l'indice assegnato in fase di inserimento. Il dominio
 * della mappa rappresenta quindi l'insieme dei nodi.
 * 
 * Gli archi sono memorizzati nella matrice di adiacenza. A differenza della
 * rappresentazione standard con matrice di adiacenza, la posizione i,j della
 * matrice non contiene un flag di presenza, ma è null se i nodi i e j non sono
 * collegati da un arco e contiene un oggetto della classe GraphEdge<L> se lo
 * sono. Tale oggetto rappresenta l'arco. Un oggetto uguale (secondo equals) e
 * con lo stesso peso (se gli archi sono pesati) deve essere presente nella
 * posizione j, i della matrice.
 * 
 * Questa classe non supporta i metodi di cancellazione di nodi e archi, ma
 * supporta tutti i metodi che usano indici, utilizzando l'indice assegnato a
 * ogni nodo in fase di inserimento.
 * 
 * @author Template: Luca Tesei, Implementation: Luca Mozzoni - luca.mozzoni@studenti.unicam.it
 *
 */
public class AdjacencyMatrixUndirectedGraph<L> extends Graph<L> {
    /*
     * Le seguenti variabili istanza sono protected al solo scopo di agevolare
     * il JUnit testing
     */

    // Insieme dei nodi e associazione di ogni nodo con il proprio indice nella
    // matrice di adiacenza
    protected Map<GraphNode<L>, Integer> nodesIndex;

    // Matrice di adiacenza, gli elementi sono null o oggetti della classe
    // GraphEdge<L>. L'uso di ArrayList permette alla matrice di aumentare di
    // dimensione gradualmente ad ogni inserimento di un nuovo nodo.
    protected ArrayList<ArrayList<GraphEdge<L>>> matrix;

    /*
     * NOTA: per tutti i metodi che ritornano un set utilizzare la classe
     * HashSet<E> per creare l'insieme risultato. Questo garantisce un buon
     * funzionamento dei test JUnit che controllano l'uguaglianza tra insiemi
     */
    
    /**
     * Crea un grafo vuoto.
     */
    public AdjacencyMatrixUndirectedGraph() {
        this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
    }

    //Resituisce la dimensione delle set di key della Map, ovvero i nodi
    @Override
    public int nodeCount() {
        return this.nodesIndex.keySet().size();
    }

    //Restituisce la dimensione della lista di edge
    @Override
    public int edgeCount() {
        return this.getEdges().size();
    }

    //Ripristina la matrice di adiacenza e la mappa
    // dei nodi allo stato iniziale
    @Override
    public void clear() {
        this.matrix = new ArrayList<>();
        this.nodesIndex = new HashMap<>();
    }

    //Indica se il grafo è orientato o meno
    @Override
    public boolean isDirected() {
        // Questa classe implementa un grafo non orientato
        return false;
    }

    //Restituisce l'insieme dei nodi
    @Override
    public Set<GraphNode<L>> getNodes() {
        return this.nodesIndex.keySet();
    }

    //Aggiunge un nodo alla mappa associandogli
    // un indice in base al numero di nodi già presenti
    // e aumenta le dimensioni della matrice di conseguenza.
    @Override
    public boolean addNode(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException("Il nodo che si vuole aggiungere è nullo.");
        if (this.nodesIndex.containsKey(node))
            return false;
        this.nodesIndex.put(node, this.nodeCount());
        this.adjustMatrix();
        return true;
    }

    //metodo privato che, dopo l'aggiunta di ogni nodo,
    // aumenta le dimensioni della matrice di adiacenza
    // in modo che sia ad ogni istante una matrice di
    // dimensione nodeCount() * nodeCount()
    private void adjustMatrix() {
        for (ArrayList<GraphEdge<L>> list : this.matrix)
            list.add(null);
        this.matrix.add(new ArrayList<>());
        for (int i = 0; i < this.nodeCount(); i++)
            this.matrix.get(nodeCount() - 1).add(null);
    }

    @Override
    public boolean removeNode(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Remove di nodi non supportata");
    }

    //Scorre l'insieme dei nodi e verifica se è presente
    // un nodo equivalente a quello passato come parametro
    @Override
    public boolean containsNode(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException("Il nodo che si sta cercando all'interno del grafo è nullo.");
        for (GraphNode<L> lGraphNode : this.nodesIndex.keySet()) {
            if (lGraphNode.equals(node))
                return true;
        }
        return false;
    }

    //Scorre l'insieme dei nodi e ritorna un nodo
    // con label equivalente a quella passata come parametro,
    // se esiste
    @Override
    public GraphNode<L> getNodeOf(L label) {
        if (label == null)
            throw new NullPointerException("La label inserita è nulla.");
        for (GraphNode<L> lGraphNode : nodesIndex.keySet()) {
            if (lGraphNode.getLabel().equals(label))
                return lGraphNode;
        }
        return null;
    }

    //Scorre l'insieme dei nodi e ritorna l'indice del nodo
    // con label equivalente a quella passata come parametro,
    // se esiste
    @Override
    public int getNodeIndexOf(L label) {
        if (label == null)
            throw new NullPointerException("La label inserita è nulla.");
        for (GraphNode<L> lGraphNode : nodesIndex.keySet()) {
            if (lGraphNode.getLabel().equals(label))
                return this.nodesIndex.get(lGraphNode);
        }
        throw new IllegalArgumentException("Non esiste alcun nodo associato a questa label.");
    }

    //Scorre l'insieme dei nodi e ritorna il nodo
    // corrispondente all'indice passato come parametro,
    // se esiste
    @Override
    public GraphNode<L> getNodeAtIndex(int i) {
        if (i < 0 || i > this.nodeCount() - 1)
            throw new IndexOutOfBoundsException("L'indice inserito è troppo piccolo o troppo grande.");
        for (GraphNode<L> lGraphNode : nodesIndex.keySet()) {
            if (this.nodesIndex.get(lGraphNode).equals(i))
                return lGraphNode;
        }
        return null;
    }

    //Scorre la lista degli edge di questo grafo e,
    // se uno dei due nodi corrisponde a quello passato come parametro,
    // aggiunge l'altro nodo ad un set che viene poi restituito
    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException("Il nodo di cui si stanno cercando i nodi adiacenti è nullo.");
        if (!this.containsNode(node))
            throw new IllegalArgumentException(
                    "Il nodo di cui sistanno cercando i nodi adiacenti non esiste in questo grafo.");
        Set<GraphNode<L>> adjNodes = new HashSet<>();
        for (GraphEdge<L> lGraphEdge : this.getEdges()) {
            if (lGraphEdge.getNode1().equals(node))
                adjNodes.add(lGraphEdge.getNode2());
            else if (lGraphEdge.getNode2().equals(node))
                adjNodes.add(lGraphEdge.getNode1());
        }
        return adjNodes;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    //Scorre la matrice contenente gli edges e li
    // aggiunge ad un set che viene poi restituito
    @Override
    public Set<GraphEdge<L>> getEdges() {
        Set<GraphEdge<L>> edges = new HashSet<>();
        for (ArrayList<GraphEdge<L>> graphEdges : this.matrix) {
            for (GraphEdge<L> graphEdge : graphEdges) {
                if (graphEdge != null)
                    edges.add(graphEdge);
            }
        }
        return edges;
    }

    //Aggiunge l'edge passato come parametro alla matrice degli edges
    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        if (edge == null)
            throw new NullPointerException("L'arco che si vuole aggiunere è nullo.");
        if (!this.containsNode(edge.getNode1()) || !this.containsNode(edge.getNode2()))
            throw new IllegalArgumentException("Almeno uno dei due nodi associati all'arco non esiste.");
        if (edge.isDirected())
            throw new IllegalArgumentException(
                    "Tentativo di inserire un arco orientato in un grafo non orientato.");
        if (this.containsEdge(edge))
            return false;
        //Restituisce la lista con indice equivalente
        // all'indice corrispondente al primo nodo dell'edge
        this.matrix.get(this.nodesIndex.get(edge.getNode1()))
                //Imposta l'edge alla posizione con indice equivalente
                // all'indice corrispondente al secondo nodo dell'edge
                .set(this.nodesIndex.get(edge.getNode2()), edge);
        //Restituisce la lista con indice equivalente
        // all'indice corrispondente al secondo nodo dell'edge
        this.matrix.get(this.nodesIndex.get(edge.getNode2()))
                //Imposta l'edge alla posizione con indice equivalente
                // all'indice corrispondente al primo nodo dell'edge
                .set(this.nodesIndex.get(edge.getNode1()), edge);
        return true;
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        throw new UnsupportedOperationException(
                "Operazione di remove non supportata in questa classe");
    }

    //Scorre la lista degli edge di questo grafo e
    // verifica se l'edge passato come parametro è
    // contenuto in tale lista
    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        if (edge == null)
            throw new NullPointerException("L'arco che si vuole cercare è nullo.");
        if (!this.containsNode(edge.getNode1()) || !this.containsNode(edge.getNode2()))
            throw new IllegalArgumentException("Almeno uno dei due nodi associati all'arco non esiste.");
        for (GraphEdge<L> lGraphEdge : this.getEdges()) {
            if (lGraphEdge.equals(edge))
                return true;
        }
        return false;
    }

    //Scorre la lista degli edge di questo grafo e restituisce
    // un set contenente gli edge che hanno il primo nodo o il
    // secondo nodo equivalente a quello passato come parametro
    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException("Il nodo di cui cerchi gli archi è nullo.");
        if (!this.containsNode(node))
            throw new IllegalArgumentException("Il nodo di cui cerchi gli archi non esiste.");
        Set<GraphEdge<L>> nodeEdges = new HashSet<>();
        for (GraphEdge<L> edge : this.getEdges()) {
            if (edge.getNode1().equals(node) || edge.getNode2().equals(node))
                nodeEdges.add(edge);
        }
        return nodeEdges;
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }
}
