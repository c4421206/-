package com.clouddo.commons.kettle.util;

import com.clouddo.commons.common.service.ApplicationContextRegister;
import com.clouddo.commons.kettle.api.RepositoryConfig;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.pentaho.di.trans.Trans;

/**
 * kettle资源库工具类
 * @author zhongming
 * @since 3.0
 * 2018/6/13下午6:58
 */
public class RepositoryUtil {

    private static RepositoryConfig repositoryConfig;

    public Trans getTransById(String id) {
        return null;
    }


    private static void init() {
        if(repositoryConfig == null) {
            repositoryConfig = ApplicationContextRegister.getBean(RepositoryConfig.class);
        }
    }

    //初始化资源库信息 Repository
    private static KettleDatabaseRepository initRepository() throws KettleException {
        KettleDatabaseRepository repository = new KettleDatabaseRepository();
        DatabaseMeta databaseMeta = new DatabaseMeta(repositoryConfig.getName(),repositoryConfig.getDataType(),repositoryConfig.getAccess(),repositoryConfig.getHost(),repositoryConfig.getDB(),repositoryConfig.getPort(),repositoryConfig.getUsername(),repositoryConfig.getPassword());

        KettleDatabaseRepositoryMeta kettleDatabaseRepositoryMeta = new KettleDatabaseRepositoryMeta(repositoryConfig.getId(),repositoryConfig.getName(),"Transformation description",databaseMeta);
        repository.init(kettleDatabaseRepositoryMeta);
        repository.connect("admin","admin");
        RepositoryDirectoryInterface directoryInterface = repository.loadRepositoryDirectoryTree();

        return null;
    }

}
