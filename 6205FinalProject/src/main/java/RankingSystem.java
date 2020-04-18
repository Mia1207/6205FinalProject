import com.csvreader.CsvWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class RankingSystem {
    /**
     * This class is the most important class in this project
     * @param args
     * @initializaData (MatchDirectory matchDirectory, TeamDirectory teamDirectory) Obtain the team information and past matches information.
     * @updateTimes (ArrayList<Match> futureMatch, TeamDirectory teamDirectory) Add the played times of team after predict one result of a future match.
     * @calTeamInfo (MatchDirectory matchDirectory, TeamDirectory teamDirectory) Add some necessary information to team by analyzing the past matches.
     * @calTeamELOPoint (ArrayList<Match> Matchs, ArrayList<Team> teams) Return teams after calculate the ELO point by the result of matches.
     * @getFinalTable (ArrayList<Match> pastMatchs, ArrayList<Match> futureMatchs, ArrayList<Team> teams) Return a Map which stores
     * the whole matches result in this season both past result and future prediction.
     * @objects2Csv (ArrayList<Team> objects, String path) Store the rank of teams into an csv file.
     * @write (Map<String, String> ints, ArrayList<Team> teams) Store the final table into an csv file.
     */

    public static void main(String[] args) {
        MatchDirectory matchDirectory = new MatchDirectory();
        TeamDirectory teamDirectory = new TeamDirectory();
        sortHelper sortHelper = new sortHelper();
        DrawMath drawMath = new DrawMath();
        FutureMatchDirectory futureMatchDirectory = new FutureMatchDirectory();
        initializaData(matchDirectory, teamDirectory);
        calTeamInfo(matchDirectory, teamDirectory);
        ArrayList<Team> rankingResult = sortHelper.calRanking(teamDirectory.getTeamArrayList());
        ArrayList<Team> rankWithELO = calTeamELOPoint(matchDirectory.matchArrayList, rankingResult);
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("The rank result sorted by EPL Point");
        for (int i = 0; i < teamDirectory.getTeamArrayList().size(); i++) {
            System.out.println(rankingResult.get(i));
        }
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("The rank result sorted by ELO rating principle");
        rankWithELO = sortHelper.calRankWithELO(rankWithELO);
        for (int i = 0; i < teamDirectory.getTeamArrayList().size(); i++) {
            System.out.println(rankWithELO.get(i));
        }
        try {
            objects2Csv(rankingResult, "main/resources/Rank result of EPL Principle/Already Rank.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            objects2Csv(rankWithELO, "main/resources/Rank result of ELO Rating Principle/Already Rank with ELO.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Match> futureMatch = futureMatchDirectory.getFutureMatch(matchDirectory, teamDirectory);
        float[][] u = futureMatchDirectory.predictForGD(futureMatch, teamDirectory);
        int i = 0;
        System.out.println("------------------------------------------------------------------------------------------------");
        for (Match match : futureMatch) {
            match.setPHS(u[i][0]);
            match.setPAS(u[i][1]);
            i++;
            if (match.getPAS() < 0 && match.getPHS() > 0) {
                drawMath.poissonDistribution(match.getPHS(), match.getPAS(), match.getHomeTeam(), match.getAwayTeam());
                drawMath.theProOfResultWithPo(match);
                System.out.println("The result of this match is: " + match.getResult());
                System.out.println("------------------------------------------------------------------------------------------------");
            } else if (match.getPAS() > 0 && match.getPHS() < 0) {
                drawMath.poissonDistribution(match.getPAS(), match.getPHS(), match.getAwayTeam(), match.getHomeTeam());
                drawMath.theProOfResultWithPo(match);
                System.out.println("The result of this match is: " + match.getResult());
                System.out.println("------------------------------------------------------------------------------------------------");
            } else {
                drawMath.skellamDistribution(match.getPHS(), match.getPAS(), match.getHomeTeam(), match.getAwayTeam());
                System.out.println("The probabiltiy of " + match.getHomeTeam() + " win this match with " + match.getAwayTeam() + " is " + drawMath.theProbabiltiyOfResult(match, "HW"));
                System.out.println("The probabiltiy of " + match.getHomeTeam() + " draw this match with " + match.getAwayTeam() + " is " + drawMath.theProbabiltiyOfResult(match, "Draw"));
                System.out.println("The probabiltiy of " + match.getHomeTeam() + " loss this match with " + match.getAwayTeam() + " is " + drawMath.theProbabiltiyOfResult(match, "AW"));
                if (drawMath.theProbabiltiyOfResult(match, "HW") > drawMath.theProbabiltiyOfResult(match, "AW") && drawMath.theProbabiltiyOfResult(match, "HW") > drawMath.theProbabiltiyOfResult(match, "Draw")) {
                    match.setResult("H");
                } else if (drawMath.theProbabiltiyOfResult(match, "AW") > drawMath.theProbabiltiyOfResult(match, "HW") && drawMath.theProbabiltiyOfResult(match, "AW") > drawMath.theProbabiltiyOfResult(match, "Draw")) {
                    match.setResult("A");
                } else if (drawMath.theProbabiltiyOfResult(match, "Draw") > drawMath.theProbabiltiyOfResult(match, "HW") && drawMath.theProbabiltiyOfResult(match, "Draw") > drawMath.theProbabiltiyOfResult(match, "AW")) {
                    match.setResult("D");
                }
                System.out.println("The result of this match is: " + match.getResult());
                System.out.println("------------------------------------------------------------------------------------------------");
            }
        }
        ArrayList<Team> finalyRank = futureMatchDirectory.addPointOfFutureMatch(rankingResult, futureMatch);
        updateTimes(futureMatch, teamDirectory);
        ArrayList<Team> finalRankWithELO = calTeamELOPoint(futureMatch, finalyRank);
        finalyRank = sortHelper.calRanking(finalRankWithELO);
        System.out.println("The final rank result sorted by EPL point");
        for (int j = 0; j < finalyRank.size(); j++) {
            System.out.println(finalyRank.get(j));
        }
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("The final rank result sort by ELO point");
        finalRankWithELO = sortHelper.calRankWithELO(finalRankWithELO);
        for (int j = 0; j < finalRankWithELO.size(); j++) {
            System.out.println(finalRankWithELO.get(j));
        }
        System.out.println("------------------------------------------------------------------------------------------------");
        try {
            objectsCsv(finalyRank, "main/resources/Rank result of EPL Principle/Final Rank.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            objects2Csv(finalRankWithELO, "main/resources/Rank result of ELO Rating Principle/Final Rank with ELO.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> table = getFinalTable(matchDirectory.matchArrayList, futureMatch, rankingResult);
        write(table, rankingResult);
    }

    public static void initializaData(MatchDirectory matchDirectory, TeamDirectory teamDirectory) {
        String filePath = "main/resources/2019-2020.csv";
        DataReader dataReader = new DataReader();
        matchDirectory.matchArrayList = dataReader.readMatchFile(filePath);
        dataReader.teamInformation(teamDirectory);
    }

    private static void updateTimes(ArrayList<Match> futureMatch, TeamDirectory teamDirectory) {
        for (Match match : futureMatch) {
            for (Team team : teamDirectory.getTeamArrayList()) {
                if (team.getName().equals(match.getAwayTeam())) {
                    team.updateTimes();
                } else if (team.getName().equals(match.getHomeTeam())) {
                    team.updateTimes();
                }
            }
        }
    }

    public static void calTeamInfo(MatchDirectory matchDirectory, TeamDirectory teamDirectory) {
        for (Team team : teamDirectory.getTeamArrayList()) {
            for (Match match : matchDirectory.getMatchArrayList()) {
                if (match.getHomeTeam().equals(team.getName())) {
                    team.setTotalGoals(match.getHomeScore());
                    team.updateTimes();
                    team.addTotalShot(match.getHST());
                    team.setTotalFouls(match.getHF());
                    team.addRivalAtHome(match.getAwayTeam());
                    team.setGD(match.getHomeScore() - match.getAwayScore());
                    team.TotalSuccDefense(match.getAST() - match.getAwayScore());
                } else if (match.getAwayTeam().equals(team.getName())) {
                    team.setTotalGoals(match.getAwayScore());
                    team.updateTimes();
                    team.addTotalShot(match.getAST());
                    team.setGD(match.getAwayScore() - match.getHomeScore());
                    team.setTotalFouls(match.getAF());
                    team.TotalSuccDefense(match.getHST() - match.getHomeScore());
                }
            }
            team.setAverageGoals();
            team.setAverageDefense();
            team.setAverageFouls();
        }
    }

    public static ArrayList<Team> calTeamELOPoint(ArrayList<Match> Matchs, ArrayList<Team> teams) {
        int count = 0;
        for (Match match : Matchs) {
            if (match.getResult().equals("H")) {
                for (Team team1 : teams) {
                    if (team1.getName().equals(match.getHomeTeam())) {
                        for (Team team2 : teams) {
                            if (team2.getName().equals(match.getAwayTeam())) {
                                count++;
                                team1.updateELO((float) (team2.getPoint() + 100) / team1.getTheNumberofGamesPlayed());
                                team2.updateELO((float) (team1.getPoint() - 100) / team2.getTheNumberofGamesPlayed());
                            }
                        }
                    }
                }
            } else if (match.getResult().equals("D")) {
                for (Team team1 : teams) {
                    if (team1.getName().equals(match.getHomeTeam())) {
                        for (Team team2 : teams) {
                            if (team2.getName().equals(match.getAwayTeam())) {
                                count++;
                                team1.updateELO((float) (team2.getPoint() / team1.getTheNumberofGamesPlayed()));
                                team2.updateELO((float) (team1.getPoint() / team2.getTheNumberofGamesPlayed()));
                            }
                        }
                    }
                }
            }
            if (match.getResult().equals("A")) {
                for (Team team1 : teams) {
                    if (team1.getName().equals(match.getHomeTeam())) {
                        for (Team team2 : teams) {
                            if (team2.getName().equals(match.getAwayTeam())) {
                                count++;
                                team2.updateELO((float) (team1.getPoint() + 100) / team2.getTheNumberofGamesPlayed());
                                team1.updateELO((float) (team2.getPoint() - 100) / team1.getTheNumberofGamesPlayed());
                            }
                        }
                    }
                }
            }
        }
        return teams;
    }

    public static Map<String, String> getFinalTable(ArrayList<Match> pastMatchs, ArrayList<Match> futureMatchs, ArrayList<Team> teams) {
        Map<String, String> result = new HashMap<>();
        pastMatchs.addAll(futureMatchs);
        for (Team team : teams) {
            String r = new String();
            for (Team team1 : teams) {
                if (!team.getName().equals(team1.getName())) {
                    for (Match match : pastMatchs) {
                        if (team.getName().equals(match.getHomeTeam()) && team1.getName().equals(match.getAwayTeam())) {
                            r += team1.getName() + "=" + match.getResult() + ",";
                        }
                    }
                } else {
                    r += team1.getName() + "=" + "N/A,";
                }
            }
            result.put(team.getName(), r);
        }
        return result;
    }


    /**
     * 写入CSV文件
     */

    public static void objects2Csv(ArrayList<Team> objects, String path) throws IOException {
        File file = new File(path);
        try (OutputStream out = new FileOutputStream(file);

             OutputStreamWriter writer = new OutputStreamWriter((out), "GBK")) {

            ArrayList<String> list = new ArrayList<String>();
            int rowNumCount = objects.size();
            // 获取title
            String title = "";
            boolean titleStatusFlag = false;
            // 循环行Row
            for (int rowNum = 0; rowNum < rowNumCount; rowNum++) {

                // 获取传来的对象数据
                String o = objects.get(rowNum).toString();
                // 获取 对象属性数据对
                String[] entrys = o.split(",");

                // 创建对应的csv 数据对象
                String data = "";
                // 获取当前的 行 Cell 的所有列 Row 数据
                for (int cellNum = 0; cellNum < entrys.length; cellNum++) {
                    String entry = entrys[cellNum];
                    String[] titleAndData = entry.split("=");
                    if (!titleStatusFlag) {
                        // title
                        title += titleAndData[0] + ",";
                    }
                    // data
                    data += titleAndData[1] + ",";
                }
                titleStatusFlag = true;
                list.add(data);
            }

            writer.append(title + "\n");
            for (String string : list) {
                writer.append(string + "\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void objectsCsv(ArrayList<Team> objects, String path) throws IOException {
        File file = new File(path);
        try (OutputStream out = new FileOutputStream(file);

             OutputStreamWriter writer = new OutputStreamWriter((out), "GBK")) {

            ArrayList<String> list = new ArrayList<String>();
            int rowNumCount = objects.size();
            // 获取title
            String title = "";
            boolean titleStatusFlag = false;
            // 循环行Row
            for (int rowNum = 0; rowNum < rowNumCount; rowNum++) {

                // 获取传来的对象数据
                String o = objects.get(rowNum).toString();
                // 获取 对象属性数据对
                String[] entrys = o.split(",");

                // 创建对应的csv 数据对象
                String data = "";
                // 获取当前的 行 Cell 的所有列 Row 数据
                for (int cellNum = 0; cellNum < entrys.length; cellNum++) {
                    String entry = entrys[cellNum];
                    String[] titleAndData = entry.split("=");
                    if (!titleStatusFlag) {
                        // title
                        title += titleAndData[0] + ",";
                    }
                    // data
                    data += titleAndData[1] + ",";
                }
                titleStatusFlag = true;
                list.add(data);
            }

            writer.append(title + "\n");
            for (String string : list) {
                writer.append(string + "\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(Map<String, String> ints, ArrayList<Team> teams) {
        String path = "main/resources/Final Table.csv";
        ArrayList<String> re = new ArrayList<>();
        try (OutputStream out = new FileOutputStream(path);

             OutputStreamWriter writer = new OutputStreamWriter((out), "GBK")) {

            ArrayList<String> list = new ArrayList<String>();
            int rowNumCount = ints.size() + 1;
            // 获取title
            String title = " ,";
            boolean titleStatusFlag = false;
            // 循环行Row
            for (String s : ints.keySet()) {

                // 获取传来的对象数据
                String o = ints.get(s);
                // 获取 对象属性数据对
                String[] entrys = o.split(",");

                // 创建对应的csv 数据对象
                String data = s+",";
                // 获取当前的 行 Cell 的所有列 Row 数据
                for (int cellNum = 0; cellNum < entrys.length; cellNum++) {
                    String entry = entrys[cellNum];
                    String[] titleAndData = entry.split("=");
                    if (!titleStatusFlag) {
                        // title
                        title += titleAndData[0] + ",";
                    }
                    // data
                    data += titleAndData[1] + ",";
                }
                titleStatusFlag = true;
                list.add(data);
            }

            writer.append(title + "\n");
            for (String string : list) {
                writer.append(string + "\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}