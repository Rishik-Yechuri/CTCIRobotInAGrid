import java.util.*;

public class CTCIRobotInAGrid {
    public static void main(String[] args) {
        try {
            CTCIRobotInAGrid obj = new CTCIRobotInAGrid();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(String[] args) {
        //Creates an empty maze
        boolean[][] holdObstacles = new boolean[5][5];
        for (int y = 0; y < holdObstacles.length; y++) {
            for (int x = 0; x < holdObstacles[0].length; x++) {
                holdObstacles[y][x] = true;
            }
        }
        //Adds obstacles
        holdObstacles[1][1] = false;
        holdObstacles[3][2] = false;
        holdObstacles[4][3] = false;
        holdObstacles[2][4] = false;
        //Calls the 'findPath' function and saves the result
        String[][] completedMap = findPath(holdObstacles);
        //Prints out the result
        for(int y=0;y< completedMap.length;y++){
            System.out.println(Arrays.toString(completedMap[y]));
        }
    }

    public String[][] findPath(boolean[][] currentMap) {
        HashMap<List<Integer>, Boolean> holdTrueCoords = new HashMap<List<Integer>, Boolean>();
        Queue<List<Integer>> coordsToExplore = new LinkedList<List<Integer>>();
        coordsToExplore.add(
                Collections.unmodifiableList(Arrays.asList(currentMap.length - 1, currentMap[0].length - 1)));
        while (!coordsToExplore.isEmpty()) {
            List<Integer> currentCoords = coordsToExplore.remove();
            holdTrueCoords.put(Collections.unmodifiableList(currentCoords), true);
            if (currentCoords.get(0) >= 1 && !holdTrueCoords.containsKey(Arrays.asList(currentCoords.get(0) - 1, currentCoords.get(1))) /*!= true*/ && currentMap[currentCoords.get(0) - 1][currentCoords.get(1)] != false) {
                coordsToExplore.add(Collections.unmodifiableList(Arrays.asList(currentCoords.get(0) - 1, currentCoords.get(1))));
            }
            if (currentCoords.get(1) >= 1 && !holdTrueCoords.containsKey(Arrays.asList(currentCoords.get(0), currentCoords.get(1) - 1)) /*!= true*/ && currentMap[currentCoords.get(0)][currentCoords.get(1) - 1] != false) {
                coordsToExplore.add(Collections.unmodifiableList(Arrays.asList(currentCoords.get(0), currentCoords.get(1) - 1)));
            }
        }
        boolean keepGoing = true;
        int yCoord = 0;
        int xCoord = 0;
        int height = currentMap.length;
        int width = currentMap[0].length;
        String[][] pathMap = new String[height][width];
        for(int y =0;y<height;y++){
            for(int x=0;x<width;x++){
                if(!currentMap[y][x]){
                    pathMap[y][x] = "W";
                }else{
                    pathMap[y][x] = "-";
                }
            }
        }
        while (keepGoing) {
            pathMap[yCoord][xCoord] = "P";
            if(yCoord == height-1 && xCoord == width-1){
                keepGoing = false;
            }else {
                if (holdTrueCoords.containsKey(Arrays.asList(yCoord + 1, xCoord))) {
                    yCoord++;
                } else if (holdTrueCoords.containsKey(Arrays.asList(yCoord, xCoord + 1))) {
                    xCoord++;
                }
            }
        }
        return pathMap;
    }
}
