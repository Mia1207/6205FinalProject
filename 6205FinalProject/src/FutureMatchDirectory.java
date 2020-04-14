import java.util.ArrayList;

public class FutureMatchDirectory {


    public ArrayList<Match> getFutureMatch(MatchDirectory matchDirectory, TeamDirectory teamDirectory){
        ArrayList<Match> futureMatch = new ArrayList<>();
        for (Team team: teamDirectory.getTeamArrayList()){
            for(Match match:matchDirectory.getMatchArrayList()){
                if (team.getName().equals(match.getHomeTeam())){
                    for (Team team1:teamDirectory.getTeamArrayList()){
                        if(!team.getRivalAtHome().contains(team1.getName()) && team1.getName() != team.getName()){
                            Match match1 = new Match();
                            match1.setHomeTeam(team.getName());
                            team.addRivalAtHome(team1.getName());
                            match1.setAwayTeam(team1.getName());
                            futureMatch.add(match1);
                        }
                    }
                }
            }
        }
        return futureMatch;
    }

}
