package abc;

import java.io.File;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/*
 *Graph Manager class
 * 
 * @author Jonas da Silva
 *
 */

public class TownGraphManager implements TownGraphManagerInterface {
	
	private Graph graph = new Graph();



	
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		graph.addVertex(destination);
		graph.addVertex(source);
		Road road = graph.addEdge(source, destination, weight, roadName);
		
		if(road == null)
			return false;
		return true;
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		Road road = graph.getEdge(source, destination);
		if(road == null) {
			return null;
		}
		return road.getName();
	}

	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) {
		Town town = new Town(v);
		return graph.addVertex(town);
	}

	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	@Override
	public Town getTown(String name) {
		Set<Town> vertices = graph.vertexSet();
	       Iterator<Town> iterator = vertices.iterator();
	       Town town = new Town(name);
	       Town iterTown;
	       while(iterator.hasNext()) {
	           iterTown = iterator.next();
	           if(iterTown.compareTo(town) == 0) {
	        	   return town;
	           }
	       }
	       return null;
	}

	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) {
		Town town = new Town(v);
		return graph.containsVertex(town);
	}
	
	
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		return graph.containsEdge(source, destination);
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {
		Set<Road> roadSet = graph.edgeSet();
		ArrayList<String> allRoads = new ArrayList<String>();
		for(Road road : roadSet) {
			allRoads.add(road.getName());
		}
		Collections.sort(allRoads);
		return allRoads;
	}

	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		Road r = graph.getEdge(source, destination);
		if(r == null) {
			return false;
		}
		graph.removeEdge(source, destination, r.getWeight(), road);
		return true;
	}

	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) {
		Town town = new Town(v);
		return graph.removeVertex(town);
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() {
		Set<Town> townSet = graph.vertexSet();
		ArrayList<String> arrayList = new ArrayList<String>();
		for(Town town : townSet) {
			arrayList.add(town.getName());
		}
		Collections.sort(arrayList);
		return arrayList;
	}

	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
				
		 Town source = new Town(town1);
		 Town destination = new Town(town2);
		 ArrayList<String> path = new ArrayList<String>();
		 if(graph.containsVertex(source) && graph.containsVertex(destination)
				 && !graph.edgesOf(source).isEmpty() && !graph.edgesOf(destination).isEmpty()) {
			 path = graph.shortestPath(source, destination);
			 if(path == null) {
	        	   return new ArrayList<String>();
			 }
			 return path;
		 }
		 return new ArrayList<String>();
		/*
		 
		Town source = new Town("");
	    Town destination = new Town("");
	    Road road = new Road(destination, destination, 0, town2);
	   // importSetsToGraph();
		for(Town town : graph.vertexSet()) {
			if(town.getName().equals(town1))
				source = town;
			if(town.getName().equals(town2))
				destination = town;
		}
		
		
		
		for(Road r : graph.edgeSet()) {
		//	System.out.println("birju");
				
		}
		
		
		
		
		if(!graph.edgesOf(source).isEmpty())
			System.out.println("3");
		
		if(!graph.edgesOf(destination).isEmpty())
			System.out.println("4");
		
		for(Town town : graph.vertexSet()) {
			if(town.getName().equals(town1)) {
				for(Town t : graph.vertexSet()) {
					if(t.getName().equals(town2)) {
						if(graph.edgesOf(town).isEmpty())
							System.out.println("3");					
						if(graph.edgesOf(t).isEmpty())
							System.out.println("4");
						if((graph.containsVertex(town) && graph.containsVertex(t))
					              && (!graph.edgesOf(town).isEmpty() && !graph.edgesOf(t).isEmpty())) {
							System.out.println("dale higgins");
						}
					}
				}
			}
		}
		
	      // Town source = new Town(town1);
	     //  Town destination = new Town(town2);
	       ArrayList<String> path = new ArrayList<String>();
	       if((graph.containsVertex(source) && graph.containsVertex(destination))
	              && (!graph.edgesOf(source).isEmpty() && !graph.edgesOf(destination).isEmpty())) 
	    		   {
	    	   System.out.print("works");
	           path = graph.shortestPath(source, destination);
	           if(path == null) 
	        	   return new ArrayList<String>();
	           return path;
	       }
	       
	       System.out.print("hello");
	       
	       return new ArrayList<String>();
		
		
	/*	Graph graph = new Graph();
		Town source = new Town("");
		Town destination = new Town("");
		for(Town town : towns) {
			graph.addVertex(town);
			if(town.getName().equals(town1))
				source = town;
			if(town.getName().equals(town2))
				destination = town;
		}
	for(Road road : roads) {
			graph.addEdge(road.getSource(), road.getDestination(), road.getWeight(), road.getName());
		}
		for(Town town : graph.vertexSet()) {
			if(town.getName().equals(town1))
				source = town;
			if(town.getName().equals(town2))
				destination = town;
		}
		
		ArrayList<String> path = graph.shortestPath(source, destination);
		return path;

		
	/*	HashSet<Town> townSet = new HashSet<Town>();
		HashSet<Road> roadSet = new HashSet<Road>();
		for(Town town : towns) {
			townSet.add(town);
		}
		for(Road road : roads) {
			roadSet.add(road);
		}
		Town source = new Town("");
		Town destination = new Town("");
		for(Town town : towns){
			if(town.getName().equals(town1))
				source = town;
			if(town.getName().equals(town2))
				destination = town;
		}
		Graph graph = new Graph(towns, roads);
		//graph.dijkstraShortestPath(source);
		ArrayList<String> path = graph.shortestPath(source, destination);
		return path;
		
	/*	ArrayList<String> listOfTowns= graph.shortestPath(source,destination);
		for(int i = 0; i < listOfTowns.size(); i++) {
			path.add(listOfTowns.get(i).toString());
		}
		path.add(destination.toString());
		return path;
		/*
		HashSet<Town> townSet = new HashSet<Town>();
		HashSet<Road> roadSet = new HashSet<Road>();
		for(Town town : towns) {
			townSet.add(town);
		}
		for(Road road : roads) {
			roadSet.add(road);
		}
		Graph graph = new Graph(townSet, roadSet);
		for(Town town : towns) {
			if(town.getName().equals(town1))
				source = town;
			if(town.getName().equals(town2))
				destination = town;
		}
		return graph.shortestPath(source, destination);*/
	}
	
	

	@Override
	public void populateTownGraph(File file) throws FileNotFoundException, IOException {
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			char[] charArray = line.toCharArray();
			int counter1 = 0;
			int counter2 = 0;
			for(int i = 0; i < charArray.length; i++) {
				if(charArray[i] == ';')
					counter1 += 1;
				if(charArray[i] == ',') {
					counter2 += 1;
					if(counter1 > 0) {
						throw new IOException();
					}
				}						
			}
			if(!(counter1 == 2 && counter2 == 1))
				throw new IOException();
			String roadName = "";
			int distance = 0;
			String distanceString = "" ;
			String town1 = "";
			String town2 = "";
			int x = 0;
			for(int i = 0; i < charArray.length; i++) {
				if(charArray[i] == ',') {
					x = i + 1;
					break;
				}
				roadName += charArray[i];
			}
			
			for(int i = x; i < charArray.length; i++) {
				if(charArray[i] == ';') {
					x = i + 1;
					break;
				}
				if(charArray[i] == ' ')
					continue;
				distanceString += charArray[i];
			}
			distance = Integer.parseInt(distanceString);
			
			for(int i = x; i < charArray.length; i++) {
				if(charArray[i] == ';') {
					x = i + 1;
					break;
				}
				town1 += charArray[i];
			}
			
			for(int i = x; i < charArray.length; i++) {
				town2 += charArray[i];
			}
			
			addTown(town1);
			addTown(town2);
			addRoad(town1, town2, distance, roadName);		
		}
	}
	
	
}
