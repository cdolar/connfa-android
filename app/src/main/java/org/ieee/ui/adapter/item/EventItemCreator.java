package org.ieee.ui.adapter.item;

import org.ieee.icce2017.model.data.Event;

public interface EventItemCreator {

    EventListItem getItem(Event event);

}
