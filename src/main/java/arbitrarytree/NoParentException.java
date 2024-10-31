package arbitrarytree;

public class NoParentException extends Exception {
    public NoParentException() {
        super("The root node does not have a parent.");
    }
}
