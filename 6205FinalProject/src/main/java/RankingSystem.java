import com.csvreader.CsvReader;

import java.nio.charset.Charset;
import java.util.*;

public class RankingSystem {

    public static void main(String[] args) {
        MatchDirectory matchDirectory = new MatchDirectory();
        TeamDirectory teamDirectory = new TeamDirectory();
        sortHelper sortHelper = new sortHelper();
        FutureMatchDirectory futureMatchDirectory = new FutureMatchDirectory();
        initializaData(matchDirectory,teamDirectory);
//        for (Match match:matchDirectory.getMatchArrayList()){
//            System.out.println(match);
//        }
        calTeamInfo(matchDirectory,teamDirectory);
        calTeamPoint(matchDirectory, teamDirectory);
        ArrayList<Team> rankingResult = sortHelper.calRanking(teamDirectory);
//        for (int i = 0; i<teamDirectory.getTeamArrayList().size(); i++){
//            System.out.println(rankingResult.get(i));
//        }
        ArrayList<Match> futureMatch = futureMatchDirectory.getFutureMatch(matchDirectory,teamDirectory);
        System.out.println(futureMatch.size());
        float[][] u = futureMatchDirectory.predictForGD(futureMatch,teamDirectory);
        for(int i=0; i<u.length; i++){
            for(int j=0; j<u[i].length; j++){
                System.out.print(u[i][j] +" ");
            }
            System.out.println();
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
                    team.addTotalShot(match.getHST());
                    team.setTotalFouls(match.getHF());
                    team.addRivalAtHome(match.getAwayTeam());
                    team.setGD(match.getHomeScore()-match.getAwayScore());
                    team.TotalSuccDefense(match.getAST() - match.getAwayScore());
                } else if (match.getAwayTeam().equals(team.getName())) {
                    team.setTotalGoals(match.getAwayScore());
                    team.updateTimes();
                    team.addTotalShot(match.getAST());
                    team.setGD(match.getAwayScore()-match.getHomeScore());
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
        int count =0;
        for (Match match:matchDirectory.getMatchArrayList()) {
            if (match.getResult().equals("H")) {
                for (Team team1 : teamDirectory.getTeamArrayList()) {
                    if (team1.getName().equals(match.getHomeTeam())) {
                        for(Team team2:teamDirectory.getTeamArrayList()){
                            if(team2.getName().equals(match.getAwayTeam())){
                                count ++;
                                team1.addPoint( (float)(team2.getPoint() +1)/team1.getTheNumberofGamesPlayed());
                                team2.addPoint((float)(team1.getPoint()-1)/team2.getTheNumberofGamesPlayed());
                            }
                        }
                    }
                }
            }else if (match.getResult().equals("D")){
                for (Team team1 : teamDirectory.getTeamArrayList()) {
                    if (team1.getName().equals(match.getHomeTeam())) {
                        for(Team team2:teamDirectory.getTeamArrayList()){
                            if(team2.getName().equals(match.getAwayTeam())) {
                                count++;
                                team1.addPoint((float) (team2.getPoint() / team1.getTheNumberofGamesPlayed()));
                                team2.addPoint((float) (team1.getPoint() / team2.getTheNumberofGamesPlayed()));
                            }
                        }
                    }
                }
            }
            if (match.getResult().equals("A")){
                for (Team team1 : teamDirectory.getTeamArrayList()) {
                    if (team1.getName().equals(match.getHomeTeam())) {
                        for(Team team2:teamDirectory.getTeamArrayList()){
                            if(team2.getName().equals(match.getAwayTeam())) {
                                count++;
                                team2.addPoint((float) (team1.getPoint() + 1) / team2.getTheNumberofGamesPlayed());
                                team1.addPoint((float) (team2.getPoint() - 1) / team1.getTheNumberofGamesPlayed());
                            }
                        }
                    }
                }
            }
        }

        for (Team team : teamDirectory.getTeamArrayList()) {
            float temp;
            temp = (float) (team.getPoint()*0.5*team.getTheNumberofGamesPlayed() + team.getGD()*0.3 + 0.05*team.getTotalGoals() + 0.05*team.getTotalSuccDefense() +0.1*team.getTotalGoals())/team.getTheNumberofGamesPlayed()*100;
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