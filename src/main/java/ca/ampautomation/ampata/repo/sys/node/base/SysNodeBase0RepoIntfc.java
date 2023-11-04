package ca.ampautomation.ampata.repo.sys.node.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface SysNodeBase0RepoIntfc<T, ID extends Serializable>
        extends Repository<T, ID>
        {

    Integer execPr_Upd_AllCalcVals_ForAllRows();

    Integer execPr_Del_ForDeletedRows();

}
