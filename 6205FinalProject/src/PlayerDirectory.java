import java.util.ArrayList;

public class PlayerDirectory {

    ArrayList<Player> playerArrayList;
    public PlayerDirectory(){
        this.playerArrayList = new ArrayList<>();
    }

    public ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    public Player createPlayer(String name, String teamName, Float points, int age){
        Player player = new Player();
        player.setTeamName(teamName);
        player.setPoints(points);
        player.setName(name);
        player.setAge(age);
        playerArrayList.add(player);
        return player;
    }
}
