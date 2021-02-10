/**
 * 
 */
package it.unicam.cs.asdl2021.mp2;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Implementazione della classe astratta {@code Graph<L>} che realizza un grafo
 * orientato. Non sono accettate etichette dei nodi null e non sono accettate
 * etichette duplicate nei nodi (che in quel caso sono lo stesso nodo).
 * 
 * Per la rappresentazione viene usata una variante della rappresentazione con
 * liste di adiacenza. A differenza della rappresentazione standard si usano
 * strutture dati più efficienti per quanto riguarda la complessità in tempo
 * della ricerca se un nodo è presente (pseudocostante, con tabella hash) e se
 * un arco è presente (pseudocostante, con tabella hash). Lo spazio occupato per
 * la rappresentazione risultà tuttavia più grande di quello che servirebbe con
 * la rappresentazione standard.
 * 
 * Le liste di adiacenza sono rappresentate con una mappa (implementata con
 * tabelle hash) che associa ad ogni nodo del grafo i nodi adiacenti. In questo
 * modo il dominio delle chiavi della mappa è l'insieme dei nodi, su cui è
 * possibile chiamare il metodo contains per testare la presenza o meno di un
 * nodo. Ad ogni chiave della mappa, cioè ad ogni nodo del grafo, non è
 * associata una lista concatenata dei nodi collegati, ma un set di oggetti
 * della classe GraphEdge<L> che rappresentano gli archi uscenti dal nodo: in
 * questo modo la rappresentazione riesce a contenere anche l'eventuale peso
 * dell'arco (memorizzato nell'oggetto della classe GraphEdge<L>). Per
 * controllare se un arco è presenta basta richiamare il metodo contains in
 * questo set. I test di presenza si basano sui metodi equals ridefiniti per
 * nodi e archi nelle classi GraphNode<L> e GraphEdge<L>.
 * 
 * Questa classe non supporta le operazioni di rimozione di nodi e archi e le
 * operazioni indicizzate di ricerca di nodi e archi.
 * 
 * @author Template: Luca Tesei, Implementation: Luca Mozzoni - luca.mozzoni@studenti.unicam.it
 *
 * @param <L>
 *                etichette dei nodi del grafo
 */
public class MapAdjacentListDirectedGraph<L> extends Graph<L> {

    /*
     * Le liste di adiacenza sono rappresentate con una mappa. Ogni nodo viene
     * associato con l'insieme degli archi uscenti. Nel caso in cui un nodo non
     * abbia archi uscenti è associato con un insieme vuoto. La variabile
     * istanza è protected solo per scopi di test JUnit.
     */
    protected final Map<GraphNode<L>, Set<GraphEdge<L>>> adjacentLists;

    /*
     * NOTA: per tutti i metodi che ritornano un set utilizzare la classe
     * HashSet<E> per creare l'insieme risultato. Questo garantisce un buon
     * funzionamento dei test JUnit che controllano l'uguaglianza tra insiemi
     */
    
    /**
     * Crea un grafo vuoto.
     */
    public MapAdjacentListDirectedGraph() {
        // Inizializza la mappa con la mappa vuota
        this.adjacentLists = new HashMap<GraphNode<L>, Set<GraphEdge<L>>>();
    }

    //Restituisce la dimensione della lista di
    // chiavi della mappa, ovvero i nodi
    @Override
    public int nodeCount() {
        return this.adjacentLists.size();
    }

    //Restituisce il numero totale di edge presenti
    // nel set puntato da ogni nodo della mappa
    @Override
    public int edgeCount() {
        int count = 0;
        for (GraphNode<L> lGraphNode : adjacentLists.keySet()) {
            count += this.adjacentLists.get(lGraphNode).size();
        }
        return count;
    }

    //Ripristina la lista di adiacenza allo stato iniziale
    @Override
    public void clear() {
        this.adjacentLists.clear();
    }

    //Indica se il grafo è orientato o meno
    @Override
    public boolean isDirected() {
        // Questa classe implementa grafi orientati
        return true;
    }

    //Restituisce la lista di chiavi della mappa, ovvero i nodi
    @Override
    public Set<GraphNode<L>> getNodes() {
        return this.adjacentLists.keySet();
    }

    //Aggiunge un nodo alla lista di chiavi della mappa
    @Override
    public boolean addNode(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException("Il nodo che si sta cercando di inserire è nullo.");
        if (this.adjacentLists.containsKey(node))
            return false;
        this.adjacentLists.put(node, new HashSet<>());
        return true;
    }

    @Override
    public boolean removeNode(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Rimozione dei nodi non supportata");
    }

    //Scorre la lista di chiavi della mappa e verifica
    // se il nodo passato come parametro è presente o meno
    @Override
    public boolean containsNode(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException("Il nodo che si sta cercando all'interno del grafo è nullo.");
        for (GraphNode<L> lGraphNode : adjacentLists.keySet()) {
            if (lGraphNode.equals(node))
                return true;
        }
        return false;
    }

