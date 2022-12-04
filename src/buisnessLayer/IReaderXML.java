package buisnessLayer;

import java.io.File;

import transferObject.*;

/*
 * Author Remal Fatima
 */

public interface IReaderXML {

	// Reading XML File
	boolean readFile(File file);

	// Return content
	Content getContent();

}