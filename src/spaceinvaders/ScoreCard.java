package spaceinvaders;

public class ScoreCard {
    private int score = 0;

    public void increaseScore(int points){
        score += points;
    }

    public void decreaseScore(int points){
        score += -points;

        if(score < 0){
            GameExceptions.showGameOver("Better Luck Next Time!");
        }
    }

    public int getScore(){
        return score;
    }

    public void resetScore(){
        score = 0;
    }
}
