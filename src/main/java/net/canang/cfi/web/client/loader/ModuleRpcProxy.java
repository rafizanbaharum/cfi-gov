package net.canang.cfi.web.client.loader;

import com.extjs.gxt.ui.client.data.LoadConfig;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import net.canang.cfi.web.am.client.AmDelegateAsync;
import net.canang.cfi.web.am.client.model.ModuleModel;

import java.util.Set;
import java.util.logging.Logger;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class ModuleRpcProxy extends RpcProxy<Set<ModuleModel>> {

    public static final Logger log = Logger.getLogger(ModuleRpcProxy.class.getName());

    private AmDelegateAsync delegate;

    public ModuleRpcProxy(AmDelegateAsync delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void load(Object loadConfig, final AsyncCallback<Set<ModuleModel>> callback) {
        final LoadConfig config = (LoadConfig) loadConfig;
    }
}

