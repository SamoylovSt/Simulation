import java.util.ArrayDeque;
import java.util.HashSet;

public class Herbivore extends Creature {
    private String color = "\uD83D\uDC30";
    private Coordinates coordinates;

    @Override
    public String getColor() {

        return this.color;
    }

    @Override
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Herbivore(Coordinates coordinates) {
//        super();
        this.coordinates = coordinates;
        this.color = "\uD83D\uDC30";
    }

    public void eatGrass(Coordinates targetCoordinates, Coordinates herbCoordinates, Map map) {
        if (herbCoordinates.equals(targetCoordinates) && map.getEntyty(targetCoordinates) instanceof Grass) {

            map.deleteEntyty(targetCoordinates);
        }

    }



    public Coordinates makeMove(Herbivore herbivore, Coordinates targetCoordinates, Map map) {//
        int dx = (int) Math.signum(targetCoordinates.COLUMN - herbivore.coordinates.COLUMN);
        int dy = (int) Math.signum(targetCoordinates.ROW - herbivore.coordinates.ROW);

        Entyty nextTurnentyty = map.getEntyty(new Coordinates(herbivore.coordinates.COLUMN + dx, herbivore.coordinates.ROW + dy));


// это стоп при границе карты
        if (herbivore.coordinates.COLUMN +dx>10){
            dx=0;
        }
        if (herbivore.coordinates.COLUMN +dx<1){
            dx=0;
        }
        if (herbivore.coordinates.ROW +dy>10){
            dy=0;
        }
        if (herbivore.coordinates.ROW +dy<1){
            dy=0;
        }
        // разбить на два метода
        // это обход
            if (nextTurnentyty instanceof Rock ) {//
                if ( nextTurnentyty.getCoordinates().COLUMN > herbivore.coordinates.COLUMN) {
                    return herbivore.coordinates = new Coordinates(herbivore.coordinates.COLUMN, herbivore.coordinates.ROW + 1);
                }
                if ( nextTurnentyty.getCoordinates().ROW > herbivore.coordinates.ROW) {
                    return herbivore.coordinates = new Coordinates(herbivore.coordinates.COLUMN + 1, herbivore.coordinates.ROW);
                }
                if ( nextTurnentyty.getCoordinates().ROW < herbivore.coordinates.ROW) {
                    return herbivore.coordinates = new Coordinates(herbivore.coordinates.COLUMN - 1, herbivore.coordinates.ROW);
                }
                if ( nextTurnentyty.getCoordinates().COLUMN < herbivore.coordinates.COLUMN) {
                    return herbivore.coordinates = new Coordinates(herbivore.coordinates.COLUMN -1, herbivore.coordinates.ROW + 1);
                }

            }
        return herbivore.coordinates = new Coordinates(herbivore.coordinates.COLUMN + dx, herbivore.coordinates.ROW + dy);
    }


    public Coordinates obhod(Creature creature,Map m){
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
                {+1, +0}, {+0, -1}, {+1, +1}, {+1, -1}, {+0, +1}, {-1, +1}, {-1, +0}, {-1, -1}
        };
        while (!queue.isEmpty()) {
            queue.removeFirst();

            if (m.emptyCoordainates(queue.getFirst()) && creature instanceof Herbivore && m.getEntyty(queue.getFirst()) instanceof Grass) {
                targetCoordinates = new Coordinates(queue.getFirst().COLUMN, queue.getFirst().ROW);
                break;
            }
            if (m.emptyCoordainates(queue.getFirst()) && creature instanceof Predator && m.getEntyty(queue.getFirst()) instanceof Herbivore) {
                targetCoordinates = new Coordinates(queue.getFirst().COLUMN, queue.getFirst().ROW);
                break;
            }
            else {
                visited.add(queue.getFirst());

                for (int i = 0; i < directions.length; i++) {
                    int newRow = directions[i][1];
                    int newCol = directions[i][0];
                    if (!visited.contains(new Coordinates(queue.getFirst().COLUMN + newCol, queue.getFirst().ROW + newRow))) {
                        queue.add(new Coordinates(queue.getFirst().COLUMN + newCol, queue.getFirst().ROW + newRow));

                    }
                }
            }

        }
        return targetCoordinates;

    }

}

