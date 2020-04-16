import com.csvreader.CsvReader;

import java.lang.invoke.SwitchPoint;
import java.nio.charset.Charset;
import java.util.*;

public class RankingSystem {

    public static void main(String[] args) {
        MatchDirectory matchDirectory = new MatchDirectory();
        TeamDirectory teamDirectory = new TeamDirectory();
        sortHelper sortHelper = new sortHelper();
        DrawMath drawMath = new DrawMath();
        FutureMatchDirectory futureMatchDirectory = new FutureMatchDirectory();
        initializaData(matchDirectory,teamDirectory);
//        for (Match match:matchDirectory.getMatchArrayList()){
//            System.out.println(match);
//        }
        calTeamInfo(matchDirectory,teamDirectory);
        calTeamPoint(matchDirectory, teamDirectory);
        ArrayList<Team> rankingResult = sortHelper.calRanking(teamDirectory);
        for (int i = 0; i<teamDirectory.getTeamArrayList().size(); i++){
            System.out.println(rankingResult.get(i));
        }
        ArrayList<Match> futureMatch = futureMatchDirectory.getFutureMatch(matchDirectory,teamDirectory);
//        System.out.println(futureMatch);
        float[][] u = futureMatchDirectory.predictForGD(futureMatch,teamDirectory);
        int i =0;
        for(Match match: futureMatch){
            match.setPHS(u[i][0]);
            match.setPAS(u[i][1]);
            i++;
            if(match.getPAS() < 0 && match.getPHS() > 0){
                drawMath.poissonDistribution(match.getPHS(),match.getPAS(),match.getHomeTeam(), match.getAwayTeam());
                drawMath.theProOfResultWithPo(match);
                System.out.println(match.getResult());
                System.out.println("------------------------------------------------------------------------------------------------");
            }else if(match.getPAS() > 0 &&match.getPHS()<0){
                drawMath.poissonDistribution(match.getPAS(), match.getPHS(),match.getAwayTeam(),match.getHomeTeam());
                drawMath.theProOfResultWithPo(match);
                System.out.println(match.getResult());
                System.out.println("------------------------------------------------------------------------------------------------");
            }else {
                drawMath.skellamDistribution(match.getPHS(), match.getPAS(), match.getHomeTeam(), match.getAwayTeam());
                System.out.println("The probabiltiy of " + match.getHomeTeam() + " win this match with " + match.getAwayTeam() + " is " + drawMath.theProbabiltiyOfResult(match, "HW"));
                System.out.println("The probabiltiy of " + match.getHomeTeam() + " draw this match with " + match.getAwayTeam() + " is " + drawMath.theProbabiltiyOfResult(match, "Draw"));
                System.out.println("The probabiltiy of " + match.getHomeTeam() + " loss this match with " + match.getAwayTeam() + " is " + drawMath.theProbabiltiyOfResult(match, "AW"));
                if (drawMath.theProbabiltiyOfResult(match, "HW") > drawMath.theProbabiltiyOfResult(match, "AW") && drawMath.theProbabiltiyOfResult(match, "HW") >drawMath.theProbabiltiyOfResult(match, "Draw")){
                    match.setResult("H");
                }else if (drawMath.theProbabiltiyOfResult(match, "AW") > drawMath.theProbabiltiyOfResult(match, "HW") && drawMath.theProbabiltiyOfResult(match, "AW") >drawMath.theProbabiltiyOfResult(match, "Draw")) {
                    match.setResult("A");
                }else if (drawMath.theProbabiltiyOfResult(match, "Draw") > drawMath.theProbabiltiyOfResult(match, "HW") && drawMath.theProbabiltiyOfResult(match, "Draw") >drawMath.theProbabiltiyOfResult(match, "AW")) {
                    match.setResult("D");
                }
                System.out.println(match.getResult());
                System.out.println("------------------------------------------------------------------------------------------------");
            }
        }
        ArrayList<Team> finalyRank = futureMatchDirectory.addPointOfFutureMatch(rankingResult,futureMatch);
        finalyRank = sortHelper.calRanking(finalyRank);
        for(int j = 0; j<finalyRank.size();j++){
            System.out.println(finalyRank.get(j));
        }
    }

    public static void initializaData(MatchDirectory matchDirectory, TeamDirectory teamDirectory){
        String filePath = "main/resources/2019-2020.csv";
        DataReader dataReader = new DataReader();
        matchDirectory.matchArrayList = dataReader.readMatchFile(filePath);
        dataReader.teamInformation(teamDirectory);
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

}
