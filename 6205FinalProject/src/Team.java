import java.text.DecimalFormat;
import java.util.ArrayList;

public class Team {

    private String name;
    private int totalGoals = 0;
    private double point;
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
    private Team rival;
    private int GD;
    private ArrayList<String> rivalAtHome;

    public Team(){
        teamID = count;
        count++;
        rivalAtHome = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setRival(Team rival) {
        this.rival = rival;
    }

    public ArrayList<String> getRivalAtHome() {
        return rivalAtHome;
    }

    public void addRivalAtHome(String name) {
        this.rivalAtHome.add(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point += point;
    }

    public void addPoint(double result){
        point += result;
        DecimalFormat df = new DecimalFormat("0.##");
        String goal = df.format((float)point);
        this.point = Float.valueOf(goal);
    }

    public int getGD() {
        return GD;
    }

    public void setGD(int GD) {
        this.GD += GD;
    }

    public void minusPoint(double result){
        point -= result;
        DecimalFormat df = new DecimalFormat("0.##");
        String goal = df.format((float)point);
        this.point = Float.valueOf(goal);
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
        DecimalFormat df = new DecimalFormat("0.##");
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
        DecimalFormat df = new DecimalFormat("0.##");
        String goal = df.format((float) totalSuccDefense / theNumberofGamesPlayed);
        averageDefense= Float.valueOf(goal);
    }

    public void setAverageFouls() {
        DecimalFormat df = new DecimalFormat("0.##");
        String goal = df.format((float) totalFouls / theNumberofGamesPlayed);
        averageFouls= Float.valueOf(goal);
    }

    @Override
    public String toString() {
        return "Team{" +
                "Team ID=" + teamID +
                ", name='" + name + '\'' +
                ", Goal Difference=" + GD +
                ", Average Goal=" + averageGoals +
                ", Average fouls commited=" + averageFouls +
                ", Average successful defense=" + averageDefense +
                ", played Game=" + theNumberofGamesPlayed +
                ", point=" + point +
                '}';
    }

}
