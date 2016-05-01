import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AptReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
	
		
		ArrayList<String> spammy_listings = new ArrayList();		// list to store spammy listings 
		
		ArrayList<Listing> listings = new ArrayList();
		
		ArrayList<Integer> prices = new ArrayList();
		
		
		///// create listings objects from input /////
		for(Text value:values){
			
			String[] temp = value.toString().split("\t");
			
			//craigslist listings
			if(temp.length >= 4){
				int id = Integer.parseInt(temp[0]);
				String neighborhood = temp[1];
				int price = Integer.parseInt(temp[2]);
				int bedrooms = Integer.parseInt(temp[3]);
				int num_images = Integer.parseInt(temp[4]);
				int has_map = Integer.parseInt(temp[5]);
				String description = "";
				for(int i=6; i<temp.length; i++){
					description += temp[i];
				}
				listings.add(new Listing(id, neighborhood, price, bedrooms, num_images, has_map, description));
			}
			//non-craigslist listings
			if(temp.length >= 4){
				int id = Integer.parseInt(temp[0]);
				String neighborhood = temp[1];
				int price = Integer.parseInt(temp[2]);
				int bedrooms = Integer.parseInt(temp[3]);
				
				listings.add(new Listing(id, neighborhood, price, bedrooms));
				
			}
			
		}
		
		
		
		
		int count =0;
		int sum=0;
		int avg;
	
		
		for(Text value:values){
			prices.add(Util.getPriceFromListing(value.toString()));
		}
		
		// count the number of values and get the sum of prices for the neighborhood
		for(int price : prices){
			sum+=price;
			count++;
		}
		avg=sum/count;
		
		
		// calculate the price std. deviation for the neighborhood
		double std_deviation;
		double std_numerator = 0.0;
		
		for(int price:prices){
			std_numerator += Math.pow(((double) price - avg), 2);
			System.out.println("STD NUMERATOR: " + std_numerator);
		}
				
		std_deviation = Math.sqrt(std_numerator / count);

	
		int high_outliers = 0;
		int low_outliers = 0;
		
		// get high/low 
		for(int price:prices){
			if(price <= avg - 2*std_deviation){
				low_outliers++;
			}
			
			if(price >= avg + 2*std_deviation){
				high_outliers++;
			}
		}
		
		
		// format values as output:
		String output_string = "price avg: $" + avg + " | std deviation: " + std_deviation;
		//output_string += " | high price outliers: " + high_outliers;
		//output_string += " | low price outliers: " + low_outliers;
		output_string +=" apartment count: "+count;
		
		
		
		////////// FIND SPAM //////////
		
		
		
		
		
		
		context.write(key, new Text(output_string));
	}
  
  
  
  
  
}





