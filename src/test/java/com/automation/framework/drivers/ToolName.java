package com.automation.framework.drivers;

import com.automation.framework.FrameworkException;

public enum ToolName {
	/**
	 * Default platform
	 */
	DEFAULT("Default"),
	
	/**
	 * Use Appium for execution
	 */
	APPIUM("Appium"),
	
	/**
	 * Use Remote WebDriver for execution
	 */
	REMOTE_WEBDRIVER("Remote_Webdriver");
	
	 /**
	   * Gets a platform with the name matching the parameter.
	   *
	   * @param name the platform name
	   * @return the Platform enum value matching the parameter
	   */
	  public static ToolName fromString(String name) {
	    for (ToolName toolName : values()) {
	      if (toolName.toString().equalsIgnoreCase(name)) {
	        return toolName;
	      }
	    }

	   throw new FrameworkException("Unrecognized platform: " + name);
	  }
	  private String value;
		
	  ToolName(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
}
