package com.zertones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "COM_CHAT_MESSAGES")
@JsonIgnoreProperties(
{ "hibernateLazyInitializer", "handler" })
public class ComChatMessages extends BaseModel implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CHAT_ID")
	private int		chatId;

	@Column(name = "String1")
	private String	textMsg;

	@Column(name = "SEND_BY")
	private int		fromId;

	@Column(name = "VIEW_ID")
	private int		toId;

	@Column(name = "VIEW_STS")
	private int		viewStatus;

	@Column(name = "CHAT_IMAGE")
	private byte[]	images;

	/*
	 * @ManyToOne
	 *
	 * @JoinColumn(name = "CLIENT_ID", nullable = true) private ComClientName
	 * comClientName;
	 */

	/*
	 * public ComClientName getComClientName() { return comClientName; }
	 *
	 * public void setComClientName(ComClientName comClientName) {
	 * this.comClientName = comClientName; }
	 */
	public byte[] getImages()
	{
		return images;
	}

	public void setImages(byte[] images)
	{
		this.images = images;
	}

	public int getChatId()
	{
		return chatId;
	}

	public void setChatId(int chatId)
	{
		this.chatId = chatId;
	}

	public String getTextMsg()
	{
		return textMsg;
	}

	public void setTextMsg(String textMsg)
	{
		this.textMsg = textMsg;
	}

	public int getFromId()
	{
		return fromId;
	}

	public void setFromId(int fromId)
	{
		this.fromId = fromId;
	}

	public int getToId()
	{
		return toId;
	}

	public void setToId(int toId)
	{
		this.toId = toId;
	}

	public int getViewStatus()
	{
		return viewStatus;
	}

	public void setViewStatus(int viewStatus)
	{
		this.viewStatus = viewStatus;
	}

	@Override
	public String toString()
	{
		return "ComChatMessages [ChatId=" + chatId + ", TextMsg=" + textMsg + ", FromId=" + fromId + ", ToId=" + toId
				+ ", ViewStatus=" + viewStatus + ",Image=" + images + "]";
	}

}
