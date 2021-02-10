package it.unicam.cs.asdl2021.mp2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixUndirectedGraphTest1 {

    AdjacencyMatrixUndirectedGraph<String> adjMatrix = new AdjacencyMatrixUndirectedGraph<>();

    GraphNode<String> n1 = new GraphNode<>("a");
    GraphNode<String> n2 = new GraphNode<>("b");
    GraphNode<String> n3 = new GraphNode<>("c");
    GraphNode<String> n4 = new GraphNode<>("d");
    GraphNode<String> n5 = new GraphNode<>("e");
    GraphNode<String> n6 = new GraphNode<>("f");
    GraphNode<String> n7 = new GraphNode<>("g");
    
    GraphEdge<String> a1 = new GraphEdge<>(n1, n6, false);
    GraphEdge<String> a2 = new GraphEdge<>(n6, n3, false);
    GraphEdge<String> a3 = new GraphEdge<>(n3, n5, false);
    GraphEdge<String> a4 = new GraphEdge<>(n5, n4, false);
    GraphEdge<String> a5 = new GraphEdge<>(n3, n4, false);
    GraphEdge<String> a6 = new GraphEdge<>(n4, n2, false);
    GraphEdge<String> a7 = new GraphEdge<>(n3, n2, false);
    GraphEdge<String> a8 = new GraphEdge<>(n4, n4, false);

    @Test
    void nodeCount() {
        assertEquals(0, adjMatrix.nodeCount());

        assertTrue(adjMatrix.addNode(n1));
        assertEquals(1, adjMatrix.nodeCount());

        assertTrue(adjMatrix.addNode(n2));
        assertEquals(2, adjMatrix.nodeCount());

        assertTrue(adjMatrix.addNode(n3));
        assertEquals(3, adjMatrix.nodeCount());
    }

    @Test
    void edgeCount() {
        this.adjMatrix.addNode(n1);
        this.adjMatrix.addNode(n3);
        this.adjMatrix.addNode(n6);

        assertEquals(0, adjMatrix.edgeCount());

        assertTrue(adjMatrix.addEdge(a1));
        assertEquals(1, adjMatrix.edgeCount());

        assertTrue(adjMatrix.addEdge(a2));
        assertEquals(2, adjMatrix.edgeCount());
    }

    @Test
    void addNode() {
        assertEquals(0, adjMatrix.nodeCount());
        assertEquals(0, adjMatrix.matrix.size());
        for (ArrayList<GraphEdge<String>> matrix : adjMatrix.matrix)
            assertEquals(0, matrix.size());

        assertTrue(adjMatrix.addNode(n1));
        assertEquals(1, adjMatrix.nodeCount());
        assertEquals(1, adjMatrix.matrix.size());
        for (ArrayList<GraphEdge<String>> matrix : adjMatrix.matrix)
            assertEquals(1, matrix.size());

        assertTrue(adjMatrix.addNode(n2));
        assertEquals(2, adjMatrix.nodeCount());
        assertEquals(2, adjMatrix.matrix.size());
        for (ArrayList<GraphEdge<String>> matrix : adjMatrix.matrix)
            assertEquals(2, matrix.size());

        assertTrue(adjMatrix.addNode(n3));
        assertEquals(3, adjMatrix.nodeCount());
        assertEquals(3, adjMatrix.matrix.size());
        for (ArrayList<GraphEdge<String>> matrix : adjMatrix.matrix)
            assertEquals(3, matrix.size());

        assertFalse(adjMatrix.addNode(n3));
        assertEquals(3, adjMatrix.nodeCount());
        assertEquals(3, adjMatrix.matrix.size());
        for (ArrayList<GraphEdge<String>> matrix : adjMatrix.matrix)
            assertEquals(3, matrix.size());

        assertThrows(NullPointerException.class, () -> adjMatrix.addNode(null));
    }

    @Test
    void containsNode() {
        adjMatrix.addNode(n1);
        adjMatrix.addNode(n2);
        adjMatrix.addNode(n3);

        assertTrue(adjMatrix.containsNode(n1));
        assertTrue(adjMatrix.containsNode(n2));
        assertTrue(adjMatrix.containsNode(n3));
        assertFalse(adjMatrix.containsNode(n4));

        assertThrows(NullPointerException.class, () -> adjMatrix.addNode(null));
    }

    @Test
    void getNodeOf() {
        this.adjMatrix.addNode(n1);

        assertEquals(this.adjMatrix.getNodeOf("a"), n1);

        assertNull(this.adjMatrix.getNodeOf("b"));

        assertThrows(NullPointerException.class, () -> this.adjMatrix.getNodeOf(null));
    }

    @Test
    void getNodeIndexOf() {
        this.adjMatrix.addNode(n1);
        this.adjMatrix.addNode(n2);
        this.adjMatrix.addNode(n3);

        assertEquals(this.adjMatrix.getNodeIndexOf("a"), 0);
        assertEquals(this.adjMatrix.getNodeIndexOf("b"), 1);
        assertEquals(this.adjMatrix.getNodeIndexOf("c"), 2);

        assertThrows(NullPointerException.class, () -> this.adjMatrix.getNodeIndexOf(null));

        assertThrows(IllegalArgumentException.class, () -> this.adjMatrix.getNodeIndexOf("d"));
    }

    @Test
    void getNodeAtIndex() {
        this.adjMatrix.addNode(n1);
        this.adjMatrix.addNode(n2);
        this.adjMatrix.addNode(n3);

        assertEquals(this.adjMatrix.getNodeAtIndex(0), n1);
        assertEquals(this.adjMatrix.getNodeAtIndex(1), n2);
        assertEquals(this.adjMatrix.getNodeAtIndex(2), n3);

        assertThrows(IndexOutOfBoundsException.class, () -> this.adjMatrix.getNodeAtIndex(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> this.adjMatrix.getNodeAtIndex(3));
    }

    @Test
    void getAdjacentNodesOf() {
        this.adjMatrix.addNode(n1);
        this.adjMatrix.addNode(n2);
        this.adjMatrix.addNode(n3);
        this.adjMatrix.addNode(n4);
        this.adjMatrix.addNode(n5);
        this.adjMatrix.addNode(n6);

        this.adjMatrix.addEdge(a1);
        this.adjMatrix.addEdge(a2);
        this.adjMatrix.addEdge(a3);
        this.adjMatrix.addEdge(a4);
        this.adjMatrix.addEdge(a5);
        this.adjMatrix.addEdge(a6);
        this.adjMatrix.addEdge(a7);
        this.adjMatrix.addEdge(a8);

        Set<GraphNode<String>> adjNodes = this.adjMatrix.getAdjacentNodesOf(n4);
        assertFalse(adjNodes.contains(n1));
        assertTrue(adjNodes.contains(n2));
        assertTrue(adjNodes.contains(n3));
        assertTrue(adjNodes.contains(n4));
        assertTrue(adjNodes.contains(n5));
        assertFalse(adjNodes.contains(n6));

        assertThrows(IllegalArgumentException.class, () -> this.adjMatrix.getAdjacentNodesOf(n7));
        assertThrows(NullPointerException.class, () -> this.adjMatrix.getAdjacentNodesOf(null));
    }

    @Test
    void getEdges(){
        this.adjMatrix.addNode(n1);
        this.adjMatrix.addNode(n3);
        this.adjMatrix.addNode(n4);
        this.adjMatrix.addNode(n5);
        this.adjMatrix.addNode(n6);

        Set<GraphEdge<String>> edges = new HashSet<>();
        assertEquals(this.adjMatrix.getEdges(), edges);

        edges.add(a1);
        this.adjMatrix.addEdge(a1);
        assertEquals(this.adjMatrix.getEdges(), edges);

        edges.add(a2);
        this.adjMatrix.addEdge(a2);
        assertEquals(this.adjMatrix.getEdges(), edges);

        edges.add(a3);
        assertNotEquals(this.adjMatrix.getEdges(), edges);

        this.adjMatrix.addEdge(a4);
        assertNotEquals(this.adjMatrix.getEdges(), edges);
    }

    @Test
    void addEdge() {
        this.adjMatrix.addNode(n2); //index: 0
        this.adjMatrix.addNode(n3); //index: 1
        this.adjMatrix.addNode(n4); //index: 2
        this.adjMatrix.addNode(n5); //index: 3

        this.adjMatrix.addEdge(a3); //matrix[1][3], matrix[3][1]
        this.adjMatrix.addEdge(a4); //matrix[2][3], matrix[3][2]
        this.adjMatrix.addEdge(a5); //matrix[1][2], matrix[2][1]
        this.adjMatrix.addEdge(a6); //matrix[0][2], matrix[2][0]
        this.adjMatrix.addEdge(a7); //matrix[0][1], matrix[1][0]

        assertEquals(this.adjMatrix.matrix.get(1).get(3), a3);
        assertEquals(this.adjMatrix.matrix.get(3).get(1), a3);

        assertEquals(this.adjMatrix.matrix.get(2).get(3), a4);
        assertEquals(this.adjMatrix.matrix.get(3).get(2), a4);

        assertEquals(this.adjMatrix.matrix.get(1).get(2), a5);
        assertEquals(this.adjMatrix.matrix.get(2).get(1), a5);

        assertEquals(this.adjMatrix.matrix.get(0).get(2), a6);
        assertEquals(this.adjMatrix.matrix.get(2).get(0), a6);

        assertEquals(this.adjMatrix.matrix.get(1).get(0), a7);
        assertEquals(this.adjMatrix.matrix.get(0).get(1), a7);
    }

    @Test
    void containsEdge() {
        this.adjMatrix.addNode(n1);
        this.adjMatrix.addNode(n3);
        this.adjMatrix.addNode(n4);
        this.adjMatrix.addNode(n5);
        this.adjMatrix.addNode(n6);

        this.adjMatrix.addEdge(a1);
        this.adjMatrix.addEdge(a3);
        this.adjMatrix.addEdge(a5);

        assertTrue(this.adjMatrix.containsEdge(a1));
        assertFalse(this.adjMatrix.containsEdge(a2));
        assertTrue(this.adjMatrix.containsEdge(a3));
        assertFalse(this.adjMatrix.containsEdge(a4));
        assertTrue(this.adjMatrix.containsEdge(a5));

        assertThrows(NullPointerException.class, () -> this.adjMatrix.containsEdge(null));
        assertThrows(IllegalArgumentException.class, () -> this.adjMatrix.containsEdge(a6));
    }

    @Test
    void getEdgesOf() {
        this.adjMatrix.addNode(n2);
        this.adjMatrix.addNode(n3);
        this.adjMatrix.addNode(n4);
        this.adjMatrix.addNode(n5);

        this.adjMatrix.addEdge(a4);
        this.adjMatrix.addEdge(a5);
        this.adjMatrix.addEdge(a6);
        this.adjMatrix.addEdge(a8);

        Set<GraphEdge<String>> n4Edges = new HashSet<>();
        n4Edges.add(a4);
        n4Edges.add(a5);
        n4Edges.add(a6);
        n4Edges.add(a8);

        assertEquals(this.adjMatrix.getEdgesOf(n4), n4Edges);
        assertEquals(this.adjMatrix.getEdgesOf(n4).size(), 4);

        assertThrows(NullPointerException.class, () -> this.adjMatrix.getEdgesOf(null));
        assertThrows(IllegalArgumentException.class, () -> this.adjMatrix.getEdgesOf(n6));
    }
}