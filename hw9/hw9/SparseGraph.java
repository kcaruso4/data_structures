package hw9;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;

import java.util.ArrayList;
import java.util.List;

/**
    An implementation of a directed graph using incidence lists
    for sparse graphs where most things aren't connected.
    @param <V> Vertex element type.
    @param <E> Edge element type.
*/
public class SparseGraph<V, E> implements Graph<V, E> {

    // Class for a vertex of type V
    private final class VertexNode<V> implements Vertex<V> {
        V data;
        Graph<V, E> owner;
        List<Edge<E>> outgoing;
        List<Edge<E>> incoming;
        Object label;

        VertexNode(V v) {
            this.data = v;
            this.outgoing = new ArrayList<>();
            this.incoming = new ArrayList<>();
            this.label = null;
        }

        @Override
        public V get() {
            return this.data;
        }

        @Override
        public void put(V v) {
            this.data = v;
        }

        public void setOwner(Graph<V, E> g) {
            this.owner = g;
        }
    }

    //Class for an edge of type E
    private final class EdgeNode<E> implements Edge<E> {
        E data;
        Graph<V, E> owner;
        VertexNode<V> from;
        VertexNode<V> to;
        Object label;

        // Constructor for a new edge
        EdgeNode(VertexNode<V> f, VertexNode<V> t, E e) {
            this.from = f;
            this.to = t;
            this.data = e;
            this.label = null;
        }

        @Override
        public E get() {
            return this.data;
        }

        @Override
        public void put(E e) {
            this.data = e;
        }

        public void setOwner(Graph<V, E> g) {
            this.owner = g;
        }
    }

    private List<Vertex<V>> vertices;
    private List<Edge<E>> edges;

    /** Constructor for instantiating a graph. */
    public SparseGraph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    // Checks vertex belongs to this graph
    private void checkOwner(VertexNode<V> toTest) {
        if (toTest.owner != this) {
            throw new PositionException();
        }
    }

    // Checks edge belongs to this graph
    private void checkOwner(EdgeNode<E> toTest) {
        if (toTest.owner != this) {
            throw new PositionException();
        }
    }

    // Converts the vertex back to a VertexNode to use internally
    private VertexNode<V> convert(Vertex<V> v) throws PositionException {
        try {
            VertexNode<V> gv = (VertexNode<V>) v;
            this.checkOwner(gv);
            return gv;
        } catch (ClassCastException ex) {
            throw new PositionException();
        }
    }

    // Converts and edge back to a EdgeNode to use internally
    private EdgeNode<E> convert(Edge<E> e) throws PositionException {
        try {
            EdgeNode<E> ge = (EdgeNode<E>) e;
            this.checkOwner(ge);
            return ge;
        } catch (ClassCastException ex) {
            throw new PositionException();
        }
    }

    //used to find a vertex in the vertex list
    private Vertex<V> findV(V v) {
        for (Vertex<V> graphv : this.vertices) {
            if ((v == null && graphv.get() == null) || v.equals(graphv.get())) {
                return graphv;
            }
        }
        return null;
    }

    private Edge<E> findE(Vertex<V> f, Vertex<V> t) {
        for (Edge<E> getedge : this.edges) {
            if ((f.equals(this.from(getedge)) && t.equals(this.to(getedge)))) {
                return getedge;
            }
        }
        return null;
    }

    @Override
    public Vertex<V> insert(V v) {
        // TODO
        VertexNode<V> newv = new VertexNode<V>(v);
        newv.setOwner(this);
        this.vertices.add(newv);
        return newv;
    }

