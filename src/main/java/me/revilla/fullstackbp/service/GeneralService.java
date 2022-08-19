package me.revilla.fullstackbp.service;

import me.revilla.fullstackbp.dto.response.ApiResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * GeneralService
 *
 * @author Revilla Pool
 */
public interface GeneralService<T, ID extends Long> {

    List<T> findAll(Pageable pageable);

    T findOne(ID id);

    ApiResponse delete(ID id);

}
