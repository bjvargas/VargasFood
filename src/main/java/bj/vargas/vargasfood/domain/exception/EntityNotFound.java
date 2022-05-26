package bj.vargas.vargasfood.domain.exception;

public class EntityNotFound extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public EntityNotFound(final String message)  {
        super(message);
    }

}
