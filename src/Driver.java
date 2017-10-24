import java.util.ArrayList;
import java.util.Collections;

public class Driver {
    public static void main(String[] args) {
        Building building1 = new Building();
        ArrayList<Person> people = generatePeople();
        mourningMode(building1, people);
    }

    public static boolean checkIfPersonWantsOff(Building building, int elevatorNum ) {
        return building.getElevators()[elevatorNum - 1].peek().getTargetFloor() ==
                building.getElevators()[elevatorNum - 1].getCurrentFloor();
    }

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
                        System.out.println(person.toString());
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

    public static void eveningMode(Building building) {
        //get 20 people on the second floor off and drop them off at the first floor
        //get 20 people off the third floor and drop them off at the first floor
        //repeat this: grabbing 20 people off the next floor
    }

    /**
     * calculate and prints the mean of a given array of frustration levels.
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
