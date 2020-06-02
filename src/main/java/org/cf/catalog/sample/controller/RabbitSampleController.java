package org.cf.catalog.sample.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.cf.catalog.rabbitmq.RabbitMQMessage;
import org.cf.catalog.util.InterfaceFormat;
import org.cf.catalog.util.JsonUtil;

@Controller
@RequestMapping("/service_brokers")
public class RabbitSampleController {
	
	@Autowired
	private RabbitTemplate rabbitTemplate; 

	// /rabbit-sample/messageTest/
	/*
	 * {
			"service_id": "54e2de61-de84-4b9c-afc3-88d08aadfcb6",
			"plan_id": "efdcab49-1364-43c2-8bbd-c0d939522371",
			"context": {
				"platform": "cloudfoundry",
				"properties":{
					"organization_guid": "0e47bc7f-9f16-47ca-a96b-77c1f54ca5e5",
					"organization_name": "jin-org",
					"space_guid": "4cd1d3bd-ddb7-43f2-a0f2-ca8b294413e8",
					"space_name": "jin-space",
					"instance_name": "redis-hoho"
				}
			},
			"parameters": {
				"vm_type": "small",
				"persistent_disk_type": "2GB",
				"properties": {
					"desc": "redis"
				}
			}
		}
	 * 
	 * 
	 * */
	@GetMapping("/messageTest/{testValue}")
	public void rabbitSample(@PathVariable String testValue) throws Exception {
		System.out.println("/// RabbitMQ Message Test //");
		System.out.println(rabbitTemplate);
		
		RabbitMQMessage message = new RabbitMQMessage("Hello 2 Message! " + testValue, 1, true);
		
		Map<String, Object> queueMessageMap = new LinkedHashMap<String, Object>();

		
		Map<String, Object> contextPropMap = new LinkedHashMap<String, Object>();
		contextPropMap.put("organization_guid", "0e47bc7f-9f16-47ca-a96b-77c1f54ca5e5");
		contextPropMap.put("organization_name", "jin-org");
		contextPropMap.put("space_guid", "4cd1d3bd-ddb7-43f2-a0f2-ca8b294413e8");
		contextPropMap.put("space_name", "jin-space");
		contextPropMap.put("instance_name", "redis-haha");
		
		Map<String, Object> contextMap = new LinkedHashMap<String, Object>();
		contextMap.put("platform", "cloudfoundry");
		contextMap.put("properties", contextPropMap);
		
		
		Map<String, Object> parameterPropMap = new LinkedHashMap<String, Object>();
		parameterPropMap.put("desc", "redis");

		Map<String, Object> parameterMap = new LinkedHashMap<String, Object>();
		parameterMap.put("vm_type", "small");
		parameterMap.put("persistent_disk_type", "2GB");
		parameterMap.put("properties", parameterPropMap);
		
		queueMessageMap.put("serviceType", "redis");
		
		if("A".equals(testValue)) {
			queueMessageMap.put("requestType", "SR"); // ���� ���� ��û
		} else if("B".equals(testValue)) {
			queueMessageMap.put("requestType", "SS"); // ���� ���� ���� ��ȸ
		} else if("C".equals(testValue)) {
			queueMessageMap.put("requestType", "SM"); // ���� ����
		} else if("D".equals(testValue)) {
			queueMessageMap.put("requestType", "SD"); // ���� ����
		}
		
		queueMessageMap.put("service_id", "54e2de61-de84-4b9c-afc3-88d08aadfcb6");
		queueMessageMap.put("plan_id", "efdcab49-1364-43c2-8bbd-c0d939522371");
		queueMessageMap.put("request_id", testValue);
		queueMessageMap.put("context", contextMap);
		queueMessageMap.put("parameters", parameterMap);
		
		System.out.println(queueMessageMap);
		
//		rabbitTemplate.convertAndSend("catalog-service-broker-exchange", "foo.bar.baz", message);
		rabbitTemplate.convertAndSend("catalog-service-broker-exchange", "service.broker.msg", JsonUtil.jsonStrFromObject(queueMessageMap));
	}
	
	@GetMapping("/serviceList")
	public void selectPortalServiceList() throws Exception {
		System.out.println("////// SERVICE LIST //////");
		Map<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("requestType", "SL"); // consumer 쪽 Service 클래스에 serviceRequest 메소드 호출, Constants 참고
		
		rabbitTemplate.convertAndSend("catalog-service-broker-exchange", "service.broker.msg", JsonUtil.jsonStrFromObject(bodyMap));
	}
	
	
	
	@PutMapping("/{serviceType}")
	public void requestInstance(@RequestBody HashMap<String, Object> bodyMap
			, @PathVariable String serviceType) throws Exception {

		try {
			System.out.println("=============================================");
			System.out.println(JsonUtil.jsonStrFromObject(bodyMap));
			
			rabbitTemplate.convertAndSend("catalog-service-broker-exchange", "service.broker.msg", JsonUtil.jsonStrFromObject(bodyMap));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@DeleteMapping("/{serviceType}/{instanceId}")
	public void deleteInstance(@PathVariable String serviceType
			, @PathVariable String instanceId) throws Exception {
		Map<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("requestType", "SD"); // consumer 쪽 Service 클래스에 serviceDelete 메소드 호출, Constants 참고
		bodyMap.put("serviceType", serviceType);
		bodyMap.put("instanceId", instanceId);
		
		rabbitTemplate.convertAndSend("catalog-service-broker-exchange", "service.broker.msg", JsonUtil.jsonStrFromObject(bodyMap));
	}
	
	@PutMapping("/{serviceType}/{instanceId}")
	public void updateInstance(@RequestBody HashMap<String, Object> bodyMap
			, @PathVariable String serviceType
			, @PathVariable String instanceId) throws Exception {
		
		bodyMap.put("requestType", "SM"); // consumer 쪽 Service 클래스에 serviceModify 메소드 호출, Constants 참고
		bodyMap.put("serviceType", serviceType);
		bodyMap.put("instanceId", instanceId);
		
		rabbitTemplate.convertAndSend("catalog-service-broker-exchange", "service.broker.msg", JsonUtil.jsonStrFromObject(bodyMap));
	}
	
	@GetMapping("/document/{type}")
	public void documentTest(@PathVariable int type) throws Exception {
		Map<String, Object> documentMap = null;
		
		if(type == 1) {
			documentMap = InterfaceFormat.getCreate();
		} else if(type == 2) {
			documentMap = InterfaceFormat.getModify();
		} else if(type == 3) {
			documentMap = InterfaceFormat.getDelete();
		} else if(type == 4) {
			documentMap = InterfaceFormat.getSearch();
		} else if(type == 5) {
			documentMap = InterfaceFormat.getServiceList();
		} else if(type == 6) {
			documentMap = InterfaceFormat.getBrokerList();
		} else if(type == 7) {
			documentMap = InterfaceFormat.getBrokerReg();
		} else if(type == 8) {
			documentMap = InterfaceFormat.getBrokerUseInfo();
		} else if(type == 9) {
			documentMap = InterfaceFormat.getPlatformUseInfo();
		}
		
		System.out.println("///// DOCUMENT MAP VALUES //////");
		System.out.println(documentMap);
		
//		rabbitTemplate.convertAndSend("catalog-service-broker-exchange", "service.broker.msg", JsonUtil.jsonStrFromObject(documentMap));
		rabbitTemplate.convertAndSend("catalog-service-broker-exchange", "service.broker.msg", documentMap);
	}
}
