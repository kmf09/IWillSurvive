package com.mobdb.android;
import java.security.InvalidParameterException;


public class GetFile {
	
	private String file_ID = null;
	
	/**
	 * Set file ID
	 * @param fileID
	 * @throws InvalidParameterException
	 */
	public GetFile( String fileID ) throws InvalidParameterException{
		
		if(fileID == null || fileID.trim().length() <= 0){
			throw new InvalidParameterException("Required valid file ID");
		}
		this.file_ID = fileID;
		
	}
	
	public String getFileID(){
		return this.file_ID;
	}
	
	public String getQueryString(){
		return this.file_ID;
	}
	
}
