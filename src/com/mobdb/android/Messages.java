package com.mobdb.android;

import java.util.HashMap;

public class Messages {
	
	public static HashMap<Integer,String> msg = new HashMap<Integer,String>();
	public static HashMap<Integer,String> value = new HashMap<Integer,String>();
	
	/**
	 * List of mobDB communication massages and values
	 */
	public final static String SUCCESS_MSG = "Success" ;
	public final static Integer SUCCESS_VALUE = Integer.valueOf(101) ;

	public final static String FILE_ID_EXPIRED_OR_INVALID_MSG = "Invalid File ID" ;
	public final static Integer FILE_ID_EXPIRED_OR_INVALID_VALUE = Integer.valueOf(102) ;

	public final static String TABLE_DATA_NOT_FOUND_MSG = "Table data not found" ;
	public final static Integer TABLE_DATA_NOT_FOUND_VALUE = Integer.valueOf(103) ;

	public final static String QUERY_NOT_EXECUTED_MSG = "Query not executed" ;
	public final static Integer QUERY_NOT_EXECUTED_VALUE = Integer.valueOf(104) ;

	public final static String NO_CHANGES_ON_TABLE_MSG = "No changes on table" ;
	public final static Integer NO_CHANGES_ON_TABLE_VALUE = Integer.valueOf(105);
	
	public final static String SQL_SYNTAX_ERROR_MSG = "SQL syntax error" ;
	public final static Integer SQL_SYNTAX_ERROR_VALUE = Integer.valueOf(106);

	public final static String INVALID_APPLICATION_KEY_MSG = "Invalid application key" ;
	public final static Integer INVALID_APPLICATION_KEY_VALUE = Integer.valueOf(107);

	public final static String INVALID_TABLE_NAME_MSG = "Invalid table name" ;
	public final static Integer INVALID_TABLE_NAME_VALUE = Integer.valueOf(108);

	public final static String INVALID_XML_REQUEST_MSG = "Invalid XML request" ;
	public final static Integer INVALID_XML_REQUEST_VALUE = Integer.valueOf(109);

	public final static String FILE_INFORMATION_OR_DATA_IS_EMPTY_MSG = "File information or data is empty" ;
	public final static Integer FILE_INFORMATION_OR_DATA_IS_EMPTY_VALUE = Integer.valueOf(110);

	public final static String INVALID_XML_REQUEST_DELIMETER_REFER_MOBDB_DOCUMENTATION_MSG = "Invalid XML request delimiter, refer mobDB documentation" ;
	public final static Integer INVALID_XML_REQUEST_DELIMETER_REFER_MOBDB_DOCUMENTATION_VALUE = Integer.valueOf(111);

	public final static String INVALID_XML_REQUEST_FORMAT_REFER_MOBDB_DOCUMENTATION_MSG = "Invalid XML request format, refer mobDB documentation" ;
	public final static Integer INVALID_XML_REQUEST_FORMAT_REFER_MOBDB_DOCUMENTATION_VALUE = Integer.valueOf(112);

	public final static String FILE_UPLOAD_FAILED_MSG = "File upload failed" ;
	public final static Integer FILE_UPLOAD_FAILED_VALUE = Integer.valueOf(113) ;

	public final static String STREAM_CONTENT_TYPE_REQUIRED_MSG = "Content type required, refer mobDB document" ;
	public final static Integer STREAM_CONTENT_TYPE_REQUIRED_VALUE = Integer.valueOf(114);

	public final static String STREAM_CONTENT_EMPTY_MSG = "Stream content is empty" ;
	public final static Integer STREAM_CONTENT_EMPTY_VALUE = Integer.valueOf(115);
	
	public final static String DATA_TOO_LONG_MSG = "Data too long for column" ;
	public final static Integer DATA_TOO_LONG_VALUE = Integer.valueOf(116);
	
	public final static String UNSUPPORTED_MOBDB_SQL_REQUEST_MSG = "Unsupported mobDB SQl request" ;
	public final static Integer UNSUPPORTED_MOBDB_SQL_REQUEST_VALUE = Integer.valueOf(117);
	
