
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

    public void addMatch(Match match){
        matchArrayList.add(match);
    }

    public void setMatchArrayList(ArrayList<Match> matchArrayList) {
        this.matchArrayList = matchArrayList;
    }

    public ArrayList<Match> getMatchArrayList() {
        return matchArrayList;
    }
}
