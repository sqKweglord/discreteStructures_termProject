/**
 * Class: SortTypeException
 * @Author Harrison Brown
 * @Version 1.1
 * Started Writing:
 * Version Date:
 *
 * Description:
 * Exception for Sort class so that a value isn't input incorrectly when deciding which sort to execute
 */

public class SortTypeException extends Exception {
    public SortTypeException(String msg) {
        super(msg);
    }
}
