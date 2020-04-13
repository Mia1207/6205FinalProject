

public class Match {
    /**
     * @param teamA the name of A whitch will match with B.
     * @param teamB the name of B which  will match with A.
     * @param date the match date.
     * @param location the match location to determine who is home team.
     * @param probabiltiy the probability A will beat B.
     * @param Attendence the number of people go to watch this match, which means the number of supporter of home team
     * @param HST Home Team Shots on Target
     * @param AST Away Team Shots on Target
     * @param HF Home Team Fouls Committed
     * @param AF Away Team Fouls Committed
     * @param homeScore Full Time Home Team Goals
     * @param awayScore Full Time Away Team Goals
     */
    private  int matchID;
    private String homeTeam;
    private String awayTeam;
    private String date;
    private int homeScore;
    private int awayScore;
    private int HST;
    private int AST;
    private int HF;
    private int AF;
    private static int count = 1;

    public Match(){
        matchID = count;
        count++;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getAF() {
        return AF;
    }

    public void setAF(int AF) {
        this.AF = AF;
    }

    public int getAST() {
        return AST;
    }

    public void setAST(int AST) {
        this.AST = AST;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getHF() {
        return HF;
    }

    public void setHF(int HF) {
        this.HF = HF;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getHST() {
        return HST;
    }

    public void setHST(int HST) {
        this.HST = HST;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getDate() {
        return date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchID=" + matchID +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", date='" + date + '\'' +
                ", homeScore=" + homeScore +
                ", awayScore=" + awayScore +
                ", HST=" + HST +
                ", AST=" + AST +
                ", HF=" + HF +
                ", AF=" + AF +
                '}';
    }
}
