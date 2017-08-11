package com.crowdfunding.sjtu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//交易流水

@Entity
public class JournalTractional {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ournalTractionalId;
	private String wIDout_trade_no;
	private String wIDtotal_amount;
	private String wIDsubject;
	private String wIDbody;
	private String dateTime;
	private int status;
	private String out_trade_no;
	private String trade_no;
	private String trade_status;
	/**
	 * @return the ournalTractionalId
	 */
	public int getOurnalTractionalId() {
		return ournalTractionalId;
	}
	/**
	 * @param ournalTractionalId the ournalTractionalId to set
	 */
	public void setOurnalTractionalId(int ournalTractionalId) {
		this.ournalTractionalId = ournalTractionalId;
	}
	/**
	 * @return the wIDout_trade_no
	 */
	public String getwIDout_trade_no() {
		return wIDout_trade_no;
	}
	/**
	 * @param wIDout_trade_no the wIDout_trade_no to set
	 */
	public void setwIDout_trade_no(String wIDout_trade_no) {
		this.wIDout_trade_no = wIDout_trade_no;
	}
	/**
	 * @return the wIDtotal_amount
	 */
	public String getwIDtotal_amount() {
		return wIDtotal_amount;
	}
	/**
	 * @param wIDtotal_amount the wIDtotal_amount to set
	 */
	public void setwIDtotal_amount(String wIDtotal_amount) {
		this.wIDtotal_amount = wIDtotal_amount;
	}
	/**
	 * @return the wIDsubject
	 */
	public String getwIDsubject() {
		return wIDsubject;
	}
	/**
	 * @param wIDsubject the wIDsubject to set
	 */
	public void setwIDsubject(String wIDsubject) {
		this.wIDsubject = wIDsubject;
	}
	/**
	 * @return the dateTime
	 */
	public String getDateTime() {
		return dateTime;
	}
	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the wIDbody
	 */
	public String getwIDbody() {
		return wIDbody;
	}
	/**
	 * @param wIDbody the wIDbody to set
	 */
	public void setwIDbody(String wIDbody) {
		this.wIDbody = wIDbody;
	}
	/**
	 * @return the out_trade_no
	 */
	public String getOut_trade_no() {
		return out_trade_no;
	}
	/**
	 * @param out_trade_no the out_trade_no to set
	 */
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	/**
	 * @return the trade_no
	 */
	public String getTrade_no() {
		return trade_no;
	}
	/**
	 * @param trade_no the trade_no to set
	 */
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	/**
	 * @return the trade_status
	 */
	public String getTrade_status() {
		return trade_status;
	}
	/**
	 * @param trade_status the trade_status to set
	 */
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
}
