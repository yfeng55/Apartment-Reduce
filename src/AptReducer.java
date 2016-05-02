import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AptReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {


		ArrayList<Listing> spammy_listings = new ArrayList();		// list to store spammy listings 

		ArrayList<Listing> legit_listings = new ArrayList();

		ArrayList<Listing> listings = new ArrayList();

		ArrayList<Integer> prices = new ArrayList();

		int count =0;
		///// create listings objects from input /////
		for(Text value:values){
			//System.out.println(value.toString());
			String[] temp = value.toString().split("\t");
			//System.out.println(Arrays.toString(temp));
			String key_arr[]=key.toString().split(" ");
			String neighborhood=key_arr[0];
			//craigslist listings

			if(temp.length >= 2){

				int price = (int)Double.parseDouble(temp[0]);

				int num_images = Integer.parseInt(temp[1]);
				int has_map = Integer.parseInt(temp[2]);
				String description = "";
				for(int i=6; i<temp.length; i++){
					description += temp[i];
				}
				listings.add(new Listing( neighborhood, price, num_images, has_map, description));
			}
			//non-craigslist listings
			else{

				int price = (int)Double.parseDouble(temp[0]);

				listings.add(new Listing( neighborhood, price));

			}

		}





		int sum=0;
		double avg;




		// count the number of values and get the sum of prices for the neighborhood
		for(Listing l:listings){
			//System.out.println("prices "+l.price);
			sum+=l.price;
			count++;
		}
		//System.out.println("sum " +sum+" count "+count);
		//System.out.println("count "+count);
		avg=(double)sum/count;
		//System.out.println("average "+avg);

		// calculate the price std. deviation for the neighborhood
		double std_deviation;
		double std_numerator = 0.0;

		for(int price:prices){
			std_numerator += Math.pow(((double) price - avg), 2);
			//System.out.println("price,  avg " + price+ " "+avg);
			//System.out.println("STD NUMERATOR: " + std_numerator);
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

		//output_string += " | high price outliers: " + high_outliers;
		//output_string += " | low price outliers: " + low_outliers;




		////////// FIND SPAM //////////
		for(Listing l:listings)
		{
			if(std_deviation==0)
			{
				l.zscore=0;
			}
			else
			{
				l.zscore=(l.price-avg)/std_deviation;
			}
		}
		
		
		for(Listing l:listings)
		{
			if(Util.isSpam(l)==true)
			{
				spammy_listings.add(l);
				System.out.println(l.neighborhood+" "+l.price+" "+"is spam, images:"+l.num_images+ " description length "+l.description.length()+" hasmap "+l.has_map+ " zscore: "+Math.log(l.zscore+6));
			}
			else
			{
				legit_listings.add(l);
			}
		}
		//System.out.println("countsss"+ listings.size()+" "+spammy_listings.size()+" "+legit_listings.size());
		int legit_sum=0;
		double legit_avg=0;
		if(legit_listings.size()>0)
		{
			for(Listing l:legit_listings)
			{
				legit_sum+=l.price;			
			}

			legit_avg=legit_sum/legit_listings.size();

			int legit_numerator=0;

			for(Listing l:legit_listings)
			{
				legit_numerator+=Math.pow(((double) l.price - legit_avg), 2);
			}
			std_deviation = Math.sqrt(legit_numerator / legit_listings.size());
		}
		String output_string = "price avg: $" + legit_avg + " | std deviation: " + std_deviation;
		context.write(key, new Text(output_string));
	}





}





