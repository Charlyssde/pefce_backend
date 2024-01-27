package com.devx.software.ferias.Repositories.Files;

import com.devx.software.ferias.Entities.Files.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<FileEntity, Long> {
}
