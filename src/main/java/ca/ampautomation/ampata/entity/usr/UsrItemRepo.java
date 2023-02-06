package ca.ampautomation.ampata.entity.usr;


import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UsrItemRepo extends CrudRepository<UsrItem, Long>, UsrItemRepoCustom {

}