    //Scorre la lista di chiavi della mappa e
    // restituisce il nodo con label uguale a
    // quella passata come parametro
    @Override
    public GraphNode<L> getNodeOf(L label) {
        if (label == null)
            throw new NullPointerException("La label di cui si sta cercando il nodo è nulla.");
        for (GraphNode<L> lGraphNode : adjacentLists.keySet()) {
            if (lGraphNode.getLabel().equals(label))
                return lGraphNode;
        }
        return null;
    }

    @Override
    public int getNodeIndexOf(L label) {
        throw new UnsupportedOperationException(
                "Ricerca dei nodi con indice non supportata");
    }

    @Override
    public GraphNode<L> getNodeAtIndex(int i) {
        throw new UnsupportedOperationException(
                "Ricerca dei nodi con indice non supportata");
    }

    //Scorre il set di edge puntati dal nodo passato come parametro,
    // aggiunge i secondi nodi di ogni edge (ovvero i nodi in cui
    // tali archi entrano) ad un set e lo restituisce
    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException("Il nodo di cui si stanno cercando i nodi adiacenti è nullo.");
        if (!this.containsNode(node))
            throw new IllegalArgumentException(
                    "Il nodo di cui sistanno cercando i nodi adiacenti non esiste in questo grafo.");
        Set<GraphNode<L>> adjNodes = new HashSet<>();
        for (GraphEdge<L> lGraphEdge : this.adjacentLists.get(node)) {
            adjNodes.add(lGraphEdge.getNode2());
        }
        return adjNodes;
    }

    //Scorre il set di edge entranti nel nodo passato come parametro,
    // aggiunge i primi nodi di ogni edge (ovvero i nodi da cui
    // tali archi escono) ad un set e lo restituisce
    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException("Il nodo di cui si stanno cercando i nodi adiacenti è nullo.");
        if (!this.containsNode(node))
            throw new IllegalArgumentException(
                    "Il nodo di cui si stanno cercando i nodi adiacenti non esiste in questo grafo.");
        Set<GraphNode<L>> preNodes = new HashSet<>();
        for (GraphEdge<L> lGraphEdge : this.getIngoingEdgesOf(node)) {
            preNodes.add(lGraphEdge.getNode1());
        }
        return preNodes;
    }

    //Scorre tutti i set puntati dai nodi della mappa e
    // li aggiunge ad un set che viene poi restituito
    @Override
    public Set<GraphEdge<L>> getEdges() {
        Set<GraphEdge<L>> edges = new HashSet<>();
        for (GraphNode<L> lGraphNode : adjacentLists.keySet()) {
            edges.addAll(this.adjacentLists.get(lGraphNode));
        }
        return edges;
    }

    //Aggiunge l'edge passato come parametro al set
    // puntato dal nodo da cui tale arco esce
    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        if (edge == null)
            throw new NullPointerException("L'arco che si vuole aggiungere è nullo.");
        if (!this.containsNode(edge.getNode1()) || !this.containsNode(edge.getNode2()))
            throw new IllegalArgumentException(
                    "Almeno uno dei nodi collegati all'arco che si vuole aggiungere non è esistente in questo grafo.");
        if (!edge.isDirected())
            throw new IllegalArgumentException(
                    "L'arco che si sta cercando di inserire in questo grafo orientato non è orientato.");
        return this.adjacentLists.get(edge.getNode1()).add(edge);
    }

    @Override
    public boolean removeEdge(GraphEdge<L> edge) {
        throw new UnsupportedOperationException(
                "Rimozione degli archi non supportata");
    }

    //Verifica se l'edge passato come parametro è presente
    // nel set puntato dal nodo da cui tale arco esce
    @Override
    public boolean containsEdge(GraphEdge<L> edge) {
        if (edge == null)
            throw new NullPointerException("L'arco che si sta cercando all'interno del grafo è nullo.");
        if (!this.containsNode(edge.getNode1()) || !this.containsNode(edge.getNode2()))
            throw new IllegalArgumentException(
                    "Almeno uno dei nodi collegati all'arco che si vuole cercare non è esistente in questo grafo.");
        return this.adjacentLists.get(edge.getNode1()).contains(edge);
    }

    //Restituisce il set di edge puntati dal
    // nodo passato come parametro
    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException("Il nodo di cui si stanno cercando gli archi è nullo.");
        if (!this.containsNode(node))
            throw new IllegalArgumentException(
                    "Il nodo di cui si stanno cercando gli archi non esiste all'interno del grafo.");
        return this.adjacentLists.get(node);
    }

    //Restituisce un set contenente gli edge che
    // hanno il secondo nodo equivalente al nodo
    // passato come parametro
    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException("Il nodo di cui si stanno cercando gli archi entranti è nullo.");
        if (!this.containsNode(node))
            throw new IllegalArgumentException(
                    "Il nodo di cui si stanno cercando gli archi entranti non esiste all'interno del grafo.");
        Set<GraphEdge<L>> inEdges = new HashSet<>();
        for (GraphEdge<L> edge : this.getEdges()) {
            if (edge.getNode2().equals(node))
                inEdges.add(edge);
        }
        return inEdges;
    }
}
