import java.text.DecimalFormat;
import java.util.ArrayList;

public class Team {
    /**
     * @param name The name of a team.
     * @param totalGoals The total number of goals the team obtain in past matches.
     * @param point The point calculated by the regular EPL game principle.
     * @param TheNumberofGamesPlayed The total number of game the team attends in this season.
     * @param averageGoals Average goal the team gain.
     * @param totalFouls The total number of Fouls the team was commited in past matches.
     * @param totalSuccDefense The total times of successful defense.
     * @param ELOPoint The point calculated by the ELO rating principle.
     * @param GD The total Goal Difference in the past matches.
     * @param totalShot the total number of team's shot in the past matches.
     */

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
    private float ELOPoint;
    private int GD;
    private double totalShot = 0;
    private double averageShot = 0;

    private ArrayList<String> rivalAtHome;

    public Team(){
        teamID = count;
        count++;
        rivalAtHome = new ArrayList<>();
    }

    public void addTotalShot(double shot){
        this.totalShot += shot;
    }

    public Double getAverageShot(){
        this.averageShot = totalShot/getTheNumberofGamesPlayed();
        return averageShot;
    }

    public String getName() {
        return name;
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

    public void setPoint(float point) {
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

    public float getELOPoint() {
        return ELOPoint;
    }

    public void updateELO(Float amount){
        ELOPoint += amount;
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

    public int getTeamID() {
        return teamID;
    }

    @Override
    public String toString() {
        return  "Team ID=" + teamID +
                ", Name=" + name  +
                ", Goal Difference=" + GD +
                ", Average Shot=" + getAverageShot() +
                ", Average Goal=" + averageGoals +
                ", Average Fouls commited=" + averageFouls +
                ", Average Successful Defense=" + averageDefense +
                ", Played Game=" + theNumberofGamesPlayed +
                ", Point=" + point +
                ", ELO Point=" + ELOPoint;
    }

}
