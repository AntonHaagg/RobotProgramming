package impl.builder;

import impl.BuilderResult;

public class BuilderResultImpl<ClassType> implements BuilderResult {

    private final ClassType object;
    private final int errorCode;
    private final String errorMessage;

    public BuilderResultImpl(ClassType object, int errorCode, String errorMessage) {
        this.object = object;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Object getObject() {
        return this.object;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
