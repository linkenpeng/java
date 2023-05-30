package com.intecsec.java.basic.example.testcard.admission.data;

import com.intecsec.java.basic.example.testcard.admission.po.AdmissionTicket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdmissionDao {
	/**
	 *  批量添加准考证
	 * @param tickets
	 * @return
	 */
	public static int addAll(List<AdmissionTicket> tickets) {
		Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            //构建数据库执行者
            PreparedStatement p = conn.prepareStatement("INSERT INTO `t_admission_ticket`(`name`, `candidate_number`, `id_number`, `gender`, `subject`, `address`, `time`, `seat`, `email`) " +
                    "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,?);");
	        for (AdmissionTicket at : tickets) {
	    	   p.setString(1, at.getName());
	    	   p.setString(2, at.getCandidateNumber());
	    	   p.setString(3, at.getIdNumber());
	    	   p.setString(4, at.getGender());
	    	   p.setString(5, at.getSubject());
	    	   p.setString(6, at.getAddress());
	    	   p.setString(7, at.getTime());
	    	   p.setString(8, at.getSeat());
	    	   p.setString(9, at.getEmail());
	    	   p.addBatch();
	        }
	        p.executeBatch();
	        conn.commit();
	        p.close();
        } catch (Exception e){
            e.printStackTrace();
            try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            return -1;
        } finally {
        	try	{
        		if(null != conn) {
            		conn.close();
            	}
        	} catch (SQLException e){
                e.printStackTrace();
        	}        	
        }
        return 0;
	}


	/**
	 * 批量获取准考证
	 * @return
	 */
	public static List<AdmissionTicket> getAll() {
		List<AdmissionTicket> tickets = new ArrayList<>();
		Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            PreparedStatement p = conn.prepareStatement("select `name`, `candidate_number`, `id_number`, `gender`, `subject`, `address`, `time`, `seat`, `email` from `t_admission_ticket` ;");
            ResultSet rs = p.executeQuery();
            while(rs.next()){
            	AdmissionTicket ticket = new AdmissionTicket();
            	ticket.setName(rs.getString("name"));
            	ticket.setCandidateNumber(rs.getString("candidate_number"));
            	ticket.setIdNumber(rs.getString("id_number"));
            	ticket.setGender(rs.getString("gender"));
            	ticket.setSubject(rs.getString("subject"));
            	ticket.setAddress(rs.getString("address"));
            	ticket.setTime(rs.getString("time"));
            	ticket.setSeat(rs.getString("seat"));
            	ticket.setEmail(rs.getString("email"));
            	tickets.add(ticket);
            }
            p.close();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
        	try	{
        		if(null != conn) {
            		conn.close();
            	}
        	} catch (SQLException e){
                e.printStackTrace();
        	}        	
        }
        return tickets;
	}
}
