package bj.vargas.vargasfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) //reason = "Entidade não encontrada!")
public class EntityNotFound extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public EntityNotFound(final String message)  {
        super(message);
    }

}
