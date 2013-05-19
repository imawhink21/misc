package boggle;

import java.util.HashMap;
import java.util.Map;

public class BoggleDictionary
{
	private static BoggleDictionary dictionary = null;
	private DictionaryNode root;
	
	public static BoggleDictionary instance()
	{
		if (dictionary == null)
		{
			dictionary = new BoggleDictionary();
			dictionary.root = new DictionaryNode(null);
			DictionaryNode n1 = new DictionaryNode(null);
			dictionary.root.addChild('y', n1);
			DictionaryNode n2 = new DictionaryNode(null);
			n1.addChild('u', n2);
			DictionaryNode n3 = new DictionaryNode("yum");
			n2.addChild('m', n3);
			DictionaryNode n4 = new DictionaryNode(null);
			n2.addChild('c', n4);
			DictionaryNode n5 = new DictionaryNode("yuck");
			n4.addChild('k', n5);
		}
		return dictionary;
	}
	
	private BoggleDictionary()
	{
	}
	
	public DictionaryNode getRoot()
	{
		return root;
	}
	
	public static class DictionaryNode
	{
		private String word = null;
		
		private Map<Character, DictionaryNode> children =
				new HashMap<Character, DictionaryNode>();
		
		public DictionaryNode(String s)
		{
			word = s;
		}
		
		public void addChild(char c, DictionaryNode node)
		{
			children.put(c, node);
		}
		
		public DictionaryNode getChild(char c)
		{
			return children.get(c);
		}
		
		public boolean isWord()
		{
			return word != null;
		}
		
		public String getWord()
		{
			return word;
		}
	}
}


