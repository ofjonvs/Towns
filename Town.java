package abc;

/*
 *Town class
 * 
 * @author Jonas da Silva
 *
 */

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Town implements Comparable<Town>{
	
	private String name;
	private LinkedHashSet<Town> adjacentTowns;
	private LinkedList<Town> shortestPath;
	
	public Town(String name) {
		this.name = name;
		adjacentTowns = new LinkedHashSet<Town>();
		shortestPath = new LinkedList<Town>();
	}
	
	//copy constructor
	public Town (Town templateTown) {
		name = templateTown.name;
		adjacentTowns = new LinkedHashSet<Town>();
		shortestPath = new LinkedList<Town>();
	}
	
	//returns name
	public String getName() {
		return name;
	}
	
	//set name
	public void setName(String name) {
		this.name = name;
	}
	
	public LinkedHashSet<Town> getAdjacent(){
		return adjacentTowns;
	}
	
	//set adjacentTowns
	public void setAdjacent(LinkedHashSet<Town> adjacentTown) {
	       adjacentTowns = adjacentTown;
	   }
	
	//adds adjacent town
	public boolean addAdjacent(Town town) {
	       return adjacentTowns.add(town);
	   }
	
	//removes adjacent town
	public boolean removeAdjacent(Town town) {
	       return adjacentTowns.remove(town);
	   }
	

	//returns 0 if town names are the same and another number if not
	@Override
	public int compareTo(Town o) {
		int i = o.getName().compareTo(name);
		return i;
	}

	//returns name
	public String toString() {
		return name;
	}
	
	//returns name hash code
	public int hashCode() {
		int i = name.hashCode();
		return i;
	}
	
	//returns true if town names are equal and false if not
	public boolean equals(Object obj) {
		Town town = (Town) obj;
		if (town.getName().equals(name))
			return true;
		return false;				
	}
	
	//gets shortest path
	public LinkedList<Town> getShortestPath(){
		return shortestPath;
	}
	
	
	
	

}