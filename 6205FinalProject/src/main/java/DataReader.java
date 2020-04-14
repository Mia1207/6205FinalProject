import com.csvreader.CsvReader;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class DataReader {

    public DataReader(){

    }

    public ArrayList<Match> readMatchFile(String filePath){
        ArrayList<Match> matchArrayList = new ArrayList<>();
        try {
            ArrayList<String[]> csvList = new ArrayList<String[]>();
            CsvReader reader = new CsvReader(filePath,',', Charset.forName("GBK"));
            reader.readHeaders(); //跳过表头,不跳可以注释掉

            while(reader.readRecord()){
                csvList.add(reader.getValues()); //按行读取，并把每一行的数据添加到list集合
            }
            reader.close();

            for(int row=0;row<csvList.size();row++){
                //打印每一行的数据
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



}

