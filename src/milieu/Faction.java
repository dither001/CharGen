package milieu;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Faction<T> {

	public boolean addCurrentMember(T member);

	public boolean addAllCurrentMembers(Collection<T> c);

	public void setCurrentMembers(Collection<T> c);

	public Set<T> currentMemberSet();

	public boolean addFormerMember(T member);

	public boolean addAllFormerMembers(Collection<T> c);

	public void setFormerMembers(Collection<T> c);

	public Set<T> formerMemberSet();

	public World getHomeworld();

	//
	public List<String> nameList();

}