	public final static String INACTIVE_USER_MSG = "Inactive user, please upgrade account" ;
	public final static Integer INACTIVE_USER_VALUE = Integer.valueOf(118);
	
	public final static String INVALID_DEVICE_TOKEN_MSG = "Device token not exists" ;
	public final static Integer INVALID_DEVICE_TOKEN_VALUE = Integer.valueOf(119);
	
	public final static String CERTIFICATE_FILE_NOT_EXISTS_MSG = "Certificate file not exists in mobDB settings";
	public final static Integer CERTIFICATE_FILE_NOT_EXISTS_VALUE = Integer.valueOf(120);
	
	public final static String AUTH_TOKEN_NOT_EXISTS_MSG = "Google authentication token not exists in mobDB settings";
	public final static Integer AUTH_TOKEN_NOT_EXISTS_VALUE = Integer.valueOf(121);
	
	public final static String INVALID_BUNDLE_TOKEN_MSG = "Bundle not exists or device token not exists";
	public final static Integer INVALID_BUNDLE_TOKEN_VALUE = Integer.valueOf(122);
	
	public final static String DEVICE_TOKEN_ALREADY_EXISTS_MSG = "iOS token already exists";
	public final static Integer DEVICE_TOKEN_ALREADY_EXISTS_VALUE = Integer.valueOf(123);
	
	public final static String ANDROID_ID_ALREADY_EXISTS_MSG = "Android registration ID already exists";
	public final static Integer ANDROID_ID_ALREADY_EXISTS_VALUE = Integer.valueOf(124);
	
	public final static String INVALID_GOOGLE_TOKEN_MSG = "Invalid token, This happens if the password is changed or token expires, please generate new token." ;
	public final static Integer INVALID_GOOGLE_TOKEN_VALUE = Integer.valueOf(125);
	
	public final static String INVALID_DEVICE_TYPE_MSG = "Invalid device type, please specify 'ios' or 'android'." ;
	public final static Integer INVALID_DEVICE_TYPE_VALUE = Integer.valueOf(126);
	
	public final static String INVALID_JSON_REQUEST_FORMAT_REFER_MOBDB_DOCUMENTATION_MSG = "Invalid 'JSON' request format, refer mobDB documentation" ;
	public final static Integer INVALID_JSON_REQUEST_FORMAT_REFER_MOBDB_DOCUMENTATION_VALUE = Integer.valueOf(127);
	
	public final static String INVALID_REQUEST_FORMAT_MSG = "Invalid request format, refer mobDB documentation" ;
	public final static Integer INVALID_REQUEST_FORMAT_VALUE = Integer.valueOf(128);
	
	public final static String INVALID_DATA_TYPE_MSG = "Data type not supported, refer mobDB documentation" ;
	public final static Integer INVALID_DATA_TYPE_VALUE = Integer.valueOf(129);
	
	public final static String INVALID_PAYLOAD_MSG = "Invalid payload format, refer mobDB documentation" ;
	public final static Integer INVALID_PAYLOAD_VALUE = Integer.valueOf(130);
	
	public final static String INVALID_DATE_TIME_MSG = "Invalid date and time format, refer mobDB documentation" ;
	public final static Integer INVALID_DATE_TIME_VALUE = Integer.valueOf(131);
	
