package com.supra.dto;

public class Notification {
	 private String title;
	 private String body;
	 private String sound;
	 private String click_action;
	
	public Notification() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getClick_action() {
		return click_action;
	}

	public void setClick_action(String click_action) {
		this.click_action = click_action;
	}

	@Override
	public String toString() {
		return "Notification [title=" + title + ", body=" + body + ", sound=" + sound + ", click_action=" + click_action
				+ "]";
	}
	

}
