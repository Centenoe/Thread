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
        mourningMode(building1, people);
        eveningMode(building1);
    }

    /**
     * this class represents the mourning mode of 1 building (the assignment).  Basically what this method does is
     * populates the elevator with twenty people then moves the elevator to each of the target floors while
     * incrementing the frustration level of each person.  It repeats this until there is no more people in
     * the lobby.
     *
     * @param building this is the building that the mourning mode will be acting on
     * @param people this is the list of people waiting in the lobby
     */
    public static void mourningMode(Building building, ArrayList<Person> people) {
        int peopleSize = people.size(), count = 0;
        int[] frustrationLevels = new int[350];
        while (people.size() > 0) {
            if (peopleSize - 20 <= 0) {
                peopleSize -= (peopleSize - 20);
            }
            while (people.size() > peopleSize - 20) {
                building.getElevators()[0].push(people.get(people.size() - 1));
                people.remove(people.size() - 1);
            }
            peopleSize -= 20;

            do {
                if (building.getElevators()[0].peek() != null) {
                    if (checkIfPersonWantsOff(building, 1)) {
                        Person person = building.getElevators()[0].pop();
                        //System.out.println(person.toString());
                        frustrationLevels[count] = person.getFrustrationLevel();
                        count += 1;
                        building.getFloors()[building.getElevators()[0].getCurrentFloor() - 1].personGetsOntoFloor(person);
                    }
                }
                if (building.getElevators()[0].isEmpty())
                    continue;
                if (building.getElevators()[0].getCurrentFloor() > building.getElevators()[0].peek().getTargetFloor())
                    building.getElevators()[0].previousFloor();
                else
                    building.getElevators()[0].nextFloor();
            } while (!building.getElevators()[0].isEmpty());
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
        Floor currentFloor, previousFloor;
        do {
            //still need to make sure that the elevator is not going to the same floor
            //still need frustration level
            while (building.getFloors()[elevator.getCurrentFloor()].isEmpty()) { //make sure it works
                if (elevator.getCurrentFloor() >= 5) {
                    backToFirstFloor(building, 1);
                    elevator.nextFloor();
                } else
                    elevator.nextFloor();
            }
            currentFloor = building.getFloors()[elevator.getCurrentFloor()];
            previousFloor = currentFloor;
            while (!currentFloor.isEmpty()) {
                if (elevator.isFull())
                    break;
                elevator.push(currentFloor.personWantsOffFloor());
            }
            currentFloor = backToFirstFloor(building, 1);
            while (!elevator.isEmpty()) {
                currentFloor.personGetsOntoFloor(elevator.pop());
                //System.out.println(currentFloor.getNumOfPeopleCurrentOnFloor());
            }
        } while (!areFloorsEmpty(building));
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
     * sends the given elevator to the first floor
     *
     * @param building the building that holds the elevator
     * @return the floor that the elevator is currently on (will be 1)
     */
    public static Floor backToFirstFloor(Building building, int elevatorNum) {
        while (building.getElevators()[elevatorNum - 1].getCurrentFloor() > 1) {
            building.getElevators()[elevatorNum - 1].previousFloor();
        }
        return building.getFloors()[0];
    }

    /**
     * tells you if all the floors in the building are empty or not
     *
     * @param building the current building instance
     * @return true if all the floors are empty otherwise false
     */
    public static boolean areFloorsEmpty(Building building) {
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
    public static double mean(int[] array) {

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
    public static ArrayList<Person> generatePeople() {
        ArrayList<Person> result = new ArrayList<>();
        int index = 0;
        while (index < 100) {
            result.add(new Person(index, 2));
            index++;
        }
        
        while (index < 165) {
            result.add(new Person(index, 3));
            index++;
        }
        
        while (index < 240) {
            result.add(new Person(index, 4));
            index++;
        }
        
        while (index < 350) {
            result.add(new Person(index, 5));
            index++;
        }
        Collections.shuffle(result);
        return result;
    }
}
