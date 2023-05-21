package exceptions;

public class AuthorNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;


    public AuthorNotFoundException(String name) {
        super(String.format("Could not find author %s", name));
    }
}
