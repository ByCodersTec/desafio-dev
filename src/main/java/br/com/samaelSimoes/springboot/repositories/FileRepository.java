package br.com.samaelSimoes.springboot.repositories;

import br.com.samaelSimoes.springboot.entities.File;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FileRepository extends PagingAndSortingRepository<File, Integer> {
    public Iterable<File> findByNmStoreContainingIgnoreCase(String Store);
}
