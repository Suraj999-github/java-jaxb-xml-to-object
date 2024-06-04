package crud.demo.jaxb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crud.demo.jaxb.models.JaxbSample;
@Repository
public interface JaxbSampleRepository extends JpaRepository<JaxbSample, Long>{

}
