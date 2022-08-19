package me.revilla.fullstackbp.service.imp;

import lombok.extern.slf4j.Slf4j;
import me.revilla.fullstackbp.dto.response.ApiResponse;
import me.revilla.fullstackbp.service.GeneralService;
import me.revilla.fullstackbp.util.Entity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * GeneralServiceImp
 *
 * @author Revilla Pool
 */
@Slf4j
@Service
public abstract class GeneralServiceImp<T, ID extends Long, E> implements GeneralService<T, ID> {

    private ModelMapper modelMapper;
    private Class<T> firstGeneric;
    private Class<E> thirdGeneric;

    @Autowired
    public final void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public final ModelMapper getModelMapper() {
        return this.modelMapper;
    }

    @SuppressWarnings("unchecked")
    public Class<T> getFirstGenericClass() {
        if (firstGeneric == null) {
            firstGeneric = (Class<T>) Objects.requireNonNull(GenericTypeResolver
                    .resolveTypeArguments(this.getClass(), GeneralServiceImp.class))[0]; //this.getClass() == subclass type
        }
        return firstGeneric;
    }

    @SuppressWarnings("unchecked")
    public Class<E> getThirdGenericClass() {
        if (thirdGeneric == null) {
            thirdGeneric = (Class<E>) Objects.requireNonNull(GenericTypeResolver
                    .resolveTypeArguments(this.getClass(), GeneralServiceImp.class))[2]; //this.getClass() == subclass type
        }
        return thirdGeneric;
    }

    @Override
    public List<T> findAll(Pageable pageable) {
        log.info("Calling the findAll method in "
                + this.getClass().getSimpleName());
        return this.getRepo().findAll(pageable)
                .getContent()
                .stream()
                .map(obj -> this.modelMapper.map(obj, this.getFirstGenericClass()))
                .collect(Collectors.toList());
    }

    @Override
    public T findOne(ID id) {
        log.info("Calling the findOne method in "
                + this.getClass().getSimpleName());
        E obj = Entity.getById(id, this.getRepo(),
                this.getThirdGenericClass());
        return this.modelMapper.map(obj, this.getFirstGenericClass());
    }

    @Override
    @Transactional
    public ApiResponse delete(ID id) {
        log.info("Calling the delete method in "
                + this.getClass().getSimpleName());
        E obj = Entity.getById(id, this.getRepo(),
                this.getThirdGenericClass());
        this.getRepo().delete(obj);
        return new ApiResponse(Boolean.TRUE, this.getThirdGenericClass().getSimpleName()
                + " deleted successfully");
    }

    public abstract JpaRepository<E, ID> getRepo();

}
