package org.cf.catalog.util;

import java.util.HashMap;
import java.util.Map;

public class InterfaceFormat {

	public static Map<String, Object> getEgovplatformMsgHeader() {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		messageMap.put("serviceAccessToken", "H_serviceAccessToken");
		messageMap.put("seq",                "H_seq");	// 고유번호 생성규칙(20) = 송신시스템ID(2) + 년월일(8) + 일련번호(10)
		messageMap.put("msgId",              "H_msgId");	// 'C', 'R', 'U', 'D', 운영 입력 구분
		messageMap.put("crud",               "H_crud");
		messageMap.put("fromPartyId",        "H_fromPartyId");
		messageMap.put("toPartyId",          "H_toPartyId");
		messageMap.put("interfaceId",        "egovplatform.up.sc.1001");
		messageMap.put("msgPubUserId",       "H_msgPubUserId");
		messageMap.put("createDtm",          "H_createDtm");	// YYYYMMDDHH24MISS
		messageMap.put("processStatus",      "H_processStatus");	// 송신 : Defalut '0000' , 수신 : Defalut '0003'
		
		return messageMap;
	}
	
	/**
	 * 서비스 생성	egovplatform.up.sc.1001
	 * @return
	 */
	public static Map<String, Object> getCreate() {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		Map<String, Object> egovplatformMsgHeader = getEgovplatformMsgHeader();
		Map<String, Object> egovplatformMsgBody = new HashMap<String, Object>();
		egovplatformMsgBody.put("instance_nm",     "CREATE_B_instance_nm");
		egovplatformMsgBody.put("service_id",      "CREATE_B_service_id");
		egovplatformMsgBody.put("service_plan_id", "CREATE_B_service_plan_id");
		egovplatformMsgBody.put("space_id",        "CREATE_B_space_id");
		egovplatformMsgBody.put("platform_type",   "CREATE_B_platform_type");
		egovplatformMsgBody.put("service_type",    "CREATE_B_service_type");
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("password", "PARAM_password");
		parameter.put("port",     "PARAM_port");
		egovplatformMsgBody.put("parameter", parameter);
		
		messageMap.put("egovplatformMsgHeader", egovplatformMsgHeader);
		messageMap.put("egovplatformMsgBody", egovplatformMsgBody);
		return messageMap;
	}
	
	/**
	 * 서비스 수정	egovplatform.up.sc.1002
	 * @return
	 */
	public static Map<String, Object> getModify() {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		Map<String, Object> egovplatformMsgHeader = getEgovplatformMsgHeader();
		Map<String, Object> egovplatformMsgBody = new HashMap<String, Object>();
		egovplatformMsgBody.put("service_id",          "MODIFY_B_service_id");
		egovplatformMsgBody.put("service_instance_id", "MODIFY_B_service_instance_id");
		egovplatformMsgBody.put("service_plan_id",     "MODIFY_B_service_plan_id");
		egovplatformMsgBody.put("platform_type",       "MODIFY_B_platform_type");
		egovplatformMsgBody.put("service_type",        "MODIFY_B_service_type");
		
		messageMap.put("egovplatformMsgHeader", egovplatformMsgHeader);
		messageMap.put("egovplatformMsgBody", egovplatformMsgBody);
		return messageMap;
	}
	
	/**
	 * 서비스 삭제	egovplatform.up.sc.1003
	 * @return
	 */
	public static Map<String, Object> getDelete() {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		Map<String, Object> egovplatformMsgHeader = getEgovplatformMsgHeader();
		Map<String, Object> egovplatformMsgBody = new HashMap<String, Object>();
		egovplatformMsgBody.put("service_id",          "DELETE_B_service_id");
		egovplatformMsgBody.put("service_instance_id", "DELETE_B_service_instance_id");
		egovplatformMsgBody.put("platform_type",       "DELETE_B_platform_type");
		egovplatformMsgBody.put("service_type",        "DELETE_B_service_type");
		
		messageMap.put("egovplatformMsgHeader", egovplatformMsgHeader);
		messageMap.put("egovplatformMsgBody", egovplatformMsgBody);
		return messageMap;
	
	}
	
	/**
	 * 서비스 조회	egovplatform.up.sc.1004
	 * @return
	 */
	public static Map<String, Object> getSearch() {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		Map<String, Object> egovplatformMsgHeader = getEgovplatformMsgHeader();
		Map<String, Object> egovplatformMsgBody = new HashMap<String, Object>();
		egovplatformMsgBody.put("service_id",          "SEARCH_B_service_id");
		egovplatformMsgBody.put("service_instance_id", "SEARCH_B_service_instance_id");
		egovplatformMsgBody.put("platform_type",       "SEARCH_B_platform_type");
		egovplatformMsgBody.put("service_type",        "SEARCH_B_service_type");
		
		messageMap.put("egovplatformMsgHeader", egovplatformMsgHeader);
		messageMap.put("egovplatformMsgBody", egovplatformMsgBody);
		return messageMap;
	}
	
