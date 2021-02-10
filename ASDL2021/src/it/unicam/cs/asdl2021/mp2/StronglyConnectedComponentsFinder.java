package it.unicam.cs.asdl2021.mp2;

import java.util.*;

/**
 * Un oggetto di questa classe singoletto è un attore che trova le componenti
 * fortemente connesse in un grafo orientato che viene passato come parametro.
 * 
 * @author Template: Luca Tesei, Implementation: Luca Mozzoni - luca.mozzoni@studenti.unicam.it
 *
 */
public class StronglyConnectedComponentsFinder<L> {

    private Stack<GraphNode<L>> stack;
    private Graph<L> graph;

    /*
     * NOTA: per tutti i metodi che ritornano un set utilizzare la classe
     * HashSet<E> per creare l'insieme risultato. Questo garantisce un buon
     * funzionamento dei test JUnit che controllano l'uguaglianza tra insiemi
     */

    /**
     * Dato un grafo orientato determina l'insieme di tutte le componenti
     * fortemente connesse dello stesso.
     * 
     * @param g
     *              un grafo orientato
     * @return l'insieme di tutte le componenti fortemente connesse di g dove
     *         ogni componente fortemente connessa è rappresentata dall'insieme
     *         dei nodi che la compongono.
     * @throws IllegalArgumentException
     *                                      se il grafo passato non è orientato
     * @throws NullPointerException
     *                                      se il grafo passato è nullo
     */
    public Set<Set<GraphNode<L>>> findStronglyConnectedComponents(Graph<L> g) {
        if (g == null)
            throw new NullPointerException("Il grafo passato è nullo.");
        if (!g.isDirected())
            throw new IllegalArgumentException("Il grafo passato non è orientato.");
        //Inizializzo uno stack
        stack = new Stack<>();
        graph = g;
        //Inizializzo il set che conterrà le componenti
        // fortemente connesse del grafo passato come parametro
        Set<Set<GraphNode<L>>> dfsForest = new HashSet<>();
        //Imposto il colore di ogni nodo a bianco (non visitato)
        for (GraphNode<L> node : g.getNodes()) {
            node.setColor(GraphNode.COLOR_WHITE);
        }
        //Per ogni nodo non visitato eseguo l'operazione di setup
        for (GraphNode<L> node : g.getNodes()) {
            if(node.getColor() == GraphNode.COLOR_WHITE) {
                setUpNodes(node);
            }
        }
        //Ricavo il grafo trasposto
        Graph<L> transposedGraph = getTransposedGraph();
        //Inizializzo nuovamente il colore dei nodi
        for (GraphNode<L> node : transposedGraph.getNodes()) {
            node.setColor(GraphNode.COLOR_WHITE);
        }
        //Riempo il set delle componenti fortemente connesse
        populateDFSForest(dfsForest, transposedGraph);
        return dfsForest;
    }

    private void populateDFSForest(Set<Set<GraphNode<L>>> dfsForest, Graph<L> gr) {
        //Fino a che lo stack contiene nodi
        while(!stack.isEmpty()) {
            //Estraggo il nodo in cima
            GraphNode<L> node = stack.pop();
            //Se il suo colore è bianco
            if(node.getColor() == GraphNode.COLOR_WHITE) {
                //Inizializzo un nuovo set che conterrà
                // la componente fortemente connessa
                Set<GraphNode<L>> scc = new HashSet<>();
                dfs(gr, scc, node);
                //Aggiungo la componente fortemente connessa
                // al set complessivo delle componenti
                dfsForest.add(scc);
            }
        }
    }

    //Creo un grafo contenente gli stessi nodi del grafo
    // di partenza ma con il verso degli edge invertito
    private Graph<L> getTransposedGraph() {
        Graph<L> graph = new MapAdjacentListDirectedGraph<>();
        for (GraphNode<L> node : this.graph.getNodes()) {
            graph.addNode(node);
        }
        for(GraphEdge<L> edge : this.graph.getEdges()) {
            graph.addEdge(new GraphEdge<>(edge.getNode2(), edge.getNode1(), true));
        }
        return graph;
    }

    private void dfs(Graph<L> graph, Set<GraphNode<L>> scc, GraphNode<L> node) {
        //Imposto il colore del nodo a nero (visitato)
        node.setColor(GraphNode.COLOR_BLACK);
        //Aggiungo il nodo alla componente fortemente connessa
        scc.add(node);
        //Per ogni nodo adiacente non ancora visitato
        // eseguo ricorsivamente l'operazione
        for(GraphNode<L> g : graph.getAdjacentNodesOf(node)) {
            if(g.getColor() == GraphNode.COLOR_WHITE)
                dfs(graph, scc, g);
        }
    }

    private void setUpNodes(GraphNode<L> node) {
        //Imposto il colore del nodo a nero (visitato)
        node.setColor(GraphNode.COLOR_BLACK);
        //Per ogni nodo adiacente non ancora visitato
        // eseguo ricorsivamente l'operazione
        for(GraphNode<L> g : graph.getAdjacentNodesOf(node)) {
            if(g.getColor() == GraphNode.COLOR_WHITE)
                setUpNodes(g);
        }
        //Aggiungo il nodo allo stack
        stack.push(node);
    }
}