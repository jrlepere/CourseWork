import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AikenReader implements AutoCloseable
{
	private File file;
	private Scanner in;
	/**
	 * Creates a AikenReader 
	 * @param fileName
	 */
	public AikenReader(String fileName)
	{
		file = new File(fileName);
	}
	
	/**
	 * Reads the questions in the file
	 * return an ArrayList of Questions 
	 * @throws FileNotFoundException 
	 */
	public ArrayList<ChoiceQuestion> readQuestions() throws FileNotFoundException
	{
		ArrayList<ChoiceQuestion> questions = new ArrayList<>();
		int lineCount = 1;
		in = new Scanner(file);
		while(in.hasNextLine()) {
			ChoiceQuestion cq = new ChoiceQuestion();
			String text = in.nextLine();
			if (text.trim().isEmpty()) {
				throw new SyntaxException(lineCount);
			}
			lineCount++;
			cq.setText(text);
			ArrayList<String> results = new ArrayList<>();
			boolean found = false;
			String previous = "A";
			int resultCount = 1;
			boolean answerLineFound = false;
			while (in.hasNextLine() && !found)
			{
				String choice = in.nextLine();
				if (choice.startsWith("ANSWER: ")) {
					if (choice.startsWith("Answer:")) {
						throw new SyntaxException(lineCount);
					}
					answerLineFound = true;
				} else {
					if (choice.trim().isEmpty()) {
						throw new SyntaxException(lineCount);
					}
					String firstChar = choice.substring(0,1);
					if (choice.substring(1, 2).equals(" ")) {
						throw new SyntaxException(lineCount);
					} else if (!choice.substring(2, 3).equals(" ")) {
						throw new SyntaxException(lineCount);
					}
					if (resultCount == 1) {
						if (!firstChar.equals(previous)) {
							throw new SyntaxException(lineCount);
						}
					} else {
						if (firstChar.compareTo(previous) <= 0) {
							throw new SyntaxException(lineCount -1);
						} else {
							previous = firstChar;
						}
					}
				}
				if (answerLineFound) {
					if (in.hasNextLine()) {
						lineCount++;
						String next = in.nextLine();
						if (!next.trim().isEmpty()) {
							throw new SyntaxException(lineCount);
						} else {
							found = true;
						}
					} else {
						found = true;
					}
				}
				results.add(choice);
				lineCount++;
				resultCount++;
			}
			String answer = results.get(results.size()-1);
			String answerLetter = answer.substring(answer.length() - 1);
			for (int i = 0; i < results.size()-1; i ++) {
				String result = results.get(i);
				if (result.substring(0,1).equals(answerLetter)) {
					cq.addChoice(result.substring(2), true);
				} else {
					cq.addChoice(result.substring(2), false);
				}
			}
			questions.add(cq);
		}
		in.close();
		return questions;
	}

	
	public void close() throws SyntaxException {
		in.close();
	}
}
