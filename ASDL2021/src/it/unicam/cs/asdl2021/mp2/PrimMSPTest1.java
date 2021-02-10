package it.unicam.cs.asdl2021.mp2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Classe di test interattiva per verificare il funzionamento dell'algoritmo di
 * PRIM.
 * 
 * @author Luca Tesei
 *
 */
public class PrimMSPTest1 {

    @Test
     void computeMSPTest() {
        Graph<String> gr = new AdjacencyMatrixUndirectedGraph<>();
        GraphNode<String> a = new GraphNode<>("a");
        gr.addNode(a);
        GraphNode<String> b = new GraphNode<>("b");
        gr.addNode(b);
        GraphNode<String> c = new GraphNode<>("c");
        gr.addNode(c);
        GraphNode<String> d = new GraphNode<>("d");
        gr.addNode(d);
        GraphNode<String> e = new GraphNode<>("e");
        gr.addNode(e);
        GraphNode<String> f = new GraphNode<>("f");
        gr.addNode(f);
        GraphNode<String> g = new GraphNode<>("g");
        gr.addNode(g);
        GraphNode<String> h = new GraphNode<>("h");
        gr.addNode(h);
        GraphNode<String> i = new GraphNode<>("i");
        gr.addNode(i);
        gr.addEdge(new GraphEdge<>(a, b, false, 4));
        gr.addEdge(new GraphEdge<>(a, h, false, 8));
        gr.addEdge(new GraphEdge<>(b, h, false, 11));
        gr.addEdge(new GraphEdge<>(b, c, false, 8));
        gr.addEdge(new GraphEdge<>(c, i, false, 2));
        gr.addEdge(new GraphEdge<>(c, d, false, 7));
        gr.addEdge(new GraphEdge<>(c, f, false, 4));
        gr.addEdge(new GraphEdge<>(d, f, false, 14));
        gr.addEdge(new GraphEdge<>(d, e, false, 9));
        gr.addEdge(new GraphEdge<>(e, f, false, 10));
        gr.addEdge(new GraphEdge<>(f, g, false, 2));
        gr.addEdge(new GraphEdge<>(g, i, false, 6));
        gr.addEdge(new GraphEdge<>(g, h, false, 1));
        gr.addEdge(new GraphEdge<>(h, i, false, 7));
        PrimMSP<String> alg = new PrimMSP<>();
        alg.computeMSP(gr, a);
        for (GraphNode<String> node : gr.getNodes()) {
            System.out.println(node.toString());
        }
        assertNull(a.getPrevious());
        assertEquals(b.getPrevious(), a);
        assertEquals(c.getPrevious(), f);
        assertEquals(d.getPrevious(), c);
        assertEquals(e.getPrevious(), d);
        assertEquals(f.getPrevious(), g);
        assertEquals(g.getPrevious(), h);
        assertEquals(h.getPrevious(), a);
        assertEquals(i.getPrevious(), c);
    }

   @Test
   void computeMSP2(){
      Graph<String> gr = new AdjacencyMatrixUndirectedGraph<>();
      GraphNode<String> a = new GraphNode<>("a");
      gr.addNode(a);
      GraphNode<String> b = new GraphNode<>("b");
      gr.addNode(b);
      GraphNode<String> c = new GraphNode<>("c");
      gr.addNode(c);
      GraphNode<String> d = new GraphNode<>("d");
      gr.addNode(d);
      GraphNode<String> e = new GraphNode<>("e");
      gr.addNode(e);
      GraphNode<String> f = new GraphNode<>("f");
      gr.addNode(f);
      GraphNode<String> g = new GraphNode<>("g");
      gr.addNode(g);

      gr.addEdge(new GraphEdge<>(a, b, false, 10));
      //gr.addEdge(new GraphEdge<>(a, c, true,5)); //da provare per l'illegal
      gr.addEdge(new GraphEdge<>(a, g, false, 1));
      gr.addEdge(new GraphEdge<>(a, e, false, 2));
      gr.addEdge(new GraphEdge<>(a, f, false, 3));
      gr.addEdge(new GraphEdge<>(b, f, false, 2));
      gr.addEdge(new GraphEdge<>(b, c, false, 6));
      gr.addEdge(new GraphEdge<>(b, d, false, 11));
      gr.addEdge(new GraphEdge<>(c, g, false, 4));
      gr.addEdge(new GraphEdge<>(c, d, false, 7));
      gr.addEdge(new GraphEdge<>(d, e, false, 2));
      gr.addEdge(new GraphEdge<>(e, g, false, 5));
      gr.addEdge(new GraphEdge<>(e, f, false, 8));

      PrimMSP<String> alg = new PrimMSP<>();
      alg.computeMSP(gr, a);

      assertNull(a.getPrevious());
      assertEquals(b.getPrevious(), f);
      assertEquals(c.getPrevious(), g);
      assertEquals(d.getPrevious(), e);
      assertEquals(e.getPrevious(), a);
      assertEquals(f.getPrevious(), a);
      assertEquals(g.getPrevious(), a);
   }

