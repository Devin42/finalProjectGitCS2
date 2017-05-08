package finalProjectGit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

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

				String[] data = scan.nextLine().split(", ");

				TweetCats t = new TweetCats();
				t.num = Integer.parseInt(data[0]);
				t.timeSent = data[1];
				t.id = Long.parseLong(data[2]);
				t.replyToUserID = Long.parseLong(data[3]);
				t.isRetweet = Boolean.parseBoolean(data[4]);
				t.retweetCount = Integer.parseInt(data[5]);
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
		
	}
}
