import java.util.Arrays;

public class Elevator {

    // --- Fields --- //
    private Person[] stack;
    private int numberOfPeople = 0;
    private int currentFloor = 1;
    private final int MAX_NUM_OF_PEOPLE = 20;
    private boolean initialized = false;
    private int top = -1;

    public Elevator() {
        stack = new Person[MAX_NUM_OF_PEOPLE];
        initialized = true;
    }

    public boolean push(Person person) {
        checkInitialization();
        stack[top + 1] = person;
        top++;
        incrementFrustration();
        numberOfPeople++;
        return true;
    }

    public Person pop() {
        checkInitialization();
        Person remove = stack[top];
        stack[top] = null;
        top--;
        numberOfPeople--;
        return remove;
    }

    public Person peek() {
        return top == -1 ? null : stack[top];
    }

    private void incrementFrustration() {
        for (int i = 0; i < numberOfPeople; i++) {
            if (stack[i] != null)
                stack[i].setFrustrationLevel(stack[i].getFrustrationLevel() + 1);
        }
    }

    public void nextFloor() {
        currentFloor++;
    }

    public void previousFloor() {
        currentFloor--;
    }

    public boolean isEmpty() {
        return numberOfPeople == 0;
    }

    public boolean checkInitialization(){
        if (initialized)
            return true;
        else
            throw new IllegalStateException("Elevator Not Initialiezed");
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

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
