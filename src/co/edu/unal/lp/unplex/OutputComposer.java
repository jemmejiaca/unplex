package co.edu.unal.lp.unplex;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

/**
 * WEIRD
 * @author mauricio
 *
 */
public class OutputComposer {
	private String sentence;
	private String[] units;
	public static final String[] VERB_ROOTS = {
			"corr", "vend", "camin", "com", "jug", "aprend", "program", "sub", "cant", "aprob"
	};
	public static final String[][] NOUN_WITH_MEANINGS = {
			{"cabo", "Cada uno de los extremos de las cosas.", "Militar de la clase de tropa inmediatamente superior al soldado o marinero e inferior al sargento."},
			{"banco", "Objeto usado para sentarse", "Entidad financiera"},
			{"tasa", "Utensilio para la cocina", "Indice de variación"},
			{"coca", "Plato hondo", "Planta alucinógenea."},
			{"materia", "Lo que compone al universo.", "Asignatura académica."}
	};
	public static final String[] PRONOUNS = {
			"yo", "tu", "el", "ella", "ellos", "ellas", "nosotros", "nosotras", "eso"	
	};
	public static final String[] PREPOSITIONS = {
			"a", "ante", "bajo", "cabe", "con", "contra", "de", "desde", "para", "por", "sobre", "según", "hacia"
	};
	public static final String[] ARTICLES = {
			"el", "la", "un", "una", "los", "las", "de", "unos", "unas"
	};
	
	
	public OutputComposer(String sentence) {
		this.sentence = sentence;
		units = sentence.split(" ");
	}
	
	public Token[] getTokens() {
		Token[] tokenList = new Token[5];
		String[] nonDefined = {""};
		
		String pronoun = units[0];
		String verb = units[1];
		String preposition = units[2];
		String article = units[3];
		String noun = units[4];
		
		
		Token token0 = new Token();
		token0.setLexicalCategory(LexicalCategory.PRONOUN);
		token0.setVerbSublexicalCategory(VerbSublexicalCategory.NON_APPLIED);
		token0.setLexeme(pronoun);
		token0.setMeanings(nonDefined);
		tokenList[0] = token0;
		
		Token token1 = new Token();
		token1.setLexicalCategory(LexicalCategory.VERB);
		token1.setLexeme(verb);
		token1.setMeanings(nonDefined);
		tokenList[1] = token1;
		
		Token token2 = new Token();
		token2.setLexicalCategory(LexicalCategory.PREPOSITION);
		token2.setVerbSublexicalCategory(VerbSublexicalCategory.NON_APPLIED);
		token2.setLexeme(preposition);
		token2.setMeanings(nonDefined);
		tokenList[2] = token2;
		
		Token token3 = new Token();
		token3.setLexicalCategory(LexicalCategory.ARTICLE);
		token3.setVerbSublexicalCategory(VerbSublexicalCategory.NON_APPLIED);
		token3.setLexeme(article);
		token3.setMeanings(nonDefined);
		tokenList[3] = token3;
		
		Token token4 = new Token();
		token4.setLexicalCategory(LexicalCategory.NOUN);
		token4.setVerbSublexicalCategory(VerbSublexicalCategory.NON_APPLIED);
		token4.setLexeme(noun);
		token4.setMeanings(nonDefined);
		tokenList[4] = token4;

		for (int i = 0; i < VERB_ROOTS.length; ++i) {
			if (verb.startsWith(VERB_ROOTS[i])) {
				if (pronoun.equals("tu")) {
					if (verb.endsWith("s")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.PRESENT);
					if (verb.endsWith("iste") || verb.endsWith("aste")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.PAST);
					if (verb.endsWith("rás")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.FUTURE);
				} else if (pronoun.equals("el") || pronoun.equals("ella") || pronoun.equals("eso")) {
					if (verb.endsWith("a") || verb.endsWith("e")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.PRESENT);
					if (verb.endsWith("ó") || verb.endsWith("aste")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.PAST);
					if (verb.endsWith("ra")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.FUTURE);
				} else if (pronoun.equals("yo")) {
					if (verb.endsWith("o")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.PRESENT);
					if (verb.endsWith("í") || verb.endsWith("é")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.PAST);
					if (verb.endsWith("ré")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.FUTURE);
				} else if (pronoun.equals("nosotros") || pronoun.equals("nosotras")) {
					if (verb.endsWith("amos") || verb.endsWith("emos")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.PRESENT);
					if (verb.endsWith("imos")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.PAST);
					if (verb.endsWith("remos")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.FUTURE);
				} else if (pronoun.equals("ellos") || pronoun.equals("ellas")) {
					if (verb.endsWith("an") || verb.endsWith("en")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.PRESENT);
					if (verb.endsWith("eron") || verb.endsWith("aron")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.PAST);
					if (verb.endsWith("erán") || verb.endsWith("irán") || verb.endsWith("arán")) tokenList[1].setVerbSublexicalCategory(VerbSublexicalCategory.PRESENT);
				}
			}
		}
		
		for (int j = 0; j < NOUN_WITH_MEANINGS.length; ++j)  {
			if (noun.contains(NOUN_WITH_MEANINGS[j][0])) {
				String[] meaningsList = {NOUN_WITH_MEANINGS[j][1], NOUN_WITH_MEANINGS[j][2]};
				tokenList[4].setMeanings(meaningsList);
			}
		}
		
		return tokenList;
	}
	
	public void writeFile() {
		Path file = Paths.get("./tokens_list");
		try {
			OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
			for (Token t : getTokens()) {
				output.write(t.toString().getBytes());
				output.write(System.getProperty("line.separator").getBytes());
			}
			output.flush();
			output.close();
		} catch (Exception e) {
			System.out.println("Message: " + e);
		}
	}
}
