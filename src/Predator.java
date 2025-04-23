public class Predator extends Creature {
    private String color = "\uD83D\uDC3A";
    private Coordinates coordinates;


    @Override
    public String getColor() {
        // super.getColor();
        return this.color;
    }

    @Override
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Predator(Coordinates coordinates) {//, String color
        //super(color, coordinates);
        this.coordinates = coordinates;
        this.color = "\uD83D\uDC3A";
    }



    public Coordinates makeMove(Predator predator, Coordinates targetCoordinates, Map map) {//

        int dx = (int) Math.signum(targetCoordinates.x - predator.coordinates.x);
        int dy = (int) Math.signum(targetCoordinates.y - predator.coordinates.y);
        //  return predator.coordinates=new Coordinates(predator.coordinates.x + dx, predator.coordinates.y + dy);

        Entyty nextTurnentyty = map.getEntyty(new Coordinates(predator.coordinates.x + dx, predator.coordinates.y + dy));

        if(predator.coordinates.x + dx>10  || predator.coordinates.y + dy>10
                || predator.coordinates.x +dx<1 || predator.coordinates.y +dy<1 ){
            return predator.coordinates = new Coordinates(predator.coordinates.x , predator.coordinates.y );
        }


        if (nextTurnentyty instanceof Rock || nextTurnentyty instanceof Predator) {//
            if (nextTurnentyty.getCoordinates().x > predator.coordinates.x) {
                return predator.coordinates = new Coordinates(predator.coordinates.x, predator.coordinates.y + 1);
            }
            if (nextTurnentyty.getCoordinates().y > predator.coordinates.y) {
                return predator.coordinates = new Coordinates(predator.coordinates.x + 1, predator.coordinates.y);
            }
            if (nextTurnentyty.getCoordinates().y < predator.coordinates.y) {
                return predator.coordinates = new Coordinates(predator.coordinates.x +1, predator.coordinates.y); // тут изменял
            }
            if (nextTurnentyty.getCoordinates().x < predator.coordinates.x) {
                return predator.coordinates = new Coordinates(predator.coordinates.x -1, predator.coordinates.y + 1);
            }


        }
        return predator.coordinates = new Coordinates(predator.coordinates.x + dx, predator.coordinates.y + dy);
    }
        // все что ниже работает точно также
//                int newPredCoordsX = predator.coordinates.x;
//        int newPredCoordsY = predator.coordinates.y;
//
//        if (predator.getCoordinates().x > targetCoordinates.x && predator.getCoordinates().y == targetCoordinates.y) {
//
//            newPredCoordsX = predator.coordinates.x - 1;
//            newPredCoordsY = predator.coordinates.y;
//        } else if (predator.getCoordinates().x < targetCoordinates.x && predator.getCoordinates().y == targetCoordinates.y) {
//
//            newPredCoordsX = predator.coordinates.x + 1;
//            newPredCoordsY = predator.coordinates.y;
//        } else if (predator.getCoordinates().x == targetCoordinates.x && predator.getCoordinates().y < targetCoordinates.y) {
//
//            newPredCoordsX = predator.coordinates.x;
//            newPredCoordsY = predator.coordinates.y + 1;
//        } else if (predator.getCoordinates().x == targetCoordinates.x && predator.getCoordinates().y > targetCoordinates.y) {
//
//            newPredCoordsX = predator.coordinates.x;
//            newPredCoordsY = predator.coordinates.y - 1;
//        } else if (predator.getCoordinates().x < targetCoordinates.x && predator.getCoordinates().y < targetCoordinates.y) {
//
//            newPredCoordsX = predator.coordinates.x + 1;
//            newPredCoordsY = predator.coordinates.y + 1;
//        } else if (predator.getCoordinates().x > targetCoordinates.x && predator.getCoordinates().y > targetCoordinates.y) {
//
//            newPredCoordsX = predator.coordinates.x - 1;
//            newPredCoordsY = predator.coordinates.y - 1;
//        } else if (predator.getCoordinates().x < targetCoordinates.x && predator.getCoordinates().y > targetCoordinates.y) {
//
//            newPredCoordsX = predator.coordinates.x + 1;
//            newPredCoordsY = predator.coordinates.y - 1;
//        } else if (predator.getCoordinates().x > targetCoordinates.x && predator.getCoordinates().y < targetCoordinates.y) {
//
//            newPredCoordsX = predator.coordinates.x - 1;
//            newPredCoordsY = predator.coordinates.y + 1;
//        }
//
//        return predator.coordinates = new Coordinates(newPredCoordsX, newPredCoordsY);





}
