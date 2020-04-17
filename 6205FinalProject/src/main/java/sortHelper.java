import java.util.*;

public class sortHelper<X extends Comparable<X>>{
    /**
     * This class is defined to rank the ArrayList<Team> by some elementary sort method.
     * calRanking(ArrayList<Team> list)  Return the team list after rank by regular point using insertion sort.
     * calRankWithELO(ArrayList<Team> list) Return the team list after tank by ELO points using selection sort.
     * swap(ArrayList<Team> teams,int i, int j) swap two teams if need.
     */

    public ArrayList<Team> calRanking(ArrayList<Team> list){
        for (int i = 0; i < list.size();i++){
            for(int j = i; j > 0; j-- ){
                if (list.get(j).getPoint() > list.get(j-1).getPoint()){
                    swap(list,j,j-1);
                }
            }
        }
        return list;
    }

    public ArrayList<Team> calRankWithELO(ArrayList<Team> list) {
//        int n = list.size();
//        int h = 1;
//        while(h<n/3) h = 3*h + 1;
//        while(h>1){
//            for(int i = h; i<n; i++){
//                for(int j = i; j>=h && list.get(j).getELOPoint() > list.get(j-h).getELOPoint(); j -= h){
//                    swap(list,j,j-h);
//                }
//            }
//        } Shell sort is too complex to run, we change to use selection sort
        int N = list.size();
        for (int i = 0; i < N; i++) {
            int max = i;
            for (int j = i + 1; j < N; j++)
                if (list.get(j).getELOPoint() > list.get(max).getELOPoint()) max = j;
            swap(list, i, max);

        }
        return list;
    }


    public void swap(ArrayList<Team> teams,int i, int j){
        Collections.swap(teams,i,j);
    }
}
