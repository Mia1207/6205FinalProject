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

    public float[][] predictForGD(ArrayList<Match> futureMatch, TeamDirectory teamDirectory){
        float[][] u = new float[92][2];
        for(Match match:futureMatch){
            for(Team team:teamDirectory.getTeamArrayList()){
                if(team.getName().equals(match.getHomeTeam())){
                    for(Team team1:teamDirectory.getTeamArrayList()){
                        if (team1.getName().equals(match.getAwayTeam())){
                            u[futureMatch.indexOf(match)][0] = (float) (team.getAverageShot() - team1.getAverageDefense());
                            u[futureMatch.indexOf(match)][1] = (float) (team1.getAverageShot() - team.getAverageDefense());
                        }
                    }
                }
            }
        }
        return u;
    }

    public ArrayList<Team> addPointOfFutureMatch(ArrayList<Team> RankingAlready, ArrayList<Match> futureMatch){
        ArrayList<Team> RankingFinally = new ArrayList<>();
        for (Match match:futureMatch){
            for (Team team: RankingAlready){
                if(match.getResult().equals("H")){
                    if (match.getHomeTeam().equals(team.getName())){
                        team.addPoint(3);
                    }
                    if (match.getAwayTeam().equals(team.getName())){
                        team.addPoint(0);
                    }
                }else if(match.getResult().equals("A")){
                    if (match.getHomeTeam().equals(team.getName())){
                        team.addPoint(0);
                    }
                    if (match.getAwayTeam().equals(team.getName())){
                        team.addPoint(3);
                    }
                }else {
                        if (match.getHomeTeam().equals(team.getName())){
                            team.addPoint(3);
                        }
                        if (match.getAwayTeam().equals(team.getName())){
                            team.addPoint(0);
                        }
                }
            }
        }
        for (Team team: RankingAlready){
            RankingFinally.add(team);
        }
        return RankingFinally;
    }

}
