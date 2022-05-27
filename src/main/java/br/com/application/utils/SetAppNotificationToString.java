package br.com.application.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

import br.com.application.enums.user.AppNotificationType;



@Convert
public class SetAppNotificationToString implements AttributeConverter<Set<AppNotificationType>, String> {

    @Override
    public String convertToDatabaseColumn(Set<AppNotificationType> appNotifications) {
        if (appNotifications.isEmpty()) return "";
        String listConcat = appNotifications.stream().map(appNotification -> appNotification.name()).collect(Collectors.joining(","));
        return listConcat;
    }

    @Override
    public Set<AppNotificationType> convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) return new HashSet<>();
         Set<AppNotificationType> appNotifications = Arrays.asList(s.split(","))
        		.stream()
        		.map(name -> {
		        	try{
		        		return AppNotificationType.valueOf(name);        		
		        	}catch (Exception e) {
		        		throw new AplicationException("APP_NOTIFICATION_TYPE_CONVERT", "Error App Notification Convert", ("AppNotificationType " + name + " is not type in sistem"));
		        	}
        		})
        		.collect(Collectors.toSet());
        return appNotifications;
    }
}
