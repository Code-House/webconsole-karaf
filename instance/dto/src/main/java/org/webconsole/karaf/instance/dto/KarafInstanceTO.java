package org.webconsole.karaf.instance.dto;

import java.io.Serializable;

public class KarafInstanceTO implements Serializable
{
	private final String name;
	private final Integer pid;
	private final String state;

	public KarafInstanceTO(String name, Integer pid, String state)
	{
		this.name = name;
		this.pid = pid;
		this.state = state;
	}

	public String getName()
	{
		return name;
	}

	public Integer getPid()
	{
		return pid;
	}

	public String getState()
	{
		return state;
	}

}
