package abc;

/*
 *Graph class
 * 
 * @author Jonas da Silva
 *
 */

import java.util.*;

public class Graph implements GraphInterface<Town, Road > {
	
	private HashSet <Town> towns;
	private HashSet <Road> roads;
	private ArrayList<String> shortestPath;
	
	public Graph() {
		towns = new HashSet<Town>();
		roads = new HashSet<Road>();
	}

	public Graph(HashSet<Town> towns, HashSet<Road> roads) {
		this.towns = towns;
		this.roads = roads;
	}
	
	 /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		for(Road road : roads){
			if(road.getSource().compareTo(sourceVertex) == 0)  {
				if(road.getDestination().compareTo(destinationVertex) == 0) {
					return road;
				}
			}      
			if(road.getDestination().compareTo(sourceVertex) == 0) {
				if(road.getSource().compareTo(destinationVertex) == 0) {
					return road;
				}
			}
		}
		return null;
	}
	
	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(sourceVertex == null) {
			throw new NullPointerException();      
		}
		if(destinationVertex == null) {
			throw new NullPointerException();
		}
		if(!containsVertex(sourceVertex)){
			throw new IllegalArgumentException();
		}
		if(!containsVertex(destinationVertex)) {
			throw new IllegalArgumentException();
		}
		Road road = new Road(sourceVertex, destinationVertex, weight, description);
		boolean added = roads.add(road);      
		sourceVertex.addAdjacent(destinationVertex);
		destinationVertex.addAdjacent(sourceVertex);
		if(added) 
			return road;
		return null;
	}

	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) {
		if (v == null)
			throw new NullPointerException();
		boolean contains = false;
		for(Town town : towns) {
			if (town == v)
				contains = true;
		}
		if(!contains) {
			towns.add(v);
			return true;
		}
		return false;
	}

	/**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
	       Road test = new Road(sourceVertex, destinationVertex, "");
	       if(roads.contains(test)) {
	    	   return true;
	       }
	       else
	    	   return false;
	}

	/**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) {
		if(v == null)
			return false;
		for(Town town : towns) {
			if(town.getName().equals(v.getName()))
				return true;
		}
		return false;
	}

	 /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() {
		return roads;
	}

	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> edges = new HashSet<Road>();
		for(Road road : roads) {
			if(road.contains(vertex))
				edges.add(road);
		}
		return edges;
	}

	/**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
	
		
		  
		       Road removeRoad = new Road(sourceVertex, destinationVertex, weight, description);
		       Iterator<Road> iterator = roads.iterator();
		       Road iteratorRoad;
		      
		       while(iterator.hasNext()) {
		           iteratorRoad = iterator.next();
		           if((weight > -1 && weight == iteratorRoad.getWeight()) || weight == -1 && (description != null )) {
		        	   if(iteratorRoad.equals(removeRoad)) {
		        		   if (description.equals(iteratorRoad.getName()) || description == null) {
		        			   roads.remove(iteratorRoad);
		                       sourceVertex.removeAdjacent(destinationVertex);
		                       destinationVertex.removeAdjacent(sourceVertex);
		                       return iteratorRoad;
		        		   }
		               }
		           }
		       }
		       return null;
		
		
	}

	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) {		
		 HashSet<Road> roadSet = new HashSet<Road>();
		
		 if(towns.contains(v)) {
			 towns.remove(v);
			 Iterator<Road> iterator = roads.iterator();
			 Road road;
			 
			 while(iterator.hasNext()) {
				 road = iterator.next();
				 if(road.contains(v)) {
					 roadSet.add(road);
				 }
			 }
			 for(Road r: roadSet) {
				 towns.remove(r);
			 }
			 return true;
		 }
		 return false;
	}

	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() {
		return towns;
	}

	
	
    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {

		shortestPath = new ArrayList<String>();
		Queue<Town> vertexQueue = new PriorityQueue<Town>();
		HashSet<Town> containedVertices = new HashSet<Town>();
		HashSet<Town> toBeRemoved = new HashSet<Town>();
		HashSet<Road> containedEdges = new HashSet<Road>();
		HashSet<Road> possibleEdges = new HashSet<Road>();
		HashSet<Road> currentEdges = new HashSet<Road>();
		
		shortestPath.add(sourceVertex.getName() + " via NONE to " + sourceVertex.getName() + " 0 mi");
		containedVertices.add(sourceVertex);
		vertexQueue.add(sourceVertex);
		currentEdges = (HashSet<Road>) edgesOf(sourceVertex);
		for(Road r: currentEdges) {
			Town destination;
			if(r.getSource().equals(sourceVertex)) 
				destination = r.getDestination();
			else
				destination = r.getSource();
			shortestPath.add(sourceVertex.getName() + " via " + r.getName() +
					" to " + destination.getName() + " " + r.getWeight() + " mi");
           }
         
		while((containedVertices.size() != towns.size()) && vertexQueue.size() >= 0) {
			for(Town t: vertexQueue) {
				currentEdges = (HashSet<Road>) edgesOf(t);
				int minWeight = 999999999;
				Road minimum = null;				
				for(Road r: currentEdges) {					
					if((r.getWeight() < minWeight) && !containedEdges.contains(r)
							&& !(containedVertices.contains(r.getSource())
									&& containedVertices.contains(r.getDestination()))) {
						minimum = r;
						minWeight = r.getWeight();
					}
				}
				if(minimum != null) {
					if(minimum.getSource().equals(t)) possibleEdges.add(minimum);
					else possibleEdges.add(new Road(minimum.getDestination(), minimum.getSource(),
							minimum.getWeight(), minimum.getName()));
				}
				else toBeRemoved.add(t);
			}
			
			for(Town town: toBeRemoved) {
				vertexQueue.remove(town);
			}
              
			int min = 999999;
			Road minimum = null;
			
			for(Road r: possibleEdges) {
				if(r.getWeight() < min) {
					minimum = r;
					min = r.getWeight();
				}
			}
			
			if(minimum != null) {
				
				containedEdges.add(minimum);
				Town newNode = minimum.getDestination();
				containedVertices.add(newNode);
				vertexQueue.add(newNode);
				currentEdges = (HashSet<Road>) edgesOf(newNode);
				
				for(Road road: currentEdges) {
					String nextNodeName;
					if(road.getSource().equals(newNode))
						nextNodeName = road.getDestination().getName();
					else 
						nextNodeName = road.getSource().getName();
					
					int notationIndex = -1;
					for(int i = 0; i < shortestPath.size(); i++) {
						if(shortestPath.get(i).contains("to " + nextNodeName)) {
							notationIndex = i;
							break;
                           }
                       }
					
					if(notationIndex == -1) {						
                           for(int i = 0; i < shortestPath.size(); i++) {
                        	   if(shortestPath.get(i).contains("to " + newNode.getName())) {
                        		   break;
                               }
                           }
                          
                           shortestPath.add(newNode.getName() + " via " + road.getName() + " to " + nextNodeName + " "
                        		   + (road.getWeight()) + " mi");
					}
					else {
						
						int neighborWeight = getTotalWeight(shortestPath.get(notationIndex), sourceVertex);
						int newNodeWeight = -1;
						for(int i = 0; i < shortestPath.size(); i++) {
							if(shortestPath.get(i).contains("to " + newNode.getName())) {
								newNodeWeight = getTotalWeight(shortestPath.get(i), sourceVertex);
								break;
							}
						}
               
						if(newNodeWeight + road.getWeight() < neighborWeight) {
							shortestPath.remove(notationIndex);
							shortestPath.add(newNode.getName() + " via " + road.getName() +
									" to " + nextNodeName + " " + (road.getWeight()) + " mi");
						}
					}
				}
			}
			else break;
			possibleEdges.clear();
			toBeRemoved.clear();
		}
	}
	
	private int getTotalWeight(String string, Town sourceVertex) {
		// TODO Auto-generated method stub
		return 0;
	}


	
    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */   
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
	       dijkstraShortestPath(sourceVertex);
	       String currentNode = "";
	       ArrayList<String> shortPath = new ArrayList<String>();
	       Stack<String> path = new Stack<String>();
	       boolean pathExists = false;      
	       for(int i = 0; i < shortestPath.size(); i++) {
	           if(shortestPath.get(i).contains("to " + destinationVertex.getName())) {
	               path.push(shortestPath.get(i));
	               currentNode = shortestPath.get(i).substring(0, shortestPath.get(i).indexOf(" via"));
	               pathExists = true;
	               break;
	           }
	       }
	       if(!pathExists)
	    	   return null;
	       while(!currentNode.equals(sourceVertex.getName())) {
	    	   for(int i = 0; i < shortestPath.size(); i++) {
	        	   if(shortestPath.get(i).contains("to " + currentNode)) {
	        		   path.push(shortestPath.get(i));
	        		   currentNode = shortestPath.get(i).substring(0, shortestPath.get(i).indexOf(" via"));
	        		   break;
	        	   }
	    	   }
	       }
	       
	       while(!path.empty()) {
	           shortPath.add(path.pop());
	       }
	      
	       return shortPath;
	}
	
	

}