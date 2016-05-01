
public class Listing {

	public static int id;
	public static String neighborhood;
	public static int num_bedrooms;
	public static int price;
	public static String description;
	public static int num_images;
	public static int has_map;
	
	public static double spam_score;
//	public static int[] features;
	
	
	public Listing(int id, String neighborhood, int price, int bedrooms){
		this.id = id;
		this.neighborhood = neighborhood;
		this.price = price;
		this.num_bedrooms = bedrooms;
		
		
		//default value, must manually set for craigslist
		this.has_map = 1;
		this.num_images = 0;
		this.description = "";
		this.spam_score = 0;
	}

	
	// for craigslist //
	public Listing(int id, String neighborhood, int price, int bedrooms, int num_images, int has_map, String description){
		this.id = id;
		this.neighborhood = neighborhood;
		this.price = price;
		this.description = description;
		this.has_map = has_map;
		this.num_images = num_images;
		this.spam_score = 0;
		
	}
	
	
}
