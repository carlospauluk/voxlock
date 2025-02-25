package br.com.voxlock.repository;

import br.com.voxlock.model.Integration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationRepository extends JpaRepository<Integration, Long> {
}
