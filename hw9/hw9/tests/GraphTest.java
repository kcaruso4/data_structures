package hw9.tests;

import hw9.Graph;
import hw9.Vertex;
import hw9.Edge;
import hw9.Position;
import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public abstract class GraphTest {

    protected Graph<String, String> graph;

    protected abstract Graph<String, String> createGraph();

    @Before
    public void setupGraph() {
        this.graph = createGraph();
    }

    // TODO - Add tests

    //test inserting a vertex
        //when v is null
        //when v is normal
    @Test
    public void testInsertV() {
        //should be able to insert null for the vertex
        Vertex<String> v = graph.insert(null);
        assertEquals(v.get(), null);
        //inserting a non null vertx
        Vertex<String> v2 = graph.insert("A");
        assertEquals("A", v2.get());
    }

    //test inserting an edge
    @Test
    public void testInsertE() {
        //when from vertex is invalid
        try {
            Vertex<String> from = null;
            Vertex<String> to = graph.insert("got here");
            graph.insert(from, to, "edge");
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be thrown
        }
        //when to vertex is invalid
        try {
            Vertex<String> to = null;
            Vertex<String> from = graph.insert("start here");
            graph.insert(from, to, "edge");
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be thrown
        }
        //when the to and from vertex are the same
        try {
            Vertex<String> from = graph.insert("left here");
            Vertex<String> to = from;
            graph.insert(from, to, "edge");
            assertTrue(false);
        }
        catch (InsertionException e) {
            //should be thrown
        }
        //when the edge already exists
        try {
            Vertex<String> from = graph.insert("left here");
            Vertex<String> to = graph.insert("got here");
            Edge<String> newedge = graph.insert(from, to, "edge");
            graph.insert(from, to, "edge");
            assertTrue(false);
        }
        catch (InsertionException e) {
            //should be caught
        }
        //when the data of the edge is null
        Vertex<String> from = graph.insert("left here");
        Vertex<String> to = graph.insert("arrived here");
        Vertex<String> to2 = graph.insert("B");
        assertEquals("arrived here", to.get());
        assertEquals("left here", from.get());
        Edge<String> nulle = graph.insert(from, to, null);
        assertEquals(null, nulle.get());
        //when the data of the edge is not null
        Edge<String> normale = graph.insert(from, to2, "new street");
        assertEquals("new street", normale.get());

    }

    //test remove vertex
    @Test
    public void testRemoveV() {
        //testing removing a normal vertex
        Vertex<String> testv = graph.insert("testing");
        String removeval = graph.remove(testv);
        assertEquals("testing", removeval);
        //testing when the vertex data is null
        Vertex<String> newtempv = graph.insert(null);
        assertEquals(null, newtempv.get());
        removeval = graph.remove(newtempv);
        assertEquals(null, removeval);
        //testing when the vertex itself is null
        newtempv = null;
        try {
            graph.remove(newtempv);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be caught
        }
        //removing a vertex that has already been removed
        try {
            graph.remove(testv);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be caught
        }
        //removing a vertex that does not belong to our graph
        try {
            Graph<String, String> tempg = this.createGraph();
            Vertex<String> outside = tempg.insert("outside v");
            graph.remove(outside);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be caught
        }
        //when the vertex has an outgoing edge
        testv = graph.insert("start");
        newtempv = graph.insert("end");
        Edge<String> newedge = graph.insert(testv, newtempv, "EDGE");
        try {
            graph.remove(testv);
            assertTrue(false);
        }
        catch (RemovalException e) {
            //should be caught
        }
        //when the vertex has an ingoing edge
        try {
            graph.remove(newtempv);
            assertTrue(false);
        }
        catch (RemovalException e) {
            //should be caught
        }
    }

    //test remove edge
    @Test
    public void testRemoveE() {
        //removing when the edge is null
        try {
            Edge<String> edget = null;
            graph.remove(edget);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be caught
        }
        //when edge does not belong to graph
        Graph<String, String> tempg = createGraph();
        Vertex<String> fromt = tempg.insert("from");
        Vertex<String> tot = tempg.insert("to");
        Edge<String> edget = tempg.insert(fromt, tot, "random edge");
        try {
            graph.remove(edget);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be caught
        }
        //when the edge data is null
        fromt = graph.insert("from");
        tot = graph.insert("to");
        Edge<String> teste = graph.insert(fromt, tot, null);
        assertEquals(null, teste.get());
        String eval = graph.remove(teste);
        assertEquals(null, eval);
        //when the edge has already been removed
        try {
            graph.remove(teste);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be caught
        }
        //when the dge is normal
        teste = graph.insert(fromt, tot, "edge");
        assertEquals("edge", teste.get());
        eval = graph.remove(teste);
        assertEquals("edge", eval);

    }

    //test getting an interable object over verticies
    @Test
    public void testIterableV() {
        Vertex<String> first = graph.insert("first");
        Vertex<String> second = graph.insert("second");
        Vertex<String> third = graph.insert("third");
        Vertex<String> fourth = graph.insert("fourth");
        Vertex<String> fifth = graph.insert("fifth");
        assertEquals("first", first.get());
        assertEquals("second", second.get());
        assertEquals("third", third.get());
        assertEquals("fourth", fourth.get());
        assertEquals("fifth", fifth.get());
        int count = 0;
        Iterable<Vertex<String>> vertexlist = graph.vertices();
        for (Iterator<Vertex<String>> it = vertexlist.iterator(); it.hasNext(); it.next()) {
            count++;
        }
        assertEquals(5, count);
    }
    //test getting an interable object over edgeString
    @Test
    public void testIterableE() {
        Vertex<String> firstfrom = graph.insert("firstf");
        Vertex<String> secondfrom = graph.insert("secondf");
        Vertex<String> thirdfrom = graph.insert("thirdf");
        Vertex<String> firstto = graph.insert("firstt");
        Vertex<String> secondto = graph.insert("secondt");
        Vertex<String> thirdto = graph.insert("thirdt");
        Edge<String> first = graph.insert(firstfrom, firstto, "first");
        Edge<String> second = graph.insert(secondfrom, secondto, "second");
        Edge<String> third = graph.insert(thirdfrom, thirdto, "third");
        int count = 0;
        Iterable<Edge<String>> edgelist = graph.edges();
        for (Iterator<Edge<String>> it = edgelist.iterator(); it.hasNext(); it.next()) {
            count++;
        }
        assertEquals(3, count);
    }
    //test geting an interable object over the outgoing edges of the graphs
    @Test
    public void testIterableOutgoing() {
        //when vertex is null
        try {
            Vertex<String> from = null;
             graph.outgoing(from);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be thrown
        }
        //when to vertex doesnot belong to the proper graph
        try {
            Graph<String, String> tempg = createGraph();
            Vertex<String> to = tempg.insert("hello");
            graph.outgoing(to);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be thrown
        }
        //when the vertex is not found in the graph
        try {
            Vertex<String> from = graph.insert("first");
            String val = graph.remove(from);
            assertEquals(val, "first");
            graph.outgoing(from);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be thrown
        }
        Vertex<String> nullvertex = graph.insert(null);
        Vertex<String> normalvertex = graph.insert("first");
        Vertex<String> firstto = graph.insert("firstto");
        Vertex<String> secondto = graph.insert("secondto");
        Vertex<String> thirdto = graph.insert("thirdto");
        //when the vertex data is null
        Edge<String> firstnull = graph.insert(nullvertex, firstto, "first null");
        Edge<String> secondnull = graph.insert(nullvertex, secondto, "second null");
        Edge<String> thirdnull = graph.insert(nullvertex, thirdto, "third null");
        int count = 0;
        Iterable<Edge<String>> nulllist = graph.outgoing(nullvertex);
        for (Iterator<Edge<String>> it = nulllist.iterator(); it.hasNext(); it.next()) {
            count++;
        }
        assertEquals(3, count);
        //when the vertex data is normal
        Edge<String> firstnorm = graph.insert(normalvertex, firstto, "first norm");
        Edge<String> secondnorm = graph.insert(normalvertex, secondto, "second norm");
        Edge<String> thirdnorm = graph.insert(normalvertex, thirdto, "third norm");
        count = 0;
        Iterable<Edge<String>> normlist = graph.outgoing(normalvertex);
        for (Iterator<Edge<String>> it = normlist.iterator(); it.hasNext(); it.next()) {
            count++;
        }
        assertEquals(3, count);
    }

    //test geting an interable object over the incoming edges of the graphs
                //when vertex is invalid
                //when the vertex works
                    //when no incoming exist
                    //when incoming exist
    @Test
    public void testIterableIncomming() {
        //when vertex is null
        try {
            Vertex<String> from = null;
             graph.incoming(from);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be thrown
        }
        //when to vertex doesnot belong to the proper graph
        try {
            Graph<String, String> tempg = createGraph();
            Vertex<String> to = tempg.insert("hello");
            graph.incoming(to);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be thrown
        }
        //when the vertex is not found in the graph
        try {
            Vertex<String> from = graph.insert("first");
            String val = graph.remove(from);
            assertEquals(val, "first");
            graph.incoming(from);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should be thrown
        }
        Vertex<String> nullvertex = graph.insert(null);
        Vertex<String> normalvertex = graph.insert("first");
        Vertex<String> firstto = graph.insert("firstto");
        Vertex<String> secondto = graph.insert("secondto");
        Vertex<String> thirdto = graph.insert("thirdto");
        //when the vertex data is null
        Edge<String> firstnull = graph.insert(firstto, nullvertex, "first null");
        Edge<String> secondnull = graph.insert(secondto, nullvertex, "second null");
        Edge<String> thirdnull = graph.insert(thirdto, nullvertex, "third null");
        int count = 0;
        Iterable<Edge<String>> nulllist = graph.incoming(nullvertex);
        for (Iterator<Edge<String>> it = nulllist.iterator(); it.hasNext(); it.next()) {
            count++;
        }
        assertEquals(3, count);
        //when the vertex data is normal
        Edge<String> firstnorm = graph.insert(firstto, normalvertex, "first norm");
        Edge<String> secondnorm = graph.insert(secondto, normalvertex, "second norm");
        Edge<String> thirdnorm = graph.insert(thirdto, normalvertex, "third norm");
        count = 0;
        Iterable<Edge<String>> normlist = graph.incoming(normalvertex);
        for (Iterator<Edge<String>> it = normlist.iterator(); it.hasNext(); it.next()) {
            count++;
        }
        assertEquals(3, count);
    }

    //test getting the origin of an edges
    @Test
    public void testFrom() {
        try {
            graph.from(null);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should catch
        }
        try {
            Graph<String, String> tempg = createGraph();
            Vertex<String> first = tempg.insert("first");
            Vertex<String> second = tempg.insert("second");
            Edge<String> firste = tempg.insert(first, second, "firste");
            graph.from(firste);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should catch
        }
        Vertex<String> first = graph.insert("first");
        Vertex<String> second = graph.insert("second");
        Vertex<String> third = graph.insert("third");
        Vertex<String> fourth = graph.insert("fourth");
        Edge<String> seconde = graph.insert(first, third, "seconde");
        Edge<String> thirde = graph.insert(first, fourth, "fourthe");
        assertEquals(first, graph.from(seconde));
        assertEquals(first, graph.from(thirde));
    }

    //test getting the end of an edges
    @Test
    public void testTo() {
        try {
            graph.to(null);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should catch
        }
        try {
            Graph<String, String> tempg = createGraph();
            Vertex<String> first = tempg.insert("first");
            Vertex<String> second = tempg.insert("second");
            Edge<String> firste = tempg.insert(first, second, "firste");
            graph.to(firste);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should catch
        }
        Vertex<String> first = graph.insert("first");
        Vertex<String> second = graph.insert("second");
        Vertex<String> third = graph.insert("third");
        Vertex<String> fourth = graph.insert("fourth");
        Edge<String> seconde = graph.insert(first, third, "seconde");
        Edge<String> thirde = graph.insert(first, fourth, "fourthe");
        assertEquals(third, graph.to(seconde));
        assertEquals(fourth, graph.to(thirde));
    }

    //test setting and geting the label of a vertex
        //when the vertex is invalid
        //when the vertex is valid
            //setting it once
            //setting it a second time
    @Test
    public void testLabelV() {
        //when the vertex is null (setting)
        try {
            Vertex<String> tempv = null;
            graph.label(tempv, "tried");
            assertTrue(false);
        }
        catch (PositionException e) {
            //should catch
        }
        //when the vertex is invalid (setting)
        try {
            Graph<String, String> tempg = createGraph();
            Vertex<String> tempv = tempg.insert("hell0");
            graph.label(tempv, "tried");
            assertTrue(false);
        }
        catch (PositionException e) {
            //should catch
        }
        //when vertex is null (getting)
        try {
            Vertex<String> tempv = null;
            graph.label(tempv);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should catch
        }
        //when the vertex is invalid (getting)
        try {
            Graph<String, String> tempg = createGraph();
            Vertex<String> tempv = tempg.insert("hell0");
            graph.label(tempv);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should catch
        }
        //when the vertex is valid
        Vertex<String> first = graph.insert("first");
        graph.label(first, "firstLabel");
        assertEquals("firstLabel", graph.label(first));
        graph.label(first, "secondLabel");
        assertEquals("secondLabel", graph.label(first));
    }

    //test setting and getting the label of a edge
        //when the edge is invalid
        //when the edge is valid
            //setting it once
            //setting it a second time
    @Test
    public void testLabelE() {
        //when the edge is null (setting)
        try {
            Edge<String> tempe = null;
            graph.label(tempe, "tried");
            assertTrue(false);
        }
        catch (PositionException e) {
            //should catch
        }
        //when the edge is invalid (setting)
        try {
            Graph<String, String> tempg = createGraph();
            Vertex<String> tempv = tempg.insert("hell0");
            Vertex<String> tempv2 = tempg.insert("bye");
            Edge<String> firste = tempg.insert(tempv, tempv2, "firste");
            graph.label(firste, "tried");
            assertTrue(false);
        }
        catch (PositionException e) {
            //should catch
        }
        //when vertex is null (getting)
        try {
            Vertex<String> tempv = null;
            graph.label(tempv);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should catch
        }
        //when the vertex is invalid (getting)
        try {
            Graph<String, String> tempg = createGraph();
            Vertex<String> tempv = tempg.insert("hell0");
            Vertex<String> tempv2 = tempg.insert("bye");
            Edge<String> firste = tempg.insert(tempv, tempv2, "firste");
            graph.label(firste);
            assertTrue(false);
        }
        catch (PositionException e) {
            //should catch
        }
        //when the vertex is valid
        Vertex<String> first = graph.insert("first");
        Vertex<String> second = graph.insert("second");
        Edge<String> edge1 = graph.insert(first, second, "edge1");
        graph.label(edge1, "firstLabel");
        assertEquals("firstLabel", graph.label(edge1));
        graph.label(edge1, "secondLabel");
        assertEquals("secondLabel", graph.label(edge1));
    }
    //test cearing all the labels in the graph
        //works when some are set and some arent
        //when none are set
        //when all are set
    @Test
    public void testClearLabels() {
        Vertex<String> first = graph.insert("first");
        Vertex<String> second = graph.insert("second");
        Vertex<String> third = graph.insert("third");
        Edge<String> efirst = graph.insert(first, second, "efirst");
        Edge<String> esecond = graph.insert(second, third, "esecond");
        graph.label(first, "first");
        graph.label(second, "second");
        graph.label(third, "third");
        graph.label(efirst, "efirst");
        graph.label(esecond, "esecond");
        assertEquals("first", graph.label(first));
        assertEquals("second", graph.label(second));
        assertEquals("third", graph.label(third));
        assertEquals("efirst", graph.label(efirst));
        assertEquals("esecond", graph.label(esecond));
        graph.clearLabels();
        assertEquals(null, graph.label(first));
        assertEquals(null, graph.label(second));
        assertEquals(null, graph.label(third));
        assertEquals(null, graph.label(efirst));
        assertEquals(null, graph.label(esecond));

    }
}
