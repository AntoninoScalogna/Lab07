package it.polito.tdp.poweroutages.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List<PowerOutage> migliore;
	private int maxClienti=0;
	
	public List<PowerOutage> getMigliore() {
		return migliore;
	}

	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	public List<PowerOutage> getPoList(Nerc n){
		List<PowerOutage> po=new LinkedList<PowerOutage>();
		for(PowerOutage p:podao.getPowerOutageList())
			if(p.getNerc_id()==n.getId())
				po.add(p);
		Collections.sort(po,(po1,po2)->po1.getInizio().compareTo(po2.getInizio()));
		return po;
	}
	
	public void cercaSolMigliore (int annoX, long oreY,Nerc n) {
		migliore=new LinkedList<PowerOutage>();
		List<PowerOutage> parziale=new LinkedList<PowerOutage>();
		
		ricorsivo(parziale,0,annoX,oreY,migliore,n);
	}

	
	
	public int getMaxClienti() {
		return maxClienti;
	}

	public void ricorsivo(List<PowerOutage> parziale, int livello, int annoX, long oreY, List<PowerOutage>migliore, Nerc n) {
		/*if(calcolaAnni(parziale)>=annoX)
			
			return;*/
		if(livello==getPoList(n).size())
			return;
		if(calcolaOre(parziale)>=oreY)
			return;
		/*if(calcolaOre(parziale)==oreY) {
			if(calcolaClienti(parziale)>maxClienti)
				maxClienti=calcolaClienti(parziale);
			    migliore=parziale;	
		}*/
		
		for(PowerOutage p: getPoList(n)) {
			if(!parziale.contains(p)) {
				parziale.add(p);
				if(calcolaClienti(parziale)>maxClienti && calcolaAnni(parziale)<=annoX) {
					migliore=new LinkedList<PowerOutage>(parziale);
					maxClienti=calcolaClienti(parziale);
				}
				ricorsivo(parziale, livello+1, annoX, oreY, migliore,n);
				parziale.remove(parziale.size()-1);
			}}
		
		
	}
	
	public int calcolaClienti(List<PowerOutage> parziale) {
		int numClienti=0;
		for(PowerOutage p:parziale)
			numClienti=numClienti+p.getCustomers_affected();
		return numClienti;
	}

	public Long calcolaAnni(List<PowerOutage> po) {
		long mill=po.get(po.size()-1).getFine().getTime()-po.get(0).getInizio().getTime();
		mill=mill/1000;
		mill=mill/60;
		mill=mill/60;
		mill=mill/24;
		mill=mill/365;
		return mill;
	}
	public Long calcolaOre(List<PowerOutage> po) {
		long durataTot=0;
		for(PowerOutage p:po)
			durataTot=durataTot+p.getDurata();
		return durataTot;
	}
	
	public String miglioreToString(List<PowerOutage> list) {
		String s="";
		for(PowerOutage p:list)
			s=s+p.getInizio()+"; "+p.getInizio()+"; "+p.getDurata()+"; "+p.getCustomers_affected()+"\n";
		return s;
	}

}
