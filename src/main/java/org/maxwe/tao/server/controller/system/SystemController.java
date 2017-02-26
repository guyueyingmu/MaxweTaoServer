package org.maxwe.tao.server.controller.system;

import com.alibaba.druid.util.StringUtils;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import org.maxwe.tao.server.ApplicationConfigure;
import org.maxwe.tao.server.common.utils.DateTimeUtils;
import org.maxwe.tao.server.interceptor.SessionInterceptor;
import org.maxwe.tao.server.service.account.agent.AgentEntity;
import org.maxwe.tao.server.service.account.agent.AgentServices;
import org.maxwe.tao.server.service.account.agent.IAgentServices;
import org.maxwe.tao.server.service.system.BackupEntity;
import org.maxwe.tao.server.service.system.SystemServices;
import org.maxwe.tao.server.service.tao.jidi.JiDiServices;

import java.io.File;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Created by Pengwei Ding on 2017-02-14 21:48.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class SystemController extends Controller implements ISystemController {
    private IAgentServices iAgentServices = new AgentServices();

    @Override
    @Before(SessionInterceptor.class)
    public void system() {

        render("/webapp/widgets/systemInfoList.view.html");
    }

    @Override
    @Before(SessionInterceptor.class)
    public void money() {
        LinkedList<AgentEntity> agentEntities = iAgentServices.retrieveByTop();
        this.setAttr("topAgents", agentEntities);
        render("/webapp/widgets/systemMoneyList.view.html");
    }

    @Override
    @Before(SessionInterceptor.class)
    public void append() {
        String mark = this.getPara("mark");
        int appendCodes = this.getParaToInt("appendCodes");
        if (StringUtils.isEmpty(mark) || appendCodes <= 0 || appendCodes > 1000) {
            this.setAttr("errorInfo", "提交参数错误");
        } else {
            AgentEntity agentEntity = new AgentEntity();
            agentEntity.setMark(mark);
            boolean appendResult = iAgentServices.appendCodes(agentEntity, appendCodes);
            if (appendResult) {

            } else {
                this.setAttr("errorInfo", "修改失败");
            }
        }
    }

    @Override
    @Before(SessionInterceptor.class)
    public void backups() {
        int pageIndex = this.getParaToInt("pageIndex") == null ? 0 : this.getParaToInt("pageIndex");
        int pageSize = (this.getParaToInt("pageSize") == null || this.getParaToInt("pageSize") == 0) ? 12 : this.getParaToInt("pageSize");
        LinkedList<BackupEntity> backupEntities = SystemServices.getInstance().retrieveAll(pageIndex, pageSize);
        this.setAttr("databaseBackups", backupEntities);
        render("/webapp/widgets/systemBackups.view.html");
    }

    @Override
    @Before(SessionInterceptor.class)
    public void backup() {
        this.getResponse().setContentType("application/json; charset=utf-8");
        try {
            String filePath = ApplicationConfigure.DATABASE_BACKUP_FILE_DIR + File.separator + DateTimeUtils.getCurrentTimestamp() + ".sql";
            SystemServices.getInstance().backup(filePath, ApplicationConfigure.username, ApplicationConfigure.password, ApplicationConfigure.databaseName);
            BackupEntity backupEntity = SystemServices.getInstance().createBackup(new BackupEntity(UUID.randomUUID().toString(), null, filePath, 1, 1, 0));
            if (backupEntity == null) {
                renderError(500);
            } else {
                renderJson("{\"result\":\"ok\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            renderError(500);
        }
    }

    @Override
    @Before(SessionInterceptor.class)
    public void download() {
        int type = this.getParaToInt("type") == null ? 0 : this.getParaToInt("type");
        String id = this.getPara("id");
        if (type == 0 || StringUtils.isEmpty(id)) {
            renderError(400);
        } else {
            if (type == 1) {
                BackupEntity backupEntity = SystemServices.getInstance().retrieveById(id);
                renderFile(new File(backupEntity.getFilePath()));
            } else if (type == 2) {
                renderError(400);
            } else {
                renderError(400);
            }
        }
    }


    @Override
    @Before(SessionInterceptor.class)
    public void initThird() {
        JiDiServices.getInstance().init();
        renderJson(new String[]{"ok"});
    }

    @Override
    @Before(SessionInterceptor.class)
    public void summaryThird() {
        int dataCounter = JiDiServices.getInstance().getDataCounter();
        this.setAttr("dataCounter", dataCounter);
        render("/webapp/widgets/systemThirdData.view.html");
    }
}
