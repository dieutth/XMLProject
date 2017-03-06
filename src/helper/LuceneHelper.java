package helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LuceneHelper {
	private String dictFileLocation = "D://Data//xmlproject//bookList.dat";
	private String spellCheckerFileLocation = "D://Data//xmlproject//spellchecker";
	private SpellChecker spellChecker;
	public LuceneHelper(){
		File dir = new File(spellCheckerFileLocation);
	     
		try {
			Directory directory = FSDirectory.open(dir);
			spellChecker  = new SpellChecker(directory);
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, null);
			spellChecker.indexDictionary(new PlainTextDictionary(new File(dictFileLocation)), config, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	 
    public List<String> getSuggestion(String searchTitle){
    	List<String> titles = new ArrayList<String>();
		try {
			String[] suggestions;
			suggestions = spellChecker.suggestSimilar(searchTitle, 5);
			if (suggestions!=null && suggestions.length>0) {
		         for (String word : suggestions)
		             titles.add(word);
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return titles;
    }

}
