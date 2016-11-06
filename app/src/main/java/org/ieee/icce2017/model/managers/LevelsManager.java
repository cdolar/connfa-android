package org.ieee.icce2017.model.managers;

import org.ieee.drupal.AbstractBaseDrupalEntity;
import org.ieee.drupal.DrupalClient;
import org.ieee.icce2017.model.dao.LevelDao;
import org.ieee.icce2017.model.data.Level;
import org.ieee.icce2017.model.requests.LevelsRequest;

import java.util.List;

public class LevelsManager extends SynchronousItemManager<Level.Holder, Object, String> {

    private LevelDao mLevelDao;

    public LevelsManager(DrupalClient client) {
        super(client);
        mLevelDao = new LevelDao();
    }

    @Override
    protected AbstractBaseDrupalEntity getEntityToFetch(DrupalClient client, Object requestParams) {
        return new LevelsRequest(client);
    }

    @Override
    protected String getEntityRequestTag(Object params) {
        return "levels";
    }

    @Override
    protected boolean storeResponse(Level.Holder requestResponse, String tag) {
        List<Level> levels = requestResponse.getLevels();
        if (levels == null) {
            return false;
        }

        mLevelDao.saveOrUpdateDataSafe(levels);
        for (Level level : levels) {
            if (level != null) {
                if (level.isDeleted()) {
                    deleteLevel(level);
                }
            }
        }
        return true;
    }

    public Level getLevel(long levelId) {
        List<Level> data = mLevelDao.getDataSafe(levelId);
        return data.size() > 0 ? data.get(0) : null;
    }

    public List<Level> getLevels() {
        return mLevelDao.getAllSafe();
    }

    public void deleteLevel(Level level) {
        mLevelDao.deleteDataSafe(level.getId());
    }

    public void clear() {
        mLevelDao.deleteAll();
    }
}
