-- beeline -u jdbc:hive2://quickstart:10000/default -n cloudera -d org.apache.hive.jbdc.HiveDriver

create external table listings (id int, neighborhood string, price decimal, beds int, imgs int, title string)
row format delimited fields terminated by '\t'
location '/user/cloudera/craigslist_input';

-- PROFILE STATS --

select neighborhood, MIN(price) as min, MAX(price) as max, AVG(price) as avg from listings GROUP BY neighborhood;

select neighborhood, MIN(bed) as min, MAX(bed) as max, AVG(bed) as avg from listings GROUP BY neighborhood;

select neighborhood, MIN(imgs) as min, MAX(imgs) as max, AVG(imgs) as avg from listings GROUP BY neighborhood;

select neighborhood, MIN(LENGTH(title)) as min, MAX(LENGTH(title)) as max, AVG(LENGTH(title)) as avg from listings GROUP BY neighborhood;


