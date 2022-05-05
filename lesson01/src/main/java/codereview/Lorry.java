package codereview;

class Lorry extends Car implements Moveable, Stopable { //implements + интерфейсы

    @Override //переопределить
    public void move() {
        System.out.println("Car is moving");
    }
    @Override //переопределить
    public void stop() {
        System.out.println("Car is stop");
    }

    @Override //реализовать метод open()
    void open() {
        System.out.println("Car is open");
    }

}
