import java.util.ArrayList;

public class Floor {

    private int floorNum;
    private final int numOfPeopleLivingOnFloor;
    private ArrayList<Person> peopleCurrentOnFloor = new ArrayList<>();
    private int top = 0;

    public Floor(int floorNum, int numOfPeopleLivingOnFloor) {
        this.floorNum = floorNum;
        this.numOfPeopleLivingOnFloor = numOfPeopleLivingOnFloor;
    }

    public void personGetsOntoFloor(Person newPerson) {
        this.peopleCurrentOnFloor.add(newPerson);
    }

    public Person personWantsOffFloor() {
        Person personToRemove = peopleCurrentOnFloor.get(top);
        peopleCurrentOnFloor.remove(top);
        top++;
        return personToRemove;
    }

    public int getNumOfPeopleCurrentOnFloor() {
        return peopleCurrentOnFloor.size();
    }

    public boolean isEmpty() {
        return peopleCurrentOnFloor.size() <= 0;
    }

    public int getTop() {
        return this.top;
    }
}
