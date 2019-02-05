package impl.service;

import impl.RobotService;

public class RobotServiceImpl implements RobotService {
    private static final String SUBTRACTION = "SUBTRACTION";
    private static final String ADDITION = "ADDITION";
    private int START = 0;

    public int iterate(int currentState, int iterationLength, POTCUse potcUse) {
        int tempState = currentState;
        switch(potcUse) {
            case SUBTRACTION:
                if(currentState <= 0){
                    return iterationLength-1;
                }
                tempState--;
                return tempState;
            case ADDITION:
                if(currentState == iterationLength-1){
                    return START;
                }
                tempState++;
                return tempState;
            default:
                return -1;
        }
    }

}
