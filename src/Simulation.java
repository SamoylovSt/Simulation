import java.util.*;

public class Simulation {

    public void render(Map map) {

        for (int ROW = 10; ROW >= 1; ROW--) {
            String line = "";
            for (int COLUMN = 1; COLUMN <=10 ; COLUMN++) {
                Coordinates coordinates = new Coordinates(COLUMN, ROW);
                if (!map.map.containsKey(coordinates)) {
                    line += "\uD83D\uDFEB"; //"\uD83D\uDFEB";
                   // map.setEntyty(coordinates,new EmptyCell());// тут хз надо ли
                } else {
                    line += map.getEntyty(coordinates).getColor();
                    //map.getEntyty(coordinates).coordinates=coordinates;
                    //System.out.println("Отрисовка: x=" + x + ", y=" + y + ", entity=" + map.getEntyty(coordinates));
                }
            }
            System.out.println(line);
        }
    }

    public void initStartAction(Map map){
        Grass grass2 = new Grass(new Coordinates(5, 10));
        Grass grass3 = new Grass(new Coordinates(1, 7));
        map.setEntyty(grass2.getCoordinates(), grass2);
        map.setEntyty(grass3.getCoordinates(), grass3);

        Rock rock= new Rock(new Coordinates(4,8));
        Rock rock1= new Rock(new Coordinates(6,5));
        Rock rock2= new Rock(new Coordinates(5,10));
        map.setEntyty(rock.getCoordinates(),rock);
        map.setEntyty(rock1.getCoordinates(),rock1);
        map.setEntyty(rock2.getCoordinates(),rock2);

    }

    public void startSimulation() {
        Map map = new Map();
        List<Herbivore> rabits= new ArrayList<>();

        Herbivore herbivore1 = new Herbivore(new Coordinates(1, 1));
        map.setEntyty(herbivore1.getCoordinates(), herbivore1);

        rabits.add(herbivore1);

        initStartAction(map);

         Predator predator = new Predator(new Coordinates(8, 8));
         map.setEntyty(predator.getCoordinates(), predator);

        int turnCount = 0;
        int interval = 500;
        boolean condition = true;
        int iterationCount = 0;
        while (condition) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            turnCount++;





            Coordinates predatorTarget = poisk(predator, map);
            predator.makeMove(predator, predatorTarget, map);
            predator.eatHerbivore(predatorTarget,predator.getCoordinates(),map,rabits);




            render(map);
            map.reMap(nextTurn(map.map));

            rabbitsWalk(turnCount,map,rabits);
             setRandomEntyty(map, turnCount);
            System.out.println(turnCount);
        }


    }

    public void rabbitsWalk(int turnCount, Map map,List<Herbivore> rabits){
        if (turnCount %2 == 0) {
            Random random = new Random();
            int COLUMN = random.nextInt(10) + 1;
            int ROW = random.nextInt(10) + 1;
            Herbivore newRabit = new Herbivore(new Coordinates(COLUMN, ROW));
            map.setEntyty(new Coordinates(COLUMN, ROW), newRabit);
            rabits.add(newRabit);
        }
        //зайцы из списка ходят по очереди
        for (Herbivore rabit : rabits) {
            Coordinates rabitTarget = poisk(rabit, map);
            rabit.makeMove(rabit, rabitTarget, map);
            rabit.eatGrass(rabitTarget, rabit.getCoordinates(), map);

        }
    }


    public void setRandomEntyty(Map map, int turtcount) {
        Random random = new Random();
        int x = random.nextInt(10) + 1;
        int y = random.nextInt(10) + 1;
        int z = random.nextInt(10) + 1;
        int c = random.nextInt(10) + 1;

        if (!map.emptyCoordainates(new Coordinates(x, y)) && turtcount % 1 == 0) {
            Grass gras = new Grass(new Coordinates(x, y));
            map.setEntyty(new Coordinates(x, y), gras);
            Grass gras1 = new Grass(new Coordinates(z, c));
            map.setEntyty(new Coordinates(z, c), gras1);
        }
    }



    public Coordinates poisk(Entyty creature, Map m) {

        ArrayDeque<Coordinates> queue = new ArrayDeque<>();

        Coordinates checkCoordinates1 = new Coordinates(creature.getCoordinates().COLUMN + 1, creature.getCoordinates().ROW);
        Coordinates checkCoordinates2 = new Coordinates(creature.getCoordinates().COLUMN + 1, creature.getCoordinates().ROW + 1);
        Coordinates checkCoordinates3 = new Coordinates(creature.getCoordinates().COLUMN, creature.getCoordinates().ROW + 1);
        Coordinates checkCoordinates4 = new Coordinates(creature.getCoordinates().COLUMN - 1, creature.getCoordinates().ROW + 1);
        Coordinates checkCoordinates5 = new Coordinates(creature.getCoordinates().COLUMN - 1, creature.getCoordinates().ROW);
        Coordinates checkCoordinates6 = new Coordinates(creature.getCoordinates().COLUMN - 1, creature.getCoordinates().ROW - 1);
        Coordinates checkCoordinates7 = new Coordinates(creature.getCoordinates().COLUMN, creature.getCoordinates().ROW - 1);
        Coordinates checkCoordinates8 = new Coordinates(creature.getCoordinates().COLUMN + 1, creature.getCoordinates().ROW - 1);

        queue.add(checkCoordinates1);
        queue.add(checkCoordinates2);
        queue.add(checkCoordinates3);
        queue.add(checkCoordinates4);
        queue.add(checkCoordinates5);
        queue.add(checkCoordinates6);
        queue.add(checkCoordinates7);
        queue.add(checkCoordinates8);

        HashSet<Coordinates> visited = new HashSet<>();
        Coordinates targetCoordinates = new Coordinates(0, 0);

        int[][] directions = {
                {+1, +0}, {+0, -1}, {+1, +1}, {+1, -1},
                {+0, +1}, {-1, +1}, {-1, +0}, {-1, -1}
        };
        while (!queue.isEmpty()) {
            queue.removeFirst();
            Coordinates nextCoordinates = queue.getFirst();

                if (m.emptyCoordainates(nextCoordinates) && creature instanceof Herbivore && m.getEntyty(nextCoordinates) instanceof Grass) {
                    targetCoordinates = new Coordinates(nextCoordinates.COLUMN, nextCoordinates.ROW);
                    break;
                }
                if (m.emptyCoordainates(nextCoordinates) && creature instanceof Predator && m.getEntyty(nextCoordinates) instanceof Herbivore) {
                    targetCoordinates = new Coordinates(nextCoordinates.COLUMN, nextCoordinates.ROW);
                    break;
                } else {
                    visited.add(nextCoordinates);

                    for (int i = 0; i < directions.length; i++) {
                        int newRow = directions[i][1];
                        int newCol = directions[i][0];
                        if (!visited.contains(new Coordinates(nextCoordinates.COLUMN + newCol, nextCoordinates.ROW + newRow))) {
                            queue.add(new Coordinates(nextCoordinates.COLUMN + newCol, nextCoordinates.ROW + newRow));

                        }
                    }
                }

        }
        return targetCoordinates;
    }



    public  HashMap<Coordinates,Entyty> nextTurn(HashMap<Coordinates,Entyty> map){
        HashMap<Coordinates,Entyty> newMap = new HashMap<>();

        for (Coordinates key: map.keySet()){
            Entyty value=map.get(key);
            newMap.put(value.getCoordinates(),value);
        }
        return newMap;
    }


}
