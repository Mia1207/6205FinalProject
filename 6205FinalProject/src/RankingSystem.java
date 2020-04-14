import com.csvreader.CsvReader;

import java.nio.charset.Charset;
import java.util.*;

public class RankingSystem {

    public static void main(String[] args) {
        MatchDirectory matchDirectory = new MatchDirectory();
        TeamDirectory teamDirectory = new TeamDirectory();
        sortHelper sortHelper = new sortHelper();
        initializaData(matchDirectory,teamDirectory);
//        for (Match match:matchDirectory.getMatchArrayList()){
//            System.out.println(match);
//        }
        calTeamInfo(matchDirectory,teamDirectory);
        calTeamPoint(matchDirectory, teamDirectory);
        ArrayList<Team> rankingResult = new ArrayList<>();
        rankingResult = sortHelper.calRanking(teamDirectory);
        for (int i = 0; i<teamDirectory.getTeamArrayList().size(); i++){
            System.out.println(rankingResult.get(i));
        }
    }

    public static void initializaData(MatchDirectory matchDirectory, TeamDirectory teamDirectory){
        String filePath = "/Users/huaruilu/Documents/GitHub/6205FinalProject/6205FinalProject/main/resources/2019-2020.csv";
        DataReader dataReader = new DataReader();
        RankingSystem rankingSystem = new RankingSystem();
        matchDirectory.matchArrayList = dataReader.readMatchFile(filePath);
        rankingSystem.teamInformation(teamDirectory);
    }

    public static void calTeamInfo(MatchDirectory matchDirectory, TeamDirectory teamDirectory){
        for(Team team:teamDirectory.getTeamArrayList()) {
            for (Match match : matchDirectory.getMatchArrayList()) {
                if (match.getHomeTeam().equals(team.getName())) {
                    team.setTotalGoals(match.getHomeScore());
                    team.updateTimes();
                    team.setTotalFouls(match.getHF());
                    team.TotalSuccDefense(match.getAST() - match.getAwayScore());
                } else if (match.getAwayTeam().equals(team.getName())) {
                    team.setTotalGoals(match.getAwayScore());
                    team.updateTimes();
                    team.setTotalFouls(match.getAF());
                    team.TotalSuccDefense(match.getHST() - match.getHomeScore());
                }
            }
            team.setAverageGoals();
            team.setAverageDefense();
            team.setAverageFouls();
        }
    }

    public static void calTeamPoint(MatchDirectory matchDirectory,TeamDirectory teamDirectory) {
        for (Match match:matchDirectory.getMatchArrayList()) {
            if (match.getResult().equals("H")) {
                for (Team team1 : teamDirectory.getTeamArrayList()) {
                    if (team1.getName().equals(match.getHomeTeam())) {
                        team1.addPoint( 0.75);
                    }
                    if (team1.getName().equals(match.getAwayTeam())) {
                        team1.minusPoint(0.75);
                    }
                }
            }
            if (match.getResult().equals("D")){
                for (Team team1 : teamDirectory.getTeamArrayList()) {
                    if (team1.getName().equals(match.getHomeTeam())) {
                        team1.addPoint(0.4);
                    }
                    if (team1.getName().equals(match.getAwayTeam())) {
                        team1.addPoint(0.6);
                    }
                }
            }
            if (match.getResult().equals("A")){
                for (Team team1 : teamDirectory.getTeamArrayList()) {
                    if (team1.getName().equals(match.getHomeTeam())) {
                        team1.minusPoint(1.25);
                    }
                    if (team1.getName().equals(match.getAwayTeam())) {
                        team1.addPoint(1.25);
                    }
                }
            }
        }
        for (Team team : teamDirectory.getTeamArrayList()) {
            float temp;
            temp = (float) (team.getPoint()*0.55 + team.getTotalGoals()*0.25 + 0.15*team.getTotalSuccDefense() - 0.05 * team.getTotalFouls());
            team.setPoint(temp);
        }
    }

    public void teamInformation(TeamDirectory teamDirectory) {
        teamDirectory.createTeam("Arsenal");
        teamDirectory.createTeam("Aston Villa");
        teamDirectory.createTeam("Bournemouth");
        teamDirectory.createTeam("Brighton");
        teamDirectory.createTeam("Burnley");
        teamDirectory.createTeam("Chelsea");
        teamDirectory.createTeam("Crystal Palace");
        teamDirectory.createTeam("Everton");
        teamDirectory.createTeam("Leicester");
        teamDirectory.createTeam("Liverpool");
        teamDirectory.createTeam("Man City");
        teamDirectory.createTeam("Man United");
        teamDirectory.createTeam("Newcastle");
        teamDirectory.createTeam("Norwich");
        teamDirectory.createTeam("Sheffield United");
        teamDirectory.createTeam("Southampton");
        teamDirectory.createTeam("Tottenham");
        teamDirectory.createTeam("Watford");
        teamDirectory.createTeam("West Ham");
        teamDirectory.createTeam("Wolves");
    }

}
