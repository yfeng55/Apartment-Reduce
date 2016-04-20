import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AptReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
	
		int count =0;
		  int sum=0;
	
		for(IntWritable value:values)
		{
			count++;
			sum=sum+=value.get();
		}
		sum=sum/count;
		
		context.write(key, new IntWritable(sum));
	
	}
  
  
  
  
  
}





