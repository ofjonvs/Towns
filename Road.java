package abc;

/*
 *Road class
 * 
 * @author Jonas da Silva
 *
 */

public class Road implements Comparable<Road>{

	
	private Town source;
	private Town destination;
	private int degrees;
	private String name;
	
	public Road(Town source, Town destination, int degrees, String name){
		this.source = source;
		this.destination = destination;
		this.degrees = degrees;
		this.name = name;
	}
	
	//copy constructor
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.name = name;
		degrees = 1;
	}
	
	//returns true if road contains town
	public boolean contains(Town town) {
		if(source.equals(town) || destination.equals(town))
			return true;
		return false;
	}
	
	
	//returns name
	public String toString() {
		return name;
	}
	
	//returns road name
	public String getName() {
		return name;
	}
	
	//returns second town on road
	public Town getDestination() {
		return destination;
	}
	
	//returns first town on road
	public Town getSource() {
		return source;
	}
	
	//returns 0 if the road names are the same and another number if not
	@Override
	public int compareTo(Road o) {
		int i = o.getName().compareTo(name);
		return i;
	}
	
	
	//returns distance of road
	public int getWeight() {
		return degrees;
	}
	
	 @Override
	   public boolean equals(Object o) {
	       Road road;
	       if(o instanceof Road) {
	           road = (Road) o;
	           if (road.contains(this.getSource()) && road.contains(this.getDestination())) {
	        	   return true;
	           }
	       }
	       return false;
	   }

	public void setName(String next) {
		name = next;		
	}
	
	public void setWeight(int weight){
		degrees = weight;
	}
	
	public void setSource(Town source){
		this.source = source;
	}
	
	
	
	

	   /**
	   * Sets the second town on the road to the desired reference.
	   * @param destination - the desired reference which will become the new destination.
	   */
	   public void setDestination(Town destination) {
	       this.destination = destination;
	   }

	  
	   /**
	   * Returns the hash code of the road, which is based on its two towns added together
	   * @return the hash code of the road, which is based on its two towns added together
	   */
	   @Override
	   public int hashCode() {
	       return getSource().hashCode() + getDestination().hashCode();
	   }
	  
	   
	
	
	
}

