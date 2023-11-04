package ca.ampautomation.ampata.repo.usr.node.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface UsrNodeBase0RepoIntfc<T, ID extends Serializable>
        extends Repository<T, ID>
        {

    Integer execPr_Upd_AllCalcVals_ForAllRows();

    Integer execPr_Del_ForDeletedRows();

}
