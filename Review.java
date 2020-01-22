import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * Class that contains helper methods for the Review Lab
 **/
public class Review {
  
  private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
  private static ArrayList<String> posAdjectives = new ArrayList<String>();
  private static ArrayList<String> negAdjectives = new ArrayList<String>();
 
  
  private static final String SPACE = " ";
  
  static{
    try {
      Scanner input = new Scanner(new File("cleanSentiment.csv"));
      while(input.hasNextLine()){
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0],Double.parseDouble(temp[1]));
        //System.out.println("added "+ temp[0]+", "+temp[1]);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing cleanSentiment.csv");
     
  //read in the positive adjectives in postiveAdjectives.txt
     try {
      Scanner input = new Scanner(new File("positiveAdjectives.txt"));
      while(input.hasNextLine()){
        String temp = input.nextLine().trim();
        System.out.println(temp);
        posAdjectives.add(temp);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }   
 
  //read in the negative adjectives in negativeAdjectives.txt
     try {
      Scanner input = new Scanner(new File("negativeAdjectives.txt"));
      while(input.hasNextLine()){
        negAdjectives.add(input.nextLine().trim());
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing negativeAdjectives.txt");
    }   
  }
  
  /** 
   * returns a string containing all of the text in fileName (including punctuation), 
   * with words separated by a single space 
   */
  public static String textToString( String fileName )
  {  
    String temp = "";
    try {
      Scanner input = new Scanner(new File(fileName));
      
      //add 'words' in the file to the string, separated by a single space
      while(input.hasNext()){
        temp = temp + input.next() + " ";
      }
      input.close();
      
    }
    catch(Exception e){
      System.out.println("Unable to locate " + fileName);
    }
    //make sure to remove any additional space that may have been added at the end of the string.
    return temp.trim();
  }
  
  /**
   * @returns the sentiment value of word as a number between -1 (very negative) to 1 (very positive sentiment) 
   */
  public static double sentimentVal( String word )
  {
    try
    {
      return sentiment.get(word.toLowerCase());
    }
    catch(Exception e)
    {
      return 0;
    }
  }
  
  /**
   * Returns the ending punctuation of a string, or the empty string if there is none 
   */
  public static String getPunctuation( String word )
  { 
    String punc = "";
    for(int i=word.length()-1; i >= 0; i--){
      if(!Character.isLetterOrDigit(word.charAt(i))){
        punc = punc + word.charAt(i);
      } else {
        return punc;
      }
    }
    return punc;
  }
  /**
   * Returns the word after removing any beginning or ending punctuation
   */
  public static String removePunctuation( String word )
  {
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(0)))
    {
      word = word.substring(1);
    }
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(word.length()-1)))
    {
      word = word.substring(0, word.length()-1);
    }
    
    return word;
  }
 

  
  /** 
   * Randomly picks a positive adjective from the positiveAdjectives.txt file and returns it.
   */
  public static String randomPositiveAdj()
  {
    int index = (int)(Math.random() * posAdjectives.size());
    return posAdjectives.get(index);
  }
  
  /** 
   * Randomly picks a negative adjective from the negativeAdjectives.txt file and returns it.
   */
  public static String randomNegativeAdj()
  {
    int index = (int)(Math.random() * negAdjectives.size());
    return negAdjectives.get(index);
    
  }
  
  /** 
   * Randomly picks a positive or negative adjective and returns it.
   */
  public static String randomAdjective()
  {
    boolean positive = Math.random() < .5;
    if(positive){
      return randomPositiveAdj();
    } else {
      return randomNegativeAdj();
    }
  }
   public static double totalSentiment(String fileName)
    {
      String theReview = textToString(fileName);
      String newString = "";
     //I added double wordValue as a placeholder, same as int totalValue
      double wordValue = 0.0;
      int totalValue = 0;
      for(int i = 0;i<theReview.length();i++)
      {
        //if the character equals a space then everything before it is a word and we can find the total sentiment value of the word. else, we continue through it as if it were a word. 
         if(word.substring(i,i+1).equals(" "))
         {
            newString.removePunctuation(word);
            wordValue = newString.sentimentVal(word);
            totalValue += wordValue;
            newString = "";
         }
         else
         {
            word.substring(i,i+1)+= newString;
         }
       }
       return totalValue;
       
            
    }
    //this is my starRating() method
    public static int starRating(String fileName)
    {
      double totalValue = totalSentimentVal(fileName);
      if(totalValue >= 1)
      {
         return 5;
      }
      else if(totalValue >= 0)
      {
         return 3;
      }
      else
      {
         return 1;
      }
     }
     //Answers to Questions
     //4. The totalSentiment method works by processing each character of a string searching for a space. When it finds a space it takes all the previous letters, puts it into word and then takes out all pronuncation, and calls the getSentimentVal method and finds the sentiment value of the word. 
     //the sentiment value is then added to the total value, and then the string is reiterated as blank. Then the process starts all over again, until another space occurs. 
     //5a. Yes the ratings make sense. The lowest sentiment value got a 1 star and the higher of the sentiment values got a 5. 
     //5b. I could adjust the totalSentiment value by making even more specific ratings, I only had a 5 star, 3 star and 1 star, while i could have made even more specific star values.
     //6a. This student made an error with his if statements. By having it be < than 15 first, everything after it will also be true so you won't get an accurate rating. If there is a number such as 4 then it is technichnically less than 15, but that wasn't the right answer. It ws supposed to have printed the code that was under <5. 
     //6b. He could have fixed it by doing >= instead of <. Then if the number was four, it would have only printed out the rating <= 1. He would also have to change the rating for each if statement.        
     
    
            
            
         
    
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  }
  

