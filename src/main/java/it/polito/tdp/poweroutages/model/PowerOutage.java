package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.util.Date;

public class PowerOutage {
	
	Integer id;
	Integer nerc_id;
	Integer customers_affected;
	Date inizio;
	Date fine;
	Long durata;
	
	
	
	public PowerOutage(Integer id, Integer nerc_id, Integer customers_affected, Date inizio, Date fine,Long durata) {
		this.inizio=inizio;
		this.fine=fine;
		this.id = id;
		this.nerc_id = nerc_id;
		this.customers_affected = customers_affected;
		this.durata=durata/60;
	}
	public Long getDurata() {
		return durata;
	}
	public void setDurata(Long durata) {
		this.durata = durata;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNerc_id() {
		return nerc_id;
	}
	public void setNerc_id(Integer nerc_id) {
		this.nerc_id = nerc_id;
	}
	public Integer getCustomers_affected() {
		return customers_affected;
	}
	public void setCustomers_affected(Integer customers_affected) {
		this.customers_affected = customers_affected;
	}
	public Date getInizio() {
		return inizio;
	}
	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}
	public Date getFine() {
		return fine;
	}
	public void setFine(Date fine) {
		this.fine = fine;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
