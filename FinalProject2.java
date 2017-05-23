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
		int xCoord = 0;
		int yCoord = 0;
		
		Scanner scan = new Scanner(System.in);

		DaltonDraw draw = new DaltonDraw();
		DaltonDraw occurence = new DaltonDraw();
		
		
		draw.drawString("Please Wait Making", 120, 250, 40, Color.BLACK);
		draw.drawString("Precoursory Calculations", 90, 300, 40, Color.BLACK);
		draw.render();
		draw.clear();
		int tweetWeek = tweetWeek(j);
		System.out.println("As you can see "+tweetWeek+ " had by far the most tweets with "+ yCoordinate(tweetWeek, j)+" tweets!");
		
		
		draw.drawString("Graphing Please Wait!",70, 300, 50, Color.BLACK);
		draw.render();//tells user to wait
		draw.clear();//preps message to be cleared when the graph is ready.


		//origin of this graph at 50, 550
		draw.drawRect(1, 500, 100, 50, 0, Color.BLACK);
		draw.drawRect(1, 500, 350, 300, 90, Color.BLACK);
		for(int y = 540; y > 100.0; y -= 5){
			if((540-y)%20 != 0){
				draw.drawRect(3, 1, 97, y, 0, Color.BLACK);
			}
			else if((540-y)%20 == 0){
				draw.drawRect(10, 1, 90, y, 0, Color.BLACK);
				draw.drawString(""+(550-y), 60, y+5, 15, Color.BLACK);
			}
		}
		for(int x = 105; x < 550; x += 5){
			if((x-105)%20 != 0){
				draw.drawRect(1, 3, x, 550, 0, Color.BLACK);
			}
			else if((x-105)%20 == 0){
				draw.drawRect(1, 10, x, 550, 0, Color.BLACK);
				draw.drawString(""+(x-105), x-7, 570, 10, Color.BLACK);
			}
		}


		//draw.render();

		System.out.println("Graphing Please Wait!");
		for(int m = 0; m < 415.0; m++){
			xCoord = m+100;
			yCoord = 550 - (yCoordinate(m, j));
			draw.drawRect(2, 2, xCoord, yCoord, 0, Color.BLACK);
			//System.out.println("("+xCoord+", "+yCoord+")");
		}
		
		// Graph word occurences

				// User enters word to find
				System.out.println("What word would you like to search for?");
				String word = scan.nextLine();
				// Records and graphs occurences of the chosen word
				for (int m = 0; m < 415.0; m++) {
					xCoord = m + 50;
					yCoord = 550 - (yCoordinateWord(m, j, word));
					occurence.drawRect(2, 2, xCoord, yCoord, 0, Color.BLACK);
				}
				
				occurence.render();
		
		draw.drawString("Week Since First Tweet", 250, 600, 15, Color.BLACK);
		draw.drawString("Number", 5, 300, 15, Color.BLACK);
		draw.drawString("of", 20, 320, 15, Color.BLACK);
		draw.drawString("Tweets", 7, 340, 15, Color.BLACK);
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
	public static int tweetWeek(TweetCats[] j){
		int tweetWeek = 0;
		
		//ArrayList<Integer> mostWeek = new ArrayList();
		
		//System.out.println("Starting the funtion! YEA");
		//415.0 is the total number of weeks since trumps first tweet
		for(int i = 0; i <= 415.0; i++){
				if(yCoordinate(i, j) > yCoordinate(tweetWeek, j)){
					tweetWeek = i;
					//System.out.println("In the loop!");
			}//checks if the array list contains anything larger than the value of the week under question and if it does it replaces that as the "reigning" tweetWeek
			//System.out.println("i ="+ i);
		}
		
		//System.out.println("Function Complete!");
		return tweetWeek;
	}
	public static int yCoordinateWord(double week, TweetCats[] j, String word) {
		int yCoord = 0;

		for (int i = 0; i < j.length; i++) {
			if (calcWeek(i, j) == week && j[i].text.contains(word)) {
				yCoord++;
			}
		}
		return yCoord;
	}
}

