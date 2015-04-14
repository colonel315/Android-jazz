package ninjacoder.additionflashcards;

import java.util.Random;

/**
 * Created by Trey on 4/13/2015.
 */
public class FlashCards {
	private int additiveRight;
	private int additiveLeft;

	public String createAdditionProblem() {
		Random rand = new Random();

		this.additiveRight = rand.nextInt(41);
		this.additiveLeft = rand.nextInt(41);

		return this.additiveRight + " + " + this.additiveLeft;
	}

	public int getSum() {
		return additiveRight + additiveLeft;
	}

	public int getAdditiveRight() {
		return this.additiveRight;
	}

	public int getAdditiveLeft() {
		return this.additiveLeft;
	}

	public void setAdditiveRight(int additiveRight) {
		this.additiveRight = additiveRight;
	}

	public void setAdditiveLeft(int additiveLeft) {
		this.additiveLeft = additiveLeft;
	}
}