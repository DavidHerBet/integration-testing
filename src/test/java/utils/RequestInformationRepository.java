package utils;

import com.dherbet.integration.testing.batch.RequestInformation;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@TestComponent
@Repository
public interface RequestInformationRepository extends JpaRepository<RequestInformation, Long> {

}
