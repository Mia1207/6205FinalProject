

public class Match {
    /**
     * @param teamA the name of A whitch will match with B.
     * @param teamB the name of B which  will match with A.
     * @param date the match date.
     * @param location the match location to determine who is home team.
     * @param probabiltiy the probability A will beat B.
     */
    private  int matchID;
    private String homeTeam;
    private String awayTeam;
    private String date;
    private float probability;
    private static int count = 1;

    public Match(){
        matchID = count;
        count++;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
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


    @Override
    public java.lang.String toString() {
        return "Match{" +
                "matchID=" + matchID +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", date='" + date + '\'' +
                ", probability=" + probability;
    }
}
