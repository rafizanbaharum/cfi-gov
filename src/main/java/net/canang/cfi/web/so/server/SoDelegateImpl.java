package net.canang.cfi.web.so.server;

import net.canang.cfi.biz.dd.manager.DdFinder;
import net.canang.cfi.biz.dd.manager.DdManager;
import net.canang.cfi.biz.so.manager.SoFinder;
import net.canang.cfi.biz.so.manager.SoManager;
import net.canang.cfi.web.am.server.AmDelegateImpl;
import net.canang.cfi.web.dd.server.DdConverter;
import net.canang.cfi.web.server.common.AutoInjectingRemoteServiceServlet;
import net.canang.cfi.web.so.client.SoDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * @author rafizan.baharum
 * @since 10/21/13
 */
public class SoDelegateImpl extends AutoInjectingRemoteServiceServlet implements SoDelegate {

    private static final Logger log = Logger.getLogger(AmDelegateImpl.class);

    @Autowired
    private DdManager ddManager;

    @Autowired
    private DdFinder ddFinder;

    @Autowired
    private DdConverter ddConverter;

    @Autowired
    private SoManager soManager;

    @Autowired
    private SoFinder soFinder;

    @Autowired
    private SoConverter soConverter;

    @Autowired
    private AuthenticationManager authenticationManager;



}
