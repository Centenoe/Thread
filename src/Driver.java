import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Joe Matteson
 *
 * this class drives the whole application
 */
public class Driver {

    /**
     * the main entry point to the application
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Building building1 = new Building();
        ArrayList<Person> people = generatePeople();
        for (Person person : people)
            building1.getFloors()[0].personGetsOntoFloor(person);
        people.clear();
        mourningMode(building1);
        eveningMode(building1);
    }

    /*
    I did this lab a little differently than most would probably do it, but I still did the assignment.
    The reason I did it this way is because I wanted the try doing it across multiple buildings, or more floors,
    or more people, or more elevators.

    I did the assignment in the first part of the code but then the second is the "for fun code".
     */

    /**
     * this class represents the mourning mode of 1 building (the assignment).  Basically what this method does is
     * populates the elevator with twenty people then moves the elevator to each of the target floors while
     * incrementing the frustration level of each person.  It repeats this until there is no more people in
     * the lobby.
     *
     * @param building this is the building that the mourning mode will be acting on
     */
    public static void mourningMode(Building building) {
        int count = 0;
        Elevator elevator = building.getElevators()[0];
        Floor firstFloor = building.getFloors()[0];
        int[] frustrationLevels = new int[350];

        while (!firstFloor.isEmpty()) {

            //put people on elevator
            while (!firstFloor.isEmpty()) {
                if (elevator.isFull())
                    break;
                elevator.push(firstFloor.personWantsOffFloor());
            }

            //get everyone off elevator
            while (!elevator.isEmpty()) {
                moveToCertainFloor(building, 1, elevator.peek().getTargetFloor());
                if (elevator.peek() != null) {
                    if (checkIfPersonWantsOff(building, 1)) {
                        Person person = elevator.pop();
                        frustrationLevels[count] = person.getFrustrationLevel();
                        count += 1;
                        person.setFrustrationLevel(0);
                        building.getFloors()[elevator.getCurrentFloor()].personGetsOntoFloor(person);
                    }
                }
                if (!firstFloor.isEmpty())
                    firstFloor.incrementFrustration();
            }
        }
        System.out.println("The mean frustration level for the morning is: " + mean(frustrationLevels));
    }

    /**
     * This class represents the evening mode of a building.  The way this works
     * is that since the building already has the people in their home floors, the elevator
     * first finds a floor that does has people on it, but it does not go back to the same
     * twice. Then goes down to the first floor and lets everyone off.  Repeats this until
     * everyone is one the lobby floor.
     *
     * @param building the building instance that has all the people in their "home floors
     */
    public static void eveningMode(Building building) {
        Elevator elevator = building.getElevators()[0];
        Floor previousFloor = building.getFloors()[elevator.getCurrentFloor()];
        int[] frustrationLevels = new int[350];
        int count = 0;

        if (areFloorsEmpty(building))
            throw new IllegalArgumentException("building is empty");
        while (!areFloorsEmpty(building)) {
            switch (previousFloor.getFloorNum()) {
                case 0:
                    previousFloor = moveToCertainFloor(building, 1, 1);
                    break;
                case 1:
                    previousFloor = moveToCertainFloor(building, 1, 2);
                    break;
                case 2:
                    previousFloor = moveToCertainFloor(building, 1, 3);
                    break;
                case 3:
                    previousFloor = moveToCertainFloor(building, 1, 4);
                    break;
                case 4:
                    previousFloor = moveToCertainFloor(building, 1, 1);
                    break;
            }

            while (!previousFloor.isEmpty()) {
                if (elevator.isFull())
                    break;
                Person person = previousFloor.personWantsOffFloor();
                frustrationLevels[count] = person.getFrustrationLevel();
                count++;
                person.setFrustrationLevel(0);
                elevator.push(person);
            }

            //go back to first floor and let everyone off
            moveToCertainFloor(building, 1, 0);
            while (!elevator.isEmpty()) {
                building.getFloors()[0].personGetsOntoFloor(elevator.pop());
            }

            //increment everyone else's frustration level
            for (Floor floor : building.getFloors()) {
                if (!floor.isEmpty())
                    floor.incrementFrustration();
            }
        }
        System.out.println(building.getFloors()[0].getNumOfPeopleCurrentOnFloor());
        System.out.println("The mean frustration level for the eventing is: " + mean(frustrationLevels));

    }

    /**
     * check to see if the next person wants to get off the elevator
     *
     * @param building the building that holds the elevator
     * @param elevatorNum the elevator number (in the assignments case 1)
     * @return true if it is the target floor otherwise false
     */
    private static boolean checkIfPersonWantsOff(Building building, int elevatorNum ) {
        return building.getElevators()[elevatorNum - 1].peek().getTargetFloor() ==
                building.getElevators()[elevatorNum - 1].getCurrentFloor();
    }

    /**
     * sends the given elevator to the given floor
     *
     * @param building the building that holds the elevator
     * @return the floor that the elevator is currently on (will be 1)
     */
    private static Floor moveToCertainFloor(Building building, int elevatorNum, int floorNum) {
        Elevator elevator = building.getElevators()[elevatorNum - 1];
        while (elevator.getCurrentFloor() != floorNum) {
            if (elevator.getCurrentFloor() > floorNum) {
                elevator.previousFloor();
            } else {
                elevator.nextFloor();
            }
        }
        return building.getFloors()[floorNum];
    }

    /**
     * tells you if all the floors in the building are empty or not
     *
     * @param building the current building instance
     * @return true if all the floors are empty otherwise false
     */
    private static boolean areFloorsEmpty(Building building) {
        boolean result = true;
        for (int i = 1; i < building.getFloors().length; i++) {
            if (!building.getFloors()[i].isEmpty())
                result = false;
        }
        return result;
    }

    /**
     * calculate and prints the mean of a given array of frustration levels
     *
     * @param array frustration levels
     */
    private static double mean(int[] array) {

        int count = 0, sum = 0;
        while (count < array.length) {
            sum += array[count];
            count++;
        }
        return (double) sum / count;
    }

    /**
     * generates random people in a random order
     *
     * @return an array list of random people
     */
    private static ArrayList<Person> generatePeople() {
        ArrayList<Person> result = new ArrayList<>();
        int index = 0;
        while (index < 100) {
            result.add(new Person(index, 1));
            index++;
        }
        
        while (index < 165) {
            result.add(new Person(index, 2));
            index++;
        }
        
        while (index < 240) {
            result.add(new Person(index, 3));
            index++;
        }
        
        while (index < 350) {
            result.add(new Person(index, 4));
            index++;
        }
        Collections.shuffle(result);
        return result;
    }

    //fun code

    public static void funCode() {
        //have'nt pushed this code yet
    }
}
