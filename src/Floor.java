import java.util.ArrayList;

public class Floor {

    // --- Fields --- //
    private int floorNum;
    private final int numOfPeopleLivingOnFloor;
    private Queue<Person> peopleCurrentOnFloor = new Queue<>();

    /**
     * constructor that reprsents a floor in a building
     * @param floorNum
     * @param numOfPeopleLivingOnFloor
     */
    public Floor(int floorNum, int numOfPeopleLivingOnFloor) {
        this.floorNum = floorNum;
        this.numOfPeopleLivingOnFloor = numOfPeopleLivingOnFloor;
    }

    public void personGetsOntoFloor(Person newPerson) {
        this.peopleCurrentOnFloor.enqueue(newPerson);
    }

    public Person personWantsOffFloor() {
        Person personToRemove = peopleCurrentOnFloor.dequeue();
        return personToRemove;
    }

    public int getNumOfPeopleCurrentOnFloor() {
        return peopleCurrentOnFloor.size();
    }

    public boolean isEmpty() {
        return peopleCurrentOnFloor.size() <= 0;
    }
}
