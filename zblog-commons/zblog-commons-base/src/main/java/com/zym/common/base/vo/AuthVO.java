package com.zym.common.base.vo;

import java.io.Serializable;


/**
 * 授权信息
 * @author gavin
 * @date 2016-09-28
 */
public class AuthVO implements Serializable
{
	private static final long serialVersionUID = -2820072026519595626L;
	
	private String accessToken;
	private Integer accessTokenExpires;
	private String refreshToken;
	private Integer refreshTokenExpires;
	
	public String getAccessToken()
	{
		return accessToken;
	}
	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}
	public Integer getAccessTokenExpires()
	{
		return accessTokenExpires;
	}
	public void setAccessTokenExpires(Integer accessTokenExpires)
	{
		this.accessTokenExpires = accessTokenExpires;
	}
	public String getRefreshToken()
	{
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken)
	{
		this.refreshToken = refreshToken;
	}
	public Integer getRefreshTokenExpires()
	{
		return refreshTokenExpires;
	}
	public void setRefreshTokenExpires(Integer refreshTokenExpires)
	{
		this.refreshTokenExpires = refreshTokenExpires;
	}
}
