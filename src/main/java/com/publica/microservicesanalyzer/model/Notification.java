package com.publica.microservicesanalyzer.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Uma notificação enviada
 * @author Ariel Rai Rodrigues(arielrairodrigues@gmail.com)
 *
 */
@Entity
@Table(name="notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ds_message")
	private String message;
	
	@Column(name = "dt_sent")
	private Date dateSent;
	
	@ManyToMany
	private List<RegisteredDevice> devices;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<RegisteredDevice> getDevices() {
		return devices;
	}

	public void setDevices(List<RegisteredDevice> devices) {
		this.devices = devices;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}
}
