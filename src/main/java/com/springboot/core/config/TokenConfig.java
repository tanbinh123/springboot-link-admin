package com.springboot.core.config;

public class TokenConfig {
	/** token名称 (同时也是cookie名称) */
	private String tokenName = "LinkAdmin-Token";

	/** token的长久有效期(单位:秒) 默认30天, -1代表永久 */
	private long timeout = 60 * 60 * 24 * 30;

	/** 是否尝试从请求体里读取token */
	private Boolean isReadBody = true;

	/** 是否尝试从header里读取token */
	private Boolean isReadHead = true;

	/** 是否尝试从cookie里读取token */
	private Boolean isReadCookie = true;

	private String privateKey;

	public Boolean getIsReadBody() {
		return isReadBody;
	}

	public void setIsReadBody(Boolean isReadBody) {
		this.isReadBody = isReadBody;
	}

	public Boolean getIsReadHead() {
		return isReadHead;
	}

	public void setIsReadHead(Boolean isReadHead) {
		this.isReadHead = isReadHead;
	}

	public Boolean getIsReadCookie() {
		return isReadCookie;
	}

	public void setIsReadCookie(Boolean isReadCookie) {
		this.isReadCookie = isReadCookie;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	/**
	 * @return token名称 (同时也是cookie名称)
	 */
	public String getTokenName() {
		return tokenName;
	}

	/**
	 * @param tokenName
	 *            token名称 (同时也是cookie名称)
	 * @return 对象自身
	 */
	public TokenConfig setTokenName(String tokenName) {
		this.tokenName = tokenName;
		return this;
	}

	/**
	 * @return token的长久有效期(单位:秒) 默认30天, -1代表永久
	 */
	public long getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout
	 *            token的长久有效期(单位:秒) 默认30天, -1代表永久
	 * @return 对象自身
	 */
	public TokenConfig setTimeout(long timeout) {
		this.timeout = timeout;
		return this;
	}

	public TokenConfig setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
		return this;
	}

	@Override
	public String toString() {
		return "TokenConfig [" + "tokenName=" + tokenName + ", timeout="
				+ timeout + ", isReadBody=" + isReadBody + ", isReadHead="
				+ isReadHead + ", isReadCookie=" + isReadCookie + "]";
	}

}