    @Override
    public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e)
            throws PositionException, InsertionException {
        // TODO
        if (from == null || to == null ||
            this.findV(from.get()) == null || this.findV(to.get()) == null) {
            throw new PositionException();
        }
        //checks to make sure you are not making a self loop
        if (from.equals(to)) {
            throw new InsertionException();
        }
        //checks to make sure you are not creating a duplicate edge
        if (this.findE(from, to) != null) {
            throw new InsertionException();
        }
        VertexNode<V> fromNode = this.convert(from);
        VertexNode<V> toNode = this.convert(to);
        //adds the new edge
        EdgeNode<E> newe = new EdgeNode<E>(fromNode, toNode, e);
        newe.setOwner(this);
        fromNode.outgoing.add(newe);
        toNode.incoming.add(newe);
        this.edges.add(newe);
        return newe;
    }

    @Override
    public V remove(Vertex<V> v) throws PositionException,
            RemovalException {
        // TODO
        if (v == null || this.findV(v.get()) == null) {
            throw new PositionException();
        }
        VertexNode<V> vNode = this.convert(v);
        this.checkOwner(vNode);
        V vfinal = v.get();
        if ((vNode.outgoing).isEmpty() && (vNode.incoming).isEmpty()) {
            this.vertices.remove(v);
            return vfinal;
        }
        else {
            throw new RemovalException();
        }
    }

    @Override
    public E remove(Edge<E> e) throws PositionException {
        // TODO
        if (e == null || this.findE(this.from(e), this.to(e)) == null) {
            throw new PositionException();
        }
        EdgeNode<E> eNode = this.convert(e);
        this.checkOwner(eNode);
        E eremove = eNode.get();
        eNode.from.outgoing.remove(e);
        eNode.to.incoming.remove(e);
        this.edges.remove(e);
        return eremove;
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        // TODO
        List<Vertex<V>> vertexlist = new ArrayList<Vertex<V>>();
        for (Vertex<V> v : this.vertices) {
            vertexlist.add(v);
        }
        return vertexlist;
    }

    @Override
    public Iterable<Edge<E>> edges() {
        // TODO
        List<Edge<E>> edgelist = new ArrayList<Edge<E>>();
        for (Edge<E> e : this.edges) {
            edgelist.add(e);
        }
        return edgelist;
    }

    @Override
    public Iterable<Edge<E>> outgoing(Vertex<V> v) throws PositionException {
        // TODO
        if (v == null || this.findV(v.get()) == null) {
            throw new PositionException();
        }
        VertexNode<V> vNode = this.convert(v);
        this.checkOwner(vNode);
        List<Edge<E>> outgoinge = new ArrayList<Edge<E>>();
        for (Edge<E> e : vNode.outgoing) {
            outgoinge.add(e);
        }
        return outgoinge;
    }

    @Override
    public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {
        // TODO
        if (v == null || this.findV(v.get()) == null) {
            throw new PositionException();
        }
        VertexNode<V> vNode = this.convert(v);
        this.checkOwner(vNode);
        List<Edge<E>> incominge = new ArrayList<Edge<E>>();
        for (Edge<E> e : vNode.incoming) {
            incominge.add(e);
        }
        return incominge;
    }

    @Override
    public Vertex<V> from(Edge<E> e) throws PositionException {
        // TODO
        if (e == null) {
            throw new PositionException();
        }
        EdgeNode<E> en = this.convert(e);
        this.checkOwner(en);
        return this.convert(en).from;
    }

    @Override
    public Vertex<V> to(Edge<E> e) throws PositionException {
        // TODO
        if (e == null) {
            throw new PositionException();
        }
        EdgeNode<E> en = this.convert(e);
        this.checkOwner(en);
        return this.convert(en).to;
    }

    @Override
    public void label(Vertex<V> v, Object l) throws PositionException {
        // TODO
        if (v == null) {
            throw new PositionException();
        }
        VertexNode<V> vNode = this.convert(v);
        this.checkOwner(vNode);
        vNode.label = l;
    }

    @Override
    public void label(Edge<E> e, Object l) throws PositionException {
        // TODO
        if (e == null) {
            throw new PositionException();
        }
        EdgeNode<E> eNode = this.convert(e);
        this.checkOwner(eNode);
        eNode.label = l;
    }

    @Override
    public Object label(Vertex<V> v) throws PositionException {
        // TODO
        if (v == null) {
            throw new PositionException();
        }
        VertexNode<V> vNode = this.convert(v);
        this.checkOwner(vNode);
        return vNode.label;
    }

    @Override
    public Object label(Edge<E> e) throws PositionException {
        // TODO
        if (e == null) {
            throw new PositionException();
        }
        EdgeNode<E> eNode = this.convert(e);
        this.checkOwner(eNode);
        return eNode.label;
    }

    @Override
    public void clearLabels() {
        for (int i = 0; i < vertices.size(); i++) {
            this.label(vertices.get(i), null);
        }
        for (int i = 0; i < edges.size(); i++) {
            this.label(edges.get(i), null);
        }
    }

    private String vertexString(Vertex<V> v) {
        return "\"" + v.get() + "\"";
    }

    private String verticesToString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<V> v : this.vertices) {
            sb.append("  ").append(vertexString(v)).append("\n");
        }
        return sb.toString();
    }

    private String edgeString(Edge<E> e) {
        return String.format("%s -> %s [label=\"%s\"]",
                this.vertexString(this.from(e)),
                this.vertexString(this.to(e)),
                e.get());
    }

    private String edgesToString() {
        String edgs = "";
        for (Edge<E> e : this.edges) {
            edgs += "    " + this.edgeString(e) + ";\n";
        }
        return edgs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n")
                .append(this.verticesToString())
                .append(this.edgesToString())
                .append("}");
        return sb.toString();
    }
}
