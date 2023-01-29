package ca.ampautomation.ampata.screen.main;

import com.google.common.base.Strings;
import io.jmix.multitenancy.core.TenantProvider;
import io.jmix.multitenancyui.MultitenancyUiSupport;
import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenTools;
import io.jmix.ui.component.AppWorkArea;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Window;
import io.jmix.ui.component.mainwindow.Drawer;
import io.jmix.ui.icon.JmixIcon;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiControllerUtils;
import io.jmix.ui.screen.UiDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_MainScreen")
@UiDescriptor("main-screen.xml")
@Route(path = "main", root = true)
public class MainScreen extends Screen implements Window.HasWorkArea {


    @Autowired
    private Notifications notifications;

    @Autowired
    private TenantProvider tenantProvider;

    @Autowired
    private MultitenancyUiSupport multitenancyUiSupport;

    @Autowired
    private ScreenTools screenTools;

    @Autowired
    private AppWorkArea workArea;

    @Autowired
    private Drawer drawer;

    @Autowired
    private Button collapseDrawerButton;

    Logger logger = LoggerFactory.getLogger(MainScreen.class);

    @Subscribe
    public void onInit(InitEvent event) {
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        String currentTenantId = tenantProvider.getCurrentUserTenantId();

    }

    @Override
    public AppWorkArea getWorkArea() {
        return workArea;
    }

    @Subscribe("collapseDrawerButton")
    private void onCollapseDrawerButtonClick(Button.ClickEvent event) {
        drawer.toggle();
        if (drawer.isCollapsed()) {
            collapseDrawerButton.setIconFromSet(JmixIcon.CHEVRON_RIGHT);
        } else {
            collapseDrawerButton.setIconFromSet(JmixIcon.CHEVRON_LEFT);
        }
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        screenTools.openDefaultScreen(
                UiControllerUtils.getScreenContext(this).getScreens());

        screenTools.handleRedirect();
    }

    @Subscribe("ShowTenantBtn")
    public void onShowTenantBtnClick(Button.ClickEvent event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        String currentTenantId = tenantProvider.getCurrentUserTenantId();
        if (Strings.isNullOrEmpty(currentTenantId)) {
            currentTenantId = "<Null>";
        }
        notifications.create().withCaption("The tenant is " + currentTenantId + ".").show();

        logger.trace(logPrfx + " <-- ");

    }
}
