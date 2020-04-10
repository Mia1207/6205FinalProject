

public class Match {
    /**
     * @param teamA the name of A whitch will match with B.
     * @param teamB the name of B which  will match with A.
     * @param date the match date.
     * @param location the match location to determine who is home team.
     * @param probabiltiy the probability A will beat B.
     */
    private String teamA;
    private String teamB;
    private String date;
    private String location;
    private float probability;

    public Match(){

    }

    public void setTeamA(String teamA){
        this.teamA = teamA;
    }

    public void setTeamB(String teamB){
        this.teamB = teamB;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setLocation(String location){
        this.location = location;
    }
}