	public final static String INVALID_FILE_KEY_MSG = "Invalid file key" ;
	public final static Integer INVALID_FILE_KEY_VALUE = Integer.valueOf(132);
	
	
	static{
		
		msg.put(INVALID_APPLICATION_KEY_VALUE, INVALID_APPLICATION_KEY_MSG);
		msg.put(FILE_ID_EXPIRED_OR_INVALID_VALUE, FILE_ID_EXPIRED_OR_INVALID_MSG);
		msg.put(FILE_INFORMATION_OR_DATA_IS_EMPTY_VALUE, FILE_INFORMATION_OR_DATA_IS_EMPTY_MSG);
		msg.put(FILE_UPLOAD_FAILED_VALUE, FILE_UPLOAD_FAILED_MSG);
		msg.put(INVALID_XML_REQUEST_DELIMETER_REFER_MOBDB_DOCUMENTATION_VALUE, INVALID_XML_REQUEST_DELIMETER_REFER_MOBDB_DOCUMENTATION_MSG);
		msg.put(INVALID_XML_REQUEST_FORMAT_REFER_MOBDB_DOCUMENTATION_VALUE, INVALID_XML_REQUEST_FORMAT_REFER_MOBDB_DOCUMENTATION_MSG);
		msg.put(INVALID_TABLE_NAME_VALUE, INVALID_TABLE_NAME_MSG);
		msg.put(INVALID_XML_REQUEST_VALUE, INVALID_XML_REQUEST_MSG);
		msg.put(SUCCESS_VALUE, SUCCESS_MSG);
		msg.put(QUERY_NOT_EXECUTED_VALUE, QUERY_NOT_EXECUTED_MSG);
		msg.put(NO_CHANGES_ON_TABLE_VALUE, NO_CHANGES_ON_TABLE_MSG);	
		msg.put(SQL_SYNTAX_ERROR_VALUE, SQL_SYNTAX_ERROR_MSG);
		msg.put(STREAM_CONTENT_TYPE_REQUIRED_VALUE, STREAM_CONTENT_TYPE_REQUIRED_MSG);
		msg.put(STREAM_CONTENT_EMPTY_VALUE, STREAM_CONTENT_EMPTY_MSG);
		msg.put(TABLE_DATA_NOT_FOUND_VALUE, TABLE_DATA_NOT_FOUND_MSG);
		msg.put(DATA_TOO_LONG_VALUE, DATA_TOO_LONG_MSG);
		msg.put(UNSUPPORTED_MOBDB_SQL_REQUEST_VALUE, UNSUPPORTED_MOBDB_SQL_REQUEST_MSG);
		msg.put(INACTIVE_USER_VALUE, INACTIVE_USER_MSG);
		msg.put(INVALID_DEVICE_TOKEN_VALUE, INVALID_DEVICE_TOKEN_MSG);
		msg.put(CERTIFICATE_FILE_NOT_EXISTS_VALUE, CERTIFICATE_FILE_NOT_EXISTS_MSG);
		msg.put(AUTH_TOKEN_NOT_EXISTS_VALUE, AUTH_TOKEN_NOT_EXISTS_MSG);
		msg.put(INVALID_BUNDLE_TOKEN_VALUE, INVALID_BUNDLE_TOKEN_MSG);
		msg.put(DEVICE_TOKEN_ALREADY_EXISTS_VALUE, DEVICE_TOKEN_ALREADY_EXISTS_MSG);
		msg.put(ANDROID_ID_ALREADY_EXISTS_VALUE, ANDROID_ID_ALREADY_EXISTS_MSG);
		msg.put(INVALID_GOOGLE_TOKEN_VALUE, INVALID_GOOGLE_TOKEN_MSG);
		msg.put(INVALID_DEVICE_TYPE_VALUE, INVALID_DEVICE_TYPE_MSG);
		msg.put(INVALID_JSON_REQUEST_FORMAT_REFER_MOBDB_DOCUMENTATION_VALUE, INVALID_JSON_REQUEST_FORMAT_REFER_MOBDB_DOCUMENTATION_MSG);
		msg.put(INVALID_REQUEST_FORMAT_VALUE, INVALID_REQUEST_FORMAT_MSG);
		msg.put(INVALID_DATA_TYPE_VALUE, INVALID_DATA_TYPE_MSG);
		msg.put(INVALID_PAYLOAD_VALUE, INVALID_PAYLOAD_MSG);
		msg.put(INVALID_DATE_TIME_VALUE, INVALID_DATE_TIME_MSG);
		msg.put(INVALID_FILE_KEY_VALUE, INVALID_FILE_KEY_MSG);

	}
		
}

