package com.MibacTechnologies.WebDataSaving;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Micha³ "mibac138" B¹czkowski
 * @since Creation date: 30 gru 2014 (01:23:19)
 */
/**
 * @author mibac138
 * 
 */
public class Client {
	private String url;

	public Client( String url ) {
		if ( url.endsWith( "?" ) )
			url = url.substring( 0, url.length( ) - 1 );

		this.url = url;
	}

	/**
	 * 
	 * @param args
	 * @return JSON (?) as String
	 */
	public String exec ( String... args ) {
		String url = this.url + "?";

		for ( String s : args ) {
			url += args;
		}

		return "";
	}

	public boolean isAvalible ( ) {
		try {
			URL urlServer = new URL( url );
			HttpURLConnection urlConn = (HttpURLConnection) urlServer
					.openConnection( );
			urlConn.setConnectTimeout( 3000 ); //<- 3Seconds Timeout 
			urlConn.connect( );
			if ( urlConn.getResponseCode( ) == 200 )
				return true;
			else
				return false;

		} catch ( MalformedURLException e ) {
			return false;
		} catch ( IOException e ) {
			return false;
		}
	}
}
