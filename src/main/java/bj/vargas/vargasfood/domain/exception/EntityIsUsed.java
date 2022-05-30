package bj.vargas.vargasfood.domain.exception;

public class EntityIsUsed extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public EntityIsUsed(String message)  {
        super(message);
    }

}
