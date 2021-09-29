//exception for Sort class so that a value isn't input incorrectly when deciding which sort to execute

public class SortTypeException extends Exception {

    public SortTypeException() {}

    public SortTypeException(String msg) {
        super(msg);
    }
}
