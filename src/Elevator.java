import java.util.Arrays;

/**
 * @author Joe matteson
 *
 * represents a elevator in a building whit a max capacity of 20 and very narrow
 * (its basically a stack)
 */
public class Elevator {

    // --- Fields --- //
    private Person[] stack;
    private int numberOfPeople = 0;
    private int currentFloor = 0;
    private final int MAX_NUM_OF_PEOPLE = 20;
    private boolean initialized = false;
    private int top = -1;

    /**
     * creates a new Elevator. default constructor
     */
    public Elevator() {
        stack = new Person[MAX_NUM_OF_PEOPLE];
        initialized = true;
    }

    /**
     * person gets into elevator (first in last out)
     *
     * @param person the person that gets into the elevator
     * @return true if
     */
    public boolean push(Person person) {
        checkInitialization();
        if (isFull())
            return false;
        stack[top + 1] = person;
        top++;
        incrementFrustration();
        numberOfPeople++;
        return true;
    }

    /**
     * removes the last on person from the elevator
     *
     * @return the person that was removed
     * */
    public Person pop() {
        if (isEmpty())
            return null;
        checkInitialization();
        Person remove = stack[top];
        stack[top] = null;
        top--;
        numberOfPeople--;
        return remove;
    }

    /**
     * returns the last person on the elevator as long as there is someone in the elevator
     *
     * @return the last preson on the elevator
     */
    public Person peek() {
        return top == -1 ? null : stack[top];
    }

    /**
     * increment the frustration level of every on in the elevator
     */
    private void incrementFrustration() {
        for (int i = 0; i < numberOfPeople; i++) {
            if (stack[i] != null)
                stack[i].setFrustrationLevel(stack[i].getFrustrationLevel() + 1);
        }
    }

    /**
     * move the elevator to the up a floor
     */
    public void nextFloor() {
        if (currentFloor >= 5)
            return;
        currentFloor++;
    }

    /**
     * move the elevator to the down a floor
     */
    public void previousFloor() {
        if (currentFloor <= 0 )
            return;
        currentFloor--;
    }

    /**
     * is the elevator empty
     *
     * @return true otherwise false
     */
    public boolean isEmpty() {
        return numberOfPeople <= 0;
    }

    /**
     * is the elevator full
     *
     * @return true otherwise false
     */
    public boolean isFull() { return numberOfPeople >= 20; }

    /**
     * check if the elevator is initialized
     *
     * @return true otherwise
     * @throws IllegalStateException
     */
    private boolean checkInitialization(){
        if (initialized)
            return true;
        else
            throw new IllegalStateException("Elevator Not Initialiezed");
    }

    /**
     * get the current floor that the elevator is on
     * @return the current floor number
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * returns the string representation of a elevator
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Elevator{" +
                "stack=" + Arrays.toString(stack) +
                ", numberOfPeople=" + numberOfPeople +
                ", currentFloor=" + currentFloor +
                ", MAX_NUM_OF_PEOPLE=" + MAX_NUM_OF_PEOPLE +
                ", initialized=" + initialized +
                ", top=" + top +
                '}';
    }
}
