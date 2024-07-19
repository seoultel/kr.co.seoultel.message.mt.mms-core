package kr.co.seoultel.message.mt.mms.core.message.direct;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.soap.MCMPSoapRenderException;
import kr.co.seoultel.message.mt.mms.core.messages.direct.SoapMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.hazelcast.jet.core.test.JetAssert.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SoapMessageTest {

    private Document document;
    private Element parentElement;


    @BeforeEach
    void setUp() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();

        parentElement = document.createElement("parent");
        document.appendChild(parentElement);
    }



    @Test
    @DisplayName("*MC-SMT-1*")
    void testSoapMessageConstructorThrowsMCMPSoapRenderException() {
        // Mock the static method MessageFactory.newInstance() to throw an exception
        try (MockedStatic<MessageFactory> messageFactoryMock = Mockito.mockStatic(MessageFactory.class)) {
            // When MessageFactory.newInstance() is called, throw a SOAPException
            messageFactoryMock.when(MessageFactory::newInstance).thenThrow(new SOAPException("Test exception"));

            // Define an executable that attempts to create a new instance of the abstract SoapMessage class
            Executable constructorInvocation = () -> new SoapMessage() {
                @Override
                public SOAPMessage toSOAPMessage() {
                    return null; // No operation implementation for this test
                }

                @Override
                public void fromSOAPMessage(SOAPMessage soapMessage) {
                    // No operation implementation for this test
                }
            };

            // Assert that the constructor invocation throws an MCMPSoapRenderException
            MCMPSoapRenderException thrownException = assertThrows(MCMPSoapRenderException.class, constructorInvocation);
            // Assert that the exception message is as expected
            assertEquals("[SOAP] Fail to create soap factory", thrownException.getMessage());
        }
    }

    @Test
    @DisplayName("*MC-SMT-2*")
    void testGetElementValueReturnsChildElementValue() {
        try {
            // Arrange
            String tagName = "CHILD";
            String value = "test value";

            // create tag -> <CHILD></CHILD>
            Element childElement = document.createElement(tagName);

            childElement.setTextContent(value); // add text in child-element <CHILD>test vaule</CHILD>;
            parentElement.appendChild(childElement); // add child-element to parent element;

            // create anonymous SoapMessage instance;
            SoapMessage soapMessage = new SoapMessage() {
                @Override
                public SOAPMessage toSOAPMessage() {
                    return null; // No operation implementation for this test
                }

                @Override
                public void fromSOAPMessage(SOAPMessage soapMessage) {
                    // No operation implementation for this test
                }
            };

            //
            String result = soapMessage.getElementValue(parentElement, tagName);

            // Assert
            assertEquals(value, result);
        } catch (MCMPSoapRenderException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("*MC-SMT-3*")
    void testGetElementValueThrowsExceptionIfChildElementNotFound() {
        try {
            // Arrange
            String tagName = "child";

            // Act
            SoapMessage soapMessage = new SoapMessage() {
                @Override
                public SOAPMessage toSOAPMessage() {
                    return null; // No operation implementation for this test
                }

                @Override
                public void fromSOAPMessage(SOAPMessage soapMessage) {
                    // No operation implementation for this test
                }
            };

            MCMPSoapRenderException thrownException = assertThrows(MCMPSoapRenderException.class, () -> {
                soapMessage.getElementValue(parentElement, tagName);
            });

            // Assert
            assertEquals(String.format("[SOAP] Fail to find tag[%s] in element[%s]", tagName, parentElement), thrownException.getMessage());
        } catch (MCMPSoapRenderException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }
}
