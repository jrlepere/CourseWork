
/**
 * This program creates a choice question with multiple answers
 * @author JLepere2
 */
public class MultiChoiceQuestion extends ChoiceQuestion
{
	private String answers;
	private int count;
	
	/**
	 * Creates a choice question with no choices
	 */
	public MultiChoiceQuestion() 
	{
		answers = "";
		count = 0;
	}
	
	/**
	 * Adds a choice to the question
	 * @param choice the choice
	 * @param correct whether or not the choice is correct
	 */
	public void addChoice(String choice, boolean correct) 
	{
		 super.addChoice(choice, correct);
		 count++;
		 if (correct) {
			 if (count == 1) {
				 answers += count;
			 } else {
				 answers += " " + count;
			 }
		 }
		 super.setAnswer(answers);
	}
	
	/**
	 * Displays the question
	 */
	public void display() 
	{
		super.display();
		System.out.println("Enter all correct choices separated by spaces.");
	}
}
