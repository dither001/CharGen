package com.norvendae.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.traveller1e.worlds.World;

import actor.Actor;
import actor.Immortal;

public class Pantheon implements Faction<Actor> {
	/*
	 * STATIC FIELDS
	 */
	private static Set<Pantheon> pantheons;

	static {
		pantheons = new HashSet<Pantheon>();

		Immortal.Lesser[] array = Immortal.getPantheons();
		Set<Immortal> set;
		Immortal god;
		Pantheon pantheon;

		for (int i = 0; i < array.length; ++i) {
			pantheon = new Pantheon(array[i]);
			pantheons.add(pantheon);

			set = Immortal.filterByPantheon(array[i]);
			for (Iterator<Immortal> it = set.iterator(); it.hasNext();) {
				god = it.next();
				pantheon.add(god);

			}

		}

	}

	/*
	 * INSTANCE FIELDS
	 */
	private Immortal.Lesser affiliation;
	private String name;
	private Immortal leader;

	private Set<Immortal> members;
	private int membership;

	/*
	 * CONSTRUCTORS
	 */
	public Pantheon(Immortal.Lesser affiliation) {
		this.members = new HashSet<Immortal>();
		membership = 0;

		//
		this.name = Names.stringToName(affiliation.toString());

		Set<Immortal> set = Immortal.filterByPantheon(affiliation);
		Immortal instance;
		for (Iterator<Immortal> it = set.iterator(); it.hasNext();) {
			instance = it.next();

			add(instance);
		}
	}

	/*
	 * 
	 */
	public void add(Immortal immortal) {
		if (immortal != null && members.contains(immortal) != true) {
			members.add(immortal);
			++membership;

		}
	}

	@Override
	public String toString() {
		String string = String.format("%s", name);

		return string;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Set<Pantheon> getPantheons() {
		return pantheons;
	}

	public List<String> nameList() {
		List<String> nameList = new ArrayList<String>();
		String name;
		for (Iterator<Immortal> it = members.iterator(); it.hasNext();) {
			name = it.next().getName();
			if (name != null)
				nameList.add(name);
		}

		return nameList;
	}

	@Override
	public boolean addCurrentMember(Actor actor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAllCurrentMembers(Collection<Actor> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCurrentMembers(Collection<Actor> c) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Actor> currentMemberSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addFormerMember(Actor actor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAllFormerMembers(Collection<Actor> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFormerMembers(Collection<Actor> c) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Actor> formerMemberSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public World getHomeworld() {
		// TODO Auto-generated method stub
		return null;
	}

}
