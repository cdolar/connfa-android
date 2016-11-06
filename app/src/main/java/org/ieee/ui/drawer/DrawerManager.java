package org.ieee.ui.drawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.ieee.ui.fragment.AboutFragment;
import org.ieee.ui.fragment.EventHolderFragment;
import org.ieee.ui.fragment.FloorPlanFragment;
import org.ieee.ui.fragment.LocationFragment;
import org.ieee.ui.fragment.SocialMediaFragment;
import org.ieee.ui.fragment.SpeakersListFragment;
import org.jetbrains.annotations.NotNull;

public class DrawerManager {

	public enum EventMode {Program, Bofs, Social, Speakers, Favorites, Location, About}

	private FragmentManager fragmentManager;
	private int fragmentHolderId;
	private EventMode currentEventMode;

	public static DrawerManager getInstance(FragmentManager theFragmentManager, int theMainFragmentId) {
		return new DrawerManager(theFragmentManager, theMainFragmentId);
	}

	private DrawerManager(FragmentManager theFragmentManager, int theMainFragmentId) {
		this.fragmentManager = theFragmentManager;
		this.fragmentHolderId = theMainFragmentId;
	}

	public void setFragment(@NotNull DrawerMenu.DrawerItem mode) {
		Fragment fragment;
		String fragmentTag = null;

		switch (mode) {
			case Program:
				fragment = EventHolderFragment.newInstance(DrawerMenu.DrawerItem.Program.ordinal());
				fragmentTag = EventHolderFragment.TAG;
				break;

			case Bofs:
				fragment = EventHolderFragment.newInstance(DrawerMenu.DrawerItem.Bofs.ordinal());
				fragmentTag = EventHolderFragment.TAG;
				break;

			case Social:
				fragment = EventHolderFragment.newInstance(DrawerMenu.DrawerItem.Social.ordinal());
				fragmentTag = EventHolderFragment.TAG;
				break;

			case Favorites:
				fragment = EventHolderFragment.newInstance(DrawerMenu.DrawerItem.Favorites.ordinal());
				fragmentTag = EventHolderFragment.TAG;
				break;

			case Speakers:
				fragment = new SpeakersListFragment();
				fragmentTag = SpeakersListFragment.TAG;
				break;

			case FloorPlan:
				fragment = new FloorPlanFragment();
				fragmentTag = FloorPlanFragment.TAG;
				break;

			case Location:
				fragment = new LocationFragment();
				fragmentTag = LocationFragment.TAG;
				break;
			case SocialMedia:
				fragment = new SocialMediaFragment();
				fragmentTag = SocialMediaFragment.TAG;
				break;
			case About:
				fragment = new AboutFragment();
				fragmentTag = SocialMediaFragment.TAG;
				break;
			default:
				fragment = EventHolderFragment.newInstance(EventMode.Program.ordinal());
		}
		fragmentManager.beginTransaction().replace(fragmentHolderId, fragment, fragmentTag).commit();
	}

	public void reloadPrograms(@NotNull DrawerMenu.DrawerItem mode) {
		Fragment fragment;
		switch (mode) {
			case Program:
				fragment = EventHolderFragment.newInstance(DrawerMenu.DrawerItem.Program.ordinal());
				break;

			case Bofs:
				fragment = EventHolderFragment.newInstance(DrawerMenu.DrawerItem.Bofs.ordinal());
				break;

			case Social:
				fragment = EventHolderFragment.newInstance(DrawerMenu.DrawerItem.Social.ordinal());
				break;
			default:
				fragment = EventHolderFragment.newInstance(EventMode.Program.ordinal());
		}
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(fragmentHolderId, fragment, EventHolderFragment.TAG);
		ft.commitAllowingStateLoss();
	}
}
