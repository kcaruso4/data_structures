package hw9;

import exceptions.InsertionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;

/**
 * Search for the shortest path between two endpoints using
 * Djikstra's. We use a HashMap to store all the vertices so we can
 * find them by name (i.e. their coordinates) when inserting for a
 * fast duplicates check.
 *
 * Vertex data is the coordinates, stored as a String.
 * Vertex label is the Edge into it on the path found.
 * Edge data is the road name, stored as a String.
 * Edge label is the road length, stored as a Double.
 *
 */
public final class StreetSearcher {

    //maybe add more?
    private static class LoadedVertex {
        Vertex<String> vert;
        double weight;
        boolean visited;

        LoadedVertex(Vertex<String> v) {
            vert = v;
            weight = MAX_DISTANCE;
            visited = false;
        }

        public double getWeight() {
            return weight;
        }

        public boolean getVisited() {
            return visited;
        }

        public void setVisited(boolean n) {
            visited = n;
        }

        public void setWeight(double w) {
            weight = w;
        }
    }

    private static class StreetComparator implements Comparator<LoadedVertex> {

        //override compare() method of comaprator
        public int compare(LoadedVertex v1, LoadedVertex v2) {
            if (v1.getWeight() < v2.getWeight()) {
                return -1;
            }
            else if (v1.getWeight() > v2.getWeight()) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    // useful for marking distance to nodes, or use Double.POSITIVE_INFINITY
    private static final double MAX_DISTANCE = 1e18;

    // Global variables, bleh
    private static Map<String, Vertex<String>> vertices = new HashMap<>();
    private static SparseGraph<String, String> graph = new SparseGraph<>();
    private static Map<Vertex<String>, LoadedVertex> pd = new HashMap<>();
    private static PriorityQueue<LoadedVertex> pq =
        new PriorityQueue<LoadedVertex>(5, new StreetComparator());

    // Silencing checkstyle
    private StreetSearcher() {}

    // Get the path by tracing labels back from end to start.
    private static List<Edge<String>> getPath(Vertex<String> end,
                                              Vertex<String> start) {
        if (graph.label(end) != null) {
            List<Edge<String>> path = new ArrayList<>();

            Vertex<String> cur = end;
            Edge<String> road;
            while (cur != start) {
                road = (Edge<String>) graph.label(cur);  // unchecked cast ok
                path.add(road);
                cur = graph.from(road);
            }
            return path;
        }
        return null;
    }

    // Print the path found.
    private static void printPath(List<Edge<String>> path,
                                  double totalDistance) {
        if (path == null) {
            System.out.println("No path found");
            return;
        }

        System.out.println("Total Distance: " + totalDistance);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println(path.get(i).get() + " "
                               + graph.label(path.get(i)));
        }
    }


    // Djikstra's Algorithm to find shortest path.
    private static void findShortestPath(String startName, String endName) {
        Vertex<String> start = vertices.get(startName);
        Vertex<String> end = vertices.get(endName);
        double totalDist = -1;

        // TODO - write this!
        /*
           The biggest issue here is how to create and maintain an
           adaptable priority queue somehow. You do not have to write
           the most efficient implementation possible. It does need to
           be correct. You will need to keep track of distances for
           vertices. Feel free to update the SparseGraph
           implementation to handle that for you. Another option is to
           create a nested class here for comparable objects that have
           vertex info and distance info to use in a standard priority
           queue.
        */

        LoadedVertex lCurr = new LoadedVertex(start);
        lCurr.setWeight(0);
        lCurr.setVisited(true);
        pd.put(start, lCurr);
        Vertex<String> vCurr = start;

        while (!vCurr.equals(end) && vCurr != null) {
            Iterable<Edge<String>> neighbors = graph.outgoing(vCurr);
            lCurr = pd.get(vCurr);
            double currdis = lCurr.getWeight();

            //go through each neighbor
            for (Edge<String> ed : neighbors) {
                Vertex<String> to = graph.to(ed);
                LoadedVertex tLCurr = pd.get(to);

                //continues to next edge if the to node is already visited
                if (pd.containsKey(to) && tLCurr.getVisited()) {
                    continue;
                }
                double newweight = currdis + (double) graph.label(ed);
                //if the neighbor does not already have a weight
                if (tLCurr == null) {
                    pd.put(to, new LoadedVertex(to));
                    tLCurr = pd.get(to);
                    pq.add(tLCurr);

                }
                //checking to see if the neighbor weight needs to be updated
                lCurr = tLCurr;
                if (newweight < lCurr.getWeight()) {
                    lCurr.setWeight(newweight);
                    graph.label(to, ed);
                }
            }
            lCurr = pq.poll();
            if (lCurr == null) {
                break;
            }
            lCurr.setVisited(true);
            vCurr = lCurr.vert;

        }
        if (vCurr != null) {
            totalDist = pd.get(end).getWeight();
        }

        // These method calls will create and print the path for you
        List<Edge<String>> path = getPath(end, start);
        printPath(path, totalDist);
    }


    // Add an endpoint to the network if it is a new endpoint
    private static Vertex<String> addLocation(String name) {
        if (!vertices.containsKey(name)) {
            Vertex<String> v = graph.insert(name);
            vertices.put(name, v);
            return v;
        }
        return vertices.get(name);
    }


    // Load network from fileName, returns number of roads
    private static int loadNetwork(String fileName)
            throws FileNotFoundException {

        int numRoads = 0;

        // Read in from file fileName
        Scanner input = new Scanner(new FileInputStream(new File(fileName)));
        while (input.hasNext()) {

            // Parse the line in to <end1> <end2> <road-distance> <road-name>
            String[] tokens = input.nextLine().split(" ");
            String fromName = tokens[0];
            String toName = tokens[1];
            double roadDistance = Double.parseDouble(tokens[2]);
            String roadName = tokens[3];

            // Get the from and to endpoints, adding if necessary
            Vertex<String> from = addLocation(fromName);
            Vertex<String> to =  addLocation(toName);

            // Add the road to the network - We assume all roads are two-way and
            // ignore if we've already added the road as a reverse of another
            try {

                Edge<String> road = graph.insert(from, to, roadName);
                Edge<String> backwardsRoad = graph.insert(to, from, roadName);
                numRoads += 2;

                // Label each road with it's weight
                graph.label(road, roadDistance);
                graph.label(backwardsRoad, roadDistance);

            } catch (InsertionException ignored) {
                // Nothing to do.
            }
        }

        return numRoads;
    }

    private static void checkValidEndpoint(String endpointName) {
        if (!vertices.containsKey(endpointName)) {
            throw new IllegalArgumentException(endpointName);
        }
    }

    /**
     * Main method.
     * @param args See usage.
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: " +
                    "StreetSearcher <map_name> <start_coords> <end_coords>");
            return;
        }

        String fileName  = args[0];
        String startName = args[1];
        String endName   = args[2];

        try {

            int numRoads = loadNetwork(fileName);
            System.out.println("Network Loaded!");
            System.out.println("Loaded " + numRoads + " roads");
            System.out.println("Loaded " + vertices.size() + " endpoints");

            checkValidEndpoint(startName);
            checkValidEndpoint(endName);

        } catch (FileNotFoundException e) {
            System.err.println("Could not find file " + fileName);
            return;
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid Endpoint: " + e.getMessage());
            return;
        }

        findShortestPath(startName, endName);
    }


}
