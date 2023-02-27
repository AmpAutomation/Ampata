package ca.ampautomation.ampata.fragment;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.ScreenFragment;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("frag_UsrNodeFrag")
@UiDescriptor("usr-node-frag.xml")
public class UsrNodeFrag extends ScreenFragment {

    public void setInstCntnrMain(InstanceContainer<UsrNode> instCntnrMain) {
    }
}