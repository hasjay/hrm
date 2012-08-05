package com.hrm.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.hrm.ws.schema.bean.employee.Employee;

public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	JmsTemplate jmsTemplate;
	
	@Override
	public void sendMessage(Employee employee) {
		jmsTemplate.send(composeMessage(employee));
	}
	
	private MessageCreator composeMessage(final Employee employee){
		return new MessageCreator(){
			public Message createMessage(Session session)throws JMSException{
				ObjectMessage message = session.createObjectMessage();
				message.setObject(employee);
				return message;
			}
		};
	}
}
