import java.util.ArrayList;

public class Floor {

    private int floorNum;
    private final int numOfPeopleLivingOnFloor;
    private ArrayList<Person> numOfPeopleCurrentOnFloor = new ArrayList<>();

    public Floor(int floorNum, int numOfPeopleLivingOnFloor) {
        this.floorNum = floorNum;
        this.numOfPeopleLivingOnFloor = numOfPeopleLivingOnFloor;
    }

    public void personGetsOntoFloor(Person newPerson) {
        this.numOfPeopleCurrentOnFloor.add(newPerson);
    }
}
