import java.util.ArrayList;

public class Question {
	public String question;
	public int id;
	public ArrayList<String> answers;

	public Question(int id, String question) {
		this.question = question;
		this.id = id;
		this.answers = new ArrayList<String>();
	}
}