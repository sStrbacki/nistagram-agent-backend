package rs.ac.uns.ftn.nistagram.exceptions;

public class EntityAlreadyExistsException extends ApplicationException {

    public EntityAlreadyExistsException(){}

    public EntityAlreadyExistsException(String message){
        super(message);
    }
}
