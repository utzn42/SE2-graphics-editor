package messages;

import canvas.Canvas;

import java.util.ArrayList;
import java.util.List;

public class ServerResponse {

    private String projectID;
    private List<Exception> errors;
    private Canvas canvas;

    public ServerResponse() {
        projectID = null;
        errors = new ArrayList<>();
        canvas = null;
    }

    public ServerResponse(String projectID) {
        this.projectID = projectID;
        errors = new ArrayList<>();
        canvas = null;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public List<String> getErrors() {
        List<String> errorStrings = new ArrayList<>();
        for (Exception error : errors) {
            errorStrings.add(error.getClass().getSimpleName() + ": " + error.getMessage());
        }
        return errorStrings;
    }

    public void setErrors(List<Exception> errors) {
        this.errors = errors;
    }

    public void addError(Exception error) {
        this.errors.add(error);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
