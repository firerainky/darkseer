package com.mockitotutorial.happyhotel.booking;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// With spy, we can call the real method of the object, but we can also stub the method
public class Test08Spies {

    private BookingService bookingService;

    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        paymentServiceMock = mock(PaymentService.class);
        roomServiceMock = mock(RoomService.class);
        bookingDAOMock = spy(BookingDAO.class);
        mailSenderMock = mock(MailSender.class);

        bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }

    @Test
    void should_MakeBooking_When_InputOK() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),
                2, true);

        // when
        String bookingId = bookingService.makeBooking(bookingRequest);

        // then
        verify(bookingDAOMock).save(bookingRequest);
        System.out.println("bookingId = " + bookingId);;
    }

    @Test
    void should_CancelBooking_When_InputOK() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),
                2, true);
        bookingRequest.setRoomId("1.3");
        String bookingId = "1";

        doReturn(bookingRequest).when(bookingDAOMock).get(bookingId);

        // when
        bookingService.cancelBooking(bookingId);  
    }
}
