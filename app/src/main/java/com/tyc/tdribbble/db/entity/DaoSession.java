package com.tyc.tdribbble.db.entity;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.tyc.tdribbble.db.entity.HistoryEntity;

import com.tyc.tdribbble.db.entity.HistoryEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 *
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig historyEntityDaoConfig;

    private final HistoryEntityDao historyEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        historyEntityDaoConfig = daoConfigMap.get(HistoryEntityDao.class).clone();
        historyEntityDaoConfig.initIdentityScope(type);

        historyEntityDao = new HistoryEntityDao(historyEntityDaoConfig, this);

        registerDao(HistoryEntity.class, historyEntityDao);
    }

    public void clear() {
        historyEntityDaoConfig.clearIdentityScope();
    }

    public HistoryEntityDao getHistoryEntityDao() {
        return historyEntityDao;
    }

}
