import java.util.ArrayList;

public class TeamDirectory {
    ArrayList<Team> teamArrayList;

    public TeamDirectory(){
        teamArrayList = new ArrayList<>();
    }

    public ArrayList<Team> getTeamArrayList() {
        return teamArrayList;
    }

    public Team createTeam(String name, int number){
        Team team = new Team();
        team.setName(name);
        team.setNumberOfPlayers(number);
        teamArrayList.add(team);
        return team;
    }
}
