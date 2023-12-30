package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenUnion;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenUnion0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenUnionType;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenUnion0Service;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeGenUnion.edit")
@UiDescriptor("usr-node-gen-file-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeGenUnion0Edit extends UsrNodeBase0BaseEdit<UsrNodeGenUnion, UsrNodeGenUnionType, UsrNodeGenUnion0Service, UsrNodeGenUnion0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenUnion.Service")
    public void setService(UsrNodeGenUnion0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenUnion.Repo")
    public void setRepo(UsrNodeGenUnion0Repo repo) { this.repo = repo; }

}