import java.util.*;

public class Simulation {


    public void render(Map map) {

        for (int y = 10; y >= 1; y--) {
            String line = "";
            for (int x = 1; x <=10 ; x++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (!map.map.containsKey(coordinates)) {
                    line += "\uD83D\uDFEB";
                   // map.setEntyty(coordinates,new EmptyCell());// тут хз надо ли
                } else {
                    line += map.getEntyty(coordinates).getColor();
                    //map.getEntyty(coordinates).coordinates=coordinates;
                }
            }
            System.out.println(line);
        }
    }

    public void startSimulation() {
        // Predator predator = new Predator(new Coordinates(3, 5));//,"\uD83D\uDC3A"
        Herbivore herbivore = new Herbivore(new Coordinates(8, 5));
        Map map = new Map();
//        Rock rock = new Rock(new Coordinates(2, 4));
        Rock rock1 = new Rock(new Coordinates(7, 4));
        Rock rock2 = new Rock(new Coordinates(8, 4));
        Rock rock3 = new Rock(new Coordinates(9, 4));
        Rock rock4 = new Rock(new Coordinates(10, 4));

        map.setEntyty(herbivore.getCoordinates(), herbivore);

//        Grass grass = new Grass(new Coordinates(3, 2));
        Grass grass1 = new Grass(new Coordinates(4, 2));
        Grass grass2 = new Grass(new Coordinates(7, 2));
        Grass grass3 = new Grass(new Coordinates(1, 10));
        map.setEntyty(grass1.getCoordinates(), grass1);
        map.setEntyty(grass2.getCoordinates(), grass2);
        map.setEntyty(grass3.getCoordinates(), grass3);
        map.setEntyty(rock1.getCoordinates(), rock1);
        map.setEntyty(rock2.getCoordinates(), rock2);
        map.setEntyty(rock3.getCoordinates(), rock3);
        map.setEntyty(rock4.getCoordinates(), rock4);
        //   Herbivore herbivor1 = new Herbivore(new Coordinates(3, 1));
        //  map.setEntyty(herbivor1.getCoordinates(),herbivor1);


        Simulation mapRenderer = new Simulation();
        //  map.setEntyty(predator.getCoordinates(), predator);


        //  map.setEntyty(grass.getCoordinates(), grass);
        // Coordinates herbivorTarget = poisk(grass, map);


        // map.setEntyty(herbivore.makeMove(herbivore,grass.getCoordinates(),map), herbivore);
        mapRenderer.render(map);
        int turnCount = 0;
        int interval = 500;
        boolean condition = true;
        int iterationCount = 0;
        do {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //map.map.clear();

            System.out.println("текущие"+ herbivore.getCoordinates().x+" "+herbivore.getCoordinates().y);

            Coordinates herbivoreTarget = poisk(herbivore, map);
            map.setEntyty(herbivore.makeMove(herbivore, herbivoreTarget, map), herbivore);
//              Coordinates predatorTarget = poisk(predator, map);
//               map.setEntyty(predator.makeMove(predator, predatorTarget,map), predator);//,poisk(predator,map)
            herbivore.eatGrass(herbivoreTarget, herbivore.getCoordinates(), map);


            mapRenderer.render(map);
            System.out.println();
        } while (condition);


    }


    public Coordinates poisk(Entyty creature, Map m) {

        ArrayDeque<Coordinates> queue = new ArrayDeque<>();

        Coordinates checkCoordinates1 = new Coordinates(creature.getCoordinates().x + 1, creature.getCoordinates().y);
        Coordinates checkCoordinates2 = new Coordinates(creature.getCoordinates().x + 1, creature.getCoordinates().y + 1);
        Coordinates checkCoordinates3 = new Coordinates(creature.getCoordinates().x, creature.getCoordinates().y + 1);
        Coordinates checkCoordinates4 = new Coordinates(creature.getCoordinates().x - 1, creature.getCoordinates().y + 1);
        Coordinates checkCoordinates5 = new Coordinates(creature.getCoordinates().x - 1, creature.getCoordinates().y);
        Coordinates checkCoordinates6 = new Coordinates(creature.getCoordinates().x - 1, creature.getCoordinates().y - 1);
        Coordinates checkCoordinates7 = new Coordinates(creature.getCoordinates().x, creature.getCoordinates().y - 1);
        Coordinates checkCoordinates8 = new Coordinates(creature.getCoordinates().x + 1, creature.getCoordinates().y - 1);

        queue.add(checkCoordinates1);
        queue.add(checkCoordinates2);
        queue.add(checkCoordinates3);
        queue.add(checkCoordinates4);
        queue.add(checkCoordinates5);
        queue.add(checkCoordinates6);
        queue.add(checkCoordinates7);
        queue.add(checkCoordinates8);

        HashSet<Coordinates> visited = new HashSet<>();
        Coordinates targetCoordinates= new Coordinates(0,0);
        while (!queue.isEmpty()) {

            queue.removeFirst();

            if (m.emptyCoordainates(queue.getFirst()) && m.getEntyty(queue.getFirst()) instanceof Herbivore ||  m.getEntyty(queue.getFirst()) instanceof Grass  ) {
               targetCoordinates= new Coordinates(queue.getFirst().x, queue.getFirst().y);
               break;

            } else {
                visited.add(queue.getFirst());
                if (!visited.contains(new Coordinates(queue.getFirst().x + 1, queue.getFirst().y))) {
                    queue.add(new Coordinates(queue.getFirst().x + 1, queue.getFirst().y));
                 //   System.out.println("1");
                }
                if (!visited.contains(new Coordinates(queue.getFirst().x + 1, queue.getFirst().y + 1))) {
                    queue.add(new Coordinates(queue.getFirst().x + 1, queue.getFirst().y + 1));
                   // System.out.println("2");
                }
                if (!visited.contains(new Coordinates(queue.getFirst().x, queue.getFirst().y + 1))) {
                    queue.add(new Coordinates(queue.getFirst().x, queue.getFirst().y + 1));
                  //  System.out.println("3");
                }
                if (!visited.contains(new Coordinates(queue.getFirst().x - 1, queue.getFirst().y + 1))) {
                    queue.add(new Coordinates(queue.getFirst().x - 1, queue.getFirst().y + 1));
                   // System.out.println("4");
                }
                if (!visited.contains(new Coordinates(queue.getFirst().x - 1, queue.getFirst().y))) {
                    queue.add(new Coordinates(queue.getFirst().x - 1, queue.getFirst().y));
                  //  System.out.println("5");
                }
                if (!visited.contains(new Coordinates(queue.getFirst().x - 1, queue.getFirst().y - 1))) {
                    queue.add(new Coordinates(queue.getFirst().x - 1, queue.getFirst().y - 1));
                  //  System.out.println("6");
                }
                if (!visited.contains(new Coordinates(queue.getFirst().x, queue.getFirst().y - 1))) {
                    queue.add(new Coordinates(queue.getFirst().x, queue.getFirst().y - 1));
                  //  System.out.println("7");
                }
                if (!visited.contains(new Coordinates(queue.getFirst().x + 1, queue.getFirst().y - 1))) {
                    queue.add(new Coordinates(queue.getFirst().x + 1, queue.getFirst().y - 1));
                 //   System.out.println("8");
                }


            }//queue.removeFirst();


        }
        //System.out.println("x "+targetCoordinates.x+" y "+ targetCoordinates.y);
        return  targetCoordinates;

    }
}
