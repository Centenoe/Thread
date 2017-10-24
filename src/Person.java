public class Person {

    private int id;
    private int frustrationLevel = 0;
    private int targetFloor;

    public Person(int id, int targetFloor){
        this.id = id;
        this.targetFloor = targetFloor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrustrationLevel() {
        return frustrationLevel;
    }

    public void setFrustrationLevel(int frustrationLevel) {
        this.frustrationLevel = frustrationLevel;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", frustrationLevel=" + frustrationLevel +
                ", targetFloor=" + targetFloor +
                '}';
    }
}
