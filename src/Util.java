import java.util.HashSet;


public class Util {
	
	public static final double PRICE_MIN = 200;				// minimum price for a listing to not be considered spam
	public static final double SPAM_SCORE_THRESHOLD = 0.5;	// threshold score for a listing to be considered spam 
	
	
	public static boolean isSpam(String listing, double price){
		
		// (1) check if the neighborhood is valid //
		if(!isValidNeighborhood(listing)){
			return true;
		}
		
		// (2) check that the price is above the minimum //
		else if(price < PRICE_MIN){
			return true;
		}
		
		// (3) assign a score for the title from 0 - 1 and check if it exceeds the spam_score threshold
		else if(spamScore(listing, price) > SPAM_SCORE_THRESHOLD){
			return true;
		}
		
		return false;
	}
	
	
	// assign a score to the listing based on keywords present in the description and other factors
	public static double spamScore(String listing, double price){
		return 0;
	}
	
	
	// checks if the neighborhood is a valid one
	public static boolean isValidNeighborhood(String listing){
		
		// TODO: extract the neighborhood name from the listing
		String neighborhood = null;
		
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
		neighborhood_set.add("Stuyvesant Town");
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
		for(String n : neighborhood_set){
			if(neighborhood.toLowerCase().replaceAll("[\\W]", "").equals(n.toLowerCase().replaceAll("[\\W]", "")) || 
					neighborhood.toLowerCase().replaceAll("[\\W]", "").contains(n.toLowerCase().replaceAll("[\\W]", ""))){
				
				return true;
			}
		}	
		
		return false;
	}
	
	
	
	
	
}
















