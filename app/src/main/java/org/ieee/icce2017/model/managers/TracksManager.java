package org.ieee.icce2017.model.managers;

import org.ieee.drupal.AbstractBaseDrupalEntity;
import org.ieee.drupal.DrupalClient;
import org.ieee.icce2017.model.Model;
import org.ieee.icce2017.model.dao.TrackDao;
import org.ieee.icce2017.model.data.Level;
import org.ieee.icce2017.model.data.Track;
import org.ieee.icce2017.model.requests.TracksRequest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TracksManager extends SynchronousItemManager<Track.Holder, Object, String> {

    private TrackDao mTrackDao;

    public TracksManager(DrupalClient client) {
        super(client);
        mTrackDao = new TrackDao();
    }

    @Override
    protected AbstractBaseDrupalEntity getEntityToFetch(DrupalClient client, Object requestParams) {
        return new TracksRequest(client);
    }

    @Override
    protected String getEntityRequestTag(Object params) {
        return "tracks";
    }

    @Override
    protected boolean storeResponse(Track.Holder requestResponse, String tag) {
        List<Track> tracks = requestResponse.getTracks();
        if (tracks == null) return false;

        mTrackDao.saveOrUpdateDataSafe(tracks);
        for (Track track : tracks) {
            if (track != null) {
                if (track.isDeleted()) {
                    mTrackDao.deleteDataSafe(track.getId());
                }
            }
        }
        return true;
    }

    public List<Track> getTracks() {
        List<Track> tracks = mTrackDao.getAllSafe();
        Collections.sort(tracks, new Comparator<Track>() {
            @Override
            public int compare(Track track, Track track2) {
                return Double.compare(track.getOrder(), track2.getOrder());
            }
        });
        return tracks;
    }

    public Track getTrack(long trackId) {
        List<Track> data = mTrackDao.getDataSafe(trackId);
        return data.size() > 0 ? data.get(0) : null;
    }

    public List<Level> getLevels() {
        LevelsManager levelManager = Model.instance().getLevelsManager();

        List<Level> levels = levelManager.getLevels();
        Collections.sort(levels, new Comparator<Level>() {
            @Override
            public int compare(Level level, Level level2) {
                return Double.compare(level.getOrder(), level2.getOrder());
            }
        });

        return levels;
    }

    public void clear() {
        mTrackDao.deleteAll();
    }
}
