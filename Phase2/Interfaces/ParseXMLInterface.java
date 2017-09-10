import java.io.File;
public interface ParseXMLInterface {
	
	// parseXML: Parse input data from xml and load it into database
	// GIVEN: Filename to be parsed and loaded into the database
	// RETURNS: True if loaded successfully else returns false if some error is encountered while loading.
	public boolean parseXML(File filename);
	
}
