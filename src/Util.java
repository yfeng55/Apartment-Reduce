import java.util.HashSet;


public class Util {
	
	public static final double PRICE_MIN = 200;				// minimum price for a listing to not be considered spam
	public static final double SPAM_SCORE_THRESHOLD =9.0;	// threshold score for a listing to be considered spam 
	
	
	public static boolean isSpam(Listing listing){
		
		// (1) check if the neighborhood is valid //
		/*if(!isValidNeighborhood(listing.neighborhood)){
			System.out.println("inavlid neighborhood");
			return true;
		}
		
		// (2) check that the price is above the minimum //
		else*/
			if(listing.price < PRICE_MIN){
			return true;
		}
		
		// (3) assign a score for the title from 0 - 1 and check if it exceeds the spam_score threshold
		else if(spamScore(listing) < SPAM_SCORE_THRESHOLD){
			return true;
		}
		
		return false;
	}
	
	
	// assign a score to the listing based on keywords present in the description and other factors
	// variables: # of pictures, price, price std, keywords in text, presence of location
	public static double spamScore(Listing listing){
		
		double score = 0;
		
		score=Math.max(0,(2.5*Math.log(listing.num_images+1)/1.39794000867)+(4.5*Math.log(listing.zscore+6))+(listing.description.length()/150)+2*listing.has_map);
		
		
		return score;
	}
	
	
	// checks if the neighborhood is a valid one
	public static boolean isValidNeighborhood(String listing_neighborhood){

		String neighborhood = listing_neighborhood;
		
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
			if(neighborhood.toLowerCase().replaceAll("[\\s+]", "").equals(n.toLowerCase().replaceAll("[\\s+]", "")) || 
					neighborhood.toLowerCase().replaceAll("[\\s+]", "").contains(n.toLowerCase().replaceAll("[\\s+]", ""))){
				
				return true;
			}
		}	
		
		return false;
	}
	
	
	
	
	
	
	public static int getPriceFromListing(String listing){
		
		String[] listing_arr = listing.split("\t");
		
		int price = (int) Double.parseDouble(listing_arr[0]);
	
		return price;
	}
	
	
	
}
















