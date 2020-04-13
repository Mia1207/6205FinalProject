
import java.util.ArrayList;

//这是一行注释

public class MatchDirectory {

    ArrayList<Match> matchArrayList;

    public MatchDirectory(){
        this.matchArrayList = new ArrayList<>();
    }

    public void deleteMatch(Match match){
        matchArrayList.remove(match);
    }

    public Match createMatch(int ID, String teamA, String teamB, String date, String location){
        Match match = new Match();
        match.setTeamA(teamA);
        match.setTeamB(teamB);
        match.setDate(date);
        match.setLocation(location);
        matchArrayList.add(match);
        return match;
    }

    public ArrayList<Match> getMatchArrayList() {
        return matchArrayList;
    }
}
