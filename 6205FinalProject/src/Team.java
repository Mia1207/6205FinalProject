import java.text.DecimalFormat;

public class Team {

    private String name;
    private int totalGoals = 0;
    private float point;
    private int playedGame = 0;
    private  int theNumberofGamesPlayed;
    private float averageGoals;
    private int totalFouls = 0;
    private int totalSuccDefense = 0;
    private int temp = 0;
    private int temp1 = 0;
    private static int count =0;
    private int teamID;
    private float averageFouls;
    private float averageDefense;

    public Team(){
        teamID = count;
        count++;
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

    public int getTotalSuccDefense() {
        return totalSuccDefense;
    }

    public void TotalSuccDefense(int SuccDefense) {
        temp1 += SuccDefense;
        totalSuccDefense = temp1;
    }

    public float getAverageGoals() {
        return averageGoals;
    }

    public int getTotalFouls() {
        return totalFouls;
    }

    public void setTotalFouls(int Fouls) {
        temp += Fouls;
        totalFouls = temp;
    }

    public void setAverageGoals() {
        DecimalFormat df = new DecimalFormat("0.###");
        String goal = df.format((float) totalGoals / theNumberofGamesPlayed);
        this.averageGoals = Float.valueOf(goal);
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

    public float getAverageDefense() {
        return averageDefense;
    }

    public float getAverageFouls() {
        return averageFouls;
    }

    public void setAverageDefense() {
        DecimalFormat df = new DecimalFormat("0.###");
        String goal = df.format((float) totalSuccDefense / theNumberofGamesPlayed);
        averageDefense= Float.valueOf(goal);
    }

    public void setAverageFouls() {
        DecimalFormat df = new DecimalFormat("0.###");
        String goal = df.format((float) totalFouls / theNumberofGamesPlayed);
        averageFouls= Float.valueOf(goal);
    }

    @Override
    public String toString() {
        return "Team{" +
                "Team ID=" + teamID +
                ", name='" + name + '\'' +
                ", totalGoals=" + totalGoals +
                ", Average Goal=" + averageGoals +
                ", Average fouls commited=" + averageFouls +
                ", Average successful defense=" + averageDefense +
                ", played Game=" + theNumberofGamesPlayed +
                ", point=" + point +
                '}';
    }
}
