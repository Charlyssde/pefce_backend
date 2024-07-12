package com.devx.software.ferias.Repositories.Dsp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devx.software.ferias.Entities.DSP.DSP;

@Repository
public interface DspRepository extends JpaRepository<DSP, Long> {


}
