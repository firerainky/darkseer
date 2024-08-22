package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class Test05ThrowingExceptions {

    private BookingService bookingService;

    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        paymentServiceMock = mock(PaymentService.class);
        roomServiceMock = mock(RoomService.class);
        bookingDAOMock = mock(BookingDAO.class);
        mailSenderMock = mock(MailSender.class);

        bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }

    @Test
    void should_ThrowException_When_NoRoomAvailable() {
        // given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05),
                2, false);
        when(roomServiceMock.findAvailableRoomId(bookingRequest)).thenThrow(BusinessException.class);

        // when
        Executable executable = () -> bookingService.makeBooking(bookingRequest);

        // then
        assertThrows(BusinessException.class, executable);
    }
}
