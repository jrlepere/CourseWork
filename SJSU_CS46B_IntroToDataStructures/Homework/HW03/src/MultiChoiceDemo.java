import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MultiChoiceDemo
{
   public static void main(String[] args) throws FileNotFoundException
   {
      System.out.print("Quiz file: ");
      Scanner in = new Scanner(System.in);
      String filename = in.nextLine();
      ArrayList<Question> quiz = readQuestions(filename);
      for (Question q : quiz)
         presentQuestion(q);
   }

   public static void presentQuestion(Question q) 
   {
      q.display();
      System.out.print("Your answer: ");
      Scanner in = new Scanner(System.in);
      String response = in.nextLine();
      System.out.println(q.checkAnswer(response));
   }

   public static ArrayList<Question> readQuestions(String filename) throws FileNotFoundException
   {
      File questionFile = new File(filename); // Creates a file 
      Scanner in = new Scanner(questionFile); // Creates a scanner to read through the file
      ArrayList<Question> questions = new ArrayList<>();
      while (in.hasNextLine()) {
    	  String text = in.nextLine();
    	  ArrayList<String> results = new ArrayList<>();
    	  results.add(text);
    	  boolean qFound = false;
    	  while (in.hasNextLine() && !qFound) {
    		  String result = in.nextLine();
    		  if (!(result.trim().isEmpty())) {
    			  results.add(result);
    		  } else {
    			  qFound = true;
    		  }
    	  }
    	  if (results.size() == 2) {
        	  Question q = new Question();
        	  q.setText(results.get(0));
              q.setAnswer(results.get(1));
              questions.add(q);
          } else {
        	  int answerCounter = 0;
        	  for (int i = 1; i < results.size(); i ++) {
        		  if (results.get(i).startsWith("*")) {
        			  answerCounter ++;
        		  }
        	  }
        	  if (answerCounter == 1) {
        		  ChoiceQuestion cq = new ChoiceQuestion();
        		  cq.setText(results.get(0));
        		  for (int i = 1; i < results.size(); i ++) {
        			  if (results.get(i).startsWith("*")) {
       					  cq.addChoice(results.get(i).substring(1), true);
       				  } else {
       					  cq.addChoice(results.get(i), false);
       				  }    			  }
        		  questions.add(cq);
        	  } else {
        		  MultiChoiceQuestion mcq = new MultiChoiceQuestion();
       			  mcq.setText(results.get(0));
        		  for (int i = 1; i < results.size(); i ++) {
        			  if (results.get(i).startsWith("*")) {
       					  mcq.addChoice(results.get(i).substring(1), true);
        			  } else {
        				  mcq.addChoice(results.get(i), false);
       				  }
        		  }
        		  questions.add(mcq);
        	  }
       	  }
      }  
      in.close();
      return questions;
   }
}