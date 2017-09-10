package com.msd.DBLPParser.interfaces;

import java.io.File;


/**
 * The Interface ParseXMLInterface.
 */
public interface ParseXMLInterface {
	
	/**
	 * parseXML: Parse input data from xml and load it into database
	 *
	 * @param filename Filename to be parsed and loaded into the database
	 * @return True if loaded successfully else returns false if some error is encountered while loading.
	 */
	public boolean parseXML(File filename);
	
	/**
	 * Parses the committee.
	 *
	 * @param filename the filename
	 * @return true, if successful
	 */
	public boolean parseCommittee(File filename);
	
}

