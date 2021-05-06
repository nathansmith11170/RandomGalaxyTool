package abstractmodel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of an undirected graph
 */
public class Graph implements IGraph {
    /**
     * A representation of a node in a graph. Includes a list of neighbors.
     */
    public class Node implements Comparable<Node>, Comparator<Node>{
        /* fields */
        final String identifier;
        ArrayList<String> neighbors;
    
        /* public facing accessors */
        public String getId() {
            return this.identifier;
        }
    
        public ArrayList<String> getNeighbors() {
            return this.neighbors;
        }
    
        /* Constructor */
        public Node( String id, ArrayList<String> neighbors ) {
            this.identifier = id;
            this.neighbors = neighbors;
        }

        @Override
        public boolean equals( Object o ) {
            if( o.getClass() != Node.class ) {
                return false;
            }
            Node cast = (Node) o;
            return this.getId() == cast.getId();
        }
    
        @Override
        public int compare(Node o1, Node o2) {
            return o1.getId().compareTo( o2.getId() );
        }
    
        @Override
        public int compareTo(Node o) {
            return this.getId().compareTo( o.getId() );
        }
    
        /**
         * This method adds to the list of neighbors
         * 
         * @param id
         *          The ID of the Node we are adding to {@code this.neighbors}
         * @throws IllegalArgumentException
         *          If the id is already a neighbor
         */
        public void addNeighbor(String id) throws IllegalArgumentException {
            if( this.neighbors.contains(id) ) {
                throw new IllegalArgumentException(id + " already is a neighbor.");
            }
            else {
                this.neighbors.add(id);
            }
        }

        /**
         * This method removes from the list of neighbors
         * 
         * @param id
         *          The ID of the Node we are adding to {@code this.neighbors}
         * @throws IllegalArgumentException 
         *          If the id is not a neighbor
         */
        public void removeNeighbor( String id ) throws IllegalArgumentException {
            if( !this.neighbors.contains( id ) ) {
                throw new IllegalArgumentException( id + " is not a neighbor of " + this.identifier );
            }
            else {
                this.neighbors.remove( id );
            }
        }
    }

    /**
     * This map of {@code Node} objects represents the graph. Each key matches the private field {@code Node.identifier}
     */
    private Map<String, Node> graph;

    /**
     * (@inheritDoc)
     */
    public ArrayList<ArrayList<String>> getGraph() {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        for( Node n : this.graph.values() )  {
            ArrayList<String> temp = new ArrayList<String>();
            temp.add( n.getId() );
            temp.addAll(n.getNeighbors());
            result.add(temp);
        }
        return result;
    }

    public void setGraph(Map<String, Node> g) {
        this.graph = g;
    }

    public Graph() {
        this.graph = new HashMap<String, Node>();
    }

    public Graph( int nodeQuantity ) {
        this.graph = new HashMap<String, Node>();
        int inc = 1;
        while( graph.size() < nodeQuantity ) {
            this.addIsolatedNode( String.valueOf( inc ) );
            inc++;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addIsolatedNode(String id) throws IllegalArgumentException {
        Node toAdd = new Node(id, new ArrayList<String>());
        if( this.containsNode(id) ) {
            throw new IllegalArgumentException( "The graph already contains a Node with identifier " + id );
        }
        else {
            this.graph.put(id, toAdd);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSpecificNode(String id, ArrayList<String> neighbors) throws IllegalArgumentException {
        Node toAdd = new Node(id, neighbors);
        if( this.containsNode(id) ) {
            throw new IllegalArgumentException(id + " already exists in graph.");
        }
        else if ( !neighbors.stream().allMatch( (String s) -> {return this.containsNode(s);} ) ) {
            throw new IllegalArgumentException( "The graph does not contain one of the specified neighbors " + neighbors.toString() );
        }
        else {
            this.graph.put(id, toAdd);
            for( String neighbor : neighbors ) {
                Node alteredNeighbor = this.graph.get(neighbor);
                alteredNeighbor.addNeighbor(id);
                this.graph.replace(neighbor, alteredNeighbor);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsNode(String Node_id) {
        return this.graph.containsKey(Node_id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdge( String Node_one, String Node_two ) throws IllegalArgumentException {
        if( !this.containsNode(Node_one) ) {
            throw new IllegalArgumentException("Node " + Node_one + " does not exist in graph.");
        }
        if( !this.containsNode(Node_two) ) {
            throw new IllegalArgumentException("Node " + Node_two + " does not exist in graph.");
        }
        Node v1 = this.graph.get(Node_one);
        Node v2 = this.graph.get(Node_two);
        v1.addNeighbor(Node_two);
        v2.addNeighbor(Node_one);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsEdge(String Node_one, String Node_two) throws IllegalArgumentException {
        if( !this.containsNode(Node_one) ) {
            throw new IllegalArgumentException("Node " + Node_one + " does not exist in graph.");
        }
        if( !this.containsNode(Node_two) ) {
            throw new IllegalArgumentException("Node " + Node_two + " does not exist in graph.");
        }

        Node v1 = this.graph.get(Node_one);
        Node v2 = this.graph.get(Node_two);

        boolean v2ExistsinNeighborsofv1 = v1.getNeighbors().contains(Node_two);
        boolean v1ExistsinNeighborsofv2 = v2.getNeighbors().contains(Node_one);

        return v2ExistsinNeighborsofv1 && v1ExistsinNeighborsofv2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeNode( String id ) throws IllegalArgumentException {
        if( !this.containsNode( id ) ) {
            throw new IllegalArgumentException( "Node " + id + " does not exist in graph." );
        }
        Node toRemove = this.graph.get( id );
        this.graph.remove( id );
        for( String neighbor : toRemove.getNeighbors() ) {
            Node temp = this.graph.get( neighbor );
            temp.removeNeighbor( id );
            this.graph.replace( neighbor, temp );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEdge( String Node_one, String Node_two ) throws IllegalArgumentException {
        if( !this.containsNode( Node_one ) ) {
            throw new IllegalArgumentException( "Node " + Node_one + " does not exist in graph." );
        }
        if( !this.containsNode( Node_two ) ) {
            throw new IllegalArgumentException( "Node " + Node_two + " does not exist in graph." );
        }
        if( !this.containsEdge( Node_one, Node_two) ) {
            throw new IllegalArgumentException( "The specified edge (" + Node_one + "," + Node_two + ") does not exist.");
        }
        Node n1 = this.graph.get( Node_one );
        Node n2 = this.graph.get( Node_two );

        n1.removeNeighbor( Node_two );
        n2.removeNeighbor( Node_one );

        this.graph.replace( Node_one, n1 );
        this.graph.replace( Node_two, n2 );
    }
}