	/**
	 * 서비스 목록조회	egovplatform.mp.sc.1005
	 * @return
	 */
	public static Map<String, Object> getServiceList() {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		Map<String, Object> egovplatformMsgHeader = getEgovplatformMsgHeader();
		Map<String, Object> egovplatformMsgBody = new HashMap<String, Object>();
		egovplatformMsgBody.put("platform_type", "SERVICE_LIST_B_platform_type");
		egovplatformMsgBody.put("service_type",  "SERVICE_LIST_B_service_type");
		
		messageMap.put("egovplatformMsgHeader", egovplatformMsgHeader);
		messageMap.put("egovplatformMsgBody", egovplatformMsgBody);
		return messageMap;
	}
	
	/**
	 * 서비스 broker 목록조회	egovplatform.mp.sc.1006
	 * @return
	 */
	public static Map<String, Object> getBrokerList() {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		Map<String, Object> egovplatformMsgHeader = getEgovplatformMsgHeader();
		Map<String, Object> egovplatformMsgBody = new HashMap<String, Object>();
		egovplatformMsgBody.put("platform_type", "BROKER_LIST_B_platform_type");
		
		messageMap.put("egovplatformMsgHeader", egovplatformMsgHeader);
		messageMap.put("egovplatformMsgBody", egovplatformMsgBody);
		return messageMap;
	
	}
	
	/**
	 * 서비스 broker 등록	egovplatform.mp.sc.1007
	 * @return
	 */
	public static Map<String, Object> getBrokerReg() {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		Map<String, Object> egovplatformMsgHeader = getEgovplatformMsgHeader();
		Map<String, Object> egovplatformMsgBody = new HashMap<String, Object>();
		egovplatformMsgBody.put("platform_type",       "BROKER_REG_B_platform_type");
		egovplatformMsgBody.put("service_type",        "BROKER_REG_B_service_type");
		egovplatformMsgBody.put("service_broker_name", "BROKER_REG_B_service_broker_name");
		egovplatformMsgBody.put("service_broker_url",  "BROKER_REG_B_service_broker_url");
		
		messageMap.put("egovplatformMsgHeader", egovplatformMsgHeader);
		messageMap.put("egovplatformMsgBody", egovplatformMsgBody);
		return messageMap;
	}
	
	/**
	 * 서비스브로커 이용정보 조회	egovplatform.mp.sc.1008
	 * @return
	 */
	public static Map<String, Object> getBrokerUseInfo() {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		Map<String, Object> egovplatformMsgHeader = getEgovplatformMsgHeader();
		Map<String, Object> egovplatformMsgBody = new HashMap<String, Object>();
		egovplatformMsgBody.put("platform_type", "BROKER_USER_INFO_B_platform_type");
		egovplatformMsgBody.put("start_dagte",   "BROKER_USER_INFO_B_start_dagte");	// yyyymmdd
		egovplatformMsgBody.put("end_date",      "BROKER_USER_INFO_B_end_date");	// yyyymmdd
		
		messageMap.put("egovplatformMsgHeader", egovplatformMsgHeader);
		messageMap.put("egovplatformMsgBody", egovplatformMsgBody);
		return messageMap;
	}
	
	/**
	 * 플랫폼 별 서비스 이용정보 조회	egovplatform.mp.sc.1009
	 * @return
	 */
	public static Map<String, Object> getPlatformUseInfo() {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		Map<String, Object> egovplatformMsgHeader = getEgovplatformMsgHeader();
		Map<String, Object> egovplatformMsgBody = new HashMap<String, Object>();
		egovplatformMsgBody.put("platform_type", "PLATFORM_USER_INFO_B_platform_type");
		egovplatformMsgBody.put("service_type",  "PLATFORM_USER_INFO_B_service_type");
		egovplatformMsgBody.put("start_dagte",   "PLATFORM_USER_INFO_B_start_dagte");	// yyyymmdd
		egovplatformMsgBody.put("end_date",      "PLATFORM_USER_INFO_B_end_date");	// yyyymmdd
		
		messageMap.put("egovplatformMsgHeader", egovplatformMsgHeader);
		messageMap.put("egovplatformMsgBody", egovplatformMsgBody);
		return messageMap;
	}
}
