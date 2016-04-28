import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AptReducer extends Reducer<Text, IntWritable, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
	
		
		ArrayList<Integer> prices = new ArrayList();
		
		int count =0;
		int sum=0;
		int avg;
	
		
		for(IntWritable value:values){
			prices.add(value.get());
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
		output_string += " | high price outliers: " + high_outliers;
		output_string += " | low price outliers: " + low_outliers;
		
		context.write(key, new Text(output_string));
	}
  
  
  
  
  
}





