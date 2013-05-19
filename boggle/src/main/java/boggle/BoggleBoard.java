package boggle;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;

import org.junit.Test;

import boggle.BoggleDictionary.DictionaryNode;

public class BoggleBoard
{
	private BoggleTile[][] board = new BoggleTile[4][4];
	private BoggleDictionary dict = BoggleDictionary.instance();

	public void build(char[][] b)
	{
		for (int i = 0; i < b.length; ++i)
		{
			for (int j = 0; j < b[i].length; ++j)
			{
				board[i][j] = new BoggleTile(b[i][j]);
			}
		}

		// set up the neighbors
		for (int i = 0; i < board.length; ++i)
		{
			for (int j = 0; j < board[i].length; ++j)
			{
				if (checkBounds(i-1, j-1))
					board[i][j].addNeighbor(board[i-1][j-1]);
				if (checkBounds(i-1, j))
					board[i][j].addNeighbor(board[i-1][j]);
				if (checkBounds(i-1, j+1))
					board[i][j].addNeighbor(board[i-1][j+1]);
				if (checkBounds(i, j-1))
					board[i][j].addNeighbor(board[i][j-1]);
				if (checkBounds(i, j+1))
					board[i][j].addNeighbor(board[i][j+1]);
				if (checkBounds(i+1, j-1))
					board[i][j].addNeighbor(board[i+1][j-1]);
				if (checkBounds(i+1, j))
					board[i][j].addNeighbor(board[i+1][j]);
				if (checkBounds(i+1, j+1))
					board[i][j].addNeighbor(board[i+1][j+1]);
			}
		}
	}

	public Set<String> findAllWords()
	{
		// starting position
		Set<String> allWords = new HashSet<String>();
		for (int i = 0; i < board.length; ++i)
		{
			for (int j = 0; j < board[i].length; ++j)
			{
				Set<BoggleTile> tilesVisited = new HashSet<BoggleTile>();
				traverse(board[i][j], tilesVisited, allWords, dict.getRoot()); 
			}
		}
		
		return allWords;
	}

	private void traverse(BoggleTile currentTile, 
			Set<BoggleTile> tilesVisited,
			Set<String> matchingWords, 
			DictionaryNode dictNode)
	{
		tilesVisited.add(currentTile);
		
		DictionaryNode node = dictNode.getChild(currentTile.getCharacter());
		if (node != null)
		{	
			if (node.isWord()) 
			{ 
				matchingWords.add(node.getWord()); 
			}

			for (BoggleTile neighbor : currentTile.getNeighbors())
			{
				if (!tilesVisited.contains(neighbor))
				{	
					traverse(neighbor, tilesVisited, matchingWords, node);
				}	
			}
		}

		tilesVisited.remove(currentTile);
	}

	public boolean checkBounds(int i, int j)
	{
		return (i >= 0 && i < 4) && (j >= 0 && j < 4);
	}
	
	@Test
	public void test1()
	{
		char[][] board = 
			{
				{'a', 'c', 'k', 'e'},
				{'n', 'u', 's', 'g'},
				{'m', 'c', 'y', 'l'},
				{'r', 'o', 'w', 'e'}
			};
		
		build(board);
		Set<String> expected = new HashSet<String>();
		expected.add("yuck");
		expected.add("yum");
		assertEquals("Find all words test 1: ", findAllWords(), expected);
	}
	
	@Test
	public void test2()
	{
		char[][] board = 
			{
				{'a', 'c', 'k', 'e'},
				{'n', 'i', 's', 'g'},
				{'m', 'c', 'y', 'l'},
				{'r', 'o', 'w', 'e'}
			};
		
		build(board);
		Set<String> expected = new HashSet<String>();
		assertEquals("Find all words test 2: ", findAllWords(), expected);
	}
}


