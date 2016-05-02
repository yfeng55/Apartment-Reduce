
public class Listing {

	public  int id;
	public  String neighborhood;
	public  int num_bedrooms;
	public  int price;
	public  String description;
	public  int num_images;
	public  int has_map;
	public  double zscore;
	
	public  double spam_score;
//	public static int[] features;
	
	
	public Listing( String neighborhood, int price){
		this.id = 0;
		this.neighborhood = neighborhood;
		this.price = price;
		this.num_bedrooms = 0;;
		
		
		//default value, must manually set for craigslist
		this.has_map = 1;
		this.num_images = 0;
		this.description = "";
		this.spam_score = 0;
		this.zscore=0;
	}

	
	// for craigslist //
	public Listing( String neighborhood, int price, int num_images, int has_map, String description){
		this.id = 0;;
		this.neighborhood = neighborhood;
		this.price = price;
		this.description = description;
		this.has_map = has_map;
		this.num_images = num_images;
		this.spam_score = 0;
		this.zscore=0;
	}
	
	
}
