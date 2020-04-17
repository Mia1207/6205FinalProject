
import java.util.ArrayList;


public class MatchDirectory {
    /**
     * This class is a fundamental class in order to make sure our project will run successively.
     * @param matchArrayList Store the whole matches palyed.
     */

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
