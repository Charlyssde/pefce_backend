package com.devx.software.ferias.Repositories.Dsp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devx.software.ferias.Entities.DSP.DSP;

@Repository
public interface DspRepository extends JpaRepository<DSP, Long> {

    @Query("SELECT dsp FROM DSP dsp WHERE dsp.descripcion LIKE %:search% OR dsp.concepto LIKE %:search%")
    List<DSP> search(@Param("search") String search);

}
