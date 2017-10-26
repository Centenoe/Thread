import java.util.Random;

/**
 * @author Joe matteson
 *
 * represents a building witha certain amount floors and a certain amount of elevators
 */
public class Building {

    // --- Fields --- //
    private Elevator[] elevators;
    private Floor[] floors;
    private int numOfFloors;
    private final int[] FIRST_FIVE_FLOORS = {0, 100, 65, 75, 110};
    private Random rnd = new Random();

    /**
     * the default building constructor for making a Building
     */
    public Building() {
        this(1);
    }

    /**
     * creates a new building with a given number of elevators that the building will has.
     * (fun code)
     *
     * @param numOfElevators the number of elevators the building has
     */
    public Building(int numOfElevators) {
        if (numOfElevators < 1)
            throw new IllegalArgumentException("There cant be less than 1 elevator in a building.");
        this.numOfFloors = 5;
        setUpElevators(numOfElevators);
        setUpFloors(numOfFloors);

    }

    /**
     * creates a new building with a given number of elevators and a given number of floors
     * (fun code)
     *
     * @param numOfElevators in the building
     * @param numOfFloors in the building
     */
    public Building(int numOfElevators, int numOfFloors) {
        this.numOfFloors = numOfFloors;
        setUpElevators(numOfElevators);
        setUpFloors(numOfFloors);
    }

    /**
     * sets up the elevators for the new building
     *
     * @param numOfElevators number of elevators to set up
     */
    private void setUpElevators(int numOfElevators) {
        this.elevators = new Elevator[numOfElevators];
        for (int i = 0; i < numOfElevators; i++) {
            elevators[i] = new Elevator();
        }
    }

    /**
     * sets up the floors for the new building
     *
     * @param numOfFloors number of floors that will be in the building
     */

    private void setUpFloors(int numOfFloors) {
        this.floors = new Floor[numOfFloors];
        for (int i = 0; i < this.floors.length; i++)
            this.floors[i] = new Floor(i);
    }

    /**
     * get the elevators that the building has
     *
     * @return the elevators in the building
     */
    public Elevator[] getElevators() {
        return elevators;
    }

    /**
     * get the floors of the building
     *
     * @return the floors of the building
     */
    public Floor[] getFloors() {
        return floors;
    }
}
