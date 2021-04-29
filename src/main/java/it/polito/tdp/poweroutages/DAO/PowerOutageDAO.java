package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	public List<PowerOutage> getPowerOutageList() {

		String sql = "SELECT id,nerc_id,customers_affected,date_event_began,date_event_finished "
				+ "FROM poweroutages ";
		List<PowerOutage> poList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutage po=new PowerOutage(res.getInt("id"),res.getInt("nerc_id"),res.getInt("customers_affected"),res.getDate("date_event_began"),res.getDate("date_event_finished"),Duration.between(res.getTimestamp("date_event_finished").toLocalDateTime(), res.getTimestamp("date_event_began").toLocalDateTime()).getSeconds());
				poList.add(po);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return poList;
	}
	

}
