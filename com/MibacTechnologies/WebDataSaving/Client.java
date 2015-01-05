package com.MibacTechnologies.WebDataSaving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Michał "mibac138" Bączkowski
 * @since Creation date: 30 dec 2014 (01:23:19)
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
	 *            Arguments of URL (define it in constructor)<br>
	 *            Format: "key=value&key2=value2&..."
	 * @return JSON (?) as String. When error occurs null
	 */
	public String exec ( String args ) {
		if ( !isAvailable( ) )
			return "";

		try {
			String url = this.url + "?" + ( args.isEmpty( ) ? "" : args );
			URL site = new URL( url );

			BufferedReader in = new BufferedReader( new InputStreamReader(
					site.openStream( ) ) );

			String data = "";
			Pattern pattern = Pattern.compile( "\\{.*\\}", Pattern.DOTALL );
			Matcher matcher;
			String inputLine;
			while ( ( inputLine = in.readLine( ) ) != null ) {
				matcher = pattern.matcher( inputLine );
				if ( matcher.find( ) ) {
					data = matcher.group( );
					break;
				}
			}

			in.close( );

			if ( data == null )
				return "";

			return data;
		} catch ( IOException e ) {
			return null;
		}
	}

	/**
	 * 
	 * @param timeout
	 *            in milliseconds
	 * @return true, if URL is available, otherwise false
	 */
	public boolean isAvailable ( int timeout ) {
		try {
			URL urlServer = new URL( url );
			HttpURLConnection urlConn = (HttpURLConnection) urlServer
					.openConnection( );
			urlConn.setConnectTimeout( timeout );
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

	/**
	 * 
	 * @return this.isAvailable(2000) (2s)
	 */
	public boolean isAvailable ( ) {
		return isAvailable( 2000 );
	}
}
