package impl;

public interface BuilderResult<ClassType> {
    /**
     * @return returns an object depending on if the build result went ok or not
     */
    public ClassType getObject();

    /**
     * @return returns an error message depending on if the build result went ok or not
     */
    public String getErrorMessage();

    /**
     * @return returns an error code depending on if the build result went ok or not
     */
    public int getErrorCode();

}
