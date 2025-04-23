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
        if (herbCoordinates.equals(targetCoordinates)) {
            map.deleteEntyty(targetCoordinates);
        }

    }



    public Coordinates makeMove(Herbivore herbivore, Coordinates targetCoordinates, Map map) {//


        int dx = (int) Math.signum(targetCoordinates.x - herbivore.coordinates.x);
        int dy = (int) Math.signum(targetCoordinates.y - herbivore.coordinates.y);


        Entyty nextTurnentyty = map.getEntyty(new Coordinates(herbivore.coordinates.x + dx, herbivore.coordinates.y + dy));
        System.out.println("следующие"+(herbivore.coordinates.x + dx)+" "+(herbivore.coordinates.y + dy));
        //поедание травы
//      if(nextTurnentyty instanceof Grass){
//          map.map.remove(herbivore.coordinates.x + dx, herbivore.coordinates.y + dy);
//      }

// это стоп при границе карты
        if (herbivore.coordinates.x +dx>10){
            dx=0;
        }
        if (herbivore.coordinates.x +dx<1){
            dx=0;
        }
        if (herbivore.coordinates.y +dy>10){
            dy=0;
        }
        if (herbivore.coordinates.y +dy<1){
            dy=0;
        }

//       if(herbivore.coordinates.x + dx>10  || herbivore.coordinates.y + dy>10
//            || herbivore.coordinates.x +dx<1 || herbivore.coordinates.y +dy<1 ){
//            return herbivore.coordinates = new Coordinates(herbivore.coordinates.x , herbivore.coordinates.y );
//        }

        // разбить на два метода
        // это обход
            if (nextTurnentyty instanceof Rock || nextTurnentyty instanceof Herbivore ) {//
                if ( nextTurnentyty.getCoordinates().x > herbivore.coordinates.x) {
                    return herbivore.coordinates = new Coordinates(herbivore.coordinates.x, herbivore.coordinates.y + 1);
                }
                if ( nextTurnentyty.getCoordinates().y > herbivore.coordinates.y) {
                    return herbivore.coordinates = new Coordinates(herbivore.coordinates.x + 1, herbivore.coordinates.y);
                }
                if ( nextTurnentyty.getCoordinates().y < herbivore.coordinates.y) {
                    return herbivore.coordinates = new Coordinates(herbivore.coordinates.x - 1, herbivore.coordinates.y);
                }
                if ( nextTurnentyty.getCoordinates().x < herbivore.coordinates.x) {
                    return herbivore.coordinates = new Coordinates(herbivore.coordinates.x-1, herbivore.coordinates.y + 1);
                }

            }





        return herbivore.coordinates = new Coordinates(herbivore.coordinates.x + dx, herbivore.coordinates.y + dy);


    }
    // все что ниже работает точно также

//        int newHerbCoordsX = herbivore.coordinates.x;
//        int newHerbCoordsY = herbivore.coordinates.y;
//
//        if (herbivore.getCoordinates().x > targetCoordinates.x && herbivore.getCoordinates().y == targetCoordinates.y) {
//
//            newHerbCoordsX = herbivore.coordinates.x - 1;
//            newHerbCoordsY = herbivore.coordinates.y;
//        } else if (herbivore.getCoordinates().x < targetCoordinates.x && herbivore.getCoordinates().y == targetCoordinates.y) {
//
//            newHerbCoordsX = herbivore.coordinates.x + 1;
//            newHerbCoordsY = herbivore.coordinates.y;
//        } else if (herbivore.getCoordinates().x == targetCoordinates.x && herbivore.getCoordinates().y < targetCoordinates.y) {
//
//            newHerbCoordsX = herbivore.coordinates.x;
//            newHerbCoordsY = herbivore.coordinates.y + 1;
//        } else if (herbivore.getCoordinates().x == targetCoordinates.x && herbivore.getCoordinates().y > targetCoordinates.y) {
//
//            newHerbCoordsX = herbivore.coordinates.x;
//            newHerbCoordsY = herbivore.coordinates.y - 1;
//        } else if (herbivore.getCoordinates().x < targetCoordinates.x && herbivore.getCoordinates().y < targetCoordinates.y) {
//
//            newHerbCoordsX = herbivore.coordinates.x + 1;
//            newHerbCoordsY = herbivore.coordinates.y + 1;
//        } else if (herbivore.getCoordinates().x > targetCoordinates.x && herbivore.getCoordinates().y > targetCoordinates.y) {
//
//            newHerbCoordsX = herbivore.coordinates.x - 1;
//            newHerbCoordsY = herbivore.coordinates.y - 1;
//        } else if (herbivore.getCoordinates().x < targetCoordinates.x && herbivore.getCoordinates().y > targetCoordinates.y) {
//
//            newHerbCoordsX = herbivore.coordinates.x + 1;
//            newHerbCoordsY = herbivore.coordinates.y - 1;
//        } else if (herbivore.getCoordinates().x > targetCoordinates.x && herbivore.getCoordinates().y < targetCoordinates.y) {
//
//            newHerbCoordsX = herbivore.coordinates.x - 1;
//            newHerbCoordsY = herbivore.coordinates.y + 1;
//        }
//
//        return herbivore.coordinates = new Coordinates(newHerbCoordsX, newHerbCoordsY);
//      }
}