   @Test
   void illegalCompute1(){
      Graph<String> gr = new AdjacencyMatrixUndirectedGraph<>();
      GraphNode<String> a = new GraphNode<>("a");
      gr.addNode(a);
      GraphNode<String> b = new GraphNode<>("b");
      gr.addNode(b);
      GraphNode<String> c = new GraphNode<>("c");
      gr.addNode(c);
      GraphNode<String> d = new GraphNode<>("d");
      gr.addNode(d);
      GraphNode<String> e = new GraphNode<>("e");
      gr.addNode(e);
      GraphNode<String> f = new GraphNode<>("f");
      gr.addNode(f);
      GraphNode<String> g = new GraphNode<>("g");
      gr.addNode(g);
      GraphNode<String> h = new GraphNode<>("h"); //da provare per l'illegal
      gr.addEdge(new GraphEdge<>(a, b, false, 10));
      gr.addEdge(new GraphEdge<>(a, g, false, 1));
      gr.addEdge(new GraphEdge<>(a, e, false, 2));
      gr.addEdge(new GraphEdge<>(a, f, false, 3));
      gr.addEdge(new GraphEdge<>(b, f, false, 2));
      gr.addEdge(new GraphEdge<>(b, c, false, 6));
      gr.addEdge(new GraphEdge<>(b, d, false, 11));
      gr.addEdge(new GraphEdge<>(c, g, false, 4));
      gr.addEdge(new GraphEdge<>(c, d, false, 7));
      gr.addEdge(new GraphEdge<>(d, e, false, 2));
      gr.addEdge(new GraphEdge<>(e, g, false, 5));
      gr.addEdge(new GraphEdge<>(e, f, false, 8));

      PrimMSP<String> alg = new PrimMSP<>();
      assertThrows(IllegalArgumentException.class, () ->alg.computeMSP(gr,h));
   }

   @Test
   void illegalCompute2(){
      Graph<String> gr = new AdjacencyMatrixUndirectedGraph<>();
      GraphNode<String> a = new GraphNode<>("a");
      gr.addNode(a);
      GraphNode<String> b = new GraphNode<>("b");
      gr.addNode(b);
      GraphNode<String> c = new GraphNode<>("c");
      gr.addNode(c);
      GraphNode<String> d = new GraphNode<>("d");
      gr.addNode(d);
      GraphNode<String> e = new GraphNode<>("e");
      gr.addNode(e);
      GraphNode<String> f = new GraphNode<>("f");
      gr.addNode(f);
      GraphNode<String> g = new GraphNode<>("g");
      gr.addNode(g);

      gr.addEdge(new GraphEdge<>(a, b, false, 10));
      gr.addEdge(new GraphEdge<>(a, g, false, 1));
      gr.addEdge(new GraphEdge<>(a, e, false, 2));
      gr.addEdge(new GraphEdge<>(a, f, false, 3));
      gr.addEdge(new GraphEdge<>(b, f, false, 2));
      gr.addEdge(new GraphEdge<>(b, c, false, 6));
      gr.addEdge(new GraphEdge<>(b, d, false, 11));
      gr.addEdge(new GraphEdge<>(c, g, false, 4));
      gr.addEdge(new GraphEdge<>(c, d, false, 7));
      gr.addEdge(new GraphEdge<>(d, e, false, 2));
      gr.addEdge(new GraphEdge<>(e, g, false, 5));
      gr.addEdge(new GraphEdge<>(e, f, false, -3));

      PrimMSP<String> alg = new PrimMSP<>();
      assertThrows(IllegalArgumentException.class, () ->alg.computeMSP(gr,a));
   }

   @Test
   void illegalCompute3(){
      Graph<String> gr = new AdjacencyMatrixUndirectedGraph<>();
      GraphNode<String> a = new GraphNode<>("a");
      gr.addNode(a);
      GraphNode<String> b = new GraphNode<>("b");
      gr.addNode(b);
      GraphNode<String> c = new GraphNode<>("c");
      gr.addNode(c);
      GraphNode<String> d = new GraphNode<>("d");
      gr.addNode(d);
      GraphNode<String> e = new GraphNode<>("e");
      gr.addNode(e);
      GraphNode<String> f = new GraphNode<>("f");
      gr.addNode(f);
      GraphNode<String> g = new GraphNode<>("g");
      gr.addNode(g);

      gr.addEdge(new GraphEdge<>(a, b, false, 10));
      gr.addEdge(new GraphEdge<>(a, g, false, 1));
      gr.addEdge(new GraphEdge<>(a, e, false, 2));
      gr.addEdge(new GraphEdge<>(a, f, false, 3));
      gr.addEdge(new GraphEdge<>(b, f, false, 2));
      gr.addEdge(new GraphEdge<>(b, c, false, 6));
      gr.addEdge(new GraphEdge<>(b, d, false, 11));
      gr.addEdge(new GraphEdge<>(c, g, false, 4));
      gr.addEdge(new GraphEdge<>(c, d, false, 7));
      gr.addEdge(new GraphEdge<>(d, e, false, 2));
      gr.addEdge(new GraphEdge<>(e, g, false, 5));
      gr.addEdge(new GraphEdge<>(e, f, false));

      PrimMSP<String> alg = new PrimMSP<>();
      assertThrows(IllegalArgumentException.class, () ->alg.computeMSP(gr,a));
   }

   @Test
   void nullCompute(){
      Graph<String> g=null;
      GraphNode<String> a = new GraphNode<>("a");
      PrimMSP<String> alg = new PrimMSP<>();
      assertThrows(NullPointerException.class, () ->alg.computeMSP(g,a));
   }
}
