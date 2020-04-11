
public class Player {
    private String name;
    private String teamName;
    private float points;
    private int age;

    public Player(){

    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPoints() {
        return points;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
