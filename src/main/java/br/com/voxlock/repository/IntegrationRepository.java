package br.com.voxlock.repository;

import br.com.voxlock.model.Integration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IntegrationRepository extends JpaRepository<Integration, Long> {

	@Query("SELECT i FROM Integration i WHERE i.id IN :ids")
	Set<Integration> findIntegrationsByIds(@Param("ids") List<Long> ids);

}
