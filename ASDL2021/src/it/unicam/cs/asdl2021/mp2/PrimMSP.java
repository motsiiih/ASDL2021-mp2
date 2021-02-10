package it.unicam.cs.asdl2021.mp2;

/**
 *
 * Classe singoletto che implementa l'algoritmo di Prim per trovare un Minimum
 * Spanning Tree di un grafo non orientato, pesato e con pesi non negativi.
 *
 * L'algoritmo usa una coda di min priorità tra i nodi implementata dalla classe
 * TernaryHeapMinPriorityQueue. I nodi vengono visti come PriorityQueueElement
 * poiché la classe GraphNode<L> implementa questa interfaccia. Si noti che
 * nell'esecuzione dell'algoritmo è necessario utilizzare l'operazione di
 * decreasePriority.
 *
 * @author Template: Luca Tesei, Implementation: Luca Mozzoni - luca.mozzoni@studenti.unicam.it
 *
 * @param <L>
 *                etichette dei nodi del grafo
 *
 */
public class PrimMSP<L> {

    /*
     * Coda di priorità che va usata dall'algoritmo. La variabile istanza è
     * protected solo per scopi di testing JUnit.
     */
    protected TernaryHeapMinPriorityQueue queue;

    /**
     * Crea un nuovo algoritmo e inizializza la coda di priorità con una coda
     * vuota.
     */
    public PrimMSP() {
        this.queue = new TernaryHeapMinPriorityQueue();
    }

    /**
     * Utilizza l'algoritmo goloso di Prim per trovare un albero di copertura
     * minimo in un grafo non orientato e pesato, con pesi degli archi non negativi.
     * Dopo l'esecuzione del metodo nei nodi del grafo il campo previous deve
     * contenere un puntatore a un nodo in accordo all'albero di copertura
     * minimo calcolato, la cui radice è il nodo sorgente passato.
     *
     * @param g
     *              un grafo non orientato, pesato, con pesi non negativi
     * @param s
     *              il nodo del grafo g sorgente, cioè da cui parte il calcolo
     *              dell'albero di copertura minimo. Tale nodo sarà la radice
     *              dell'albero di copertura trovato
     *
     * @throws NullPointerException se il grafo g o il nodo sorgente s sono nulli
     * @throws IllegalArgumentException se il nodo sorgente s non esiste in g
     * @throws IllegalArgumentException se il grafo g è orientato, non pesato o
     *        con pesi negativi
     */
    @SuppressWarnings("unchecked")
    public void computeMSP(Graph<L> g, GraphNode<L> s) {
        if (g == null || s == null)
            throw new NullPointerException("Il grafo o il nodo sorgente sono nulli.");
        if (!g.containsNode(s))
            throw new IllegalArgumentException("Il grafo non contiene il nodo sorgente.");
        if (g.isDirected())
            throw new IllegalArgumentException("Il grafo passato è orientato.");
        for (GraphEdge<L> edge : g.getEdges()) {
            if (!edge.hasWeight() || edge.getWeight() < 0)
                throw new IllegalArgumentException(
                        "Il grafo passato è non pesato o contiene almeno un peso negativo.");
        }
        //Imposto ogni nodo col colore bianco, la priorità
        // ad infinito ed il nodo predecessore a null
        for (GraphNode<L> node : g.getNodes()){
            node.setColor(GraphNode.COLOR_WHITE);
            node.setPriority(Double.MAX_VALUE);
            node.setPrevious(null);
        }
        //La priorità del nodo sorgente è l'unica a partire da zero
        s.setPriority(0);
        //Inserisco i nodi nella queue
        for (GraphNode<L> node : g.getNodes())
            queue.insert(node);
        //Finchè la queue contiene nodi
        while (queue.size() > 0) {
            //Estraggo il nodo con priorità minore dalla queue e imposto
            // il suo colore a nero così so che non è più nella queue
            GraphNode<L> u = (GraphNode<L>) queue.extractMinimum();
            u.setColor(GraphNode.COLOR_BLACK);
            //Itero gli edge collegati al nodo 'u' estratto dalla queue
            // e cerco di ricavare l'altro nodo a cui l'edge è collegato
            for (GraphEdge<L> edge : g.getEdgesOf(u)) {
                GraphNode<L> node;
                //CASO 1: l'edge è della forma (v, u) ed il nodo v è nella queue
                if (edge.getNode1().getColor() == 0 && edge.getNode2() == u)
                    //node = v
                    node = edge.getNode1();
                    //CASO 2: l'edge è della forma (u, v) ed il nodo 'v' è nella queue
                else if (edge.getNode1() == u && edge.getNode2().getColor() == 0)
                    //node = v
                    node = edge.getNode2();
                //CASO 3: nessuno dei due casi precedenti è verificato,
                    // il ciclo si interrompe e passiamo all'edge successivo
                else continue;
                //Il nodo ricavato ha associato un valore maggiore
                // del peso dell'arco di cui fa parte
                if (node.getPriority() > edge.getWeight()) {
                    //Il predecessore del nodo è 'u'
                    node.setPrevious(u);
                    //Il valore associato al nodo viene
                    // posto uguale al peso dell'arco
                    queue.decreasePriority(node, edge.getWeight());
                }
            }
        }
    }
}