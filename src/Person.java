/**
 * @author Joe matteson
 *
 * represents a elevator rider
 */
public class Person {

    // --- Fields --- //
    private int id;
    private int frustrationLevel = 0;
    private int targetFloor;

    /**
     * creates a new person
     *
     * @param id the unique id of the person
     * @param targetFloor their "home" floor
     */
    public Person(int id, int targetFloor){
        this.id = id;
        this.targetFloor = targetFloor;
    }

    /**
     * @return the unique id of this person
     */
    public int getId() {
        return id;
    }

    /**
     * set a new id to this person
     *
     * @param id new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get the frustration level of this person
     *
     * @return the frustration level
     */
    public int getFrustrationLevel() {
        return frustrationLevel;
    }

    /**
     * set or reset the frustration level
     *
     * @param frustrationLevel new frustration level
     */
    public void setFrustrationLevel(int frustrationLevel) {
        this.frustrationLevel = frustrationLevel;
    }

    /**
     * get the target floor of this person
     *
     * @return the target floor number
     */
    public int getTargetFloor() {
        return targetFloor;
    }

    /**
     * set a new target floor for this person
     *
     * @param targetFloor new target floor
     */
    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    /**
     * custom toString representation
     * @return string representation
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", frustrationLevel=" + frustrationLevel +
                ", targetFloor=" + targetFloor +
                '}';
    }
}
