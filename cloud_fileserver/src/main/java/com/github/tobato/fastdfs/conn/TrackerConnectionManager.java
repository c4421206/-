package com.github.tobato.fastdfs.conn;

import com.github.tobato.fastdfs.FdfsClientConstants;
import com.github.tobato.fastdfs.domain.TrackerLocator;
import com.github.tobato.fastdfs.exception.FdfsConnectException;
import com.github.tobato.fastdfs.proto.FdfsCommand;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理TrackerClient连接池分配
 * @author tobato 1.0
 * @author zhongming 2.0
 * 修改trackerList，解决springcloud配置中心无法读取数组的问题
 * @since 3.0
 * 2018/6/26下午3:18
 */
@Component
@ConfigurationProperties(prefix = FdfsClientConstants.ROOT_CONFIG_PREFIX)
public class TrackerConnectionManager extends ConnectionManager {

    /** Tracker定位 */
    private TrackerLocator trackerLocator;

    /** tracker服务配置地址列表 */
    private List<String> trackerList = new ArrayList<String>();

    @NotNull
    private String trackerLists;

    /** 构造函数 */
    public TrackerConnectionManager() {
        super();
    }

    /** 构造函数 */
    public TrackerConnectionManager(FdfsConnectionPool pool) {
        super(pool);
    }

    /** 初始化方法 */
    @PostConstruct
    public void initTracker() {
        //v2.0 zhongming
        if(!StringUtils.isEmpty(this.trackerLists)) {
            for(String tracker : trackerLists.split(";")) {
                if(!StringUtils.isEmpty(tracker.trim())) {
                    this.trackerList.add(tracker);
                }
            }
        }

        LOGGER.debug("init trackerLocator {}", trackerList);
        trackerLocator = new TrackerLocator(trackerList);
    }

    /**
     * 获取连接并执行交易
     *
     * @param command
     * @return
     */
    public <T> T executeFdfsTrackerCmd(FdfsCommand<T> command) {
        Connection conn = null;
        InetSocketAddress address = null;
        // 获取连接
        try {
            address = trackerLocator.getTrackerAddress();
            LOGGER.debug("获取到Tracker连接地址{}", address);
            conn = getConnection(address);
            trackerLocator.setActive(address);
        } catch (FdfsConnectException e) {
            trackerLocator.setInActive(address);
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unable to borrow buffer from pool", e);
            throw new RuntimeException("Unable to borrow buffer from pool", e);
        }
        // 执行交易
        return execute(address, conn, command);
    }

    public List<String> getTrackerList() {
        return trackerList;
    }

    public void setTrackerList(List<String> trackerList) {
        this.trackerList = trackerList;
    }

    public String getTrackerLists() {
        return trackerLists;
    }

    public void setTrackerLists(String trackerLists) {
        this.trackerLists = trackerLists;
    }
}
