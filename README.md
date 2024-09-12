# redteam
MKOSZ : Hungarian basketball website scrapper + Instat Excel file

coded by : https://www.instagram.com/coach.mert


I am helping a basketball team to video analyse, but i want to take it to another level and decided to put some more information for players to check before games.

All the data is scraped thru hunbasket.hu site and i create the sql (create and insert) statement and then push them into a sqllite file . Then move that file to a web server and do more python code inorder to show them in a report.


First part is the java execution part : 

Only you need to give the team name (Instat name) and the URL in the hunbasket.hu website you want to scrap

public static final String teamURL = "https://hunbasket.hu/csapat/x2324/hun2b/10575/david-kornel-ka";
	public static final String TEAMNAME="David Kornel";
		
	public static final String SQLFILE2 = "/Users/kam/Desktop/sql.txt";
	public static final String SQLFILE = "/Users/kam/Desktop/sql_end.txt";



from the URL find the players , get the player average and total stats and relative stats , and then finds the games the players played and get the stats for those games 
We can find the starting five , missing players for that game etc....

and then we get an export of an Excel file from Instat including many team wise stats , and we can use these stats depending on the coaches ideas, what to show to the team. 

Second part is the python html part 
we simply move the sqllite database and create the html files with a python , at the moment , we can put local information , but i think we need to put them into db as well (i will work on that part later ) 

so the player will see something like 

Last game information , who to be careful on 3p , who to faul if needed.
some key KPIs, the stats of the player they will defend and more information (will be provided by a video coordinator or it can be done via all team wise) 
and the video analysis video 

so all in one place.


