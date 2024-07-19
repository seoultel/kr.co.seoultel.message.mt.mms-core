package kr.co.seoultel.message.mt.mms.core.message.direct.skt.exception;

import jakarta.xml.soap.*;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.soap.MCMPSoapRenderException;
import kr.co.seoultel.message.mt.mms.core.messages.direct.skt.SktSubmitReqMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.xml.namespace.QName;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SktSubmitReqMessageExceptionTest {

    @Test
    @DisplayName("MC-SSReqME-1 : Test NoArgConstructor throws MCMPSoapRenderException when MessageFactory.newInstance throws SOAPException")
    void testNoArgConstructor() {
        // Mock the static method MessageFactory.newInstance() to throw an exception
        try (MockedStatic<MessageFactory> messageFactoryMock = Mockito.mockStatic(MessageFactory.class)) {
            // When MessageFactory.newInstance() is called, throw a SOAPException
            messageFactoryMock.when(MessageFactory::newInstance).thenThrow(new SOAPException("Test exception"));

            // Define the parameters for the SktSubmitReqMessage constructor
            String vaspId = "testVaspId";
            String vasId = "testVasId";
            String cpid = "testCpid";
            String tid = "testTid";
            String callback = "testCallback";
            String receiver = "testReceiver";
            String subject = "testSubject";
            String message = "testMessage";
            String originCode = "testOriginCode";
            String startCID = "testStartCID";
            String smilCID = "testSmilCID";

            // Assert that the constructor invocation throws an MCMPSoapRenderException
            MCMPSoapRenderException thrownException = assertThrows(MCMPSoapRenderException.class, SktSubmitReqMessage::new);

            // Assert that the exception message is as expected
            assertEquals("[SOAP] Fail to create soap factory, cause : null", thrownException.getMessage());
        }
    }

    @Test
    @DisplayName("MC-SSReqME-2 : Test AllArgConstructor throws MCMPSoapRenderException when MessageFactory.newInstance throws SOAPException")
    void testAllArgConstructor() {
        // Mock the static method MessageFactory.newInstance() to throw an exception
        try (MockedStatic<MessageFactory> messageFactoryMock = Mockito.mockStatic(MessageFactory.class)) {
            // When MessageFactory.newInstance() is called, throw a SOAPException
            messageFactoryMock.when(MessageFactory::newInstance).thenThrow(new SOAPException("Test exception"));

            // Define the parameters for the SktSubmitReqMessage constructor
            String vaspId = "testVaspId";
            String vasId = "testVasId";
            String cpid = "testCpid";
            String tid = "testTid";
            String callback = "testCallback";
            String receiver = "testReceiver";
            String subject = "testSubject";
            String message = "testMessage";
            String originCode = "testOriginCode";
            String startCID = "testStartCID";
            String smilCID = "testSmilCID";

            // Assert that the constructor invocation throws an MCMPSoapRenderException
            MCMPSoapRenderException thrownException = assertThrows(MCMPSoapRenderException.class, () ->
                    new SktSubmitReqMessage(vaspId, vasId, cpid, tid, callback, receiver, subject, message, originCode, startCID, smilCID)
            );

            // Assert that the exception message is as expected
            assertEquals("[SOAP] Fail to create soap factory, cause : null", thrownException.getMessage());
        }
    }

    @Test
    @DisplayName("MC-SSReqME-3 : Test toSOAPMessage throws MCMPSoapRenderException when setting properties throws Exception")
    void testToSOAPMessageWhenSettingPropertiesThrowsException() throws Exception {
        // Mock MessageFactory and SOAPMessage to simulate exceptions
        try (MockedStatic<MessageFactory> messageFactoryMock = Mockito.mockStatic(MessageFactory.class)) {
            MessageFactory mockedFactory = Mockito.mock(MessageFactory.class);
            SOAPMessage mockedSOAPMessage = Mockito.mock(SOAPMessage.class);

            // Simulate an exception when setting properties
            Mockito.when(mockedFactory.createMessage()).thenReturn(mockedSOAPMessage);
            Mockito.doThrow(new SOAPException("Test exception setting property"))
                    .when(mockedSOAPMessage).setProperty(anyString(), any());

            // Set up the instance of SktSubmitReqMessage
            SktSubmitReqMessage message = new SktSubmitReqMessage(
                    "vaspId", "vasId", "cpid", "tid", "callback", "receiver",
                    "subject", "message", "originCode", "startCID", "smilCID"
            );

            // Assert that the method throws MCMPSoapRenderException
            MCMPSoapRenderException thrownException = assertThrows(MCMPSoapRenderException.class, () -> {
                message.toSOAPMessage();
            });

            // Assert the exception message
            assertTrue(thrownException.getMessage().startsWith("[SOAP] Fail to create"));
        }
    }

    @Test
    @DisplayName("MC-SSReqME-4 : Test toSOAPMessage throws MCMPSoapRenderException when adding SOAP elements throws Exception")
    void testToSOAPMessageWhenAddingSOAPElementsThrowsException() throws Exception {
        // Mock MessageFactory and SOAPMessage to simulate exceptions
        try (MockedStatic<MessageFactory> messageFactoryMock = Mockito.mockStatic(MessageFactory.class)) {
            MessageFactory mockedFactory = Mockito.mock(MessageFactory.class);
            SOAPMessage mockedSOAPMessage = Mockito.mock(SOAPMessage.class);
            SOAPPart mockedSOAPPart = Mockito.mock(SOAPPart.class);
            SOAPEnvelope mockedEnvelope = Mockito.mock(SOAPEnvelope.class);
            SOAPHeader mockedSOAPHeader = Mockito.mock(SOAPHeader.class);
            SOAPBody mockedSOAPBody = Mockito.mock(SOAPBody.class);
            SOAPBodyElement mockedSOAPBodyElement = Mockito.mock(SOAPBodyElement.class);

            Mockito.when(mockedFactory.createMessage()).thenReturn(mockedSOAPMessage);
            Mockito.when(mockedSOAPMessage.getSOAPPart()).thenReturn(mockedSOAPPart);
            Mockito.when(mockedSOAPPart.getEnvelope()).thenReturn(mockedEnvelope);
            Mockito.when(mockedEnvelope.getHeader()).thenReturn(mockedSOAPHeader);
            Mockito.when(mockedEnvelope.getBody()).thenReturn(mockedSOAPBody);
            Mockito.when(mockedSOAPBody.addBodyElement(any(QName.class))).thenReturn(mockedSOAPBodyElement);

            // Simulate an exception when adding SOAP elements
            Mockito.doThrow(new SOAPException("Test exception adding SOAP elements"))
                    .when(mockedSOAPHeader).addChildElement(any(QName.class));

            // Set up the instance of SktSubmitReqMessage
            SktSubmitReqMessage message = new SktSubmitReqMessage(
                    "vaspId", "vasId", "cpid", "tid", "callback", "receiver",
                    "subject", "message", "originCode", "startCID", "smilCID"
            );

            // Assert that the method throws MCMPSoapRenderException
            MCMPSoapRenderException thrownException = assertThrows(MCMPSoapRenderException.class, () -> {
                message.toSOAPMessage();
            });

            // Assert the exception message
            assertTrue(thrownException.getMessage().startsWith("[SOAP] Fail to create"));
        }
    }

    @Test
    @DisplayName("MC-SSReqME-5 : Test fromSOAPMessage throws MCMPSoapRenderException on parsing failure")
    void testFromSOAPMessageThrowsException() throws Exception {
        // Create mocks
        SOAPMessage soapMessage = Mockito.mock(SOAPMessage.class);
        SOAPBody soapBody = Mockito.mock(SOAPBody.class);

        // Configure mocks
        Mockito.when(soapMessage.getSOAPHeader()).thenReturn(Mockito.mock(SOAPHeader.class));
        Mockito.when(soapMessage.getSOAPBody()).thenReturn(soapBody);
        Mockito.when(soapBody.extractContentAsDocument()).thenThrow(new SOAPException("Test SOAPException"));

        // Create SktSubmitReqMessage instance
        SktSubmitReqMessage message = new SktSubmitReqMessage();

        // Assert that the method throws MCMPSoapRenderException
        MCMPSoapRenderException thrownException = assertThrows(MCMPSoapRenderException.class, () -> {
            message.fromSOAPMessage(soapMessage);
        });

        // Assert the exception message
        assertTrue(thrownException.getMessage().startsWith("[SOAP] Fail to create"));
    }
}