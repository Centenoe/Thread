import java.util.Random;

public class Building {

    private Elevator[] elevators;
    private Floor[] floors;
    private int numOfFloors;
    private final int[] FIRST_FIVE_FLOORS = {0, 100, 65, 75, 110};
    private Random rnd = new Random();

    public Building() {
        this(1);
    }

    public Building(int numOfElevators) {
        if (numOfElevators < 1)
            throw new IllegalArgumentException("There cant be less than 1 elevator in a building.");
        this.numOfFloors = 5;
        setUpElevators(numOfElevators);
        setUpFloors(numOfFloors);

    }

    public Building(int numOfElevators, int numOfFloors) {
        this.numOfFloors = numOfFloors;
        setUpElevators(numOfElevators);
        setUpFloors(numOfFloors);
    }

    private void setUpElevators(int numOfElevators) {
        this.elevators = new Elevator[numOfElevators];
        for (int i = 0; i < numOfElevators; i++) {
            elevators[i] = new Elevator();
        }
    }

    private void setUpFloors(int numOfFloors) {
        this.floors = new Floor[numOfFloors];
        for (int i = 0; i < this.floors.length; i++) {
            if (i < FIRST_FIVE_FLOORS.length)
                this.floors[i] = new Floor(i, FIRST_FIVE_FLOORS[i]);
            else
                this.floors[i] = new Floor(i, rnd.nextInt(200));
        }
    }

    public Elevator[] getElevators() {
        return elevators;
    }

    public Floor[] getFloors() {
        return floors;
    }
}
