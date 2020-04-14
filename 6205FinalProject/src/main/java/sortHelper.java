import java.util.*;

public class sortHelper<X extends Comparable<X>>{

    public ArrayList<Team> calRanking(TeamDirectory teamDirectory){
        ArrayList<Team> rankingResult = intialized(teamDirectory);
        for (int i = 0; i < teamDirectory.getTeamArrayList().size();i++){
            for(int j = i; j > 0; j-- ){
                if (rankingResult.get(j).getPoint() > rankingResult.get(j-1).getPoint()){
                    swap(rankingResult,j,j-1);
                }
            }
        }
        return rankingResult;
    }

    public ArrayList<Team> intialized(TeamDirectory teamDirectory){
        ArrayList<Team> rankingResult = new ArrayList<>();
        int i =0;
        for (Team team:teamDirectory.getTeamArrayList()){
            rankingResult.add(team);
        }
        return rankingResult;
    }

    public void swap(ArrayList<Team> teams,int i, int j){
        Collections.swap(teams,i,j);
    }

    public Boolean bigger(Team x, Team y){
        return  x.getPoint() > y.getPoint();
    }
}
