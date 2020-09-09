package com.automation.framework.drivers;

import com.automation.framework.FrameworkException;

public enum MobileExecutionPlatform {
	
	/**
	 * Execute on an Android Device
	 */
	ANDROID("Android"),
	
	/**
	 * Execute on an iOS Device
	 */
	IOS("Ios"),
	/**
	 * Execute on a browser in Android Device 
	 */
	WEB_ANDROID("Web_Android"),
	
	/**
	 * Execute on browser in iOS Device
	 */
	WEB_IOS("Web_Ios");
	

	  /**
	   * Gets a platform with the name matching the parameter.
	   *
	   * @param name the platform name
	   * @return the Platform enum value matching the parameter
	   */
	  public static MobileExecutionPlatform fromString(String name) {
	    for (MobileExecutionPlatform platform : values()) {
	      if (platform.toString().equalsIgnoreCase(name)) {
	        return platform;
	      }
	    }

	   throw new FrameworkException("Unrecognized platform: " + name);
	  }
	  private String value;
		
		MobileExecutionPlatform(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
}
