package repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import entity.Goods;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, UUID> {
  @Query(value = "select _id ,name from goods where name like %?1", nativeQuery = true)
  Goods getGoodsByName(String name);

}
