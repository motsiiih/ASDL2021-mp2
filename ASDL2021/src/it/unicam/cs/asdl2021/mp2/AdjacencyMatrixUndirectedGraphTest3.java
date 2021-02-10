package it.unicam.cs.asdl2021.mp2;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/*
    @author - Riccardo Pierini 105131
    @project - asdl2021-codice-mp2-traccia
 */
class AdjacencyMatrixUndirectedGraphTest3 {

    @org.junit.jupiter.api.Test
    void nodeCount() {
        AdjacencyMatrixUndirectedGraph<Integer> graph=new AdjacencyMatrixUndirectedGraph();
        GraphNode<Integer> node= new GraphNode<>(39);
        GraphNode<Integer> node1= new GraphNode<>(31);
        GraphNode<Integer> node2= new GraphNode<>(5);
        graph.addNode(node);
        assertFalse(graph.isEmpty());
        graph.addNode(node1);
        graph.addNode(node2);
        assertFalse(graph.nodeCount()==2);
        assertTrue(graph.nodeCount()==3);
    }

    @org.junit.jupiter.api.Test
    void addNode() {
        AdjacencyMatrixUndirectedGraph<Integer> graph=new AdjacencyMatrixUndirectedGraph();
        GraphNode<Integer> node= new GraphNode<>(39);
        assertThrows(NullPointerException.class, () -> graph.addNode(null));
        GraphNode<Integer> node1= new GraphNode<>(31);
        GraphNode<Integer> node2= new GraphNode<>(5);
        assertTrue(graph.addNode(node));
        assertTrue(graph.addNode(node1));
        assertFalse(graph.addNode(node1));
        assertTrue(graph.addNode(node2));
    }

    @org.junit.jupiter.api.Test
    void containsNode() {
        AdjacencyMatrixUndirectedGraph<Integer> graph=new AdjacencyMatrixUndirectedGraph();
        GraphNode<Integer> node= new GraphNode<>(39);
        GraphNode<Integer> node1= new GraphNode<>(31);
        GraphNode<Integer> node2= new GraphNode<>(5);
        graph.addNode(node);
        graph.addNode(node2);
        assertThrows(NullPointerException.class, () -> graph.containsNode(null));
        assertTrue(graph.containsNode(node));
        assertFalse(graph.containsNode(node1));
        assertTrue(graph.containsNode(node2));
    }

    @org.junit.jupiter.api.Test
    void getNodeOf() {
        AdjacencyMatrixUndirectedGraph<Integer> graph=new AdjacencyMatrixUndirectedGraph();
        GraphNode<Integer> node= new GraphNode<>(39);
        GraphNode<Integer> node1= new GraphNode<>(31);
        GraphNode<Integer> node2= new GraphNode<>(5);
        graph.addNode(node);
        graph.addNode(node1);
        graph.addNode(node2);
        assertThrows(NullPointerException.class, () -> graph.getNodeOf(null));
        assertTrue(graph.getNodeOf(31)==node1);
        assertFalse(graph.getNodeOf(39)==node2);
    }

    @org.junit.jupiter.api.Test
    void getNodeIndexOf() {
        AdjacencyMatrixUndirectedGraph<Integer> graph=new AdjacencyMatrixUndirectedGraph();
        GraphNode<Integer> node= new GraphNode<>(39);
        GraphNode<Integer> node1= new GraphNode<>(31);
        GraphNode<Integer> node2= new GraphNode<>(5);
        graph.addNode(node);
        graph.addNode(node1);
        graph.addNode(node2);
        assertThrows(NullPointerException.class, () -> graph.getNodeIndexOf(null));
        assertThrows(IllegalArgumentException.class, () -> graph.getNodeIndexOf(34));
        assertTrue(graph.getNodeIndexOf(31)==1);
        assertFalse(graph.getNodeIndexOf(5)==1);
    }

    @org.junit.jupiter.api.Test
    void getNodeAtIndex() {
        AdjacencyMatrixUndirectedGraph<Integer> graph=new AdjacencyMatrixUndirectedGraph();
        GraphNode<Integer> node= new GraphNode<>(77);
        GraphNode<Integer> node1= new GraphNode<>(19);
        GraphNode<Integer> node2= new GraphNode<>(11);
        graph.addNode(node);
        graph.addNode(node1);
        graph.addNode(node2);
        assertThrows(IndexOutOfBoundsException.class, () -> graph.getNodeAtIndex(3));
        assertEquals(graph.getNodeAtIndex(2),node2);
        assertNotEquals(graph.getNodeAtIndex(2),node);
        assertEquals(graph.getNodeAtIndex(1),node1);
        assertEquals(graph.getNodeAtIndex(0),node);
    }

    @org.junit.jupiter.api.Test
    void getAdjacentNodesOf() {
        AdjacencyMatrixUndirectedGraph<Integer> graph=new AdjacencyMatrixUndirectedGraph();
        GraphNode<Integer> node= new GraphNode<>(77);
        GraphNode<Integer> node1= new GraphNode<>(19);
        GraphNode<Integer> node2= new GraphNode<>(11);
        GraphNode<Integer> node3= new GraphNode<>(17);
        graph.addNode(node);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        GraphEdge<Integer> edge=new GraphEdge<>(node,node1,false);
        GraphEdge<Integer> edge1=new GraphEdge<>(node1,node2,false);
        GraphEdge<Integer> edge2=new GraphEdge<>(node2,node3,false);
        GraphEdge<Integer> edge3=new GraphEdge<>(node1,node1,false);
        graph.addEdge(edge);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        Set<GraphNode<Integer>> result=graph.getAdjacentNodesOf(node1);
        assertTrue(result.size()==3);
    }

