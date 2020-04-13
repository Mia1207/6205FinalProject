import com.csvreader.CsvReader;

import java.nio.charset.Charset;
import java.util.*;

public class RankingSystem {

    public static void main(String[] args) {
        MatchDirectory matchDirectory = new MatchDirectory();
        TeamDirectory teamDirectory = new TeamDirectory();
        initializaData(matchDirectory,teamDirectory);
//        for (Match match:matchDirectory.getMatchArrayList()){
//            System.out.println(match);
//        }
        calTeamInfo(matchDirectory,teamDirectory);;
        for (Team team :teamDirectory.getTeamArrayList()){
            System.out.println(team);
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
                } else if (match.getAwayTeam().equals(team.getName())) {
                    team.setTotalGoals(match.getAwayScore());
                    team.updateTimes();
                }
            }
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
