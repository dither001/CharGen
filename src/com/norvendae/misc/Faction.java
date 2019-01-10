package com.norvendae.misc;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.traveller1e.worlds.World;

public interface Faction<T> {
	/*
	 * A faction might form around a person, place, or thing (as in, a physical
	 * object), but time has taught us (mainly me) that 3 things to choose from just
	 * isn't interesting enough for a game.
	 */

	/*
	 * Think of cultural groups like the Jews who were separated and displaced from
	 * their homelands and whatnot; they centered around ideals and religion instead
	 * of people. "People" might be an example of a chief or family or tribe whereas
	 * "thing" might be a ship or a big pile of money (company or corporation).
	 */

	// person, people, place, thing, goal, ideal
	
	public boolean addCurrentMember(T member);

	public boolean addAllCurrentMembers(Collection<T> c);

	public void setCurrentMembers(Collection<T> c);

	public Set<T> currentMemberSet();

	//
	public boolean addFormerMember(T member);

	public boolean addAllFormerMembers(Collection<T> c);

	public void setFormerMembers(Collection<T> c);

	public Set<T> formerMemberSet();

	//
	public World getHomeworld();

	public List<String> nameList();

}
