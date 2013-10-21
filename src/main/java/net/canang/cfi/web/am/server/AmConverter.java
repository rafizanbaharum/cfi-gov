package net.canang.cfi.web.am.server;

import net.canang.cfi.biz.am.manager.AmFinder;
import net.canang.cfi.biz.am.manager.AmManager;
import net.canang.cfi.core.am.model.CfModule;
import net.canang.cfi.web.am.client.model.ModuleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
@Component("amConverter")
public class AmConverter {

    @Autowired
    private AmManager amManager;

    @Autowired
    private AmFinder amFinder;

    public ModuleModel convert(CfModule module) {
        ModuleModel model = new ModuleModel();
        model.setId(module.getId());
        model.setCode(module.getCode());
        model.setDescription(module.getDescription());
        model.setOrder(module.getOrder());
        return model;
    }
}
