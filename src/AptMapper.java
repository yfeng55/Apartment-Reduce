import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import java.util.*;
public class AptMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	
	
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	
		
		String line = value.toString();
		
		Scanner sc=new Scanner(line);
		while(sc.hasNext())
		{
			String nextLine=sc.nextLine();
			
			nextLine.toLowerCase();
			String line_array[]=nextLine.split("\t");
			
			String region;
			String price;
			String bedrooms;
			String reducer_key;
			String description_pics_info = "";
			
			try
			{
				region=line_array[1];
				price=line_array[2];
				bedrooms=line_array[3];
				
				for(int i=4; i<line_array.length; i++){
					description_pics_info += line_array[i] + "\t";
				}
				//System.out.println(description_pics_info);
			}
			catch(Exception e)
			{
				region="null";
				price="-1";
				bedrooms="-1";
			}
			
			
			if(Integer.parseInt(bedrooms)==0)
			{
				reducer_key=region+" "+ "studio APT";
			}
			else
			{
				reducer_key=region+" "+bedrooms+" bedroom APT";
			}
			String Value=price+"\t"+description_pics_info;
	
			
			context.write(new Text(reducer_key),new Text(Value));
		}
		
		sc.close();
	
		
	}
  
  
	
	
	
	
	
	
	

	
}