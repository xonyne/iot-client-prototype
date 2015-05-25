package ch.keutsa.prototype.logic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLHelper {

	public static void saveInstance(File outputFile, Object instance)
			throws JAXBException, IOException {
		Marshaller marshaller = JAXBContext.newInstance(instance.getClass())
				.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(instance, outputFile);
	}
	
	public static Object loadInstance(InputStream inputStream, Class instanceClass) throws JAXBException {
		Unmarshaller unmarshaller = JAXBContext.newInstance(instanceClass).createUnmarshaller();
		Object object = unmarshaller.unmarshal(inputStream);
		return object;
	}

}
