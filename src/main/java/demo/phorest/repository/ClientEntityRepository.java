package demo.phorest.repository;

import demo.phorest.enity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Client entity Repository
 */
public interface ClientEntityRepository extends JpaRepository<ClientEntity, UUID> {

    @Query(value="SELECT CLI.* \n" +
            "   FROM \n" +
            "  TCLIENT CLI, TAPPOINTMENT APP,  (SELECT * FROM TPURCHASE   UNION ALL SELECT * FROM  TSERVICE ) SRV\n" +
            "WHERE \n" +
            "  CLI.ID = APP.CLIENT_ID \n" +
            "AND\n" +
            "APP.ID = SRV.APPOINTMENT_ID\n" +
            "AND\n" +
            "CLI.BANNED =FALSE\n" +
            "AND\n" +
            "APP.START_TIME >= ?2\n"+
            "GROUP BY\n" +
            "CLI.ID\n" +
            "ORDER BY SUM(SRV.LOYALTY_POINTS) DESC\n" +
            "limit ?1  ", nativeQuery = true)
    List<ClientEntity> findTopClients(@Param("top_number") int top, @Param("fromDate") Date fromDate);
}