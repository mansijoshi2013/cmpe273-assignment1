package edu.sjsu.cmpe.library.constants;

public enum StatusEnum {
	available("available"), 
	checkedOut("checked-out"), 
	inQueue("in-queue"), 
	lost("lost");
	
	private String status;
	
	StatusEnum(String status){
		this.status = status;
	}
	
	public String getName(){
		return status;
	}
	
	public static StatusEnum getStatusByName(String status){
		if(status.equalsIgnoreCase("checked-out"))
			return StatusEnum.checkedOut;
		else if(status.equalsIgnoreCase("in-queue"))
			return StatusEnum.inQueue;
		else if(status.equalsIgnoreCase("lost"))
			return StatusEnum.lost;
		else
			return StatusEnum.available;
	}
}
