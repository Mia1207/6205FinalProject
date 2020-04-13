import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class RankingSystem {

    MatchDirectory matchDirectory;
    TeamDirectory teamDirectory;
    private static String xls = "main\\resources\\20192020.csv";

    public RankingSystem(){
        this.matchDirectory = new MatchDirectory();
        this.teamDirectory = new TeamDirectory();
    }

    public static void main(String[] args){

        System.out.println("************读取Excel2003的主表数据（将Excel单元格设为文本格式）******************");
        long start2003 = System.currentTimeMillis();
        List<Match> list2003 = ReadExcel.readFromXLS2003(xls);
        for (Match match : list2003) {
            System.out.println(match);
        }
    }

    public void matchHistory(){

    }

    public void teamInformation(){
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

    public static class ReadExcel {

        /**
         * 读取Excel2003的主表数据 （单个sheet）
         *
         * @param filePath
         * @return
         */
        private static List<Match> readFromXLS2003(String filePath) {
            File excelFile = null;// Excel文件对象
            InputStream is = null;// 输入流对象
            String cellStr = null;// 单元格，最终按字符串处理
            List<Match> matchesList = new ArrayList<>();// 返回封装数据的List
            Match match = null;// 每一个雇员信息对象
            try {
                excelFile = new File(filePath);
                is = new FileInputStream(excelFile);// 获取文件输入流
                HSSFWorkbook workbook2003 = new HSSFWorkbook(is);// 创建Excel2003文件对象
                HSSFSheet sheet = workbook2003.getSheetAt(0);// 取出第一个工作表，索引是0
                // 开始循环遍历行，表头不处理，从1开始
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    HSSFRow row = sheet.getRow(i);// 获取行对象
                    Match match1 = new Match();// 实例化Student对象
                    if (row == null) {// 如果为空，不处理
                        continue;
                    }
                    // 循环遍历单元格
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        HSSFCell cell = row.getCell(j);// 获取单元格对象
                        if (cell == null) {// 单元格为空设置cellStr为空串
                            cellStr = "";
//                        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
//                            cellStr = String.valueOf(cell.getBooleanCellValue());
//                        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
//                            cellStr = cell.getNumericCellValue() + "";
                        } else {// 其余按照字符串处理
                            cellStr = cell.getStringCellValue();
                        }
                        // 下面按照数据出现位置封装到bean中
                        if (j == 2) {
                            match1.setTeamA(cellStr);
                        } else if (j == 3) {
                            match1.setTeamB(cellStr);
//                        } else if (j == 2) {
//                            employee.setAge(new Double(cellStr).intValue());
//                        } else if (j == 3) {
//                            employee.setDepartment(cellStr);
//                        } else if(j == 4){
//                            employee.setSalary(new Double(cellStr).intValue());
//                        }else {
//                            employee.setDate(cellStr);
                        }
                    }
                    matchesList.add(match1);// 数据装入List
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {// 关闭文件流
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return matchesList;
        }

    }
}
