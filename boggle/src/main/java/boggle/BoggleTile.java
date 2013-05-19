package boggle;

import java.util.Set;
import java.util.HashSet;

public class BoggleTile
{
	private char c;
	private Set<BoggleTile> neighbors = new HashSet<BoggleTile>();

	public BoggleTile(char c)
	{
		this.c = c;
	}
	
	public char getCharacter()
	{
		return c;
	}

	public Set<BoggleTile> getNeighbors()
	{
		return neighbors;
	}
	
	public void addNeighbor(BoggleTile n)
	{
		neighbors.add(n);
	}
}