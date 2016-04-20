import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import java.util.*;
public class AptMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	
	
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	
		
		String line = value.toString();
		
		Scanner sc=new Scanner(line);
		while(sc.hasNext())
		{
			String nextLine=sc.nextLine();
			
			nextLine.toLowerCase();
			String region;
			String sprice;
			int price=0;
			try
			{
				
			region=nextLine.substring(nextLine.indexOf("(")+1, nextLine.indexOf(")"));
			//System.out.println(region);
			sprice=nextLine.substring(nextLine.indexOf("$")+1,nextLine.length()-1);
			System.out.println(sprice);
			Scanner temp=new Scanner(sprice);
			price=temp.nextInt();
			System.out.println(sprice);
		
			}
			catch(Exception e)
			{
				region="null";
				sprice="-1";
			}
			
			context.write(new Text(region),new IntWritable(price));
		}
		
		
	
		
	}
  
  
	
	
	
	
	
	
	

	
}