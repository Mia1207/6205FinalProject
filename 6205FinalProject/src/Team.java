
public class Team {

    private String name;
    private int totalGoals = 0;
    private float point;
    private int playedGame = 0;
    private  int theNumberofGamesPlayed;

    public Team(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public void updateTimes(){
        playedGame++;
        theNumberofGamesPlayed = playedGame;
    }

    public int getTheNumberofGamesPlayed() {
        return theNumberofGamesPlayed;
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(int goal) {
        totalGoals += goal;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", totalGoals=" + totalGoals +
                ", played Game=" + theNumberofGamesPlayed +
                ", point=" + point +
                '}';
    }
}
