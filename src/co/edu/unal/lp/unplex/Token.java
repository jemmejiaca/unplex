package co.edu.unal.lp.unplex;

/*********
 * WEIRD *
 *********/
public class Token {
	private LexicalCategory lexicalCategory;
	private VerbSublexicalCategory verbSublexicalCategory;
	private String lexeme;
	private String[] meanings;
	
	public Token(LexicalCategory lexicalCategory, VerbSublexicalCategory sublexicalCategory, String lexeme,
			String[] meanings) {
		this.lexicalCategory = lexicalCategory;
		this.verbSublexicalCategory = sublexicalCategory;
		this.lexeme = lexeme;
		this.meanings = meanings;
	}

	public Token() {
		this(null, null, "", null);
	}
	
	public LexicalCategory getLexicalCategory() {
		return lexicalCategory;
	}
	public void setLexicalCategory(LexicalCategory lexicalCategory) {
		this.lexicalCategory = lexicalCategory;
	}
	public VerbSublexicalCategory getVerbSublexicalCategory() {
		return verbSublexicalCategory;
	}
	public void setVerbSublexicalCategory(VerbSublexicalCategory sublexicalCategory) {
		this.verbSublexicalCategory = sublexicalCategory;
	}
	public String getLexeme() {
		return lexeme;
	}
	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}
	public String[] getMeanings() {
		return meanings;
	}
	public void setMeanings(String[] meanings) {
		this.meanings = meanings;
	}
	
	@Override
	public String toString() {
		StringBuilder meaningsLexeme = new StringBuilder("");
		if (meanings.length > 1)
			for (String meaning : meanings)
				meaningsLexeme.append(", " + meaning);
		return "< " + getLexicalCategory().toString() + ", " +
				getVerbSublexicalCategory().toString() + ", " +
				getLexeme().toString() + meaningsLexeme.toString() + " >";
	}

}
