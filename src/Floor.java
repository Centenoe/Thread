/**
 * @author Joe matteson
 *
 * represents a floor in a building
 */
public class Floor {

    // --- Fields --- //
    private int floorNum;
    private Queue<Person> peopleCurrentOnFloor = new Queue<>();

    /**
     * constructor that reprsents a floor in a building
     * @param floorNum
     */
    public Floor(int floorNum) {
        this.floorNum = floorNum;
    }

    /**
     * puts a person onto the floor
     *
     * @param newPerson the new person on the floor
     */
    public void personGetsOntoFloor(Person newPerson) {
        this.peopleCurrentOnFloor.enqueue(newPerson);
    }

    /**
     * removes the person from the floor
     *
     * @return the person that was removed
     */
    public Person personWantsOffFloor() {
        Person personToRemove = peopleCurrentOnFloor.dequeue();
        return personToRemove;
    }

    /**
     * get the number of people currently on the floor
     *
     * @return the number of people currently on floor
     */
    public int getNumOfPeopleCurrentOnFloor() {
        return peopleCurrentOnFloor.size();
    }

    /**
     * increment the frustration level of everybody currently on the floor
     */
    public void incrementFrustration() {
        for (Person person : peopleCurrentOnFloor.toArray(Person.class)) {
            if (person != null)
                person.setFrustrationLevel(person.getFrustrationLevel() + 1);
        }
    }

    /**
     * get the floor number of the floor
     *
     * @return
     */
    public int getFloorNum() {
        return floorNum;
    }

    /**
     * tells us if the floor is empty
     * @return true if the floor is empty
     */
    public boolean isEmpty() {
        return peopleCurrentOnFloor.size() <= 0;
    }
}