    @org.junit.jupiter.api.Test
    void getEdges() {
        AdjacencyMatrixUndirectedGraph<Integer> graph=new AdjacencyMatrixUndirectedGraph();
        GraphNode<Integer> node= new GraphNode<>(77);
        GraphNode<Integer> node1= new GraphNode<>(19);
        GraphNode<Integer> node2= new GraphNode<>(11);
        GraphNode<Integer> node3= new GraphNode<>(17);
        graph.addNode(node);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        GraphEdge<Integer> edge=new GraphEdge<>(node,node1,false);
        GraphEdge<Integer> edge1=new GraphEdge<>(node1,node2,false);
        GraphEdge<Integer> edge2=new GraphEdge<>(node2,node3,false);
        graph.addEdge(edge);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        Set<GraphEdge<Integer>> result=graph.getEdges();
        assertTrue(result.size()==3);
    }

    @org.junit.jupiter.api.Test
    void addEdge() {
        AdjacencyMatrixUndirectedGraph<Integer> graph=new AdjacencyMatrixUndirectedGraph();
        GraphNode<Integer> node= new GraphNode<>(77);
        GraphNode<Integer> node1= new GraphNode<>(19);
        GraphNode<Integer> node2= new GraphNode<>(11);
        GraphNode<Integer> node3= new GraphNode<>(17);
        GraphNode<Integer> node4= new GraphNode<>(22);
        graph.addNode(node);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        GraphEdge<Integer> edge=new GraphEdge<>(node,node1,false);
        GraphEdge<Integer> edge1=new GraphEdge<>(node1,node2,false);
        GraphEdge<Integer> edge2=new GraphEdge<>(node1,node2,true);
        GraphEdge<Integer> edge3=null;
        GraphEdge<Integer> edge4=new GraphEdge<>(node2,node4,false);
        assertTrue(graph.addEdge(edge));
        assertTrue(graph.addEdge(edge1));
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(edge2));
        assertThrows(NullPointerException.class, () -> graph.addEdge(edge3));
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(edge4));
    }

    @org.junit.jupiter.api.Test
    public final void testAddEdgeNull() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<>();
        GraphNode<String> ns = new GraphNode<>("s");
        g.addNode(ns);
        assertThrows(NullPointerException.class, () ->g.addEdge(null));
    }

    @org.junit.jupiter.api.Test
    public final void testAddEdgeIllegal1() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        // g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<>(ns,
                nu, true);
        assertThrows(IllegalArgumentException.class, () ->g.addEdge(esu));
    }

    @org.junit.jupiter.api.Test
    public final void testAddEdgeIllegal2() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns,
                nu, true);
        assertThrows(IllegalArgumentException.class, () ->g.addEdge(esu));
    }


    @org.junit.jupiter.api.Test
    void containsEdge() {
        AdjacencyMatrixUndirectedGraph<Integer> graph=new AdjacencyMatrixUndirectedGraph();
        GraphNode<Integer> node= new GraphNode<>(77);
        GraphNode<Integer> node1= new GraphNode<>(19);
        GraphNode<Integer> node2= new GraphNode<>(11);
        GraphNode<Integer> node3= new GraphNode<>(17);
        graph.addNode(node);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        GraphEdge<Integer> edge=new GraphEdge<>(node,node1,false);
        GraphEdge<Integer> edge1=new GraphEdge<>(node1,node2,false);
        GraphEdge<Integer> edge2=new GraphEdge<>(node2,node3,false);
        assertFalse(graph.containsEdge(edge));
        graph.addEdge(edge);
        graph.addEdge(edge1);
        assertTrue(graph.containsEdge(edge));
        assertTrue(graph.containsEdge(edge1));
        assertFalse(graph.containsEdge(edge2));
    }

    @org.junit.jupiter.api.Test
    void getEdgesOf() {
        AdjacencyMatrixUndirectedGraph<Integer> graph=new AdjacencyMatrixUndirectedGraph();
        GraphNode<Integer> node= new GraphNode<>(77);
        GraphNode<Integer> node1= new GraphNode<>(19);
        GraphNode<Integer> node2= new GraphNode<>(11);
        GraphNode<Integer> node3= new GraphNode<>(17);
        graph.addNode(node);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        GraphEdge<Integer> edge=new GraphEdge<>(node,node1,false);
        GraphEdge<Integer> edge1=new GraphEdge<>(node1,node2,false);
        GraphEdge<Integer> edge2=new GraphEdge<>(node2,node3,false);
        graph.addEdge(edge);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        Set<GraphEdge<Integer>> result=graph.getEdgesOf(node1);
        assertTrue(result.size()==2);
    }

    @org.junit.jupiter.api.Test
    public final void testGetEdgesGraphNodeOfVNull() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns,
                nu, false);
        g.addEdge(esu);
        assertThrows(NullPointerException.class, () ->g.getEdgesOf(null));
    }

    @org.junit.jupiter.api.Test
    public final void testGetEdgesGraphNodeOfVIllegal() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphNode<String> acab = new GraphNode<String>("f");
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        assertThrows(IllegalArgumentException.class, () ->g.getEdgesOf(acab));
    }

}