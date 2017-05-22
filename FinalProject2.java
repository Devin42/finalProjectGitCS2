package finalProjectGit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.awt.Color;
import org.dalton.DaltonDraw;

class TweetCats{//short for tweet categories

	int num;
	String timeSent;
	long id;
	long replyToUserID;
	boolean isRetweet;
	int retweetCount;
	String source;
	String text;


}



public class FinalProject2{

	static TweetCats[] fillArray() { 
		//declarations:
		try {
			Scanner scan = new Scanner(new BufferedReader(new FileReader("/Users/student/git/DataAnalyzerProject/trump_tweets.csv")));

			ArrayList<TweetCats> l = new ArrayList<TweetCats>();

			//main loop:
			while(scan.hasNext()) {

				String[] data = scan.nextLine().split(",");

				/*for(int i = 0; i < data.length; i++){
					System.out.println(data[i]);
				}*/

				if(data[4].equals("")){
					data[4] = "0";
				}

				TweetCats t = new TweetCats();
				t.num = Integer.parseInt(data[0]);
				t.timeSent = data[1];
				t.id = Long.parseLong(data[2]);
				t.replyToUserID = Long.parseLong(data[3]);
				t.retweetCount = (int) Double.parseDouble(data[4]);
				t.isRetweet = Boolean.parseBoolean(data[5]);
				t.source = data[6];
				t.text = data[7];

				l.add(t);
			}

			scan.close();
			return l.toArray(new TweetCats[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new  TweetCats[0];
	}

	public static void main(String[] args){
		TweetCats[] j = fillArray();
		Random ran = new Random();
		int i = ran.nextInt(5000);
		System.out.println(j[0].num);
		int xCoord = 0;
		int yCoord = 0;
		
		DaltonDraw draw = new DaltonDraw();
		
		//origin of this graph at 50, 550
		draw.drawRect(1, 500, 50, 50, 0, Color.BLACK);
		draw.drawRect(1, 500, 300, 300, 90, Color.BLACK);
		for(int y = 540; y > 100.0; y -= 20){
			draw.drawRect(3, 1, 47, y, 0, Color.BLACK);
			draw.drawString(""+(550-y), 20, y+5, 15, Color.BLACK);
		}
		
		for(int r = 0; r < 30794; r++){
			
		}
		
		//draw.render();
	
		System.out.println("Graphing Please Wait!");
		for(int m = 0; m < 415.0; m++){
			xCoord = m+50;
			yCoord = 550 - (yCoordinate(m, j));
			draw.drawRect(2, 2, xCoord, yCoord, 0, Color.BLACK);
			System.out.println("("+xCoord+", "+yCoord+")");
		}
		
		draw.render();
		
		
		
		/*
		System.out.println(daysSince(i, j));
		System.out.println(calcWeek(30195, j));
		System.out.println(yCoordinate(171.0, j));
		*/
	}

	public static double daysSince(int i, TweetCats[] j){
		double secondsSince = 0.00;
		double daysToMonth = 0;
		double daysToYear = 0;

		String[] bigTsmallT = j[i].timeSent.split(" ");
		String[] bigTime = bigTsmallT[0].split("-");
		String[] smallTime = bigTsmallT[1].split(":");
		ArrayList<Double> time = new ArrayList();

		for(int z = 0; z < bigTime.length; z++){
			time.add(Double.parseDouble(bigTime[z]));
		}//convert times to ints
		for(int z = 0; z < smallTime.length; z++){
			time.add(Double.parseDouble(smallTime[z]));
		}//convert string times to ints

		if(time.get(1) == 1){
			daysToMonth = 0;
		}
		else if(time.get(1) == 2){
			daysToMonth = 31;
		}
		else if(time.get(1) == 3){
			daysToMonth = 59;
		}
		else if(time.get(1) == 4){
			daysToMonth = 90;
		}
		else if(time.get(1) == 5){
			daysToMonth = 120;
		}
		else if(time.get(1) == 6){
			daysToMonth = 151;
		}
		else if(time.get(1) == 7){
			daysToMonth = 181;
		}
		else if(time.get(1) == 8){
			daysToMonth = 212;
		}
		else if(time.get(1) == 9){
			daysToMonth = 243;
		}
		else if(time.get(1) == 10){
			daysToMonth = 273;
		}
		else if(time.get(1) == 11){
			daysToMonth = 304;
		}
		else if(time.get(1) == 12){
			daysToMonth = 334;
		}//calculate months
		
		secondsSince = ((time.get(0)*365) + (daysToMonth) + (time.get(2)) + (time.get(3)/24) + (time.get(4)/1440) + (time.get(5)/86400)) - 733409.75;
		
		//System.out.println("i = "+i);

		return secondsSince;
	}
	public static double calcWeek(int i, TweetCats[] j){
		double week = 0;
		double day;
		
		day = daysSince(i, j);
		
		day -= (day%7);
		
		week = day/7;
		
		return week;
	}
	public static int yCoordinate(double week, TweetCats[] j){
		int yCoord = 0;
				
		for(int i = 0; i < j.length; i++){
			if(calcWeek(i, j) == week){
				yCoord++;
			}
		}
		
		return yCoord;
	}
}
