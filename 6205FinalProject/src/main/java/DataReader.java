import com.csvreader.CsvReader;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class DataReader {
    /*
    * This Class is defined to help us to read the dataset from csv file
     */

    public DataReader(){

    }

    /**
     * Obtain the match history by a csv file which is useful us to analyze the future match
     * @param filePath
     * @return
     */
    public ArrayList<Match> readMatchFile(String filePath){
        ArrayList<Match> matchArrayList = new ArrayList<>();
        try {
            ArrayList<String[]> csvList = new ArrayList<String[]>();
            CsvReader reader = new CsvReader(filePath,',', Charset.forName("GBK"));
            reader.readHeaders(); // skip the header of the csv file

            while(reader.readRecord()){
                csvList.add(reader.getValues()); //scan by row
            }
            reader.close();

            for(int row=0;row<csvList.size();row++){
                // print each value in the row
                Match match = new Match();
                match.setHomeTeam(csvList.get(row)[3]);
                match.setDate(csvList.get(row)[1]);
                match.setAwayTeam(csvList.get(row)[4]);
                match.setAF(Integer.valueOf(csvList.get(row)[17]));
                match.setAST(Integer.valueOf(csvList.get(row)[15]));
                match.setAwayScore(Integer.valueOf(csvList.get(row)[6]));
                match.setHF(Integer.valueOf(csvList.get(row)[16]));
                match.setHST(Integer.valueOf(csvList.get(row)[14]));
                match.setHomeScore(Integer.valueOf(csvList.get(row)[5]));
                match.setResult(csvList.get(row)[7]);
                matchArrayList.add(match);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchArrayList;
    }

    /**
     * As we know the name of teams in this season, we just need to create those team in one Directory
     * @param teamDirectory A ArrayList stores the directory of team.
     */
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

