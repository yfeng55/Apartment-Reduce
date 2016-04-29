import java.util.HashSet;


public class Util {
	
	//detects mispelled words within 2 letters
	public static boolean isValidNeighborhood(String neighborhood){
		
		HashSet<String> neighborhood_set = new HashSet();

		neighborhood_set.add("Battery Park City");
		neighborhood_set.add("Bowery");
		neighborhood_set.add("Chinatown");
		neighborhood_set.add("Civic Center");
		neighborhood_set.add("East Village");
		neighborhood_set.add("Financial District");
		neighborhood_set.add("Greenwich Village");
		neighborhood_set.add("Little Italy");
		neighborhood_set.add("Lower East Side");
		neighborhood_set.add("NoHo");
		neighborhood_set.add("NoLita");
		neighborhood_set.add("SoHo");
		neighborhood_set.add("Tribeca");
		neighborhood_set.add("Two Bridges");
		neighborhood_set.add("West Village");
		neighborhood_set.add("Chelsea");
		neighborhood_set.add("Flatiron District");
		neighborhood_set.add("Garment District");
		neighborhood_set.add("Gramercy Park");
		neighborhood_set.add("Hell's Kitchen");
		neighborhood_set.add("Kips Bay");
		neighborhood_set.add("Koreatown");
		neighborhood_set.add("Midtown East");
		neighborhood_set.add("Murray Hill");
		neighborhood_set.add("NoMad");
		neighborhood_set.add("Stuyvesant Town - Peter Cooper Village");
		neighborhood_set.add("Theater District");
		neighborhood_set.add("Central Harlem");
		neighborhood_set.add("Central Park");
		neighborhood_set.add("East Harlem");
		neighborhood_set.add("Inwood");
		neighborhood_set.add("Upper East Side");
		neighborhood_set.add("Upper West Side");
		neighborhood_set.add("Washington Heights");
		neighborhood_set.add("West Harlem");
		
		// check if the neighborhood name is contained in the known neighborhood list
		for(String name : neighborhood_set){
			if(neighborhood.toLowerCase().equals(name.toLowerCase())){
				return true;
			}
		}
		
		//TODO: check if neighborhood is within 2 characters of a known neighborhood name (in order to account for mispellings)
		
		
		return false;
	}
	
}
