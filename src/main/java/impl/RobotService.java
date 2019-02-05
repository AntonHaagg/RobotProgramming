package impl;

public interface RobotService {

    public static enum POTCUse {
        SUBTRACTION, ADDITION;
    }
    /**
     * Iterates a robots point of the compass direction
     * @return the potc direction
     */
    int iterate(int currentState, int iterationLength, POTCUse potcUse);

}
